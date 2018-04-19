
package elämänkiertokulku.kontrolleri;

import elämänkiertokulku.simulaatio.Elain;
import elämänkiertokulku.simulaatio.Lauma;
import java.util.List;


public class Kello {
    Kontrolleri kontrolleri = new Kontrolleri(3,3);
    protected Runnable helloRunnable = new Runnable() {
/*/
            kontrolleri.getKartta().alusta();
  
            kontrolleri.lisaaLauma(new Lauma(kontrolleri.seuraavaId(), kontrolleri.getKartta().getRuudut()[1][1]));
            
            List<Lauma> laumat = kontrolleri.getLaumat();
            if (laumat.size() == 1){
                    kontrolleri.lisaaLauma(new Lauma(kontrolleri.seuraavaId(), kontrolleri.getKartta().getRuudut()[0][0]));
                }
            laumat.forEach((lauma) -> {
                lauma.lisaaJasen(new Elain(kontrolleri.seuraavaId(), lauma.getRuutu()));
            }
            
            );
            /*/
        @Override
        public void run() {
            kontrolleri.getKartta().piirraKartta();
            }
    };
    public Runnable getRunnable() {
        return this.helloRunnable;
    }

}
