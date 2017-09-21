package yxinfo.core.framework.util;

import java.util.Random;

/**
 * 随机工具
 * Created by dy on 2016/6/28.
 */
public final class RandomUtil {

    public static final String ALL_CHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String LETTER_CHAR = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String NUMBER_CHAR = "0123456789";

    /**
     * 生成随机字符串
     *
     * @param length
     * @param souce
     * @return
     */
    public static String generateString( int length, String souce ) {

        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for ( int i = 0; i < length; i++ ) {
            sb.append( ALL_CHAR.charAt( random.nextInt( souce.length() ) ) );
        }
        return sb.toString();
    }
}
