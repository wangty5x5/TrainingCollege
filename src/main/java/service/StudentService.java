package service;

import entity.Student;

import java.util.List;

/**
 *
 */
public interface StudentService {

    /**
     * 注册新学员;
     * @param student
     */
    public void register(Student student);

    /**
     * 检查邮箱是否注册;
     * @param mail
     * @return
     */
    public boolean isExist(String mail);

    /**
     * 获得学员;
     * @param mail
     * @return
     */
    public Student getStudent(String mail);

    /**
     * 检查密码是否正确;
     * @param mail
     * @param password
     * @return
     */
    public boolean checkPassword(String mail, String password);

    /**
     * 更新学员信息;
     * @param student
     */
    public void update(Student student);

    /**
     * 获得全部学员;
     * @return
     */
    public List<Student> getAll();
}
