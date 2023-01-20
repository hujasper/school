//Matt Jasperson
//mjaspers
public class Neighbor {
    public String near;
    Double distance = 1000000000000000000000.0;
    //pre-condition: Tree has been formed
    //post-condition: returns the nearest point
    public void findNear(double x1, double y1, Node n){
        if (n == null) return;
        Double x2 = n.x;
        Double y2 = n.y;

        //Calculates the distance between two points
        Double d = Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));

        //if the distance is smaller, it becomes the newest closest point
        if (d< distance){
            distance = d;
            near = (n.data);
        }

        findNear(x1,y1,n.left);

        findNear(x1,y1,n.right);

    }
}
