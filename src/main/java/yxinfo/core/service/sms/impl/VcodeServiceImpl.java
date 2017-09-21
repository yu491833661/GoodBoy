package yxinfo.core.service.sms.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import yxinfo.core.common.error.ErrorCode;
import yxinfo.core.common.validation.Add;
import yxinfo.core.common.validation.Select;
import yxinfo.core.common.validation.Valid;
import yxinfo.core.common.validation.Validator;
import yxinfo.core.framework.exception.DctException;
import yxinfo.core.framework.util.HttpURLConnectionUtil;
import yxinfo.core.framework.util.RandomUtil;
import yxinfo.core.service.sms.VcodeService;
import yxinfo.core.service.sms.context.ZjpushContext;
import yxinfo.core.service.sms.dao.mapper.EvtVcodeMapper;
import yxinfo.core.service.sms.dao.mapper.EvtVcodeMapperExtend;
import yxinfo.core.service.sms.dao.mapper.EvtVcodeNumMapper;
import yxinfo.core.service.sms.dao.mapper.EvtVcodeTemMapper;
import yxinfo.core.service.sms.dao.model.EvtVcode;
import yxinfo.core.service.sms.dao.model.EvtVcodeExample;
import yxinfo.core.service.sms.dao.model.EvtVcodeNum;
import yxinfo.core.service.sms.dao.model.EvtVcodeTem;
import yxinfo.core.service.sms.dto.EvtVcodeDTO;

import javax.annotation.Resource;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 短信验证码服务
 * Created by dy on 2016/6/27.
 */
@Component
@Service
public class VcodeServiceImpl implements VcodeService {

    private static final Logger log = LoggerFactory.getLogger( VcodeServiceImpl.class );
    @Resource
    private EvtVcodeMapper evtVcodeDal;
    @Resource
    private EvtVcodeMapperExtend evtVcodeMapperExtend;
    @Resource
    private EvtVcodeTemMapper evtVcodeTemDal;
    @Resource
    private EvtVcodeNumMapper evtVcodeNumDal;

    @Validator
    @Transactional( noRollbackFor = DctException.class )
    public void sendVCode( @Valid( groups = Add.class ) EvtVcodeDTO vcode ) {

        // 是否有未验证通过的短信记录
        EvtVcode evtVcode = getVcode( vcode.getMobile(), vcode.getUseAt() );
        if ( evtVcode != null && evtVcode.getCreateAt() != null ) {
            // 时间间隔 < 短信发送间隔限制
            if ( ( new Date().getTime() - evtVcode.getCreateAt().getTime() ) / 1000 < 60 ) {
                throw new DctException( ErrorCode.Sms.VCODE_TOO_OFTEN );
            }
        }

        // 短信计数限制
        EvtVcodeNum evtVcodeNum = evtVcodeNumDal.selectByPrimaryKey( vcode.getMobile() );
        if ( evtVcodeNum == null ) {
            EvtVcodeNum addNum = new EvtVcodeNum();
            addNum.setFlagAt( new Date() );
            addNum.setMobile( vcode.getMobile() );
            addNum.setNum( 1 );
            evtVcodeNumDal.insertSelective( addNum );
        } else {
            // 获取次数大于限制
            if ( evtVcodeNum.getNum() >= 11 ) {
                throw new DctException( ErrorCode.Sms.VCODE_TOO_OFTEN_DAY );
            }
            if ( ( new Date().getTime() - evtVcodeNum.getFlagAt().getTime() ) / 1000 >= 24 * 3600 ) {
                EvtVcodeNum update = new EvtVcodeNum();
                update.setMobile( evtVcodeNum.getMobile() );
                update.setNum( 0 );
                update.setFlagAt( new Date() );
                evtVcodeNumDal.updateByPrimaryKeySelective( update );
            } else {
                EvtVcodeNum update = new EvtVcodeNum();
                update.setMobile( evtVcodeNum.getMobile() );
                update.setNum( evtVcodeNum.getNum() + 1 );
                evtVcodeNumDal.updateByPrimaryKeySelective( update );
            }
        }

        String smsTem = "您的短信验证码为{0}。";
        // 获取短信模板
        EvtVcodeTem evtVcodeTem = evtVcodeTemDal.selectByPrimaryKey( vcode.getUseAt() );
        if ( evtVcodeTem != null ) {
            smsTem = evtVcodeTem.getTem();
        }

        // 组装短信
        List<String> msgs = new ArrayList<String>();
        msgs.add( vcode.getFcode() );
        if ( vcode.getEctMsg() != null ) {
            msgs.addAll( vcode.getEctMsg() );
        }
        String msg = MessageFormat.format( smsTem, msgs );

        // 保存短信验证信息
        EvtVcode code = vcode.toModel( EvtVcode.class );
        code.setCreateAt( new Date() );
        code.setErrCount( 0 );
        evtVcodeMapperExtend.replace( code );

        // 发送短信验证码
        String ret = null;
        long times = System.currentTimeMillis();
        try {
            log.error( "向[{}]下发短信[{}]", vcode.getMobile(), msg );
            String getData = MessageFormat.format( ZjpushContext.DATA_TEM, ZjpushContext.USR, ZjpushContext.PWD,
                    vcode.getMobile(), URLEncoder.encode( msg, "gbk" ), ZjpushContext.EXTDSRC_ID );
            ret = HttpURLConnectionUtil.get( ZjpushContext.URL, getData, "gbk" );
            times = System.currentTimeMillis() - times;
            log.error( "向[{}]下发短信返回[{}], 耗时[{}]ms", vcode.getMobile(), ret, times );
        } catch ( Exception e ) {
            times = System.currentTimeMillis() - times;
            log.error( "下发短信验证码异常, 短信网关返回信息[{}], 耗时[{}]ms, {}", ret, times, e.getMessage(), e );
        }
    }

    @Validator
    @Transactional( noRollbackFor = DctException.class )
    public void sendVCodeCrtCode( @Valid( groups = Add.class ) EvtVcodeDTO vcode ) {
        vcode.setFcode( RandomUtil.generateString( 5, RandomUtil.NUMBER_CHAR ) );
        sendVCode( vcode );
    }

    @Validator
    public void checkVCode( @Valid( groups = Select.class ) EvtVcodeDTO vcodeDTO ) {
        // 获取短信验证详情
        EvtVcode vcode = getVcode( vcodeDTO.getMobile(), vcodeDTO.getUseAt() );
        // 验证码不存在
        if ( vcode == null ) {
            throw new DctException( ErrorCode.Sms.VCODE_NOT_SEND );
        }
        // 验证码验证超限
        if ( vcode.getErrCount() >= 5 ) {
            throw new DctException( ErrorCode.Sms.VCODE_INVALID );
        }
        // 验证码超时
        if ( ( new Date().getTime() - vcode.getCreateAt().getTime() ) / 1000 >= 3 * 60 ) {
            throw new DctException( ErrorCode.Sms.VCODE_EXPIRE );
        }
        // 验证码不正确
        if ( !vcodeDTO.getFcode().equals( vcode.getFcode() ) ) {
            // 更新错误次数+1
            EvtVcode update = new EvtVcode();
            update.setErrCount( vcode.getErrCount() );
            update.setId( vcode.getId() );
            evtVcodeDal.updateByPrimaryKeySelective( update );
            throw new DctException( ErrorCode.Sms.VCODE_NOT_MEET );
        }
        // 验证通过
        evtVcodeDal.deleteByPrimaryKey( vcode.getId() );
    }

    /**
     * 获取验证码记录
     *
     * @param mobile
     * @param useAt
     * @return
     */
    private EvtVcode getVcode( String mobile, String useAt ) {
        EvtVcodeExample example = new EvtVcodeExample();
        EvtVcodeExample.Criteria criteria = example.createCriteria();
        criteria.andMobileEqualTo( mobile );
        criteria.andUseAtEqualTo( useAt );
        List<EvtVcode> list = evtVcodeDal.selectByExample( example );
        EvtVcode evtVcode = null;
        if ( !CollectionUtils.isEmpty( list ) ) {
            evtVcode = list.get( 0 );
        }
        return evtVcode;
    }
}



