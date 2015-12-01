package kz.greetgo.utilgg;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ObjectAccessorTest {

  private class Asd {
    public String onlyField;

    private String property;
    private boolean propertyBool;

    public String propertyField;

    public String getProperty() {
      return property + "_get";
    }

    public void setProperty(String property) {
      this.property = property + "_set";
    }

    public boolean isPropertyBool() {
      return !propertyBool;
    }

    public void setPropertyBool(boolean propertyBool) {
      this.propertyBool = propertyBool;
    }

    public String getPropertyField() {
      return propertyField + "_GET";
    }

    public void setPropertyField(String propertyField) {
      this.propertyField = propertyField + "_SET";
    }
  }

  private ObjectAccessor objectAccessor;

  @BeforeMethod
  public void createObjectAccessor() {
    objectAccessor = new ObjectAccessor();
  }

  @Test
  public void getGettersMap_onlyField() throws Exception {

    Map<String, ValueGetter> gettersMap = objectAccessor.getGettersMap(Asd.class);

    assertThat(gettersMap).containsKeys("onlyField");

    String rndOnlyField = RND.str(10);

    Asd asd = new Asd();
    asd.onlyField = rndOnlyField;

    Object rndValue = gettersMap.get("onlyField").getValue(asd);
    assertThat(rndValue).isEqualTo(rndOnlyField);

  }

  @Test
  public void getGettersMap_property() throws Exception {

    Map<String, ValueGetter> gettersMap = objectAccessor.getGettersMap(Asd.class);

    assertThat(gettersMap).containsKeys("property");

    String rndValue = RND.str(10);

    Asd asd = new Asd();
    asd.setProperty(rndValue);

    Object propertyFieldValue = gettersMap.get("property").getValue(asd);
    assertThat(propertyFieldValue).isEqualTo(rndValue + "_set_get");

  }

  @Test
  public void getGettersMap_propertyBool() throws Exception {

    Map<String, ValueGetter> gettersMap = objectAccessor.getGettersMap(Asd.class);

    assertThat(gettersMap).containsKeys("propertyBool");

    boolean rndValue = RND.bool();

    Asd asd = new Asd();
    asd.setPropertyBool(rndValue);

    Object actual = gettersMap.get("propertyBool").getValue(asd);
    assertThat(actual).isEqualTo(!rndValue);

  }

  @Test
  public void getGettersMap_propertyField() throws Exception {

    Map<String, ValueGetter> gettersMap = objectAccessor.getGettersMap(Asd.class);

    assertThat(gettersMap).containsKeys("propertyField");

    String rndValue = RND.str(10);

    Asd asd = new Asd();
    asd.setPropertyField(rndValue);

    Object actual = gettersMap.get("propertyField").getValue(asd);
    assertThat(actual).isEqualTo(rndValue + "_SET_GET");

  }
}
