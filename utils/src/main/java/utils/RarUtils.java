package utils;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.Executor;
import org.apache.commons.lang.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;


public class RarUtils {

    protected static Logger logger = LoggerFactory.getLogger(RarUtils.class);

    //调用系统的RAR软件 路径
    public static final String RAR_BIN_DIR = "/Applications/WinRAR.app/Contents/Resources/drive_c/Program Files/Common Files";


    public void rar(String inFilePath, String outFilePath, String password) throws Exception {
        Executor executor = new DefaultExecutor();

        CommandLine cmd = null;//new CommandLine("ls -l");
        if (SystemUtils.IS_OS_WINDOWS) {
            //如果是windows系统
            //命令行命令
            cmd = new CommandLine(new File(RAR_BIN_DIR, "winrar-x64-393.exe"));
            //" \"" + pdfFilePath + "\" -t -T 9 -o \"" + filePath + "/" + "%.swf\"";
            cmd.addArgument("--help");
            cmd.addArgument("-T");
            cmd.addArgument("9");
            cmd.addArgument("-o");
            cmd.addArgument(outFilePath);
            //Runtime执行后返回创建的进程对象
            //pro = Runtime.getRuntime().exec(cmd);
        } else {
            //如果是linux系统,路径不能有空格，而且一定不能用双引号，否则无法创建进程

            cmd = new CommandLine(new File(RAR_BIN_DIR, "winrar-x64-393.exe"));
            cmd.addArgument("--help");
        }

        logger.info(org.apache.commons.lang.StringUtils.join(cmd.toStrings(), " "));

        //非要读取一遍cmd的输出，要不不会flush生成文件（多线程）
        //new DoOutput(pro.getInputStream()).start();
        //new DoOutput(pro.getErrorStream()).start();
        try {
            int execute = executor.execute(cmd);
            logger.info("rar result: " + execute);
        } catch (Exception e) {
            logger.error("转换swf异常：" + e.getMessage(), e);
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

    }
}
