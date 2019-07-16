package sample.Objects;

import sample.Tiles.*;

import java.util.ArrayList;

/**
 * A játékgépet reprezentáló osztály
 */
public class Arcademachine extends Object{

    /**
     * Függvény, ami lépteti a játékgépet
     * Az összes játékgéppel szomszédos csempét értesítjük, hogy a játékgép lépett, és ennek megfelelően cselekedjenek
     * @return a művelet sikeres volt, ezért igazat ad vissza
     */
    public boolean step(){
        int random = (int)(Math.random() * 2);
        if(random == 0) {
            ArrayList<Tile> list = getTile().GetNeighborList();
            for (Tile t : list) {
                t.noticedNeighborAM();
            }
        }
        return true;
    }

    /**
     * Függvény, ami kiírja a játékgép fontos adatait: a nevét és a csempét amin áll
     * @return a művelet sikeres volt, ezért igazat ad vissza
     */
    public boolean stat(){
        System.out.println("name: " + getName());
        System.out.println("objectname: Arcademachine");
        if(getTile() != null)
            System.out.println("container: " + getTile().getName());
        else
            System.out.println("container: null");
        System.out.println();
        return true;
    }

}
