
import java.util.Scanner;

public class Test {
    public static Scanner scanner = new Scanner(System.in);

    /**
     * 返回ki向量和ji向量的叉乘结果
     *
     * @param i i点坐标
     * @param j b点坐标
     * @param k k点坐标
     * @return int
     */
    private static int direction(int[] i, int[] j, int[] k) {
        return (i[0] - k[0]) * (i[1] - j[1]) - (i[0] - j[0]) * (i[1] - k[1]);
    }

    /**
     * 判断线段p1,p2是否和p3,p4相交
     *
     * @param p1 p1点的坐标
     * @param p2 p2点的坐标
     * @param p3 p3点的坐标
     * @param p4 p4点的坐标
     * @return boolean
     */
    private static boolean judge(int[] p1, int[] p2, int[] p3, int[] p4) {
        int d1 = direction(p3, p4, p1);
        int d2 = direction(p3, p4, p2);
        int d3 = direction(p1, p2, p3);
        int d4 = direction(p1, p2, p4);
        if (((d1 > 0 && d2 < 0) || (d1 < 0 && d2 > 0)) &&
                ((d3 > 0 && d4 < 0) || (d3 < 0 && d4 > 0))) {
            return true;
        } else return d1 == 0 || d2 == 0 || d3 == 0 || d4 == 0;
    }

    public static void main(String[] args) {
        int n = scanner.nextInt();//线段的数量
        int[] xArr = new int[n];//在x轴选取的点的所有横坐标
        int[] yArr = new int[n];//在y轴选取的点的所有纵坐标
        for (int i = 0; i < n; i++) {
            xArr[i] = scanner.nextInt();
        }
        for (int i = 0; i < n; i++) {
            yArr[i] = scanner.nextInt();
        }
        /*System.out.println(Arrays.toString(xArr));
        System.out.println(Arrays.toString(yArr));*/
        int m = scanner.nextInt();//表示询问数量
        int[][] questPoints = new int[m][2];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < 2; j++) {
                questPoints[i][j] = scanner.nextInt();
            }
        }
        int sum = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                boolean bingo = judge(new int[]{0, 0}, new int[]{questPoints[i][0], questPoints[i][1]}, new int[]{xArr[j], 0}, new int[]{0, yArr[j]});
                if (bingo) sum++;
            }
            System.out.println(sum);
        }

    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

class Main{
    public static ListNode reverseList(){
        return new ListNode();
    }
    public static void main(String[] args) {

    }
}