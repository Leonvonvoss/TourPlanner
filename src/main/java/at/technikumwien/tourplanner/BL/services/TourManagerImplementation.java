package at.technikumwien.tourplanner.BL.services;

import at.technikumwien.tourplanner.Tours.Log;
import at.technikumwien.tourplanner.Tours.Tour;

import java.util.Collections;
import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class TourManagerImplementation implements TourManager {

        //private static final Logger logger = LogManager.getLogger(TourManagerImplementation.class);


        @Override
        public List<Tour> getTours() {
                return Collections.emptyList();
        }

        @Override
        public Tour getTour(String name) {
                Tour tour = new Tour(1,"","","",1.0,"","","");
                return tour;
        }

        @Override
        public Tour getTour(int id) {
                Tour tour = new Tour(1,"","","",1.0,"","","");
                return tour;
        }

        @Override
        public void createTour(Tour receivedtour) {
                System.out.println(receivedtour);
                System.out.println(receivedtour.getName());
                System.out.println(receivedtour.getDescription());
        }

        @Override
        public void cloneTour(Tour tour) {

        }

        @Override
        public void modifyTour(Tour tour) {

        }

        @Override
        public void deleteTour(Tour tour) {

        }

        private boolean searchTourLogs(Tour tour, String searchText) {
                return true;
        }

        @Override
        public List<Tour> search(String searchText) {
                return Collections.emptyList();
        }

        @Override
        public List<Log> getAllLogs() {
                return Collections.emptyList();
        }

        @Override
        public List<Log> getLogs(int tourId) {
                return Collections.emptyList();
        }

        @Override
        public Log getLog(int id) {
                Log log = new Log();
                return log;
        }

        @Override
        public void createLog(Log log) {

        }

        @Override
        public void modifyLog(Log log) {

        }

        @Override
        public void deleteLog(Log log) {

        }

        @Override
        public void printReport(Tour tour) {

        }

        @Override
        public void printSummary(Tour tour) {

        }

        @Override
        public void exportTourData(Tour tour) {

        }

        @Override
        public void importTourData(String filename) {

        }


        private String createName(String origin, String destination) {
                String string = "";
                return string;
        }
}

