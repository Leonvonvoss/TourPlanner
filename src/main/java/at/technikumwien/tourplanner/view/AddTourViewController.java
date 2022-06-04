package at.technikumwien.tourplanner.view;

import at.technikumwien.tourplanner.BL.DAL.model.TourModel;
import at.technikumwien.tourplanner.BL.managers.TourManager;
import at.technikumwien.tourplanner.viewmodel.AddTourViewModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

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

    public void initData(TourManager manager, TourModel currentTour) {
        viewModel.initData(manager);
    }

    @FXML

    public void saveTourEntryButtonClick() throws ExecutionException, InterruptedException, JsonProcessingException {
        if(viewModel.saveTour()) {
            Stage stage = (Stage) tourTitleField.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    public void cancelTourEntryButtonClick() {
        Stage stage = (Stage) tourTitleField.getScene().getWindow();
        stage.close();
    }
}
