package algorithms;

import java.io.FileWriter;
import java.util.Random;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        String mode = args.length>0?args[0]:"demo";
        if("demo".equals(mode)) demo();
        else if("bench".equals(mode)) bench();
        else usage();
    }
    private static void usage(){ System.out.println("Usage: demo | bench"); }
    private static void demo(){
        Metrics m = new Metrics();
        int[] a = new int[50]; Random rnd = new Random(1);
        for(int i=0;i<a.length;i++) a[i]=rnd.nextInt(1000);
        m.reset(); MergeSort.sort(a,m); System.out.println("mergesort ok, metrics:"+m.toCsv());
        for(int i=0;i<a.length;i++) a[i]=rnd.nextInt(1000);
        m.reset(); QuickSort.sort(a,m); System.out.println("quicksort ok, metrics:"+m.toCsv());
        int[] b = new int[101]; for(int i=0;i<b.length;i++) b[i]=rnd.nextInt(10000);
        int[] bc = b.clone(); Arrays.sort(bc);
        m.reset(); int got = DeterministicSelect.select(b, b.length/2, m); System.out.println("select expect="+bc[b.length/2]+" got="+got+" metrics:"+m.toCsv());
        ClosestPair.P[] pts = new ClosestPair.P[100]; for(int i=0;i<pts.length;i++) pts[i]=new ClosestPair.P(rnd.nextDouble()*1000, rnd.nextDouble()*1000);
        m.reset(); double d = ClosestPair.closest(pts,m); System.out.println("closest d="+d+" metrics:"+m.toCsv());
    }
    private static void bench() throws Exception {
        int[] sizes = new int[]{1000,2000,5000,10000};
        try(FileWriter fw = new FileWriter("bench_results.csv")) {
            fw.write("algo,n,time_us,comparisons,swaps,allocations,maxDepth\n");
            Random rnd = new Random(1);
            for(int n: sizes){
                Metrics m = new Metrics();
                int[] a = new int[n];
                for(int i=0;i<n;i++) a[i]=rnd.nextInt(n*3+1);
                long t1 = System.nanoTime();
                MergeSort.sort(a,m);
                long t2 = System.nanoTime();
                fw.write("mergesort,"+n+"," + ((t2-t1)/1000) + "," + m.toCsv()+"\n");
                for(int i=0;i<n;i++) a[i]=rnd.nextInt(n*3+1);
                m.reset(); t1 = System.nanoTime(); QuickSort.sort(a,m); t2 = System.nanoTime();
                fw.write("quicksort,"+n+"," + ((t2-t1)/1000) + "," + m.toCsv()+"\n");
                int[] b = new int[n]; for(int i=0;i<n;i++) b[i]=rnd.nextInt(n*3+1);
                m.reset(); t1 = System.nanoTime(); DeterministicSelect.select(b,n/2,m); t2 = System.nanoTime();
                fw.write("select,"+n+"," + ((t2-t1)/1000) + "," + m.toCsv()+"\n");
                ClosestPair.P[] pts = new ClosestPair.P[n]; for(int i=0;i<n;i++) pts[i]=new ClosestPair.P(rnd.nextDouble()*n, rnd.nextDouble()*n);
                m.reset(); t1 = System.nanoTime(); ClosestPair.closest(pts,m); t2 = System.nanoTime();
                fw.write("closest,"+n+"," + ((t2-t1)/1000) + "," + m.toCsv()+"\n");
                fw.flush();
            }
        }
        System.out.println("bench_results.csv written");
    }
}
