import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.exceptions.base.MockitoException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;

//@ExtendWith(MockitoExtension.class)
public class HorseTest {

    @Test
    public void horseConstructorWithNullName() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse(null, 1, 1)
        );
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t"})
    public void horseConstructorWithBlankName(String name) {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse(name, 1, 1)
        );
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    public void horseConstructorWithNegativeSpeed() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse("Name", -1, 1)
        );
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    public void horseConstructorWithNegativeDistance() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse("Name", 1, -1)
        );
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    public void getName() {
        String name = "Blackbird";
        Horse horse = new Horse(name, 1, 1);
        assertEquals(name, horse.getName());
    }

    @Test
    public void getSpeed() {
        int speed = 9;
        Horse horse = new Horse("Blackbird", speed, 1);
        assertEquals(speed, horse.getSpeed());
    }

    @Test
    public void getDistance() {
        int distance = 9;
        Horse horse = new Horse("Blackbird", 1, distance);
        assertEquals(distance, horse.getDistance());
        Horse horseWithoutDistance = new Horse("Blackbird", 1);
        assertEquals(0, horseWithoutDistance.getDistance());
    }

    @Test
    public void moveHorse() {
        try (MockedStatic<Horse> mockedHorse = mockStatic(Horse.class)) {
            mockedHorse.when(() -> Horse.getRandomDouble(.2, .9)).thenReturn(.5);
            Horse horse = new Horse("Blackbird", 1, 1);
            horse.move();
            mockedHorse.verify(
                    () -> Horse.getRandomDouble(.2, .9),
                    times(1)
            );
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {.3, .5, .7})
    public void move_ShouldUpdateDistanceCorrectly(double mockValue) {
        try (MockedStatic<Horse> mockedHorse = mockStatic(Horse.class)) {
            mockedHorse.when(() -> Horse.getRandomDouble(.2, .9)).thenReturn(mockValue);
            Horse horse = new Horse("Blackbird", 1, 1);
            horse.move();

            double expextedDistance = 1.0 + 1.0 * mockValue;
            assertEquals(expextedDistance, horse.getDistance(), 0.001);
        }
    }

}
