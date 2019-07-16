package sample.Graphics;

import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;
import sample.Tiles.Tile;

/**
 * A tíz oldalú, csillag alapú csempe osztálya
 */
public class Pentagram extends TileGraph{

    /**
     * A csempe konstruktora, ami 10 oldalat vesz fel
     * @param _tile a csempe, amihez tartozik
     */
    public Pentagram(Tile _tile){
        super(_tile);
        sides = 10;
    }

    /**
     * A csempe létrehozása, kirajzolása
     * Felvesszük a forma tíz pontját a megadottaknak megfelelően
     * és megadjuk őket a poly attribútumnak, majd beállítjuk, hogy ki van rajzolva
     * @param _center a csempe középpontja
     * @param angle a csempe szöge
     */
    public void Create(double angle, Point2D _center){

        center = _center;
        double[] points = new double[20];
        for (int i = 0; i < 10; i++) {
            double t1 = (double)i / 10 + angle;
            if(i % 2==0){
                Point2D vec = CircleValue(t1, MapGenerator.pentagram_R, center);
                points[2 * i] = vec.getX();
                points[2 * i + 1] = vec.getY();
            }
            else{
                Point2D vec = CircleValue(t1, MapGenerator.pentagram_r, center);
                points[2 * i] = vec.getX();
                points[2 * i + 1] = vec.getY();
            }
        }
        Polygon polygon = new Polygon(points);

        drawn = true;
        poly = polygon;
    }

}

