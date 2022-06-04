package at.technikumwien.tourplanner.view;

import at.technikumwien.tourplanner.viewmodel.HomeViewModel;

public class ControllerViewFactory {

    private final HomeViewModel homeViewModel;

    public ControllerViewFactory() {
        homeViewModel = new HomeViewModel();
    }

    //
    // Factory-Method Pattern
    //
    public Object create(Class<?> controllerClass) {
        if (controllerClass == HomeViewController.class) {
            return new HomeViewController(homeViewModel);
        } else if (controllerClass == HelpDialogViewController.class) {
            return new HelpDialogViewController();
        }
        throw new IllegalArgumentException("Unknown controller class: " + controllerClass);
    }

    //
    // Singleton-Pattern with early-binding
    //
    private static ControllerViewFactory instance = new ControllerViewFactory();

    public static ControllerViewFactory getInstance() {
        return instance;
    }

}
