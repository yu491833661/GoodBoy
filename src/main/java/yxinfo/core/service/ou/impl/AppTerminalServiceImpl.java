package yxinfo.core.service.ou.impl;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import yxinfo.core.framework.util.transform.ListTransformer;
import yxinfo.core.framework.util.transform.Transformer;
import yxinfo.core.service.ou.AppTerminalService;
import yxinfo.core.service.ou.dao.mapper.SysAppTerminalMapper;
import yxinfo.core.service.ou.dao.model.SysAppTerminal;
import yxinfo.core.service.ou.dao.model.SysAppTerminalExample;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dy on 2017/7/6.
 */
@Component
@Service
public class AppTerminalServiceImpl implements AppTerminalService {

    @Resource
    private SysAppTerminalMapper appTerminalMapper;

    public List<String> getAppByTerminal( String terminal ) {
        if ( StringUtils.isEmpty( terminal ) ) {
            return null;
        }
        SysAppTerminalExample example = new SysAppTerminalExample();
        SysAppTerminalExample.Criteria criteria = example.createCriteria();
        criteria.andTermCodeEqualTo( terminal );
        List<SysAppTerminal> list = appTerminalMapper.selectByExample( example );
        return new ListTransformer<SysAppTerminal, String>().listTransform( list, new Transformer<SysAppTerminal, String>() {
            public String transform( SysAppTerminal sysAppTerminal ) {
                return sysAppTerminal.getApp();
            }
        } );
    }

    public List<String> getTerminalByApp( String app ) {
        if ( StringUtils.isEmpty( app ) ) {
            return null;
        }
        SysAppTerminalExample example = new SysAppTerminalExample();
        SysAppTerminalExample.Criteria criteria = example.createCriteria();
        criteria.andAppEqualTo( app );
        List<SysAppTerminal> list = appTerminalMapper.selectByExample( example );
        return new ListTransformer<SysAppTerminal, String>().listTransform( list, new Transformer<SysAppTerminal, String>() {
            public String transform( SysAppTerminal sysAppTerminal ) {
                return sysAppTerminal.getTermCode();
            }
        } );
    }
}
