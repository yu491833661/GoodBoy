package yxinfo.core.service.sms.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import yxinfo.core.service.sms.dao.model.EvtVcode;
import yxinfo.core.service.sms.dao.model.EvtVcodeExample;

public interface EvtVcodeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table evt_vcode
     *
     * @mbggenerated
     */
    int countByExample(EvtVcodeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table evt_vcode
     *
     * @mbggenerated
     */
    int deleteByExample(EvtVcodeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table evt_vcode
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table evt_vcode
     *
     * @mbggenerated
     */
    int insert(EvtVcode record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table evt_vcode
     *
     * @mbggenerated
     */
    int insertSelective(EvtVcode record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table evt_vcode
     *
     * @mbggenerated
     */
    List<EvtVcode> selectByExample(EvtVcodeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table evt_vcode
     *
     * @mbggenerated
     */
    EvtVcode selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table evt_vcode
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") EvtVcode record, @Param("example") EvtVcodeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table evt_vcode
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") EvtVcode record, @Param("example") EvtVcodeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table evt_vcode
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(EvtVcode record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table evt_vcode
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(EvtVcode record);
}