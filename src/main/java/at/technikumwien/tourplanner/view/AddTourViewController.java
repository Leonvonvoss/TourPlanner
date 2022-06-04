package at.technikumwien.tourplanner.view;

import at.technikumwien.tourplanner.BL.services.TourManager;
import at.technikumwien.tourplanner.Tours.Tour;
import at.technikumwien.tourplanner.viewmodel.AddTourViewModel;
import com.sun.scenario.effect.DropShadow;
import com.sun.scenario.effect.Effect;
import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlurType;
import javafx.stage.Stage;
import org.slf4j.event.Level;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class AddTourViewController  extends Application implements Initializable {

    //private static final Logger logger = LogManager.getLogger(AddTourViewController.class);

    private final AddTourViewModel viewModel = new AddTourViewModel();

    @FXML
    public TextField tourTitleField;
    @FXML
    public TextField tourStartField;
    @FXML
    public TextField tourDestinationField;
    @FXML
    public TextArea tourDescriptionField;
    @FXML
    public ComboBox<String> tourTransportBox;

    @Override
    public void start(Stage stage) throws Exception {
        //unused
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tourTitleField.textProperty().bindBidirectional(viewModel.getTourTitle());
        //tourTitleField.effectProperty().bindBidirectional(viewModel.getTourTitleEffect());
        tourStartField.textProperty().bindBidirectional(viewModel.getTourOrigin());
        //tourStartField.effectProperty().bindBidirectional(viewModel.getTourOriginEffect());
        tourDestinationField.textProperty().bindBidirectional(viewModel.getTourDestination());
        //tourDestinationField.effectProperty().bindBidirectional(viewModel.getTourDestinationEffect());
        tourDescriptionField.textProperty().bindBidirectional(viewModel.getTourDescription());
        //tourDescriptionField.effectProperty().bindBidirectional(viewModel.getTourDescriptionEffect());
        tourTransportBox.valueProperty().bindBidirectional(viewModel.getTourTransportation());
        //tourTransportBox.effectProperty().bindBidirectional(viewModel.getTourTransportationEffect());

        tourTransportBox.setItems(viewModel.getTransportOptions());

        //logger.log(Level.DEBUG, "Tour Designer initiated");
    }

    public void initData(TourManager manager) {
        viewModel.initData(manager);
    }

    public void initData(TourManager manager, Tour currentTour) {
        viewModel.initData(manager,currentTour);
    }

    @FXML
    public void saveTourEntryButtonClick() {
        /*if(viewModel.saveTour()) {
            Stage stage = (Stage) tourTitleField.getScene().getWindow();
            stage.close();
        }*/
    }

    @FXML
    public void cancelTourEntryButtonClick() {
        Stage stage = (Stage) tourTitleField.getScene().getWindow();
        stage.close();
    }
}
