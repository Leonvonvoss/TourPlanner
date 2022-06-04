package at.technikumwien.tourplanner.BL.DAL.services;

import at.technikumwien.tourplanner.BL.DAL.dao.TourDao;
import at.technikumwien.tourplanner.BL.DAL.model.TourModel;

import java.util.Collection;
import java.util.Optional;

public class TourService {

    public static void main(String[] args) {
        TourDao tourDao = new TourDao();

        System.out.println("1: #########################");

        tourDao.saveTour(new TourModel(1, "BigCityLife", "Super nice tour in Wien", "10km", "02:00:00", "Vienna Hauptbahnhof", "Vienna Westbahnhof", "Zu Fuß"));
        tourDao.saveTour(new TourModel(1, "TheBIGFive", "Super nice tour in Africa", "100km", "20:00:00", "Captown", "Outdoorshoorn", "Auto"));

        System.out.println("2: #########################");

        for (TourModel allTour : tourDao.getAllTours()) {
            System.out.println(allTour.toString());
        }

        System.out.println("3: #########################");

        Optional<TourModel> tourByTourId = tourDao.getTourByTourId(new TourModel(2));
        tourByTourId.ifPresent(System.out::println);

        System.out.println("4: #########################");

        for (TourModel allTour : tourDao.getToursByName(new TourModel("TheBIGFive"))) {
            System.out.println(allTour.toString());
        }

        System.out.println("5: #########################");

        for (TourModel allTour : tourDao.getToursBySearchFilter(new TourModel(""))) {
            System.out.println(allTour.toString());
        }

        System.out.println("6: #########################");

        tourDao.updateTourById(new TourModel(1, "BIGViennaLife", "Super nice tour in Wien", "20km", "04:00:00", "Vienna Hauptbahnhof", "Vienna Westbahnhof", "Zu Fuß"));
    }

}
