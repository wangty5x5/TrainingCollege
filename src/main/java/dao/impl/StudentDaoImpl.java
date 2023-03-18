package dao.impl;

import dao.StudentDao;
import entity.Student;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 */
@Transactional(rollbackFor = Exception.class)
@Repository("StudentDao")
public class StudentDaoImpl implements StudentDao {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public void add(Student student) {
        sessionFactory.getCurrentSession().save(student);
    }

    @Override
    public boolean isExist(String mail){
        if(sessionFactory.getCurrentSession().get(Student.class, mail)!=null){
            return true;
        }
        return false;
    }

    @Override
    public Student find(String mail) {
        return (Student) sessionFactory.getCurrentSession().get(Student.class, mail);
    }

    @Override
    public boolean checkPassword(String mail, String password){
        Student student = this.find(mail);
        if(password.equals(student.getPassword()))
            return true;
        return false;
    }

    @Override
    public void update(Student student) {
        sessionFactory.getCurrentSession().update(student);
    }

    @Override
    public List<Student> getAllStudents() {
        String hql = "from " + Student.class.getName();
        return (List)sessionFactory.getCurrentSession().createQuery(hql).list();
    }
}
