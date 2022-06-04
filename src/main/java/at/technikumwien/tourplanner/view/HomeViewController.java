package at.technikumwien.tourplanner.view;

import at.technikumwien.tourplanner.BL.managers.TourManager;
import at.technikumwien.tourplanner.BL.managers.TourManagerFactory;
import at.technikumwien.tourplanner.viewmodel.HomeViewModel;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    @FXML private ListView<String> leftVBoxListViewTours;
    @FXML private TableView tableView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TourManager manager = TourManagerFactory.getManager();
        homeViewModel.setup(manager);
        leftVBoxListViewListItems = FXCollections.observableArrayList("Item 1", "Item 2", "Item 3");
        leftVBoxListViewTours.setItems(leftVBoxListViewListItems);
        topVBoxTextFieldSearch.textProperty().bindBidirectional(homeViewModel.inputProperty());
        topVBoxButtonSearch.disableProperty().bind(homeViewModel.enabledProperty());
        tableView.setPlaceholder(new Label(". . ."));
    }

    public HomeViewController(HomeViewModel homeViewModel) {
        this.homeViewModel = homeViewModel;
    }

    @FXML
    protected void onTopVBoxButtonSearchClick() {
        String textInput = topVBoxTextFieldSearch.textProperty().get();
        try {
            if (textInput.isEmpty()) {
                throw new IllegalArgumentException("Empty text input!");
            }
            leftVBoxListViewListItems.add(textInput);
            leftVBoxListViewTours.getSelectionModel().selectLast();
        } catch (IllegalArgumentException IllegalArgumentException) {
            IllegalArgumentException.printStackTrace();
            leftVBoxListViewListItems.add("EMPTY ADDED");
        }
    }

    @FXML
    protected void onTopVBoxButtonAddTourClick() {
        homeViewModel.onTopVBoxButtonAddTourClick();
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