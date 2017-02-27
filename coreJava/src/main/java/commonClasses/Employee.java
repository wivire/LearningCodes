package commonClasses;

import java.util.Date;

/**
 * <pre>
 * Created with IntelliJ IDEA.
 * User: zhengzhi
 * Date: 2017/2/27
 * Time: 10:14
 * To change this template use File | Settings | File Templates.
 * </pre>
 *
 * @author Administrator
 */
public class Employee implements Comparable<Employee>, Cloneable {
    private String name;
    private double salary;
    private Date hiredate;

    public Employee() {
    }

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public double getSalaray() {
        return salary;
    }

    public void raiseSalary(double byPercent) {
        double raise = salary * byPercent / 100;
        salary += raise;
    }

    //compares employee by salary
    @Override
    public int compareTo(Employee other) {
        return Double.compare(salary, other.salary);
    }

    public Employee clone() throws CloneNotSupportedException {
        return (Employee) super.clone();
    }

}
