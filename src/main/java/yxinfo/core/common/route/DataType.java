package yxinfo.core.common.route;

import com.alibaba.fastjson.TypeReference;
import yxinfo.core.common.dto.PageDTO;
import yxinfo.core.service.ou.dto.MemberDTO;
import yxinfo.core.service.ou.dto.OrgDTO;
import yxinfo.yjh.service.approve.dto.ApproveCoreDTO;
import yxinfo.yjh.service.article.dto.ArticleAnswerDTO;
import yxinfo.yjh.service.article.dto.ArticleCoreDTO;
import yxinfo.yjh.service.meeting.dto.MeetingDTO;

public class DataType {

    public static final String PREFIX = "DT";

    public static final TypeReference<PageDTO<MemberDTO>> DT0501014 = new TypeReference<PageDTO<MemberDTO>>() {
    };
    public static final TypeReference<PageDTO<OrgDTO>> DT0501005 = new TypeReference<PageDTO<OrgDTO>>() {
    };

    public static final TypeReference<PageDTO<ArticleCoreDTO>> DT010003 = new TypeReference<PageDTO<ArticleCoreDTO>>() {
    };
    public static final TypeReference<PageDTO<ArticleCoreDTO>> DT010004 = new TypeReference<PageDTO<ArticleCoreDTO>>() {
    };
    public static final TypeReference<PageDTO<ArticleCoreDTO>> DT010005 = new TypeReference<PageDTO<ArticleCoreDTO>>() {
    };
    public static final TypeReference<PageDTO<ArticleAnswerDTO>> DT010006 = new TypeReference<PageDTO<ArticleAnswerDTO>>() {
    };

    public static final TypeReference<PageDTO<MeetingDTO>> DT040004 = new TypeReference<PageDTO<MeetingDTO>>() {
    };
    public static final TypeReference<PageDTO<MeetingDTO>> DT040005 = new TypeReference<PageDTO<MeetingDTO>>() {
    };
    public static final TypeReference<PageDTO<MeetingDTO>> DT040006 = new TypeReference<PageDTO<MeetingDTO>>() {
    };
    public static final TypeReference<PageDTO<ArticleAnswerDTO>> DT010010 = new TypeReference<PageDTO<ArticleAnswerDTO>>() {
    };

    public static final TypeReference<PageDTO<ApproveCoreDTO>> DT020001 = new TypeReference<PageDTO<ApproveCoreDTO>>() {
    };
}
