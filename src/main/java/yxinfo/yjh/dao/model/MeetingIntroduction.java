package yxinfo.yjh.dao.model;

public class MeetingIntroduction {
    //会议Id
    private Integer meetingId;

    //会议介绍
    private String meetingIntruduction;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column meeting_introduction.meeting_id
     *
     * @return the value of meeting_introduction.meeting_id
     *
     * @mbg.generated
     */
    public Integer getMeetingId() {
        return meetingId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column meeting_introduction.meeting_id
     *
     * @param meetingId the value for meeting_introduction.meeting_id
     *
     * @mbg.generated
     */
    public void setMeetingId(Integer meetingId) {
        this.meetingId = meetingId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column meeting_introduction.meeting_intruduction
     *
     * @return the value of meeting_introduction.meeting_intruduction
     *
     * @mbg.generated
     */
    public String getMeetingIntruduction() {
        return meetingIntruduction;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column meeting_introduction.meeting_intruduction
     *
     * @param meetingIntruduction the value for meeting_introduction.meeting_intruduction
     *
     * @mbg.generated
     */
    public void setMeetingIntruduction(String meetingIntruduction) {
        this.meetingIntruduction = meetingIntruduction == null ? null : meetingIntruduction.trim();
    }
}