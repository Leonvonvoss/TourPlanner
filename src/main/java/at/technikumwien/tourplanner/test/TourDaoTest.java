package at.technikumwien.tourplanner.test;

import at.technikumwien.tourplanner.BL.DAL.dao.TourDao;
import at.technikumwien.tourplanner.BL.DAL.model.TourLog;
import at.technikumwien.tourplanner.BL.DAL.model.TourModel;
import org.junit.jupiter.api.*;

import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TourDaoTest {

    TourDao tourDao = TourDao.getInstance();

    @BeforeEach
    void setup() {
    }

    @Test
    @Order(1)
    @DisplayName("saveTourAndCheckIfInDatabaseById")
    void saveTourAndCheckIfInDatabaseById() {
        if(tourDao.saveTour(new TourModel(1, "Test 1", "This is a awesome Test Tour in Vienna!", "100 km", "01:00 00", "Vienna", "Vienna", "Car"))) {
            Optional<TourModel> tourModelTest1 = tourDao.getTourByTourId(new TourModel(1));
            if(tourModelTest1.isPresent()) {
                assertEquals(tourModelTest1.get().getName(), "Test 1");
                assertEquals(tourModelTest1.get().getDescription(), "This is a awesome Test Tour in Vienna!");
            } else {
                fail("Couldn't find saved tour in database.");
            }
        } else {
            fail("Couldn't save tour in database.");
        }
    }

    @Test
    @Order(2)
    @DisplayName("saveTourAndCheckIfInDatabaseByName")
    void saveTourAndCheckIfInDatabaseByName() {
        if(tourDao.saveTour(new TourModel(2, "Test 2", "This is a awesome Test Tour in Hamburg!", "10 km", "01:00 00", "Hamburg", "Hamburg", "Foot"))) {
            Collection<TourModel> tourModelTest2 = tourDao.getToursByName(new TourModel(1, "Test 2"));
            if(!tourModelTest2.isEmpty()) {
                for (TourModel allTour : tourModelTest2) {
                    assertEquals(allTour.getName(), "Test 2");
                    assertEquals(allTour.getDescription(), "This is a awesome Test Tour in Hamburg!");
                    assertEquals(allTour.getLocationfrom(), "Hamburg");
                }
            } else {
                fail("Couldn't find saved tour in database.");
            }
        } else {
            fail("Couldn't save tour in database.");
        }
    }

    @Test
    @Order(3)
    @DisplayName("saveTourAndCheckIfInDatabaseByGetToursBySearchFilter")
    void saveTourAndCheckIfInDatabaseByGetToursBySearchFilter() {
        if(tourDao.saveTour(new TourModel(3, "Test 3", "This is a awesome Test Tour in Lübeck!", "5 km", "02:30 00", "Lübeck", "Lübeck", "Plane"))) {
            Collection<TourModel> tourModelTest3 = tourDao.getToursBySearchFilter(new TourModel("Test"));
            if(!tourModelTest3.isEmpty()) {
                for (TourModel allTour : tourModelTest3) {
                    System.out.println(allTour.toString());
                    assertTrue(allTour.getName().contains("Test"));
                }
            } else {
                fail("Couldn't find saved tour in database.");
            }
        } else {
            fail("Couldn't save tour in database.");
        }
    }

    @Test
    @Order(4)
    @DisplayName("saveTourAndCheckIfInDatabaseByGetAllTours")
    void saveTourAndCheckIfInDatabaseByGetAllTours() {
        if(tourDao.saveTour(new TourModel(4, "Test 4", "This is a awesome Test Tour in Salzburg!", "15 km", "01:30 00", "Salzburg", "Salzburg", "Foot"))) {
            Collection<TourModel> tourModelTest4 = tourDao.getAllTours();
            if(!tourModelTest4.isEmpty()) {
                assertEquals(tourModelTest4.size(), 4);
                for (TourModel allTour : tourModelTest4) {
                    System.out.println(allTour.toString());
                    assertTrue(allTour.getName().contains("Test"));
                }
            } else {
                fail("Couldn't find saved tours in database.");
            }
        } else {
            fail("Couldn't save tour in database.");
        }
    }

    @Test
    @Order(5)
    @DisplayName("updateTourByIdChangeTotalDistanceAndCheck")
    void updateTourByIdChangeTotalDistanceAndCheck() {
        if(tourDao.updateTourById(new TourModel(4, "Test 4", "This is a awesome Test Tour in Salzburg!", "9 km", "01:30 00", "Salzburg", "Salzburg", "Foot"))) {
            Optional<TourModel> tourModelTest5 = tourDao.getTourByTourId(new TourModel(4));
            if(tourModelTest5.isPresent()) {
                assertNotEquals(tourModelTest5.get().getTotaldistance(), "15 km");
                assertEquals(tourModelTest5.get().getTotaldistance(), "9 km");
            } else {
                fail("Couldn't find updated tour in database.");
            }
        } else {
            fail("Couldn't update and save tour in database.");
        }
    }

    @Test
    @Order(6)
    @DisplayName("deleteTourByIdAndCheckIfDeleted")
    void deleteTourByIdAndCheckIfDeleted() {
        if(tourDao.deleteTourById(new TourModel(1))) {
            Optional<TourModel> tourModelTest6 = tourDao.getTourByTourId(new TourModel(1));
            assertFalse(tourModelTest6.isPresent());
        } else {
            fail("Couldn't delete tour in database.");
        }
    }

    @Test
    @Order(7)
    @DisplayName("saveToursAndCheckIfSearchFilterIsWorkingCorrectly")
    void saveToursAndCheckIfSearchFilterIsWorkingCorrectly() {
        tourDao.saveTour(new TourModel(5, "Test 5", "This is a awesome Test Tour in Salzburg!", "9 km", "01:30 00", "Salzburg", "Salzburg", "Foot"));
        tourDao.saveTour(new TourModel(6, "Mama 6", "This is a awesome Test Tour in Salzburg!", "9 km", "01:30 00", "Salzburg", "Salzburg", "Foot"));
        tourDao.saveTour(new TourModel(7, "Mama 7", "This is a awesome Test Tour in Salzburg!", "9 km", "01:30 00", "Salzburg", "Salzburg", "Foot"));
        tourDao.saveTour(new TourModel(8, "Test 8", "This is a awesome Test Tour in Salzburg!", "9 km", "01:30 00", "Salzburg", "Salzburg", "Foot"));

        Collection<TourModel> tourModelTest7 = tourDao.getToursBySearchFilter(new TourModel("Mama"));
        if(!tourModelTest7.isEmpty()) {
            for (TourModel allTour : tourModelTest7) {
                System.out.println(allTour.toString());
            }
            assertEquals(tourModelTest7.size(), 2);
        } else {
            fail("Couldn't find saved tour in database.");
        }
    }

    @Test
    @Order(8)
    @DisplayName("checkIfInDatabaseByGetAllToursAfterAllOtherTests")
    void checkIfInDatabaseByGetAllToursAfterAllOtherTests() {
        Collection<TourModel> tourModelTest4 = tourDao.getAllTours();
        if(!tourModelTest4.isEmpty()) {
            for (TourModel allTour : tourModelTest4) {
                System.out.println(allTour.toString());
            }
            assertEquals(tourModelTest4.size(), 7);
        } else {
            fail("Couldn't find saved tours in database.");
        }
    }

}