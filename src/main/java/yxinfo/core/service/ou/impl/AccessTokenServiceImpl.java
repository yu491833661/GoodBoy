package yxinfo.core.service.ou.impl;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import yxinfo.core.common.exception.ErrorCodeCore;
import yxinfo.core.framework.exception.DctException;
import yxinfo.core.service.ou.AccessTokenService;
import yxinfo.core.service.ou.context.AccessTokConstants;
import yxinfo.core.service.ou.dao.mapper.SysAccessTokMapper;
import yxinfo.core.service.ou.dao.mapper.SysAccessTokMapperExtend;
import yxinfo.core.service.ou.dao.model.SysAccessTok;
import yxinfo.core.service.ou.dao.model.SysAccessTokExample;
import yxinfo.core.service.ou.dto.AccessTokenDTO;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by dy on 2017/6/28.
 */
@Component
@Service
public class AccessTokenServiceImpl implements AccessTokenService {

    @Resource
    private SysAccessTokMapper accessTokMapper;
    @Resource
    private SysAccessTokMapperExtend accessTokMapperExtend;

    public AccessTokenDTO createAccessToken( String terminal, Integer memberId ) {

        SysAccessTokExample tokExample = new SysAccessTokExample();
        tokExample.createCriteria().andMemberIdEqualTo( memberId ).andDeviceTypeEqualTo( terminal );
        accessTokMapper.deleteByExample( tokExample );

        SysAccessTok sysAccessTok = new SysAccessTok();
        sysAccessTok.setDeviceType( terminal );
        sysAccessTok.setMemberId( memberId );
        sysAccessTok.setAccessTok( UUID.randomUUID().toString().replaceAll( "-", "" ) );
        sysAccessTok.setCreateAt( new Date() );
        accessTokMapper.insertSelective( sysAccessTok );

        AccessTokenDTO token = new AccessTokenDTO().toDTO( sysAccessTok );
        token.setMemberId( null );
        token.setExpireIn( AccessTokConstants.EXPIRE_IN );
        return token;
    }

    public AccessTokenDTO updateAccessToken( String accessToken ) {
        AccessTokenDTO token = getAccessToken( accessToken );
        if ( token == null ) {
            throw new DctException( ErrorCodeCore.ACCESS_TOKEN_INVALID );
        }
        return createAccessToken( token.getDeviceType(), token.getMemberId() );
    }

    public AccessTokenDTO extendAccessToken( String accessToken ) {
        AccessTokenDTO token = getAccessToken( accessToken );
        if ( token == null ) {
            throw new DctException( ErrorCodeCore.ACCESS_TOKEN_INVALID );
        }
        long expireIn = System.currentTimeMillis() - AccessTokConstants.EXPIRE_IN;
        if ( token.getCreateAt().before( new Date( expireIn ) ) ) {
            throw new DctException( ErrorCodeCore.ACCESS_TOKEN_INVALID );
        }

        SysAccessTok update = new SysAccessTok();
        update.setAccessTok( accessToken );
        update.setCreateAt( new Date() );
        accessTokMapper.updateByPrimaryKeySelective( update );

        token.setCreateAt( update.getCreateAt() );
        token.setExpireIn( AccessTokConstants.EXPIRE_IN );
        return token;
    }

    public AccessTokenDTO getAccessToken( String accessToken ) {
        if ( StringUtils.isEmpty( accessToken ) ) {
            throw new DctException( ErrorCodeCore.ACCESS_TOKEN_INVALID );
        }
        SysAccessTok sysAccessTok = accessTokMapper.selectByPrimaryKey( accessToken );
        if ( sysAccessTok == null ) {
            return null;
        }
        AccessTokenDTO token = new AccessTokenDTO().toDTO( sysAccessTok );
        token.setExpireIn( AccessTokConstants.EXPIRE_IN );
        return token;
    }

    public AccessTokenDTO getAccessToken( Integer memberId, String terminal ) {
        if ( memberId == null || StringUtils.isEmpty( terminal ) ) {
            return null;
        }
        SysAccessTokExample example = new SysAccessTokExample();
        SysAccessTokExample.Criteria criteria = example.createCriteria();
        criteria.andMemberIdEqualTo( memberId );
        criteria.andDeviceTypeEqualTo( terminal );
        List<SysAccessTok> toks = accessTokMapper.selectByExample( example );
        if ( CollectionUtils.isEmpty( toks ) ) {
            return null;
        }
        AccessTokenDTO token = new AccessTokenDTO().toDTO( toks.get( 0 ) );
        token.setExpireIn( AccessTokConstants.EXPIRE_IN );
        return token;
    }
}
