package at.technikumwien.tourplanner.view;

import at.technikumwien.tourplanner.BL.managers.TourManager;
import at.technikumwien.tourplanner.viewmodel.AddLogViewModel;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddLogViewController  extends Application implements Initializable {


    private final AddLogViewModel viewModel = new AddLogViewModel();
    @FXML
    public TextField logDurationField;
    @FXML
    public DatePicker logDatePicker;
    @FXML
    public Slider logRatingSlide;
    @FXML
    public Slider logDifficultySlide;
    @FXML
    public Label logRatingDisplay;
    @FXML
    public Label logDifficultyDisplay;
    @FXML
    public TextArea logCommentField;


    public int getValueOfSlider() { return (int) Math.round(logRatingSlide.getValue()); }

    @Override
    public void start(Stage stage) throws Exception {
        //unused
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //logDatePicker.valueProperty().bindBidirectional(viewModel.logDateProperty());
        logDurationField.textProperty().bindBidirectional(viewModel.logDurationProperty());
        logRatingSlide.valueProperty().bindBidirectional(viewModel.logRatingProperty());
        logRatingDisplay.textProperty().bindBidirectional(viewModel.logRatingLabelProperty());
        logCommentField.textProperty().bindBidirectional(viewModel.logCommentProperty());
        logDifficultySlide.valueProperty().bindBidirectional(viewModel.logDifficulty());
        logDifficultyDisplay.textProperty().bindBidirectional(viewModel.logDifficultyLabel());
    }


    @FXML
    public void saveLogEntryButtonClick(ActionEvent actionEvent) {
        if(viewModel.saveTourLog()) {
            Node source = (Node) actionEvent.getSource();
            var stage  = (Stage) source.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    public void cancelLogEntryButtonClick(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        var stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void initData(TourManager manager, int tourId) {
        viewModel.initData(manager, tourId);
    }

}