package yxinfo.yjh.dao.mapper;

import yxinfo.yjh.dao.model.ArticleAnswerExample;
import yxinfo.yjh.dao.model.ArticleAnswerExtend;

import java.util.List;

public interface ArticleAnswerMapperExtend {

    /**
     * 查询回答 join 内容
     *
     * @param example
     * @return
     */
    List<ArticleAnswerExtend> selectAnswerWithContext(ArticleAnswerExample example);
}