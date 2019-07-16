package sample.System;

import sample.Objects.Armchair;
import sample.Tiles.*;
import sample.Animals.*;

/**
 * Az Object és az Animal osztályok közös ősosztálya
 */
abstract public class Thing {

    /**
     * A previousTile tárolja azt a csempét, ahol a dolog előzőleg állt
     * A tile tárolja azt a csempét ahol a csempe áll
     * A name tárolja a dolog nevét
     */
    Tile previousTile = null;
    Tile tile = null;
    String name = null;

    /**
     * Visszaadja a name értékét
     * @return a name értéke
     */
    public String getName(){
        return name;
    }

    /**
     * Beállítja a name értékét a paraméterül kapott értékre
     */
    public void setName(String n){
        name = n;
    }

    /**
     * Metódus, ami különböző leszármazottaknál máshogy viselkedik
     * @return false-al tér vissza, mert alapesetben nem engedélyezett
     */
    public boolean removeAnimal(){
        return false;
    }

    /**
     * Metódus, ami visszaadja az előző csempét
     * @return a previousTile értéke
     */
    public Tile getPreviousTile(){
        return previousTile;
    }

    /**
     * Metódus, ami visszaadja az aktuális csempét
     * @return a tile értéke
     */
    public Tile getTile(){
        return tile;
    }

    /**
     * Beállítja a previousTile értékét a paraméterül kapott értékre
     * @return a művelet sikeres volt, ezért igazat ad vissza
     */
    public boolean setPreviousTile(Tile p){
        previousTile = p;
        return true;
    }

    /**
     * Beállítja a tile értékét a paraméterül kapott értékre
     * @return a művelet sikeres volt, ezért igazat ad vissza
     */
    public boolean setTile(Tile p){
        tile = p;
        return true;
    }

    /**
     * Beállítja a previousTile értékét nullra
     * @return a művelet sikeres volt, ezért igazat ad vissza
     */
     public boolean setPreviousTileNull(){
         previousTile = null;
         return true;
     }

    /**
     * Beállítja a tile értékét nullra
     * @return a művelet sikeres volt, ezért igazat ad vissza
     */
     public boolean setTileNull(){
         tile = null;
         return true;
     }

    /**
     * A dolog egy orángutánnal ütközött
     */
    abstract public boolean hitByApe(Ape a);

    /**
     * A dolog egy pandával ütközött
     */
    abstract public boolean hitByPanda(Panda p);

    /**
     * A dolog figyelmeztetve lett egy játékgép által
     * @return false-al tér vissza, mert alapesetben nincs hatása
     */
    public boolean noticedByAM(){   //Figyelmeztetve lett egy Archademachine által, különböző leszármazottaknál máshogy viselkedhet
        return false;
    }

    /**
     * A dolog figyelmeztetve lett egy csokiautomata által
     * @return false-al tér vissza, mert alapesetben nincs hatása
     */
    public boolean noticedByVM(){   //Figyelmeztetve lett egy Vendingmachine által, különböző leszármazottaknál máshogy viselkedhet
        return false;
    }

    /**
     * A dolog figyelmeztetve lett egy fotel által
     * @return false-al tér vissza, mert alapesetben nincs hatása
     */
    public boolean noticedByAC(Armchair ac){    //Figyelmeztetve lett egy Armchair által, különböző leszármazottaknál máshogy viselkedhet
        return false;
    }
}
