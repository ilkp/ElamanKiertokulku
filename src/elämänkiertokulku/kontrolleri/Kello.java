
package elämänkiertokulku.kontrolleri;

import elämänkiertokulku.simulaatio.Lauma;

/*
EI KÄYTÖSSÄ, KÄYTETÄÄN UUTTA TAPAA -> Timer
Timer löytyy mainista ElämänKiertokulkuTesti luokasta
Kellon tikillä tapahtuva ajo on luokan TimerAjo metodissa run()
*/

public class Kello {
    private Kontrolleri kontrolleri;
    private Runnable helloRunnable;

    
    public Kello (Kontrolleri kontrolleri) {
        this.kontrolleri = kontrolleri;
        helloRunnable = () -> {
            kontrolleri.ajaLaumat();
            Lauma lauma = (Lauma) kontrolleri.getLaumat().get(0);
            kontrolleri.getKartta().piirraKartta();
            
            
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
        };
    }
    
    public Runnable getRunnable() {
        return this.helloRunnable;
    }

}
