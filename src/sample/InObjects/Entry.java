package sample.InObjects;

import sample.Animals.*;
import sample.System.*;

/**
 * A bejáratot reprezentáló osztály
 */
public class Entry extends Thing{ //Az Entry osztály az Object osztály leszármazottja



    /**
     * A contained egy változó, amiben tároljuk az éppen a bejáraton álló állatot
     */
    Animal contained = null;

    /**
     * a contained getter-e
     * @return visszaadja a contanied értékét
     */
    public Animal getContained(){
        return contained;
    }

    /**
     * függvény, aminek segítségével beállítjuk, ha egy állat a bejáraton áll
     * @param a az állat ami a bejáraton áll
     * @return a művelet sikeres volt, ezért igazat ad vissza
     */
    public boolean setAnimal(Animal a){
        a.setContainer(this);
        contained = a;
        return true;
    }

    /**
     * Egy Ape akar a bejáratra lépni
     * @return a lépés nem engedélyezett, ezért hamissal tér vissza
     */
    public boolean hitByApe(Ape a){
        return false;
    }

    /**
     * Egy Panda akar a bejáratra lépni
     * @return a lépés nem engedélyezett, ezért hamissal tér vissza
     */
    public boolean hitByPanda(Panda p){
        return false;
    }

    /**
     * Függvény, aminek a segítségével kitöröljük a bejáratra lépett állatot
     * @return a művelet sikeres volt, ezért igazat ad vissza
     */
    public boolean removeAnimal(){
        contained.removeContainer();
        contained = null;
        return true;
    }

    /**
     * Függvény, ami kiírja a bejárat fontos adatait: a nevét, a csempét amin áll és a rajta álló állatot
     * @return a művelet sikeres volt, ezért igazat ad vissza
     */
    public boolean stat(){
        System.out.println("name: " + getName());
        System.out.println("objectname: Entry");
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
