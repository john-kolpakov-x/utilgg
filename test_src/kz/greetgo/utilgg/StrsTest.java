package kz.greetgo.utilgg;

import org.testng.annotations.Test;

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
}