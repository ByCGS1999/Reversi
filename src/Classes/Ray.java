package Classes;

import java.util.*;
import Interfaces.*;

public class Ray implements Interfaces.IRay 
{
    public int x,y;
    public int dir_x,dir_y;

    public Ray(int[] pos, int[] dir)
    {
        x = pos[0];
        y = pos[1];

        dir_x = dir[0];
        dir_y = dir[1];
    }

    public void Execute()
    {
        
    }
}