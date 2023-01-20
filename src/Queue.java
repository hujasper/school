//Matt Jasperson
//mjaspers
//lot of this was taken from project 1
public class Queue {
        Queue head;
        Queue tail;
        Node node;
        Queue link;

        //two different constructors
        //To handle if we creat a queue with data already added to it or not
        public Queue(){}

        public Queue(Node initialNode, Queue initialLink) {
            node = initialNode;
            head = tail = link = initialLink;
        }

        //precondition: Node object needs has been created
        //post-condition: Node is added to the queue

        public void add(Node n){
            //if queue is empty, make a queue with the first node the head and tail
            //if already has a node, then adds a node to the tail
            if(head==null){
                head = tail  = new Queue(n, null);
            }else{
                node = n;
                tail = tail.link = new Queue(n, null);
            }
        }

        //precondition: Queue object has been created
        //post-condition: takes out head node and returns it

        //pulls the head node and makes the next node the head.
        public Node poll(){
            Node returnHead = null;

            //checks if there is a head, if there is,
            //returns the head and makes the link of the head, the new head
            if(head != null) {
                returnHead = head.node;
                head = head.link;
            }
            //if the link is null, then it will empty the queue
            else if(link== null && node!=null ){
               // System.out.println("we here");
                returnHead = node;
                node = null;
            }else {
                return node;
            }
            return returnHead;
        }
        //precondition: Queue object has been created
        //post-condition: returns true or false, depending on if the queue has nodes it it or not

        //checks if there is a head, and if there is, we know it is not empty
        //if there is not, we know it is empty
        public Boolean isEmpty(){
            if (head == null){
                return true;
            }else {
                return false;
            }
        }

    }

