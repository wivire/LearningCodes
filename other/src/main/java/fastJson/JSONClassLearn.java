package fastJson;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.joda.time.DateTime;

/**
 * <pre>
 * Created with IntelliJ IDEA.
 * User: zhengzhi
 * Date: 2017/2/14
 * Time: 10:52
 * To change this template use File | Settings | File Templates.
 * </pre>
 *
 * @author Administrator
 */
public class JSONClassLearn {


    public static void main(String[] args) {

        String source = "{ \"mac\":\"20:5C:FA:7E:29:57\", \"probe\":[{\"M\":\"7c04d07412c2\",\"P\":-79},{\"M\":\"b8fc9a0031e8\",\"P\":-76}]}";

        /***解析JSON文本为JSONObject对象 */
        JSONObject jsonObject = JSON.parseObject(source);

        if (jsonObject.containsKey("mac") && jsonObject.containsKey("probe")) {
            String wifiMac = jsonObject.getString("mac").replaceAll(":", "");
            String time = DateTime.now().toString("yyyy-MM-dd HH:mm:ss");

            JSONArray probe = jsonObject.getObject("probe", JSONArray.class);
        }


    }
}
