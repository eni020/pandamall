package sample.InObjects;

import sample.Animals.*;
import sample.System.*;

/**
 * A kijáratot reprezentáló osztály
 */
public class Exit extends Thing{

    /**
     * en egy változó, amiben tároljuk a kijárathoz tartozó bejáratot
     */
    Entry en = null;

    /**
     * Függvény, aminek a segítségével beállítjuk a kijárathoz tartozó bejáratot
     * @param e a beállítandó kijárat
     * @return a művelet sikeres volt, ezért igazat ad vissza
     */
    public boolean setEntry(Entry e){
        en = e;
        return true;
    }

    /**
     * Függvény, ami egy orángutánnal való ütközést szimulálja
     * Ha az orángutánt követik más állatok, akkor azokat a kijáraton való áthaladáskor pontozzuk
     * Az orángután a kijáraton áthaladva a bejáratra teleportálódik
     * @param a az orángután ami a kijáratra lépett
     * @return a művelet sikeres volt, ezért igazat ad vissza
     */
    public boolean hitByApe(Ape a) {
        if(en.getContained() == null){
            if (a.getRear() != null) {
                a.getRear().pointToApe(a);
            }
            if(a.setTeleported(true)){
                if (a.getTile() != null) {
                    a.getTile().remove(a);
                    a.setTileNull();
                }
            }
            en.setAnimal(a);
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Egy Panda akar a kijáratra lépni
     * @return a lépés nem engedélyezett, ezért hamissal tér vissza
     */
    public boolean hitByPanda(Panda p){//metódus, ami egy pandával való ütközést szimulál
        return false;
    }

    /**
     * Függvény, ami kiírja a kijárat fontos adatait: a nevét, a csempét amin áll, a rajta álló állatot és a hozzá tartozó kijáratot
     * @return a művelet sikeres volt, ezért igazat ad vissza
     */
    public boolean stat(){
        System.out.println("name: " + getName());
        System.out.println("objectname: Exit");
        if(getTile() != null)
            System.out.println("container: " + getTile().getName());
        else
            System.out.println("container: null");
        if(en != null)
            System.out.println("entry: " + en.getName());
        System.out.println();
        return true;
    }
}
