package at.technikumwien.tourplanner.viewmodel;

import at.technikumwien.tourplanner.BL.DAL.model.TourLog;
import at.technikumwien.tourplanner.BL.DAL.model.TourModel;
import at.technikumwien.tourplanner.BL.managers.TourManager;
import at.technikumwien.tourplanner.config.Configuration;
import at.technikumwien.tourplanner.view.AddLogViewController;
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
import org.apache.logging.log4j.LogManager;
import org.slf4j.event.Level;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class HomeViewModel {

    private final StringProperty input = new SimpleStringProperty();
    private final BooleanProperty enabled = new SimpleBooleanProperty();
    private final ObjectProperty<TourModel> currenTourModel = new SimpleObjectProperty<>();
    private ObservableList<TourModel> tourList;
    private ObservableList<TourLog> logList;
    private TourManager manager;

    private final StringProperty tourDescription = new SimpleStringProperty("");
    private final StringProperty tourDistance = new SimpleStringProperty("");
    private final StringProperty tourOrigin = new SimpleStringProperty("");
    private final StringProperty tourDestination = new SimpleStringProperty("");
    private final StringProperty tourTransportation = new SimpleStringProperty("");
    private final StringProperty tourName = new SimpleStringProperty("");
    private final StringProperty tourDuration = new SimpleStringProperty("");
    private StringProperty tourSummary = new SimpleStringProperty("");
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
        Configuration configuration =Configuration.Instance();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource(configuration.getProperty("addTour")));
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

    public void onTopVBoxButtonAddLogClick() {
        var tourStage = new Stage();
        Configuration configuration =Configuration.Instance();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource(configuration.getProperty("addLog")));
        try {
            Parent root =  (Parent) loader.load();
            var scene = new Scene(root);
            AddLogViewController addLogViewController = loader.getController();
            addLogViewController.initData(manager, currenTourModel.get().getTourId());
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

    public void setupLogList() {
        logList = FXCollections.observableArrayList();
        logList.clear();
        logList.addAll(manager.getTourLogs(currenTourModel.get().getTourId()));
    }

    public ObjectProperty<TourModel> getCurrentTourModel() {
        return currenTourModel;
    }



    public void changeTourView() {
        tourName.set(currenTourModel.get().getNameTest());
        tourDescription.set(currenTourModel.get().getDescription());
        tourOrigin.set(currenTourModel.get().getLocationfrom());
        tourDestination.set(currenTourModel.get().getLocationto());
        tourTransportation.set(currenTourModel.get().getTransporttype());
        tourDistance.set(currenTourModel.get().getTotaldistance());
        tourDuration.set(currenTourModel.get().getTotalduration());
        tourSummary.set(this.createTourSummary());
        try {
            Configuration configuration =Configuration.Instance();
            File dir = new File(configuration.getProperty("imgdir"));
            File[] directoryListing = dir.listFiles();
            if (directoryListing != null) {
                boolean imgfound = false;
                for (File child : directoryListing) {
                    if (child.getName().contains(tourName.get())) {
                        var img = new Image(child.toURI().toString());
                        tourImage.set(img);
                        imgfound = true;
                        break;
                    }
                }
                if (!imgfound) {
                    File nodir = new File(configuration.getProperty("noimgfounddir"));
                    File[] nodirectoryListing = nodir.listFiles();
                    if (nodirectoryListing != null) {
                        for (File child : nodirectoryListing) {
                            var img = new Image(child.toURI().toString());
                            tourImage.set(img);
                            break;
                        }
                    }
                }
            } else {
                var img = new Image(configuration.getProperty("noimgfound"));
                tourImage.set(img);
                System.out.println("No Image found");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public ObservableList<TourModel> getTourList() {
        return tourList;
    }

    public ObservableList<TourLog> getLogList() {
        return logList;
    }

    public StringProperty getCurrentTourModelName() {
        return tourName;
    }

    public StringProperty getTourModelSummary() {
        return tourSummary;
    }

    public Property<Image> getTourImg() {
        return tourImage;
    }

    public String createTourSummary() {
        if (tourName.getValue() != "") {
            String summary ="From " + tourOrigin.get() + " to " +
                    tourDestination.get() + " with " +
                    tourTransportation.get() + "\nDuration: " +
                    tourDuration.get() + " over " + tourDistance.get() +
                    "km\nDescription: " + tourDescription.get();
            return summary;
        }
        return "";
    }

    public TourModel selectRandomTour() {
        Random rand = new Random();
        int numb = rand.nextInt(tourList.size());
        return tourList.get(numb);
    }
}
