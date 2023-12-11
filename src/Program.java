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
        
    }

}