package sample.Graphics;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.image.Image;
import javafx.geometry.Rectangle2D;

import sample.InObjects.*;
import sample.Objects.Arcademachine;
import sample.Objects.Armchair;
import sample.Objects.Vendingmachine;
import sample.System.Timer;
import sample.Tiles.Thintile;
import sample.Tiles.Tile;
import sample.Animals.*;

import javafx.scene.image.ImageView;

import java.util.ArrayList;
import javafx.scene.text.*;

public class MapGenerator {

    /**
     *
     */
    static double decagonRadius = 90; //180;
    static double side = 2 * decagonRadius * Math.sin(Math.PI/10);
    static double pentagonRadius = side/(Math.sin(Math.PI/5) * 2);

    static double ratio = (1 + Math.sqrt(5))/2;
    static double pentagramB = side/ratio;
    static double theta = Math.toRadians(36);
    static double pentagram_r = pentagramB/(2 * Math.tan(theta)) + pentagramB/(2 * Math.tan(theta/2));
    static double pentagram_R = pentagramB/(2 * Math.sin(theta));

    static double decagon_and_pentagon_distance = Math.sqrt(decagonRadius * decagonRadius - (side/2) * (side/2)) + Math.sqrt(pentagonRadius * pentagonRadius - (side/2) * (side/2));
    static double pentagram_and_pentagon_distance = pentagram_R + pentagonRadius;
    static double pentagon_and_pentagon_distance = side/Math.tan(Math.PI/5);

    static double scale = 2.1;//4.2;

    boolean endgame = false;

    Group g;
    Group hands = new Group();

    ArrayList<Tile> tiles = new ArrayList<>();
    ArrayList<TileGraph> graphs = new ArrayList<>();

    ArrayList<Panda> pandas = new ArrayList<>();
    ArrayList<ThingGraph> objects = new ArrayList<>();

    Text p1score;
    Text p2score;


    Ape p1 = new Ape();
    Ape p2 = new Ape();
    Entry en = new Entry();
    Exit ex = new Exit();
    Wardrobe w1 = new Wardrobe();
    Wardrobe w2 = new Wardrobe();
    Armchair ac = new Armchair();

    Image img;

    Arrow arrow = new Arrow();
    Tile p1next = null;
    Tile p2next = null;

    boolean player1 = true;

    static boolean firstSwitch = true;


    static boolean acSit = false;
    static Panda tempPanda = null;

    static int nb = 0;

    /**
     * @param t
     * @param r
     * @param center
     * @return
     */
    public Point2D CircleValue(double t, double r, Point2D center) {
        double x0 = center.getX() + r * Math.cos(2 * Math.PI * t);
        double y0 = center.getY() + r * Math.sin(2 * Math.PI * t);
        return new Point2D(x0, y0);
    }

    /**
     * a pálya generálásáért felelős függvény
     * @param gr a csoport, amibe a pálya elemeit rakja
     * létrehoz egy-egy szövegdonozt a játékosok pontjainak
     * betölti az elemeket ábrázoló képet egy Image objektumba
     *
     *
     */
    public void startGenerate(Group gr) {

        p1score = new Text(10, 20, "Player1 points: 0");
        p2score = new Text(10, 40, "Player2 points: 0");

        Image temp = new Image("sample/Graphics/objects.png");
        img = new Image("sample/Graphics/objects.png",temp.getWidth() * scale, temp.getHeight() * scale, true,true);


        g = gr;

        for(int i = 0; i <= 2; i++){
            for(int j = 0; j <= 3; j++){
                Rectangle2D rectangle2D = new Rectangle2D(j * 25 * scale, i * 25 * scale, 25 * scale - 1, 25 * scale - 1);
                ImageView tempview = new ImageView(img);
                tempview.setViewport(rectangle2D);
            }
        }



        //csempék rajzolása
        {
            for (int i = 0; i <= 41; i++) {

                if (i == 5 || i == 6) {
                    tiles.add(new Thintile());
                } else {
                    tiles.add(new Tile());
                }

                if (i < 5) {
                    graphs.add(new Decagon(tiles.get(i)));
                } else if (i < 8) {
                    graphs.add(new Pentagram(tiles.get(i)));
                } else {
                    graphs.add(new Pentagon(tiles.get(i)));
                }
            }


            graphs.get(0).Create(0, new Point2D(480, 450));//960, 900));

            for (int i = 8; i <= 14; i++) {

                double angle = (double) (i - 4) / 10 + 0.05;
                Point2D vec = CircleValue(angle, decagon_and_pentagon_distance, graphs.get(0).getCenter());

                if ((i - 4) % 2 == 0) graphs.get(i).Create(0.05, vec);
                else graphs.get(i).Create(0.15, vec);
            }

            graphs.get(5).Create(0.05, CircleValue(0.55, pentagram_and_pentagon_distance, graphs.get(9).getCenter()));

            for (int i = 15; i <= 18; i++) {
                double angle = (double) (i + 1) * 2 / 10 + 0.05;
                Point2D vec = CircleValue(angle, pentagram_and_pentagon_distance, graphs.get(5).getCenter());

                graphs.get(i).Create(0.15, vec);
            }


            graphs.get(6).Create(0.05, CircleValue(0.95, pentagram_and_pentagon_distance, graphs.get(13).getCenter()));

            for (int i = 19; i <= 22; i++) {

                double angle = (double) (i - 1) * 2 / 10 + 0.05;
                Point2D vec = CircleValue(angle, pentagram_and_pentagon_distance, graphs.get(6).getCenter());

                graphs.get(i).Create(0.15, vec);
            }

            double angle = (double) 2 / 10 + 0.05;
            Point2D vec = CircleValue(angle, pentagon_and_pentagon_distance, graphs.get(21).getCenter());

            graphs.get(23).Create(0.05, vec);

            graphs.get(2).Create(0, CircleValue(0.65, decagon_and_pentagon_distance, graphs.get(11).getCenter()));


            for (int i = 24; i <= 28; i++) {

                angle = (double) (i) / 10 + 0.05;
                vec = CircleValue(angle, decagon_and_pentagon_distance, graphs.get(2).getCenter());

                if ((i) % 2 == 0) graphs.get(i).Create(0.05, vec);
                else graphs.get(i).Create(0.15, vec);
            }

            graphs.get(1).Create(0, CircleValue(0.75, decagon_and_pentagon_distance, graphs.get(12).getCenter()));

            for (int i = 29; i <= 32; i++) {

                angle = (double) (i - 2) / 10 + 0.05;
                vec = CircleValue(angle, decagon_and_pentagon_distance, graphs.get(1).getCenter());

                if ((i - 2) % 2 == 0) graphs.get(i).Create(0.05, vec);
                else graphs.get(i).Create(0.15, vec);
            }

            graphs.get(7).Create(0.15, CircleValue(0.85, pentagram_and_pentagon_distance, graphs.get(30).getCenter()));

            for (int i = 33; i <= 34; i++) {
                angle = (double) (i + 1) * 2 / 10 + 0.15;
                vec = CircleValue(angle, pentagram_and_pentagon_distance, graphs.get(7).getCenter());

                graphs.get(i).Create(0.05, vec);
            }

            graphs.get(35).Create(0.15, CircleValue((double) 1 / 10 + 0.05, pentagon_and_pentagon_distance, graphs.get(33).getCenter()));

            graphs.get(4).Create(0, CircleValue(0.25, decagon_and_pentagon_distance, graphs.get(35).getCenter()));

            for (int i = 36; i <= 37; i++) {

                angle = (double) (i - 5) / 10 + 0.05;
                vec = CircleValue(angle, decagon_and_pentagon_distance, graphs.get(4).getCenter());

                if ((i - 5) % 2 == 0) graphs.get(i).Create(0.05, vec);
                else graphs.get(i).Create(0.15, vec);
            }

            graphs.get(3).Create(0, CircleValue(0.45, decagon_and_pentagon_distance, graphs.get(25).getCenter()));

            for (int i = 38; i <= 41; i++) {

                if (i < 40) {
                    angle = (double) (i - 1) / 10 + 0.05;
                } else {
                    angle = (double) (i + 2) / 10 + 0.05;
                }

                vec = CircleValue(angle, decagon_and_pentagon_distance, graphs.get(3).getCenter());

                if (i < 40) {
                    if ((i - 1) % 2 == 0) graphs.get(i).Create(0.05, vec);
                    else graphs.get(i).Create(0.15, vec);
                } else {
                    if ((i + 2) % 2 == 0) graphs.get(i).Create(0.05, vec);
                    else graphs.get(i).Create(0.15, vec);
                }

            }
        }

        //0-tól 41-ig szomszédok beállítása
        {
            for (int i = 8; i <= 14; i++) {
                tiles.get(0).addNeighbor(tiles.get(i));
            }

            tiles.get(1).addNeighbor(tiles.get(2));
            for (int i = 28; i <= 32; i++) {
                tiles.get(1).addNeighbor(tiles.get(i));
            }
            tiles.get(1).addNeighbor(tiles.get(19));
            tiles.get(1).addNeighbor(tiles.get(12));
            tiles.get(1).addNeighbor(tiles.get(11));

            for (int i = 24; i <= 28; i++) {
                tiles.get(2).addNeighbor(tiles.get(i));
            }


            tiles.get(2).addNeighbor(tiles.get(1));
            tiles.get(2).addNeighbor(tiles.get(11));
            tiles.get(2).addNeighbor(tiles.get(10));
            tiles.get(2).addNeighbor(tiles.get(18));

            tiles.get(3).addNeighbor(tiles.get(38));
            tiles.get(3).addNeighbor(tiles.get(39));
            tiles.get(3).addNeighbor(tiles.get(25));
            tiles.get(3).addNeighbor(tiles.get(24));
            tiles.get(3).addNeighbor(tiles.get(17));
            tiles.get(3).addNeighbor(tiles.get(40));
            tiles.get(3).addNeighbor(tiles.get(41));


            tiles.get(4).addNeighbor(tiles.get(32));
            tiles.get(4).addNeighbor(tiles.get(31));
            tiles.get(4).addNeighbor(tiles.get(34));
            tiles.get(4).addNeighbor(tiles.get(35));
            tiles.get(4).addNeighbor(tiles.get(36));
            tiles.get(4).addNeighbor(tiles.get(37));
            tiles.get(4).addNeighbor(tiles.get(20));

            tiles.get(5).addNeighbor(tiles.get(16));
            tiles.get(5).addNeighbor(tiles.get(17));
            tiles.get(5).addNeighbor(tiles.get(18));
            tiles.get(5).addNeighbor(tiles.get(9));
            tiles.get(5).addNeighbor(tiles.get(15));

            tiles.get(6).addNeighbor(tiles.get(13));
            tiles.get(6).addNeighbor(tiles.get(19));
            tiles.get(6).addNeighbor(tiles.get(20));
            tiles.get(6).addNeighbor(tiles.get(21));
            tiles.get(6).addNeighbor(tiles.get(22));

            tiles.get(7).addNeighbor(tiles.get(33));
            tiles.get(7).addNeighbor(tiles.get(34));
            tiles.get(7).addNeighbor(tiles.get(30));

            tiles.get(8).addNeighbor(tiles.get(15));
            tiles.get(8).addNeighbor(tiles.get(9));
            tiles.get(8).addNeighbor(tiles.get(0));

            tiles.get(9).addNeighbor(tiles.get(5));
            tiles.get(9).addNeighbor(tiles.get(10));
            tiles.get(9).addNeighbor(tiles.get(0));
            tiles.get(9).addNeighbor(tiles.get(8));

            tiles.get(10).addNeighbor(tiles.get(18));
            tiles.get(10).addNeighbor(tiles.get(2));
            tiles.get(10).addNeighbor(tiles.get(11));
            tiles.get(10).addNeighbor(tiles.get(0));
            tiles.get(10).addNeighbor(tiles.get(9));

            tiles.get(11).addNeighbor(tiles.get(10));
            tiles.get(11).addNeighbor(tiles.get(2));
            tiles.get(11).addNeighbor(tiles.get(1));
            tiles.get(11).addNeighbor(tiles.get(12));
            tiles.get(11).addNeighbor(tiles.get(0));

            tiles.get(12).addNeighbor(tiles.get(11));
            tiles.get(12).addNeighbor(tiles.get(1));
            tiles.get(12).addNeighbor(tiles.get(19));
            tiles.get(12).addNeighbor(tiles.get(13));
            tiles.get(12).addNeighbor(tiles.get(0));

            tiles.get(13).addNeighbor(tiles.get(0));
            tiles.get(13).addNeighbor(tiles.get(12));
            tiles.get(13).addNeighbor(tiles.get(6));
            tiles.get(13).addNeighbor(tiles.get(14));

            tiles.get(14).addNeighbor(tiles.get(0));
            tiles.get(14).addNeighbor(tiles.get(13));
            tiles.get(14).addNeighbor(tiles.get(22));

            tiles.get(15).addNeighbor(tiles.get(5));
            tiles.get(15).addNeighbor(tiles.get(8));

            tiles.get(16).addNeighbor(tiles.get(40));
            tiles.get(16).addNeighbor(tiles.get(5));

            tiles.get(17).addNeighbor(tiles.get(40));
            tiles.get(17).addNeighbor(tiles.get(3));
            tiles.get(17).addNeighbor(tiles.get(24));
            tiles.get(17).addNeighbor(tiles.get(5));

            tiles.get(18).addNeighbor(tiles.get(5));
            tiles.get(18).addNeighbor(tiles.get(24));
            tiles.get(18).addNeighbor(tiles.get(2));
            tiles.get(18).addNeighbor(tiles.get(10));

            tiles.get(18).addNeighbor(tiles.get(5));
            tiles.get(18).addNeighbor(tiles.get(24));
            tiles.get(18).addNeighbor(tiles.get(2));
            tiles.get(18).addNeighbor(tiles.get(10));

            tiles.get(19).addNeighbor(tiles.get(12));
            tiles.get(19).addNeighbor(tiles.get(1));
            tiles.get(19).addNeighbor(tiles.get(32));
            tiles.get(19).addNeighbor(tiles.get(6));

            tiles.get(20).addNeighbor(tiles.get(6));
            tiles.get(20).addNeighbor(tiles.get(32));
            tiles.get(20).addNeighbor(tiles.get(4));
            tiles.get(20).addNeighbor(tiles.get(37));

            tiles.get(21).addNeighbor(tiles.get(6));
            tiles.get(21).addNeighbor(tiles.get(37));
            tiles.get(21).addNeighbor(tiles.get(23));

            tiles.get(22).addNeighbor(tiles.get(14));
            tiles.get(22).addNeighbor(tiles.get(6));
            tiles.get(22).addNeighbor(tiles.get(23));

            tiles.get(23).addNeighbor(tiles.get(22));
            tiles.get(23).addNeighbor(tiles.get(21));

            tiles.get(24).addNeighbor(tiles.get(3));
            tiles.get(24).addNeighbor(tiles.get(25));
            tiles.get(24).addNeighbor(tiles.get(2));
            tiles.get(24).addNeighbor(tiles.get(18));
            tiles.get(24).addNeighbor(tiles.get(17));

            tiles.get(25).addNeighbor(tiles.get(3));
            tiles.get(25).addNeighbor(tiles.get(39));
            tiles.get(25).addNeighbor(tiles.get(26));
            tiles.get(25).addNeighbor(tiles.get(2));
            tiles.get(25).addNeighbor(tiles.get(24));

            tiles.get(26).addNeighbor(tiles.get(27));
            tiles.get(26).addNeighbor(tiles.get(2));
            tiles.get(26).addNeighbor(tiles.get(25));

            tiles.get(27).addNeighbor(tiles.get(26));
            tiles.get(27).addNeighbor(tiles.get(28));
            tiles.get(27).addNeighbor(tiles.get(2));

            tiles.get(28).addNeighbor(tiles.get(27));
            tiles.get(28).addNeighbor(tiles.get(29));
            tiles.get(28).addNeighbor(tiles.get(1));
            tiles.get(28).addNeighbor(tiles.get(2));

            tiles.get(29).addNeighbor(tiles.get(28));
            tiles.get(29).addNeighbor(tiles.get(30));
            tiles.get(29).addNeighbor(tiles.get(1));

            tiles.get(30).addNeighbor(tiles.get(29));
            tiles.get(30).addNeighbor(tiles.get(7));
            tiles.get(30).addNeighbor(tiles.get(31));
            tiles.get(30).addNeighbor(tiles.get(1));

            tiles.get(31).addNeighbor(tiles.get(1));
            tiles.get(31).addNeighbor(tiles.get(30));
            tiles.get(31).addNeighbor(tiles.get(34));
            tiles.get(31).addNeighbor(tiles.get(4));
            tiles.get(31).addNeighbor(tiles.get(32));

            tiles.get(32).addNeighbor(tiles.get(1));
            tiles.get(32).addNeighbor(tiles.get(31));
            tiles.get(32).addNeighbor(tiles.get(4));
            tiles.get(32).addNeighbor(tiles.get(20));
            tiles.get(32).addNeighbor(tiles.get(19));

            tiles.get(33).addNeighbor(tiles.get(7));
            tiles.get(33).addNeighbor(tiles.get(35));

            tiles.get(34).addNeighbor(tiles.get(7));
            tiles.get(34).addNeighbor(tiles.get(35));
            tiles.get(34).addNeighbor(tiles.get(4));
            tiles.get(34).addNeighbor(tiles.get(31));

            tiles.get(35).addNeighbor(tiles.get(34));
            tiles.get(35).addNeighbor(tiles.get(33));
            tiles.get(35).addNeighbor(tiles.get(4));

            tiles.get(36).addNeighbor(tiles.get(37));
            tiles.get(36).addNeighbor(tiles.get(4));

            tiles.get(37).addNeighbor(tiles.get(20));
            tiles.get(37).addNeighbor(tiles.get(4));
            tiles.get(37).addNeighbor(tiles.get(36));
            tiles.get(37).addNeighbor(tiles.get(21));

            tiles.get(38).addNeighbor(tiles.get(39));
            tiles.get(38).addNeighbor(tiles.get(3));

            tiles.get(39).addNeighbor(tiles.get(38));
            tiles.get(39).addNeighbor(tiles.get(25));
            tiles.get(39).addNeighbor(tiles.get(3));

            tiles.get(40).addNeighbor(tiles.get(41));
            tiles.get(40).addNeighbor(tiles.get(3));
            tiles.get(40).addNeighbor(tiles.get(17));
            tiles.get(40).addNeighbor(tiles.get(16));

            tiles.get(41).addNeighbor(tiles.get(3));
            tiles.get(41).addNeighbor(tiles.get(40));
        }




        //tárgyak rárakása
        /*
        0 - Ugrós Panda
        1 - Ugrós Panda
        2 - Félénk Panda
        3 - Félénk Panda
        4 - Alvós Panda
        5 - Alvós Panda
        6 - Csokiautomata
        7 - Játékgép
        8 - Fotel
        9 - 1-es játékos
        10 - 2-es játékos
        11 - Kijárat
        12 - Bejárat
        13 - Szekrény
        14 - Szekrény
        */
        {
            objects.add(new ThingGraph(GetView(0,1),scale,graphs.get(26).center));
            JumpyPanda jp1 = new JumpyPanda();
            tiles.get(26).accept(jp1);
            Timer.instance().addSteppable(jp1);
            pandas.add(jp1);
            objects.add(new ThingGraph(GetView(0,1),scale,graphs.get(22).center));
            JumpyPanda jp2 = new JumpyPanda();
            tiles.get(22).accept(jp2);
            Timer.instance().addSteppable(jp2);
            pandas.add(jp2);

            objects.add(new ThingGraph(GetView(2,1),scale,graphs.get(10).center));
            ShyPanda sp1 = new ShyPanda();
            tiles.get(10).accept(sp1);
            Timer.instance().addSteppable(sp1);
            pandas.add(sp1);
            objects.add(new ThingGraph(GetView(2,1),scale,graphs.get(34).center));
            ShyPanda sp2 = new ShyPanda();
            tiles.get(34).accept(sp2);
            Timer.instance().addSteppable(sp2);
            pandas.add(sp2);

            objects.add(new ThingGraph(GetView(1,1),scale,graphs.get(35).center));
            SleepyPanda slp1 = new SleepyPanda();
            tiles.get(35).accept(slp1);
            Timer.instance().addSteppable(slp1);
            pandas.add(slp1);
            objects.add(new ThingGraph(GetView(1,1),scale,graphs.get(28).center));
            SleepyPanda slp2 = new SleepyPanda();
            tiles.get(28).accept(slp2);
            Timer.instance().addSteppable(slp2);
            pandas.add(slp2);

            objects.add(new ThingGraph(GetView(2,0),scale,graphs.get(0).center));
            Vendingmachine vm = new Vendingmachine();
            tiles.get(0).setThing(vm);
            Timer.instance().addSteppable(vm);

            objects.add(new ThingGraph(GetView(3,0),scale,graphs.get(1).center));
            Arcademachine am = new Arcademachine();
            tiles.get(1).setThing(am);
            Timer.instance().addSteppable(am);

            objects.add(new ThingGraph(GetView(0,0),scale,graphs.get(23).center));
            tiles.get(23).setThing(ac);
            Timer.instance().addSteppable(ac);


            tiles.get(3).setThing(en);
            p1.setTeleported(true);
            en.setAnimal(p1);
            tiles.get(4).setThing(ex);
            ex.setEntry(en);

            tiles.get(15).setThing(w1);
            tiles.get(36).setThing(w2);
            w2.setPair(w1);
            w1.setPair(w2);

            objects.add(new ThingGraph(GetView(3,1),scale,graphs.get(3).center));

            objects.add(new ThingGraph(GetView(0,2),scale,graphs.get(3).center));

            objects.add(new ThingGraph(GetView(2,2),scale,graphs.get(4).center));

            objects.add(new ThingGraph(GetView(1,2),scale,graphs.get(3).center));

            objects.add(new ThingGraph(GetView(3,2),scale,graphs.get(15).center));

            objects.add(new ThingGraph(GetView(3,2),scale,graphs.get(36).center));


        }

        int o = 0;

        ArrayList<TileGraph> TempTiles = new ArrayList<>();

        TileGraph tg = graphs.get(0);
        tg.getPoly().setFill(Color.YELLOW);
        tg.getPoly().setStroke(Color.BLACK);
        g.getChildren().add(tg.getPoly());

        TempTiles.add(tg);

        while(o < tiles.size()){

            int maxI = tiles.get(graphs.indexOf(TempTiles.get(o))).GetNeighborList().size();

            for(int i = 0; i < maxI; i++){

                Tile tempTile2 = tiles.get(graphs.indexOf(TempTiles.get(o))).GetNeighborList().get(i);
                int grapIX2 = tiles.indexOf(tempTile2);

                if(!g.getChildren().contains(graphs.get(grapIX2).getPoly())){
                    int maxK = tiles.get(graphs.indexOf(TempTiles.get(o))).GetNeighborList().get(i).GetNeighborList().size();

                    ArrayList<Paint> colors = new ArrayList<>();
                    colors.add(Color.BLUE);
                    colors.add(Color.GREEN);
                    colors.add(Color.DARKRED);
                    colors.add(Color.YELLOW);

                    for(int k = 0; k < maxK; k++){

                        Tile tempTile = tiles.get(graphs.indexOf(TempTiles.get(o))).GetNeighborList().get(i).GetNeighborList().get(k);

                        int grapIX = tiles.indexOf(tempTile);

                        if(graphs.get(grapIX).color != null){
                            colors.remove(graphs.get(grapIX).color);
                        }
                    }

                    graphs.get(grapIX2).getPoly().setStroke(Color.BLACK);
                    graphs.get(grapIX2).color = colors.get(0);
                    graphs.get(grapIX2).getPoly().setFill(graphs.get(grapIX2).color);
                    g.getChildren().add(graphs.get(grapIX2).getPoly());
                    TempTiles.add(graphs.get(grapIX2));
                }
            }

            o++;

        }


        g.getChildren().add(hands);

        g.getChildren().add(p1score);
        g.getChildren().add(p2score);

        g.getChildren().add(arrow.GetPath());

        for (int i = objects.size()-1; i >= 0; i--) {
            g.getChildren().add(objects.get(i).GetImageView());
        }

        Tile t = p1.getContainer().getTile();
        Tile actT = t.GetNeighborList().get(0);

        arrow.Create(graphs.get(tiles.indexOf(t)).center, graphs.get(tiles.indexOf(actT)).center, Color.RED);
        if(player1) {
            p1next = actT;
        }else{
            p2next = actT;
        }

    }


    /**
     * azt a nyilat forgatja jobbra, amivel a játékos eldöntheti, hogy melyik csempére akar lépni
     * ha már végigjárt minden szomszédot, elölről kezdi a számolást (nb), egyébként növeli eggyel a  számlálót
     * majd kirajzolja a nyilat a csempék között
     */
    public void RotateArrowRight(){

        Tile t;
        if(player1) {
            if(p1.getTile() != null){
                t = p1.getTile();
            }
            else if(p1.getContainer() != null){
                t = p1.getContainer().getTile();
            }
            else{
                t = en.getTile();
            }
        }
        else{
            if(p2.getTile() != null){
                t = p2.getTile();
            }
            else if(p2.getContainer() != null){
                t = p2.getContainer().getTile();
            }
            else{
                t = en.getTile();
            }
        }

        if (t.GetNeighborList().size() == nb + 1) {
            nb = 0;
        } else {
            nb++;
        }
        Tile actT = t.GetNeighborList().get(nb);
        arrow.Create(graphs.get(tiles.indexOf(t)).center, graphs.get(tiles.indexOf(actT)).center,Color.RED);
        if(player1) {
            p1next = actT;
        }else{
            p2next = actT;
        }
    }

    /**
     *
     * azt a nyilat forgatja balra, amivel a játékos eldöntheti, hogy melyik csempére akar lépni
     * ha a számláló értéke 0, visszaállítja szomszédok száma mínusz 1-re , egyébként csökkenti eggyel a  számlálót
     * majd kirajzolja a nyilat a csempék között
     */
    public void RotateArrowLeft(){
        Tile t;
        if(player1) {
            if(p1.getTile() != null){
                t = p1.getTile();
            }
            else if(p1.getContainer() != null){
                t = p1.getContainer().getTile();
            }
            else{
                t = en.getTile();
            }
        }
        else{
            if(p2.getTile() != null){
                t = p2.getTile();
            }
            else if(p2.getContainer() != null){
                t = p2.getContainer().getTile();
            }
            else{
                t = en.getTile();
            }
        }

        if(nb == 0){
            nb = t.GetNeighborList().size()-1;
        }else{
            nb--;
        }
        Tile actT = t.GetNeighborList().get(nb);
        arrow.Create(graphs.get(tiles.indexOf(t)).center, graphs.get(tiles.indexOf(actT)).center,Color.RED);
        if(player1) {
            p1next = actT;
        }else{
            p2next = actT;
        }
    }

    /**
     * Egy orángután lépését megvalósító függvény
     * az adott játékosra meghívja a move metódust
     * ha meghalt, beállítja a bejáratra
     * és rárajzolja a csempére, amin áll
     * majd kirajzolja a nyilat is
     * meghívja az Update függvényt
     */
    public void ApeStep(){
        Tile t;
        if(player1){
            p1.move(p1next);
            if(p1.getDied()){
                p1.setTeleported(true);
                en.setAnimal(p1);
                p1.setDied(false);
            }
            if(p1.getTile() != null){
                t = p1.getTile();
                objects.get(9).SetPoint(graphs.get(tiles.indexOf(p1.getTile())).center);
            }
            else{
                t = p1.getContainer().getTile();
                objects.get(9).SetPoint(graphs.get(tiles.indexOf(p1.getContainer().getTile())).center);
            }
        }
        else{
            p2.move(p2next);
            if(p2.getDied()){
                p2.setTeleported(true);
                en.setAnimal(p2);
                p2.setDied(false);
            }
            if(p2.getTile() != null){
                t = p2.getTile();
                objects.get(10).SetPoint(graphs.get(tiles.indexOf(p2.getTile())).center);
            }
            else{
                t = p2.getContainer().getTile();
                objects.get(10).SetPoint(graphs.get(tiles.indexOf(p2.getContainer().getTile())).center);
            }
        }
        nb = 0;
        Tile actT = t.GetNeighborList().get(nb);
        arrow.Create(graphs.get(tiles.indexOf(t)).center, graphs.get(tiles.indexOf(actT)).center,Color.RED);
        if(player1) {
            p1next = actT;
        }else{
            p2next = actT;

        }
        Update();
    }

    /**
     * elengedi mindkét játékos a mögötte álló panda kezét
     */
    public void ReleasePandas(){
        if(player1){
            p1.releaseBack(p1);
        }
        else{
            p2.releaseBack(p2);
        }
    }

    /**
     * @param i az x koordinátához tartozó érték
     * @param j az y koordinátához tartozó érték
     * @return visszaadja az aktuális képet
     */
    public ImageView GetView(int i , int j){
        Rectangle2D rectangle2D = new Rectangle2D(i * 25 * scale, j * 25 * scale, 25 * scale - 1, 25 * scale - 1);
        ImageView tempview = new ImageView(img);
        tempview.setViewport(rectangle2D);
        return tempview;
    }

    /**
     * a megjelenítés frissítéséért felelős függvény
     * törli az összekapcsoldótott kezeket
     * ha aktuálisan nem az első játékos van soron, beiktat egy tick-et
     * minden élő pandát berajzol arra a mezőre, amin áll
     * az egymás kezét fogó pandák közé nyilat rajzol, és felveszi a kezek nyilvántartásába
     * a halott pandát eltávoltja a képernyőről
     * újrarajzolja a játékosokat és a hozzájuk kapcsolódó panda közötti vonalat is, amit felvesz a keezek nyilvántartásához
     * ha egy törékeny csempe eltörött, beszínezi feketére
     * ha már nincs panda a pályán, a játéknak vége
     * frissíti a játékosok pontjait
     */
    public void Update(){
        hands.getChildren().clear();

        if(!player1){
            Timer.instance().tick();
        }

        for(int i = 0; i < pandas.size(); i++){
            if(pandas.get(i) != null){
                if(!pandas.get(i).getDied()){
                    Tile t = pandas.get(i).getTile();
                    if(t != null){
                        int ix = tiles.indexOf(t);
                        objects.get(i).SetPoint(graphs.get(ix).center);
                    }
                    else if(pandas.get(i).getContainer().getTile() != null){
                        int ix = tiles.indexOf(pandas.get(i).getContainer().getTile());
                        objects.get(i).SetPoint(graphs.get(ix).center);
                    }

                    if(pandas.get(i).getRear() != null){
                        Arrow a = new Arrow();
                        a.Create(graphs.get(tiles.indexOf(pandas.get(i).getTile())).center, graphs.get(tiles.indexOf(pandas.get(i).getRear().getTile())).center,Color.BLACK);
                        hands.getChildren().add(a.GetPath());
                    }

                }else{
                    g.getChildren().remove(objects.get(i).GetImageView());
                    pandas.set(i, null);
                }


            }
        }
        if(p1.getTile() != null)
        objects.get(9).SetPoint(graphs.get(tiles.indexOf(p1.getTile())).center);
        if(p2.getTile() != null)
        objects.get(10).SetPoint(graphs.get(tiles.indexOf(p2.getTile())).center);

        if(p1.getRear() != null){
            if(p1.getTile() != null) {
                Arrow a = new Arrow();
                a.Create(graphs.get(tiles.indexOf(p1.getTile())).center, graphs.get(tiles.indexOf(p1.getRear().getTile())).center, Color.BLACK);
                hands.getChildren().add(a.GetPath());
            }
        }

        if(p2.getRear() != null){
            if(p2.getTile() != null){
                Arrow a = new Arrow();
                a.Create(graphs.get(tiles.indexOf(p2.getTile())).center, graphs.get(tiles.indexOf(p2.getRear().getTile())).center,Color.BLACK);
                hands.getChildren().add(a.GetPath());
            }
        }


        if(acSit && ac.getPanda() == null){
            g.getChildren().add(objects.get(pandas.indexOf(tempPanda)).GetImageView());
            g.getChildren().remove(objects.get(8).GetImageView());
            objects.set(8, new ThingGraph(GetView(0,0),scale,graphs.get(23).center));
            g.getChildren().add(objects.get(8).GetImageView());
            acSit = false;
        }

        if(ac.getPanda() != null){
            acSit = true;
            g.getChildren().remove(objects.get(pandas.indexOf(ac.getPanda())).GetImageView());
            tempPanda = ac.getPanda();
            g.getChildren().remove(objects.get(8).GetImageView());
            objects.set(8, new ThingGraph(GetView(1,0),scale,graphs.get(23).center));
            g.getChildren().add(objects.get(8).GetImageView());
        }

        if(tiles.get(5).getBroken()){
            graphs.get(5).getPoly().setFill(Color.BLACK);
        }

        if(tiles.get(6).getBroken()){
            graphs.get(6).getPoly().setFill(Color.BLACK);
        }

        boolean end = true;

        for(int i = 0; i < pandas.size(); i++){
            if(pandas.get(i) != null){
                end = false;
            }
        }

        p1score.setText("Player1 points: " + p1.getPoint());
        p2score.setText("Player2 points: " + p2.getPoint());

        if(end){
            endgame = true;
        }

    }

    /**
     * Vált a játékosok között
     * ha ez az első váltás, a második játékost beállítja arra, hogy teleportált
     * a bejáratra állítja a második játékot, és a firstSwitch értékét false-ra állítja
     * ha az első játékos volt eddig, a player1 értékét false-ra állítja, ellenben true-ra
     * az aktuális játékos mezője és egy szomszédja közé berajzol egy nyilat
     */
    public void SwitchPlayer(){
        if(firstSwitch){
            p2.setTeleported(true);
            en.setAnimal(p2);
            firstSwitch = false;
        }

        Tile t;
        if(player1) {
            player1 = false;
            if(p2.getTile() != null){
                t = p2.getTile();
            }
            else if(p2.getContainer() != null){
                t = p2.getContainer().getTile();
            }
            else{
                t = en.getTile();
            }
        }
        else{
            player1 = true;
            if(p1.getTile() != null){
                t = p1.getTile();
            }
            else if(p1.getContainer() != null){
                t = p1.getContainer().getTile();
            }
            else{
                t = en.getTile();
            }
        }

        Tile actT = t.GetNeighborList().get(0);
        arrow.Create(graphs.get(tiles.indexOf(t)).center, graphs.get(tiles.indexOf(actT)).center,Color.RED);

    }

    /**
     * megadja, hogy a játék véget ért-e
     * @return visszaadja az endgame értékét
     */
    public boolean getEnd(){
        return endgame;
    }

    /**
     * a második játékos pontjának gettere
     * @return visszaadja a második játékos pontjainak értékét
     */
    public int getP1score(){
        return p1.getPoint();
    }

    /**
     * a második játékos pontjának gettere
     * @return visszaadja a második játékos pontjainak értékét
     */
    public int getP2score(){
        return p2.getPoint();
    }
}