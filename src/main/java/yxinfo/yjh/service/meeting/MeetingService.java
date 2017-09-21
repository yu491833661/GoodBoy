package yxinfo.yjh.service.meeting;

import yxinfo.core.common.dto.PageDTO;
import yxinfo.core.common.dto.RequestMsg;
import yxinfo.core.common.route.DataType;
import yxinfo.core.common.route.Route;
import yxinfo.core.common.route.RouteCode;
import yxinfo.yjh.service.meeting.dto.MeetingDTO;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hlf on 2017/9/15.
 */
public interface MeetingService {

    /**
     *  取消会议
     * @param id
     * @param requestMsg
     */
    @Route( code = RouteCode.Meeting.CANCEL_MEETING )
    void cancelMeeting ( Integer id, RequestMsg requestMsg );

    /**
     * 添加会议
     * @param meetingDTO
     * @param requestMsg
     */
    @Route( code = RouteCode.Meeting.ADD_MEETING )
    void saveMeeting( MeetingDTO meetingDTO, RequestMsg requestMsg );

    /**
     * 获取会议详情
     * @param id
     * @param requestMsg
     * @return
     */
    @Route( code = RouteCode.Meeting.GET_MEETING_DETAIL )
    MeetingDTO getMeetingById ( Integer id, RequestMsg requestMsg );

    /**
     * 管理员获取会议列表
     * @param meetingDTOPageDTO
     * @param requestMsg
     * @return
     */
    @Route( code = RouteCode.Meeting.GET_ADMIN_MEETING, dataType = DataType.class )
    PageDTO<List<MeetingDTO>> getAdminMeetingDTOList( PageDTO<MeetingDTO> meetingDTOPageDTO, RequestMsg requestMsg );

    /**
     * 会议封面展示正报名会议列表
     * @param meetingDTOPageDTO
     * @param requestMsg
     * @return
     */
    @Route( code = RouteCode.Meeting.GET_SIGNING_MEETING )
    PageDTO<List<MeetingDTO>> getFrontMeetingDTOList( PageDTO<MeetingDTO> meetingDTOPageDTO, RequestMsg requestMsg );

    /**
     * 展示往期会议列表
     * @param meetingDTOPageDTO
     * @param requestMsg
     * @return
     */
    @Route( code = RouteCode.Meeting.GET_BEFORE_MEETING )
    PageDTO<List<MeetingDTO>> getBeforeMeetingDTOList( PageDTO<MeetingDTO> meetingDTOPageDTO, RequestMsg requestMsg );
}
