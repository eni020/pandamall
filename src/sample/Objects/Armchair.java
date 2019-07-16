package sample.Objects;

import sample.Animals.*;
import sample.Tiles.Tile;

import java.util.ArrayList;

/**
 * A fotelt reprezentáló osztály
 */
public class Armchair extends Object {

    /**
     * A contained egy változó, amiben tároljuk a fotelben pihenő pandát
     */
    SleepyPanda contained = null;

    /**
     * Függvény, ami lépteti a fotelt
     * Az összes fotellel szomszédos csempét értesítjük, hogy a fotel lépett, és ennek megfelelően cselekedjenek
     * @return a művelet sikeres volt, ezért igazat ad vissza
     */
    public boolean step(){
        int random = (int)(Math.random() * 2);
        if(random == 0) {
            ArrayList<Tile> list = getTile().GetNeighborList();
            for (Tile t : list) {
                t.noticedNeighborAC(this);
            }
        }
        return true;
    }

    /**
     * A contained getter-e
     * @return visszaadja a fotelben ülő pandát
     */
    public SleepyPanda getPanda(){
        return contained;
    } //visszaadjuk a fotelben ülő pandát

    /**
     * Függvény, aminek a segítségével beállítjuk, ha egy panda beül a fotelbe
     * @return a művelet sikeres volt, ezért igazat ad vissza
     */
    public boolean setPanda(SleepyPanda s){
        s.setContainer(this);
        contained = s;
        return true;
    }

    /**
     * Függvény, aminek a segítségével beállítjuk, ha egy panda kiszáll a fotelből
     * @return a művelet sikeres volt, ezért igazat ad vissza
     */
    public boolean removeAnimal(){
        contained.removeContainer();
        contained = null;
        return true;
    }

    /**
     * Függvény, ami kiírja a fotel fontos adatait: a nevét, a csempét amin áll és a fotelben fekvő állatot
     * @return a művelet sikeres volt, ezért igazat ad vissza
     */
    public boolean stat(){
        System.out.println("name: " + getName());
        System.out.println("objectname: Armchair");
        if(getTile() != null)
            System.out.println("container: " + getTile().getName());
        else
            System.out.println("container: null");
        if(contained != null)
            System.out.println("contained: " + contained.getName());
        else
            System.out.println("contained: null");
        System.out.println();
        return true;
    }
}
