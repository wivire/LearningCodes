package chapter6;

import commonClasses.TimePrinter;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * <pre>
 * Created with IntelliJ IDEA.
 * User: zhengzhi
 * Date: 2017/2/27
 * Time: 15:22
 * To change this template use File | Settings | File Templates.
 * </pre>
 *
 * @author Administrator
 */
public class TimerTest {

    public static void main(String[] args) {
        ActionListener listener = new TimePrinter();


        //定时器第一个参数是发出通告的间隔时间，10秒通告一次；第二个参数是监听器对象；
        Timer t = new Timer(10000, listener);

        //启动定时器；
        t.start();

        //弹出一个消息对话框，并等待用户点击Ok按钮来终止程序的执行；
        JOptionPane.showMessageDialog(null, "Quit program?");
        System.exit(0);

    }

}
