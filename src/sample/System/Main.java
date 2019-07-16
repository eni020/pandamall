package sample.System;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;
import sample.Animals.*;
import sample.Objects.Arcademachine;
import sample.Objects.Armchair;
import sample.Objects.Object;
import sample.Objects.Vendingmachine;
import sample.Tiles.*;
import sample.InObjects.*;
import sample.Graphics.*;

import javafx.application.Application;

/**
 * A Main osztály
 */
public class Main extends Application {

    /**
     * A playState tárolja, hogy a játékon belül vagyunk-e
     * Az init tárolja, hogy az inicializálás fázisban vagyunk-e
     */
    static boolean playState = false;
    static boolean init = false;

    /**
     * A tiles tárolja a játék csempéinek listáját
     * A pandas tárolja a játék pandáinak listáját
     * Az objects tárolja a játék tárgyainak listáját
     * A wardrobes tárolja a játék szekrényeinek listáját
     * A p1 tárolja az egyik  orángutánt
     * A p2 tárolja a másik orángutánt
     * Az en tárolja a játék bejáratát
     * Az ex tárolja a játék kijáratát
     */
    static ArrayList<Tile> tiles = new ArrayList<>();
    static ArrayList<Panda> pandas = new ArrayList<>();
    static ArrayList<Object> objects = new ArrayList<>();
    static ArrayList<Wardrobe> wardrobes = new ArrayList<>();
    static Ape p1 = null;
    static Ape p2 = null;
    static Entry en = null;
    static Exit ex = null;
    BorderPane layout;
    String lastwinner = "";
    

    static MapGenerator mg;
    static boolean generate = false;
    /**
     * A baos-ba kerül majd a tesztbemenetek kimenete
     */
    static ByteArrayOutputStream baos = new ByteArrayOutputStream();

    /**
     * A main függvény, beolvassa a parancsokat és azoknak megfelelően működik
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {//Ebből a függvényből lehet kezelni a játék előtti menüt

        primaryStage.setTitle("Start screen");

        //Az ablak mérete
        int width = 960;//1920;
        int height = 580;//1080;

        Label winner = new Label();
        Button start = new Button();
        Button exit = new Button();

        winner.setText(lastwinner);

        //A gomboknak adunk egy méretet, mert alapból nagyon kicsik
        start.setMinSize(100, 50);
        exit.setMinSize(100, 50);

        start.setText("Start");
        exit.setText("Exit");
        primaryStage.setResizable(false);

        //A játék indító gombja
        start.setOnAction(click -> {
            primaryStage.close();
            gameplay(primaryStage);
        });

        //A program leállító gombja
        exit.setOnAction(click -> {
            primaryStage.close();
        });

        //A gombok és a legutóbbi győztest kiíró Label elhelyezése
        layout = new BorderPane();
        layout.setPadding(new Insets(100));
        layout.setLeft(start);
        layout.setCenter(winner);
        layout.setRight(exit);

        //A megjelenítés
        Scene scene = new Scene(layout, width, height);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

        //Ez felelős a játék futtatásáért
    public void gameplay(Stage primaryStage) {


        primaryStage.setTitle("Game screen");

        //A group segít a játék grafikai megjelenítésében
        Group g = new Group();

        //kijelző szélessége és magassága
        int width = 960;//1920;
        int height = 580;//1080;

        //egy újraindító és egy kikapcsoló gomb
        MenuItem restart = new MenuItem("Restart");
        MenuItem exit = new MenuItem("Exit");

        //egy menü amibe a gombok kerülnek
        Menu menu = new Menu("Menu");

        //A gombok a menühöz adása
        menu.getItems().add(exit);
        menu.getItems().add(restart);

        //újraindító gomb funkciója
        restart.setOnAction(click -> {
            primaryStage.close();
            start(primaryStage);
        });

        //kikapcsoló gomb funkciója
        exit.setOnAction(click -> {
            primaryStage.close();
        });

        //menücsík amire a menüt rakjuk
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(menu);

        //A menücsík és a group elhelyezése
        layout = new BorderPane();
        layout.setTop(menuBar);
        layout.setCenter(g);

        //scene beállítások
        Scene scene = new Scene(layout, width, height);
        primaryStage.setScene(scene);
        primaryStage.show();

        //a map elgenerálása
        mg = new MapGenerator();
        mg.startGenerate(g);

        //billentyűkre való feliratkozás
        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if (key.getCode() == KeyCode.LEFT) {
                mg.RotateArrowLeft();
            }
            if (key.getCode() == KeyCode.RIGHT) {
                mg.RotateArrowRight();
            }
            if (key.getCode() == KeyCode.SPACE) {
                mg.ApeStep();
                mg.SwitchPlayer();
                if (mg.getEnd()){
                    primaryStage.close();

                    //minden kör végén ellenőrzi hogy van-e még panda és ha nincs, a start menüre dob és kiírja hogy ki nyert
                    if(mg.getP2score() == mg.getP1score())
                        lastwinner = "It was a draw";
                    else if(mg.getP1score() > mg.getP2score())
                        lastwinner = "Player 1 won the game";
                    else
                        lastwinner = "Player 2 won the game";

                    start(primaryStage);
                }
            }
            if(key.getCode()== KeyCode.ENTER){
                mg.ReleasePandas();
                mg.Update();
                mg.SwitchPlayer();
            }
        });

    }

}
