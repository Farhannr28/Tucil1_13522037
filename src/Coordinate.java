package src;

//import java.util.SortedSet;
//import java.util.TreeSet;

public class Coordinate implements Comparable<Coordinate> {
    int col;
    int row;

    Coordinate(int c, int r){
        this.col = c;
        this.row = r;
    }

    @Override
    public int compareTo(Coordinate other){
        int result = Integer.compare(this.col, other.col);
        if (result != 0) {
            return result;
        }
        return Integer.compare(this.row, other.row);
    }

    /*
    public static void main(String[] args){
        Coordinate c1 = new Coordinate(0, 0);
        Coordinate c2 = new Coordinate(0, 0);
        Coordinate c3 = new Coordinate(0, 1);
        System.out.println(c1.compareTo(c2));
        SortedSet<Coordinate> s = new TreeSet<Coordinate>();
        System.out.println(s.add(c1));
        System.out.println(s.add(c2));
        c2.row = 1;
        System.out.println(s.add(c2));
        System.out.println(s.first().col + " " + s.first().row);
        System.out.println(s.last().col + " " + s.last().row);
        System.out.println(s.size());
    }
    */
}
