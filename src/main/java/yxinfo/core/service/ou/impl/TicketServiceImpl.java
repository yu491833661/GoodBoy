package yxinfo.core.service.ou.impl;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import yxinfo.core.service.ou.TicketService;
import yxinfo.core.service.ou.dao.mapper.SysTicketMapper;
import yxinfo.core.service.ou.dao.mapper.SysTicketMapperExtend;
import yxinfo.core.service.ou.dao.model.SysTicket;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

/**
 * Created by dy on 2016/7/5.
 */
@Component
@Service()
public class TicketServiceImpl implements TicketService {

    @Resource
    private SysTicketMapper sysTicketMapper;
    @Resource
    private SysTicketMapperExtend sysTicketMapperExtend;

    public String createTicket( String userAt, String key ) {
        SysTicket sysTicket = new SysTicket();
        sysTicket.setUserKey( key );
        sysTicket.setUseAt( userAt );
        sysTicket.setCreateAt( new Date() );
        sysTicket.setTicket( UUID.randomUUID().toString().replaceAll( "-", "" ) );
        sysTicketMapperExtend.replace( sysTicket );
        return sysTicket.getTicket();
    }

    public boolean useTicket( String ticket, String userAt, String key ) {
        SysTicket sysTicket = sysTicketMapper.selectByPrimaryKey( ticket );
        if ( sysTicket != null ) {
            if ( !sysTicket.getUseAt().equals( userAt ) ) {
                return false;
            }
            if ( !sysTicket.getUserKey().equals( key ) ) {
                return false;
            }
            int i = sysTicketMapper.deleteByPrimaryKey( ticket );
            if ( i == 0 ) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
}
