package yxinfo.yjh.dao.model;

public class ArticleProblemTag {
    //文章（提问）id，<FK>article_core.id
    private Integer articleId;

    //标签
    private String tag;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article_problem_tag.article_id
     *
     * @return the value of article_problem_tag.article_id
     *
     * @mbg.generated
     */
    public Integer getArticleId() {
        return articleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article_problem_tag.article_id
     *
     * @param articleId the value for article_problem_tag.article_id
     *
     * @mbg.generated
     */
    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article_problem_tag.tag
     *
     * @return the value of article_problem_tag.tag
     *
     * @mbg.generated
     */
    public String getTag() {
        return tag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article_problem_tag.tag
     *
     * @param tag the value for article_problem_tag.tag
     *
     * @mbg.generated
     */
    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }
}