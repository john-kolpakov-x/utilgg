package kz.greetgo.utilgg;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class StrsTest {

  @Test
  public void upper() throws Exception {
    assertThat(Strs.upper("lower_UPPER")).isEqualTo("LOWER_UPPER");
    assertThat(Strs.upper(null)).isNull();
    assertThat(Strs.upper("")).isEmpty();
    assertThat(Strs.upper("x")).isEqualTo("X");
  }

  @Test
  public void lower() throws Exception {
    assertThat(Strs.lower("lower_UPPER")).isEqualTo("lower_upper");
    assertThat(Strs.lower(null)).isNull();
    assertThat(Strs.lower("")).isEmpty();
    assertThat(Strs.lower("X")).isEqualTo("x");
  }

  @Test
  public void firstLower() throws Exception {
    assertThat(Strs.firstLower("HelloWorld")).isEqualTo("helloWorld");
    assertThat(Strs.firstLower(null)).isNull();
    assertThat(Strs.firstLower("")).isEmpty();
    assertThat(Strs.firstLower("X")).isEqualTo("x");
  }

  @Test
  public void firstUpper() throws Exception {
    assertThat(Strs.firstUpper("helloWorld")).isEqualTo("HelloWorld");
    assertThat(Strs.firstUpper(null)).isNull();
    assertThat(Strs.firstUpper("")).isEmpty();
    assertThat(Strs.firstUpper("x")).isEqualTo("X");
  }

  @Test
  public void join_array() throws Exception {

    String s1 = RND.str(10);
    String s2 = RND.str(10);
    String s3 = RND.str(10);

    String j = RND.str(10);

    //
    //
    String str = Strs.join(j, new Object[]{s1, s2, s3});
    //
    //

    assertThat(str).isEqualTo(s1 + j + s2 + j + s3);

  }

  @Test
  public void join_iterable() throws Exception {

    String s1 = RND.str(10);
    String s2 = RND.str(10);
    String s3 = RND.str(10);

    String j = RND.str(10);

    List<String> list = new ArrayList<>();
    list.add(s1);
    list.add(s2);
    list.add(s3);

    //
    //
    String str = Strs.join(j, list);
    //
    //

    assertThat(str).isEqualTo(s1 + j + s2 + j + s3);

  }
}
