package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * <pre>
 * Created with IntelliJ IDEA.
 * User: zhengzhi
 * Date: 2017/3/1
 * Time: 15:44
 * To change this template use File | Settings | File Templates.
 * </pre>
 *
 * @author Administrator
 */
public class CalendarDemo {

    /**
     * 产生两个日期之间的日期列表，包含开始日期和结束日期
     */
    public static List<String> genDateList(Date start, Date end) {

        List<String> dayList = new ArrayList<String>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        //获取一个Calendar对象
        Calendar cal = Calendar.getInstance();
        cal.setTime(start);
        while (start.compareTo(end) < 1) {
            dayList.add(simpleDateFormat.format(start));
            cal.add(Calendar.DAY_OF_MONTH, 1);
            start = cal.getTime();
        }

        return dayList;
    }

    /**
     * 从源日期列表中删除某些日期
     */

    public static List<String> getDelDateList(List<String> source, List<String> delList) {
        if (source != null && delList != null) {
            source.removeAll(delList);
        }
        return source == null ? new ArrayList<String>() : source;
    }


    /**
     * 获取当前时间
     */

    public static String getCurrentTime() {
        String formateStr = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formateStr);
        String currTimeStr = simpleDateFormat.format(new Date());
        return currTimeStr;
    }

    //获取专区日期区间
    public static String[] getTimeZone(String date) {
        String[] rl = new String[2];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cd = Calendar.getInstance();
        try {
            cd.setTime(sdf2.parse(date));
            cd.add(Calendar.DAY_OF_MONTH, -30);
            System.out.println("start: " + sdf2.format(cd.getTime()) + " end: " + date);
            rl[0] = String.valueOf(sdf.parse(sdf2.format(cd.getTime()) + " 00:00:00").getTime());
            rl[0] = rl[0].substring(0, rl[0].length() - 3);
            rl[1] = String.valueOf(sdf.parse(date + " 23:59:59").getTime());
            rl[1] = rl[1].substring(0, rl[1].length() - 3);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rl;
    }


    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        // 创建一个Calendar对象；
        Calendar calendar = Calendar.getInstance();

        //using the default time zone and locale.
        System.out.println("Date And Time is " + calendar.getTime());

        //格式化后时间
        System.out.println("Date And Time is " + sdf.format(calendar.getTime()));

        calendar.add(Calendar.DAY_OF_MONTH, 1);
        System.out.println("Date And Time is " + calendar.getTime());

        //格式化后时间
        System.out.println("Date And Time is " + sdf.format(calendar.getTime()));


        System.out.println("######## 获得一个时间区段 ###########");
        List<String> dateStrings = genDateList(new Date(10000), new Date(1000000000));
        for (String date : dateStrings) {
            System.out.println(date);
        }
        System.out.println("###################");

        //设置时间；
        calendar.setTime(new Date(1000000));
        System.out.println("获得特定时刻：" + calendar.getTime());


        /**
         *
         */
        String[] timeZone = getTimeZone("2017-01-01");
        for (String date : timeZone) {
            System.out.println(date);
        }


    }
}
