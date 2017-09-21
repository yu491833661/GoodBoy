package yxinfo.core.common.dto;

/**
 * Created by dy on 2017/6/22.
 */
public class RequestMsg extends BaseDTO {

    private static final long serialVersionUID = -5047587199213091759L;

    // 请求码
    private String code;

    // 终端类型
    private String terminal;

    // 终端版本
    private String version;

    // 用户token
    private String token;

    // 组织id
    private Integer orgId;

    // 用户id
    private Integer memberId;

    // ip
    private String ip;

    // hit
    private String hit;

    public String getCode() {
        return code;
    }

    public void setCode( String code ) {
        this.code = code;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal( String terminal ) {
        this.terminal = terminal;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion( String version ) {
        this.version = version;
    }

    public String getToken() {
        return token;
    }

    public void setToken( String token ) {
        this.token = token;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId( Integer orgId ) {
        this.orgId = orgId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId( Integer memberId ) {
        this.memberId = memberId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp( String ip ) {
        this.ip = ip;
    }

    public String getHit() {
        return hit;
    }

    public void setHit( String hit ) {
        this.hit = hit;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append( "code: [" ).append( code );
        sb.append( "], terminal: [" ).append( terminal );
        sb.append( "], version: [" ).append( version );
        sb.append( "], token: [" ).append( token );
        sb.append( "], orgId: [" ).append( orgId );
        sb.append( "], ip: [" ).append( ip );
        sb.append( "], hit: [" ).append( hit ).append( "]" );
        return sb.toString();
    }
}
