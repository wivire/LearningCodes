package dataHub;

import org.apache.commons.lang.StringUtils;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;
import utlis.DistanceUtil;
import utlis.RedisUtilsSingleton;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * <pre>
 * Created with IntelliJ IDEA.
 * User: zhengzhi
 * Date: 2017/2/9
 * Time: 10:31
 * To change this template use File | Settings | File Templates.
 * </pre>
 *
 * @author Administrator
 */

/**
 * 统计一个月中在四个城市记录到的设备出现的天数，实现方法是：存redis，统计出现次数
 */

public class DataHubDeviceMonthSumJob {

    private String date;

    public DataHubDeviceMonthSumJob(String date) {
        this.date = date;
    }

    static {
        System.setProperty("spark.local.dir", "f:/spark-temp");
    }


    public void startSparkJob() {

        JavaSparkContext sc = new JavaSparkContext("local[4]", "DataHubDeviceMonthSumJob");

        JavaRDD<String> input = sc.textFile("file:///home/hadoop/software/ndmp/lwbtest/" + date);

        JavaPairRDD<String, String> result = input.mapToPair(new PairFunction<String, String, String>() {
            public Tuple2<String, String> call(String line) throws Exception {
                String[] split = line.split("\\t", -1);

                String imei = split[0];
                String idfa = split[1];
                String mac = split[2];
                double latitude = Double.parseDouble(split[4]);
                double longitude = Double.parseDouble(split[5]);

                String city = "other";
                if (DistanceUtil.pointDistance(new Tuple2<Double, Double>(39.9046363143, 116.4071136987), new Tuple2<Double, Double>(latitude, longitude)) < 100000) {
                    city = "bj";
                } else if (DistanceUtil.pointDistance(new Tuple2<Double, Double>(24.4790303305, 118.0871169942), new Tuple2<Double, Double>(latitude, longitude)) < 100000) {
                    city = "xm";
                } else if (DistanceUtil.pointDistance(new Tuple2<Double, Double>(22.2707231528, 113.5766877110), new Tuple2<Double, Double>(latitude, longitude)) < 100000) {
                    city = "zh";
                } else if (DistanceUtil.pointDistance(new Tuple2<Double, Double>(22.8167102042, 108.3668999675), new Tuple2<Double, Double>(latitude, longitude)) < 100000) {
                    city = "nn";
                }

                String DeviceId = "";

                if (StringUtils.isNotBlank(imei)) {
                    DeviceId = imei;
                } else if (StringUtils.isNotBlank(idfa)) {
                    DeviceId = idfa;
                } else {
                    DeviceId = mac;
                }
                return new Tuple2<String, String>(city, city + "-" + DeviceId);
            }
        }).distinct().groupByKey().mapValues(new Function<Iterable<String>, String>() {
            public String call(Iterable<String> Iden) throws Exception {

                Set<String> Devices = new LinkedHashSet<String>();

                Iterator<String> iterator = Iden.iterator();

                while (iterator.hasNext()) {
                    String currKey = iterator.next();

                    Devices.add(currKey);

                    if (Devices.size() == 100000) {
                        incrCount(Devices);
                        Devices.clear();
                    }
                }

                if (Devices.size() > 0) {
                    incrCount(Devices);
                }


                return "当日MAC新增为:";
            }
        });

        result.collectAsMap();

    }

    public void incrCount(Set<String> newKeys) {
//        RedisUtils redis = new RedisUtils();

        RedisUtilsSingleton redis = RedisUtilsSingleton.getSingleReidsUtils();
        redis.setjedis(0); //选择redis库
        redis.piplineIncrease(newKeys);

        redis.close();

    }


    public static void main(String[] args) throws Exception {


        try {
            String[] dates = new String[]{"2016-12-23", "2016-12-24", "2016-12-25", "2016-12-26", "2016-12-27", "2016-12-28",
                    "2016-12-29", "2016-12-30", "2016-12-31", "2017-01-01", "2017-01-02", "2017-01-03",
                    "2017-01-04", "2017-01-05", "2017-01-06", "2017-01-07", "2017-01-08", "2017-01-09",
                    "2017-01-10", "2017-01-11", "2017-01-12", "2017-01-13", "2017-01-14", "2017-01-15",
                    "2017-01-16", "2017-01-17", "2017-01-18", "2017-01-19", "2017-01-20", "2017-01-21",
                    "2017-01-22", "2017-01-23", "2017-01-24", "2017-01-25", "2017-01-26", "2017-01-27",
            };
            for (String date : dates) {
                new DataHubDeviceMonthSumJob(date).startSparkJob();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
