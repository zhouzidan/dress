package com.zhou.dress.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtils {

    public static long getMonthFirstDay() {
        Calendar calendar = Calendar.getInstance();// 获取当前日期
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        return calendar.getTimeInMillis();// (yearMonth).getTime();
    }

    /**
     * 获取两个月之间相差的月数
     * 
     * @param date1
     *            单位毫秒
     * @param date2
     *            单位毫秒
     * @return
     */
    public static int getMonthSpace(long date1, long date2) {
        int result = 0;
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(new Date(date1));
        c2.setTime(new Date(date2));
        result = c2.get(Calendar.MONDAY) - c1.get(Calendar.MONTH);
        return result == 0 ? 0 : Math.abs(result);
    }

    /**
     * 计算该时间戳是哪个小时内 // 现在几点
     * 
     * @param calldate
     *            单位毫秒
     * @return
     */
    public static int getTimesOfDay(long calldate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(calldate));
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 计算参数时间戳所在week的第几天 // 今天周几
     */
    public static int getDaysOfWeek(long calldate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(calldate));
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 1) {
            dayOfWeek = 7;
        } else {
            dayOfWeek = dayOfWeek - 1;
        }
        return dayOfWeek;
    }

    /**
     * 判断两个日期是否属于同一时段
     * 
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isInSameDayTime(long date1, long date2) {
        long date = Math.abs(date1 - date2);
        if (date / 1000 / 60 / 60 == 0)
            return true;
        return false;
    }

    /**
     * 判断两个日期是否属于同一天
     * 
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isInSameDay(long date1, long date2) {
        long date = Math.abs(date1 - date2);
        if (date / 1000 / 60 / 60 / 24 == 0)
            return true;
        return false;
    }

    public static String getYearMouth(long calldate) {
        // 字段（年+月）的值
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMM");
        return simpleDateFormat.format(calldate);
    }

    public static  String getFormatDate(String format,long timedate){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(timedate);
    }

    /**
     * 与当前日期相差的月份数
     * 
     * @param date
     * @return
     */
    public static int getCountMonthFromNow(long date) {

        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTimeInMillis(date);
        int monthCount1 = calendar1.get(Calendar.YEAR) * 12 + calendar1.get(Calendar.MONTH);
        Calendar calendar2 = Calendar.getInstance();
        int monthCount2 = calendar2.get(Calendar.YEAR) * 12 + calendar2.get(Calendar.MONTH);

        return Math.abs(monthCount2 - monthCount1);
    }

    /**
     * 获取当前日期距离1970年的月份数目
     * 
     * @return
     */
    public static int getCurrentMonthNum() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR) * 12 + calendar.get(Calendar.MONTH) + 1;
    }

    public static String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return sdf.format(date);
    }

    public static String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return sdf.format(date);
    }

    public static String getYearMouthFromNow() {
        // 字段（年+月）的值
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMM");
        return simpleDateFormat.format(new Date());
    }

    public static String getYearMouthFromFromLastYear() {
        // 字段（年+月）的值
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMM");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 1);
        return simpleDateFormat.format(calendar.getTime());
    }

    public static String getYearMouthFromTwoMouthAgo() {
        // 字段（年+月）的值
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMM");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 2);
        return simpleDateFormat.format(calendar.getTime());
    }

    public static int getCurrentYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    public static int getCurrentMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH) + 1;
    }

    public static int getCurrentDay() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static int getCurrentHour() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public static int getCurrentMin() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * 判断两个时间是否在同一个小时内
     * 
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isInOneHour(long date1, long date2) {
        long date = Math.abs(date1 - date2);
        if (date < 1000 * 60 * 60)
            return true;
        return false;
    }

    public static int getHourFromTime(long date) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTimeInMillis(date);
        return calendar1.get(Calendar.HOUR_OF_DAY);
    }

    public static int getMonthFromTime(long date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    public static int getDayFromTime(long date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static int getMinFromTime(long date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        return calendar.get(Calendar.MINUTE);
    }

    public static int getYearFromTime(long date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        return calendar.get(Calendar.YEAR);
    }

}
