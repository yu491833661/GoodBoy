package yxinfo.yjh.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import yxinfo.yjh.dao.model.BlockManagerExample;
import yxinfo.yjh.dao.model.BlockManagerKey;

public interface BlockManagerMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table block_manager
     *
     * @mbg.generated
     */
    long countByExample(BlockManagerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table block_manager
     *
     * @mbg.generated
     */
    int deleteByExample(BlockManagerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table block_manager
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(BlockManagerKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table block_manager
     *
     * @mbg.generated
     */
    int insert(BlockManagerKey record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table block_manager
     *
     * @mbg.generated
     */
    int insertSelective(BlockManagerKey record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table block_manager
     *
     * @mbg.generated
     */
    List<BlockManagerKey> selectByExample(BlockManagerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table block_manager
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") BlockManagerKey record, @Param("example") BlockManagerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table block_manager
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") BlockManagerKey record, @Param("example") BlockManagerExample example);
}