package sample.Animals;

import sample.System.*;
import sample.Tiles.*;


/**
 * A Panda és Ape osztályok ősosztálya
 * egy állatot reprezentál
*/
abstract public class Animal extends Thing{

    /**
     * A boolean típusú teleported változó értéke megadja, hogy az állat teleportált-e, alapértelmezetten hamis
     * A frontAnimal a példány kezét fogó elülső állat
     * A rearAnimal a példány kezét fogó hátsó állat
     * A container olyan tárgy, amiben az állat éppen áll vagy ül, pl szekrény, bejárat, fotel
     * A boolean típusú változó megadja, hogy az állat meghalt-e, alapértelmezetten hamis
     */
    boolean teleported = false;
    Animal frontAnimal = null;
    Panda rearAnimal = null;
    Thing container = null;
    boolean died = false;


    /**
     * Az állat mozgását reprezentáló absztrakt metódus, implementálva a leszármazott osztályokban
     * @param t csempe, amire az állat mozog
     * @return boolean érték, hogy a művelet sikeres volt-e
     */
    public abstract boolean move(Tile t);

    /**
     * Metódus, amely beállítja a teleported változó értékét a paraméterként kapott értékre
     * @param b boolean típusú
     * @return a művelet sikeres volt, ezért igazat ad vissza
     */
    public boolean setTeleported(boolean b){ //
        teleported = b; // itt kapja meg a teleported változó a paraméter értékét
        return true;
    }

    /**
     * metódus, beállítja, hogy az állat maga előtt a paraméterként kapott állat kezét fogja
     * @param a ez az állat lesz a frontanimal
     * @return a művelet sikeres volt, ezért igazat ad vissza
     */
    public boolean setFront(Animal a){
        frontAnimal = a; //az elülső állatot tároló változó a paraméter értékét kapta meg
        return true;
    }

    /**
     * metódus, beállítja null-ra a frontAnimal változó értékét
     * @return a művelet sikeres volt, ezért igazat ad vissza
     */
    public boolean setFrontNull(){
        frontAnimal = null;
        return true;
    }

    /**
     * metódus, beállítja, hogy az állat maga mögött a paraméterként kapott állat kezét fogja
     * @param a ez az állat csak panda lehet
     * @return a művelet sikeres volt, ezért igazat ad vissza
     */
    public boolean setRear(Panda a){
        rearAnimal = a; //a hátsó állatot tároló változó a paraméter értékét kapta meg
        return true;
    }

    /**
     * metódus, beállítja null-ra a rearAnimal változó értékét
     * @return a művelet sikeres volt, ezért igazat ad vissza
     */
    public boolean setRearNull(){
        rearAnimal = null;
        return true;
    }

    /**
     * A frontAnimal getter-e
     * @return az elülső állatot adja vissza
     */
    public Animal getFront(){
        return frontAnimal;
    }

    /**
     * A rearAnimal getter-e
     * @return az hátsó állatot adja vissza
     */
    public Panda getRear(){
        return rearAnimal;
    }

    /**
     * Az állat halálát reprezentáló absztrakt metódus, implementálva a leszármazott osztályokban
     * @return
     */
    abstract public boolean die();

    public boolean getDied(){
        return died;
    }

    /**
     * metódus, azt az esetet valósítja meg, amikor az állat elengedi a mögötte álló állat kezét
     * ha az állat még fogja a kezét egy másik állatnak hátrafelé, rekurzívan meghívja a mögötte lévő állatra a függvényt
     * a az állat még fogja a kezét egy másik állatnak előrefelé, az előtte lévő állatnak null-ra állítja a rearAnimal változóját
     * ha az állat fogja a kezét a paraméterként kapott állatnak előrefelé, rekurzívan meghívja az előtte lévő állatra a függvényt
     * végül elengedi az előtte lévő állat kezét is
     * @param a a mögötte lévő állat
     * @return a művelet sikeres volt, ezért igazat ad vissza
     */
    public boolean releaseBack(Animal a){
        if(rearAnimal != null){
            rearAnimal.releaseBack(a);
        }
        else{
            if(frontAnimal != null){
                frontAnimal.setRearNull();
                if(frontAnimal != a){
                    frontAnimal.releaseBack(a);
                }
            }
            this.setFrontNull();
        }
        return true;
    }

    /**
     * beállítja a container változót
     * @param t erre az értékre állítja be a container változót
     * @return a művelet sikeres volt, ezért igazat ad vissza
     */
    public boolean setContainer(Thing t){
        container = t;
        return true;
    }

    /**
     * eltávolítja az állat konténerét, amiben állt vagy ült
     * @return a művelet sikeres volt, ezért igazat ad vissza
     */
    public boolean removeContainer(){
        container = null;
        return true;
    }

    /**
     * A container getter-e
     * @return visszaadja az állat konténerét
     */
    public Thing getContainer(){
        return container;
    }

    /**
     * kiírja az állat tulajdonságait a console-ra, implementálva a leszármazott osztályokban
     * @return a művelet sikeres volt, ezért igazat ad vissza
     */
    public abstract boolean stat();
}
