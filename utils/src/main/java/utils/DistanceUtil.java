package utils;

import scala.Tuple2;

/**
 * <pre>
 * Created with IntelliJ IDEA.
 * User: ly
 * Date: 2017/1/25
 * Time: 12:58
 * To change this template use File | Settings | File Templates.
 * </pre>
 *
 * @author ly
 */

public class DistanceUtil {
    private static final double ra = 6378.137 * 1000; //6378.140 赤道半径 (km)
    private static final double rb = 6356.755 * 1000; //极半径 (km)
    private static final double flatten = (ra - rb) / ra; // 地球扁率

    /**
     * @param Lat_A a点的纬度
     * @param Lng_A a点的经度
     * @param Lat_B b点的纬度
     * @param Lng_B b点的经度
     * @return
     */
    public static double calcDistance(double Lat_A, double Lng_A, double Lat_B, double Lng_B) {
        final double rad_lat_A = Math.toRadians(Lat_A);
        final double rad_lng_A = Math.toRadians(Lng_A);
        final double rad_lat_B = Math.toRadians(Lat_B);
        final double rad_lng_B = Math.toRadians(Lng_B);

        final double pA = Math.atan(rb / ra * Math.tan(rad_lat_A));
        final double pB = Math.atan(rb / ra * Math.tan(rad_lat_B));
        final double xx = Math.acos(Math.sin(pA) * Math.sin(pB) + Math.cos(pA) * Math.cos(pB) * Math.cos(rad_lng_A - rad_lng_B));
        final double c1 = (Math.sin(xx) - xx) * Math.pow(Math.sin(pA) + Math.sin(pB), 2) / Math.pow(Math.cos(xx / 2), 2);
        final double c2 = (Math.sin(xx) + xx) * Math.pow(Math.sin(pA) - Math.sin(pB), 2) / Math.pow(Math.sin(xx / 2), 2);
        final double dr = flatten / 8 * (c1 - c2);
        final double distance = ra * (xx + dr);

        return distance;
    }

    public static double pointDistance(Tuple2<Double, Double> pointA, Tuple2<Double, Double> pointB) {
        return calcDistance(pointA._1(), pointA._2(), pointB._1(), pointB._2());
    }


    //================================网络上的一种算法===================================//

    /**
     * 根据两点间经纬度坐标（double值），计算两点间距离，单位为米
     */
    private static final double EARTH_RADIUS = 6378137; //赤道半径

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * @param lon1 第一个点的经度
     * @param lat1 第一个点的纬度度
     * @param lon2 第二个点的经度
     * @param lat2 第二个点的经度
     * @return
     */
    public static double GetDistance(double lon1, double lat1, double lon2, double lat2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lon1) - rad(lon2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        return s;//单位米
    }

    public static void main(String[] args) {

        /**
         * (纬度,经度)
         * A = (32.060255, 118.796877) # 南京
         * B = (39.904211, 116.407395) # 北京
         */
        final Tuple2<Double, Double> A = new Tuple2<Double, Double>(32.060255, 118.796877);
        final Tuple2<Double, Double> B = new Tuple2<Double, Double>(39.904211, 116.407395);

        final double distance = pointDistance(A, B);
        System.out.println("北京与南京相距约: " + distance + "m");

        //网上计算方法
        final double distance1 = GetDistance(118.796877, 32.060255, 116.407395, 39.904211);
        System.out.println("#####距离#####" + distance1);
    }

}
