package sample.InObjects;

import sample.System.*;
import sample.Animals.*;

/**
 * A szekrényt reprezentáló osztály
 */
public class Wardrobe extends Thing{//A Wardrobe osztály a Thing osztály leszármazottja

    /**
     * A pair a szekrény párját tárolja
     * A contanined a szekrényben tartózkodó állatot tárolja
     */
    Wardrobe pair = null;
    Animal contained = null;

    /**
     * függvény, aminek segítségével beállítjuk a szekrény párját
     * @param w a beállítandó szekrény
     */
    public void setPair(Wardrobe w){
        pair = w;
    }

    /**
     * a contained getter-e
     * @return visszaadja a contanied értékét
     */
    public Animal getContained(){
        return contained;
    }

    /**
     * függvény, aminek segítségével beállítjuk, ha egy állat a a szekrényen áll
     * @param a az állat ami a szekrényen áll
     * @return a művelet sikeres volt, ezért igazat ad vissza
     */
    public boolean setAnimal(Animal a){
        a.setContainer(this);
        contained = a;
        return true;
    }

    /**
     * Függvény, aminek a segítségével kitöröljük a szekrényre lépett állatot
     * @return a művelet sikeres volt, ezért igazat ad vissza
     */
    public boolean removeAnimal(){
        contained.removeContainer();
        contained = null;
        return true;
    }

    /**
     * Metódus, ami egy orángutánnal való ütközést szimulál
     * MEgnézzük hogy a szekrény párján áll-e éppen állat
     * Ha nem, akkor az orángutánt a szekrény párjához teleportáljuk
     * Ha igen, akkor nem engedjük a lépést
     * @param a a szekrényre lépő orángután
     * @return visszatér azzal, hogy sikeres volt-e a lépés
     */
    public boolean hitByApe(Ape a){
        Animal a2 = pair.getAnimal();
        if(a2 == null){
            if(a.setTeleported(true)){
                a.getTile().remove(a);
                a.setTileNull();
            }
            pair.setAnimal(a);
            return true;
        }
        else{
            return false;
        }

    }

    /**
     * Metódus, ami egy pandával való ütközést szimulál
     * MEgnézzük hogy a szekrény párján áll-e éppen állat
     * Ha nem, akkor az pandát a szekrény párjához teleportáljuk
     * Ha igen, akkor nem engedjük a lépést
     * @param p a szekrényre lépő panda
     * @return visszatér azzal, hogy sikeres volt-e a lépés
     */
    public boolean hitByPanda(Panda p){
        Animal a2 = pair.getAnimal();
        if(a2 == null){
            if(p.setTeleported(true)){
                p.getTile().remove(p);
                p.setTileNull();
            }
            pair.setAnimal(p);
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * a contained getter-e
     * @return visszaadja a contanied értékét
     */
    public Animal getAnimal(){
        return contained;
    }

    /**
     * a pair getter-e
     * @return visszaadja a szekrény párját
     */
    public Wardrobe getPair(){
        return pair;
    }

    /**
     * Függvény, ami kiírja a szekrény fontos adatait: a nevét, a csempét amin áll, a rajta álló állatot és a párját
     * @return a művelet sikeres volt, ezért igazat ad vissza
     */
    public boolean stat(){
        System.out.println("name: " + getName());
        System.out.println("objectname: Wardrobe");
        if(getTile() != null)
            System.out.println("container: " + getTile().getName());
        else
            System.out.println("container: null");
        if(contained != null)
            System.out.println("contained: " + contained.getName());
        else
            System.out.println("contained: null");
        if(pair != null)
            System.out.println("pair: " + pair.getName());
        else
            System.out.println("pair: null");
        System.out.println();
        return true;
    }
}
