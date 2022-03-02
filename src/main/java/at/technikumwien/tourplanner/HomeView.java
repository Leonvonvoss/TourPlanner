package at.technikumwien.tourplanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeView implements Initializable {

    private final HomeViewModel homeViewModel = new HomeViewModel();

    @FXML
    private BorderPane borderPane;

    @FXML
    private MenuItem menuFile;

    @FXML
    private MenuItem menuItemClose;

    @FXML
    private MenuItem menuExport;

    @FXML
    private MenuItem menuItemPDF;

    @FXML
    private MenuItem menuItemFile;

    @FXML
    private MenuItem menuHelp;

    @FXML
    private MenuItem menuItemAbout;

    @FXML
    private TextField leftVBoxTextField;

    @FXML
    private Button leftVBoxSaveButton;

    @FXML
    private ListView<String> rightVBoxListView;

    private ObservableList<String> rightVBoxListViewListItems;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        menuItemClose.setOnAction(e -> {
            ((Stage) borderPane.getScene().getWindow()).close();
        });

        rightVBoxListViewListItems = FXCollections.observableArrayList("Item 1", "Item 2", "Item 3");
        rightVBoxListView.setItems(rightVBoxListViewListItems);

        leftVBoxSaveButton.setOnAction(e -> {
            String textInput = leftVBoxTextField.textProperty().get();
            try {
                if (textInput.isEmpty()) {
                    throw new IllegalArgumentException("Empty text input!");
                }
                rightVBoxListViewListItems.add(textInput);
            } catch (IllegalArgumentException IllegalArgumentException) {
                IllegalArgumentException.printStackTrace();
                rightVBoxListViewListItems.add("EMPTY ADDED");
            }
        });

        leftVBoxTextField.textProperty().bindBidirectional(homeViewModel.inputProperty());
        leftVBoxSaveButton.disableProperty().bind(homeViewModel.enabledProperty());

    }

    @FXML
    protected void onMenuItemPDFClick() {
        System.out.println("Export PDF");
    }

    @FXML
    protected void onMenuItemAboutClick() {
        Stage aboutStage = new Stage();
        HelpDialog dialog = new HelpDialog();
        aboutStage.setScene(new Scene(dialog));
        aboutStage.setTitle("Help");
        //aboutStage.initModality(Modality.APPLICATION_MODAL); // <- geht auch so - ist der kürzere Weg
        aboutStage.initModality(Modality.WINDOW_MODAL);
        aboutStage.initOwner(borderPane.getScene().getWindow());
        aboutStage.show();
    }

}