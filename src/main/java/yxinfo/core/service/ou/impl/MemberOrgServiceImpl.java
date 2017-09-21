package yxinfo.core.service.ou.impl;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import yxinfo.core.framework.spring.SpringContext;
import yxinfo.core.framework.util.transform.ListTransformer;
import yxinfo.core.framework.util.transform.Transformer;
import yxinfo.core.service.ou.MemberOrgService;
import yxinfo.core.service.ou.OrgTerminalService;
import yxinfo.core.service.ou.dao.mapper.SysMemberOrgMapper;
import yxinfo.core.service.ou.dao.model.SysMemberOrg;
import yxinfo.core.service.ou.dao.model.SysMemberOrgExample;
import yxinfo.core.service.ou.dto.MemberOrgDTO;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dy on 2017/6/28.
 */
@Component
@Service
public class MemberOrgServiceImpl implements MemberOrgService {

    @Resource
    private OrgTerminalService orgTerminalService;
    @Resource
    private SysMemberOrgMapper memberOrgMapper;

    public void addMemberOrg( MemberOrgDTO memberOrg ) {
        if ( memberOrg.getMemberId() == null || memberOrg.getOrgId() == null ) {
            return;
        }
        SysMemberOrg add = memberOrg.toModel( SysMemberOrg.class );
        memberOrgMapper.insertSelective( add );

    }

    public void updateMemberOrg( MemberOrgDTO memberOrg ) {
        if ( memberOrg.getMemberId() == null || memberOrg.getOrgId() == null ) {
            return;
        }
        if ( memberOrg.getFcode() == null && memberOrg.getPosition() == null
                && memberOrg.getAuthStatus() == null && memberOrg.getTel() == null ) {
            return;
        }
        SysMemberOrg update = new SysMemberOrg();
        update.setPosition( memberOrg.getPosition() );
        update.setFcode( memberOrg.getFcode() );
        update.setAuthStatus( memberOrg.getAuthStatus() );
        update.setTel( memberOrg.getTel() );
        SysMemberOrgExample example = new SysMemberOrgExample();
        SysMemberOrgExample.Criteria criteria = example.createCriteria();
        criteria.andOrgIdEqualTo( memberOrg.getOrgId() );
        criteria.andMemberIdEqualTo( memberOrg.getMemberId() );
        memberOrgMapper.updateByExampleSelective( update, example );
    }

    public void deleteMemberOrg( MemberOrgDTO memberOrg ) {
        if ( memberOrg.getMemberId() == null || memberOrg.getOrgId() == null ) {
            return;
        }
        SysMemberOrgExample example = new SysMemberOrgExample();
        SysMemberOrgExample.Criteria criteria = example.createCriteria();
        criteria.andMemberIdEqualTo( memberOrg.getMemberId() );
        criteria.andOrgIdEqualTo( memberOrg.getOrgId() );
        memberOrgMapper.deleteByExample( example );
    }

    public List<MemberOrgDTO> getMemberOrgId( Integer memberId ) {
        if ( memberId == null ) {
            return null;
        }
        SysMemberOrgExample example = new SysMemberOrgExample();
        SysMemberOrgExample.Criteria criteria = example.createCriteria();
        criteria.andMemberIdEqualTo( memberId );
        List<SysMemberOrg> memberOrgs = memberOrgMapper.selectByExample( example );
        if ( !CollectionUtils.isEmpty( memberOrgs ) ) {
            return new ListTransformer<SysMemberOrg, MemberOrgDTO>().listTransform( memberOrgs, new Transformer<SysMemberOrg, MemberOrgDTO>() {
                public MemberOrgDTO transform( SysMemberOrg sysMemberOrg ) {
                    return new MemberOrgDTO().toDTO( sysMemberOrg );
                }
            } );
        }
        return null;
    }

    public List<MemberOrgDTO> getMemberOrgId( Integer memberId, String terminal ) {
        if ( memberId == null ) {
            return null;
        }
        List<MemberOrgDTO> memberOrgs = SpringContext.getBean( MemberOrgService.class ).getMemberOrgId( memberId );
        if ( !CollectionUtils.isEmpty( memberOrgs ) ) {
            List<MemberOrgDTO> retList = new ArrayList<MemberOrgDTO>( memberOrgs.size() );
            for ( MemberOrgDTO memberOrg : memberOrgs ) {
                if ( !StringUtils.isEmpty( terminal ) ) {
                    if ( orgTerminalService.checkOrgTerminalExist( memberOrg.getOrgId(), terminal ) ) {
                        retList.add( new MemberOrgDTO().toDTO( memberOrg ) );
                    }
                } else {
                    retList.add( new MemberOrgDTO().toDTO( memberOrg ) );
                }
            }
            return retList;
        }
        return null;
    }

    public MemberOrgDTO getMemberByMemberIdAndOrgId( Integer memberId, Integer orgId, String terminal ) {
        List<MemberOrgDTO> memberOrgs = null;
        if ( StringUtils.isEmpty( terminal ) ) {
            memberOrgs = SpringContext.getBean( MemberOrgService.class ).getMemberOrgId( memberId );
        } else {
            getMemberOrgId( memberId, terminal );
        }
        if ( !CollectionUtils.isEmpty( memberOrgs ) ) {
            for ( MemberOrgDTO memberOrg : memberOrgs ) {
                if ( memberOrg.getOrgId().equals( orgId ) ) {
                    return memberOrg;
                }
            }
        }
        return null;
    }
}
