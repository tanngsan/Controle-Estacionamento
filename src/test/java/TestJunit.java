

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestJunit {
   @Test
   public void testSetup() {
      String str= "I am done with JUnit setup";
      assertEquals("I am done with JUnit setup", str);
   }
}