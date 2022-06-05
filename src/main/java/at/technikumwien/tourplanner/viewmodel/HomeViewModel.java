package at.technikumwien.tourplanner.viewmodel;

import at.technikumwien.tourplanner.BL.DAL.model.TourModel;
import at.technikumwien.tourplanner.BL.managers.TourManager;
import at.technikumwien.tourplanner.config.Configuration;
import at.technikumwien.tourplanner.view.AddTourViewController;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class HomeViewModel {

    private final StringProperty input = new SimpleStringProperty();
    private final BooleanProperty enabled = new SimpleBooleanProperty();
    private final ObjectProperty<TourModel> currenTourModel = new SimpleObjectProperty<>();
    private ObservableList<TourModel> tourList;
    private TourManager manager;

    private final StringProperty tourDescription = new SimpleStringProperty("");
    private final StringProperty tourDistance = new SimpleStringProperty("");
    private final StringProperty tourOrigin = new SimpleStringProperty("");
    private final StringProperty tourDestination = new SimpleStringProperty("");
    private final StringProperty tourTransportation = new SimpleStringProperty("");
    private final StringProperty tourName = new SimpleStringProperty("");
    private final StringProperty tourDuration = new SimpleStringProperty("");
    private final ObjectProperty<Image> tourImage = new SimpleObjectProperty<>();

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
        try {
            Parent root =  (Parent) loader.load();
            var scene = new Scene(root);
            AddTourViewController addTourViewController = loader.getController();
            addTourViewController.initData(manager);
            tourStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
        tourStage.initModality(Modality.APPLICATION_MODAL);
        tourStage.show();
    }

    public void setuplist() {
        tourList = FXCollections.observableArrayList();
        tourList.clear();
        tourList.addAll(manager.getAllTours());
    }

    public ObjectProperty<TourModel> getCurrentTourModel() {
        return currenTourModel;
    }



    public void changeTourView() {
        System.out.println("abc:" + currenTourModel.get().getNameTest());
        tourDescription.set(currenTourModel.get().getDescription());
        System.out.println("bfc: " + tourDescription);
        tourName.set(currenTourModel.get().getNameTest());
        tourOrigin.set(currenTourModel.get().getLocationfrom());
        tourDestination.set(currenTourModel.get().getLocationto());
        tourTransportation.set(currenTourModel.get().getTransporttype());
        tourDistance.set(currenTourModel.get().getTotaldistance());
        tourDuration.set(currenTourModel.get().getTotalduration());
        System.out.println(currenTourModel);
        try {
            Configuration configuration =Configuration.Instance();
            File dir = new File(configuration.getProperty("imgdir"));
            File[] directoryListing = dir.listFiles();
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    if (child.getName().contains(tourName.toString()))
                    {
                        var img = new Image(child.toURI().toString());
                        tourImage.set(img);
                        break;
                    }

                }
            } else {
                System.out.println("No Image found");
                // insert no img found placeholder
            }
        }
        catch (Exception e){

        }
    }

    public ObservableList<TourModel> getTourList() {
        return tourList;
    }

    public StringProperty getCurrentTourModelName() {
        System.out.println(tourName);
        return tourName;
    }

    public StringProperty getCurrentTourModelSummary() {
        StringProperty summary = new SimpleStringProperty("From " + tourOrigin.get() + " to " +
                            tourDestination.get() + " with " +
                            tourTransportation.get() + "\nDuration: " +
                            tourDuration.get() + " over " + tourDistance.get() +
                            "km\nDescription" + tourDescription.get());
        System.out.println(summary);
        return summary;
    }
}
