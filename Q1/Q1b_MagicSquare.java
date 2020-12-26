import java.util.Random;
import java.util.Scanner;
// 
//  Joseph Wilson-Hall
// 
//  C1949969
// 
class Q1b_MagicSquare
{
    private static int nn;
    private static int x1;
    private static int y1;
    private static int x2;
    private static int y2;
    private static int[] axis;
    private static int rDirection;
    private static int squareCal = 0;
    private static int[][] printMagicSquare = new int[nn][nn];
    private static boolean isSquareMagic = false;
    public static void main(String args[])
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Input an ODD integer that is 3 or more");
        String nToUse ="";
        int n = 0;
        while (stringTest(nToUse = in.next())==false || Integer.parseInt(nToUse)==0 || Integer.parseInt(nToUse)%2==0 || Integer.parseInt(nToUse) < 3) {
            System.out.println("Input an ODD integer that is 3 or more");
        }
        //the while loop will ensure that what the user inputted can be used by the program
        n = Integer.parseInt(nToUse); // converts the string input into an int. it had to be a string first to use the stringTest method to make sure it is an integer that was inputted 
        genMagSquare(n);
        gameLoop();
        in.close();
    }
    
    private static void genMagSquare(int n)
    {
        int[][]magiSquare = new int[n][n];
        nn = n;
        x1=0;
        y1 = nn/2;  
        squareCal = nn*(nn*nn+1)/2;
        
        for(int x = 0; x < n; x++){
            for(int y = 0; y<n; y++){
                magiSquare[x][y] = 0;
                //populating the magic square with 0's
            }
        }
        //creates the magic square so all columns rows and diagonals are equal to n(n^2+1)/2
        for (int num = 1;num <= n * n; num++) {
            magiSquare[x1][y1]= num;
            x2 = x1;
            y2 = y1;
            x1 -= 1;
            y1 += 1;
            if (x1 == -1){
                x1 = n-1;
            }
            if( y1 == n){
                y1=0;
            }
            if (magiSquare[x1][y1] !=0){
                x1 = x2 +1;
                y1 = y2;
                if (x1 == -1) {
                    x1 = n -1;
                }
            }
        }
        printMagicSquare = magiSquare; //making the magic square accessible outside this method to allow it to be manipulated
    }
    
    private static void shuffleSquare() // method randomly selects coordinates and swaps it with a random direction 1-4 and does this n^2 times
    {
        Random rand = new Random();
        int shuffleArray[][] = printMagicSquare;
        for (int i = 0; i <nn*nn ; i++)
        {
            x1 = rand.nextInt(nn-1);
            y1 = rand.nextInt(nn-1);

            rDirection = rand.nextInt(4)+1;

            switch(rDirection)
            {
                case 1:
                    if (x1 != 0) {
                        x2 = x1--;
                    }
                    else {
                        x2 = x1++;
                    }
                    break;
                case 2:
                    if (x1 != nn-1) {
                        x2 = x1+1;
                    }
                    else {
                        x2 = x1-1;
                    }
                    break;
                case 3:
                    if (y1 != 0) {
                        y2 = y1-1;
                    }
                    else {
                        y2 = y1+1;
                    }
                    break;
                case 4:
                    if (y1 != nn-1){
                        y2 = y1+1;
                    }
                    else {
                        y2 = y1-1;
                    }
                    break;
            }
            int temp1 = shuffleArray[x1][y1]; // temp variable to store the original number which is then swapped out later
            shuffleArray[x1][y1] = shuffleArray[x2][y2];
            shuffleArray[x2][y2] = temp1;

        }
        printMagicSquare = shuffleArray;
    }
    private static boolean calculateMagSquare() // method which is used to calculate the if the square is magic by adding up all the rows, colums and diagonals
    {
        int target;
        for (int i = 0; i < nn ; i++) { //for loop that goes through the square row by row adding up all the numbers for each row
            target = 0;             // ensuring that it forms a magic square
            for (int j = 0; j < nn ; j++) {
                target+=printMagicSquare[i][j];
            }
            if (i == nn-1){

                if (target==squareCal)
                {
                    isSquareMagic=true;
                }
                else                
                {
                    isSquareMagic = false;
                }
            }
        }
        if (isSquareMagic==true) {  //if statement to stop the program from bothering to add up everything when the rows dont form a magic square
            
            for (int i = 0; i < nn ; i++) {
                target = 0;
                for (int j = 0; j < nn ; j++) {
                    target+=printMagicSquare[j][i];                    
                }
                if (i == nn-1){
    
                    if (target==squareCal)
                    {
                        isSquareMagic=true;
                    }
                    else
                    {
                        isSquareMagic=false;
                    }
                }
            }
        }
        int j = 0;
        if (isSquareMagic==true) {
            target = 0;
            for (int i = 0; i < nn ; i++) {     //for statement that adds up all numbers in a diagonal line from (0-0) to (n-n) eg (1-1) , (2-2)
                target+=printMagicSquare[i][j];
                if (target==squareCal)
                {
                    isSquareMagic=true;
                }
                else{
                    isSquareMagic=false;
                }
                j++;
            }
            
        }
        if (isSquareMagic==true) {
            j = 0;
            target = 0;
            for (int i = nn-1; i >= 0 ; i--) {  //for statement that adds up all numbers in a diagonal line from (n-1 ,j ) to (0, n) eg 2-0 , 1,1 , 0-2
                target+=printMagicSquare[i][j];
                if (target==squareCal)
                {
                    isSquareMagic=true;
                }
                else{
                    isSquareMagic=false;
                }
                j++;
            }
            
        }
        if (isSquareMagic == true) {
            return true;
        }
        else {
            return false;
        }
    }

    private static int movesMade = 0; // i had to initialize this variable outside gameLoop otherwise when a restart was used movesMade would always be 0
    public static void gameLoop() 
    {
        shuffleSquare();
        printSquare();
        movesMade=0;    //reseting the variable to 0 so when user decides to restart it resets
        System.out.println("Enter coordinates for 'X' 'Y' plus '(U(up), D(down), L(left), R(right))' ");
        System.out.println("Type 'help' for a list of commands");
        Scanner in = new Scanner(System.in);
        while(calculateMagSquare()==false)      // will continually loop while this method is false. so until the square is complete it wont break
        {
            System.out.print("Input: ");
            String playerCommand = in.nextLine();
            String[] args = playerCommand.split(" ");
            int commandArgs0=0;
            int commandArgs1=0;
            if (args[0].equals("help")) {
                System.out.println("You are trying to make a magic square with the square above where all rows, columns and diagonals are equal to "+squareCal);
                System.out.println("");
                System.out.println("Enter coordinates for 'X' 'Y' plus '(U(up), D(down), L(left), R(right))' ");
                System.out.println("Example: 2 1 u  (which will move the number in 2,1 to 2,2) ");
                System.out.println("Type 'restart' to resart.");
                System.out.println("Type 'quit' to quit.");
                System.out.println("Press Enter to continue.");
                args[0] = in.nextLine();        // useful information for the user incase they struggle to use the program
            }
            if (args[0].equals("restart")) {
                gameLoop();
            }
            if (args[0].equals("quit")) {
                System.exit(1);
            }
            try {
                for (int j = 0; j < axis.length; j++) {
                    if (Integer.parseInt(args[0])==axis[j]) {
                        commandArgs0 = Integer.parseInt(args[0])-1;
                    }
                    if (Integer.parseInt(args[1])==axis[j]) {
                        commandArgs1 = nn - Integer.parseInt(args[1]);
                    }
                }
                args[2] = args[2].toUpperCase();
                int temp;
                switch (args[2]) {
                    case "U":       //looks to the postion above selected number
                        temp = printMagicSquare[commandArgs1-1][commandArgs0];
                        printMagicSquare[commandArgs1-1][commandArgs0] = printMagicSquare[commandArgs1][commandArgs0];
                        printMagicSquare[commandArgs1][commandArgs0] = temp;
                        movesMade++;
                        break;            
                    case "D":       //looks to the postion below selected number
                        temp = printMagicSquare[commandArgs1+1][commandArgs0];
                        printMagicSquare[commandArgs1+1][commandArgs0] = printMagicSquare[commandArgs1][commandArgs0];
                        printMagicSquare[commandArgs1][commandArgs0] = temp;
                        movesMade++;
                        break;
                    case "L":       //looks to the postion to the left of the selected number
                        temp = printMagicSquare[commandArgs1][commandArgs0-1];
                        printMagicSquare[commandArgs1][commandArgs0-1] = printMagicSquare[commandArgs1][commandArgs0];
                        printMagicSquare[commandArgs1][commandArgs0] = temp;
                        movesMade++;
                        break;
                    case "R":       //looks to the postion to the right of the selected number
                        temp = printMagicSquare[commandArgs1][commandArgs0+1];
                        printMagicSquare[commandArgs1][commandArgs0+1] = printMagicSquare[commandArgs1][commandArgs0];
                        printMagicSquare[commandArgs1][commandArgs0] = temp;
                        movesMade++;
                        break;
                    }
                printSquare();  //outputs the new square with the updated positions of the numbers

                
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) { // Cacthes multiple exceptions here and handles them the same way
                printSquare();      //shows that the square has not changed
                System.out.println("Please enter a valid input eg '2 1 u'");
            }


        }   
        System.out.println("Congrats you completed the magic square in "+movesMade+" moves.");      //end of the while loop will show the winning message
        System.out.println("");
        System.out.print("Would you like to restart y/n: ");
        String playerCommand = in.nextLine();
        if (playerCommand.toLowerCase().equals("y")) {  //simple way to allow the user to keep playing once the game is over
            gameLoop();
        }
        else{
            in.close();
            System.exit(1);
        }
    }

    private static boolean stringTest(String s){    //strings are passed into this method which are then used to attempt to convert to int 
    try{                                        //depending on whether this is successful or not will return the relevant value
        Integer.parseInt(s);
        return true;
    } catch (NumberFormatException ex){
        return false;
        }
    }


    private static void printSquare() // this method will print the magic square along with x and y axis so the user can easily tell what number correlate to what coordinate
    {
        int yAxis = printMagicSquare.length;
        for(int i = 0; i < printMagicSquare.length;i++){
            System.out.println("");
            System.out.print(yAxis-i+"  ["); // printing the square with the y axis put infont of it the grid
            for (int j = 0; j < printMagicSquare.length; j++) {
                if (j<nn-1) {
                    System.out.printf("%-5s",printMagicSquare[i][j]);
                }
                if (j==nn-1) {
                    System.out.print(printMagicSquare[i][j]+"]");
                }
            }
            if (i==printMagicSquare.length-1) { //when i is equal to the length/index it is going to draw the x Axis for the user which will always be below the square
                int[] xAxis = new int[printMagicSquare.length];
                axis = xAxis;
                for (int j = 0; j < printMagicSquare.length; j++) //populating an array with integers which are used to form the x axis
                {
                    xAxis[j]=j;
                    axis[j]=j+1;
                }
                System.out.println("");
                System.out.println("");
                for (int j = 1; j < xAxis.length+1; j++) {
                    System.out.printf("%5s", j);
                }
            }
        }
        System.out.println("");
    }
}