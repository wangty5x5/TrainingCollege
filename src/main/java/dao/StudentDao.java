package dao;

import entity.Student;

import java.util.List;

/**
 *
 */
public interface StudentDao {

    /**
     * 注册学员;
     * @param student
     */
    public void add(Student student);

    /**
     * 检查邮箱是否注册;
     * @param mail
     * @return
     */
    public boolean isExist(String mail);

    /**
     * 通过邮箱号查找学员;
     * @param mail
     * @return
     */
    public Student find(String mail);

    /**
     * 检查密码是否正确;
     * @param mail
     * @param password
     * @return
     */
    public boolean checkPassword(String mail, String password);

    /**
     * 更新学员密码、会员等级、积分;
     * @param student
     */
    public void update(Student student);

    /**
     * 获得全部学员;
     * @return
     */
    public List<Student> getAllStudents();

}
