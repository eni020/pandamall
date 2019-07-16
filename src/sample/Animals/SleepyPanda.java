package sample.Animals;

import sample.Objects.Armchair;

/**
 * álmos panda, a Panda osztály leszármazottja
 */
public class SleepyPanda extends Panda{

    /**
     * az álmospanda figyelmeztetve lett egy fotel által
     * ha a fotelben nem ül még panda, elengedi azokat az állatokat, akiknek eddig a kezét fogta (ha van ilyen)
     * a csempe, amin eddig állt eltávolítja magáról
     * beleül a fotelbe
     * @param ac a fotel, ami figyelmeztette
     * @return a művelet sikeres volt, ezért igazat ad vissza
     */
    public boolean noticedByAC(Armchair ac){
        if(ac.getPanda() == null){
            if(frontAnimal != null){
                releaseBack(frontAnimal);
            }
            else{
                releaseBack(this);
            }
            getTile().remove(this);
            this.setTileNull();
            ac.setPanda(this);
            setTeleported(true);
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Kiírja a panda adatait: a nevét és a változói értékét
     * @return a művelet sikeres volt, ezért igazat ad vissza
     */
    public boolean stat(){
        System.out.println("name: " + getName());
        System.out.println("objectname: SleepyPanda");
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
        System.out.println("died: " + died);
        System.out.println();
        return true;
    }
}
