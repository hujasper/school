//Matt Jasperson
//mjaspers

import java.io.FileWriter;
import java.io.IOException;
//This is a linked-list to retrieve all crimes that are found with the rectangle
//pre-condition: 2D tree has been formed

public class ListOfCrimes {
    // https://www.javatpoint.com/java-program-to-create-and-display-a-singly-linked-list
    // Made the single linked list from this website
    int crime = 0;
    int nodeCount = 0;
    int count = 0;
    double x1, x2,y1,y2;

    class Object{
        //Variables to get the current object and the link
        String data;
        Object next;
        //Constructor to creat an object
        public Object(String data) {
            this.data = data;
            this.next = null;
        }
    }

    //Represent the head and tail of the singly linked list
    public Object head = null;
    public Object tail = null;

    //addNode() will add a new node to the list
    public void add(String data) {
        //Create a new node object
        Object newNode = new Object(data);
        //Checks if the list is empty
        if(head == null) {
            //If list is empty, both head and tail will point to new node
            head = newNode;
            tail = newNode;
        }
        else {
            //newNode will be added after tail such that tail's next will point to newNode
            tail.next = newNode;
            //newNode will become new tail of the list
            tail = newNode;
        }
    }
    //pre-condition: 2D tree has been formed
    //post-condition: Creates KML file with coordinates that within the rectangle.
    public void toKML(){
        Object current = head;
        //Opens up a file name crimeMap.kml and loops through all crimes and writes to the file
        try {
            //This is all the code that needs to go in before the points
            FileWriter myWriter = new FileWriter("crimeMap.kml");
            myWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
            myWriter.write("<kml xmlns=\"http://earth.google.com/kml/2.2\">\n");
            myWriter.write("<Document>\n");
            myWriter.write("<Style id=\"style1\">\n");
            myWriter.write("<IconStyle>\n");
            myWriter.write("<Icon>\n");
            myWriter.write("<href>http://maps.gstatic.com/intl/en_ALL/mapfiles/ms/micons/blue-dot.png</href>\n");
            myWriter.write("</Icon>\n");
            myWriter.write("</IconStyle>\n");
            myWriter.write("</Style>\n");

            //as long as the list has a node, it will keep looping through and creating more placemarks
            while(current!= null){
                String[] inputLine = current.data.split(",");
                myWriter.write("<Placemark>\n");
                myWriter.write("<name>"+inputLine[4]+"</name>\n");
                myWriter.write("<description>"+inputLine[3]+"</description>\n");
                myWriter.write("<styleUrl>#style1</styleUrl>\n");
                myWriter.write("<Point>\n");
                myWriter.write("<coordinates>"+inputLine[8]+","+inputLine[7]+"</coordinates>\n");
                myWriter.write("</Point>\n");
                myWriter.write("</Placemark>\n");
                current = current.next;
            }
            myWriter.write("</Document>\n");
            myWriter.write("</kml>\n");
            myWriter.close();
            //Reports how many nodes were examined and how many crimes were found
            System.out.println("Examined "+nodeCount+" nodes during search. \nFound " + crime + " crimes");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
    //pre-condition: 2D tree has been formed
    //post-condition: Prints out list of crimes found from the search
    public String toString(){
        Object current = head;
        String cases = "";
        while(current!=null){
            System.out.println(current.data);
            cases = cases + current.data+"\n";
            current = current.next;
        }
        System.out.println("The crime data has been written to CrimeLatLonXY.KML. It is viewable\n" + "in Google Earth Pro.");
        return cases;

    }
    //pre-condition: 2D tree has been formed
    //post-condition: Returns a list of crimes found within the rectangle
    //Code from website above was used
    public void recursiveSearch(double x1, double y1, double x2,double y2, Node n){
        if (n == null) return;

        //https://www.geeksforgeeks.org/check-if-a-point-lies-on-or-inside-a-rectangle-set-2
        //Used this code to find if points fall within rectangle
        //Logic is an if statement, that compares current node with the coordinates
        //if the current node falls in there, it will be added to the list
        if (n.x > x1 && n.x < x2 && n.y > y1 && n.y < y2){
            add(n.data);
            crime++;
        }

                    recursiveSearch(x1, y1, x2, y2, n.left);
                    recursiveSearch(x1, y1, x2, y2, n.right);
    }


}
