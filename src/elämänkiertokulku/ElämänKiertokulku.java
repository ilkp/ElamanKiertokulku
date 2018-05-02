
package elämänkiertokulku;

import elämänkiertokulku.kontrolleri.Kello;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
//Moi T AKu!
//Moi T Ile
public class ElämänKiertokulku extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
        Kello kello = new Kello();
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(kello.getRunnable(), 0, 1, TimeUnit.SECONDS);
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
