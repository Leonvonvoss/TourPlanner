package at.technikumwien.tourplanner.view;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class HelpDialogViewController extends BorderPane {

    @FXML private TextArea textArea;
    @FXML private Button buttonClose;

    public HelpDialogViewController() {
        initialize();
    }

    private void initialize() {
        this.textArea = new TextArea();
        this.buttonClose = new Button();
        buttonClose.setText("Close");
        textArea.setEditable(false);
        textArea.setText("Programmed by: Leon-Vincent von Voss & Klemens Hamburger");
        setCenter(textArea);
        setBottom(buttonClose);

        buttonClose.setOnAction(e -> {
            buttonCloseClick();
        });
    }

    @FXML
    protected void buttonCloseClick() {
        ((Stage) textArea.getScene().getWindow()).close();
    }

}
