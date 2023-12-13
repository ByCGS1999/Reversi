package src;

import java.util.*;

public class Program {
    enum PlayerTurn {
        Player1,
        Player2
    }

    static char[][] board;
    static Scanner s;
    static boolean isPlaying = false;
    static PlayerTurn currentPlayerTurn = PlayerTurn.Player1;

    static char GetBoardValue(int x, int y)
    {
        return board[x][y];
    }

    static boolean SetBoardValue(int x, int y, char c, boolean hasAuthority) {
        if (hasAuthority && board[x][y] == '·') {
            board[x][y] = c;
            return true;
        } else if (board[x][y] == '·') {
            board[x][y] = c;
            return true;
        }

        return false;
    }

    static void FindNeighbors(String playerInput) {
        int[] bp = toBoardPosition(playerInput);

        int[] tl = new int[] { bp[0] - 1, bp[1] - 1 };
        int[] tc = new int[] { bp[0] - 1, bp[1] };
        int[] tr = new int[] { bp[0] - 1, bp[1] + 1 };

        int[] cl = new int[] { bp[0], bp[1] - 1 };
        int[] cc = new int[] { bp[0], bp[1] };
        int[] cr = new int[] { bp[0], bp[1] + 1 };

        int[] bl = new int[] { bp[0] + 1, bp[1] - 1 };
        int[] bc = new int[] { bp[0] + 1, bp[1] };
        int[] br = new int[] { bp[0] + 1, bp[1] + 1 };

        for(int x = 0; x < 7; x++)
        {
            
        }
    }

    static void Init() {
        board = new char[8][8];

        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                board[x][y] = '·';
            }
        }

        board[3][3] = 'X';
        board[4][3] = 'O';
        board[3][4] = 'O';
        board[4][4] = 'X';
    }

    static int[] toBoardPosition(String rawString) {
        String[] vars = rawString.split(",");

        int y = Integer.parseInt(vars[0]);
        int x = toInt(vars[1].charAt(0));

        return new int[] { y, x };
    }

    static void HandlePlayerInput() {
        int[] boardPos;
        boolean res = false;
        String pInput;
        switch (currentPlayerTurn) {
            case Player1:
                System.out.println("Player 1 Turn:");
                pInput = s.nextLine();
                boardPos = toBoardPosition(pInput);
                FindNeighbors(pInput);
                res = SetBoardValue(boardPos[0], boardPos[1], 'O', false);
                break;
            case Player2:
                System.out.println("Player 2 Turn:");
                pInput = s.nextLine();
                boardPos = toBoardPosition(pInput);
                FindNeighbors(pInput);
                res = SetBoardValue(boardPos[0], boardPos[1], 'X', false);
                break;
        }

        if (res) {
            if (currentPlayerTurn == PlayerTurn.Player1) {
                currentPlayerTurn = PlayerTurn.Player2;
            } else {
                currentPlayerTurn = PlayerTurn.Player1;
            }
        } else {
            HandlePlayerInput();
        }
    }

    static int toInt(char in) {
        switch (in) {
            case 'A':
                return 0;
            case 'B':
                return 1;
            case 'C':
                return 2;
            case 'D':
                return 3;
            case 'E':
                return 4;
            case 'F':
                return 5;
            case 'G':
                return 6;
            case 'H':
                return 7;
            default:
                return 0;
        }
    }

    static char toChar(int in) {
        switch (in) {
            case 0:
                return 'A';
            case 1:
                return 'B';
            case 2:
                return 'C';
            case 3:
                return 'D';
            case 4:
                return 'E';
            case 5:
                return 'F';
            case 6:
                return 'G';
            case 7:
                return 'H';
            default:
                return 'A';
        }
    }

    static void OutputBoard() {
        int begY = 0;

        System.out.print(" ");
        for (int x = 0; x < board[0].length; x++) {
            System.out.print(" " + toChar(x));
        }

        System.out.println();

        for (char c[] : board) {
            System.out.print(begY + " ");
            for (char x : c) {
                System.out.print(x + " ");
            }
            System.out.println();
            begY++;
        }
    }

    public static void main(String[] args) {
        s = new Scanner(System.in);

        Init();

        menu();

        s.nextLine();

        while (isPlaying) {
            OutputBoard();
            HandlePlayerInput();
        }

        s.close();
    }

    static void menu() {
        System.out.println("+----------------------------------+");
        System.out.println("| 1.Play                           |");
        System.out.println("| 2.Exit                           |");
        System.out.println("+----------------------------------+");

        int op = s.nextInt();
        switch (op) {
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
