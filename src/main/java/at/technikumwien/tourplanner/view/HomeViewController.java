package at.technikumwien.tourplanner.view;

import at.technikumwien.tourplanner.BL.DAL.model.TourModel;
import at.technikumwien.tourplanner.BL.managers.TourManager;
import at.technikumwien.tourplanner.BL.managers.TourManagerFactory;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.Getter;

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
    @FXML private Button topVBoxButtonAddTour;
    @FXML public ListView<TourModel> leftVBoxListViewTours;
    @FXML public TableView tableView;
    @FXML public ImageView tourImg;
    @FXML public Label tourName;
    @FXML public Label tourDescription;
    @FXML public AnchorPane imgAnchor;

    private final ObjectProperty<TourModel> selectedtour = new SimpleObjectProperty<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TourManager manager = TourManagerFactory.getManager();
        homeViewModel.setup(manager);
        tourImg.fitHeightProperty().bind(imgAnchor.heightProperty());
        tourImg.fitWidthProperty().bind(imgAnchor.widthProperty());
        topVBoxTextFieldSearch.textProperty().bindBidirectional(homeViewModel.inputProperty());
        topVBoxButtonSearch.disableProperty().bind(homeViewModel.enabledProperty());
        tourName.textProperty().bindBidirectional(homeViewModel.getCurrentTourModelName());
        tourDescription.textProperty().bindBidirectional(homeViewModel.getCurrentTourModelSummary());
        selectedtour.bindBidirectional(homeViewModel.getCurrentTourModel());
        tableView.setPlaceholder(new Label(". . ."));
        listener();
        setuplist();
    }

    private void listener() {
        leftVBoxListViewTours.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                if (newValue != null) {
                    selectedtour.set(newValue);
                    homeViewModel.changeTourView();
                }
            }
            catch (Exception e) {

                }

        });
    }


    private void setuplist() {
        homeViewModel.setuplist();
        System.out.println(homeViewModel.getTourList());
        leftVBoxListViewTours.setItems(homeViewModel.getTourList());
    }

    public HomeViewController(HomeViewModel homeViewModel) {
        this.homeViewModel = homeViewModel;
    }

    @FXML
    protected void onTopVBoxButtonSearchClick() {
        System.out.println(homeViewModel.getCurrentTourModelName());
        System.out.println(homeViewModel.getCurrentTourModelSummary());
        /*String textInput = topVBoxTextFieldSearch.textProperty().get();
        try {
            if (textInput.isEmpty()) {
                throw new IllegalArgumentException("Empty text input!");
            }
            leftVBoxListViewListItems.add(textInput);
            leftVBoxListViewTours.getSelectionModel().selectLast();
        } catch (IllegalArgumentException IllegalArgumentException) {
            IllegalArgumentException.printStackTrace();
            leftVBoxListViewListItems.add("EMPTY ADDED");
        }*/
    }

    @FXML
    protected void onTopVBoxButtonAddTourClick() {
        homeViewModel.onTopVBoxButtonAddTourClick();
        leftVBoxListViewTours.setItems(homeViewModel.getTourList());
    }

    @FXML
    protected void onMenuItemAboutClick() {
        Stage aboutStage = new Stage();
        HelpDialogViewController dialog = new HelpDialogViewController();
        aboutStage.setScene(new Scene(dialog));
        aboutStage.setTitle("Help");
        //aboutStage.initModality(Modality.APPLICATION_MODAL); // <- geht auch so - ist der kürzere Weg
        aboutStage.initModality(Modality.WINDOW_MODAL);
        aboutStage.initOwner(borderPane.getScene().getWindow());
        aboutStage.show();
    }

    @FXML
    protected void onMenuItemCloseClick() {
        Platform.exit();
    }

}