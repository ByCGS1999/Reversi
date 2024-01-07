package src.Classes;

import src.Program;
import src.Interfaces.*;

public class Ray implements IRay 
{
    public int x,y;
    public int dir_x,dir_y;

    public boolean hasResult, hasTarget = false;
    public boolean childHasResult, childHasTarget;

    public Ray childRay = null;
    public Ray parentRay = null;

    public char playerChar;
    public char raytraceChar = '·';

    public Ray(int[] pos, int[] dir, Ray child, Ray parent, char expectedChar)
    {
        x = pos[0];
        y = pos[1];

        hasResult = false;
        hasTarget = false;
        dir_x = dir[0];
        dir_y = dir[1];

        childRay = child;
        parentRay = parent;

        playerChar = expectedChar;
    }

    public boolean hasChild()
    {
        return childRay != null;
    }

    public boolean hasParent()
    {
        return parentRay != null;
    }

    public Ray getChild()
    {
        return childRay;
    }

    public Ray getParent()
    {
        return parentRay;
    }

    public void SetResult(boolean target)
    {
        childHasResult = true;
        childHasTarget = target;

        if(hasParent())
            getParent().SetResult(target);
    }

    public void Execute()
    {
        if(hasChild()) 
        {
            getChild().Execute();
            return;
        }

        if(hasParent())
        {
            if(getParent().hasResult)
            {
                hasResult = true;
                return;
            }
        }

        int[] newPosition = { x+dir_x, y+dir_y };
        int[] newDirection = {dir_x, dir_y};
        int _x = newPosition[0];
        int _y = newPosition[1];

        if(_x < 0 || _y < 0)
        {
            hasResult = true;
            return;
        }

        if(_x >= 8 || _y >= 8)
        {
            hasResult = true;
            return;
        }

        if(Program.board[_x][_y] == playerChar)
        {
            hasResult = true;
            hasTarget = true;

            Program.board[x][y] = playerChar;

            if(hasParent())
                getParent().SetResult(hasTarget);

            return;
        }
        else
        {
            //Program.board[_x][_y] = raytraceChar;
            childRay = new Ray(newPosition, newDirection, null, this, playerChar);
            childRay.Execute();
        }
    }
}