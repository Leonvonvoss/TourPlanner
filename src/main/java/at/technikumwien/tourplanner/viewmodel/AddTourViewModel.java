package at.technikumwien.tourplanner.viewmodel;

import at.technikumwien.tourplanner.BL.DAL.model.TourModel;
import at.technikumwien.tourplanner.BL.managers.TourManager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sun.scenario.effect.Effect;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import org.apache.logging.log4j.LogManager;
import org.slf4j.event.Level;

import java.time.LocalDate;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

public class AddTourViewModel {

    private final StringProperty tourTitle = new SimpleStringProperty("");
    private final StringProperty tourOrigin = new SimpleStringProperty("");
    private final StringProperty tourDestination = new SimpleStringProperty("");
    private final StringProperty tourDescription = new SimpleStringProperty("");
    private final StringProperty tourTransportation = new SimpleStringProperty();

    private final ObjectProperty<Effect> tourTitleEffect = new SimpleObjectProperty<>();
    private final ObjectProperty<Effect> tourOriginEffect = new SimpleObjectProperty<>();
    private final ObjectProperty<Effect> tourDestinationEffect = new SimpleObjectProperty<>();
    private final ObjectProperty<Effect> tourDescriptionEffect = new SimpleObjectProperty<>();
    private final ObjectProperty<Effect> tourTransportationEffect = new SimpleObjectProperty<>();

    private TourModel currentTour;

    private TourManager manager;

    private final ObservableList<String> transportOptions =
            FXCollections.observableArrayList(
                    "Bicycle",
                    "On Foot",
                    "Motor Vehicle"
            );

    public ObservableList<String> getTransportOptions() {
        return transportOptions;
    }

    public StringProperty getTourTitle() {
        return tourTitle;
    }

    public StringProperty getTourOrigin() {
        return tourOrigin;
    }

    public StringProperty getTourDestination() {
        return tourDestination;
    }

    public StringProperty getTourDescription() {
        return tourDescription;
    }

    public StringProperty getTourTransportation() {
        return tourTransportation;
    }

    public ObjectProperty<Effect> getTourTitleEffect() {
        return tourTitleEffect;
    }

    public ObjectProperty<Effect> getTourOriginEffect() {
        return tourOriginEffect;
    }

    public ObjectProperty<Effect> getTourDestinationEffect() {
        return tourDestinationEffect;
    }

    public ObjectProperty<Effect> getTourDescriptionEffect() {
        return tourDescriptionEffect;
    }

    public ObjectProperty<Effect> getTourTransportationEffect() {
        return tourTransportationEffect;
    }

    public void initData(TourManager manager) {
        this.manager = manager;
        //System.out.println("In inititdata: " + this.manager);
    }

    public boolean saveTour() throws ExecutionException, InterruptedException, JsonProcessingException {
        if (validateFields()) {
            this.currentTour = new TourModel(tourTitle.get(), tourDescription.get(), tourOrigin.get(), tourDestination.get(), tourTransportation.get());
            manager.createTourModel(currentTour);
        }
        else {
            var alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please fill out all required fields correctly");
            alert.show();
            return false;
        }
        return false;
    }

    private boolean validateFields() {
        var check = true;
        if(tourTitle.get().isEmpty())
        {

            check = false;
        } else { tourTitleEffect.set(null); }
        if(tourOrigin.get().isEmpty())
        {

            check = false;
        } else { tourOriginEffect.set(null); }
        if(tourDestination.get().isEmpty())
        {

            check = false;
        } else { tourDestinationEffect.set(null); }
        if(tourDescription.get().isEmpty())
        {

            check = false;
        } else { tourDescriptionEffect.set(null); }
        if(tourTransportation.getValue() == null)
        {

            check = false;
        } else { tourTransportationEffect.set(null); }

        return check;
    }

}
