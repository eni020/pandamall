package sample.Graphics;

import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;
import sample.Tiles.Tile;

/**
 * Az ötszög alapú csempe osztálya
 */
public class Pentagon extends TileGraph{

    /**
     * A csempe konstruktora, ami 5 oldalat vesz fel
     * @param _tile a cempe, amihez tartozik
     */
    public Pentagon(Tile _tile){
        super(_tile);
        sides = 5;
    }

    /**
     * A csempe létrehozása, kirajzolása
     * Felvesszük az öt pontot forgatással és megadjuk őket a poly attribútumnak, majd beállítjuk, hogy ki van rajzolva
     * @param _center a csempe középpontja
     * @param angle a csempe szöge
     */
    public void Create(double angle, Point2D _center){

        center = _center;
        double[] points = new double[10];
        for (int i = 0; i < 5; i++) {
            double t1 = (double)i / 5 + angle;

            Point2D vec = CircleValue(t1, MapGenerator.pentagonRadius, center);
            points[2 * i] = vec.getX();
            points[2 * i + 1] = vec.getY();
        }
        Polygon polygon = new Polygon(points);


        drawn = true;
        poly = polygon;
    }
}
