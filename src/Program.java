package src;
import java.util.*;

public class Program
{
    enum PlayerTurn
    {
        Player1,
        Player2
    }

    static char[][] board;
    static Scanner s;
    static boolean isPlaying = false;
    static PlayerTurn currentPlayerTurn = PlayerTurn.Player1;

    static void SetBoardValue(int x, int y, char c)
    {
        board[x][y] = c;
    }

    static void Init()
    {
        board = new char[8][8];
        
        for(int x = 0; x < board.length; x++)
        {
            for(int y = 0; y < board[x].length; y++)
            {
                board[x][y] = '·';
            }
        }

        board[3][3] = 'X';
        board[4][3] = 'X';
        board[3][4] = 'O';
        board[4][4] = 'O';
    }

    static void HandlePlayerInput(PlayerTurn player)
    {
        switch (player) {
            case Player1:
                
                break;
            case Player2:

                break;
            default:
                break;
        }
    }

    static void OutputBoard()
    {
        for(char c[] : board)
        {
            for(char x : c)
            {
                System.out.print(x + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        s = new Scanner(System.in);

        Init();

        menu();

        while(isPlaying)
        {
            HandlePlayerInput(currentPlayerTurn);
        }

        s.close();
    }

    static void menu(){
        System.out.println("+----------------------------------+");
        System.out.println("| 1.Play                           |"); 
        System.out.println("| 2.Exit                           |"); 
        System.out.println("+----------------------------------+");
        
        int op = s.nextInt();
        switch (op)
        {
            case 1:
               isPlaying = true;
               break;
            case 2:
               break;
            default:
               break;
        }   
    }

}


