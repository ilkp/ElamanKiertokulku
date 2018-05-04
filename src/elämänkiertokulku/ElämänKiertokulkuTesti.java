
package elämänkiertokulku;

import elämänkiertokulku.kontrolleri.Kello;
import elämänkiertokulku.kontrolleri.Kontrolleri;
import elämänkiertokulku.simulaatio.Kasvinsyoja;
import elämänkiertokulku.simulaatio.LaumaKasvinsyoja;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class ElämänKiertokulkuTesti {
    
    public static void main(String[] args) throws InterruptedException {
        
        Kontrolleri kontrolleri = new Kontrolleri(10, 10);
        
        kontrolleri.getKartta().alusta();
        LaumaKasvinsyoja testilauma = new LaumaKasvinsyoja(kontrolleri, kontrolleri.seuraavaId(), 10, kontrolleri.getKartta().getRuudut()[4][4]);
        testilauma.lisaaJasen(new Kasvinsyoja(kontrolleri, kontrolleri.seuraavaId(), 10, testilauma.getOmaRuutu(), testilauma));
        testilauma.getJasenet().get(0).setRuokatilanne(40);
        
        kontrolleri.lisaaLauma(testilauma);
        
        kontrolleri.getKartta().piirraKartta();
        
        Kello kello = new Kello(kontrolleri);
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(kello.getRunnable(), 0, 1, TimeUnit.SECONDS);
        
    }
}
