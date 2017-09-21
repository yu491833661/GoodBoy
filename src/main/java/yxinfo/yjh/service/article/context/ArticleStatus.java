package yxinfo.yjh.service.article.context;

/**
 * 文章审核状态
 *
 * @author jn
 * @date 2017/9/14 19:30
 **/
public interface ArticleStatus {

    // 0草稿，1未通过，2审核中，3已发表，4已删除
    Short DRAFT = 0;

    Short NOT_PASS = 1;

    Short ON_APPROVE = 2;

    Short PUBLISHED = 3;

    Short DELETED = 4;
}
