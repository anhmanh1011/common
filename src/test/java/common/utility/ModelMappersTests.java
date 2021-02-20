package common.utility;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.yody.common.utility.ModelMappers;
import com.yody.common.utility.Strings;
import common.utility.entity.Item;
import common.utility.entity.Sample;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ModelMappersTests {

  @BeforeAll
  public static void setup() {

  }

  @Test
  public void testIsEmpty() {
    assertTrue(Strings.isEmpty(null));
    assertTrue(Strings.isEmpty(""));
    assertFalse(Strings.isEmpty("test"));
  }

  @Test
  public void testTrimWithMax() {
    assertEquals(Strings.trim("testTrimWithMax   ", 4), "test");
  }

  @Test
  public void mapTest() {
    Item item = new Item("4tr", "item1", "1");
    Sample sample = ModelMappers.map(item, Sample.class);
    assertNotNull(sample);
    System.out.println(sample);
  }


  @Test
  public void mapListTest() {
    List<Item> lstItem = new ArrayList<>();
    lstItem.add(new Item("4tr", "item1", "1"));
    lstItem.add(new Item("6tr", "item2", "2"));
    lstItem.add(new Item("3tr", "item3", "3"));
    List<Sample> lstSample = ModelMappers.mapList(lstItem, Sample.class);
    assertNotNull(lstSample);
    System.out.println(lstSample);
  }

  @Test
  public void mapToTest() {
    Sample sample = new Sample("1", "sample1");
    Item item = new Item();
    ModelMappers.mapTo(sample, item);
    assertNotNull(item);
    System.out.println(item);
  }
}
