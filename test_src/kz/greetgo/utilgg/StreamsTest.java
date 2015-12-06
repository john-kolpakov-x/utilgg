package kz.greetgo.utilgg;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class StreamsTest {

  @Test
  public void copyStreams() throws Exception {

    byte[] bytes = RND.bytes(1000);
    ByteArrayInputStream in = new ByteArrayInputStream(bytes);

    ByteArrayOutputStream out = new ByteArrayOutputStream();

    //
    //
    Streams.copyStreams(in, out);
    //
    //

    assertThat(out.toByteArray()).isEqualTo(bytes);
  }

  @Test
  public void streamToStr() throws Exception {

    String someStr = RND.str(1000);

    ByteArrayInputStream in = new ByteArrayInputStream(someStr.getBytes("UTF-8"));

    //
    //
    String str = Streams.streamToStr(in);
    //
    //

    assertThat(str).isEqualTo(someStr);

  }
}