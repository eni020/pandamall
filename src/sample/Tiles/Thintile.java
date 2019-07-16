package sample.Tiles;

import sample.Animals.*;

/**
 * A törékeny csempék osztálya, a Tile osztály leszármazottja
 */
public class Thintile extends Tile{ //

    /**
     * a broken változó tárolja, hogy a csempe el van-e törve, kezdetben false
     * a ttl változó tárolja, hogy a csempére még hányszor léphetnek rá, kezdetben hússzor
     */
    boolean broken = false;
    int ttl = 20;

    /**
     * a broken setter-e
     * @param broke értékét állítja be a broken-re
     */
    public void setBroken(boolean broke)
    {
        broken = broke;
    }

    public boolean getBroken(){
        return broken;
    }

    /**
     * Ez a függvény modellezi azt, amikor egy Panda rálép egy csempére
     * Ha a newThing üres, akkor t-t állítja be newThing-nek
     * Eltávolítja t-t arról a csempéről, ahol állt
     * Beállítja, hogy t is tudja, hogy ezen a csempén áll
     * Ha az oldThing üres, akkor t lesz az oldThing
     * Ha a csempe törött, akkor t meghal
     * Egyébként csökken egyel a ttl
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
            if(broken){
                t.die();
            }else{
                decrease();
            }
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Ez a függvény modellezi azt, amikor egy Ape rálép egy csempére
     * Ha a newThing üres, akkor t-t állítja be newThing-nek
     * Eltávolítja t-t arról a csempéről, ahol állt
     * Beállítja, hogy t is tudja, hogy ezen a csempén áll
     * Ha az oldThing üres, akkor t lesz az oldThing
     * Ha a csempe törött, akkor t meghal
     * Egyébként csökken egyel a ttl
     * A lépés sikerült, igaz értékkel tér vissza
     * Ha a newThing foglalt, akkor a lépés érvénytelen
     * @param t a csempére rálépni készülő panda
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
            if(broken){
                t.die();
            }else{
                decrease();
            }
            return true;
        }
        else{
            return false;
        }

    }

    /**
     * Csökken a csempe élettartama
     * Ha még nem tört el, akkor csökken a ttl
     * Ha eléri a 0-át a ttl, akkor a broken igaz lesz
     * @return akkor ad vissza true-t, ha a csempe előzőleg nem volt törött, egyébként false-t ad vissza
     */
    public boolean decrease(){  //
        if(!broken){
            ttl--;
            if(ttl <= 0){
                broken = true;  //
            }
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Kiírja a csempe adatait: a nevét és a változói értékét
     * @return a művelet sikeres volt, ezért igazat ad vissza
     */
    public boolean stat(){
        System.out.println("name: " + name);
        System.out.println("objectname: ThinTile");
        if(oldThing != null)
            System.out.println("contained: " + oldThing.getName());
        else
            System.out.println("contained: null");
        int k = 0;
        for(Tile i : neighborTileList){
            System.out.println("neighbor_" + k + ": " + i.getName());
            k++;
        }
        if(broken){
            System.out.println("broken: true");
        }
        else {
            System.out.println("ttl: " + ttl);
        }
        System.out.println();
        return true;
    }
}
