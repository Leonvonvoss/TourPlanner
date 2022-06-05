package at.technikumwien.tourplanner.test;

import at.technikumwien.tourplanner.BL.DAL.model.TourModel;
import org.junit.jupiter.api.*;

import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TourModelTest {

    @BeforeEach
    void setup() {
    }

    @Test
    @Order(1)
    @DisplayName("checkGetName")
    void checkGetName() {
        TourModel tourModel1 = new TourModel(1, "Tour Test 1", "This is a test tour 1.", "120 km", "02:00:00", "Vienna", "Mödling", "On Foot");
        assertEquals(tourModel1.getName(), "Tour Test 1");
    }

    @Test
    @Order(2)
    @DisplayName("checkSetName")
    void checkSetName() {
        TourModel tourModel2 = new TourModel(1, "Tour Test 2", "This is a test tour 2.", "10 km", "00:50:00", "Vienna", "Mödling", "By Plane");
        tourModel2.setName("Tour Funny Test 2");
        assertEquals(tourModel2.getName(), "Tour Funny Test 2");
    }

    @Test
    @Order(3)
    @DisplayName("checkEquals")
    void checkEquals() {
        TourModel tourModel3 = new TourModel(1, "Tour Test 2", "This is a test tour 3.", "350 km", "04:00:00", "Innsbruck", "Liesing", "By Car");
        TourModel tourModel4 = new TourModel(1, "Tour Funny Test 2", "This is a test tour 3.", "350 km", "04:00:00", "Innsbruck", "Liesing", "By Car");
        tourModel3.setName("Tour Funny Test 2");
        assertNotEquals(tourModel3, tourModel4);
    }


}