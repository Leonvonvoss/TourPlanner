package at.technikumwien.tourplanner.BL.DAL.services;

import at.technikumwien.tourplanner.BL.DAL.dao.TourDao;
import at.technikumwien.tourplanner.BL.DAL.dao.TourLogDao;
import at.technikumwien.tourplanner.BL.DAL.model.TourLog;
import at.technikumwien.tourplanner.BL.DAL.model.TourModel;

import java.util.Optional;

public class TourLogService {

    public static void main(String[] args) {
        TourLogDao tourLogDao = new TourLogDao();

        tourLogDao.saveTourLog(new TourLog(1, 3, null, "!!!!", 3, 5, "30"));
        tourLogDao.saveTourLog(new TourLog(1, 3, null, "!!!!", 3, 5, "30"));
        tourLogDao.saveTourLog(new TourLog(1, 3, null, "!!!!", 3, 5, "30"));
        /*
        System.out.println("1: #########################");

        tourLogDao.saveTourLog(new TourLog(1, 1, null, "Awesome trip", 1, 2, "20"));
        tourLogDao.saveTourLog(new TourLog(1, 1, null, "Awesome trip man", 2, 4, "20"));
        tourLogDao.saveTourLog(new TourLog(1, 2, null, "Awesome !!!!", 3, 5, "30"));
        tourLogDao.saveTourLog(new TourLog(1, 2, null, "!!!!", 3, 5, "30"));
        tourLogDao.saveTourLog(new TourLog(1, 3, null, "!!!!", 3, 5, "30"));
        tourLogDao.saveTourLog(new TourLog(1, 3, null, "!!!!", 3, 5, "30"));
        tourLogDao.saveTourLog(new TourLog(1, 3, null, "!!!!", 3, 5, "30"));

        System.out.println("2: #########################");

        for (TourLog allLogs : tourLogDao.getAllTourLogs()) {
            System.out.println(allLogs.toString());
        }

        System.out.println("3: #########################");

        Optional<TourLog> tourLogByTourId = tourLogDao.getTourLogByLogId(new TourLog(1));
        tourLogByTourId.ifPresent(System.out::println);

        System.out.println("4: #########################");

        for (TourLog allLogs : tourLogDao.getTourLogsOfToursByTourid(new TourLog(1, 1))) {
            System.out.println(allLogs.toString());
        }

        System.out.println("5: #########################");

        tourLogDao.updateTourLogById(new TourLog(4, 2, null, "Extrem!!!!", 3, 5, "30"));


        System.out.println("6: #########################");

        tourLogDao.deleteTourLogById(new TourLog(1, 1));

        System.out.println("7: #########################");

        tourLogDao.deleteTourLogsOfTourByTourId(new TourLog(1, 3));*/
    }

}