package chapter6;

import commonClasses.Employee;

/**
 * <pre>
 * Created with IntelliJ IDEA.
 * User: zhengzhi
 * Date: 2017/2/27
 * Time: 14:39
 * To change this template use File | Settings | File Templates.
 * </pre>
 *
 * @author Administrator
 */
public class EmployeeCloneTest {
    public static void main(String[] args) throws Exception {
        Employee original = new Employee();

        Employee cloneOne = original.clone();

        System.out.println(original);
        System.out.println(cloneOne);
    }
}
