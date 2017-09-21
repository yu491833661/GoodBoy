package yxinfo.core.service.ou.impl;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import yxinfo.core.common.dto.RequestMsg;
import yxinfo.core.service.ou.LoginInfoService;
import yxinfo.core.service.ou.dao.mapper.SysLoginInfoMapper;
import yxinfo.core.service.ou.dao.mapper.SysLoginInfoMapperExtend;
import yxinfo.core.service.ou.dao.model.SysLoginInfo;
import yxinfo.core.service.ou.dao.model.SysLoginInfoExample;
import yxinfo.core.service.ou.dto.LoginInfoDTO;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by dy on 2017/7/10.
 */
@Component
@Service
public class LoginInfoServiceImpl implements LoginInfoService {

    @Resource
    private SysLoginInfoMapper sysLoginInfoMapper;
    @Resource
    private SysLoginInfoMapperExtend sysLoginInfoMapperExtend;

    @Transactional
    public void updateLoginInfo( LoginInfoDTO loginInfo ) {
        if ( loginInfo.getMemberId() == null || StringUtils.isEmpty( loginInfo.getTermCode() ) ) {
            return;
        }
        SysLoginInfo replace = loginInfo.toModel( SysLoginInfo.class );
        replace.setCreateAt( new Date() );
        replace.setDefAppPush( false );
        sysLoginInfoMapperExtend.replace( replace );
        // 离线其他设备
        if ( !StringUtils.isEmpty( loginInfo.getImei() ) ) {
            offlineOtherImei( loginInfo.getTermCode(), loginInfo.getImei() );
        }
    }

    public void setDefAppPush( RequestMsg req ) {
        SysLoginInfo update = new SysLoginInfo();
        update.setDefAppPush( true );
        SysLoginInfoExample example = new SysLoginInfoExample();
        SysLoginInfoExample.Criteria criteria = example.createCriteria();
        criteria.andMemberIdEqualTo( req.getMemberId() );
        criteria.andTermCodeEqualTo( req.getTerminal() );
        sysLoginInfoMapper.updateByExampleSelective( update, example );
    }

    @Transactional
    public void updateLoginInfo( LoginInfoDTO loginInfo, RequestMsg req ) {
        loginInfo.setTermCode( req.getTerminal() );
        loginInfo.setMemberId( req.getMemberId() );
        updateLoginInfo( loginInfo );
    }

    /**
     * 离线其他设备
     *
     * @param terminal
     * @param imei
     */
    private void offlineOtherImei( String terminal, String imei ) {
        SysLoginInfo update = new SysLoginInfo();
        update.setDeviceBrand( "" );
        update.setDeviceModel( "" );
        update.setImei( "" );
        update.setSystemVer( "" );
        SysLoginInfoExample example = new SysLoginInfoExample();
        SysLoginInfoExample.Criteria criteria = example.createCriteria();
        criteria.andTermCodeEqualTo( terminal );
        criteria.andImeiEqualTo( imei );
        sysLoginInfoMapper.updateByExampleSelective( update, example );
    }
}
