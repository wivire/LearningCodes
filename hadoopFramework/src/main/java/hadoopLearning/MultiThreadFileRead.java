package hadoopLearning; /**
 * <pre>
 * Created with IntelliJ IDEA.
 * User: zhengzhi
 * Date: 2017/1/19
 * Time: 9:36
 * To change this template use File | Settings | File Templates.
 * </pre>
 *
 * @author Administrator
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;


/**
 * Created by zlf on 2017/1/18.
 */
public class MultiThreadFileRead {

    private static BufferedReader br;

    private final static Semaphore semp = new Semaphore(3);

    public static void main(String[] args) {

        try {
            br = new BufferedReader(new FileReader("E://test_file_change001.txt"), 5);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // 线程池
        ExecutorService exec = Executors.newCachedThreadPool();


        // 模拟 10 个访问
        for (int index = 0; index < 10; index++) {
            final int NO = index;
            Runnable run = new Runnable() {
                public void run() {
                    try {
                        // 获取许可
                        semp.acquire();
                        System.out.println("The " + NO + " number is running ...");
                        //The test code starting ...
                        String line = null;
                        int count = 0;
                        while (true) {
                            List<String> list = new ArrayList<String>();
                            synchronized (br) {
                                try {
                                    while ((line = br.readLine()) != null) {
                                        if (count < 10) {
                                            list.add(line);
                                            count++;
                                        } else {
                                            list.add(line);
                                            count = 0;
                                            break;
                                        }
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            try {
                                System.out.println("the thread " + Thread.currentThread().getName() + " is running ...");
                                display(list);
                                Thread.sleep(3000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            if (line == null)
                                break;
                        }
                        Thread.sleep((long) (Math.random() * 6000));
                        // 访问完后，释放
                        semp.release();
                        //availablePermits()指的是当前信号灯库中有多少个可以被使用
                        System.out.println("------->>>>>>>>>>> The available number of semaphore is :" + semp.availablePermits());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                public void display(List<String> list) {
                    for (String str : list) {
                        System.out.println("-------->>>>>>>>>>>>" + str);
                    }
                    System.out.println("The list size is :__________________>>>>>>>" + list.size());
                }
                //The test code ending ...

            };
            exec.execute(run);
        }
        // 退出线程池
        exec.shutdown();
    }
}
