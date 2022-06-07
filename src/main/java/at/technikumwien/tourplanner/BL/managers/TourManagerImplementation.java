package at.technikumwien.tourplanner.BL.managers;

import at.technikumwien.tourplanner.BL.DAL.dao.TourDao;
import at.technikumwien.tourplanner.BL.DAL.dao.TourLogDao;
import at.technikumwien.tourplanner.BL.DAL.model.TourLog;
import at.technikumwien.tourplanner.BL.DAL.model.TourModel;
import at.technikumwien.tourplanner.BL.services.ApiClient;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class TourManagerImplementation implements TourManager {

        //private static final Logger logger = LogManager.getLogger(TourManagerImplementation.class);
        @Override
        public void createTourModel(TourModel receivedtour) throws ExecutionException, InterruptedException, JsonProcessingException {
                var result= ApiClient.getTourAPIData(receivedtour.getLocationfrom(), receivedtour.getLocationto(), receivedtour.getName(), receivedtour.getTransporttype());
                String length = result.getValue().getKey();
                String duration = result.getValue().getValue();
                receivedtour.setTotaldistance(length);
                receivedtour.setTotalduration(duration);
                TourDao tourdao = new TourDao();
                if (tourdao.saveTour(receivedtour))
                {
                        System.out.println("saved tour");
                }
                else {
                        System.out.println("failed to save tour");
                }
        }

        private boolean searchTourLogs(TourModel tour, String searchText) {
                return true;
        }

        @Override
        public List<TourModel> getTourModels() {
                return null;
        }

        @Override
        public TourModel getTourModel(String name) {
                return null;
        }

        @Override
        public TourModel getTourModel(int id) {
                return null;
        }


        @Override
        public void cloneTourModel(TourModel TourModel) {

        }

        @Override
        public void modifyTourModel(TourModel TourModel) {

        }

        @Override
        public void deleteTourModel(TourModel TourModel) {

        }

        @Override
        public List<TourModel> search(String searchText) {
                return Collections.emptyList();
        }

        @Override
        public List<TourModel> getAllTours() {
                TourDao tourdao = new TourDao();
                return tourdao.getAllTours();
        }

        public List<TourLog> getAllTourLogs() {
                return null;
        }

        @Override
        public List<TourLog> getTourLogs(int TourModelId) {
                TourLogDao tourLogDao = new TourLogDao();
                return tourLogDao.getTourLogsOfToursByTouridint(TourModelId);
        }

        @Override
        public TourLog getTourLog(int id) {
                return null;
        }

        @Override
        public void createTourLog(TourLog tourLog) {
                TourLogDao tourLogDao = new TourLogDao();
                tourLogDao.saveTourLog(tourLog);

        }

        @Override
        public void modifyTourLog(TourLog turLog) {

        }

        @Override
        public void deleteTourLog(TourLog tourLog) {

        }

        @Override
        public void printReport(TourModel tourModel) {

        }

        @Override
        public void printSummary(TourModel tourModel) {

        }

        @Override
        public void exportTourModelData(TourModel tourModel) {

        }

        @Override
        public void importTourModelData(String filename) {

        }


        private String createName(String origin, String destination) {
                String string = "";
                return string;
        }
}

