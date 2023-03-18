package dao;

import entity.Institution;

import java.util.List;

/**
 *
 */
public interface InstitutionDao {

    /**
     * 新增机构;
     * @param institution
     */
    public void add(Institution institution);

    /**
     * 更新机构信息;
     * @param institution
     */
    public void update(Institution institution);

    /**
     * 检查机构是否存在;
     * @param id
     * @return
     */
    public boolean isExist(int id);

    /**
     * 检查密码是否正确;
     * @param id
     * @param password
     * @return
     */
    public boolean checkPassword(int id, String password);

    /**
     * 根据编号查找机构;
     * @param id
     * @return
     */
    public Institution getInstitution(int id);

    /**
     * 根据名称查找机构;
     * @param name
     * @return
     */
    public List<Institution> getListByName(String name);

    /**
     * 根据省份查找机构;
     * @param province
     * @return
     */
    public List<Institution> getListByProvince(String province);

    /**
     * 根据城市查找机构;
     * @param city
     * @return
     */
    public List<Institution> getListByCity(String city);

    /**
     * 获得所有机构;
     * @return
     */
    public List<Institution> getAll();

    /**
     * 获得可使用的编号;
     * @param num
     * @return
     */
    public int getId(int num);

    /**
     * 根据省市获得机构;
     * @param province
     * @param city
     * @return
     */
    public List<Institution> getListByAd(String province, String city);
}
