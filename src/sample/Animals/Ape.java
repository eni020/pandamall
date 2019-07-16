package sample.Animals;

import sample.Tiles.Tile;

/**
 * az orángutánt reprezentáló osztály, az Animal osztályból származik
 */
public class Ape extends Animal{

    /**
     * A point attribútum reprezentálja azokat a pontokat, amiket az orángután a kijáraton kivezetett pandák után kap, kezdetben az értéke 0
     * A step változó megadja, hogy az orángután hány lépésig nem foghat pandát, kezdeti értéke 3
     */
    int point = 0;
    int step = 3;

    /**
     * az orángután a paraméterként kapott csempére mozog
     * ha az orángután teleportált, a eltávoltítja a konténeréből és hamisra állítja a teleported változó értékét
     * ha egy csempén állt az orángután, azt ez előző csempének álltja be
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
        else if(getContainer() != null){
            setPreviousTile(getContainer().getTile());
        }

        if(t.accept(this)){
            if(rearAnimal != null){
                if(getPreviousTile() != null){
                    rearAnimal.move(getPreviousTile());
                }
            }
        }
        else{
            getPreviousTile().accept(this);
        }
        //setPreviousTileNull();
        if(step < 3){
            this.addStep();
        }

        return true;
    }

    /**
     * Egy orángután akar vele ütközni
     * ha a másik orángután nem fog pandákat és legalább 3 lépéssel korábban raboltak tőle pandákat, ekkor a két orándután helyet cserél
     * ha az orángután már fogja egy állat kezét, a pandát beállítja a másik orángután hátsó állatának, és a panda elülső állatának az orángutánt
     * magának pedig a hátsó állatot és a lépések számát lenullázza
     * @param a az orángután, ami ütközni akar vele
     * @return ha a másik orángután sikeresen elrabolta az orángután pandáit, true-t ad vissza, egyébként false-t
     */
    public boolean hitByApe(Ape a){
        if(a.rearAnimal == null && a.step >= 3){
            a.getPreviousTile().accept(this);
            if(this.rearAnimal != null){
                if (a.setRear(this.rearAnimal)) {
                    this.rearAnimal.setFront(a);
                }
                this.setRearNull();
                this.setStepNull();
            }
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * az az eset, amikor egy panda ütközik az orángutánnal
     * @param p a panda, ami ütközik vele
     * @return false-t ad vissza, mert nincs hatása az orángutánra
     */
    public boolean hitByPanda(Panda p){ //ha egy panda akarna vele ütközni, false-t ad vissza
        return false;
    }

    /**
     * ha kivezet a kijáraton egy pandát, az után pontot kap
     * @param p a kapott pontok száma, ezt adja hozzá a point változóhoz
     * @return a művelet sikeres volt, ezért igazat ad vissza
     */
    public boolean addPoint(int p){
        point += p;
        return true;
    }

    public int getPoint(){
        return point;
    }

    /**
     * beállítja 0-ra a step változó értékét
     * @return a művelet sikeres volt, ezért igazat ad vissza
     */
    public boolean setStepNull(){
        step = 0;
        return true;
    }

    /**
     * az orángután lép egyet ezért a step változót növeli eggyel
     * @return a művelet sikeres volt, ezért igazat ad vissza
     */
    public boolean addStep(){
        step++;
        return true;
    }

    /**
     * azt az esetet valósítja meg, amikor az orángután meghal
     * elengedi a mögötte álló panda kezét és a sor felbomlik
     * a csempe eltávolítja magáról
     * és beállítja a died értékét igazra
     * @return a művelet sikeres volt, ezért igazat ad vissza
     */
    public boolean die(){ //
        this.releaseBack(this); //
        this.getTile().remove(this); //
        this.setTileNull();
        died = true;
        return true;
    }

    public void setDied(boolean b){
        died = b;
    }


    /**
     * Kiírja az orángután adatait: a nevét és a változói értékét
     * @return a művelet sikeres volt, ezért igazat ad vissza
     */
    public boolean stat(){
        System.out.println("name: " + getName());
        System.out.println("objectname: Orangutan");
        System.out.println("teleported: " + teleported);
        if(frontAnimal != null)
            System.out.println("frontanimal: " + frontAnimal.getName());
        else
            System.out.println("frontanimal: null");
        if(rearAnimal != null)
            System.out.println("rearanimal: " + rearAnimal.getName());
        else
            System.out.println("rearanimal: null");
        if(getTile() != null)
            System.out.println("container: " + getTile().getName());
        else if(container != null)
            System.out.println("container: " + container.getName());
        else
            System.out.println("container: null");

        System.out.println("points: " + point);
        System.out.println("step: " + step);

        System.out.println("died: " + died);
        System.out.println();
        return true;
    }
}
