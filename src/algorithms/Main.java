package algorithms;

import java.util.Random;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int n = 50;
        int[] a = new int[n];
        Random rnd = new Random(1);
        for (int i=0;i<n;i++) a[i]=rnd.nextInt(1000);
        MergeSort.sort(a);
        System.out.println(Arrays.toString(a));
    }
}
