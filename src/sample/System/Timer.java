package sample.System;

import java.util.ArrayList;
import java.util.*;

/**
 * A léptetendő dolgok ütemezésére használt singleton osztály
 */
public class Timer {

    /**
     * A steppable a léptethető dolgok listája
     * Az it a listához tartozó iterátor
     * Az instance változóban tároljuk az osztály egyetlen létrehozható példányát
     * A ticking változóban tároljuk, hogy éppen folyamatban van-e a tick
     */
    ArrayList<Steppable> steppable = new ArrayList<>();
    ListIterator<Steppable> it = null;
    private static Timer instance = null;
    boolean ticking = false;

    /**
     * Privát konstruktor
     */
    private Timer() { }

    /**
     * Függvény, ami visszaadja az osztály példányát
     * @return visszaadja a példányt
     */
    static public Timer instance() {
        if (instance == null) {
            instance = new Timer();
        }
        return instance;
    }

    /**
     * Függvény, ami törli a példányt
     */
    public void finalize() {
        instance = null;
    }

    /**
     * Függvény, ami hozzáad egy Steppable-t a listához
     * @param s a hozzáadandó dolog
     * @return a művelet sikeres volt, ezért igazat ad vissza
     */
    public boolean addSteppable(Steppable s){
        steppable.add(s);
        it = steppable.listIterator();
        return true;
    }

    /**
     * Függvény, ami eltávolítja a paraméterként kapott s-t a listából
     * @param s az eltávolítandó dolog
     * @return a művelet sikeres volt, ezért igazat ad vissza
     */
    public boolean removeSteppable(Steppable s){
        if(ticking)
            it.remove();
        steppable.remove(s);
        return true;
    }

    /**
     * Üt egyet a Timer, tehát minden nyilvántartott Steppable-re meghívja a step() metódusát
     * @return a művelet sikeres volt, ezért igazat ad vissza
     */
    public boolean tick(){
        ticking = true;
        if(it != null){
            it = steppable.listIterator();
            while (it.hasNext()) {
                Steppable s = it.next();
                s.step();
            }

        }
        ticking = false;
        return true;
    }

    /**
     * Alapállapotba állítja a Steppable listát
     * @return a művelet sikeres volt, ezért igazat ad vissza
     */
    public boolean resetSteppable(){
        steppable = new ArrayList<>();
        it = null;
        ticking = false;
        return true;
    }
}
