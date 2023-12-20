/***
 *       ___                                              _    
 *      | _ \    ___    __ __    ___      _ _    ___     (_)   
 *      |   /   / -_)   \ V /   / -_)    | '_|  (_-<     | |   
 *      |_|_\   \___|   _\_/_   \___|   _|_|_   /__/_   _|_|_  
 *    _|"""""|_|"""""|_|"""""|_|"""""|_|"""""|_|"""""|_|"""""| 
 *    "`-0-0-'"`-0-0-'"`-0-0-'"`-0-0-'"`-0-0-'"`-0-0-'"`-0-0-' 
 */

 /*
  * CGS & FEAR
  */

package src;

import java.util.*;
import src.Classes.*;

public class Program {
    enum PlayerTurn {
        Player1,
        Player2
    }

    public static char[][] board;
    static Scanner s;
    static boolean isPlaying = false;
    static PlayerTurn currentPlayerTurn = PlayerTurn.Player1;
    static List<Ray> rays = new ArrayList<Ray>();

    static char GetBoardValue(int x, int y) {
        return board[x][y];
    }

    static boolean HasNeighbor(int x, int y, char playerChar)
    {
        int[][] possible_positions = {
        new int[] {x-1,y-1},
        new int[] {x,y-1},
        new int[] {x+1,y-1},
        new int[] {x-1,y},
        new int[] {x+1,y},
        new int[] {x-1,y+1},
        new int[] {x,y+1},
        new int[] {x+1,y+1},
        };

        for(int i = 0; i < possible_positions.length; i++)
        {
            if(possible_positions[i][0] <= 0)
                continue;

            if(possible_positions[i][1] <= 0)
                continue;

            if(possible_positions[i][0] >= 8)
                continue;

            if(possible_positions[i][1] >= 8)
                continue;

            int bX = possible_positions[i][0];
            int bY = possible_positions[i][1];

            if(GetBoardValue(bX, bY) == playerChar)
                return true;
        }

        return false;
    }

    public static boolean SetBoardValue(int x, int y, char c, boolean hasAuthority) {
        if (hasAuthority) {
            board[x][y] = c;
            return true;
        } else if (board[x][y] == '·') {
            board[x][y] = c;
            return true;
        }

        return false;
    }
    
    
    static boolean FindNeighbors(int[] pos, char playerChar, int[] oldPos)
    {
        boolean result = false;
        
        int[] tl = {-1, -1};
        int[] tc = { 0, -1};
        int[] tr = { 1, -1};
        int[] cl = {-1, 0};
        int[] cr = { 1, 0};
        int[] bl = {-1, 1};
        int[] bc = { 0, 1};
        int[] br = { 1, 1};

        rays.add(new Ray(pos, tl, null, null, playerChar));
        rays.add(new Ray(pos, tc, null, null, playerChar));
        rays.add(new Ray(pos, tr, null, null, playerChar));
        rays.add(new Ray(pos, cl, null, null, playerChar));
        rays.add(new Ray(pos, cr, null, null, playerChar));
        rays.add(new Ray(pos, bl, null, null, playerChar));
        rays.add(new Ray(pos, bc, null, null, playerChar));
        rays.add(new Ray(pos, br, null, null, playerChar));

        for (Ray r : rays) 
        {
            r.Execute();
        }

        for(Ray r : rays)
        {
            if(r.hasResult && r.hasTarget)
            {
                board[r.x][r.y] = playerChar;
                continue;
            }
            else if(r.childHasResult)
            {
                Ray targetRay = r;

                while(targetRay.hasChild() && targetRay.getChild().childHasTarget)
                {
                    SetBoardValue(r.x, r.y, playerChar, true);
                    targetRay = targetRay.getChild();
                }
            }
        }

        return result;
    }

    static void Init() {
        board = new char[8][8];
        rays = new ArrayList<Ray>();

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
                if(HasNeighbor(boardPos[0], boardPos[1], 'O'))
                    res = SetBoardValue(boardPos[0], boardPos[1], 'O', false);
                FindNeighbors(boardPos, 'O', new int[] { -1, -1 });
                
                break;
            case Player2:
                System.out.println("Player 2 Turn:");
                pInput = s.nextLine();
                boardPos = toBoardPosition(pInput);
                if(HasNeighbor(boardPos[0], boardPos[1], 'X'))
                    res = SetBoardValue(boardPos[0], boardPos[1], 'X', false);
                FindNeighbors(boardPos, 'X', new int[] { -1, -1 });
                
                break;
        }

        rays.clear();

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
        src.Watermark.RTX.Water();

        System.out.println(src.Watermark.Motd.Message0());

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