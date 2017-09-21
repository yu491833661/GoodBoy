package yxinfo.yjh.dao.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface ArticleCoreMapperExtend {

    /**
     * 点击量
     *
     * @param articleId
     * @return
     */
    @Update( " UPDATE article_core SET click_num = click_num + 1 WHERE id = #{articleId} " )
    int click( @Param("articleId") int articleId );

    /**
     * 回答数
     *
     * @param articleId
     * @return
     */
    @Update( " UPDATE article_core SET answer_num = answer_num + 1 WHERE id = #{articleId} " )
    int answerNum( @Param("articleId") int articleId );
}