package yxinfo.yjh.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import yxinfo.yjh.dao.model.MeetingSign;
import yxinfo.yjh.dao.model.MeetingSignExample;

public interface MeetingSignMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table meeting_sign
     *
     * @mbg.generated
     */
    long countByExample(MeetingSignExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table meeting_sign
     *
     * @mbg.generated
     */
    int deleteByExample(MeetingSignExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table meeting_sign
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table meeting_sign
     *
     * @mbg.generated
     */
    int insert(MeetingSign record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table meeting_sign
     *
     * @mbg.generated
     */
    int insertSelective(MeetingSign record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table meeting_sign
     *
     * @mbg.generated
     */
    List<MeetingSign> selectByExample(MeetingSignExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table meeting_sign
     *
     * @mbg.generated
     */
    MeetingSign selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table meeting_sign
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") MeetingSign record, @Param("example") MeetingSignExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table meeting_sign
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") MeetingSign record, @Param("example") MeetingSignExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table meeting_sign
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(MeetingSign record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table meeting_sign
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(MeetingSign record);
}