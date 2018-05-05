
package elämänkiertokulku.kontrolleri;

import elämänkiertokulku.simulaatio.Kasvinsyoja;
import elämänkiertokulku.simulaatio.Lauma;
import elämänkiertokulku.simulaatio.LaumaKasvinsyoja;
import java.util.TimerTask;

import elämänkiertokulku.gui.Gui;

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
        testilauma.lisaaJasen(new Kasvinsyoja(kontrolleri, kontrolleri.seuraavaId(), 10, testilauma.getOmaRuutu(), testilauma));
        testilauma.getJasenet().get(0).setRuokatilanne(40);
        kontrolleri.lisaaLauma(testilauma);
        gui = newGui;
        gui.setKartta(kontrolleri.getKartta());
        System.out.println("Alustetaan Kartta");
        gui.alustaKartta();
    }
    
    @Override
    public void run() {
        try {
            kontrolleri.ajaLaumat();
            Lauma lauma = (Lauma) kontrolleri.getLaumat().get(0);
            kontrolleri.getKartta().piirraKartta();
            
            gui.piirräKartta();
            
            System.out.println("Lauman x: "+lauma.getOmaRuutu().getxKoord() + ",  y: " + lauma.getOmaRuutu().getyKoord());
            System.out.println("Lauman tavoite: "+lauma.getTavoite());
            System.out.println("Lauman tavoiteruutu: "+lauma.getTavoiteRuutu());
            System.out.println("liike vaihe: "+lauma.getLiikeVaihe());
            System.out.println("");
            
            System.out.println("Eläimen x: "+lauma.getJasenet().get(0).getOmaRuutu().getxKoord() + ",  y: " + lauma.getJasenet().get(0).getOmaRuutu().getyKoord());
            System.out.println("Eläimen tavoite: "+lauma.getJasenet().get(0).getTavoite());
            System.out.println("Eläimen tavoiteruutu: "+lauma.getJasenet().get(0).getTavoiteRuutu());
            System.out.println("liike vaihe: "+lauma.getJasenet().get(0).getLiikeVaihe());
            System.out.println("Ruokatilanne: "+lauma.getJasenet().get(0).getRuokatilanne());
            
            this.kontrolleri.tikkaa();
            System.out.println(this.kontrolleri.getTick());
        } catch (Exception e) {
            
        }
    }
    
}
