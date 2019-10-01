package bearmaps.hw4;


import bearmaps.proj2ab.ArrayHeapMinPQ;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex>{
    private double distance;
    double timeUsed;
    HashMap<Vertex, Double> disTo;
    HashMap<Vertex, Vertex> edgeTo;
    ArrayHeapMinPQ<Vertex> pq;
    int state;
    Vertex start;
    Vertex end;
    int num;
    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        this.start = start;
        this.end = end;
        num = 0;
        pq = new ArrayHeapMinPQ<>();
        pq.add(start,0);
        disTo = new HashMap<>();
        edgeTo = new HashMap<>();
        disTo.put(start,0.0);edgeTo.put(start,start);
        state = 0;                    // 0 means solved, 1 means unsolved, 2 means timeout
        double time1 = System.currentTimeMillis();
        while (true) {
            Vertex p = pq.removeSmallest();
            num++;
            if (p.equals(end))
                break;
            for (WeightedEdge<Vertex> edge: input.neighbors(p)) {
                Vertex q = edge.to();double w = edge.weight();
                if (disTo.get(q) == null) {
                disTo.put(q,w+disTo.get(p));
                edgeTo.put(q,p);
                pq.add(q,disTo.get(p)+w+input.estimatedDistanceToGoal(q,end));
                }else {
                    if (disTo.get(p)+w< disTo.get(q)) {
                        disTo.replace(q,disTo.get(p)+w);
                        edgeTo.replace(q,p);
                        if (pq.contains(q))
                            pq.changePriority(q,disTo.get(q)+input.estimatedDistanceToGoal(q,end));
                        else
                            pq.add(q,disTo.get(q) + input.estimatedDistanceToGoal(q,end));
                    }
                }
            }
            if (System.currentTimeMillis() - time1 > timeout*1000){
                timeUsed = 0;
                state = 2;
                break;
            }
            if (pq.size()==0) {
                timeUsed = 1;
                break;
            }
        }
        if (state == 0)
            timeUsed = (System.currentTimeMillis() - time1)/1000;
    }
    @Override
    public SolverOutcome outcome() {
        if (state == 0)
            return SolverOutcome.SOLVED;
        else if (state == 1)
            return SolverOutcome.UNSOLVABLE;
        else
            return SolverOutcome.TIMEOUT;
    }

    @Override
    public List<Vertex> solution() {
        List<Vertex> result = new ArrayList<>();
        if (outcome().equals(SolverOutcome.UNSOLVABLE) || outcome().equals(SolverOutcome.TIMEOUT))
            return result;
//        Vertex start = edgeTo.get()
        result.add(end);
        Vertex get = edgeTo.get(end);
        while (get != start) {
            result.add(get);
            get = edgeTo.get(get);
        }
        result.add(start);
        return result;
    }

    @Override
    public double solutionWeight() {
        return disTo.get(end);
    }

    @Override
    public int numStatesExplored() {
        return num;
    }

    @Override
    public double explorationTime() {
        return this.timeUsed;
    }

    public static void main(String[] args) {
        HashMap<String,Integer> hash = new HashMap<>();
        System.out.println(hash.get("1"));
    }
}
