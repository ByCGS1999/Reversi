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

    static char GetBoardValue(int x, int y) {
        return board[x][y];
    }

    static boolean SetBoardValue(int x, int y, char c, boolean hasAuthority) {
        if (hasAuthority) {
            board[x][y] = c;
            return true;
        } else if (board[x][y] == '·') {
            board[x][y] = c;
            return true;
        }

        return false;
    }

    static boolean FindNeighbors(int[] pos, char playerChar, int[] oldPos) {
        if (oldPos[0] != -1 && oldPos[0] != -1) 
        {
            if(pos[0] == oldPos[0] || pos[1] == oldPos[1]) // (line)
            {
                System.out.println("is a line");
                int[] targetPos = pos;
                // identify the not equal one
                if(pos[0] != oldPos[0])
                {
                    int yOff = pos[0]-oldPos[0];
                    targetPos = new int[] { pos[0]+yOff, pos[1] };
                }
                else if(pos[1] != oldPos[1])
                {
                    int xOff = pos[1]-oldPos[1];
                    targetPos = new int[] { pos[0], pos[1]+xOff };
                }

                System.out.println(targetPos[0]);
                System.out.println(targetPos[1]);

                if(GetBoardValue(targetPos[0], targetPos[1]) == playerChar)
                {
                    SetBoardValue(pos[0], pos[1], playerChar, true);
                    return true;
                }

                return false; // idk how you managed to trigger this.
            }
            else // diagonal
            {
                System.out.println("is a diagonal");
                if(GetBoardValue(oldPos[1], oldPos[0]) == playerChar)
                {
                    SetBoardValue(pos[0], pos[1], playerChar, true);
                    return true;
                }
                return false;
            }
        } 
        else
        {
            for (int y = -1; y <= 1; y++) {
                for (int x = -1; x <= 1; x++) {
                    int[] lookoutPos = new int[] { pos[0] + y, pos[1] + x };

                    if (lookoutPos[0] >= 0 && lookoutPos[1] >= 0 && lookoutPos[0] <= board.length
                            && lookoutPos[1] <= board.length) {
                        char value = GetBoardValue(lookoutPos[0], lookoutPos[1]);

                        if (value != '·' && value != playerChar) {
                            return FindNeighbors(lookoutPos, playerChar, pos);
                        } else if (value == playerChar) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
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
                FindNeighbors(boardPos, 'O', new int[] { -1, -1 });
                res = SetBoardValue(boardPos[0], boardPos[1], 'O', false);
                break;
            case Player2:
                System.out.println("Player 2 Turn:");
                pInput = s.nextLine();
                boardPos = toBoardPosition(pInput);
                FindNeighbors(boardPos, 'X', new int[] { -1, -1 });
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
            System.out.println("Failed to fetch player input");
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
