package yxinfo.yjh.service.article.dto;

import yxinfo.core.common.dto.BaseDTO;
import yxinfo.core.common.exception.ErrorCodeCore;
import yxinfo.core.common.validation.Add;

import javax.validation.constraints.NotNull;

/**
 * @author jn
 * @date 2017/9/18 15:51
 **/
public class ArticleMoveDTO extends BaseDTO {

    @NotNull( groups = Add.class, message = ErrorCodeCore.NOT_NULL )
    private Integer articleId;

    @NotNull( groups = Add.class, message = ErrorCodeCore.NOT_NULL )
    private Integer blockId;

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getBlockId() {
        return blockId;
    }

    public void setBlockId(Integer blockId) {
        this.blockId = blockId;
    }
}
