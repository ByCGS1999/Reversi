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
        
    }

    public static void main(String[] args) {
        s = new Scanner(System.in);

        Init();

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


