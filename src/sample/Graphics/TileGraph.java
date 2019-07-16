package sample.Graphics;

import javafx.geometry.Point2D;
import javafx.scene.paint.Paint;
import sample.Tiles.Tile;
import javafx.scene.shape.Polygon;

/**
 * A csempék kirajzolására szolgáló osztály
 */
abstract public class TileGraph {


    /**
     * A tile a csempe, amihez tartozik, a poly a kirajzoláshoz szükséges pontokat tárolja, a center a középpont,
     * a drawn tárolja, hogy ki van-e rajzolva, a sides az alakzat oldalainak a számát tárolja, a color pedig a színt
     */
    Tile tile;
    Polygon poly = new Polygon(0);
    Point2D center;
    boolean drawn;
    int sides;
    Paint color = null;

    /**
     * A csempe létrehozása, kirajzolása, a leszármazottaknak kell megvalósítania
     * @param _center a csempe középpontja
     * @param angle a csempe szöge
     */
    abstract void Create(double angle, Point2D _center);

    /**
     * A középpont visszaadása
     * @return a középpont
     */
    public Point2D getCenter(){
        return center;
    }

    /**
     * A csempe konstruktora
     * @param _tile a csempe, amihez tartozik
     */
    public TileGraph(Tile _tile){
        tile = _tile;
        drawn = false;
    }

    /**
     * A poly visszaadása
     * @return a poly értéke
     */
    public Polygon getPoly(){
        return poly;
    }

    /**
     * A drawn visszaadása
     * @return a drawn értéke
     */
    public boolean getDrawn(){
        return drawn;
    }

    /**
     * A sides visszaadása
     * @return a sides értéke
     */
    public int getSides(){
        return sides;
    }


    public Point2D CircleValue(double t, double r, Point2D center) {
        double x0 = center.getX() + r * Math.cos(2 * Math.PI * t);
        double y0 = center.getY() + r * Math.sin(2 * Math.PI * t);
        return new Point2D(x0, y0);
    }
}
