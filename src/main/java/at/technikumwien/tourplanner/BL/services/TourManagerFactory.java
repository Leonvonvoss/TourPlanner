package at.technikumwien.tourplanner.BL.services;

public class TourManagerFactory {
    private TourManagerFactory() {}

    public static TourManager getManager()
    {
        return new TourManagerImplementation();
    }
}
