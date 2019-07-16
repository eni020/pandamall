package sample.Objects;

import sample.System.*;
import sample.Animals.*;

/**
 * A játékgép, a fotel és a csokiautomata közös absztrakt ősosztálya
 */
public abstract class Object extends Thing implements Steppable{ //az Object osztály közös alapot nyújt az Armchair, ArcadeMachne és a VendingMachine osztályoknak

    /**
     * Egy Ape akar egy tárgyra lépni
     * @return a lépés nem engedélyezett, ezért hamissal tér vissza
     */
    public boolean hitByApe(Ape a){ //metódus, ami egy orángutánnal való ütközést szimulál
        return false; //visszatérünk hamis értékkel
    }

    /**
     * Egy Panda akar egy tárgyra lépni
     * @return a lépés nem engedélyezett, ezért hamissal tér vissza
     */
    public boolean hitByPanda(Panda p){//metódus, ami egy pandával való ütközést szimulál
        return false; //visszatérünk hamis értékkel
    }

    /**
     * Függvény, ami lépteti a tárgyat
     */
    public abstract boolean step();

    /**
     * Függvény, ami kiírja a tárgy fontos adatait
     */
    public abstract boolean stat();
}