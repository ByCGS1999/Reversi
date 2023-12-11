package src;
import java.util.*;

public class Program
{
    static char[][] board;

    static void SetBoardValue(int x, int y, char c)
    {
        board[x][y] = c;
    }

    static void Init()
    {
        board = new char[8][8];
        board[0][0] = 'c';
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        Init();

        s.close();
    }

    static void menu(){
        switch
    }

}