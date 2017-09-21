package yxinfo.core.framework.exception;


/**
 * 自定义异常
 * Created by dy on 2016/06/20.
 */
public class DctException extends RuntimeException {

    private static final String SEPR = "|";
    private static final long serialVersionUID = -7231399924542442151L;

    private String code;

    private String[] extra;

    public DctException( String code ) {
        this.code = code;
    }

    public DctException( String code, String extra ) {
        this.code = code;
        this.extra = new String[]{ extra };
    }

    public DctException( String code, String... extras ) {
        this.code = code;
        this.extra = extras;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

    @Override
    public String toString() {
        if ( this.extra != null && this.extra.length > 0 ) {
            StringBuffer msg = new StringBuffer( this.code );
            for ( String ex : this.extra ) {
                msg.append( SEPR ).append( ex );
            }
            return msg.toString();
        }
        return this.code;
    }

    public String getCode() {
        return code;
    }

    public void setCode( String code ) {
        this.code = code;
    }

    public String[] getExtra() {
        return extra;
    }

    public void setExtra( String[] extra ) {
        this.extra = extra;
    }
}
