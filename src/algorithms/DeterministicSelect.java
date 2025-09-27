package algorithms;

public class DeterministicSelect {
    public static int select(int[] a, int k, Metrics m){
        if(k<0 || k>=a.length) throw new IllegalArgumentException();
        return selectRange(a,0,a.length-1,k,m);
    }
    private static int selectRange(int[] a,int lo,int hi,int k,Metrics m){
        while(true){
            if(lo==hi) return a[lo];
            int pivot = medianOfMedians(a,lo,hi,m);
            int p = partition(a,lo,hi,pivot,m);
            int left = p - lo;
            if(k==left) return a[p];
            else if(k < left) hi = p-1;
            else { k = k - left - 1; lo = p+1; }
        }
    }
    private static int partition(int[] a,int lo,int hi,int pivot,Metrics m){
        int pi=lo;
        for(int i=lo;i<=hi;i++){ m.comparisons++; if(a[i]==pivot){ pi=i; break; } }
        swap(a,pi,hi,m);
        int store=lo;
        for(int i=lo;i<hi;i++){ m.comparisons++; if(a[i]<pivot){ swap(a,i,store,m); store++; } }
        swap(a,store,hi,m);
        return store;
    }
    private static int medianOfMedians(int[] a,int lo,int hi,Metrics m){
        int n=hi-lo+1;
        int groups=(n+4)/5;
        for(int i=0;i<groups;i++){
            int s=lo+i*5;
            int e=Math.min(s+4,hi);
            insertion(a,s,e,m);
            int mid=(s+e)>>>1;
            swap(a, lo+i, mid, m);
        }
        int mid = lo + (groups-1)/2;
        if(groups==1) return a[lo];
        return selectRange(a, lo, lo+groups-1, mid-lo, m);
    }
    private static void insertion(int[] a,int l,int r,Metrics m){
        for(int i=l+1;i<=r;i++){
            int key=a[i], j=i-1;
            while(j>=l && (m.comparisons++>=0 && a[j]>key)){ a[j+1]=a[j]; j--; m.swaps++; }
            a[j+1]=key; m.allocations++;
        }
    }
    private static void swap(int[] a,int i,int j, Metrics m){ if(i==j) return; int t=a[i]; a[i]=a[j]; a[j]=t; m.swaps++; }
}
