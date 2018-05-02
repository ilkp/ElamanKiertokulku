
package elämänkiertokulku;

import elämänkiertokulku.kontrolleri.Kello;
import elämänkiertokulku.kontrolleri.Kontrolleri;
import elämänkiertokulku.simulaatio.LaumaKasvinsyoja;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class ElämänKiertokulkuTesti {
    
    public static void main(String[] args) throws InterruptedException {
        
        Kontrolleri kontrolleri = new Kontrolleri(10, 10);
        
        kontrolleri.getKartta().alusta();
        kontrolleri.getKartta().getRuudut()[9][9].setKasviruoka(100);
        LaumaKasvinsyoja testiLauma = new LaumaKasvinsyoja(kontrolleri.seuraavaId(), kontrolleri.getKartta().getRuudut()[4][4], kontrolleri.getKartta().getRuudut());
        
        kontrolleri.lisaaLauma(testiLauma);
        
        kontrolleri.getKartta().piirraKartta();
        
        Kello kello = new Kello(kontrolleri);
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(kello.getRunnable(), 0, 1, TimeUnit.SECONDS);
        
    }
}
