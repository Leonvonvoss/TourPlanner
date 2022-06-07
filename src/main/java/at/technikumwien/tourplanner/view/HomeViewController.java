package at.technikumwien.tourplanner.view;

import at.technikumwien.tourplanner.BL.DAL.model.TourLog;
import at.technikumwien.tourplanner.BL.DAL.model.TourModel;
import at.technikumwien.tourplanner.BL.managers.TourManager;
import at.technikumwien.tourplanner.BL.managers.TourManagerFactory;
import at.technikumwien.tourplanner.BL.services.PDFGenerator;
import at.technikumwien.tourplanner.viewmodel.HomeViewModel;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.Getter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeViewController implements Initializable {


    @Getter
    private final HomeViewModel homeViewModel;
    private ObservableList<String> leftVBoxListViewListItems;

    @FXML private BorderPane borderPane;

    @FXML private MenuItem menuItemClose;
    @FXML private MenuItem menuItemPDF;
    @FXML private MenuItem menuItemFile;
    @FXML private MenuItem menuItemAbout;

    @FXML private TextField topVBoxTextFieldSearch;
    @FXML private Button topVBoxButtonSearch;
    @FXML private ListView<TourModel> leftVBoxListViewTours;
    @FXML private TableView<TourLog> leftVBoxLogViewLogs;
    @FXML private ImageView tourImg;
    @FXML private Label tourName;
    @FXML private Label tourDescription;
    @FXML private AnchorPane imgAnchor;

    @FXML
    public TableColumn<Object, Object> logDate;
    @FXML
    public TableColumn<Object, Object> logTime;
    @FXML
    public TableColumn<Object, Object> logDifficulty;
    @FXML
    public TableColumn<Object, Object> logRating;
    @FXML
    public TableColumn<Object, Object> logComment;

    private final ObjectProperty<TourModel> selectedtour = new SimpleObjectProperty<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TourManager manager = TourManagerFactory.getManager();
        homeViewModel.setup(manager);
        tourImg.fitHeightProperty().bind(imgAnchor.heightProperty());
        tourImg.fitWidthProperty().bind(imgAnchor.widthProperty());
        tourImg.imageProperty().bindBidirectional(homeViewModel.getTourImg());
        topVBoxTextFieldSearch.textProperty().bindBidirectional(homeViewModel.inputProperty());
        tourName.textProperty().bind(homeViewModel.getCurrentTourModelName());
        tourDescription.textProperty().bind(homeViewModel.getTourModelSummary());
        selectedtour.bindBidirectional(homeViewModel.getCurrentTourModel());
        listener();
        setuplist();
        setupLogTable();
    }

    private void listener() {
        leftVBoxListViewTours.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                if (newValue != null) {
                    selectedtour.set(newValue);
                    homeViewModel.changeTourView();
                    this.loadLogs();
                }
            }
            catch (Exception e) {

                }

        });
    }


    private void setuplist() {
        homeViewModel.setuplist();
        leftVBoxListViewTours.setItems(homeViewModel.getTourList());
    }

    private void loadLogs() {
        homeViewModel.setupLogList();
        leftVBoxLogViewLogs.setItems(homeViewModel.getLogList());
    }

    private void setupLogTable() {
        logDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        //logTime.setCellValueFactory(new PropertyValueFactory<>("duration"));
        logRating.setCellValueFactory(new PropertyValueFactory<>("rating"));
        logDifficulty.setCellValueFactory(new PropertyValueFactory<>("difficulty"));
        //logComment.setCellValueFactory(new PropertyValueFactory<>("report"));
    }

    public HomeViewController(HomeViewModel homeViewModel) {
        this.homeViewModel = homeViewModel;
    }

    @FXML
    protected void onTopVBoxButtonSearchClick() {
        selectedtour.set(homeViewModel.selectRandomTour());
        homeViewModel.changeTourView();
        this.loadLogs();
    }

    @FXML
    protected void onTopVBoxButtonAddTourClick() {
        homeViewModel.onTopVBoxButtonAddTourClick();
        leftVBoxListViewTours.setItems(homeViewModel.getTourList());
        leftVBoxListViewTours.refresh();
    }

    @FXML
    protected void onTopVBoxButtonAddLogClick() {
        homeViewModel.onTopVBoxButtonAddLogClick();
        leftVBoxListViewTours.setItems(homeViewModel.getTourList());
        leftVBoxListViewTours.refresh();
    }



    @FXML
    protected void onMenuItemAboutClick() {
        Stage aboutStage = new Stage();
        HelpDialogViewController dialog = new HelpDialogViewController();
        aboutStage.setScene(new Scene(dialog));
        //aboutStage.initModality(Modality.APPLICATION_MODAL); // <- geht auch so - ist der kürzere Weg
        aboutStage.initModality(Modality.WINDOW_MODAL);
        aboutStage.initOwner(borderPane.getScene().getWindow());
        aboutStage.show();
    }

    @FXML
    protected void onMenuItemCloseClick() {
        Platform.exit();
    }


    @FXML
    protected void onMenuItemPDFClick()  {
        PDFGenerator pdfGenerator = new PDFGenerator();
        try {
            pdfGenerator.generateReport();
            Stage aboutStage = new Stage();
            ReportDialogViewController dialog = new ReportDialogViewController();
            aboutStage.setScene(new Scene(dialog));
            aboutStage.setTitle("Report");
            //aboutStage.initModality(Modality.APPLICATION_MODAL); // <- geht auch so - ist der kürzere Weg
            aboutStage.initModality(Modality.WINDOW_MODAL);
            aboutStage.initOwner(borderPane.getScene().getWindow());
            aboutStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}