import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class HippodromeTest {
    @Test
    public void constructorWithNull() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(null)
                );
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    public void constructorWithEmptyList() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(new ArrayList<>())
                );
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }
}
