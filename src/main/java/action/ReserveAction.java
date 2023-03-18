package action;

import entity.*;
import entity.Class;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import service.*;
import util.CourseUtil;
import util.discountUtil;
import util.vipUtil;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *4.学员预订课程;
 */
@Controller
public class ReserveAction extends BaseAction {

    @Autowired
    private InstitutionService institutionService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private ClassService classService;
    @Autowired
    private ClassInfoService classInfoService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private ManagerService managerService;

    public String InstitutionSearch(){
        List<Institution> institutions = institutionService.getAll();
        for(Institution inst:institutions){
            if("W".equals(inst.getApprovalState())){
                institutions.remove(inst);
            }
        }
        request.getSession().setAttribute("institutions", institutions);
        return "institutions";
    }

    public String siftInst(){
        String province = request.getParameter("province");
        String city = request.getParameter("city");
        List<Institution> institutions;
        if("".equals(province)){
            institutions = institutionService.getAll();
        }
        else {
            if("".equals(city))
                institutions = institutionService.getListByProvince(province);
            else
                institutions = institutionService.getListByAd(province, city);
        }
        CopyOnWriteArrayList<Institution> arrayList = new CopyOnWriteArrayList<Institution>(institutions);
        for(Institution inst:arrayList){
            if("W".equals(inst.getApprovalState())){
                arrayList.remove(inst);
            }
        }
        request.getSession().setAttribute("institutions", arrayList);
        return "siftInst";
    }

    public String courseSearch(){
        int institutionId = Integer.parseInt(request.getParameter("institutionId"));
        Institution institution = institutionService.getInstitution(institutionId);
        List<Course> courses = courseService.getListById(institutionId);
        List<CourseUtil> courseList = this.getUtils(courses);
        request.getSession().setAttribute("institution", institution);
        request.getSession().setAttribute("courseList", courseList);
        return "courses";
    }

    public String siftCourse(){
        Institution institution = (Institution) request.getSession().getAttribute("institution");
        List<Course> courses = courseService.getListById(institution.getId());
        String category = request.getParameter("category");
        String time = request.getParameter("time");
        CopyOnWriteArrayList<Course> arrayList = new CopyOnWriteArrayList<Course>(courses);
        if(!"all".equals(category)){
            for(Course course:arrayList){
                if(!category.equals(course.getCategory()))
                    arrayList.remove(course);
            }
        }
        if(!"all".equals(time)){
            for(Course course:arrayList){
                if(!time.equals(course.getClassTime()))
                    arrayList.remove(course);
            }
        }
        List<CourseUtil> courseList = this.getUtils(arrayList);
        request.getSession().setAttribute("institution", institution);
        request.getSession().setAttribute("courseList", courseList);
        return "siftCourse";
    }

    public String getCourse(){
        int courseId = Integer.parseInt(request.getParameter("cid"));
        Course course = courseService.getCourse(courseId);
        List<Class> classes = classService.getListById(courseId);
        request.getSession().setAttribute("course", course);
        request.getSession().setAttribute("classes", classes);
        request.getSession().setAttribute("errorMessage", "");
        return "getCourse";
    }

    public String reserve(){
        Student student = (Student) request.getSession().getAttribute("student");
        Course course = (Course) request.getSession().getAttribute("course");
        List<Class> classes = classService.getListById(course.getCourseId());
        int count = Integer.parseInt(request.getParameter("count"));
        int max = Integer.parseInt(request.getParameter("max"));

        double price = classes.get(0).getPrice() * count;
        //生成order;
        Order order = new Order();
        order.setOrderId(orderService.getId());
        order.setStudentMail(student.getMail());
        order.setStudentName(student.getName());
        order.setCourseId(course.getCourseId());
        order.setInstitutionId(course.getInstitutionId());
        order.setNumber(count);
        order.setPrice(price);

        //支付操作;
        Account account = accountService.getAccount(student.getAccountId());
        if(account.getMoney()>=new discountUtil().getDiscount(student.getViPrank())*price){
            price = new discountUtil().getDiscount(student.getViPrank())*price;
            order.setState("NW");
            account.setMoney(account.getMoney()-price);
            accountService.update(account);
            int credit = (int) Math.floor(price/10);
            student.setCredit(student.getCredit()+credit);
            student.setViPrank(new vipUtil().getRank(student.getCredit()));
            studentService.update(student);
            Manager manager = managerService.getManager("707");
            manager.setProfit(manager.getProfit()+price);
            managerService.update(manager);
            request.getSession().setAttribute("errorMessage", "订单已完成!");
        }
        else {
            order.setState("UP");
            String error = "余额不足,已自动保存订单;生成订单15分钟内未完成支付,订单将自动取消!";
            request.getSession().setAttribute("errorMessage", error);
        }
        orderService.add(order);

        //生成classInfo;修改class信息;
        int courseNum = course.getNumber() - course.getEmptyNum();
        for(int i=1;i<=count;i++){
            String str = "stu_" + i;
            String stuName = request.getParameter(str);
            courseNum++;

            ClassInfo classInfo = new ClassInfo();
            classInfo.setStudentId(course.getCourseId()*1000+courseNum);
            classInfo.setCourseId(course.getCourseId());
            classInfo.setOrderId(order.getOrderId());
            classInfo.setStudentName(stuName);
            classInfo.setAbsence(0);
            if(max==3){
                String string = "cl_" + i;
                int cl = Integer.parseInt(request.getParameter(string));
                int classId = course.getCourseId()*10 + cl;
                classInfo.setClassId(classId);
                Class elect = classes.get(cl-1);
                elect.setEmptyNum(elect.getEmptyNum()-1);
                classService.update(elect);
            }
            else {
                classInfo.setClassId(0);
            }
            classInfoService.add(classInfo);
        }

        //修改course信息;
        course.setEmptyNum(course.getEmptyNum()-count);
        courseService.update(course);

        request.getSession().setAttribute("course", course);
        request.getSession().setAttribute("classes", classes);
        return "reserve";
    }

    public String saveOrder(){
        Student student = (Student) request.getSession().getAttribute("student");
        Course course = (Course) request.getSession().getAttribute("course");
        List<Class> classes = classService.getListById(course.getCourseId());
        int count = Integer.parseInt(request.getParameter("count"));
        int max = Integer.parseInt(request.getParameter("max"));

        //生成order;
        Order order = new Order();
        order.setOrderId(orderService.getId());
        order.setStudentMail(student.getMail());
        order.setStudentName(student.getName());
        order.setCourseId(course.getCourseId());
        order.setInstitutionId(course.getInstitutionId());
        order.setNumber(count);
        order.setState("UP");
        order.setPrice(classes.get(0).getPrice() * count);
        orderService.add(order);

        //生成classInfo;修改class信息;
//        int courseNum = course.getNumber() - course.getEmptyNum();
        List<String> students = new ArrayList<String>();
        List<Integer> numbers = new ArrayList<Integer>();
        for(int i=1;i<=count;i++){
            String str = "stu_" + i;
            String stuName = request.getParameter(str);
            students.add(stuName);
//            courseNum++;

//            ClassInfo classInfo = new ClassInfo();
//            classInfo.setStudentId(course.getCourseId()*1000+courseNum);
//            classInfo.setCourseId(course.getCourseId());
//            classInfo.setOrderId(order.getOrderId());
//            classInfo.setStudentName(stuName);
//            classInfo.setAbsence(0);
            if(max==3){
                String string = "cl_" + i;
                int cl = Integer.parseInt(request.getParameter(string));
                int classId = course.getCourseId()*10 + cl;
                numbers.add(classId);
//                classInfo.setClassId(classId);
//                Class elect = classes.get(cl-1);
//                elect.setEmptyNum(elect.getEmptyNum()-1);
//                classService.update(elect);
            }
//            classInfoService.add(classInfo);
        }
        this.TimeBomb(order.getOrderId(),students,numbers);

        //修改course信息;
//        course.setEmptyNum(course.getEmptyNum()-count);
//        courseService.update(course);

        request.getSession().setAttribute("course", course);
        request.getSession().setAttribute("classes", classes);
        request.getSession().setAttribute("errorMessage", "生成订单15分钟内未完成支付,订单将自动取消!");
        return "save";
    }

    private List<CourseUtil> getUtils(List<Course> courses){
        List<CourseUtil> utils = new ArrayList<CourseUtil>();
        for(Course course:courses){
            CourseUtil util = new CourseUtil();
            util.setCourseId(course.getCourseId());
            util.setCategory(course.getCategory());
            util.setClassNum(classService.getListById(course.getCourseId()).size());
            util.setStart(course.getStartDate());
            util.setPeriod(course.getPeriod());
            util.setTime(course.getClassTime());
            util.setNumber(course.getNumber());
            util.setEmptyNum(course.getEmptyNum());
            utils.add(util);
        }
        return utils;
    }

    private void TimeBomb(final int orderId, final List<String> students, final List<Integer> numbers){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Order order = orderService.getOrder(orderId);
                if(order.getState()!="UP"){
                    Course course = courseService.getCourse(order.getCourseId());
                    List<Class> classes = classService.getListById(course.getCourseId());
                    int courseNum = course.getNumber() - course.getEmptyNum();
                    for(int i=0;i<students.size();i++){
                        ClassInfo classInfo = new ClassInfo();
                        courseNum++;
                        classInfo.setStudentId(course.getCourseId()*1000+courseNum);
                        classInfo.setStudentName(students.get(i));
                        classInfo.setCourseId(course.getCourseId());
                        classInfo.setOrderId(order.getOrderId());
                        classInfo.setAbsence(0);
                        if(numbers.size()!=0){
                            classInfo.setClassId(numbers.get(i));
                            Class cl = classes.get(numbers.get(i)%10 - 1);
                            cl.setEmptyNum(cl.getEmptyNum()-1);
                            classService.update(cl);
                        }else{
                            classInfo.setClassId(0);
                        }
                        classInfoService.add(classInfo);
                    }
                    course.setEmptyNum(course.getNumber()-courseNum);
                    courseService.update(course);
                }
                else
                    orderService.delete(orderId);
            }
        }, 60*1000);

        timer.cancel();
    }
}
