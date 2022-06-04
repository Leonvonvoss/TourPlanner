package at.technikumwien.tourplanner.viewmodel;

import at.technikumwien.tourplanner.BL.services.TourManager;
import at.technikumwien.tourplanner.view.AddTourViewController;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeViewModel {

    private final StringProperty input = new SimpleStringProperty();
    private final BooleanProperty enabled = new SimpleBooleanProperty();
    private TourManager manager;

    public HomeViewModel() {
        enabled.bind(input.isEmpty());
    }

    public StringProperty inputProperty() {
        return input;
    }

    public BooleanProperty enabledProperty() {
        return enabled;
    }

    public void setup(TourManager manager) {
        this.manager = manager;
    }

    public void onTopVBoxButtonAddTourClick() {
        var tourStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("at/technikumwien/tourplanner/fxml/AddTour-View.fxml"));

        System.out.println("Test3");
        try {
            Parent root =  (Parent) loader.load();
            var scene = new Scene(root);
            AddTourViewController addTourViewController = loader.getController();
            addTourViewController.initData(manager);
            tourStage.setScene(scene);
            System.out.println("Test5");
        } catch (IOException e) {
            e.printStackTrace();
        }
        tourStage.initModality(Modality.APPLICATION_MODAL);
        System.out.println("Test6");
        tourStage.show();
        System.out.println("Test7");
    }
}
