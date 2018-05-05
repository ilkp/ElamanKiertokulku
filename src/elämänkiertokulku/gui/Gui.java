/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elämänkiertokulku.gui;

import elämänkiertokulku.simulaatio.Kartta;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.Random;
import javafx.fxml.FXML;
import javafx.scene.Group;

/**
 *
 * @author Drsor
 */
public class Gui extends Application {
    
    private Kartta kartta;

    public Kartta getKartta() {
        return kartta;
    }

    public void setKartta(Kartta kartta) {
        this.kartta = kartta;
    }
    
    private GraphicsContext gc;
    private Canvas primaryCanvas;
    private double leveys;
    private double korkeus;
    
    public static Random rnd = new Random ();
    
    @Override
    public void start(Stage primaryStage) {
        
        Group root = new Group();
        Scene scene = new Scene(root, 640, 700);
        System.out.println("GUI Init");
        primaryCanvas = new Canvas(640, 640);
        root.getChildren().add(primaryCanvas);
        
        alustaKartta();
        piirräKartta();
        
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    
    public void alustaKartta() {
        gc = primaryCanvas.getGraphicsContext2D();
        leveys = primaryCanvas.getWidth();
        korkeus = primaryCanvas.getHeight();
        System.out.println(leveys);
        System.out.println(korkeus);
        System.out.println("Alusta Kartta");
        Color maa = Color.rgb(120, 80, 20, 1.0);
        gc.setFill(maa);
        gc.fillRect(0, 0, leveys, korkeus); 
    }
    
    
    public void piirräKartta () {
        System.out.println("Piirrä Kartta");
        Color ruoka;
        int ruutuMaara = 36;//kartta.getRuudut().length;
        double ruutuKoko = leveys/ruutuMaara;//leveys/ruutuMaara;
        System.out.println("Piirretään " + ruutuMaara + "  " + ruutuKoko);
        for (int i = 0; i < ruutuMaara; i++) {
            for (int j = 0; j < ruutuMaara; j++) {
                ruoka = Color.rgb(20, 200, 0, rnd.nextDouble());
                gc.setFill(ruoka);
                gc.fillRect(i*(ruutuKoko), j*(ruutuKoko), ruutuKoko, ruutuKoko);
            }
        }
    }
    
}
