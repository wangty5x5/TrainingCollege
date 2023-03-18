package service.impl;

import dao.ClassDao;
import entity.Class;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.ClassService;

import java.util.List;

/**
 *
 */
@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    private ClassDao classDao;

    @Override
    public void add(Class c) {
        classDao.add(c);
    }

    @Override
    public void update(Class c) {
        classDao.update(c);
    }

    @Override
    public boolean isExist(int classId) {
        return classDao.isExist(classId);
    }

    @Override
    public Class getClass(int classId) {
        return classDao.getClass(classId);
    }

    @Override
    public List<Class> getListById(int courseId) {
        return classDao.getListById(courseId);
    }

    @Override
    public void delete(int classId) {
        classDao.delete(classId);
    }
}
