package yxinfo.core.service.sms.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import yxinfo.core.framework.util.HttpURLConnectionUtil;
import yxinfo.core.service.ou.OrgFeeService;
import yxinfo.core.service.sms.SmsService;
import yxinfo.core.service.sms.context.OrgFeeItem;
import yxinfo.core.service.sms.context.SMSRecordType;
import yxinfo.core.service.sms.context.ZjpushContext;
import yxinfo.core.service.sms.dao.mapper.EvtSmsRecordMapper;
import yxinfo.core.service.sms.dao.model.EvtSmsRecord;

import javax.annotation.Resource;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.List;

/**
 * 短信服务
 * Created by dy on 2016/6/27.
 */
@Component
@Service
public class SmsServiceImpl implements SmsService {

    private static final Logger log = LoggerFactory.getLogger( SmsServiceImpl.class );
    @Resource
    private OrgFeeService orgFeeService;
    @Resource
    private EvtSmsRecordMapper smsRecordMapper;

    public boolean sendSMS( Integer orgId, List<String> mobiles, String context ) {
        if ( CollectionUtils.isEmpty( mobiles ) || StringUtils.isEmpty( context ) ) {
            return true;
        }
        // 判断组织余额
        if ( orgId != null ) {
            if ( !orgFeeService.checkBal( orgId, OrgFeeItem.SMS_NTF, mobiles.size() ) ) {
                log.warn( "组织ID[{}]余额不足, 停止下发短信, 下发目标[{}], 短信内容[{}]", orgId, mobiles, context );
                return true;
            }
        }

        Short success = 0;
        StringBuffer successMobiles = new StringBuffer();
        String ret = null;
        for ( String mobile : mobiles ) {
            long times = System.currentTimeMillis();
            try {
                String getData = MessageFormat.format( ZjpushContext.DATA_TEM, ZjpushContext.USR, ZjpushContext.PWD,
                        mobile, URLEncoder.encode( context, "gbk" ), ZjpushContext.EXTDSRC_ID );
                log.info( "向[{}]下发短信[{}]", mobile, context );
                ret = HttpURLConnectionUtil.get( ZjpushContext.URL, getData, "gbk" );
                times = System.currentTimeMillis() - times;
                log.info( "向[{}]下发短信返回[{}], 耗时[{}]ms", mobile, ret, times );
                if ( ret != null && ret.startsWith( "0," ) ) {
                    // 下发成功
                    success++;
                    successMobiles.append( mobile ).append( "," );
                }
            } catch ( Exception e ) {
                times = System.currentTimeMillis() - times;
                log.error( "下发短信异常, 返回信息[{}], 耗时[{}]ms, {}", ret, times, e.getMessage(), e );
            }
        }
        if ( success > 0 && orgId != null ) {
            // 添加短信记录
            int relId = addSMSRecord( orgId, SMSRecordType.COMMON, successMobiles.toString(), success, context );
            // 计费
            orgFeeService.expend( orgId, OrgFeeItem.SMS_NTF, mobiles.size(), relId );
            log.info( "组织ID[{}]普通短信[{}]条, 计费项目[{}]", orgId, success, OrgFeeItem.SMS_NTF );
        }
        return true;
    }

    /**
     * 添加短信记录
     *
     * @param orgId
     * @param type
     * @param mobiles
     * @param num
     * @param context
     * @return
     */
    private int addSMSRecord( Integer orgId, String type, String mobiles, Short num, String context ) {
        EvtSmsRecord evtSmsRecord = new EvtSmsRecord();
        evtSmsRecord.setOrgid( orgId );
        evtSmsRecord.setFtype( type );
        evtSmsRecord.setNum( num );
        evtSmsRecord.setMobiles( mobiles );
        evtSmsRecord.setContent( context );
        smsRecordMapper.insertSelective( evtSmsRecord );
        return evtSmsRecord.getId();
    }
}
