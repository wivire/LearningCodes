package chapter6;

import commonClasses.Employee;

import java.util.Arrays;

/**
 * <pre>
 * Created with IntelliJ IDEA.
 * User: zhengzhi
 * Date: 2017/2/27
 * Time: 10:12
 * To change this template use File | Settings | File Templates.
 * </pre>
 *
 * @author Administrator
 */
public class EmployeeSortTest {
    public static void main(String[] args) {
        Employee[] staff = new Employee[3];

        staff[0] = new Employee("harry hacker", 35000);
        staff[1] = new Employee("carl Cracker", 75000);
        staff[2] = new Employee("Tony Tester", 38000);

        Arrays.sort(staff); //Arrays中的sort算法使用归并算法进行排序；

        //print out information about all Employee objects
        for (Employee e : staff) {
            System.out.println("name=" + e.getName() + ",salary=" + e.getSalaray());
        }
    }
}
