package yxinfo.yjh.service.article.impl;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import yxinfo.core.common.cache.CacheName;
import yxinfo.yjh.dao.mapper.ArticleCoreMapper;
import yxinfo.yjh.dao.model.ArticleCore;
import yxinfo.yjh.dao.model.ArticleCoreExample;
import yxinfo.yjh.service.article.ArticleBaseService;
import yxinfo.yjh.service.article.context.ArticleStatus;
import yxinfo.yjh.service.article.dto.ArticleCoreDTO;

import javax.annotation.Resource;

/**
 * @author jn
 * @date 2017/9/20 14:59
 **/
@Component
public class ArticleBaseServiceImpl implements ArticleBaseService {

    @Resource
    private ArticleCoreMapper articleCoreMapper;

    @Override
    @Cacheable( value = CacheName.Article.ARTICLE, key = "T(yxinfo.core.common.cache.CacheName.Article).ARTICLE + '#' + #articleId" )
    public ArticleCoreDTO getArticleDetail( int articleId ) {
        return new ArticleCoreDTO().toDTO( articleCoreMapper.selectByPrimaryKey( articleId ) );
    }

    @Override
    @CacheEvict( value = CacheName.Article.ARTICLE, key = "T(yxinfo.core.common.cache.CacheName.Article).ARTICLE + '#' + #articleId" )
    public int updateArticleStatus( int articleId, boolean approve, int approveId ) {

        ArticleCoreExample e = new ArticleCoreExample();
        e.createCriteria().andIdEqualTo( articleId ).andFstatusEqualTo( ArticleStatus.ON_APPROVE );

        ArticleCore articleCore = new ArticleCore();
        articleCore.setApproveId( approveId );
        articleCore.setFstatus( approve ? ArticleStatus.PUBLISHED : ArticleStatus.NOT_PASS );
        articleCore.setId( approveId );
        return articleCoreMapper.updateByExampleSelective( articleCore, e );
    }
}
