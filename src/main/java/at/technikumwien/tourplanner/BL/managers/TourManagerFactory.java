package at.technikumwien.tourplanner.BL.managers;

public class TourManagerFactory {
    private TourManagerFactory() {}

    public static TourManager getManager()
    {
        return new TourManagerImplementation();
    }
}
