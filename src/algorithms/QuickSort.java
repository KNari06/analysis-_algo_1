package algorithms;

import java.util.concurrent.ThreadLocalRandom;

public class QuickSort {
    public static void sort(int[] a, Metrics m){
        if(a==null || a.length<2) return;
        quicksort(a,0,a.length-1,m);
    }
    private static void quicksort(int[] a, int lo, int hi, Metrics m){
        int L=lo, H=hi;
        while(L < H){
            m.enter();
            int pivotIndex = ThreadLocalRandom.current().nextInt(L, H+1);
            int pivot = a[pivotIndex];
            swap(a,pivotIndex,H,m);
            int store=L;
            for(int i=L;i<H;i++){ m.comparisons++; if(a[i]<pivot){ swap(a,i,store,m); store++; } }
            swap(a,store,H,m);
            int leftSize = store - L;
            int rightSize = H - store;
            if(leftSize < rightSize){ quicksort(a, L, store-1, m); L = store+1; } else { quicksort(a, store+1, H, m); H = store-1; }
            m.exit();
        }
    }
    private static void swap(int[] a,int i,int j, Metrics m){ if(i==j) return; int t=a[i]; a[i]=a[j]; a[j]=t; m.swaps++; }
}
