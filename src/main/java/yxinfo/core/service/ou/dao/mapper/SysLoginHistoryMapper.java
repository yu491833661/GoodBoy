package yxinfo.core.service.ou.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import yxinfo.core.service.ou.dao.model.SysLoginHistory;
import yxinfo.core.service.ou.dao.model.SysLoginHistoryExample;

public interface SysLoginHistoryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_login_history
     *
     * @mbggenerated
     */
    int countByExample(SysLoginHistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_login_history
     *
     * @mbggenerated
     */
    int deleteByExample(SysLoginHistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_login_history
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_login_history
     *
     * @mbggenerated
     */
    int insert(SysLoginHistory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_login_history
     *
     * @mbggenerated
     */
    int insertSelective(SysLoginHistory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_login_history
     *
     * @mbggenerated
     */
    List<SysLoginHistory> selectByExample(SysLoginHistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_login_history
     *
     * @mbggenerated
     */
    SysLoginHistory selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_login_history
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") SysLoginHistory record, @Param("example") SysLoginHistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_login_history
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") SysLoginHistory record, @Param("example") SysLoginHistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_login_history
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(SysLoginHistory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_login_history
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SysLoginHistory record);
}