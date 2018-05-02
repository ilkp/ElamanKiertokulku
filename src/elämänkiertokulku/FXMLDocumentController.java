/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elämänkiertokulku;

import elämänkiertokulku.simulaatio.Kartta;

import javafx.fxml.FXML;
import javafx.scene.canvas.*;
import javafx.scene.paint.*;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;

/**
 *
 * @author Drsor
 */
public class FXMLDocumentController {
    //Pakolliset
    @FXML
    private Canvas primaryCanvas;
    
    @FXML
    private Label label;
    
    private GraphicsContext gc;
    private double leveys;
    private double korkeus;
    private Color maa, ruoka;
    private int ruokaMaara;
    private Kartta kartta;
    //Testaus
    private Random rnd = new Random();
    
    
    public FXMLDocumentController () {
        //Tarvitaanko Constructori?;
    }
    
    @FXML
    public void alustaKartta(){
        primaryCanvas = new Canvas(640, 640);
        System.out.println("Alusta Kartta");
        maa = Color.rgb(120, 80, 20, 1.0);
        gc.setFill(maa);
        gc.fillRect(0, 0, leveys, korkeus);
        
        
    }
    
    @FXML
    public void piirräKartta () {
        System.out.println("Piirrä Kartta");
        System.out.println(this);
        int ruutuMaara = 24;//kartta.getRuudut().length;
        gc = primaryCanvas.getGraphicsContext2D();
        double ruutuKoko = 640/24;//leveys/ruutuMaara;
        System.out.println("Piirretään " + ruutuMaara + "  " + ruutuKoko);
        for (int i = 0; i < ruutuMaara; i++) {
            for (int j = 0; j < ruutuMaara; j++) {
                ruoka = Color.rgb(20, 200, 0, rnd.nextDouble());
                gc.setFill(ruoka);
                gc.fillRect(i*(ruutuKoko), j*(ruutuKoko), ruutuKoko, ruutuKoko);
            }
        }
        System.out.println(ruoka);
    }

    public double getLeveys() {
        return leveys;
    }

    public void setLeveys(double leveys) {
        this.leveys = leveys;
    }

    public double getKorkeus() {
        return korkeus;
    }

    public void setKorkeus(double korkeus) {
        this.korkeus = korkeus;
    }

    public int getRuokaMaara() {
        return ruokaMaara;
    }

    public void setRuokaMaara(int ruokaMaara) {
        this.ruokaMaara = ruokaMaara;
    }

    public Kartta getKartta() {
        return kartta;
    }

    public void setKartta(Kartta kartta) {
        this.kartta = kartta;
    }
    
    @FXML
    public void alusta () {
        // TODO
        System.out.println("GUI Init");
        gc = primaryCanvas.getGraphicsContext2D();
        leveys = primaryCanvas.getWidth();
        korkeus = primaryCanvas.getHeight();
        /* TESTIT
        System.out.println("PiirräKartta");
        piirräPohja();
        System.out.println("PäivitäKartta");
        piirräKartta();
        */
    }    

}
