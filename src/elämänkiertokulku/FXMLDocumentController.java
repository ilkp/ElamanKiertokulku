/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elämänkiertokulku;

import elämänkiertokulku.simulaatio.Kartta;
import elämänkiertokulku.simulaatio.Ruutu;

import javafx.fxml.FXML;
import javafx.scene.canvas.*;
import javafx.scene.paint.*;
import java.util.Random;

/**
 *
 * @author Drsor
 */
public class FXMLDocumentController {
    
    //Pakolliset
    @FXML
    private Canvas primaryCanvas;
    private GraphicsContext gc;
    private double width;
    private double height;

    //Testaus
    private Random rnd = new Random();
    
    @FXML
    public void piirräKartta(/*Kartta kartta*/){
        System.out.println("Init");
        Color ground = Color.rgb(120, 80, 20, 1.0);
        
        System.out.println("Kartta");
        gc.setFill(ground);
        gc.fillRect(0, 0, width, height);
        

    }
    
    public void päivitäKartta () {
        Color food;
        int amount = 36;
        double spacing = width/amount;
        Ruutu[][] ruudut = new Ruutu[amount][amount];
        
        for (int i = 0; i < ruudut.length; i++) {
            for (int j = 0; j < ruudut.length; j++) {
                food = Color.rgb(20, 200, 0/*,TÄYTÄMINUT*/);
                gc.setFill(food);
                gc.fillRect(i*(width/amount), j*(width/amount), width/amount, width/amount);
            }
        }
    }

    public void initialize() {
        // TODO
        //System.out.println("Drawing grid");
        //createGrid();
        gc = primaryCanvas.getGraphicsContext2D();
        width = primaryCanvas.getWidth();
        height = primaryCanvas.getHeight();
        
        System.out.println("PiirräKartta");
        piirräKartta();
        päivitäKartta();
        
    }    
    
}
