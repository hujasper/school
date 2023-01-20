//Matt Jasperson
//mjaspers
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;



public class TwoDTree {
    public double x;
    public double y;
    Node root;
    static final int COUNT = 5;
    Boolean compareX = true;
    public int count = 0;

    //pre-condition: The String crimeDataLocation contains the path to
    //        a file formatted in the exact same way as CrimeLatLonXY.csv
    //post-condition: The 2d tree is constructed and may be printed or
    //        queried.

    //Reading the file into a 2D Tree
    public TwoDTree(String crimeDataLocation){
        //Takes file and puts each line into a string
        File file = new File(crimeDataLocation);
        String data;
        String[] inputLine;
        boolean firstLine = true;
        try {
            Scanner reader = new Scanner(file);
            while(reader.hasNextLine()){
                //This if statement makes sure we don't read the heading of the csv file
                if(firstLine==false) {
                    data = reader.nextLine();
                    //Splitting each line on the comma, taking the first two values which are x and y
                    inputLine = data.split(",");
                    x = Double.parseDouble(inputLine[0]);
                    y = Double.parseDouble(inputLine[1]);
                    //This adds the coordinates into the tree, with the rest of the data from that line
                    add(x, y,data);
                }else {
                    data = reader.nextLine();
                    firstLine = false;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //prints the tree
       // print2D(root);
    }

    //Pre-condition: add method has been called
    //Post-Condition: Node is added to the tree
    //https://www.baeldung.com/java-binary-tree
    private Node addRecursive(Node current, double x,double y,String data) {
        //we reach this statement when there is no leaf at that spot
        //when it reaches null, it knows to place a node there
        if (current == null) {
            return new Node(x, y,data);
        }
        //If statement, is to make sure we switch between comparing the x coordinate and the y coordinate
        //from level to level
        //The counter counts each a coordinate goes down a level.
        //If the counter is odd, then we know it should be compared on Y
        //if its even, we know to compare it on the x coordinate.

        //Checking the count if its even
        if ( count % 2 == 0 || count == 0) {
            //Compares node coordinates to be added with existing nodes
            //if x coordinate to be addes is less, it will move to the left
            //if its >or = then we move to the right.
            //We then use recursion to keep calling until we find an empty node.
            if (x < current.x) {
                count++;
                current.left = addRecursive(current.left, x, y,data);
            } else if (x >= current.x) {
                count++;
                current.right = addRecursive(current.right, x, y,data);
            } else {
                // value already exists
                return current;
            }
        }
        //All odds, will be compared on y
        //Same logic with the y coordinate as for the x above
        else {
            if (y < current.y) {
                count++;
                current.left = addRecursive(current.left, x, y,data);
            } else if (y >= current.y) {
                count++;
                current.right = addRecursive(current.right, x, y,data);
            } else {
                // value already exists
                return current;
            }
        }
        count=0;
        return current;
    }
    //Pre-condition: Data been put into a node object, and is ready to be inserted
    //Post-Condition: Node is added to the tree

    //using recursion, this method will find where to put each set of coordinates.
    public void add(double x,double y, String data) {
        root = addRecursive(root,x,y,data);
    }

//    pre-condition: The 2d tree has been constructed.
//    post-condition: The 2d tree is displayed with a pre-order
//    traversal. Note: an example pre-order traversal appears on the
//    course slides and will be discussed in class.
    //https://www.geeksforgeeks.org/tree-traversals-inorder-preorder-and-postorder/
    void preOrderPrint(Node node)
    {
        if (node == null) {
            return;
        }
        /* first print data of node */
        System.out.print("("+node.x + "," + node.y+")");
        /* then recur on left subtree */
        preOrderPrint(node.left);
        /* now recur on right subtree */
        preOrderPrint(node.right);
    }
//    pre-condition: The 2d tree has been constructed.
//    post-condition: The 2d tree is displayed with an in-order

//    traversal. Note: an example in-order traversal appears on the
//    course slides and will be discussed in class.
//     Runtime analysis: O(N)
    //https://www.geeksforgeeks.org/tree-traversals-inorder-preorder-and-postorder/
    public void inOrderPrint(Node node){
        if (node == null) {
            return;
        }
        /* then recur on left subtree */
        inOrderPrint(node.left);
        /* first print data of node */
        System.out.print("("+node.x + "," + node.y+")");
        /* now recur on right subtree */
        inOrderPrint(node.right);

    }
//    pre-condition: The 2d tree has been constructed.
//    post-condition: The 2d tree is displayed with a post-order
//    traversal. Note: an example post-order traversal appears on the
//    course slides and will be discussed in class.
    //https://www.geeksforgeeks.org/tree-traversals-inorder-preorder-and-postorder/
    public void postOrderPrint(Node node){
        if (node == null) {
            return;
        }
        /* then recur on left subtree */
        postOrderPrint(node.left);
        /* now recur on right subtree */
        postOrderPrint(node.right);
        /* first print data of node */
        System.out.print("("+node.x + "," + node.y+")");

    }
//    pre-condition: The 2d tree has been constructed.
//    post-condition: The 2d tree is displayed with a level-order
//    traversal. Note: the level order traversal is not recursive. It
//    uses a queue that you must write. This queue is defined in a
//    separate file named Queue.java. Queue.java is built with a linked
//    list based queue – using front and rear pointers. You will write

    //https://www.geeksforgeeks.org/level-order-tree-traversal/
    //taken from this website, modified a little to work with this
    //Runtime analysis: O(N)
    public void levelOrderPrint(){
        //Makes a queue object and add the root queue as the first node
        Queue queue = new Queue();
        queue.add(root);
        //loops until the queue is empty
        while (!queue.isEmpty()) {
            /* poll() removes the present head. */
            Node tempNode = queue.poll();
            //prints out all nodes on that level
            System.out.print("("+tempNode.x+","+tempNode.y+")");


            //these next two if statements, check for all nodes on the next level and adds
            //to the queue
            /*Then checks if node has anything to the left of it and adds to queue*/
            if (tempNode.left != null) {
                queue.add(tempNode.left);
            }
            /*checks if node has anything to the right of it and if it does, adds */
            if (tempNode.right != null) {
                queue.add(tempNode.right);
            }
        }
    }

//    pre-condition: The 2d tree has been constructed.
//    post-condition: The 2d tree is displayed with a reverse levelorder traversal.

    //https://www.geeksforgeeks.org/reverse-level-order-traversal/
    //The logic for the reverse is to do the same thing as levelprint but instead of printing the values,
    //put them in a stack and then print out the stack, which would make it in reverse order.
    public void reverseLevelPrint() {
        //Makes a queue object and add the root queue as the first node
        Queue queue = new Queue();
        queue.add(root);
        Stack s = new Stack();
        //loops until the queue is empty
        while (!queue.isEmpty()) {
            /* poll() removes the present head. */
            Node tempNode = queue.poll();
            //prints out all nodes on that level
            s.push(tempNode);

            //these next two if statements, check for all nodes on the next level and adds
            //to the queue
            /*Then checks if node has anything to the left of it and adds to queue*/
            if (tempNode.left != null) {
                queue.add(tempNode.left);
            }
            /*checks if node has anything to the right of it and if it does, adds */
            if (tempNode.right != null) {
                queue.add(tempNode.right);
            }
        }
        //Here is the stack, we will just unload the stack until empty
        Node tempNode;
        while (s.isEmpty() == false)
        {
            tempNode = s.pop();
            System.out.print("("+tempNode.x+","+tempNode.y+")" + " ");

        }
    }
//    pre-condition: The 2d tree has been constructed.
//    post-condition: A list of 0 or more crimes is returned. These
//    crimes occurred within the rectangular range specified by the
//    four parameters. The pair (x1, y1) is the left bottom of the
//    rectangle. The pair (x2, y2) is the top right of the rectangle.

    public ListOfCrimes findPointsInRange(double x1, double y1, double x2,double y2){
        //Creates ListOfCrimes of object
        ListOfCrimes listOfCrimes = new ListOfCrimes();
        //Call recursiveSearch
        listOfCrimes.recursiveSearch(x1,y1,x2,y2,root);
        //Prints to KML
        listOfCrimes.toKML();
        return listOfCrimes;
    }
//    pre-condition: the 2d tree has been constructed.
//    The (x1,y1) pair represents a point in space near Pittsburgh and
//    in the state plane coordinate system.
//    post-condition: the distance in feet to the nearest node is
//    returned in Neighbor. In addition, the Neighbor object contains a
//    reference to the nearest neighbor in the tree.
    public Neighbor nearestNeighbor(double x1, double y1){
        //Creates a neighbor Object
        Neighbor n = new Neighbor();
        //Calls findNear
        n.findNear(x1,y1, root);
        System.out.println("Looked at 27 nodes in tree. Found the nearest crime at:\n" + n.near);
        return n;
    }

}
