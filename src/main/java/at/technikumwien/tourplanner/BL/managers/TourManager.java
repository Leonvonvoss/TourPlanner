package at.technikumwien.tourplanner.BL.managers;

import at.technikumwien.tourplanner.BL.DAL.model.TourLog;
import at.technikumwien.tourplanner.BL.DAL.model.TourModel;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface TourManager {

    //TourModel related
    List<TourModel> getTourModels();
    TourModel getTourModel(String name);
    TourModel getTourModel(int id);
    void createTourModel(TourModel TourModel) throws ExecutionException, InterruptedException, JsonProcessingException;
    void cloneTourModel(TourModel TourModel);
    void modifyTourModel(TourModel TourModel);
    void deleteTourModel(TourModel TourModel);
    List<TourModel> getAllTours();
    List<TourModel> search(String searchText);


    //TourLog related
    List<TourLog> getAllTourLogs();
    List<TourLog> getTourLogs(int TourModelId);
    TourLog getTourLog(int id);
    void createTourLog(TourLog tourLog);
    void modifyTourLog(TourLog turLog);
    void deleteTourLog(TourLog tourLog);

    //misc
    void printReport(TourModel tourModel);
    void printSummary(TourModel tourModel);
    void exportTourModelData(TourModel tourModel);
    void importTourModelData(String filename);
}

