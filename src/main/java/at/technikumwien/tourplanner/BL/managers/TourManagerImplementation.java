package at.technikumwien.tourplanner.BL.managers;

import at.technikumwien.tourplanner.BL.DAL.model.TourLog;
import at.technikumwien.tourplanner.BL.DAL.model.TourModel;

import java.util.Collections;
import java.util.List;

public class TourManagerImplementation implements TourManager {

        //private static final Logger logger = LogManager.getLogger(TourManagerImplementation.class);
        @Override
        public void createTourModel(TourModel receivedtour) {
                System.out.println(receivedtour);
                System.out.println(receivedtour.getName());
                System.out.println(receivedtour.getDescription());
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
        public List<TourLog> getAllTourLogs() {
                return null;
        }

        @Override
        public List<TourLog> getTourLogs(int TourModelId) {
                return null;
        }

        @Override
        public TourLog getTourLog(int id) {
                return null;
        }

        @Override
        public void createTourLog(TourLog tourLog) {

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

