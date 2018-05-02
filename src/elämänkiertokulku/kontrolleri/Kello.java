
package elämänkiertokulku.kontrolleri;

import elämänkiertokulku.simulaatio.Elain;
import elämänkiertokulku.simulaatio.Lauma;
import java.util.List;


public class Kello {
    Kontrolleri kontrolleri = new Kontrolleri(3,3);
    Runnable helloRunnable = () -> {
        System.out.println("hello");
    };
    public Runnable getRunnable() {
        return this.helloRunnable;
    }

}
