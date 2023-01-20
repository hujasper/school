//Matt Jasperson
//Mjaspers
import java.util.Scanner;

public class TwoDTreeDriver {
    //pre-condition: There needs to be a file with crime location data
    //post-condition: User receive outputs that they desired

    public static void main(String[]args){
        //Reading CrimeLatLonXY.csv file and loading into a tree
//        TwoDTree twoDTree = new TwoDTree("test.csv");
        TwoDTree twoDTree = new TwoDTree("CrimeLatLonXY.csv");
        int choiceNumber = 0;
        Scanner in = new Scanner(System.in);
        //UI, each option will perform a different display or query on the tree
        while(choiceNumber!=8) {
            String menu = "1. inorder\n2. preorder\n3. levelorder\n4. postorder\n5. reverseLevelOrder\n6. search for points within rectangle\n7. search for nearest neighbor\n8. quit";
            System.out.println("What would you like to do?");
            System.out.println(menu);
            choiceNumber = Integer.parseInt(in.nextLine());

            switch (choiceNumber) {
                case 1:
                    //Calls the inOrderPrint, pre- and post-conditions for this method are found in TwoDTree.java
                    twoDTree.inOrderPrint(twoDTree.root);
                    System.out.println("");
                    break;
                case 2:
                    //Calls the preOrder Print, pre- and post-conditions for this method are found in TwoDTree.java
                    twoDTree.preOrderPrint(twoDTree.root);
                    System.out.println("");
                    break;
                case 3:
                    //Calls the LevelOrderPrint, pre- and post-conditions for this method are found in TwoDTree.java
                    twoDTree.levelOrderPrint();
                    System.out.println("");
                    break;
                case 4:
                    //Calls the postOrderPrint. pre- and post-conditions for this method are found in TwoDTree.java
                    twoDTree.postOrderPrint(twoDTree.root);
                    System.out.println("");
                    break;
                case 5:
                    //Calls the reverseOrderPrint. pre- and post-conditions for this method are found in TwoDTree.java
                    twoDTree.reverseLevelPrint();
                    System.out.println("");
                    break;
                case 6:
                    //Additional user input is need to get coordinates for the rectangle
                    //user input is split on space and put into the array, which then is put
                    //in for the arguments for method findPointsRange
                    System.out.println("Enter a rectangle bottom left (X1,Y1) and top right (X2, Y2) as\n" +
                            "four doubles each separated by a space.");
                    String cord = in.nextLine();
                    String[] splitCord = cord.split(" ");
                    //calling findsPointInRange, pre- and post-conditions for this method are found in TwoDTree.java
                    ListOfCrimes x = twoDTree.findPointsInRange(Double.parseDouble(splitCord[0]) ,Double.parseDouble(splitCord[1]), Double.parseDouble(splitCord[2]), Double.parseDouble(splitCord[3]));
                    x.toString();
                    System.out.println("");
                    break;
                case 7:
                    //Additional user input is need to get coordinates to perform query
                    //user input is split on space and put into the array, which then is put
                    //in for the arguments for method nearestNeighbor
                    System.out.println("Enter a point to find nearest crime. Separate with a space.");
                    String cords = in.nextLine();
                    String[] splitCords = cords.split(" ");
                    //calling nearestNeighbor, pre- and post-conditions for this method are found in TwoDTree.java

                    twoDTree.nearestNeighbor(Double.parseDouble(splitCords[0]), Double.parseDouble(splitCords[1]));
                    System.out.println("");
                    break;
                case 8:
                    //Closes program
                    System.out.println("Thank you for exploring Pittsburgh crimes in the 1990’s.");
                    break;

            }
        }


    }
}
