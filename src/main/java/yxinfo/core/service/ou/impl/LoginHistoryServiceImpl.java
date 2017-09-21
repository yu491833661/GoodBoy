package yxinfo.core.service.ou.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import yxinfo.core.common.dto.PageDTO;
import yxinfo.core.common.exception.ErrorCodeCore;
import yxinfo.core.framework.exception.DctException;
import yxinfo.core.framework.service.dal.Page;
import yxinfo.core.service.ou.LoginHistoryService;
import yxinfo.core.service.ou.dao.mapper.SysIpMapper;
import yxinfo.core.service.ou.dao.mapper.SysLoginHistoryMapper;
import yxinfo.core.service.ou.dao.mapper.SysLoginRegionNumMapper;
import yxinfo.core.service.ou.dao.mapper.SysLoginRegionNumMapperExtend;
import yxinfo.core.service.ou.dao.model.*;
import yxinfo.core.service.ou.dto.LoginHistoryDTO;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 登录历史记录
 * Created by dy on 2017/4/15.
 */
@Component
@Service
public class LoginHistoryServiceImpl implements LoginHistoryService {

    private static final Logger log = LoggerFactory.getLogger( LoginHistoryServiceImpl.class );
    private static String IP_TAOBAO = "http://ip.taobao.com/service/getIpInfo.php?ip=";
    @Resource
    private SysLoginHistoryMapper loginHistoryMapper;
    @Resource
    private SysIpMapper ipMapper;
    @Resource
    private SysLoginRegionNumMapper loginRegionNumMapper;
    @Resource
    private SysLoginRegionNumMapperExtend loginRegionNumMapperExtend;

    /**
     * 根据淘宝接口获取IP地区信息
     *
     * @param loginHistory
     * @param ip
     */
    private static void getIpInfoByTaobao( SysLoginHistory loginHistory, String ip ) {
        try {
            String ret = getIpInfoByGet( new StringBuilder( IP_TAOBAO ).append( ip ).toString(), "utf-8" );
            JSONObject jsonObject = JSON.parseObject( ret );

            if ( "0".equals( jsonObject.get( "code" ).toString() ) ) {
                JSONObject data = (JSONObject) jsonObject.get( "data" );
                if ( data != null ) {
                    loginHistory.setCity( data.get( "city" ) == null ? null : data.get( "city" ).toString() );
                    loginHistory.setCountry( data.get( "country" ) == null ? null : data.get( "country" ).toString() );
                    loginHistory.setDistrict( data.get( "county" ) == null ? null : data.get( "county" ).toString() );
                    loginHistory.setIsp( data.get( "isp" ) == null ? null : data.get( "isp" ).toString() );
                    loginHistory.setProvince( data.get( "region" ) == null ? null : data.get( "region" ).toString() );
                }
            }
        } catch ( Exception e ) {
            log.info( "获取IP地区信息异常, 由于{}", e.getMessage(), e );
        }

    }

    /**
     * 获取IP地区信息
     *
     * @param url
     * @param encode
     * @return
     */
    private static String getIpInfoByGet( String url, String encode ) {

        BufferedReader reader = null;
        try {
            log.info( "获取IP地区信息, URL: {}", url );

            URL u = new URL( url );
            URLConnection conn = u.openConnection();
            conn.setConnectTimeout( 5000 );
            conn.setReadTimeout( 5000 );
            conn.setDoOutput( true );
            conn.setUseCaches( false );
            conn.connect();

            reader = new BufferedReader( new InputStreamReader( conn.getInputStream(), encode ) );
            String line = null;
            StringBuffer ret = new StringBuffer();
            while ( ( line = reader.readLine() ) != null ) {
                ret.append( line );
            }
            log.info( "获取IP地区信息: {}", ret.toString() );
            return ret.toString();

        } catch ( Exception e ) {
            log.info( "获取IP地区信息异常, 由于{}", e.getMessage(), e );
            return null;
        } finally {
            if ( reader != null ) {
                try {
                    reader.close();
                } catch ( IOException e ) {
                }
            }
        }
    }

    public static void main( String[] args ) {
        SysLoginHistory lh = new SysLoginHistory();
        getIpInfoByTaobao( lh, "125.118.106.139" );
        System.out.println( JSON.toJSONString( lh ) );
    }

    @Async
    public void addLoginHistory( Integer memberId, String terminal, String ip ) {

        if ( !StringUtils.isEmpty( ip ) ) {

            SysLoginHistory loginHistory = new SysLoginHistory();
            loginHistory.setMemberId( memberId );
            loginHistory.setTermCode( terminal );
            loginHistory.setIp( ip );
            loginHistory.setCreateAt( new Date() );

            SysIpExample eip = new SysIpExample();
            SysIpExample.Criteria cip = eip.createCriteria();
            cip.andIpEqualTo( ip );
            List<SysIp> ips = ipMapper.selectByExample( eip );
            if ( CollectionUtils.isEmpty( ips ) ) {

                // 获取IP地区信息
                getIpInfoByTaobao( loginHistory, ip );

                if ( !StringUtils.isEmpty( loginHistory.getCity() ) || !StringUtils.isEmpty( loginHistory.getCountry() )
                        || !StringUtils.isEmpty( loginHistory.getDistrict() ) || !StringUtils.isEmpty( loginHistory.getIsp() )
                        || !StringUtils.isEmpty( loginHistory.getProvince() )
                        ) {
                    try {
                        SysIp sysIp = new SysIp();
                        sysIp.setIp( ip );
                        sysIp.setCity( loginHistory.getCity() );
                        sysIp.setCountry( loginHistory.getCountry() );
                        sysIp.setDistrict( loginHistory.getDistrict() );
                        sysIp.setIsp( loginHistory.getIsp() );
                        sysIp.setProvince( loginHistory.getProvince() );
                        ipMapper.insertSelective( sysIp );
                    } catch ( Exception e ) {
                        log.error( "保存IP缓存异常, 由于{}", e.getMessage(), e );
                    }
                }

            } else {

                loginHistory.setCity( ips.get( 0 ).getCity() );
                loginHistory.setCountry( ips.get( 0 ).getCountry() );
                loginHistory.setDistrict( ips.get( 0 ).getDistrict() );
                loginHistory.setIsp( ips.get( 0 ).getIsp() );
                loginHistory.setProvince( ips.get( 0 ).getProvince() );
            }

            // 登录地区计数增加
            plusLoginRegionNum( 1, memberId, loginHistory.getCity() );

            loginHistoryMapper.insertSelective( loginHistory );
        }
    }

    public Integer plusLoginRegionNum( Integer num, Integer memberId, String region ) {
        try {
            if ( num == null || memberId == null || StringUtils.isEmpty( region ) ) {
                return -1;
            }
            int i = loginRegionNumMapperExtend.plusNum( num, memberId, region );
            if ( i <= 0 ) {
                try {
                    SysLoginRegionNum loginRegionNum = new SysLoginRegionNum();
                    loginRegionNum.setNum( num );
                    loginRegionNum.setMemberId( memberId );
                    loginRegionNum.setRegion( region );
                    return loginRegionNumMapper.insertSelective( loginRegionNum );
                } catch ( Exception e ) {
                    return loginRegionNumMapperExtend.plusNum( num, memberId, region );
                }
            }
            return i;
        } catch ( RuntimeException e ) {
            log.error( "增加订单统计数量异常, {}", e.getMessage(), e );
            return 0;
        }
    }

    public PageDTO<List<LoginHistoryDTO>> getLoginHistoryPageByMemberId( PageDTO<LoginHistoryDTO> schPage ) {

        if ( schPage == null ) {
            throw new DctException( ErrorCodeCore.DATA_NOT_NULL );
        }
        LoginHistoryDTO data = schPage.getData();
        if ( data == null ) {
            throw new DctException( ErrorCodeCore.DATA_NOT_NULL );
        }

        Page page = schPage.toModel( Page.class );
        SysLoginHistoryExample example = new SysLoginHistoryExample();
        SysLoginHistoryExample.Criteria criteria = example.createCriteria();
        criteria.andMemberIdEqualTo( data.getMemberId() );
        if ( StringUtils.isEmpty( data.getTermCode() ) ) {
            criteria.andTermCodeEqualTo( data.getTermCode() );
        }
        if ( data.getCreateAtFrom() != null ) {
            criteria.andCreateAtGreaterThanOrEqualTo( data.getCreateAtFrom() );
        }
        if ( data.getCreateAtTo() != null ) {
            criteria.andCreateAtLessThanOrEqualTo( data.getCreateAtTo() );
        }
        example.setOrderByClause( "create_at DESC" );
        example.setPage( page );

        List<SysLoginHistory> list = loginHistoryMapper.selectByExample( example );
        if ( CollectionUtils.isEmpty( list ) ) {
            PageDTO<List<LoginHistoryDTO>> retPage = new PageDTO<List<LoginHistoryDTO>>();
            retPage.setData( new ArrayList<LoginHistoryDTO>() );
            return retPage;
        }

        // 获取用户次数最多的登录城市
        SysLoginRegionNumExample lrne = new SysLoginRegionNumExample();
        SysLoginRegionNumExample.Criteria lrnc = lrne.createCriteria();
        lrnc.andMemberIdEqualTo( data.getMemberId() );
        lrne.setOrderByClause( "num DESC LIMIT 1" );
        List<SysLoginRegionNum> loginRegionNums = loginRegionNumMapper.selectByExample( lrne );
        String loginRegion = "";
        if ( !CollectionUtils.isEmpty( loginRegionNums ) ) {
            loginRegion = loginRegionNums.get( 0 ).getRegion();
        }

        List<LoginHistoryDTO> retList = new ArrayList<LoginHistoryDTO>();
        PageDTO<List<LoginHistoryDTO>> retPage = new PageDTO<List<LoginHistoryDTO>>().toDTO( page );
        for ( SysLoginHistory history : list ) {
            LoginHistoryDTO ret = new LoginHistoryDTO().toDTO( history );
            ret.setMostLgRegion( loginRegion );
            retList.add( ret );
        }
        retPage.setData( retList );
        return retPage;
    }
}
