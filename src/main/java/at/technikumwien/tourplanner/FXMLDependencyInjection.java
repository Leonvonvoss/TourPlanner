package at.technikumwien.tourplanner;

import at.technikumwien.tourplanner.view.ControllerViewFactory;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * FXMLLoader with Dependency-Injection
 * based on https://edencoding.com/dependency-injection/
 */
public class FXMLDependencyInjection {

    public static Parent load(String location, Locale locale) throws IOException {
        FXMLLoader loader = getLoader(location, locale);
        return loader.load();
    }

    public static FXMLLoader getLoader(String location, Locale locale) {

        return new FXMLLoader(
                FXMLDependencyInjection.class.getResource("/at/technikumwien/tourplanner/fxml/" + location),
                ResourceBundle.getBundle("at.technikumwien.tourplanner.language." + "gui_strings", locale),
                new JavaFXBuilderFactory(),
                controllerClass-> ControllerViewFactory.getInstance().create(controllerClass)
                );
    }
}
