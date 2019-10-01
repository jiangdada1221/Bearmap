package bearmaps.proj2ab;

import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;
import java.util.List;

/* modified for this project */
public class KDTree implements PointSet {
    private Tree tree;

    private class Tree {
        private Node node;
        private Tree L;
        private Tree R;
        private int size;



        public Tree() {
            node = null;
            L = null;
            R = null;
        }

        public Tree(Node n) {
            L = null;
            R = null;
            node = n;
            size = 0;
        }


        public void add(Node n) {
            if (size == 0) {
                node = n;
                size++;
                return;
            }
            helper(this, n);
        }

        private Tree helper(Tree t, Node n) {

            if (t == null) {
                size++;
                t = new Tree(n);
                return t;
            } else {
                int diff = n.compareTo(t.node);
                if (diff > 0) {
                    n.changeSta();
                    t.R = helper(t.R, n);
                    return t;
                } else if (diff < 0) {
                    n.changeSta();
                    t.L = helper(t.L, n);
                    return t;
                }
                return t;
            }


        }
    }
    private class Node implements Comparable<Node> {
        public Point point;
        private boolean LF;  // if true then, compare X coordinate

        public Node(Point p) {
            this.point = p;
            LF = true;
        }

        public void changeSta() {
            if (LF == true)
                LF = false;
            else
                LF = true;
        }

        @Override
        public int compareTo(Node o) {
            if (LF) {
                double diff = this.point.getX() - o.point.getX();
                if (diff == 0)
                    return 0;
                else
                    return (int) (Math.abs(diff) / diff);
            } else {
                double diff = this.point.getY() - o.point.getY();
                if (diff == 0)
                    return 0;
                else
                    return (int) (Math.abs(diff) / diff);
            }
        }

        @Override
        public boolean equals(Object o) {
            if (o.getClass() != this.getClass())
                return false;
            else {
                Node no = (Node) o;
                return no.point.equals(this.point);
            }
        }
    }

    public KDTree(List<Point> points) {
        Tree tree = new Tree();
        /* make them random (bushy) */
//        int length = points.size();
//        int[] refer = new int[length];
//        for (int i = 0; i <= length-1;i++)
//            refer[i] = i;
//        StdRandom.shuffle(refer);
//        for (int i = 0; i < length; i ++) {
//            Point p = points.get(refer[i]);
//            Node input = new Node(p);
//            tree.add(input);
//        }
        for (Point p : points)
            tree.add(new Node(p));
        this.tree = tree;
    }

    @Override
    public Point nearest(double x, double y) {
        Point goal = new Point(x,y);
        return helper(goal,tree,tree.node.point);
    }
    private Point helper(Point goal, Tree tree, Point Best){
        if (tree == null){
            return Best;
        }
        if (Point.distance(tree.node.point,goal) < Point.distance(Best,goal))
            Best = tree.node.point;
        Best = helper(goal,GoodChild(goal,tree),Best);
//        if (WorthToT(goal,Point.distance(goal,Best),BadChild(goal,tree)))
            Best = helper(goal,BadChild(goal,tree),Best);

        return Best;


    }
    private Tree GoodChild(Point goal,Tree t) {
        Node refer = t.node;
        if (refer.LF) {
            double diff = refer.point.getX() - goal.getX();
            if (diff >= 0)
                return t.L;
            else
                return t.R;
        }
        else {
            double diff = refer.point.getY() - goal.getY();
            if (diff >= 0)
                return t.L;
            else
                return t.R;
        }
    }
    private Tree BadChild(Point goal,Tree t) {
        if (t.L == GoodChild(goal,t))
            return t.R;
        else
            return t.L;

    }
    private boolean WorthToT(Point goal,double best,Tree t) {
        if (t==null)
            return false;
        if (t.node.LF) {
            if (Math.abs(goal.getX() - t.node.point.getX()) >= best)
                return false;
            else
                return true;
        }
        else {
            if (Math.abs(goal.getY() - t.node.point.getY()) >= best)
                return false;
            else
                return true;
        }

    }

    public static void main(String[] args) {
        Point p1 = new Point(1.1,2.2);
        Point p2 = new Point(3.3,4.4);
        Point p3 = new Point(-2.9,4.2);
        Point p4 = new Point(3.0,4.1);
        Point p5 = new Point(3.1,3.9);
        Point p6 = new Point(2.7,3.3);
        Point p7 = new Point(2.8,3.8);
        List<Point> points = new ArrayList<>();
        points.add(p1);
        points.add(p2);
        points.add(p3);
        points.add(p4);
        points.add(p5);
        points.add(p6);
        points.add(p7);
//        for (int i = 0;i <= 50;i ++)
//            points.add(new Point(StdRandom.uniform(0.0,6.0),StdRandom.uniform(0.0,6.0)));
        KDTree kd = new KDTree(points);
        double time1 = System.currentTimeMillis();
        Point ret = kd.nearest(3.0,4.0);
        double time2 = System.currentTimeMillis();
        System.out.println("the time use is " + (time2 - time1));  // it is really super fast!
        System.out.println("x coordinate is " + ret.getX());
        System.out.println("y coordinate is " + ret.getY());
    }
}
