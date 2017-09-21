package yxinfo.core.service.ou.dao.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface SysLoginRegionNumMapperExtend {

    @Update( " UPDATE `sys_login_region_num` SET `num` = `num` + #{num} WHERE `member_id` = #{memberId} AND `region` = #{region} " )
    int plusNum( @Param( "num" ) Integer num, @Param( "memberId" ) Integer memberId, @Param( "region" ) String region );
}