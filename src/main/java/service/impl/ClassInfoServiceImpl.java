package service.impl;

import dao.ClassInfoDao;
import entity.ClassInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.ClassInfoService;

import java.util.List;

/**
 *
 */
@Service
public class ClassInfoServiceImpl implements ClassInfoService {

    @Autowired
    private ClassInfoDao classInfoDao;

    @Override
    public void add(ClassInfo classInfo) {
        classInfoDao.add(classInfo);
    }

    @Override
    public void update(ClassInfo classInfo) {
        classInfoDao.update(classInfo);
    }

    @Override
    public boolean isExist(int studentId) {
        return classInfoDao.isExist(studentId);
    }

    @Override
    public ClassInfo getInfo(int studentId) {
        return classInfoDao.getInfo(studentId);
    }

    @Override
    public List<ClassInfo> getListById(int courseId) {
        return classInfoDao.getListById(courseId);
    }

    @Override
    public List<ClassInfo> getAll() {
        return classInfoDao.getAll();
    }

    @Override
    public List<ClassInfo> getListByName(String name){ return classInfoDao.getListByName(name); }
}
