
package elämänkiertokulku;

import elämänkiertokulku.kontrolleri.Kello;
import elämänkiertokulku.kontrolleri.Kontrolleri;
import elämänkiertokulku.simulaatio.Elain;
import elämänkiertokulku.simulaatio.Kartta;
import elämänkiertokulku.simulaatio.Lauma;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class ElämänKiertokulkuTesti {
    
    public static void main(String[] args) throws InterruptedException{
        Kontrolleri kontrolleri = new Kontrolleri(3, 3);
        
        kontrolleri.getKartta().alusta();
        
        kontrolleri.lisaaLauma(new Lauma(kontrolleri.seuraavaId(), kontrolleri.getKartta().getRuudut()[0][0], kontrolleri.getKartta().getRuudut()));
        kontrolleri.lisaaLauma(new Lauma(kontrolleri.seuraavaId(), kontrolleri.getKartta().getRuudut()[1][1], kontrolleri.getKartta().getRuudut()));
        
        List<Lauma> laumat = kontrolleri.getLaumat();
        
        laumat.forEach((lauma) -> {
            lauma.lisaaJasen(new Elain(kontrolleri.seuraavaId(), lauma.getRuutu(), lauma));
        });
        
        kontrolleri.getKartta().piirraKartta();
        
        Kello kello = new Kello();
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(kello.getRunnable(), 0, 1, TimeUnit.SECONDS);
        
    }
}
