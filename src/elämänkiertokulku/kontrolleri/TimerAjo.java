
package elämänkiertokulku.kontrolleri;

import elämänkiertokulku.simulaatio.Kasvinsyoja;
import elämänkiertokulku.simulaatio.Lauma;
import elämänkiertokulku.simulaatio.LaumaKasvinsyoja;
import java.util.TimerTask;

import elämänkiertokulku.gui.Gui;
import elämänkiertokulku.simulaatio.Elain;

public class TimerAjo extends TimerTask {
    private final Kontrolleri kontrolleri;
    
    private final Gui gui;
    
    //Javafx on ihme ympäristö joka ei tykkää ku joku
    //toinen thread yrittää käynnistää sitä :(
    //Siksipä laitoin timerin alkaa guista lol xd
    public TimerAjo(Gui newGui) {
        kontrolleri = new Kontrolleri(20, 20);
        kontrolleri.getKartta().alusta();
        
        LaumaKasvinsyoja testilauma = new LaumaKasvinsyoja(kontrolleri, kontrolleri.seuraavaId(), 10, kontrolleri.getKartta().getRuudut()[4][4]);
        testilauma.lisaaJasen(new Kasvinsyoja(kontrolleri, kontrolleri.seuraavaId(), 10, testilauma.getOmaRuutu(), testilauma));
        testilauma.lisaaJasen(new Kasvinsyoja(kontrolleri, kontrolleri.seuraavaId(), 10, kontrolleri.getKartta().getRuudut()[4][7], testilauma));
        
        kontrolleri.lisaaLauma(testilauma);
        
        gui = newGui;
        gui.setKartta(kontrolleri.getKartta());
        System.out.println("Alustetaan Kartta");
        gui.alustaKartta();
    }
    
    @Override
    public void run() {
        //try {
            kontrolleri.ajaLaumat();
            Lauma lauma = (Lauma) kontrolleri.getLaumat().get(0);
            gui.piirräKartta();
            this.kontrolleri.tikkaa();
            System.out.println(this.kontrolleri.getTick());
            kontrolleri.ajaRuudut();
        /*} catch (Exception e) {
            System.out.println(e);
        }*/
    }
    
}
