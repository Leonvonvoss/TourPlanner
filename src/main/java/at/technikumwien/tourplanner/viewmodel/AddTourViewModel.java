package at.technikumwien.tourplanner.viewmodel;

import at.technikumwien.tourplanner.BL.services.TourManager;
import at.technikumwien.tourplanner.Tours.Tour;
import com.sun.scenario.effect.Effect;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AddTourViewModel {

    //private static final Logger logger = LogManager.getLogger(TourDesignerWindowViewModel.class);

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

    private Tour currentTour;

    private TourManager manager;

    private final ObservableList<String> transportOptions =
            FXCollections.observableArrayList(
                    "Bicycle",
                    "On Foot",
                    "Motor Vehicle"
            );
    private static final String LOC_REGEX = "[^a-zA-Z0-9äöüÄÖÜß_,\\- ]";

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
    }

    public void initData(TourManager manager, Tour currentTour) {
        this.manager = manager;
        this.currentTour = currentTour;

        tourTitle.set(currentTour.getName());
        tourOrigin.set(currentTour.getOrigin());
        tourDestination.set(currentTour.getDestination());
        tourDescription.set(currentTour.getDescription());
        tourTransportation.set(currentTour.getModeOfTransportation());
    }

    public boolean saveTour() {
        return true;
    }

    //private final Effect invalidEffect = new DropShadow(BlurType.GAUSSIAN, Color.RED, 4, 0.0, 0, 0);

    /*private boolean validateFields() {
        //logger.log(Level.DEBUG,"val:{}.",tourTransportation.getValue());
        //logger.log(Level.DEBUG,"val:{}.",tourTransportation.get());
        var check = true;
        if(tourTitle.get().isEmpty())
        {
            tourTitleEffect.set(invalidEffect);
            check = false;
        } else { tourTitleEffect.set(null); }
        if(tourOrigin.get().isEmpty())
        {
            tourOriginEffect.set(invalidEffect);
            check = false;
        } else { tourOriginEffect.set(null); }
        if(tourDestination.get().isEmpty())
        {
            tourDestinationEffect.set(invalidEffect);
            check = false;
        } else { tourDestinationEffect.set(null); }
        if(tourDescription.get().isEmpty())
        {
            tourDescriptionEffect.set(invalidEffect);
            check = false;
        } else { tourDescriptionEffect.set(null); }
        if(tourTransportation.getValue() == null)
        {
            tourTransportationEffect.set(invalidEffect);
            check = false;
        } else { tourTransportationEffect.set(null); }

        //logger.log(Level.DEBUG, check);
        return check;
    }*/
}
