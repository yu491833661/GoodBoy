package yxinfo.yjh.service.article.dto;

import yxinfo.core.common.dto.BaseDTO;

/**
 * @author jn
 * @date 2017/9/14 19:42
 **/
public class ArticleAttachDTO extends BaseDTO {

    private Integer id;

    //文章id，<FK>article_core.id
    private Integer articleId;

    //附件名称
    private String fname;

    //附件路径
    private String path;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
