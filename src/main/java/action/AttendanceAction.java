package action;

import entity.Class;
import entity.ClassInfo;
import entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import service.ClassInfoService;
import service.ClassService;
import service.CourseService;

import java.util.List;

/**
 * 10.机构听课登记;
 */
@Controller
public class AttendanceAction extends BaseAction {

    @Autowired
    private ClassInfoService classInfoService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private ClassService classService;

    public String getInfo(){
        int courseId = Integer.parseInt(request.getParameter("cid"));
        Course course = courseService.getCourse(courseId);
        List<ClassInfo> infoList = classInfoService.getListById(courseId);

        request.getSession().setAttribute("course", course);
        request.getSession().setAttribute("classNum", classService.getListById(courseId).size());
        request.getSession().setAttribute("infoList", infoList);
        return "classInfo";
    }

    public String refreshInfo(){
        Course course = (Course) request.getSession().getAttribute("course");
        List<ClassInfo> infoList = classInfoService.getListById(course.getCourseId());

        request.getSession().setAttribute("course", course);
        request.getSession().setAttribute("classNum", classService.getListById(course.getCourseId()).size());
        request.getSession().setAttribute("infoList", infoList);
        return "refresh";
    }

    public String absence(){
        int studentId = Integer.parseInt(request.getParameter("sid"));
        ClassInfo classInfo = classInfoService.getInfo(studentId);
        int absence = classInfo.getAbsence()+1;
        classInfo.setAbsence(absence);
        double grade = 100.0;
        if(absence>0&&absence<3){
            grade = grade - absence*7.5;
        }
        else if(absence>=3&&absence<5){
            grade = grade - (absence-2)*10 -15;
        }
        else {
            grade = 0.0;
        }
        classInfo.setGrade(grade);
        classInfoService.update(classInfo);

        return "absence";
    }

}
