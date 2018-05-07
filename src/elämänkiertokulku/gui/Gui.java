/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elämänkiertokulku.gui;

import elämänkiertokulku.kartta.Kartta;
import elämänkiertokulku.kontrolleri.TimerAjo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.Timer;
import java.util.TimerTask;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;


public class Gui extends Application {
    
    private Kartta kartta;

    public void setKartta(Kartta kartta) {
        this.kartta = kartta;
    }
    
    //Gui Painikkeett ja jne.
    private Slider nopeusSäätö;
    private Button pause;
    private Button play;
    //Tarvitaanko / Halutaanko ?
    private Button lisääKasvisSyöjäLauma;
    private Button lisääLihanSyöjäLauma;
    private Button lisääKasvisSyöjä;
    private Button lisääLihanSyöjä;
    
    //Gui piirron muuttujat
    private GraphicsContext gc;
    private Canvas primaryCanvas;
    private Canvas backgroundCanvas;
    private Pane controlPane;
    private double leveys;
    private double korkeus;
    private final Image kasvisElain = new Image("/elämänkiertokulku/gui/sheep.png");
    private final Image lihaElain = new Image("/elämänkiertokulku/gui/wolf.png");
    private final Image lihaRuoka = new Image("/elämänkiertokulku/gui/Liharuoka.png");
    
    public TimerTask timerTask;
    public Timer timer;
    
    //javafx käyttää start methodia main sijasta
    //Pitää selvittää miten toimii mainissa jos ollenkaa
    //Shift+F6 jos haluu ajaa
    //Clean and build joka muokkauksen jälkee ku 
    //NEtbeans on muchos huevos;
    @Override
    public void start(Stage primaryStage) {
        //Luodaan root ja scene joille gui palat
        //asetetaan
        Group root = new Group();
        Scene scene = new Scene(root, 640, 740);
        System.out.println("GUI Init");
        
        //Luodan Canvakset joille gui
        //piirretään sekä asetetaan ne rootille
        primaryCanvas = new Canvas(640, 640);
        backgroundCanvas = new Canvas (640, 640);
        root.getChildren().add(backgroundCanvas);
        root.getChildren().add(primaryCanvas);
        
        //Täytetään kartta tarvittavilla painikkeilla
        //asetettuna panelle
        controlPane = new Pane();
        controlPane.setPrefSize(640, 100);
        controlPane.setLayoutY(640);
        controlPane.setStyle("-fx-background-color: gray;");
        root.getChildren().add(controlPane);
        
        teePause();
        teePlay();
        teeSlider();
        
        primaryStage.setScene(scene);
        primaryStage.show();
        
        timerTask = new TimerAjo(this);
        play();
    }
    
    public static void main(String[] args) throws InterruptedException {
        launch(args);
    }
    
    public void pause () {
        timer.cancel();
    }
    
    public void play () {
        timer = new Timer();
        //long nopeus = 1000 - ((long)nopeusSäätö.getValue()*100-100);
        long nopeus = 100;
        timer.scheduleAtFixedRate(timerTask, 0, nopeus);
        
    }
    
    private void teePause () {
        pause = new Button("Pause Me!");
        pause.setPrefSize(100, 40);
        pause.setLayoutX(40);
        pause.setLayoutY(30);
        pause.setOnAction((ActionEvent event) -> {
            //pause();
        });
        controlPane.getChildren().add(pause);
    }
    
    private void teePlay() {
        play = new Button("Play Me!");
        play.setPrefSize(100, 40);
        play.setLayoutX(180);
        play.setLayoutY(30);
        play.setOnAction((ActionEvent event) -> {
            //play();
        });
        controlPane.getChildren().add(play);
    }
    
    private void teeSlider () {
        nopeusSäätö = new Slider();
        nopeusSäätö.setMin(1);
        nopeusSäätö.setMax(10);
        nopeusSäätö.setValue(1);
        nopeusSäätö.setShowTickLabels(true);
        nopeusSäätö.setShowTickMarks(true);
        nopeusSäätö.setSnapToTicks(true);
        nopeusSäätö.setMajorTickUnit(1);
        nopeusSäätö.setPrefSize(280, 20);
        nopeusSäätö.setLayoutX(320);
        nopeusSäätö.setLayoutY(40);
        controlPane.getChildren().add(nopeusSäätö);
    }
    
    //Asetetaan kartan piirturi ja speksit
    //Piirretään kartan pohja mikä on 0 ruoan värinen
    public void alustaKartta() {
        leveys = primaryCanvas.getWidth();
        korkeus = primaryCanvas.getHeight();
        System.out.println("Alusta Kartta");
        
        gc = backgroundCanvas.getGraphicsContext2D();
        Color maa = Color.rgb(120, 80, 20, 1.0);
        gc.setFill(maa);
        gc.fillRect(0, 0, leveys, korkeus);
        gc = primaryCanvas.getGraphicsContext2D();
        
    }
    
    //Kartan piirto/päivitys methodi
    //käydään ruudut läpi ja piirretään niiden ruoka määrä
    public void piirräKartta () {
        
        gc.clearRect(0, 0, leveys, korkeus);
        int ruutuMaara = kartta.getRuudut().length;
        double ruutuKoko = leveys/ruutuMaara;
        for (int i = 0; i < ruutuMaara; i++) {
            for (int j = 0; j < ruutuMaara; j++) {
                piirräKasviRuoka(i, j, ruutuKoko, ruokaPerKymmenen(kartta.getRuudut()[i][j].getKasviruoka()));
                piirräLihaRuoka(i, j, ruutuKoko, ruokaPerKymmenen(kartta.getRuudut()[i][j].getLiharuoka()));
                if (!kartta.getRuudut()[i][j].getElaimet().isEmpty()) {
                    piirräKasvisEläin (i, j, ruutuKoko);
                }
                if (!kartta.getRuudut()[i][j].getLaumat().isEmpty()) {
                    debugPiirräLauma (i, j, ruutuKoko);
                }
            }
        }
    }
    
    private void debugPiirräLauma (int i, int j, double ruutuKoko) {
        Color lauma = Color.rgb(255, 255, 255, 1.0);
        gc.setFill(lauma);
        gc.fillRect(i*(ruutuKoko), j*(ruutuKoko), ruutuKoko/2, ruutuKoko/2);
    }
    
    //Korja tämä se palauttaa aina nolla tai yks
    private double ruokaPerKymmenen (double ruoka) {
        //return ((ruoka+5)/10)*10/100;
        return (double)(ruoka/100);
    }
    
    //Piirto methodit enemmä tai vähemmä ittestää selviä eikö?
    private void piirräKasviRuoka (int i, int j, double ruutuKoko, double ruoka) {
        //Asetetaan ruoka 0 sillä gc ei voi piirtää
        //negatiivisia lukuja!!
        if (ruoka < 0) {
            ruoka = 0;
        }
        Color ruokaColor = Color.rgb(20, 200, 20, ruoka);
        gc.setFill(ruokaColor);
        gc.fillRect(i*(ruutuKoko), j*(ruutuKoko), ruutuKoko, ruutuKoko);
            
    }
    private void piirräLihaRuoka (int i, int j, double ruutuKoko, double ruoka) {
        //Asetetaan ruoka 0 sillä gc ei voi piirtää
        //negatiivisia lukuja!!
        if (ruoka < 0) {
            ruoka = 0;
        }
        gc.setGlobalAlpha(ruoka);
        gc.drawImage(lihaRuoka, i*(ruutuKoko), j*(ruutuKoko), ruutuKoko, ruutuKoko);
        gc.setGlobalAlpha(1.0);
    }
    
    private void piirräKasvisEläin (int i, int j, double ruutuKoko) {
        gc.drawImage(kasvisElain, i*(ruutuKoko), j*(ruutuKoko), ruutuKoko, ruutuKoko);
    }
    
    private void piirräLihanEläin (int i, int j, double ruutuKoko) {
        gc.drawImage(lihaElain, i*(ruutuKoko), j*(ruutuKoko), ruutuKoko, ruutuKoko);
    }
    
}
