package at.technikumwien.tourplanner.test;

import at.technikumwien.tourplanner.BL.DAL.dao.TourLogDao;
import at.technikumwien.tourplanner.BL.DAL.model.TourLog;
import at.technikumwien.tourplanner.BL.DAL.model.TourModel;
import org.junit.jupiter.api.*;

import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TourLogDaoTest {

    TourLogDao tourLogDao = TourLogDao.getInstance();

    @BeforeEach
    void setup() {
    }

    @Test
    @Order(1)
    @DisplayName("saveTourLogAndCheckIfInDatabaseByLogId")
    void saveTourLogAndCheckIfInDatabaseByLogId() {
        if(tourLogDao.saveTourLog(new TourLog(1, 2, "", "Super Tour! Kann ich nur weiterempfehlen", 1, 5, "02:00:00"))){
            Optional<TourLog> tourLogModelTest1 = tourLogDao.getTourLogByLogId(new TourLog(1));
            if(tourLogModelTest1.isPresent()) {
                assertEquals(tourLogModelTest1.get().getComment(), "Super Tour! Kann ich nur weiterempfehlen");
                assertEquals(tourLogModelTest1.get().getRating(), 5);
            } else {
                fail("Couldn't find saved tourLog in database.");
            }
        } else {
            fail("Couldn't save tourLog in database.");
        }
    }

    @Test
    @Order(2)
    @DisplayName("saveTourLogAndCheckIfInDatabaseByTourLogsOfToursByTourid")
    void saveTourLogAndCheckIfInDatabaseByTourLogsOfToursByTourid() {
        if(tourLogDao.saveTourLog(new TourLog(2, 2, "", "Kann ich nur weiterempfehlen, war der Wahnsinn", 1, 5, "03:00:00"))){
            Collection<TourLog> tourLogModelTest2 = tourLogDao.getTourLogsOfToursByTourid(new TourLog(1, 2));
            if(!tourLogModelTest2.isEmpty()) {
                for (TourLog allTourLogs : tourLogModelTest2) {
                    assertEquals(allTourLogs.getTourid_fk(), 2);
                }
            } else {
                fail("Couldn't find saved tourLog in database.");
            }
        } else {
            fail("Couldn't save tourLog in database.");
        }
    }

    @Test
    @Order(3)
    @DisplayName("saveTourLogAndCheckIfInDatabaseByGetAllTourLogs")
    void saveTourLogAndCheckIfInDatabaseByGetAllTourLogs() {
        if(tourLogDao.saveTourLog(new TourLog(3, 2, "", "Super nice Tour! Komme nächstes Jahr Wieder", 4, 5, "04:00:00"))){
            Collection<TourLog> tourLogModelTest3 = tourLogDao.getAllTourLogs();
            if(!tourLogModelTest3.isEmpty()) {
                for (TourLog allTourLogs : tourLogModelTest3) {
                    System.out.println(allTourLogs.toString());
                }
                assertEquals(tourLogModelTest3.size(), 3);
            } else {
                fail("Couldn't find any tourLogs in database.");
            }
        } else {
            fail("Couldn't save tourLog in database.");
        }
    }

    @Test
    @Order(4)
    @DisplayName("updateTourLogNumberThreeAssignItTourIdOne")
    void updateTourLogNumberThreeAssignItTourIdOne() {
        assertTrue(tourLogDao.updateTourLogById(new TourLog(3, 3, "", "Super nice Tour! Komme nächstes Jahr Wieder", 4, 5, "04:00:00")));
    }

    @Test
    @Order(5)
    @DisplayName("checkUpdatedTourLogIfAssignItTourIdOneWorkedCorrectly")
    void checkUpdatedTourLogIfAssignItTourIdOneWorkedCorrectly() {
        Collection<TourLog> tourLogModelTest4 = tourLogDao.getTourLogsOfToursByTourid(new TourLog(3, 3));
        if(!tourLogModelTest4.isEmpty()) {
            for (TourLog allTourLogs : tourLogModelTest4) {
                assertEquals(allTourLogs.getTourid_fk(), 3);
                assertNotEquals(allTourLogs.getTourid_fk(), 2);
            }
        } else {
            fail("Couldn't find saved tourLogs in database.");
        }
    }

    @Test
    @Order(6)
    @DisplayName("saveNewTourInDatabaseAndDeleteThisNewTourLogById")
    void saveNewTourInDatabaseAndDeleteThisNewTourLogById() {
        if(tourLogDao.saveTourLog(new TourLog(4, 5, "", "Eine Traumtour!", 1, 5, "12:00:00"))){
            Optional<TourLog> tourLogModelTest5 = tourLogDao.getTourLogByLogId(new TourLog(4));
            if(tourLogModelTest5.isPresent()) {
                assertEquals(tourLogModelTest5.get().getComment(), "Eine Traumtour!");
                assertEquals(tourLogModelTest5.get().getRating(), 5);
                if(tourLogDao.deleteTourLogById(new TourLog(5))) {
                    Optional<TourLog> tourLogModelTest6 = tourLogDao.getTourLogByLogId(new TourLog(5));
                    assertTrue(tourLogModelTest6.isEmpty());
                } else {
                    fail("Couldn't delete new tour.");
                }
            } else {
                fail("Couldn't find saved tourLog in database.");
            }
        } else {
            fail("Couldn't save tourLog in database.");
        }
    }

    @Test
    @Order(7)
    @DisplayName("deleteTourLogsOfTourOneByTourIdWithTourLogModel")
    void deleteTourLogsOfTourOneByTourIdWithTourLogModel() {
        if(tourLogDao.deleteTourLogsOfTourByTourId(new TourLog(1, 2))){
            Collection<TourLog> tourLogModelTest7 = tourLogDao.getTourLogsOfToursByTourid(new TourLog(1, 2));
            assertTrue(tourLogModelTest7.isEmpty());
        } else {
            fail("Couldn't delete tourLog in database.");
        }
    }

    @Test
    @Order(8)
    @DisplayName("deleteTourLogsOfTourOneByTourIdWithTourLogModel")
    void deleteTourLogsOfTourOneByTourIdWithTourModel() {
        if(tourLogDao.deleteTourLogsOfTourByTourId(new TourModel(3))){
            Collection<TourLog> tourLogModelTest8 = tourLogDao.getTourLogsOfToursByTourid(new TourLog(1, 3));
            assertTrue(tourLogModelTest8.isEmpty());
        } else {
            fail("Couldn't delete tourLog in database.");
        }
    }

    @Test
    @Order(9)
    @DisplayName("checkIfInDatabaseByGetAllTourLogsAfterAllOtherTests")
    void checkIfInDatabaseByGetAllTourLogsAfterAllOtherTests() {
        Collection<TourLog> tourLogModelTest9 = tourLogDao.getAllTourLogs();
        if(!tourLogModelTest9.isEmpty()) {
            for (TourLog allTour : tourLogModelTest9) {
                System.out.println(allTour.toString());
            }
            assertEquals(tourLogModelTest9.size(), 1);
        } else {
            fail("Couldn't find saved tours in database.");
        }
    }
}