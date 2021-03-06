package at.technikumwien.tourplanner.view;

import at.technikumwien.tourplanner.BL.DAL.model.TourModel;
import at.technikumwien.tourplanner.BL.managers.TourManager;
import at.technikumwien.tourplanner.viewmodel.AddTourViewModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

public class AddTourViewController  extends Application implements Initializable {


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
        tourStartField.textProperty().bindBidirectional(viewModel.getTourOrigin());
        tourDestinationField.textProperty().bindBidirectional(viewModel.getTourDestination());
        tourDescriptionField.textProperty().bindBidirectional(viewModel.getTourDescription());
        tourTransportBox.valueProperty().bindBidirectional(viewModel.getTourTransportation());

        tourTransportBox.setItems(viewModel.getTransportOptions());

    }

    public void initData(TourManager manager) {
        viewModel.initData(manager);
    }

    public void initData(TourManager manager, TourModel currentTour) {
        viewModel.initData(manager);
    }

    @FXML

    public void saveTourEntryButtonClick(ActionEvent actionEvent) throws ExecutionException, InterruptedException, JsonProcessingException {
        if(viewModel.saveTour()) {
            Node source = (Node) actionEvent.getSource();
            var stage  = (Stage) source.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    public void cancelTourEntryButtonClick() {
        Stage stage = (Stage) tourTitleField.getScene().getWindow();
        stage.close();
    }
}
