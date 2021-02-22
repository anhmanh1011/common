package common.utility;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.yody.common.utility.Maps;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

public class MapsTests {

  @BeforeAll
  public static void setup() {

  }

  @Test
  public void testSize() {
    Map<String, String> mapHaveData = new HashMap<String, String>();
    mapHaveData.put("1", "value");
    assertEquals(Maps.size(mapHaveData), 1);
    assertEquals(Maps.size(null), 0);
  }

  @Test
  public void testClear() {
    Map<String, String> mapHaveData = new HashMap<String, String>();
    mapHaveData.put("1", "value");
    Maps.clear(mapHaveData);
    assertEquals(Maps.size(mapHaveData), 0);
  }

  @Test
  public void testIsEmpty() {
    Map<String, String> mapHavenotData = new HashMap<String, String>();
    Map<String, String> mapHaveData = new HashMap<String, String>();
    mapHaveData.put("1", "value");
    assertTrue(Maps.isEmpty(mapHavenotData));
    assertTrue(Maps.isEmpty(null));
    assertFalse(Maps.isEmpty(mapHaveData));
  }

  @Test
  public void testNullToEmpty() {
    assertEquals(Maps.nullToEmpty(null).size(), 0);
  }

}
