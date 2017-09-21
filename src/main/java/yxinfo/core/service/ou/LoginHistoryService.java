package yxinfo.core.service.ou;

import yxinfo.core.common.dto.PageDTO;
import yxinfo.core.service.ou.dto.LoginHistoryDTO;

import java.util.List;

/**
 * Created by dy on 2017/4/15.
 */
public interface LoginHistoryService {

    /**
     * 添加登录历史
     * <p> 需要请求外部接口, 建议异步调用 <p/>
     *
     * @param memberId
     * @param terminal 终端编号, {@link yxinfo.core.service.ou.context.Terminal}
     * @param ip
     */
    void addLoginHistory( Integer memberId, String terminal, String ip );

    /**
     * 分页查询用户登录历史
     * <p>
     * schPage.data.memberId 必传
     * schPage.data.termCode 终端编号, {@link yxinfo.core.service.ou.context.Terminal}
     * </p>
     *
     * @param schPage
     * @return
     */
    PageDTO<List<LoginHistoryDTO>> getLoginHistoryPageByMemberId( PageDTO<LoginHistoryDTO> schPage );
}
