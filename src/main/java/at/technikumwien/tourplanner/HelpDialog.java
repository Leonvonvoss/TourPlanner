package at.technikumwien.tourplanner;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;

public class HelpDialog extends BorderPane {

    private TextArea textArea;
    private Button button;

    public HelpDialog() {
        initialize();
    }

    private void initialize() {
        this.textArea = new TextArea();
        this.button = new Button();
        button.setText("Close");
        textArea.setEditable(false);
        textArea.setText("Programmed by: Leon-Vincent von Voss & Klemens Hamburger");
        setCenter(textArea);
        setBottom(button);
    }
}
