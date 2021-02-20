package common.utility;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.yody.common.utility.Files;
import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class FilesTests {

  private String projectPath = System.getProperty("user.dir");

  @BeforeAll
  public static void setup() {

  }

  @Test
  public void testGetName() {
    assertEquals(Files.getName(new File("/test.txt")), "test.txt");
  }

  @Test
  public void testExitsts() {
    assertTrue(Files.exists("/Volumes/DATA/DEV/YODY/Backend/yody/yody-common/test.txt"));
    assertFalse(Files.exists("/test.txt"));
  }

  @Test
  public void testRead() throws IOException {
    assertNotNull(Files.read(new File("/Volumes/DATA/DEV/YODY/Backend/yody/yody-common/test.txt")));

  }

  @Test
  public void testMkdir() {
    assertTrue(Files.mkdir("/Volumes/DATA/DEV/YODY/Backend/yody/yody-common/test.txt"));
  }

  @Test
  public void testMkdirs() {
    assertTrue(Files.mkdirs("/Volumes/DATA/DEV/YODY/Backend/yody/yody-common/test.txt"));
  }

  @Test
  public void renameFromFileTest() throws IOException {
    File file = new File(projectPath + File.separator + "source-sample.txt");
    Files.rename(file, "renamed-sample.txt");
    File newFile = new File(projectPath + File.separator + "renamed-sample.txt");
    assertTrue(newFile.exists());
    System.out.println(newFile.getAbsolutePath());
  }

  @Test
  public void renameFromNameTest() throws IOException {
    String src = projectPath + File.separator + "source-sample.txt";
    String dst = "renamed-sample.txt";
    assertTrue(Files.rename(src, dst));
  }

}
