package yxinfo.core.service.ou.dao.mapper;

import org.apache.ibatis.annotations.Insert;
import yxinfo.core.service.ou.dao.model.SysLoginInfo;

public interface SysLoginInfoMapperExtend {

    @Insert( " REPLACE INTO sys_login_info ( member_id, term_code, device_brand, device_model, imei, system_ver, create_at ) " +
            "  VALUES ( #{memberId}, #{termCode}, #{deviceBrand}, #{deviceModel}, #{imei}, #{systemVer}, #{createAt} ) " )
    int replace( SysLoginInfo record );
}