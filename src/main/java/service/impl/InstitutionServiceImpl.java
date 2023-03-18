package service.impl;

import dao.InstitutionDao;
import entity.Institution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.InstitutionService;

import java.util.List;

/**
 *
 */
@Service
public class InstitutionServiceImpl implements InstitutionService{

    @Autowired
    private InstitutionDao institutionDao;

    @Override
    public void add(Institution institution) {
        institutionDao.add(institution);
    }

    @Override
    public void update(Institution institution) {
        institutionDao.update(institution);
    }

    @Override
    public boolean isExist(int id) {
        return institutionDao.isExist(id);
    }

    @Override
    public boolean checkPassword(int id, String password) {
        return institutionDao.checkPassword(id, password);
    }

    @Override
    public Institution getInstitution(int id) {
        return institutionDao.getInstitution(id);
    }

    @Override
    public List<Institution> getListByName(String name) {
        return institutionDao.getListByName(name);
    }

    @Override
    public List<Institution> getListByProvince(String province) {
        return institutionDao.getListByProvince(province);
    }

    @Override
    public List<Institution> getListByCity(String city) {
        return institutionDao.getListByCity(city);
    }

    @Override
    public List<Institution> getAll() {
        return institutionDao.getAll();
    }

    @Override
    public int getId(int num) {
        return institutionDao.getId(num);
    }

    @Override
    public List<Institution> getListByAd(String province, String city){
        return institutionDao.getListByAd(province, city);
    }
}
