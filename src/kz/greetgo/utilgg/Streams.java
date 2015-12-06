package kz.greetgo.utilgg;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Streams {
  public static long copyStreams(InputStream in, ByteArrayOutputStream out) {
    try {
      return copyStreamsEx(in, out);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static long copyStreamsEx(InputStream in, ByteArrayOutputStream out) throws IOException {
    byte buffer[] = new byte[8 * 1024];
    long ret = 0L;
    while (true) {
      int count = in.read(buffer);
      if (count < 0) return ret;
      out.write(buffer, 0, count);
      ret += count;
    }
  }

  public static String streamToStr(InputStream in) {
    if (in == null) return null;

    try {
      ByteArrayOutputStream bout = new ByteArrayOutputStream();
      copyStreamsEx(in, bout);
      return bout.toString("UTF-8");
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }
}
