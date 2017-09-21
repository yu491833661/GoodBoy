package yxinfo.yjh.service.article;

import yxinfo.core.common.dto.PageDTO;
import yxinfo.core.common.dto.RequestMsg;
import yxinfo.core.common.route.DataType;
import yxinfo.core.common.route.Route;
import yxinfo.core.common.route.RouteCode;
import yxinfo.core.service.ou.context.Roles;
import yxinfo.yjh.service.article.dto.*;

import java.util.List;

/**
 * 文章/提问
 *
 * @author jn
 * @date 2017/9/14 18:09
 **/
public interface ArticleService {

    /**
     * 发布文章/提问
     *
     * @param articleCoreDTO
     * @param requestMsg
     * @return
     */
    @Route( code = RouteCode.Article.ADD_ARTICLE, allowRoles = Roles.MEMBER )
    Integer addArticle( ArticleCoreDTO articleCoreDTO, RequestMsg requestMsg );

    /**
     * 保存文章/提问为草稿
     * 带上id则修改
     *
     * @param articleCoreDTO
     * @param requestMsg
     * @return
     */
    @Route( code = RouteCode.Article.SAVE_ARTICLE_DRAFT, allowRoles = Roles.MEMBER )
    Integer articleDraft( ArticleCoreDTO articleCoreDTO, RequestMsg requestMsg );

    /**
     * 我的文章/提问列表
     *
     * @param pageDTO
     * @param requestMsg
     * @return
     */
    @Route( code = RouteCode.Article.MY_ARTICLE_LIST, allowRoles = Roles.MEMBER, dataType = DataType.class )
    PageDTO<List<ArticleCoreDTO>> myArticleList( PageDTO<ArticleCoreDTO> pageDTO, RequestMsg requestMsg );

    /**
     * 版主文章/提问列表
     *
     * @param pageDTO
     * @param requestMsg
     * @return
     */
    @Route( code = RouteCode.Article.ADMIN_ARTICLE_LIST, allowRoles = Roles.BLOCK_MASTER, dataType = DataType.class )
    PageDTO<List<ArticleCoreDTO>> adminArticleList( PageDTO<ArticleCoreDTO> pageDTO, RequestMsg requestMsg );

    /**
     * 超级管理员文章/提问列表
     *
     * @param pageDTO
     * @param requestMsg
     * @return
     */
    @Route( code = RouteCode.Article.SUPER_ARTICLE_LIST, allowRoles = Roles.SUPER, dataType = DataType.class )
    PageDTO<List<ArticleCoreDTO>> superArticleList( PageDTO<ArticleCoreDTO> pageDTO, RequestMsg requestMsg );

    /**
     * 我的回答列表
     *
     * @param pageDTO
     * @param requestMsg
     * @return
     */
    @Route( code = RouteCode.Article.MY_ANSWER_LIST, allowRoles = Roles.MEMBER, dataType = DataType.class )
    PageDTO<List<ArticleAnswerDTO>> myAnswerList( PageDTO<ArticleAnswerDTO> pageDTO, RequestMsg requestMsg );

    /**
     * 文章/提问详情
     *
     * @param articleCoreDTO
     * @param requestMsg
     * @return
     */
    @Route( code = RouteCode.Article.ARTICLE_DETAIL )
    ArticleCoreDTO articleDetail( ArticleCoreDTO articleCoreDTO, RequestMsg requestMsg );

    /**
     * 提问回答列表
     *
     * @param pageDTO
     * @return
     */
    @Route( code = RouteCode.Article.ARTICLE_ANSWER_LIST, dataType = DataType.class )
    PageDTO<List<ArticleAnswerDTO>> articleAnswerList( PageDTO<ArticleAnswerDTO> pageDTO );

    /**
     * 删除文章/提问
     *
     * @param articleCoreDTO
     * @param requestMsg
     */
    @Route( code = RouteCode.Article.DEL_ARTICLE, allowRoles = {Roles.MEMBER, Roles.BLOCK_MASTER} )
    void delArticle( ArticleCoreDTO articleCoreDTO, RequestMsg requestMsg );

    /**
     * 修改文章
     *
     * @param articleCoreDTO
     * @param requestMsg
     */
    @Route( code = RouteCode.Article.UPDATE_ARTICLE, allowRoles = Roles.MEMBER )
    void updateArticle( ArticleCoreDTO articleCoreDTO, RequestMsg requestMsg );

    /**
     * 置顶
     *
     * @param articleCoreDTO
     * @param requestMsg
     */
    @Route( code = "" )
    void push2Top( ArticleCoreDTO articleCoreDTO, RequestMsg requestMsg );

    /**
     * 推荐首页
     *
     * @param articleCoreDTO
     * @param requestMsg
     */
    @Route( code = "" )
    void push2Home( ArticleCoreDTO articleCoreDTO, RequestMsg requestMsg );

    /**
     * 提问 添加回答
     *
     * @param answerDTO
     * @param requestMsg
     */
    @Route( code = "" )
    void addArticleAnswer( ArticleAnswerDTO answerDTO, RequestMsg requestMsg );

    /**
     * 添加新闻动态
     *
     * @param articleCoreDTO
     * @param requestMsg
     */
    @Route( code = "" )
    Integer addArticleNews( ArticleCoreDTO articleCoreDTO, RequestMsg requestMsg );

    /**
     * 编辑新闻动态
     *
     * @param articleCoreDTO
     * @param requestMsg
     */
    @Route( code = "" )
    void updateArticleNews( ArticleCoreDTO articleCoreDTO, RequestMsg requestMsg );

    /**
     * 新闻动态列表
     *
     * @param pageDTO
     * @return
     */
    @Route( code = "" )
    PageDTO<List<ArticleCoreDTO>> newsList( PageDTO<ArticleCoreDTO> pageDTO );

    /**
     * 新闻动态停用/启用
     *
     * @param configDTO
     */
    @Route( code = "" )
    void newsConfig( ArticleConfigDTO configDTO );

    /**
     * 移动待审核文章/提问
     *
     * @param moveDTO
     * @param requestMsg
     */
    @Route( code = "" )
    void moveArticle( ArticleMoveDTO moveDTO, RequestMsg requestMsg );

    /**
     * 文章/提问移动记录
     *
     * @param pageDTO
     * @param requestMsg
     * @return
     */
    @Route( code = "" )
    PageDTO<List<ArticleMoveHistoryDTO>> moveList( PageDTO<ArticleMoveHistoryDTO> pageDTO, RequestMsg requestMsg );

    /**
     * 点击量
     *
     * @param articleId
     */
    void click( Integer articleId );
}
