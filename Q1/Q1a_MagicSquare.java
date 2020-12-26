import java.util.Arrays;
import java.util.Scanner;
// 
//  Joseph Wilson-Hall
// 
//  C1949969
// 
class Q1a_MagicSquare
{
    private static int nn;
    private static int x1;
    private static int y1;
    private static int x2;
    private static int y2;
    private static int squareCal = 0;
    private static int[][] printMagicSquare = new int[nn][nn];
    public static void main(String args[])
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Input an ODD integer that is 3 or more");
        String nToUse ="";
        int n = 0;
        while (stringTest(nToUse = in.next())==false || Integer.parseInt(nToUse)==0 || Integer.parseInt(nToUse)%2==0 || Integer.parseInt(nToUse) < 3) {
            System.out.println("Input an ODD integer that is 3 or more");
        }
        if (stringTest(nToUse)==true) {
            n = Integer.parseInt(nToUse);
        }
        genMagSquare(n);
        for (int i = 0; i < printMagicSquare.length; i++) {
            System.out.println(Arrays.toString(printMagicSquare[i]));
        }
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
        printMagicSquare = magiSquare;
    }
private static boolean stringTest(String s){    //strings are passed into this method which are then used to attempt to convert to int 
    try{                                //depending on whether this is successful or not will return the relevant value
        Integer.parseInt(s);
        return true;
    } catch (NumberFormatException ex){
        return false;
        }
    }
}