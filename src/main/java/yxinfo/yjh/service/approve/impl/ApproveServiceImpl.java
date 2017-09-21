package yxinfo.yjh.service.approve.impl;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import yxinfo.core.common.dto.PageDTO;
import yxinfo.core.common.dto.RequestMsg;
import yxinfo.core.common.exception.ErrorCodeCore;
import yxinfo.core.common.validation.Valid;
import yxinfo.core.common.validation.Validator;
import yxinfo.core.framework.exception.DctException;
import yxinfo.core.framework.service.dal.Page;
import yxinfo.core.framework.util.StringUtil;
import yxinfo.core.service.ou.MemberService;
import yxinfo.core.service.ou.dto.MemberDTO;
import yxinfo.yjh.dao.mapper.ApproveCoreMapper;
import yxinfo.yjh.dao.mapper.ArticleCoreMapper;
import yxinfo.yjh.dao.model.ApproveCore;
import yxinfo.yjh.dao.model.ApproveCoreExample;
import yxinfo.yjh.dao.model.ArticleCore;
import yxinfo.yjh.dao.model.ArticleCoreExample;
import yxinfo.yjh.service.approve.ApproveService;
import yxinfo.yjh.service.approve.context.ApproveStatus;
import yxinfo.yjh.service.approve.context.ApproveType;
import yxinfo.yjh.service.approve.dto.ApproveCoreDTO;
import yxinfo.yjh.service.approve.validation.AddApprove;
import yxinfo.yjh.service.approve.validation.Approve;
import yxinfo.yjh.service.article.ArticleBaseService;
import yxinfo.yjh.service.article.context.ArticleStatus;
import yxinfo.yjh.service.article.dto.ArticleCoreDTO;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author jn
 * @date 2017/9/15 14:02
 **/
@Component
public class ApproveServiceImpl implements ApproveService {

    @Resource
    private ApproveCoreMapper approveCoreMapper;
    @Resource
    private ArticleCoreMapper articleCoreMapper;

    @Resource
    private MemberService memberService;
    @Resource
    private ArticleBaseService baseService;

    @Override
    @Transactional
    @Validator
    public void addApprove( @Valid( groups = AddApprove.class ) ApproveCoreDTO approveCoreDTO, boolean clearApprove ) {

        if ( !ApproveType.ARTICLE.equals( approveCoreDTO.getFtype() )
                && !ApproveType.MEMBER.equals( approveCoreDTO.getFtype() )
                && !ApproveType.QUESTION.equals( approveCoreDTO.getFtype() ) ) {
            throw new DctException( ErrorCodeCore.NOT_NULL );
        }

        // TODO 获取审核人员id列表
        List<Integer> approveMemberList = new ArrayList<>();
        if ( ApproveType.MEMBER.equals( approveCoreDTO.getFtype() ) ) {
            // 获取学校管理员
        } else {
            // 获取版主列表
            ArticleCoreDTO coreDTO = baseService.getArticleDetail( approveCoreDTO.getRelId() );
            approveMemberList.add( 4 );
            approveMemberList.add( 5 );
        }
        if ( CollectionUtils.isEmpty( approveMemberList ) ) {
            throw new DctException( ErrorCodeCore.Approve.NO_APPROVE_AVAILABLE );
        }

        // 移动或者修改后旧审核作废
        if ( clearApprove ) {
            ApproveCoreExample example = new ApproveCoreExample();
            example.createCriteria().andFtypeEqualTo( approveCoreDTO.getFtype() )
                    .andRelIdEqualTo( approveCoreDTO.getRelId() ).andFstatusEqualTo( ApproveStatus.WAIT );

            ApproveCore update = new ApproveCore();
            update.setFstatus( ApproveStatus.DISABLED );
            approveCoreMapper.updateByExampleSelective( update, example );
        }

        MemberDTO memberDTO = memberService.getMemberById( approveCoreDTO.getMemberId() );
        String memberName = memberDTO == null ? null : memberDTO.getRealName();
        for ( Integer id : approveMemberList ) {
            ApproveCore core = new ApproveCore();
            // TODO 人员审核 title??
            core.setTitle( approveCoreDTO.getTitle() );
            core.setMemberId( approveCoreDTO.getMemberId() );
            core.setRelId( approveCoreDTO.getRelId() );
            core.setSummary( approveCoreDTO.getSummary() );
            core.setFstatus( ApproveStatus.WAIT );
            core.setApproveMemberId( id );
            core.setMemberName( memberName );
            core.setFtype( approveCoreDTO.getFtype() );

            approveCoreMapper.insertSelective( core );
        }
    }

    @Override
    @Validator
    @Transactional
    public void approve( @Valid( groups = Approve.class ) ApproveCoreDTO approveCoreDTO, RequestMsg requestMsg ) {

        ApproveCore core = null;
        if ( approveCoreDTO.getFtype() != null && approveCoreDTO.getFstatus() != null
                && approveCoreDTO.getRelId() != null ) {
            ApproveCoreExample e = new ApproveCoreExample();
            e.createCriteria().andApproveMemberIdEqualTo( approveCoreDTO.getRealApproveMemberId() )
                    .andFtypeEqualTo( approveCoreDTO.getFtype() )
                    .andFstatusEqualTo( ApproveStatus.WAIT ).andRelIdEqualTo( approveCoreDTO.getRelId() );
            List<ApproveCore> cores = approveCoreMapper.selectByExample( e );
            core = !CollectionUtils.isEmpty( cores ) ? cores.get( 0 ) : null;
        }
        if ( core == null && approveCoreDTO.getId() != null ) {
            core = approveCoreMapper.selectByPrimaryKey( approveCoreDTO.getId() );
        }
        if ( core == null || ApproveStatus.DISABLED.equals( core.getFstatus() ) ) {
            throw new DctException( ErrorCodeCore.Approve.APPROVE_NOT_EXIST );
        }
        if ( !approveCoreDTO.getApprove() && StringUtil.isEmpty( approveCoreDTO.getOpinion() ) ) {
            throw new DctException( ErrorCodeCore.Approve.APPROVE_OPINION );
        }
        if ( !ApproveStatus.WAIT.equals( core.getFstatus() ) ) {
            throw new DctException( ErrorCodeCore.Approve.ALREADY_APPROVE );
        }

        ApproveCoreExample example = new ApproveCoreExample();
        example.createCriteria().andIdEqualTo( core.getId() ).andFstatusEqualTo( ApproveStatus.WAIT );

        ApproveCore update = new ApproveCore();
        update.setFstatus( approveCoreDTO.getApprove() ? ApproveStatus.SUCCESS : ApproveStatus.FAIL );
        update.setRealApproveMemberId( requestMsg.getMemberId() );
        update.setApproveAt( new Date() );
        update.setOpinion( approveCoreDTO.getOpinion() );

        int flag = approveCoreMapper.updateByExampleSelective( update, example );
        if ( flag > 0 ) {
            // 文章审核成功 更改文章状态
            if ( ApproveType.ARTICLE.equals( core.getFtype() ) || ApproveType.QUESTION.equals( core.getFtype() ) ) {
                // 修改
                int f = baseService.updateArticleStatus( core.getRelId(), approveCoreDTO.getApprove(), core.getId() );
                if ( f <= 0 ) {
                    throw new DctException( ErrorCodeCore.Approve.APPROVE_FAIL );
                }
            }
            if ( ApproveType.MEMBER.equals( core.getFtype() ) ) {

                // TODO 人员审核

            }

        } else {
            throw new DctException( ErrorCodeCore.Approve.APPROVE_FAIL );
        }
    }

    @Override
    public PageDTO<List<ApproveCoreDTO>> approveList( PageDTO<ApproveCoreDTO> pageDTO, RequestMsg requestMsg ) {

        ApproveCoreDTO coreDTO = pageDTO.getData();
        Page page = pageDTO.toModel( Page.class );

        ApproveCoreExample example = new ApproveCoreExample();
        ApproveCoreExample.Criteria criteria = example.createCriteria();
        criteria.andApproveMemberIdEqualTo( requestMsg.getMemberId() );
        if ( ApproveStatus.WAIT.equals( coreDTO.getFstatus() ) ) {
            criteria.andFstatusEqualTo( ApproveStatus.WAIT );
            example.setOrderByClause( " create_at DESC " );
        } else if ( ApproveStatus.SUCCESS.equals( coreDTO.getFstatus() ) ) {
            criteria.andFstatusEqualTo( ApproveStatus.SUCCESS );
            example.setOrderByClause( " approve_at DESC " );
        } else if ( ApproveStatus.FAIL.equals( coreDTO.getFstatus() ) ) {
            criteria.andFstatusEqualTo( ApproveStatus.FAIL );
            example.setOrderByClause( " approve_at DESC " );
        } else {
            List<Short> status = new ArrayList<>();
            status.add( ApproveStatus.FAIL );
            status.add( ApproveStatus.SUCCESS );
            criteria.andFstatusIn( status );
            example.setOrderByClause( " approve_at DESC " );
        }
        example.setPage( page );

        List<ApproveCore> cores = approveCoreMapper.selectByExample( example );
        PageDTO<List<ApproveCoreDTO>> ret = new PageDTO<>().toDTO( page );
        ret.setData( new ArrayList<>() );
        for ( ApproveCore core : cores ) {

            ApproveCoreDTO dto = new ApproveCoreDTO();
            dto.setFstatus( core.getFstatus() );
            dto.setFtype( core.getFtype() );
            dto.setCreateAt( core.getCreateAt() );
            dto.setMemberName( core.getMemberName() );
            dto.setTitle( core.getTitle() );
            dto.setSummary( core.getSummary() );
            dto.setApproveAt( core.getApproveAt() );
            dto.setId( core.getId() );
            if ( ApproveType.MEMBER.equals( core.getFtype() ) ) {
                // 获取人员信息
                dto.setMemberInfo( memberService.getMemberById( core.getRelId() ) );
            }
            if ( ApproveType.ARTICLE.equals( core.getFtype() ) ) {
                // TODO 板块信息
                ArticleCoreDTO article = baseService.getArticleDetail( core.getRelId() );

            }

            ret.getData().add( dto );
        }

        return ret;
    }
}
