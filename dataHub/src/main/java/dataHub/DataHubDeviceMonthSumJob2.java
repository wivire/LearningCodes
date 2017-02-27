package dataHub;

import org.apache.hadoop.conf.Configuration;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.hive.HiveContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Map;

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
 * 统计一个月中在四个城市记录到的设备出现的天数，实现方法是：利用hive，统计出现次数
 */

public class DataHubDeviceMonthSumJob2 {

    protected transient Configuration conf;

    private String date;

    public DataHubDeviceMonthSumJob2(String date) {
        this.date = date;
    }


    static {
        System.setProperty("spark.local.dir", "f:/spark-temp");
    }


    public Object startSparkJob() {

        JavaSparkContext sc = new JavaSparkContext("local[4]", "DataHubDeviceMonthSumJob2");
        HiveContext sqlCtx = new HiveContext(sc.sc());

//
//        JavaRDD<String> input = sc.textFile("F:/" + date);
//
//
//        JavaRDD<Row> Identity = input.mapToPair(new PairFunction<String, String, Integer>() {
//            @Override
//            public Tuple2<String, Integer> call(String line) throws Exception {
//                String[] split = line.split("\\t", -1);
//
//
//                String imei = split[0];
//                String idfa = split[1];
//                String mac = split[2];
//                double latitude = Double.parseDouble(split[4]);
//                double longitude = Double.parseDouble(split[5]);
//
//                String city = "other";
//                if (DistanceUtil.pointDistance(new Tuple2<Double, Double>(39.9046363143, 116.4071136987), new Tuple2<Double, Double>(latitude, longitude)) < 100000) {
//                    city = "beijing";
//                } else if (DistanceUtil.pointDistance(new Tuple2<Double, Double>(24.4790303305, 118.0871169942), new Tuple2<Double, Double>(latitude, longitude)) < 100000) {
//                    city = "xiamen";
//                } else if (DistanceUtil.pointDistance(new Tuple2<Double, Double>(22.2707231528, 113.5766877110), new Tuple2<Double, Double>(latitude, longitude)) < 100000) {
//                    city = "zhuhai";
//                } else if (DistanceUtil.pointDistance(new Tuple2<Double, Double>(22.8167102042, 108.3668999675), new Tuple2<Double, Double>(latitude, longitude)) < 100000) {
//                    city = "nanning";
//                }
//
//                String DeviceId = "";
//
//                if (StringUtils.isNotBlank(imei)) {
//                    DeviceId = imei;
//                } else if (StringUtils.isNotBlank(idfa)) {
//                    DeviceId = idfa;
//                } else {
//                    DeviceId = mac;
//                }
//
//                JSONObject jsonObject = new JSONObject();
//                jsonObject.put("city", city);
//                jsonObject.put("DeviceId", DeviceId);
//
//                return new Tuple2<String, Integer>(jsonObject.toJSONString(), 1);
//            }
//        }).reduceByKey(new Function2<Integer, Integer, Integer>() {
//            @Override
//            public Integer call(Integer v1, Integer v2) throws Exception {
//                return v1 + v2;
//            }
//        }).map(new Function<Tuple2<String, Integer>, Row>() {
//            @Override
//            public Row call(Tuple2<String, Integer> tuple) throws Exception {
//                JSONObject jsonObject = JSON.parseObject(tuple._1);
//                String city = jsonObject.getString("city");
//                String DeviceId = jsonObject.getString("DeviceID");
//                int num = tuple._2;
//                return RowFactory.create(city, DeviceId, num);
//            }
//        });

//        /***第二步：动态构造DataFrame的元数据*/
//        List<StructField> structFields = new ArrayList<StructField>();
//        structFields.add(DataTypes.createStructField("city", DataTypes.StringType, true));
//        structFields.add(DataTypes.createStructField("deviceId", DataTypes.StringType, true));
//        structFields.add(DataTypes.createStructField("sum", DataTypes.IntegerType, true));
//
//        /***构建StructType，用于最后DataFrame元数据的描述*/
//        StructType structType =DataTypes.createStructType(structFields);
//
//        /*** 第三步：基于以后的MetaData以及RDD<Row>来构造DataFrame*/
//        DataFrame idenDf = sqlCtx.createDataFrame(Identity, structType);
//
//        idenDf.registerTempTable("dentity");
        sqlCtx.sql("use etl");

        List<Row> tables = sqlCtx.sql("show tables").collectAsList();
        System.out.println("######################################");
        System.out.println("show tables: " + tables);


        return null;

    }

    public static void main(String[] args) throws Exception {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("legoContext.xml");
        Map param = context.getBean("commonJobParam", Map.class);


        try {
            String[] dates = new String[]{"2016-12-20"};
            for (String date : dates) {
                new DataHubDeviceMonthSumJob2(date).startSparkJob();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
