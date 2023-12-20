package src.Watermark;

import java.util.ArrayList;
import java.util.Random;

public class Motd {

    static ArrayList<String> mes = new ArrayList<String>();
    static int lenght;
    static Random r = new Random();
    static int index;

    public static void M() {

        mes.add("Should not be played while driving.");
        mes.add("All is full of love!");
        mes.add("Limited edition!");
        mes.add("Made by CGS!");
        mes.add("Made by FEAR!");
        mes.add("Uses GrassTracing!");
        mes.add("Not on Steam!");
        mes.add("90% bug free!");
        mes.add("Im gonna fly some planes!");
        mes.add("12345 is a bad password!");
        mes.add("Totally forgot about Dre!");
        mes.add("PC gaming since 1873!");
        mes.add("Any computer is a laptop if you're brave enough!");
        mes.add("I use Arch btw!");

    }

    public static String Message0() {
        lenght = mes.size();
        index = r.nextInt(lenght-1);
        return mes.get(index);
    }

}
