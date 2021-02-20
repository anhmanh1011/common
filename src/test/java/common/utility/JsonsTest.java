package common.utility;

import com.yody.common.utility.Jsons;
import common.utility.entity.Sample;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

public class JsonsTest {

  @Test
  public void toJSONTest() throws Exception {
    String json = "{\"id\":\"1\",\"name\":\"sample\"}";
    Sample sample = new Sample("1", "sample");
    Assert.assertEquals(json, Jsons.toJSON(sample));
  }

  @Test
  public void toObjectTest() throws Exception {
    String json = "{\r\n"
        + "   \"id\":\"1\",\r\n"
        + "   \"name\":\"sample\"\r\n"
        + "}";
    Sample sample = new Sample("1", "sample");
    Sample converted = Jsons.toObject(json, Sample.class);
    Assert.assertEquals(sample, converted);
  }

  @Test
  public void getObjectTest() throws Exception {
    String json = "{\r\n"
        + "   \"productId\":\"1\",\r\n"
        + "   \"productName\":\"hello\",\r\n"
        + "   \"Sample\":{\r\n"
        + "      \"id\":\"1\",\r\n"
        + "      \"name\":\"sample\"\r\n"
        + "   }\r\n"
        + "}";
    String json2 = "{\r\n"
        + "   \"productId\":\"1\",\r\n"
        + "   \"productName\":\"product\",\r\n"
        + "   \"Item\":{\r\n"
        + "      \"id\":\"1\",\r\n"
        + "      \"name\":\"item\",\r\n"
        + "      \"Sample\":{\r\n"
        + "         \"id\":\"1\",\r\n"
        + "         \"name\":\"sample\"\r\n"
        + "      }\r\n"
        + "   }\r\n"
        + "}";
    Sample sample = new Sample("1", "sample");
    Sample converted = Jsons.getObject(json, Sample.class);
    Assert.assertEquals(sample, converted);
  }

  @Test
  public void jsonToListTest() throws Exception {
    String json = "[\r\n"
        + "   {\r\n"
        + "      \"id\":\"1\",\r\n"
        + "      \"name\":\"sample 1\"\r\n"
        + "   },\r\n"
        + "   {\r\n"
        + "      \"id\":\"1\",\r\n"
        + "      \"name\":\"sample 2\"\r\n"
        + "   },\r\n"
        + "   {\r\n"
        + "      \"id\":\"1\",\r\n"
        + "      \"name\":\"sample 3\"\r\n"
        + "   }\r\n"
        + "]";
    ArrayList<Sample> lstConverted = Jsons.jsonToList(json, Sample.class);
    Assert.assertEquals(3, lstConverted.size());
  }


}
