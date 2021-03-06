package yxinfo.core.service.ou.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import yxinfo.core.service.ou.dao.model.SysLoginInfo;
import yxinfo.core.service.ou.dao.model.SysLoginInfoExample;

public interface SysLoginInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_login_info
     *
     * @mbggenerated
     */
    int countByExample(SysLoginInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_login_info
     *
     * @mbggenerated
     */
    int deleteByExample(SysLoginInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_login_info
     *
     * @mbggenerated
     */
    int insert(SysLoginInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_login_info
     *
     * @mbggenerated
     */
    int insertSelective(SysLoginInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_login_info
     *
     * @mbggenerated
     */
    List<SysLoginInfo> selectByExample(SysLoginInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_login_info
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") SysLoginInfo record, @Param("example") SysLoginInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_login_info
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") SysLoginInfo record, @Param("example") SysLoginInfoExample example);
}