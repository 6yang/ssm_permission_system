package cn.yang.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/***
 * @ClassName: DateUtils
 * @Description:
 * @Auther: 6yang
 * @Date: 2019/10/821:00
 * @version : V1.0
 */
public class DateUtils {
    //日期转换成字符串
    public static String date2String(Date date,String patt){
        SimpleDateFormat sdf = new SimpleDateFormat(patt);
        return sdf.format(date);
    }

    //字符串转化成日期
    public static Date string2Date(String str,String patt) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(patt);
        return sdf.parse(str);
    }
}
