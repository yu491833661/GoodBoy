package yxinfo.yjh.service.article.impl;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import yxinfo.core.common.cache.CacheName;
import yxinfo.core.common.dto.PageDTO;
import yxinfo.core.common.dto.RequestMsg;
import yxinfo.core.common.exception.ErrorCodeCore;
import yxinfo.core.common.validation.*;
import yxinfo.core.framework.exception.DctException;
import yxinfo.core.framework.service.dal.Page;
import yxinfo.core.service.ou.MemberService;
import yxinfo.core.service.ou.dto.MemberDTO;
import yxinfo.yjh.dao.mapper.*;
import yxinfo.yjh.dao.model.*;
import yxinfo.yjh.service.approve.ApproveService;
import yxinfo.yjh.service.approve.context.ApproveType;
import yxinfo.yjh.service.approve.dto.ApproveCoreDTO;
import yxinfo.yjh.service.article.ArticleService;
import yxinfo.yjh.service.article.context.ArticleStatus;
import yxinfo.yjh.service.article.context.ArticleType;
import yxinfo.yjh.service.article.dto.*;
import yxinfo.yjh.service.article.validation.AddNews;
import yxinfo.yjh.service.article.validation.UpdateNews;
import yxinfo.yjh.service.block.context.Block;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author jn
 * @date 2017/9/14 18:22
 **/
@Component
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleCoreMapper articleCoreMapper;
    @Resource
    private ArticleContextMapper contextMapper;
    @Resource
    private ArticleAttachMapper attachMapper;
    @Resource
    private ArticleAnswerMapperExtend articleAnswerMapperExtend;
    @Resource
    private ApproveCoreMapper approveCoreMapper;
    @Resource
    private ArticleAnswerMapper answerMapper;
    @Resource
    private ArticleAnswerContextMapper answerContextMapper;
    @Resource
    private ArticleMoveHistoryMapper moveHistoryMapper;
    @Resource
    private ArticleCoreMapperExtend coreMapperExtend;

    @Resource
    private ApproveService approveService;
    @Resource
    private MemberService memberService;

    @Override
    @Transactional
    @Validator
    public Integer addArticle( @Valid( groups = Add.class ) ArticleCoreDTO articleCoreDTO, RequestMsg requestMsg ) {

        ArticleCore core = new ArticleCore();
        core.setTitle( articleCoreDTO.getTitle() );
        core.setAuthorName( articleCoreDTO.getAuthorName() );
        // TODO 判断板块是否存在 是否可以投稿
        core.setBlockId( articleCoreDTO.getBlockId() );
        core.setMemberId( requestMsg.getMemberId() );
        MemberDTO memberDTO = memberService.getMemberById( requestMsg.getMemberId() );
        if ( memberDTO != null ) {
            core.setMemberName( memberDTO.getRealName() );
        }
        core.setFstatus( ArticleStatus.ON_APPROVE );
        core.setIsFront( false );
        if ( ArticleType.ARTICLE.equals( articleCoreDTO.getFtype() ) ) {
            core.setFtype( ArticleType.ARTICLE );
        } else if ( ArticleType.QUESTION.equals( articleCoreDTO.getFtype() ) ) {
            core.setFtype( ArticleType.QUESTION );
        } else {
            throw new DctException( ErrorCodeCore.NOT_NULL );
        }

        articleCoreMapper.insertSelective( core );
        // 内容处理
        ArticleContext context = new ArticleContext();
        context.setArticleId( core.getId() );
        context.setFcontext( articleCoreDTO.getContext() );
        contextMapper.insertSelective( context );
        // 附件处理 提问不处理附件
        if ( ArticleType.ARTICLE.equals( articleCoreDTO.getFtype() )
                && !CollectionUtils.isEmpty( articleCoreDTO.getAttachList() ) ) {

            handleArticleAttach( core.getId(), articleCoreDTO.getAttachList(), false );
        }

        // 添加审核记录
        handleApprove( core, requestMsg, false );

        return core.getId();
    }

    @Override
    @Transactional
    @CacheEvict( value = CacheName.Article.ARTICLE, key = "T(yxinfo.core.common.cache.CacheName.Article).ARTICLE + '#' + #articleCoreDTO.id" )
    public Integer articleDraft( ArticleCoreDTO articleCoreDTO, RequestMsg requestMsg ) {

        ArticleCore core = new ArticleCore();
        core.setTitle( articleCoreDTO.getTitle() );
        core.setAuthorName( articleCoreDTO.getAuthorName() );
        core.setBlockId( articleCoreDTO.getBlockId() );

        ArticleContext context = new ArticleContext();
        context.setFcontext( articleCoreDTO.getContext() );

        boolean flag = false;
        if ( articleCoreDTO.getId() != null ) {
            ArticleCore articleCore = articleCoreMapper.selectByPrimaryKey( articleCoreDTO.getId() );
            if ( !ArticleStatus.DRAFT.equals( articleCore.getFstatus() ) ) {
                throw new DctException( ErrorCodeCore.ARTICLE.ONLY_DRAFT );
            }
            if ( articleCore.getMemberId().equals( requestMsg.getMemberId() ) ) {
                flag = true;
            }
        }

        if ( flag ) {
            // 修改
            core.setId( articleCoreDTO.getId() );
            core.setUpdateAt( new Date() );
            articleCoreMapper.updateByPrimaryKeySelective( core );
            // 内容处理
            context.setArticleId( core.getId() );
            contextMapper.updateByPrimaryKeySelective( context );

        } else {
            // 新增
            if ( !ArticleType.ARTICLE.equals( articleCoreDTO.getFtype() )
                    && !ArticleType.QUESTION.equals( articleCoreDTO.getFtype() ) ){
                throw new DctException( ErrorCodeCore.NOT_NULL );
            }
            core.setMemberId( requestMsg.getMemberId() );
            MemberDTO memberDTO = memberService.getMemberById( requestMsg.getMemberId() );
            if ( memberDTO != null ) {
                core.setMemberName( memberDTO.getRealName() );
            }
            core.setFstatus( ArticleStatus.DRAFT );
            core.setIsFront( false );
            core.setFtype( articleCoreDTO.getFtype() );

            articleCoreMapper.insertSelective( core );
            // 内容处理
            context.setArticleId( core.getId() );
            contextMapper.insertSelective( context );
        }

        // 附件处理 提问不处理附件
        if ( ArticleType.ARTICLE.equals( articleCoreDTO.getFtype() )
                && !CollectionUtils.isEmpty( articleCoreDTO.getAttachList() ) ) {

            handleArticleAttach( core.getId(), articleCoreDTO.getAttachList(), flag );
        }

        return core.getId();
    }

    @Override
    public PageDTO<List<ArticleCoreDTO>> myArticleList( PageDTO<ArticleCoreDTO> pageDTO, RequestMsg requestMsg ) {

        ArticleCoreDTO coreDTO = pageDTO.getData();
        Page page = pageDTO.toModel( Page.class );

        ArticleCoreExample example = new ArticleCoreExample();
        ArticleCoreExample.Criteria criteria = example.createCriteria();
        criteria.andMemberIdEqualTo( requestMsg.getMemberId() );
        if ( ArticleType.ARTICLE.equals( coreDTO.getFtype() ) ) {
            criteria.andFtypeEqualTo( ArticleType.ARTICLE ).andFstatusEqualTo( ArticleStatus.PUBLISHED );
        } else if ( ArticleType.QUESTION.equals( coreDTO.getFtype() ) ) {
            criteria.andFtypeEqualTo( ArticleType.QUESTION ).andFstatusEqualTo( ArticleStatus.PUBLISHED );
        } else if ( ArticleStatus.ON_APPROVE.equals( coreDTO.getFstatus() ) ) {
            criteria.andFstatusEqualTo( ArticleStatus.ON_APPROVE );
        } else if ( ArticleStatus.NOT_PASS.equals( coreDTO.getFstatus() ) ) {
            criteria.andFstatusEqualTo( ArticleStatus.NOT_PASS );
        } else if ( ArticleStatus.DRAFT.equals( coreDTO.getFstatus() ) ) {
            criteria.andFstatusEqualTo( ArticleStatus.DRAFT );
        } else {
            throw new DctException( ErrorCodeCore.NOT_NULL );
        }
        example.setPage( page );
        example.setOrderByClause( " create_at DESC " );

        return handleAdminArticles( example, page );
    }

    @Override
    public PageDTO<List<ArticleCoreDTO>> adminArticleList( PageDTO<ArticleCoreDTO> pageDTO, RequestMsg requestMsg ) {

        ArticleCoreDTO coreDTO = pageDTO.getData();
        Page page = pageDTO.toModel( Page.class );
        ArticleCoreExample example = new ArticleCoreExample();
        ArticleCoreExample.Criteria criteria = example.createCriteria();
        criteria.andFstatusEqualTo( ArticleStatus.PUBLISHED );
        //TODO 获取板块列表
        List<Integer> blockIds = new ArrayList<>();
        if ( CollectionUtils.isEmpty( blockIds ) ) {
            throw new DctException( ErrorCodeCore.ARTICLE.NO_AVAILABLE_BLOCK );
        }
        if ( coreDTO != null ) {
            if ( blockIds.contains( coreDTO.getBlockId() ) ) {
                criteria.andBlockIdEqualTo( pageDTO.getData().getBlockId() );
            } else {
                criteria.andBlockIdIn( blockIds );
            }
            if ( coreDTO.getFtype() != null ) {
                criteria.andFtypeEqualTo( coreDTO.getFtype() );
            }
        } else {
            criteria.andBlockIdIn( blockIds );
        }
        example.setPage( page );
        example.setOrderByClause( " create_at DESC " );

        return handleAdminArticles( example, page );
    }

    @Override
    public PageDTO<List<ArticleCoreDTO>> superArticleList(PageDTO<ArticleCoreDTO> pageDTO, RequestMsg requestMsg) {

        ArticleCoreDTO coreDTO = pageDTO.getData();
        Page page = pageDTO.toModel( Page.class );
        ArticleCoreExample example = new ArticleCoreExample();
        ArticleCoreExample.Criteria criteria = example.createCriteria();
        criteria.andFstatusEqualTo( ArticleStatus.PUBLISHED );
        if ( coreDTO != null ) {
            if ( coreDTO.getBlockId() != null ) {
                criteria.andBlockIdEqualTo( coreDTO.getBlockId() );
            }
            if ( coreDTO.getFtype() != null ) {
                criteria.andFtypeEqualTo( coreDTO.getFtype() );
            }
        }
        example.setPage( page );
        example.setOrderByClause( " create_at DESC " );
        return handleAdminArticles( example, page );
    }

    /**
     * 封装分页数据
     *
     * @param example
     * @param page
     * @return
     */
    private PageDTO<List<ArticleCoreDTO>> handleAdminArticles( ArticleCoreExample example, Page page ){

        List<ArticleCore> cores = articleCoreMapper.selectByExample( example );
        PageDTO<List<ArticleCoreDTO>> ret = new PageDTO<>().toDTO( page );
        ret.setData( new ArrayList<>() );
        for ( ArticleCore core : cores ) {
            ArticleCoreDTO dto = new ArticleCoreDTO();
            dto.setId( core.getId() );
            dto.setTitle( core.getTitle() );
            dto.setBlockId( core.getBlockId() );
            dto.setCreateAt( core.getCreateAt() );
            dto.setAuthorName( core.getAuthorName() );
            dto.setClickNum( core.getClickNum() );
            ret.getData().add( dto );
        }

        return ret;
    }

    @Override
    public PageDTO<List<ArticleAnswerDTO>> myAnswerList( PageDTO<ArticleAnswerDTO> pageDTO, RequestMsg requestMsg ) {

        Page page = pageDTO.toModel( Page.class );

        ArticleAnswerExample example = new ArticleAnswerExample();
        example.createCriteria().andMemberIdEqualTo( requestMsg.getMemberId() );
        example.setPage( page );
        example.setOrderByClause( " create_at DESC " );

        List<ArticleAnswerExtend> answers = articleAnswerMapperExtend.selectAnswerWithContext( example );
        PageDTO<List<ArticleAnswerDTO>> ret = new PageDTO<>().toDTO( page );
        ret.setData( new ArrayList<>() );

        ArticleCoreDTO param = new ArticleCoreDTO();
        for ( ArticleAnswerExtend answerExtend : answers ) {
            ArticleAnswerDTO answerDTO = new ArticleAnswerDTO();
            answerDTO.setContext( answerExtend.getContext() );
            answerDTO.setCreateAt( answerExtend.getCreateAt() );
            answerDTO.setArticleId( answerExtend.getArticleId() );
            // TODO 获取文章标题 板块信息
            param.setId( answerExtend.getArticleId() );
            ArticleCoreDTO article = articleDetail( param, requestMsg );
            if ( article != null ) {
                answerDTO.setArticleTitle( article.getTitle() );
            }

            ret.getData().add( answerDTO );
        }

        return ret;
    }

    @Override
    @Cacheable( value = CacheName.Article.ARTICLE, key = "T(yxinfo.core.common.cache.CacheName.Article).ARTICLE + '#' + #articleCoreDTO.id" )
    public ArticleCoreDTO articleDetail( ArticleCoreDTO articleCoreDTO, RequestMsg requestMsg ) {

        if ( articleCoreDTO == null || articleCoreDTO.getId() == null ) {
            throw new DctException( ErrorCodeCore.NOT_NULL );
        }

        ArticleCore core = articleCoreMapper.selectByPrimaryKey( articleCoreDTO.getId() );
        if ( core == null ) return null;
        // 草稿
        if ( ArticleStatus.DRAFT.equals( core.getFstatus() ) ) {
            if ( !core.getMemberId().equals( requestMsg.getMemberId() ) ) {
                throw new DctException( ErrorCodeCore.DO_NOT_HAVE_ACCESS );
            }
        }
        if ( ArticleStatus.DELETED.equals( core.getFstatus() ) ) return null;
        // 审核中 未通过
        if ( ArticleStatus.ON_APPROVE.equals( core.getFstatus() ) || ArticleStatus.NOT_PASS.equals( core.getFstatus() ) ) {
            // TODO
        }

        ArticleContext context = contextMapper.selectByPrimaryKey( core.getId() );
        ArticleCoreDTO ret = new ArticleCoreDTO();
        ret.setTitle( core.getTitle() );
        ret.setAuthorName( core.getAuthorName() );
        ret.setContext( context == null ? null : context.getFcontext() );
        ret.setBlockId( core.getBlockId() );
        ret.setFtype( core.getFtype() );
        ret.setFstatus( core.getFstatus() );
        // 获取附件
        ArticleAttachExample example = new ArticleAttachExample();
        example.createCriteria().andArticleIdEqualTo( core.getId() );
        List<ArticleAttach> attaches = attachMapper.selectByExample( example );
        ret.setAttachList( new ArrayList<>() );
        attaches.forEach( attache -> ret.getAttachList().add( new ArticleAttachDTO().toDTO( attache ) ) );

        // 获取审核信息 状态
        if ( ArticleStatus.NOT_PASS.equals( core.getFstatus() ) ) {
            ApproveCore approveCore = approveCoreMapper.selectByPrimaryKey( core.getApproveId() );
            ret.setApproveInfo( new ApproveCoreDTO().toDTO( approveCore ) );
        }

        return ret;
    }

    @Override
    public PageDTO<List<ArticleAnswerDTO>> articleAnswerList( PageDTO<ArticleAnswerDTO> pageDTO ) {

        ArticleAnswerDTO answerDTO = pageDTO.getData();
        if ( answerDTO == null || answerDTO.getArticleId() == null ) {
            throw new DctException( ErrorCodeCore.NOT_NULL );
        }

        ArticleAnswerExample example = new ArticleAnswerExample();
        example.createCriteria().andArticleIdEqualTo( answerDTO.getArticleId() );
        List<ArticleAnswerExtend> answerExtends = articleAnswerMapperExtend.selectAnswerWithContext( example );
        PageDTO<List<ArticleAnswerDTO>> ret = new PageDTO<>();
        ret.setData( new ArrayList<>() );
        answerExtends.forEach( extend -> ret.getData().add( new ArticleAnswerDTO().toDTO( extend ) ) );

        return ret;
    }

    @Override
    @Validator
    @Transactional
    public void delArticle( @Valid( groups = Delete.class ) ArticleCoreDTO articleCoreDTO, RequestMsg requestMsg ) {

        ArticleCore core = articleCoreMapper.selectByPrimaryKey( articleCoreDTO.getId() );
        if ( core == null ) return;

        // TODO 根据角色鉴权
        // 能删除提问 各种状态
        if ( !core.getMemberId().equals( requestMsg.getMemberId() ) ) {
            throw new DctException( "" );
        }

        // TODO 获取管理板块
        if ( !articleCoreDTO.getBlockId().equals( 11 ) ) {
            throw new DctException( "" );
        }

        ArticleCore update = new ArticleCore();
        update.setFstatus( ArticleStatus.DELETED );
        update.setId( core.getId() );

        articleCoreMapper.updateByPrimaryKeySelective( update );
    }

    @Override
    @Validator
    @Transactional
    @CacheEvict( value = CacheName.Article.ARTICLE, key = "T(yxinfo.core.common.cache.CacheName.Article).ARTICLE + '#' + #articleCoreDTO.id" )
    public void updateArticle( @Valid( groups = Update.class ) ArticleCoreDTO articleCoreDTO, RequestMsg requestMsg ) {

        ArticleCore core = articleCoreMapper.selectByPrimaryKey( articleCoreDTO.getId() );
        // 提问暂不能修改 未通过可修改或者删除
        // 已删除和已发布不能修改
        if ( core == null || ArticleType.QUESTION.equals( core.getFtype() )
                || !core.getMemberId().equals( requestMsg.getMemberId() )
                || ArticleStatus.DELETED.equals( core.getFstatus() )
                || ArticleStatus.PUBLISHED.equals( core.getFstatus() )  ) return;

        ArticleCoreExample example = new ArticleCoreExample();
        example.createCriteria().andIdEqualTo( core.getId() ).andFstatusEqualTo( core.getFstatus() );

        ArticleCore update = new ArticleCore();
        update.setTitle( articleCoreDTO.getTitle() );
        update.setAuthorName( articleCoreDTO.getAuthorName() );
        update.setBlockId( articleCoreDTO.getBlockId() );
        update.setUpdateAt( new Date() );

        int flag = articleCoreMapper.updateByExampleSelective( update, example );
        if ( flag > 0 ) {
            // 处理附件 提问不处理附件
            if ( ArticleType.ARTICLE.equals( core.getFtype() ) ) {
                handleArticleAttach( core.getId(), articleCoreDTO.getAttachList(), true );
            }
            // 重新添加审核
            handleApprove( core, requestMsg, true );
        } else {
            throw new DctException( ErrorCodeCore.ARTICLE.ARTICLE_UPDATE_FAIL );
        }
    }

    /**
     * 附件处理
     *
     * @param articleId 文章id
     * @param attachDTOS 附件列表
     * @param ifDelBeforeInsert 插入前是否删除旧数据
     */
    private void handleArticleAttach( int articleId, List<ArticleAttachDTO> attachDTOS, boolean ifDelBeforeInsert ){

        if ( ifDelBeforeInsert ) {
            // 删除旧附件
            ArticleAttachExample example = new ArticleAttachExample();
            example.createCriteria().andArticleIdEqualTo( articleId );
            attachMapper.deleteByExample( example );
        }

        if ( !CollectionUtils.isEmpty( attachDTOS ) ) {
            for ( ArticleAttachDTO attachDTO : attachDTOS ) {

                ArticleAttach attach = new ArticleAttach();
                attach.setArticleId( articleId );
                attach.setFname( attachDTO.getFname() );
                attach.setPath( attachDTO.getPath() );

                attachMapper.insertSelective( attach );
            }
        }
    }

    private void handleApprove( ArticleCore core, RequestMsg requestMsg, boolean clearApprove ) {

        ApproveCoreDTO approveCoreDTO = new ApproveCoreDTO();
        approveCoreDTO.setMemberId(requestMsg.getMemberId());
        approveCoreDTO.setRelId(core.getId());
        approveCoreDTO.setTitle(core.getTitle());
        if ( ArticleType.ARTICLE.equals( core.getFtype() ) ) {
            approveCoreDTO.setFtype( ApproveType.ARTICLE );
        }
        if ( ArticleType.QUESTION.equals( core.getFtype() ) ) {
            approveCoreDTO.setFtype( ApproveType.QUESTION );
        }

        approveService.addApprove( approveCoreDTO, clearApprove );
    }

    @Override
    @Transactional
    public void push2Top( ArticleCoreDTO articleCoreDTO, RequestMsg requestMsg ) {

        if ( articleCoreDTO == null || articleCoreDTO.getId() == null ) return;
        ArticleCore core = articleCoreMapper.selectByPrimaryKey( articleCoreDTO.getId() );
        // 已发布状态才可以置顶
        if ( core == null || !ArticleStatus.PUBLISHED.equals( core.getFstatus() ) ) return;

        ArticleCore update = new ArticleCore();
        update.setId( core.getId() );
        update.setIsTop( true );

        articleCoreMapper.updateByPrimaryKeySelective( update );
    }

    @Override
    @Transactional
    public void push2Home( ArticleCoreDTO articleCoreDTO, RequestMsg requestMsg ) {

        if ( articleCoreDTO == null || articleCoreDTO.getId() == null ) return;
        ArticleCore core = articleCoreMapper.selectByPrimaryKey( articleCoreDTO.getId() );
        // 已发布状态才可以推荐首页
        if ( core == null || !ArticleStatus.PUBLISHED.equals( core.getFstatus() ) ) return;
        // TODO 判断板块 只有工作动态板和首页部分板块块有推荐首页功能

        ArticleCore update = new ArticleCore();
        update.setId( core.getId() );
        update.setIsFront( true );

        articleCoreMapper.updateByPrimaryKeySelective( update );
    }

    @Override
    @Validator
    @Transactional
    @CacheEvict( value = CacheName.Article.ARTICLE, key = "T(yxinfo.core.common.cache.CacheName.Article).ARTICLE + '#' + #answerDTO.articleId" )
    public void addArticleAnswer( @Valid( groups = Add.class ) ArticleAnswerDTO answerDTO, RequestMsg requestMsg ) {

        ArticleCore core = articleCoreMapper.selectByPrimaryKey( answerDTO.getArticleId() );
        if ( core == null || !ArticleType.QUESTION.equals( core.getFtype() ) ) return;

        ArticleAnswer answer = new ArticleAnswer();
        answer.setMemberId( requestMsg.getMemberId() );
        MemberDTO memberDTO = memberService.getMemberById( requestMsg.getMemberId() );
        if ( memberDTO != null ) {
            answer.setMemberName( memberDTO.getRealName() );
            answer.setMemberOrg( memberDTO.getEnterpriseName() );
        }
        answer.setArticleId( core.getId() );
        answerMapper.insertSelective( answer );

        ArticleAnswerContext context = new ArticleAnswerContext();
        context.setArticleAnswerId( answer.getId() );
        context.setFcontext( answerDTO.getContext() );
        answerContextMapper.insertSelective( context );

        // 更新回答数
        coreMapperExtend.answerNum( core.getId() );
    }

    @Override
    @Transactional
    @Validator
    public Integer addArticleNews(@Valid( groups = AddNews.class ) ArticleCoreDTO articleCoreDTO, RequestMsg requestMsg ) {

        ArticleCore core = new ArticleCore();
        core.setTitle( articleCoreDTO.getTitle() );
        core.setAuthorName( articleCoreDTO.getAuthorName() );
        core.setBlockId( Block.NEWS );
        core.setMemberId( requestMsg.getMemberId() );
        MemberDTO memberDTO = memberService.getMemberById( requestMsg.getMemberId() );
        if ( memberDTO != null ) {
            core.setMemberName( "" );
        }
        core.setFstatus( ArticleStatus.PUBLISHED );
        core.setFtype( ArticleType.ARTICLE );
        core.setImgSrc( articleCoreDTO.getImgSrc() );

        core.setIsFront( false );

        articleCoreMapper.insertSelective( core );
        // 内容处理
        ArticleContext context = new ArticleContext();
        context.setArticleId( core.getId() );
        context.setFcontext( articleCoreDTO.getContext() );
        contextMapper.insertSelective( context );
        // 附件处理
        if ( !CollectionUtils.isEmpty( articleCoreDTO.getAttachList() ) ) {
            handleArticleAttach( core.getId(), articleCoreDTO.getAttachList(), false );
        }

        return core.getId();
    }

    @Override
    @Transactional
    @Validator
    @CacheEvict( value = CacheName.Article.ARTICLE, key = "T(yxinfo.core.common.cache.CacheName.Article).ARTICLE + '#' + #articleCoreDTO.id" )
    public void updateArticleNews( @Valid( groups = UpdateNews.class ) ArticleCoreDTO articleCoreDTO, RequestMsg requestMsg ) {

        ArticleCore core = new ArticleCore();
        core.setTitle( articleCoreDTO.getTitle() );
        core.setAuthorName( articleCoreDTO.getAuthorName() );
        core.setImgSrc( articleCoreDTO.getImgSrc() );
        core.setId( articleCoreDTO.getId() );
        articleCoreMapper.updateByPrimaryKeySelective( core );
        // 内容处理
        ArticleContext context = new ArticleContext();
        context.setArticleId( core.getId() );
        context.setFcontext( articleCoreDTO.getContext() );
        contextMapper.updateByPrimaryKeySelective( context );
        // 附件处理
        if ( !CollectionUtils.isEmpty( articleCoreDTO.getAttachList() ) ) {
            handleArticleAttach( core.getId(), articleCoreDTO.getAttachList(), true );
        }
    }

    @Override
    public PageDTO<List<ArticleCoreDTO>> newsList( PageDTO<ArticleCoreDTO> pageDTO ) {

        Page page = pageDTO.toModel( Page.class );
        ArticleCoreExample example = new ArticleCoreExample();
        example.createCriteria().andBlockIdEqualTo( Block.NEWS ).andIsEnableEqualTo( true );
        example.setPage( page );
        example.setOrderByClause( " sort DESC, create_at DESC " );

        List<ArticleCore> cores = articleCoreMapper.selectByExample( example );
        PageDTO<List<ArticleCoreDTO>> ret = new PageDTO<>().toDTO( page );
        ret.setData( new ArrayList<>() );
        for ( ArticleCore core : cores ) {

            ArticleCoreDTO coreDTO = new ArticleCoreDTO();
            coreDTO.setId( core.getId() );
            coreDTO.setTitle( core.getTitle() );
            coreDTO.setImgSrc( core.getImgSrc() );
            coreDTO.setCreateAt( core.getCreateAt() );
            coreDTO.setAuthorName( core.getAuthorName() );
            coreDTO.setSort( core.getSort() );
            ret.getData().add( coreDTO );
        }
        return ret;
    }

    @Override
    @Transactional
    @Validator
    public void newsConfig( @Valid( groups = Add.class ) ArticleConfigDTO configDTO ) {

        ArticleCoreExample example = new ArticleCoreExample();
        example.createCriteria().andIdEqualTo( configDTO.getArticleId() ).andBlockIdEqualTo( Block.NEWS );

        ArticleCore core = new ArticleCore();
        core.setIsEnable( configDTO.getEnable() );
        articleCoreMapper.updateByExampleSelective( core, example );
    }

    @Override
    @Transactional
    @Validator
    public void moveArticle( @Valid( groups = Add.class ) ArticleMoveDTO moveDTO, RequestMsg requestMsg ) {

        ArticleCore core = articleCoreMapper.selectByPrimaryKey( moveDTO.getArticleId() );
        // 审核中才能移动
        if ( core == null || !ArticleStatus.ON_APPROVE.equals( core.getFstatus() ) ) return;
        // TODO 获取管理员板块
        if ( core.getBlockId().equals( moveDTO.getBlockId() ) ) {
            throw new DctException( ErrorCodeCore.ARTICLE.SRC_BLOCK_EQUAL_TARGET );
        }

        ArticleCoreExample example = new ArticleCoreExample();
        example.createCriteria().andIdEqualTo( moveDTO.getArticleId() ).andFstatusEqualTo( ArticleStatus.ON_APPROVE );
        ArticleCore update = new ArticleCore();
        update.setBlockId( moveDTO.getBlockId() );
        int flag = articleCoreMapper.updateByExampleSelective( update, example );
        if ( flag <= 0 ) {
            throw new DctException( ErrorCodeCore.ARTICLE.ARTICLE_MOVE_FAIL );
        }
    }

    @Override
    public PageDTO<List<ArticleMoveHistoryDTO>> moveList( PageDTO<ArticleMoveHistoryDTO> pageDTO, RequestMsg requestMsg ) {

        Page page = pageDTO.toModel( Page.class );
        ArticleMoveHistoryExample example = new ArticleMoveHistoryExample();
        example.createCriteria().andMemberIdEqualTo( requestMsg.getMemberId() );
        List<ArticleMoveHistory> histories = moveHistoryMapper.selectByExample( example );
        example.setPage( page );
        example.setOrderByClause( " create_at DESC " );

        PageDTO<List<ArticleMoveHistoryDTO>> ret = new PageDTO<>().toDTO( page );
        ret.setData( new ArrayList<>() );
        for ( ArticleMoveHistory history : histories ) {

            ArticleMoveHistoryDTO historyDTO = new ArticleMoveHistoryDTO().toDTO( history );
            ret.getData().add( historyDTO );
        }

        return ret;
    }

    @Override
    @Async
    @CacheEvict( value = CacheName.Article.ARTICLE, key = "T(yxinfo.core.common.cache.CacheName.Article).ARTICLE + '#' + #articleId" )
    public void click( Integer articleId ) {

        coreMapperExtend.click( articleId );
    }
}
