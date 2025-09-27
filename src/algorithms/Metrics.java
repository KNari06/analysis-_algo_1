package algorithms;

public class Metrics {
    public long comparisons;
    public long swaps;
    public long allocations;
    public int maxDepth;
    private int currentDepth;
    public void enter(){ currentDepth++; if(currentDepth>maxDepth) maxDepth=currentDepth; }
    public void exit(){ currentDepth--; }
    public void reset(){ comparisons=0; swaps=0; allocations=0; currentDepth=0; maxDepth=0; }
    public String toCsv(){ return comparisons + "," + swaps + "," + allocations + "," + maxDepth; }
}
