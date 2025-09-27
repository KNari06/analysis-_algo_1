package algorithms;

public class MergeSort {
    private static final int CUTOFF = 16;
    public static void sort(int[] a){
        if(a==null || a.length<2) return;
        int[] buf = new int[a.length];
        sort(a, buf, 0, a.length-1);
    }
    private static void sort(int[] a, int[] buf, int lo, int hi){
        if(hi-lo<=CUTOFF){ insertionSort(a, lo, hi); return; }
        int mid = (lo+hi)>>>1;
        sort(a, buf, lo, mid);
        sort(a, buf, mid+1, hi);
        merge(a, buf, lo, mid, hi);
    }
    private static void merge(int[] a, int[] buf, int lo, int mid, int hi){
        System.arraycopy(a, lo, buf, lo, hi-lo+1);
        int i=lo, j=mid+1;
        for(int k=lo;k<=hi;k++){
            if(i>mid) a[k]=buf[j++];
            else if(j>hi) a[k]=buf[i++];
            else if(buf[j]<buf[i]) a[k]=buf[j++];
            else a[k]=buf[i++];
        }
    }
    private static void insertionSort(int[] a, int lo, int hi){
        for(int i=lo+1;i<=hi;i++){
            int key=a[i], j=i-1;
            while(j>=lo && a[j]>key){ a[j+1]=a[j]; j--; }
            a[j+1]=key;
        }
    }
}
