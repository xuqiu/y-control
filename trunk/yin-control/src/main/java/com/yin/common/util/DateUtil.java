package com.yin.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Type DateUtil
 * @Desc
 * @author hongxia.huhx
 * @date 2012-3-4
 * @Version V1.0
 */
public class DateUtil {

    /**
     * @Title: getDateString
     * @Description: 使用"yyyy-MM-dd HH:mm:ss"格式化日期
     * @author Yanjh
     * @param date
     * @return String 返回类型
     */
    public static String getDateString(Date date) {
        return getDateString(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static String getDateDay(Date date) {
        return getDateString(date, "yyyy-MM-dd");
    }

    /**
     * @Title: getDateString
     * @Description: 格式化日期
     * @author Yanjh
     * @param date 日期
     * @param format 模式
     * @return String 返回类型
     */
    public static String getDateString(Date date, String format) {
        if (date != null) {
            SimpleDateFormat formatter = new SimpleDateFormat(format);
            String dateString = formatter.format(date);
            return dateString;
        }
        return null;
    }

    /**
     * yyyy-MM-dd格式时间转date
     * 
     * @param strDate
     * @return
     */
    public static Date getDatebyString(String strDate) {
        if (null != strDate && !"".equals(strDate)) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            try {
                return formatter.parse(strDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;

    }

}