package dfEditor.animation.tests;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import dfEditor.animation.AnimationController;

public class UniqueNameTest
{
  @Test
  public void test()
  {
    assertUniqueName("A", "A");
    assertUniqueName("A 1", "A", "A");
    assertUniqueName("A 2", "A", "A 1", "A");
    //    assertValidName(true, "A", "A 1", "A 2");
    //    assertValidName(false, "A", "A", "A 2");
    //    assertValidName(false, "       ", "A", "A 2");
  }
  @Test
  public void testDesiredBehaviour() throws Exception
  {
    String expected = "A 4";
    String filename = "A";
    String fileName1 = "A 1";
    assertUniqueName(expected, filename, fileName1);
    assertUniqueName("A 4", "A", "A 1", "A 3");
    //    assertUniqueName("A 2", "A", "A 1");
  }
  private void assertUniqueName(String expected, String filename, String... existing)
  {
    String uniqueName = AnimationController.getUniqueName(filename, Arrays.asList(existing));
    assertEquals(expected, uniqueName);
  }
}
