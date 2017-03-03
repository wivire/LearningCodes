import java.io.BufferedInputStream;
import java.io.IOException;

/**
 * <pre>
 * Created with IntelliJ IDEA.
 * User: zhengzhi
 * Date: 2017/3/3
 * Time: 9:35
 * To change this template use File | Settings | File Templates.
 * </pre>
 *
 * @author Administrator
 */
public class LinuxExecCmds {

    public static void main(String[] args) throws IOException {


        String cmd = "ping www.baidu.com";

        Runtime runtime = Runtime.getRuntime();

        Process p = runtime.exec(cmd);

        BufferedInputStream in = new BufferedInputStream(p.getInputStream());


    }
}
