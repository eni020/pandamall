package sample.Objects;

import sample.Tiles.Tile;
import java.util.ArrayList;

/**
 * A csokiautomatát reprezentáló osztály
 */
public class Vendingmachine extends Object{

    /**
     * Függvény, ami lépteti a csokiautomatát
     * Az összes csokiautomatával szomszédos csempét értesítjük, hogy a csokiautomata lépett, és ennek megfelelően cselekedjenek
     * @return a művelet sikeres volt, ezért igazat ad vissza
     */
    public boolean step(){
        ArrayList<Tile> list = getTile().GetNeighborList();
        for(Tile t : list) {
            t.noticedNeighborVM();
        }
        return true;
    }

    /**
     * Függvény, ami kiírja a csokiautomata fontos adatait: a nevét és a csempét amin áll
     * @return a művelet sikeres volt, ezért igazat ad vissza
     */
    public boolean stat(){
        System.out.println("name: " + getName());
        System.out.println("objectname: Vendingmachine");
        if(getTile() != null)
            System.out.println("container: " + getTile().getName());
        else
            System.out.println("container: null");
        System.out.println();
        return true;
    }
}