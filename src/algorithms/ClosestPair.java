package algorithms;

import java.util.Arrays;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.List;

public class ClosestPair {
    public static class P { public final double x,y; public P(double x,double y){ this.x=x; this.y=y; } }
    public static double closest(P[] pts, Metrics m){
        if(pts==null || pts.length<2) return Double.POSITIVE_INFINITY;
        P[] byX = pts.clone();
        Arrays.sort(byX, Comparator.comparingDouble(p->p.x));
        P[] byY = pts.clone();
        Arrays.sort(byY, Comparator.comparingDouble(p->p.y));
        return rec(byX, byY, 0, byX.length, m);
    }
    private static double dist(P a,P b){ double dx=a.x-b.x, dy=a.y-b.y; return Math.hypot(dx,dy); }
    private static double rec(P[] byX, P[] byY, int l, int r, Metrics m){
        int n = r-l;
        if(n<=3){
            double best = Double.POSITIVE_INFINITY;
            for(int i=l;i<r;i++) for(int j=i+1;j<r;j++) best=Math.min(best, dist(byX[i], byX[j]));
            return best;
        }
        int mid = (l+r)>>>1;
        double mx = byX[mid].x;
        P[] leftX = Arrays.copyOfRange(byX,l,mid);
        P[] rightX = Arrays.copyOfRange(byX,mid,r);
        List<P> leftY=new ArrayList<>(), rightY=new ArrayList<>();
        for(P p: byY){ if(p.x<=mx) leftY.add(p); else rightY.add(p); }
        double dl = rec(leftX, leftY.toArray(new P[0]), 0, leftX.length, m);
        double dr = rec(rightX, rightY.toArray(new P[0]), 0, rightX.length, m);
        double d = Math.min(dl, dr);
        List<P> strip=new ArrayList<>();
        for(P p: byY) if(Math.abs(p.x-mx) < d) strip.add(p);
        for(int i=0;i<strip.size();i++){
            for(int j=i+1;j<strip.size() && (strip.get(j).y - strip.get(i).y) < d; j++){
                d = Math.min(d, dist(strip.get(i), strip.get(j)));
            }
        }
        return d;
    }
}
