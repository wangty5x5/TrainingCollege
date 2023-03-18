package service.impl;

import dao.StudentDao;
import entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.StudentService;

import java.util.List;

/**
 *
 */

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentDao studentDao;

    @Override
    public void register(Student student) {
        studentDao.add(student);
    }

    @Override
    public boolean isExist(String mail) {
        return studentDao.isExist(mail);
    }

    @Override
    public Student getStudent(String mail) {
        return studentDao.find(mail);
    }

    @Override
    public boolean checkPassword(String mail, String password) {
        return studentDao.checkPassword(mail, password);
    }

    @Override
    public void update(Student student) {
        studentDao.update(student);
    }

    @Override
    public List<Student> getAll() {
        return studentDao.getAllStudents();
    }
}
