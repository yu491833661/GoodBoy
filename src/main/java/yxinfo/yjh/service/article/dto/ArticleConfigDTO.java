package yxinfo.yjh.service.article.dto;

import yxinfo.core.common.dto.BaseDTO;
import yxinfo.core.common.exception.ErrorCodeCore;
import yxinfo.core.common.validation.Add;

import javax.validation.constraints.NotNull;

/**
 * @author jn
 * @date 2017/9/18 11:54
 **/
public class ArticleConfigDTO extends BaseDTO {

    @NotNull( groups = Add.class, message = ErrorCodeCore.NOT_NULL )
    private Integer articleId;

    @NotNull( groups = Add.class, message = ErrorCodeCore.NOT_NULL )
    private Boolean isEnable;

    private Integer sort;

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Boolean getEnable() {
        return isEnable;
    }

    public void setEnable(Boolean enable) {
        isEnable = enable;
    }
}
