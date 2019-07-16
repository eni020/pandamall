package sample.Graphics;

import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;

/**
 * A tárgyak kirajzolására szolgáló osztály
 */
public class ThingGraph {

    /**
     * Az iv a tárgy képe, a scale a zoomolás aránya, a point pedig a tárgy koordinátája
     */
    ImageView iv;
    double scale;
    Point2D point;

    /**
     * A tárgy konstuktora, ahol beállítjuk az értékeit egy új elem felvételekor
     * @param i a beállítandó kép
     * @param s a beállítandó skálázás
     * @param p a beállítandó koordináta
     */
    ThingGraph(ImageView i, double s, Point2D p){ //
        iv = i;
        scale = s;
        point = p;
        iv.setX(point.getX()-25/2 * scale);
        iv.setY(point.getY()-25/2 * scale);
    }

    /**
     * A koordináták beállítása
     * @param p a beállítandó koordináta
     */
    void SetPoint(Point2D p){
        point = p;
        iv.setX(point.getX()-25/2 * scale);
        iv.setY(point.getY()-25/2 * scale);
    }

    /**
     * A kép beállítása
     * @param i a beállítandó kép
     */
    void SetIV(ImageView i){
        iv = i;
        iv.setX(point.getX()-25/2 * scale);
        iv.setY(point.getY()-25/2 * scale);
    }

    /**
     * A tárgy képének visszaadása
     * @return a tárgy képe
     */
    ImageView GetImageView(){
        return iv;
    }
}
