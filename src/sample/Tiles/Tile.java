package sample.Tiles;

import java.util.ArrayList;
import sample.Objects.Armchair;
import sample.System.*;
import sample.Animals.*;

/**
 *A Csempék osztálya
 */
public class Tile {

    /**
     * oldThing: a csempén álló régi dolog
     * newThing: a csempén álló új dolog
     * neighborTileList: egy csempe szomszédait tároló lista
     * name: a csempe neve
     */
    Thing oldThing = null;
    Thing newThing = null;

    ArrayList<Tile> neighborTileList = new ArrayList<>();

    String name = null;

    /**
     * a name változó getter-e
     * @return visszaadja a csempe nevét
     */
    public String getName(){
        return name;
    }

    /**
     * a name változó setter-e
     * @param n a név, amire beállítja a name változót
     */
    public void setName(String n){
        name = n;
    }

    /**
     * ez a függvény meghívódik, ha rálépnek a csempére, a Tile osztálynál nincs hatása
     * @return false-t ad vissza
     */
    public boolean decrease(){
        return false;
    }

    public boolean getBroken(){
        return false;
    }

    /**
     * Függvény, amivel hozzá lehet adni a paraméterként kapott csempét a szomszédaihoz
     * @param t a csempe, amit hozzá kell adni a szomszédokhoz
     * @return a művelet sikeres volt, ezért igazat ad vissza
     */
    public boolean addNeighbor(Tile t){
        boolean has = false;
        for (Tile i : neighborTileList){
            if(t == i){
                has = true;
            }
        }
        if(!has){
            neighborTileList.add(t);
        }
        return true;
    }

    /**
     * A neighborTileList getter-e
     * @return visszaadja a szomszédok listáját
     */
    public ArrayList<Tile> GetNeighborList(){
        return neighborTileList;
    }

    /**
     * az oldThing setter-e
     * @param t tárgy, amelyet beállít a csempére
     * @return a művelet sikeres volt, ezért igazat ad vissza
     */
    public boolean setThing(Thing t){
        t.setTile(this);
        oldThing = t;
        return true;
    }

    /**
     * az oldThing getter-e
     * @return visszaadja a csempén álló tárgyat
     */
    public Thing getThing(){
        return oldThing;
    }

    /**
     * Ez a függvény modellezi azt, amikor egy Ape rálép egy csempére
     * Ha a newThing üres, akkor t-t állítja be newThing-nek
     * Eltávolítja t-t arról a csempéről, ahol állt
     * Beállítja, hogy t is tudja, hogy ezen a csempén áll
     * Ha az oldThing nem üres, akkor ütközteti egymással a két dolgot
     * Ha az ütközés hamis értékkel tér vissza, a lépés érvénytelen
     * Ha az oldThing üres, akkor t lesz az oldThing
     * A lépés sikerült, igaz értékkel tér vissza
     * Ha a newThing foglalt, akkor a lépés érvénytelen
     * @param t a csempére rálépni készülő orángután
     * @return akkor ad vissza igazat, ha a csempe elfogadta az orángutánt
     */
    public boolean accept(Ape t){
        if(newThing == null){
            newThing = t;
            if(t.getTile() != null){
                t.getTile().remove(t);
            }
            t.setTile(this);
            if(oldThing != null){
                if(!oldThing.hitByApe(t)) {
                    return false;
                }
            } else {
                oldThing = t;
                newThing = null;
            }
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Ez a függvény modellezi azt, amikor egy Panda rálép egy csempére
     * Ha a newThing üres, akkor t-t állítja be newThing-nek
     * Eltávolítja t-t arról a csempéről, ahol állt
     * Beállítja, hogy t is tudja, hogy ezen a csempén áll
     * Ha az oldThing nem üres, akkor ütközteti egymással a két dolgot
     * Ha az ütközés hamis értékkel tér vissza, a lépés érvénytelen
     * Ha az oldThing üres, akkor t lesz az oldThing
     * A lépés sikerült, igaz értékkel tér vissza
     * Ha a newThing foglalt, akkor a lépés érvénytelen
     * @param t a csempére rálépni készülő panda
     * @return akkor ad vissza igazat, ha a csempe elfogadta a pandát
     */
    public boolean accept(Panda t){
        if(newThing == null){
            newThing = t;
            if(t.getTile() != null){
                t.getTile().remove(t);
            }
            t.setTile(this);
            if(oldThing != null){
                if(oldThing.hitByPanda(t)){
                    return true;
                }
                else{
                    return false;
                }
            } else {
                oldThing = t;
                newThing = null;
            }
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Eltávolítja a paraméterként kapott pandát
     * null-ra állítja az oldThinget, ha ő volt a panda
     * null-ra állítja a newThinget, ha ő volt a panda
     * Hamissal tér vissza, ha egyik dolog sem a panda volt
     * @param t az eltávolítandó panda
     * @return ha sikerült a törlés, igazat ad vissza
     */
    public boolean remove(Panda t){
        if(oldThing == t){
            oldThing = null;
        }
        else if(newThing == t){
            newThing = null;
        }
        else{
            return false;
        }
        return true;
    }

    /**
     * Eltávolítja a paraméterként kapott tárgyat
     * null-ra állítja az oldThinget, ha ő volt a tárgy
     * null-ra állítja a newThinget, ha ő volt a tárgy
     * Hamissal tér vissza, ha egyik dolog sem a tárgy volt
     * @param t az eltávolítandó tárgy
     * @return ha sikerült a törlés, igazat ad vissza
     */
    public boolean removeThing(Thing t){
        if(oldThing == t){
            oldThing = null;
        }
        else if(newThing == t){
            newThing = null;
        }
        else{
            return false;
        }
        return true;
    }

    /**
     * Eltávolítja a paraméterként kapott orángutánt
     * null-ra állítja az oldThinget, ha ő volt az orángután
     * null-ra állítja a newThinget, ha ő volt az orángután
     * Hamissal tér vissza, ha egyik dolog sem az orángután volt
     * @param t az eltávolítandó orángután
     * @return ha sikerült a törlés, igazat ad vissza
     */
    public boolean remove(Ape t){
        if(oldThing == t){
            oldThing = null;
        }
        else if(newThing == t){
            newThing = null;
        }
        else{
            return false;
        }
        return true;
    }

    /**
     * @return visszaadja a csempe egy véletlenszerűen kiválasztott szomszédját
     */
    public Tile getRandomNeighbor(){
        int random = (int)(Math.random() * neighborTileList.size());
        return neighborTileList.get(random);
    }

    /**
     * egy szomszédos csempén álló Arcademachine figyelmezteti a rajta álló tárgyat
     * @return a művelet sikeres volt, ezért igazat ad vissza
     */
    public boolean noticedNeighborAM(){
        if(oldThing != null)
           oldThing.noticedByAM();
        return true;
    }

    /**
     * egy szomszédos csempén álló Armchair figyelmezteti a rajta álló tárgyat
     * @param ac
     * @return a művelet sikeres volt, ezért igazat ad vissza
     */
    public boolean noticedNeighborAC(Armchair ac){
        if(oldThing != null)
        oldThing.noticedByAC(ac);
        return true;
    }

    /**
     * Egy szomszédos csempén álló Vendingmachine figyelmezteti a rajta álló tárgyat
     * @return a művelet sikeres volt, ezért igazat ad vissza
     */
    public boolean noticedNeighborVM(){
        if(oldThing != null)
            oldThing.noticedByVM();
        return true;
    }

    /**
     * Kiírja a csempe adatait: a nevét és a változói értékét
     * @return a művelet sikeres volt, ezért igazat ad vissza
     */
    public boolean stat(){
        System.out.println("name: " + name);
        System.out.println("objectname: Tile");
        if(oldThing != null)
            System.out.println("contained: " + oldThing.getName());
        else
            System.out.println("contained: null");
        int k = 0;
        for(Tile i : neighborTileList){
            System.out.println("neighbor_" + k + ": " + i.getName());
            k++;
        }
        System.out.println();
        return true;
    }
}