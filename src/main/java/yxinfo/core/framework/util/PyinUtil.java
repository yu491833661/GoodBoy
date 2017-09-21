package yxinfo.core.framework.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import yxinfo.core.service.ou.context.PyinConstants;

/**
 * Created by dy on 2016/7/7.
 */
public class PyinUtil {

    private static final Logger log = LoggerFactory.getLogger( PyinUtil.class );

    /**
     * 获取拼音
     *
     * @param str
     * @return
     */
    public static String getPiny( String str ) {
        if ( StringUtils.isEmpty( str ) ) {
            return null;
        }
        return PyinUtil.cn2FullSpellLowercase( str, true ) + PyinConstants.SEPARATOR + PyinUtil.cn2FirstSpellLowercase( str, true );
    }

    /**
     * 字符串转小写全拼
     *
     * @param cn        待转换字符串
     * @param nonCnJoin 是否保留非中文字符
     * @return
     */
    public static String cn2FullSpellLowercase( String cn, boolean nonCnJoin ) {
        if ( cn == null || "".equals( cn.trim() ) ) {
            return "";
        }
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setToneType( HanyuPinyinToneType.WITHOUT_TONE );
        char[] chars = cn.toCharArray();
        StringBuffer spell = new StringBuffer();
        for ( char c : chars ) {
            if ( Character.toString( c ).matches( "[\\u4E00-\\u9FA5]+" ) ) {
                try {
                    String[] spells = PinyinHelper.toHanyuPinyinStringArray( c, format );
                    if ( spells != null && spells.length > 0 ) {
                        for ( String spe : spells ) {
                            spell.append( spe );
                        }
                    }
                } catch ( Exception e ) {
                    e.printStackTrace();
                }
            } else {
                if ( nonCnJoin ) {
                    spell.append( c );
                }
            }
        }
        return spell.toString();
    }

    /**
     * 字符串转小写拼音首字母
     *
     * @param cn        待转换字符串
     * @param nonCnJoin 是否保留非中文字符
     * @return
     */
    public static String cn2FirstSpellLowercase( String cn, boolean nonCnJoin ) {
        if ( cn == null || "".equals( cn.trim() ) ) {
            return "";
        }
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setToneType( HanyuPinyinToneType.WITHOUT_TONE );
        char[] chars = cn.toCharArray();
        StringBuffer spell = new StringBuffer();
        for ( char c : chars ) {
            if ( Character.toString( c ).matches( "[\\u4E00-\\u9FA5]+" ) ) {
                try {
                    String[] spells = PinyinHelper.toHanyuPinyinStringArray( c, format );
                    if ( spells != null && spells.length > 0 ) {
                        String spp = spells[0];
                        if ( spp != null && spp.length() > 0 ) {
                            spell.append( spells[0].charAt( 0 ) );
                        }
                    }
                } catch ( Exception e ) {
                    e.printStackTrace();
                }
            } else {
                if ( nonCnJoin ) {
                    spell.append( c );
                }
            }
        }
        return spell.toString();
    }


}
