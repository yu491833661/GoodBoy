package yxinfo.core.service.ou.dto;

import org.hibernate.validator.constraints.NotEmpty;
import yxinfo.core.common.dto.BaseDTO;
import yxinfo.core.common.exception.ErrorCodeCore;
import yxinfo.core.common.validation.Add;
import yxinfo.core.common.validation.Update;
import yxinfo.core.service.ou.validation.*;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public class MemberDTO extends BaseDTO {

    private static final long serialVersionUID = -2158355359392110762L;

    @NotNull( groups = Update.class, message = ErrorCodeCore.NOT_NULL )
    private Integer id;

    // 登录名
    @NotEmpty( groups = { Add.class, Register.class }, message = ErrorCodeCore.NOT_EMPTY )
    private String loginName;

    // 密码
    @NotEmpty( groups = { Add.class, Register.class }, message = ErrorCodeCore.NOT_EMPTY )
    private String pwd;

    // 姓名
    @NotEmpty( groups = Accomplish.class, message = ErrorCodeCore.NOT_EMPTY )
    private String realName;

    // 昵称
    private String nickName;

    // 手机号
    private String mobile;

    // 头像
    private Integer head;

    // 创建时间
    private Date createAt;

    // 是否被删除（1是，0否）
    private Boolean isDel;

    // 性别（0无记录，1男，2女）
    private Short sex;

    // 认证状态（0待完善资料，1待认证，2已认证）
    private Short authStatus;

    // 职位
    private String position;

    // 人员工号
    private String fcode;

    // 组织
    private List<OrgDTO> orgs;

    // 角色id
    @NotEmpty( groups = { Add.class, Update.class }, message = ErrorCodeCore.NOT_EMPTY )
    private Integer roleId;

    // 角色
    private List<RoleDTO> roles;

    // 搜索词
    private String schText;

    // 角色id
    private List<Integer> roleIds;

    // 访问令牌
    private AccessTokenDTO token;

    // 登录信息
    private LoginInfoDTO loginInfo;

    // 座机
    private String tel;

    // 验证码
    private String vcode;

    // 票据
    private String ticket;

    // 人员类型（1个人，2企业）
    @NotEmpty( groups = { Add.class, Register.class, Update.class }, message = ErrorCodeCore.NOT_EMPTY )
    private Short ftype;

    // 邮箱
    @NotEmpty( groups = { Update.class, Accomplish.class }, message = ErrorCodeCore.NOT_EMPTY )
    private String email;

    // 部门名称
    private String departName;

    // 板块id
    @NotEmpty( groups = BlockMaster.class, message = ErrorCodeCore.NOT_EMPTY )
    private List<Integer> blockIds;

    // 学校id
    @NotEmpty( groups = SchoolAdmin.class, message = ErrorCodeCore.NOT_EMPTY )
    private List<Integer> schoolIds;

    // 专业特长
    @NotEmpty( groups = Personal.class, message = ErrorCodeCore.NOT_EMPTY )
    private List<String> majors;

    // 企业/学校名称
    @NotEmpty( groups = Enterprise.class, message = ErrorCodeCore.NOT_EMPTY )
    private String enterpriseName;

    // 学校id
    @NotNull( groups = Personal.class, message = ErrorCodeCore.NOT_EMPTY )
    private Integer enterpriseId;

    // 企业类型（）
    @NotNull( groups = Enterprise.class, message = ErrorCodeCore.NOT_NULL )
    private Short enterpriseType;

    public Integer getId() {
        return id;
    }

    public void setId( Integer id ) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName( String loginName ) {
        this.loginName = loginName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd( String pwd ) {
        this.pwd = pwd;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName( String realName ) {
        this.realName = realName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName( String nickName ) {
        this.nickName = nickName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile( String mobile ) {
        this.mobile = mobile;
    }

    public Integer getHead() {
        return head;
    }

    public void setHead( Integer head ) {
        this.head = head;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt( Date createAt ) {
        this.createAt = createAt;
    }

    public Boolean getIsDel() {
        return isDel;
    }

    public void setIsDel( Boolean del ) {
        isDel = del;
    }

    public Short getSex() {
        return sex;
    }

    public void setSex( Short sex ) {
        this.sex = sex;
    }

    public Short getAuthStatus() {
        return authStatus;
    }

    public void setAuthStatus( Short authStatus ) {
        this.authStatus = authStatus;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition( String position ) {
        this.position = position;
    }

    public String getFcode() {
        return fcode;
    }

    public void setFcode( String fcode ) {
        this.fcode = fcode;
    }

    public List<OrgDTO> getOrgs() {
        return orgs;
    }

    public void setOrgs( List<OrgDTO> orgs ) {
        this.orgs = orgs;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId( Integer roleId ) {
        this.roleId = roleId;
    }

    public List<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles( List<RoleDTO> roles ) {
        this.roles = roles;
    }

    public String getSchText() {
        return schText;
    }

    public void setSchText( String schText ) {
        this.schText = schText;
    }

    public List<Integer> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds( List<Integer> roleIds ) {
        this.roleIds = roleIds;
    }

    public AccessTokenDTO getToken() {
        return token;
    }

    public void setToken( AccessTokenDTO token ) {
        this.token = token;
    }

    public LoginInfoDTO getLoginInfo() {
        return loginInfo;
    }

    public void setLoginInfo( LoginInfoDTO loginInfo ) {
        this.loginInfo = loginInfo;
    }

    public String getTel() {
        return tel;
    }

    public void setTel( String tel ) {
        this.tel = tel;
    }

    public String getVcode() {
        return vcode;
    }

    public void setVcode( String vcode ) {
        this.vcode = vcode;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket( String ticket ) {
        this.ticket = ticket;
    }

    public Short getFtype() {
        return ftype;
    }

    public void setFtype( Short ftype ) {
        this.ftype = ftype;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail( String email ) {
        this.email = email;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName( String departName ) {
        this.departName = departName;
    }

    public List<Integer> getBlockIds() {
        return blockIds;
    }

    public void setBlockIds( List<Integer> blockIds ) {
        this.blockIds = blockIds;
    }

    public List<Integer> getSchoolIds() {
        return schoolIds;
    }

    public void setSchoolIds( List<Integer> schoolIds ) {
        this.schoolIds = schoolIds;
    }

    public List<String> getMajors() {
        return majors;
    }

    public void setMajors( List<String> majors ) {
        this.majors = majors;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName( String enterpriseName ) {
        this.enterpriseName = enterpriseName;
    }

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId( Integer enterpriseId ) {
        this.enterpriseId = enterpriseId;
    }

    public Short getEnterpriseType() {
        return enterpriseType;
    }

    public void setEnterpriseType( Short enterpriseType ) {
        this.enterpriseType = enterpriseType;
    }
}