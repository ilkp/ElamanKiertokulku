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
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;


public class Gui extends Application {
    
    private Kartta kartta;
    
//    public Gui (Kartta kartta) {
//        this.kartta = kartta;
//    }

    public Kartta getKartta() {
        return kartta;
    }

    public void setKartta(Kartta kartta) {
        this.kartta = kartta;
    }
    
    //Gui Painikkeett ja jne.
    private Slider nopeusSäätö;
    private Button pause;
    //Tarvitaanko / Halutaanko ?
    private Button lisääKasvisSyöjäLauma;
    private Button lisääLihanSyöjäLauma;
    private Button lisääKasvisSyöjä;
    private Button lisääLihanSyöjä;
    
    //Gui piirron muuttujat
    private GraphicsContext gc;
    private Canvas primaryCanvas;
    private Canvas backgroundCanvas;
    private double leveys;
    private double korkeus;
    private Image kasvisElain = new Image("/elämänkiertokulku/gui/sheep.png");
    private Image lihaElain = new Image("/elämänkiertokulku/gui/wolf.png");
    //BenchMark
    private int päivitysKerta;
    
    //javafx käyttää start methodia main sijasta
    //Pitää selvittää miten toimii mainissa jos ollenkaa
    //Shift+F6 jos haluu ajaa
    //Clean and build joka muokkauksen jälkee ku 
    //NEtbeans on muchos huevos;
    @Override
    public void start(Stage primaryStage) {
        
        Group root = new Group();
        Scene scene = new Scene(root, 640, 740);
        System.out.println("GUI Init");
        primaryCanvas = new Canvas(640, 640);
        backgroundCanvas = new Canvas (640, 640);
        root.getChildren().add(backgroundCanvas);
        root.getChildren().add(primaryCanvas);

        primaryStage.setScene(scene);
        primaryStage.show();
        
        TimerTask timerTask = new TimerAjo(this);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(timerTask, 0, 100);
        
    }
    
    public static void main(String[] args) throws InterruptedException {
        launch(args);
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
        System.out.println("Päivitetään Kartta   " + päivitysKerta++);
        int ruutuMaara = kartta.getRuudut().length;
        double ruutuKoko = leveys/ruutuMaara;
        for (int i = 0; i < ruutuMaara; i++) {
            for (int j = 0; j < ruutuMaara; j++) {
                piirräKasviRuoka(i, j, ruutuKoko, ruokaPerKymmenen(kartta.getRuudut()[i][j].getKasviruoka()));
                piirräLihaRuoka(i, j, ruutuKoko, ruokaPerKymmenen(kartta.getRuudut()[i][j].getLiharuoka()));
                if (!kartta.getRuudut()[i][j].getElaimet().isEmpty()) {
                    piirräKasvisEläin (i, j, ruutuKoko);
                }
            }
        }
    }
    //Korja tämä se palauttaa aina nolla tai yks
    private double ruokaPerKymmenen (double ruoka) {
        //return ((ruoka+5)/10)*10/100;
        return (double)ruoka/100;
    }
    
    //Piirto methodit enemmä tai vähemmä ittestää selviä eikö?
    private void piirräKasviRuoka (int i, int j, double ruutuKoko, double ruoka) {
        Color ruokaColor = Color.rgb(20, 200, 20, ruoka);
        gc.setFill(ruokaColor);
        gc.fillRect(i*(ruutuKoko), j*(ruutuKoko), ruutuKoko, ruutuKoko);
            
    }
    private void piirräLihaRuoka (int i, int j, double ruutuKoko, double ruoka) {
        Color ruokaColor = Color.rgb(200, 20, 20, ruoka);
        gc.setStroke(ruokaColor);
        gc.setLineWidth(ruutuKoko / 5);
        gc.strokeOval(i*(ruutuKoko), j*(ruutuKoko), ruutuKoko, ruutuKoko);
            
    }
    
    private void piirräKasvisEläin (int i, int j, double ruutuKoko) {
        gc.drawImage(kasvisElain, i*(ruutuKoko), j*(ruutuKoko), ruutuKoko, ruutuKoko);
    }
    
    private void piirräLihanEläin (int i, int j, double ruutuKoko) {
        gc.drawImage(lihaElain, i*(ruutuKoko), j*(ruutuKoko), ruutuKoko, ruutuKoko);
    }
    
}
