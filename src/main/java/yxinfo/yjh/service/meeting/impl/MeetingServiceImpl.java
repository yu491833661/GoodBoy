package yxinfo.yjh.service.meeting.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import yxinfo.core.common.dto.PageDTO;
import yxinfo.core.common.dto.RequestMsg;
import yxinfo.core.common.exception.ErrorCodeCore;
import yxinfo.core.framework.exception.DctException;
import yxinfo.core.framework.service.dal.Page;
import yxinfo.core.framework.util.SQLUtil;
import yxinfo.core.framework.util.StringUtil;
import yxinfo.yjh.dao.mapper.MeetingCoreMapper;
import yxinfo.yjh.dao.mapper.MeetingIntroductionMapper;
import yxinfo.yjh.dao.model.MeetingCore;
import yxinfo.yjh.dao.model.MeetingCoreExample;
import yxinfo.yjh.dao.model.MeetingIntroduction;
import yxinfo.yjh.service.meeting.MeetingService;
import yxinfo.yjh.service.meeting.context.MeetingFstatus;
import yxinfo.yjh.service.meeting.context.MeetingSignStatus;
import yxinfo.yjh.service.meeting.dto.MeetingDTO;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by hlf on 2017/9/15.
 */
@Component
public class MeetingServiceImpl implements MeetingService {

    @Resource
    private MeetingCoreMapper meetingCoreMapper;
    @Resource
    private MeetingIntroductionMapper meetingIntroductionMapper;

    /**
     *
     * @param id
     * @param requestMsg
     */
    @Transactional
    public void cancelMeeting ( Integer id, RequestMsg requestMsg ) {
        if ( null == id ) {
            throw new DctException( ErrorCodeCore.DATA_NOT_NULL );
        }

        MeetingCore meetingCore = new MeetingCore();
        meetingCore.setId( id );
        meetingCore.setFstatus( MeetingFstatus.CANCEL );
        meetingCoreMapper.updateByPrimaryKeySelective( meetingCore );
    }

    /**
     * 获取往期会议列表
     * @param meetingDTOPageDTO
     * @param requestMsg
     * @return
     */
    public PageDTO<List<MeetingDTO>> getBeforeMeetingDTOList( PageDTO<MeetingDTO> meetingDTOPageDTO, RequestMsg requestMsg ) {

        if ( null == meetingDTOPageDTO || null == meetingDTOPageDTO.getData() ) {
            throw new DctException( ErrorCodeCore.DATA_NOT_NULL );
        }

        PageDTO<List<MeetingDTO>> meetingPageDTO = new PageDTO<List<MeetingDTO>>();
        Page page = meetingDTOPageDTO.toModel( Page.class );

        MeetingCoreExample example = new MeetingCoreExample();
        MeetingCoreExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause( "start_at ASC" );
        example.setPage( page );

        criteria.andSignStatusEqualTo( MeetingSignStatus.SIGN_END );
        criteria.andFstatusNotEqualTo( MeetingFstatus.CANCEL );
        criteria.andFstatusNotEqualTo( MeetingFstatus.WAIT_PUBLISH );

        List<MeetingCore> meetingCoreList = meetingCoreMapper.selectByExample( example );

        List<MeetingDTO> meetingDTOList = new ArrayList<MeetingDTO>();
        if ( !CollectionUtils.isEmpty( meetingCoreList ) ) {
            for ( MeetingCore meetingCore: meetingCoreList ) {
                MeetingDTO tempMeeting = new MeetingDTO();
                tempMeeting.toDTO( meetingCore );
                meetingDTOList.add( tempMeeting );
            }
        }
        meetingPageDTO.toDTO( page );
        meetingPageDTO.setData( meetingDTOList );
        return meetingPageDTO;



    }

    /**
     * 会议封面展示正报名会议列表
     * @param meetingDTOPageDTO
     * @param requestMsg
     * @return
     */
    public PageDTO<List<MeetingDTO>> getFrontMeetingDTOList( PageDTO<MeetingDTO> meetingDTOPageDTO, RequestMsg requestMsg ) {

        if ( null == meetingDTOPageDTO || null == meetingDTOPageDTO.getData() ) {
            throw new DctException( ErrorCodeCore.DATA_NOT_NULL );
        }

        MeetingDTO meetingDTO = meetingDTOPageDTO.getData();
        meetingDTO.setFstatus( MeetingFstatus.WAIT_START );
        meetingDTO.setSignStatus( MeetingSignStatus.SIGNING );

        return getMeetingDTOList( meetingDTOPageDTO );
    }

    /**
     * 会议管理员获取会议列表
     * @param meetingDTOPageDTO
     * @param requestMsg
     * @return
     */
    public PageDTO<List<MeetingDTO>> getAdminMeetingDTOList( PageDTO<MeetingDTO> meetingDTOPageDTO, RequestMsg requestMsg ) {

        if ( null == meetingDTOPageDTO || null == meetingDTOPageDTO.getData() ) {
            throw new DctException( ErrorCodeCore.DATA_NOT_NULL );
        }

        meetingDTOPageDTO.getData().setMemberId( requestMsg.getMemberId() );

        return getMeetingDTOList( meetingDTOPageDTO );
    }

    /**
     * 获取会议列表
     * @param meetingDTOPageDTO
     * @return
     */
    public PageDTO<List<MeetingDTO>> getMeetingDTOList( PageDTO<MeetingDTO> meetingDTOPageDTO ) {

        PageDTO<List<MeetingDTO>> meetingPageDTO = new PageDTO<List<MeetingDTO>>();
        MeetingDTO meetingDTO = meetingDTOPageDTO.getData();

        Page page = meetingDTOPageDTO.toModel( Page.class );

        MeetingCoreExample example = new MeetingCoreExample();
        MeetingCoreExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause( "publish_at ASC" );
        example.setPage( page );

        String fname = meetingDTO.getFname();
        if ( !StringUtil.isEmpty( fname ) ) {
            criteria.andFnameLike( SQLUtil.toLink( fname ) );
        }

        Date startAt = meetingDTO.getStartAt();
        if ( null != startAt ) {
            criteria.andPublishAtGreaterThan( startAt );
        }

        Date endAt = meetingDTO.getEndAt();
        if ( null != endAt ) {
            criteria.andPublishAtLessThan( endAt );
        }

        Short fstatus = meetingDTO.getFstatus();
        if ( null != fstatus ) {
            criteria.andFstatusEqualTo( fstatus );
        }

        Short signStatus = meetingDTO.getSignStatus();
        if ( null != signStatus ) {
            criteria.andSignStatusEqualTo( signStatus );
        }

        Integer memberId = meetingDTO.getMemberId();
        if ( null != memberId ) {
            criteria.andMemberIdEqualTo( memberId );
        }

        List<MeetingCore> meetingCoreList = meetingCoreMapper.selectByExample( example );

        List<MeetingDTO> meetingDTOList = new ArrayList<MeetingDTO>();
        if ( !CollectionUtils.isEmpty( meetingCoreList ) ) {
            for ( MeetingCore meetingCore: meetingCoreList ) {
                MeetingDTO tempMeeting = new MeetingDTO();
                tempMeeting.toDTO( meetingCore );
                meetingDTOList.add( tempMeeting );
            }
        }
        meetingPageDTO.toDTO( page );
        meetingPageDTO.setData( meetingDTOList );
        return meetingPageDTO;
    }

    /**
     * 获取会议详情
     * @param id
     * @param requestMsg
     * @return
     */
    public MeetingDTO getMeetingById ( Integer id, RequestMsg requestMsg ) {

        if ( null == id ) {
            throw new DctException( ErrorCodeCore.DATA_NOT_NULL );
        }

        MeetingCore meetingCore = meetingCoreMapper.selectByPrimaryKey( id );
        if ( null == meetingCore ) {
            throw new DctException( ErrorCodeCore.Meeting.MEETING_NOT_FIND );
        }
        MeetingDTO meetingDTO = new MeetingDTO().toDTO( meetingCore );

        MeetingIntroduction meetingIntroduction = meetingIntroductionMapper.selectByPrimaryKey( id );

        if ( null != meetingIntroduction ) {
            meetingDTO.setMeetingIntruduction( meetingIntroduction.getMeetingIntruduction() );
        }

        return meetingDTO;
    }

    /**
     * 添加会议
     * @param meetingDTO
     * @param requestMsg
     */
    @Transactional
    public void saveMeeting ( MeetingDTO meetingDTO, RequestMsg requestMsg ) {
        // 校验数据
        checkMeetingStatic( meetingDTO );

        // 数据准备
        Integer meetingId = meetingDTO.getId();
        String intruduction = meetingDTO.getMeetingIntruduction();
        Date currentDate = new Date();
        MeetingCore meetingCore = meetingDTO.toModel( MeetingCore.class );
        meetingCore.setMemberId( requestMsg.getMemberId() );
        if ( MeetingFstatus.WAIT_START.equals( meetingDTO.getFstatus() ) ) {
            meetingCore.setPublishAt( currentDate );
            meetingCore.setSignStatus( MeetingSignStatus.SIGNING );
        } else {
            meetingCore.setSignStatus( MeetingSignStatus.WAIT_SIGN );
        }

        // 更新数据
        if ( null != meetingId ) {
            try {
                meetingCoreMapper.updateByPrimaryKey(meetingCore);
            } catch ( Exception e ) {
                throw new DctException( ErrorCodeCore.Meeting.MEETING_NOT_FIND );
            }
            meetingIntroductionMapper.deleteByPrimaryKey( meetingId );
        } else {
            meetingCore.setCreateAt( currentDate );
            meetingCoreMapper.insert( meetingCore );
        }
        insertMeetingIntruduction( meetingCore.getId(), intruduction );
    }

    /**
     * 添加会议介绍
     * @param meetingId
     * @param intruduction
     */
    private void insertMeetingIntruduction( Integer meetingId, String intruduction ) {
        if ( !StringUtil.isEmpty( intruduction ) ) {
            MeetingIntroduction meetingIntroduction = new MeetingIntroduction();
            meetingIntroduction.setMeetingId( meetingId );
            meetingIntroduction.setMeetingIntruduction( intruduction );
            meetingIntroductionMapper.insert( meetingIntroduction );
        }
    }

    /**
     * 校验参数
     * @param meetingDTO
     */
    private void checkMeetingStatic( MeetingDTO meetingDTO ) {
        // 参数校验
        Short fstatus = meetingDTO.getFstatus();
        Integer attendPersonNum = meetingDTO.getAttendPersonNum();
        Date signEndAt = meetingDTO.getSignEndAt();
        Date startAt = meetingDTO.getStartAt();
        Date endAt = meetingDTO.getEndAt();
        String place = meetingDTO.getPlace();
        String meetingIntruduction = meetingDTO.getMeetingIntruduction();
        Date currentDate = new Date();

        if ( meetingDTO == null ) {
            throw new DctException( ErrorCodeCore.DATA_NOT_NULL );
        }

        // 会议状态不能为（ 1、待发布 2、待进行 ）之外的状态
        if ( !MeetingFstatus.WAIT_START.equals( fstatus ) && !MeetingFstatus.WAIT_PUBLISH.equals( fstatus ) ) {
            throw new DctException( ErrorCodeCore.Meeting.FSTATUS_NOT_LEGAL );
        }

        // 若参加会议人数不为空，则参加会议人数不能小于1
        if ( null != attendPersonNum ) {
            if ( attendPersonNum < 1 ) {
                throw new DctException( ErrorCodeCore.Meeting.ATTENDPERSON_MUST_NOT_LESS_THAN_ONE );
            }
        }

        // 若报名截至时间与会议开始时间不为空时，报名截至时间不能大于会议开始时间
        if ( null != signEndAt && null != startAt ) {
            if ( startAt.getTime() < signEndAt.getTime() )
                throw new DctException( ErrorCodeCore.Meeting.SIGN_TIME_NOT_GREATER_THAN_START );
        }

        // 会议开始时间与会议结束时间不为空时，会议开始时间不能大于会议结束时间
        if ( null != startAt && null != endAt ) {
            if ( endAt.getTime() <= startAt.getTime() ) {
                throw new DctException( ErrorCodeCore.Meeting.END_TIME_NOT_LESS_THAN_START );
            }
        }

        // 会议地点不能超过100个字
        if ( null != place ) {
            if ( place.length() >= 200 ) {
                throw new DctException( ErrorCodeCore.Meeting.PLACE_LENTH_LESS_THAN_HUNDRAD );
            }
        }

        // 会议发布时
        if ( MeetingFstatus.WAIT_START.equals( fstatus ) ) {
            // 参会人数不能为空
            if ( null == attendPersonNum ) {
                throw new DctException( ErrorCodeCore.Meeting.ATTENDPERSON_NOT_NULL );
            }

            // 报名截至时间不能为空
            if ( null == signEndAt ) {
                throw new DctException( ErrorCodeCore.Meeting.SIGN_END_TIME_NOT_NULL );
            } else {
                if ( signEndAt.getTime() <= currentDate.getTime() ) {
                    throw new DctException( ErrorCodeCore.Meeting.SiGN_END_TIME_MUST_GREATE_CURRENT );
                }
            }

            // 会议开始时间不能为空
            if ( null == startAt ) {
                throw new DctException( ErrorCodeCore.Meeting.START_TIME_NOT_NULL );
            } else {
                if ( startAt.getTime() <= currentDate.getTime() ) {
                    throw new DctException( ErrorCodeCore.Meeting.START_TIME_MUST_GREATE_CURRENT );
                }
            }

            // 会议结束时间不能为空
            if ( null== endAt ) {
                throw new DctException( ErrorCodeCore.Meeting.END_TIME_NOT_NULL );
            } else {
                if ( endAt.getTime() <= currentDate.getTime() ) {
                    throw new DctException( ErrorCodeCore.Meeting.END_TIME_MUST_GREATE_CURRENT );
                }
            }

            // 会议地点不能为空
            if ( StringUtil.isEmpty( place ) ) {
                throw new DctException( ErrorCodeCore.Meeting.PLACE_NOT_NULL );
            }

            // 会议介绍不能为空
            if ( StringUtil.isEmpty( meetingIntruduction ) ) {
                throw new DctException( ErrorCodeCore.Meeting.INTRUDUCTION_NOT_NULL );
            }
        }
    }
}
