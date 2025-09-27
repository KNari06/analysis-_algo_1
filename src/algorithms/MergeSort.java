package algorithms;

public class MergeSort {
    private static final int CUTOFF = 16;
    public static void sort(int[] a, Metrics m){
        if(a==null || a.length<2) return;
        int[] buf = new int[a.length];
        sort(a, buf, 0, a.length-1, m);
    }
    private static void sort(int[] a, int[] buf, int lo, int hi, Metrics m){
        if(hi-lo<=CUTOFF){ insertionSort(a, lo, hi, m); return; }
        m.enter();
        int mid = (lo+hi)>>>1;
        sort(a, buf, lo, mid, m);
        sort(a, buf, mid+1, hi, m);
        merge(a, buf, lo, mid, hi, m);
        m.exit();
    }
    private static void merge(int[] a, int[] buf, int lo, int mid, int hi, Metrics m){
        System.arraycopy(a, lo, buf, lo, hi-lo+1);
        int i=lo, j=mid+1;
        for(int k=lo;k<=hi;k++){
            if(i>mid){ a[k]=buf[j++]; m.allocations++; }
            else if(j>hi){ a[k]=buf[i++]; m.allocations++; }
            else { m.comparisons++; if(buf[j]<buf[i]) a[k]=buf[j++]; else a[k]=buf[i++]; m.allocations++; }
        }
    }
    private static void insertionSort(int[] a, int lo, int hi, Metrics m){
        for(int i=lo+1;i<=hi;i++){
            int key=a[i], j=i-1;
            while(j>=lo && (m.comparisons++>=0 && a[j]>key)){ a[j+1]=a[j]; j--; m.swaps++; }
            a[j+1]=key;
            m.allocations++;
        }
    }
}
