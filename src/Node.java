//Matt Jasperson
//mjaspers
//https://www.baeldung.com/java-binary-tree
class Node {
    double x;
    double y;
    String data;
    Node left;
    Node right;
    //This is just created to help store coordinates and other data in the tree and lists
    Node(double x, double y,String data) {
        this.x = x;
        this.y = y;
        this.data = data;
        right = null;
        left = null;
    }
}
