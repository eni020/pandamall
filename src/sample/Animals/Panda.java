package sample.Animals;

import sample.System.*;
import sample.Tiles.Tile;

/**
 * a különböző tulajdonságú pandák absztakt ősosztálya, léptethető és az Animal osztály leszármazottja
 */
public abstract class Panda extends Animal implements Steppable{

    /**
     * a panda a paraméterként kapott csempére mozog
     * ha a panda teleportált, a eltávoltítja a konténeréből és hamisra állítja a teleported változó értékét
     * ha egy csempén állt a panda, azt ez előző csempének álltja be
     * ha a csempére sikeresen rálépett és áll mögötte panda, az a panda előző csempéjére mozog (rekurzívan)
     * egyébként marad az eredeti csempén
     * majd null-ra állítja be az előző csempéjét
     * @param t csempe, amire az állat mozog
     * @return a művelet sikeres volt, ezért igazat ad vissza
     */
    public boolean move(Tile t){
        if(teleported){
            container.removeAnimal();
            setTeleported(false);
        }
        if(getTile() != null){
            setPreviousTile(getTile());
        }
        if(t.accept(this)){
            if(rearAnimal != null){
                rearAnimal.move(getPreviousTile());
            }
        }
        else{
            getPreviousTile().accept(this);
        }
        //setPreviousTileNull();
        return true;
    }

    /**
     * egy orángután ütközött a pandával
     * ha még nem fogja a kezét senki, akkor az orángután korábbi csempéjére lép
     * ha az orángután már fogja egy állat kezét, a pandát beállítja az állat elülső állatának (és a pandának őt hátsó állatának)
     * beállítja az orángután és panda elülső és hátsó állat változóit egymásra, az orángután van elöl
     * @param a az orángfután, ami ütközött vele
     * @return ha hozzákapcsolódott az orángutánhoz, true-t ad vissza, egyébként false-t
     */
    public boolean hitByApe(Ape a){
        if(this.frontAnimal == null){
            a.getPreviousTile().accept(this);
            if(a.rearAnimal != null){
                if(a.rearAnimal.setFront(this)){
                    this.setRear(a.rearAnimal);
                }
            }
            if (a.setRear(this)) {
                this.setFront(a);
            }
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * az az eset, amikor egy panda ütközik a pandával
     * @param p a panda, ami ütközik vele
     * @return false-t ad vissza, mert nincs hatása a pandára
     */
    public boolean hitByPanda(Panda p){
        return false;
    }

    /**
     * amikor egy orángután kivezeti a kijáraton, az orángutánnak 10 pontot ad
     * ha van még utána panda, azokra is meghívja a függvényt rekurzívan
     * majd elengedi az előtte álló kezét
     * a csempe, amin állt eddig, eltávolítja magáról
     * a Timer osztály eltávolítja a léptethető deolgok listájából
     * @param a az orángután, aminek pontot ad
     * @return a művelet sikeres volt, ezért igazat ad vissza
     */
    public boolean pointToApe(Ape a){
        a.addPoint(10);
        if(this.getRear() != null){
            getRear().pointToApe(a);
        }
        releaseBack(frontAnimal);
        this.getTile().remove(this);
        this.setFrontNull();
        this.setRearNull();
        this.setTileNull();
        died = true;
        Timer.instance().removeSteppable(this);
        return true;
    }

    /**
     * azt az esetet valósítja meg, amikor a panda meghal
     * elengedi az előtte és mögötte állók kezét és a sor felbomlik
     * a csempe eltávolítja magáról
     * a Timer osztály eltávolítja a léptethető deolgok listájából
     * @return a művelet sikeres volt, ezért igazat ad vissza
     */
    public boolean die(){
        if(frontAnimal != null){
            frontAnimal.releaseBack(this);
        }
        else{
            this.releaseBack(this);
        }
        this.getTile().remove(this);
        this.setTileNull();
        Timer.instance().removeSteppable(this);
        died = true;
        return true;
    }

    /**
     * a panda egy lépését valósítja meg
     * ha nem fogja más állat kezét, véletlenszerűen egy csempére lép
     * ha teleportált, a konténere valamelyik szomszédjára lép
     * a véletlenszerűen kiválasztott csempére mozog
     * @return a művelet sikeres volt, ezért igazat ad vissza
     */
    public boolean step(){
        if(frontAnimal == null){
            Tile t;
            if(teleported){
                t = getContainer().getTile().getRandomNeighbor();
            }
            else{
                t = getTile().getRandomNeighbor();
            }
            move(t);
        }
        return true;
    }


}
