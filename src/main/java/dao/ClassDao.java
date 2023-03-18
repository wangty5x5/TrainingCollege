package dao;

import entity.Class;

import java.util.List;

/**
 *
 */
public interface ClassDao {

    /**
     * 添加新班级;
     * @param c
     */
    public void add(Class c);

    /**
     * 更新班级信息;
     * @param c
     */
    public void update(Class c);

    /**
     * 检查班级是否存在;
     * @param classId
     * @return
     */
    public boolean isExist(int classId);

    /**
     * 根据班级编号获得班级;
     * @param classId
     * @return
     */
    public Class getClass(int classId);

    /**
     * 根据课程编号获得班级;
     * @param courseId
     * @return
     */
    public List<Class> getListById(int courseId);

    /**
     * 根据编号删除班级;
     * @param classId
     */
    public void delete(int classId);
}
