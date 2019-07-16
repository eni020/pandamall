package sample.Animals;

/**
 * félénk panda, a Panda osztály leszármazottja
 */
public class ShyPanda extends Panda{

    /**
     * a félénk panda figyelmeztetve lett egy játékgép által, megijedt
     * elengedi a kögötte álló panda kezét
     * @return a művelet sikeres volt, ezért igazat ad vissza
     */
    public boolean noticedByAM(){
        if(rearAnimal != null){
            releaseBack(this);
        }
        return true;
    }

    /**
     * Kiírja a panda adatait: a nevét és a változói értékét
     * @return a művelet sikeres volt, ezért igazat ad vissza
     */
    public boolean stat(){
        System.out.println("name: " + getName());
        System.out.println("objectname: ShyPanda");
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
