package chapter6;

import javax.swing.*;

/**
 * <pre>
 * Created with IntelliJ IDEA.
 * User: zhengzhi
 * Date: 2017/2/27
 * Time: 17:14
 * To change this template use File | Settings | File Templates.
 * </pre>
 *
 * @author Administrator
 */
public class InnerClassTest {

    public static void main(String[] args) {
        TalkingClock clock = new TalkingClock(1000, true);
        //构建一个内部类TimePrinter
        TalkingClock.TimePrinter timePrinter = clock.new TimePrinter();
        clock.start();

        //keep program running until user select "Ok"
        JOptionPane.showMessageDialog(null, "Quit programm?");
        System.exit(0);


    }

}
