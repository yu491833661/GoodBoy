package yxinfo.yjh.service.article.dto;

import yxinfo.core.common.dto.BaseDTO;

import java.util.Date;

/**
 * @author jn
 * @date 2017/9/18 15:54
 **/
public class ArticleMoveHistoryDTO extends BaseDTO {

    private Integer id;

    //用户id
    private Integer memberId;

    //文章id，<Fk>article_core.id
    private Integer articleId;

    //文章类型（1文章，2提问）
    private Short ftype;

    //作者姓名
    private String authorName;

    //标题
    private String title;

    //源板块id
    private Integer fromBlockId;

    //目标板块id
    private Integer toBlockId;

    //创建时间
    private Date createAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Short getFtype() {
        return ftype;
    }

    public void setFtype(Short ftype) {
        this.ftype = ftype;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getFromBlockId() {
        return fromBlockId;
    }

    public void setFromBlockId(Integer fromBlockId) {
        this.fromBlockId = fromBlockId;
    }

    public Integer getToBlockId() {
        return toBlockId;
    }

    public void setToBlockId(Integer toBlockId) {
        this.toBlockId = toBlockId;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
}
