package sample.Graphics;

import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;
import sample.Tiles.Tile;

/**
 * A tízszög alapú csempe osztálya
 */
public class Decagon extends TileGraph {

    /**
     * A csempe konstruktora, ami 10 oldalat vesz fel
     * @param _tile a csempe, amihez tartozik
     */
    public Decagon(Tile _tile){
        super(_tile);
        sides = 10;
    }

    /**
     * A csempe létrehozása, kirajzolása
     * Felvesszük a tíz pontot forgatással és megadjuk őket a poly attribútumnak, majd beállítjuk, hogy ki van rajzolva
     * @param _center a csempe középpontja
     * @param angle a csempe szöge
     */
    public void Create(double angle, Point2D _center){
        center = _center;
        double[] points = new double[20];

        for (int i = 0; i < 10; i++) {
            double t1 = (double)i / 10;
            Point2D vec = CircleValue(t1, MapGenerator.decagonRadius, center);
            points[2 * i] = vec.getX();
            points[2 * i + 1] = vec.getY();
        }
        Polygon polygon = new Polygon(points);

        drawn = true;
        poly = polygon;
    }
}
