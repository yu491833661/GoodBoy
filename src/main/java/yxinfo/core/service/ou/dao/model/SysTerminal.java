package yxinfo.core.service.ou.dao.model;

public class SysTerminal {
    //终端编号
    private String termCode;

    //名称
    private String fname;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_terminal.term_code
     *
     * @return the value of sys_terminal.term_code
     *
     * @mbggenerated
     */
    public String getTermCode() {
        return termCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_terminal.term_code
     *
     * @param termCode the value for sys_terminal.term_code
     *
     * @mbggenerated
     */
    public void setTermCode(String termCode) {
        this.termCode = termCode == null ? null : termCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_terminal.fname
     *
     * @return the value of sys_terminal.fname
     *
     * @mbggenerated
     */
    public String getFname() {
        return fname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_terminal.fname
     *
     * @param fname the value for sys_terminal.fname
     *
     * @mbggenerated
     */
    public void setFname(String fname) {
        this.fname = fname == null ? null : fname.trim();
    }
}