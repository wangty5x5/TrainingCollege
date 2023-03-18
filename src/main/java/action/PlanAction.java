package action;

import entity.Class;
import entity.Course;
import entity.Institution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import service.ClassService;
import service.CourseService;
import service.InstitutionService;
import util.dateUtil;

import java.sql.Date;
import java.util.List;

/**
 * 8.课程一览、发布计划;
 */
@Controller
public class PlanAction extends BaseAction {

    @Autowired
    private CourseService courseService;
    @Autowired
    private ClassService classService;

    public String getCourses(){
        Institution institution = (Institution) request.getSession().getAttribute("institution");
        List<Course> courses = courseService.getListById(institution.getId());
        request.getSession().setAttribute("courses", courses);
        return "courses";
    }

    public String addPlan(){
        Institution institution = (Institution) request.getSession().getAttribute("institution");

        String start = request.getParameter("start");
        int period = Integer.parseInt(request.getParameter("period"));
        String classTime = request.getParameter("time");
        int number = Integer.parseInt(request.getParameter("number"));
        String category = request.getParameter("category");

        Course course = new Course();
        course.setCourseId(courseService.getId());
        course.setInstitutionId(institution.getId());
        course.setInstitutionName(institution.getName());
        course.setStartDate(Date.valueOf(new dateUtil().changeStr(start)));
        course.setPeriod(period);
        course.setClassTime(classTime);
        course.setNumber(number);
        course.setEmptyNum(number);
        course.setCategory(category);

        int classNum = Integer.parseInt(request.getParameter("classNum"));
        switch (classNum){
            case 1:{
                Class cl = new Class();
                cl.setClassId(course.getCourseId()*10+1);
                cl.setCourseId(course.getCourseId());
                cl.setTeacher("刘老师");
                cl.setNumber(number);
                cl.setEmptyNum(number);
                cl.setPrice(this.getPrice(number)*period);
                classService.add(cl);
                break;
            }
            case 2:{
                Class cl = new Class();
                cl.setClassId(course.getCourseId()*10+1);
                cl.setCourseId(course.getCourseId());
                cl.setTeacher("孙老师");
                cl.setNumber(number/2);
                cl.setEmptyNum(number/2);
                cl.setPrice(this.getPrice(number/2)*period);
                classService.add(cl);

                cl.setClassId(course.getCourseId()*10+2);
                cl.setTeacher("李老师");
                cl.setNumber(number/2+number%2);
                cl.setEmptyNum(number/2+number%2);
                cl.setPrice(this.getPrice(number/2+number%2)*period);
                classService.add(cl);
                break;
            }
            case 3:{
                Class cl = new Class();
                cl.setClassId(course.getCourseId()*10+1);
                cl.setCourseId(course.getCourseId());
                cl.setTeacher("钱老师");
                cl.setNumber(number/3);
                cl.setEmptyNum(number/3);
                cl.setPrice(this.getPrice(number/3)*period);
                classService.add(cl);

                cl.setClassId(course.getCourseId()*10+2);
                cl.setTeacher("吕老师");
                cl.setNumber(number/3);
                cl.setEmptyNum(number/3);
                cl.setPrice(this.getPrice(number/3)*period);
                classService.add(cl);

                cl.setClassId(course.getCourseId()*10+3);
                cl.setTeacher("郑老师");
                cl.setNumber(number/3+number%3);
                cl.setEmptyNum(number/3+number%3);
                cl.setPrice(this.getPrice(number/3+number%3)*period);
                classService.add(cl);
                break;
            }
            default:break;
        }
        courseService.add(course);
        return "plan";
    }

    /**
     * 每节课价格;
     * @param number 班级人数
     * @return
     */
    private double getPrice(int number){
        double price = 0.0;
        if(number<20)
            price = 300.0;
        else if(number>=20&&number<40)
            price = 250.0;
        else if(number>=40&&number<60)
            price = 200.0;
        else
            price = 120.0;

        return price;
    }
}
