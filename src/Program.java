package src;
import java.util.*;

public class Program
{
    static char[][] board;
    static Scanner s;

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

    public static void main(String[] args) {
        s = new Scanner(System.in);

        Init();

        for(char c[] : board)
        {
            for(char x : c)
            {
                System.out.print(x + " ");
            }
            System.out.println();
        }

        s.close();
    }

    static void menu(){
        System.out.println("+----------------------------------+
                            | Col1                             |
                            | Value 1                          |
                            | Separate                         |
                            | This is a row with only one cell |
                            +----------------------------------+");
        
        int op = s.next();
        switch (op)
        {
            case "1":

        }   
    }

}


