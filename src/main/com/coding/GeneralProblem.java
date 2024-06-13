package src.main.com.coding;

public class GeneralProblem {
    public static void main(String[] args) {
        //This approach has an issue because if first element is non zero then it will append all zeroes to start instead of end
        appendZeroesToEnd(new int[]{4, 0, 1, 0, 3, 12, 0});
    }

    private static void appendZeroesToEnd(int[] ints) {
        int zIndex = 0;
        int nzIndex = 0;
        while (zIndex < ints.length && nzIndex < ints.length) {
            if (ints[zIndex] == 0 && zIndex != nzIndex && ints[nzIndex] != 0) {
                int temp = ints[zIndex];
                ints[zIndex] = ints[nzIndex];
                ints[nzIndex] = temp;
                zIndex++;
                nzIndex++;
            } else if (ints[zIndex] != 0) {
                zIndex++;
            } else if (ints[nzIndex] == 0) {
                nzIndex++;
            }
        }
        for (int anInt : ints) {
            System.out.print(anInt + " ");
        }
    }
}
