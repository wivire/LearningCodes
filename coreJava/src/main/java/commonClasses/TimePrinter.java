package commonClasses;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * <pre>
 * Created with IntelliJ IDEA.
 * User: zhengzhi
 * Date: 2017/2/27
 * Time: 15:23
 * To change this template use File | Settings | File Templates.
 * </pre>
 *
 * @author Administrator
 */
public class TimePrinter implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        Date now = new Date();
        System.out.println("At the one,the time is " + now);
        //Toolkit.getDefaultToolkit().beep();
    }
}
