package at.technikumwien.tourplanner;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;

public class TourPlannerApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLDependencyInjection.load("home-view.fxml", Locale.GERMAN );  // Locale.GERMANY, Locale.ENGLISH
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Tour-Planner");
        //primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("at/technikumwien/tourplanner/img/menu_file.png")));
        primaryStage.show();
    }
}
