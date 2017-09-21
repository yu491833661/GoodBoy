package yxinfo.core.service.ou.dao.mapper;

import org.apache.ibatis.annotations.Insert;
import yxinfo.core.service.ou.dao.model.SysAccessTok;

public interface SysAccessTokMapperExtend {

    @Insert( " REPLACE INTO sys_access_tok ( access_tok, device_type, create_at, member_id ) " +
            "  VALUES ( #{accessTok}, #{deviceType}, #{createAt}, #{memberId} ) " )
    int replace( SysAccessTok record );
}