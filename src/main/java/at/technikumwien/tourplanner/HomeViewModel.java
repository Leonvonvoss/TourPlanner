package at.technikumwien.tourplanner;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class HomeViewModel {

    private final StringProperty input = new SimpleStringProperty();
    private final BooleanProperty enabled = new SimpleBooleanProperty();

    public HomeViewModel() {
        enabled.bind(input.isEmpty());
    }

    public StringProperty inputProperty() {
        return input;
    }

    public BooleanProperty enabledProperty() {
        return enabled;
    }
}
