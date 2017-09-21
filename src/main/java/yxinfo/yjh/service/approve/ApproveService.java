package yxinfo.yjh.service.approve;

import yxinfo.core.common.dto.PageDTO;
import yxinfo.core.common.dto.RequestMsg;
import yxinfo.core.common.route.DataType;
import yxinfo.core.common.route.Route;
import yxinfo.core.common.route.RouteCode;
import yxinfo.core.service.ou.context.Roles;
import yxinfo.yjh.service.approve.dto.ApproveCoreDTO;

import java.util.List;

/**
 * 文章/提问审核
 *
 * @author jn
 * @date 2017/9/15 14:01
 **/
public interface ApproveService {

    /**
     * 添加审核
     *
     * @see yxinfo.yjh.service.approve.context.ApproveType
     *
     * @param approveCoreDTO
     * @param clearApprove 是否清除旧审核（文章移动或者修改后需要清除）
     */
    void addApprove( ApproveCoreDTO approveCoreDTO, boolean clearApprove );

    /**
     * 审核
     *
     * 1.根据审核id审核
     * 2.根据审核类型、审核状态、relId审核
     *
     * @param approveCoreDTO
     */
    @Route( code = RouteCode.Approve.APPROVE, allowRoles = {Roles.BLOCK_MASTER, Roles.SCHOOL} )
    void approve( ApproveCoreDTO approveCoreDTO, RequestMsg requestMsg );

    /**
     * 审核列表
     *
     * @param pageDTO
     * @param requestMsg
     * @return
     */
    @Route( code = RouteCode.Approve.APPROVE_LIST, allowRoles = {Roles.BLOCK_MASTER, Roles.SCHOOL}, dataType = DataType.class )
    PageDTO<List<ApproveCoreDTO>> approveList( PageDTO<ApproveCoreDTO> pageDTO, RequestMsg requestMsg );
}
