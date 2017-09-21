package yxinfo.yjh.service.article;

import yxinfo.yjh.service.article.dto.ArticleCoreDTO;

/**
 * @author jn
 * @date 2017/9/20 14:58
 **/
public interface ArticleBaseService {

    /**
     * 获取文章详情
     *
     * @param articleId
     * @return
     */
    ArticleCoreDTO getArticleDetail( int articleId );

    /**
     * 审核修改文章状态
     *
     * @param articleId
     * @param approve
     * @param approveId
     * @return
     */
    int updateArticleStatus( int articleId, boolean approve, int approveId );
}
