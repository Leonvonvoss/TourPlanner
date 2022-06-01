package at.technikumwien.tourplanner.BL.services;

import at.technikumwien.tourplanner.Tours.Log;
import at.technikumwien.tourplanner.Tours.Tour;

import java.util.List;

public interface TourManager {
    //tour related
    List<Tour> getTours();
    Tour getTour(String name);
    Tour getTour(int id);
    void createTour(Tour tour);
    void cloneTour(Tour tour);
    void modifyTour(Tour tour);
    void deleteTour(Tour tour);
    List<Tour> search(String searchText);


    //log related
    List<Log> getAllLogs();
    List<Log> getLogs(int tourId);
    Log getLog(int id);
    void createLog(Log log);
    void modifyLog(Log log);
    void deleteLog(Log log);

    //misc
    void printReport(Tour tour);
    void printSummary(Tour tour);
    void exportTourData(Tour tour);
    void importTourData(String filename);
}

