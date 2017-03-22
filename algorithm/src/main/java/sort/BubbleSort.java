package sort;

/**
 * <pre>
 * Created with IntelliJ IDEA.
 * User: zhengzhi
 * Date: 2017/3/22
 * To change this template use File | Settings | File Templates.
 * </pre>
 */
public class BubbleSort {

    /**
     * 冒泡排序 Java实现
     * 冒泡排序的思想是：假设数据是存放在数组L中，初始时，有序区为空，无序区为L[0]~L[n-1]
     * 在无序区中，每次均从头至尾依次比较两个相邻的数据元素L[i]与L[i+1]，若存在逆序，则
     * 交换两个元素的位置，每执行这个过程称为一趟冒泡排序；
     * <p/>
     * 冒泡排序是稳定的排序方法，其平均时间复杂度为O(n^2)
     */

    public static void bubbleSort1(int[] arr1, int n) {
        int tmp = 0;
        /**
         * 外层for循环表示没一趟排序所要比较的区间范围
         * 第一趟无序数列区间为：0~n-1;第一趟之后，最大的数被选出来，放在最后一个位置上；
         * 第二趟无序数列区间为：0~n-2；
         */
        for (int j = n - 1; j > 0; j--) {
            for (int i = 0; i < j; i++) {
                if (arr1[i] > arr1[i + 1]) {
                    tmp = arr1[i];
                    arr1[i] = arr1[i + 1];
                    arr1[i + 1] = tmp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {98, 25, 70, 36, 13, 85};

        bubbleSort1(arr, 6);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}
