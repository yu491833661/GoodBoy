package yxinfo.core.framework.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期工具
 * Created by dy on 2016/6/28.
 */
public class DateUtil {

    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String TIME_PATTERN = "HH:mm:ss";
    public static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DATETIME_PATTERN_NOSEC = "yyyy-MM-dd HH:mm";
    public static final String SIMPLE_DATE_PATTERN = "yyyyMMdd";
    public static final String SIMPLE_TIME_PATTERN = "HHmmss";
    public static final String SIMPLE_PATTERN = "yyyyMMddHHmmss";

    /**
     * 返回日期格式
     *
     * @return
     */
    public static String getDate( Date date ) {
        return format( date, DATE_PATTERN );
    }

    /**
     * 返回时间格式
     *
     * @return
     */
    public static String getTime( Date date ) {
        return format( date, TIME_PATTERN );
    }

    /**
     * 返回完整格式
     *
     * @return
     */
    public static String getDateTime( Date date ) {
        return format( date, DATETIME_PATTERN );
    }

    /**
     * 返回完整格式
     *
     * @return
     */
    public static String getDateFormatPatter( Date date ) {
        return format( date, SIMPLE_PATTERN );
    }

    /**
     * 将字符串（yyyy-MM-dd）转换为时间
     *
     * @param strDate
     * @return
     * @throws ParseException
     */
    public static Date strToDate( String strDate ) throws ParseException {
        return parse( strDate, DATE_PATTERN );
    }

    /**
     * 将字符串（HH:mm:ss）转换为时间
     *
     * @param strTime
     * @return
     * @throws ParseException
     */
    public static Date strToTime( String strTime ) throws ParseException {
        return parse( strTime, TIME_PATTERN );
    }

    /**
     * 将字符串（yyyy-MM-dd HH:mm:ss）转换为时间
     *
     * @param strDate
     * @return
     * @throws ParseException
     */
    public static Date strToDateTime( String strDate ) throws ParseException {
        return parse( strDate, DATETIME_PATTERN );
    }

    public static Date getDate( String dateStr ) throws ParseException {
        if ( StringUtil.isEmpty( dateStr ) ) {
            return null;
        }
        String pattern = DATETIME_PATTERN;
        if ( dateStr.length() == 10 ) {
            pattern = DATE_PATTERN;
        }
        SimpleDateFormat sdf = new SimpleDateFormat( pattern );
        return sdf.parse( dateStr );
    }

    /**
     * 格式化时间
     *
     * @param date
     * @param patter
     * @return
     */
    public static String format( Date date, String patter ) {
        return null == date ? "" : new SimpleDateFormat( patter ).format( date );
    }

    /**
     * 解析时间字符串
     *
     * @param strDate
     * @param patter
     * @return
     * @throws ParseException
     */
    public static Date parse( String strDate, String patter ) throws ParseException {
        return strDate == null ? null : new SimpleDateFormat( patter ).parse( strDate );
    }

    /**
     * 格式化时间为字符串（yyyy-MM-dd HH:mm:ss）
     *
     * @param date
     * @return
     */
    public static String formatDatetime( Date date ) {
        SimpleDateFormat sdf = new SimpleDateFormat( DATETIME_PATTERN );
        return sdf.format( date );
    }

    /**
     * 检查字符串是否为patter格式
     *
     * @param str
     * @param patter
     * @return
     */
    public static boolean checkDate( String str, String patter ) {
        if ( str != null ) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat( patter );
                sdf.setLenient( false );
                sdf.parse( str );
            } catch ( ParseException e ) {
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * 加上天数
     *
     * @param date
     * @param day
     * @return
     */
    public static Date addDate( Date date, int day ) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime( date );
        calendar.add( Calendar.DATE, day );
        return calendar.getTime();
    }

    /**
     * 加上秒数
     *
     * @param date
     * @param sec
     * @return
     */
    public static Date addSecond( Date date, int sec ) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime( date );
        calendar.add( Calendar.SECOND, sec );
        return calendar.getTime();
    }

    /**
     * 计算间隔天数
     *
     * @param from
     * @param to
     * @return
     */
    public static long getIntervalDays( Date from, Date to ) {
        Calendar fromCalendar = Calendar.getInstance();
        fromCalendar.setTime( from );
        fromCalendar.set( Calendar.HOUR_OF_DAY, 0 );
        fromCalendar.set( Calendar.MINUTE, 0 );
        fromCalendar.set( Calendar.SECOND, 0 );
        fromCalendar.set( Calendar.MILLISECOND, 0 );
        Calendar toCalendar = Calendar.getInstance();
        toCalendar.setTime( to );
        toCalendar.set( Calendar.HOUR_OF_DAY, 0 );
        toCalendar.set( Calendar.MINUTE, 0 );
        toCalendar.set( Calendar.SECOND, 0 );
        toCalendar.set( Calendar.MILLISECOND, 0 );
        return ( toCalendar.getTime().getTime() - fromCalendar.getTime().getTime() ) / ( 1000 * 60 * 60 * 24 );
    }

    /**
     * 计算间隔秒数
     *
     * @param from
     * @param to
     * @return
     */
    public static long getIntervalSeconds( Date from, Date to ) {
        return ( to.getTime() - from.getTime() ) / 1000;
    }

    /**
     * 获取星期
     * <p> 周一至周日返回1至7 <p/>
     *
     * @param date
     * @return
     */
    public static int getWeek( Date date ) {
        Calendar cal = Calendar.getInstance();
        cal.setTime( date );
        int wk = cal.get( Calendar.DAY_OF_WEEK ) - 1;
        return wk == 0 ? 7 : wk;
    }
}
