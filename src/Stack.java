//Matt Jasperson
//mjaspers

//https://levelup.gitconnected.com/selfmade-stack-class-in-java-d401dc7d68f0
//This is all taken from the website above
public class Stack {
    public Stack pointer;
    Node data;
    Stack previous;

    public Stack(){}

    public Stack(Node n){
        this.data = n;
    }
    public Stack(Stack pre, Node node){
        this.previous =pre;
        this.data = node;
    }
    //pre-condition: Node needs to be constructed
    //post-condition: node is put into the stack
    //Takes node and puts on top of the stack
    //By a new stack putting the other ones on the previous
    public void push  (Node node){
        this.previous = new Stack(this.previous,this.data);
        this.data = node;

    }

    //precondition: Stack needs have nodes
    //post-condition: returns the latest node added
    //Get the latest node added and takes it out of the list
    public Node pop(){
        //checks if empty, if empty through an error
        if(this.isEmpty()){
            throw new IllegalArgumentException("Stack is empty");
        }
        //puts the previous node at the head now
        Node n = this.data;
        this.data = this.previous.data;
        this.previous = this.previous.previous;
        //returning node that was most recently added
        return n;
    }
    //pre-condition: A stack object was created
    //post-condition: returns true or false depending on if it contains nodes or not
    //checks if it is empty or not, if it is, returns null
    public boolean isEmpty(){
        return this.previous == null;
    }


}
