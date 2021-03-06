package yxinfo.core.service.ou.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import yxinfo.core.service.ou.dao.model.EnterpriseManager;
import yxinfo.core.service.ou.dao.model.EnterpriseManagerExample;

public interface EnterpriseManagerMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table enterprise_manager
     *
     * @mbg.generated
     */
    long countByExample(EnterpriseManagerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table enterprise_manager
     *
     * @mbg.generated
     */
    int deleteByExample(EnterpriseManagerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table enterprise_manager
     *
     * @mbg.generated
     */
    int insert(EnterpriseManager record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table enterprise_manager
     *
     * @mbg.generated
     */
    int insertSelective(EnterpriseManager record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table enterprise_manager
     *
     * @mbg.generated
     */
    List<EnterpriseManager> selectByExample(EnterpriseManagerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table enterprise_manager
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") EnterpriseManager record, @Param("example") EnterpriseManagerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table enterprise_manager
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") EnterpriseManager record, @Param("example") EnterpriseManagerExample example);
}