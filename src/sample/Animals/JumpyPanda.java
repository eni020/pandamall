package sample.Animals;

/**
 * ugrós panda, a Panda osztály leszármazottja
 */
public class JumpyPanda extends Panda{

    /**
     * az ugrós panda figyelmeztetve lett egy csokiautomata által, és megijed
     * ugrik egyet a csempén
     * @return a művelet sikeres volt, ezért igazat ad vissza
     */
    public boolean noticedByVM(){
        getTile().decrease();
        return true;
    }

    /**
     * Kiírja a panda adatait: a nevét és a változói értékét
     * @return a művelet sikeres volt, ezért igazat ad vissza
     */
    public boolean stat(){
        System.out.println("name: " + getName());
        System.out.println("objectname: JumpyPanda");
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
