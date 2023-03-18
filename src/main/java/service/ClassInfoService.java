package service;

import entity.ClassInfo;

import java.util.List;

/**
 *
 */
public interface ClassInfoService {

    /**
     * 添加新的学员课程信息;
     * @param classInfo
     */
    public void add(ClassInfo classInfo);

    /**
     * 更新信息;
     * @param classInfo
     */
    public void update(ClassInfo classInfo);

    /**
     * 检查信息是否存在;
     * @param studentId
     * @return
     */
    public boolean isExist(int studentId);

    /**
     * 查找信息;
     * @param studentId
     */
    public ClassInfo getInfo(int studentId);

    /**
     *
     * @param courseId
     * @return
     */
    public List<ClassInfo> getListById(int courseId);

    /**
     * 获得所有信息;
     * @return
     */
    public List<ClassInfo> getAll();

    public List<ClassInfo> getListByName(String name);
}
