package yxinfo.core.service.ou.impl;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import yxinfo.core.service.ou.MemberMajorService;
import yxinfo.core.service.ou.dao.mapper.SysMemberMajorMapper;
import yxinfo.core.service.ou.dao.model.SysMemberMajor;
import yxinfo.core.service.ou.dao.model.SysMemberMajorExample;

import javax.annotation.Resource;

/**
 * Created by dy on 2017/9/20.
 */
@Component
@Service
public class MemberMajorServiceImpl implements MemberMajorService {

    @Resource
    private SysMemberMajorMapper memberMajorMapper;

    public void addMemberMajor( Integer memberId, String major ) {
        SysMemberMajor memberMajor = new SysMemberMajor();
        memberMajor.setMemberId( memberId );
        memberMajor.setMajor( major );
        memberMajorMapper.insert( memberMajor );
    }

    public void deleteMemberMajor( Integer memberId ) {
        SysMemberMajorExample example = new SysMemberMajorExample();
        example.createCriteria().andMemberIdEqualTo( memberId );
        memberMajorMapper.deleteByExample( example );
    }
}
