package yxinfo.core.service.sms.dao.model;

public class EvtVcodeTem {
    //功能码
    private String fcode;

    //短信模板
    private String tem;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column evt_vcode_tem.fcode
     *
     * @return the value of evt_vcode_tem.fcode
     *
     * @mbggenerated
     */
    public String getFcode() {
        return fcode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column evt_vcode_tem.fcode
     *
     * @param fcode the value for evt_vcode_tem.fcode
     *
     * @mbggenerated
     */
    public void setFcode(String fcode) {
        this.fcode = fcode == null ? null : fcode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column evt_vcode_tem.tem
     *
     * @return the value of evt_vcode_tem.tem
     *
     * @mbggenerated
     */
    public String getTem() {
        return tem;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column evt_vcode_tem.tem
     *
     * @param tem the value for evt_vcode_tem.tem
     *
     * @mbggenerated
     */
    public void setTem(String tem) {
        this.tem = tem == null ? null : tem.trim();
    }
}