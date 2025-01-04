import java.util.Scanner;

class TikTacToe {
    public static void main(String[] args) {
        char board[][] = new char[3][3];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j]=' ';
            }
            System.out.println();
        }
        boolean gameOver = false;
        char player = 'X';
        Scanner sc = new Scanner(System.in);
        
       
        while (!gameOver) {
            //System.out.println("Enter your move");
            printBoard(board);
            System.out.println("Player "+player+" enter:");
            int row = sc.nextInt();
            int col = sc.nextInt();
            System.out.println();

            if (board[row][col] == ' ') {
                board[row][col]=player;
                gameOver =hasWon(board,player);
            
                if(gameOver){
                    System.out.println("PLAYER "+player+" WON");
                }else{
                    if(player=='X'){
                        player='O';
                    }else{player='X';}
                     
                 }
                }
                
            else{
                System.out.println("Invalid Input! Please enter a valid input");
            }
        }
        printBoard(board);
    }

public static boolean hasWon(char[][]board,char player){
    
    for(int row =0;row<board.length;row++)
    if(board[row][0]==player && board[row][1]==player && board[row][2]==player){
    return true;
}
    for(int col =0;col<board.length;col++)
    if(board[0][col]== player && board[1][col]==player && board[2][col]==player){
        return true;
    }
   
    if(board[0][0]==player && board[1][1]==player && board[2][2]==player){
        return true;
    }
    if(board[0][2]==player && board[1][1]==player && board[2][0]==player){
        return true;
    }
    return false;
}
public static void printBoard(char[][] board) {
    for (int row = 0; row < board.length; row++) {
        for (int col = 0; col < board.length; col++) {
            System.out.print(board[row][col]+" | ");
        }
        System.out.println();
    }
}
}