package sample.Graphics;

import javafx.geometry.Point2D;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Path;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;

/**
 * Az összekötő nyíl osztálya
 */
public class Arrow {
    /**
     * A start a nyíl kezdő koordinátái, az end a nyíl befejező koordinátái, a Path a nyíl hosszát jelképező út,
     * a moveTo az úthoz tartozó kiegészítés/bejárás, a lineTo az út kirajzolása
     */
    Point2D start;
    Point2D end;
    Path path = new Path();
    MoveTo moveTo = new MoveTo();
    LineTo lineTo = new LineTo();

    /**
     * egy setter függvény, ami beállítja a nyíl koordinátáit
     * @param s a beállítandó kezdő koordináta
     * @param e a beállítandó befejező koordináta
     */
    public void Set(Point2D s, Point2D e){
        start = s;
        end = e;
    }

    /**
     * a nyíl létrehozása
     * @param s a nyíl kezdőpontja
     * @param e a nyíl végpontja
     * @param p a nyíl színe
     */
    public void Create(Point2D s, Point2D e, Paint p){
        start = s;
        end = e;
        moveTo.setX(start.getX());
        moveTo.setY(start.getY());

        lineTo.setX(end.getX());
        lineTo.setY(end.getY());

        path.getElements().add(moveTo);
        path.getElements().add(lineTo);
        path.setStrokeWidth(5);
        path.setStroke(p);
    }

    /**
     * az adott útvonal visszaadása
     * @return az útvonal
     */
    public Path GetPath(){
        return path;
    }

}
