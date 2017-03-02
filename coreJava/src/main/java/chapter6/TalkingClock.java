package chapter6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * <pre>
 * Created with IntelliJ IDEA.
 * User: zhengzhi
 * Date: 2017/2/27
 * Time: 17:09
 * To change this template use File | Settings | File Templates.
 * </pre>
 *
 * @author Administrator
 */
public class TalkingClock {
    private int interval;
    private boolean beep;

    public TalkingClock(int interval, boolean beep) {
        this.interval = interval;
        this.beep = beep;
    }

    //开始时钟
    public void start() {
        ActionListener listener = new TimePrinter();
        Timer t = new Timer(interval, listener);
        t.start();
    }


    //内部类
    public class TimePrinter implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Date now = new Date();
            System.out.println("At the tone,the time is " + now);
            if (beep) Toolkit.getDefaultToolkit().beep();
//            if(TalkingClock.this.beep) Toolkit.getDefaultToolkit().beep();
        }
    }

    //在方法中定义局部内部类；
    public void begin() {
        class TimePrinter implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {

                Date now = new Date();
                System.out.println("at the tone,the time is " + now);
                if (beep) Toolkit.getDefaultToolkit().beep();
            }

        }

        ActionListener listener = new TimePrinter();
        Timer t = new Timer(interval, listener);
        t.start();
    }
}
