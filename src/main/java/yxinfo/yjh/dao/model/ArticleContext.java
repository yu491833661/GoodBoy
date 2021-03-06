package yxinfo.yjh.dao.model;

public class ArticleContext {
    //文章id，<FK>article_core.id
    private Integer articleId;

    //文章内容
    private String fcontext;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article_context.article_id
     *
     * @return the value of article_context.article_id
     *
     * @mbg.generated
     */
    public Integer getArticleId() {
        return articleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article_context.article_id
     *
     * @param articleId the value for article_context.article_id
     *
     * @mbg.generated
     */
    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article_context.fcontext
     *
     * @return the value of article_context.fcontext
     *
     * @mbg.generated
     */
    public String getFcontext() {
        return fcontext;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article_context.fcontext
     *
     * @param fcontext the value for article_context.fcontext
     *
     * @mbg.generated
     */
    public void setFcontext(String fcontext) {
        this.fcontext = fcontext == null ? null : fcontext.trim();
    }
}