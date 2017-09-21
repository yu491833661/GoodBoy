package yxinfo.yjh.service.article.dto;

import org.hibernate.validator.constraints.NotEmpty;
import yxinfo.core.common.dto.BaseDTO;
import yxinfo.core.common.exception.ErrorCodeCore;
import yxinfo.core.common.validation.Add;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author jn
 * @date 2017/9/14 20:17
 **/
public class ArticleAnswerDTO extends BaseDTO {

    private Integer id;

    //文章id，<FK>article_core.id
    @NotNull( groups = Add.class, message = ErrorCodeCore.NOT_NULL )
    private Integer articleId;

    //回答人id，<FK>sys_member.id
    private Integer memberId;

    //回答人姓名
    private String memberName;

    //回答人单位
    private String memberOrg;

    //创建时间
    private Date createAt;

    // 回答内容
    @NotEmpty( groups = Add.class, message = ErrorCodeCore.NOT_EMPTY )
    private String context;

    // 文章标题
    private String articleTitle;

    // 板块信息
    private String blockInfo;

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getBlockInfo() {
        return blockInfo;
    }

    public void setBlockInfo(String blockInfo) {
        this.blockInfo = blockInfo;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

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

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberOrg() {
        return memberOrg;
    }

    public void setMemberOrg(String memberOrg) {
        this.memberOrg = memberOrg;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
}
