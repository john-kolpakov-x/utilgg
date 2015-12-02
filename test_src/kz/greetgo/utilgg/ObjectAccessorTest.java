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

    public String readOnlyField;

    public String writeOnlyField;

    public String readWriteField;

    public void setWriteOnly(String writeOnlyField) {
      this.writeOnlyField = writeOnlyField;
    }

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

    public String getReadOnly() {
      return readOnlyField;
    }

    public String getReadWrite() {
      return readWriteField;
    }

    public void setReadWrite(String readWriteField) {
      this.readWriteField = readWriteField;
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
  public void getSettersMap_onlyField() throws Exception {

    Map<String, ValueSetter> settersMap = objectAccessor.getSettersMap(Asd.class);

    assertThat(settersMap).containsKeys("onlyField");

    String rndOnlyField = RND.str(10);

    Asd asd = new Asd();

    settersMap.get("onlyField").setValue(asd, rndOnlyField);

    assertThat(asd.onlyField).isEqualTo(rndOnlyField);

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
  public void getSettersMap_property() throws Exception {

    Map<String, ValueSetter> settersMap = objectAccessor.getSettersMap(Asd.class);

    assertThat(settersMap).containsKeys("property");

    String rndValue = RND.str(10);

    Asd asd = new Asd();

    settersMap.get("property").setValue(asd, rndValue);

    assertThat(asd.getProperty()).isEqualTo(rndValue + "_set_get");

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
  public void getSettersMap_propertyBool() throws Exception {

    Map<String, ValueSetter> settersMap = objectAccessor.getSettersMap(Asd.class);

    assertThat(settersMap).containsKeys("propertyBool");

    boolean rndValue = RND.bool();

    Asd asd = new Asd();

    settersMap.get("propertyBool").setValue(asd, rndValue);

    assertThat(asd.isPropertyBool()).isEqualTo(!rndValue);

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

  @Test
  public void getSettersMap_propertyField() throws Exception {

    Map<String, ValueSetter> settersMap = objectAccessor.getSettersMap(Asd.class);

    assertThat(settersMap).containsKeys("propertyField");

    String rndValue = RND.str(10);

    Asd asd = new Asd();

    settersMap.get("propertyField").setValue(asd, rndValue);

    assertThat(asd.getPropertyField()).isEqualTo(rndValue + "_SET_GET");

  }

  @Test
  public void getAcceptors_write() throws Exception {

    Map<String, ValueAcceptor> acceptorMap = objectAccessor.getAcceptorsMap(Asd.class);

    assertThat(acceptorMap).containsKeys("readOnly", "writeOnly", "readWrite");

    Asd asd = new Asd();

    String writeOnly = RND.str(10);
    String readWrite = RND.str(10);

    //write
    acceptorMap.get("writeOnly").setValue(asd, writeOnly);
    acceptorMap.get("readWrite").setValue(asd, readWrite);

    assertThat(asd.writeOnlyField).isEqualTo(writeOnly);
    assertThat(asd.readWriteField).isEqualTo(readWrite);
  }

  @Test
  public void getAcceptors_read() throws Exception {

    Map<String, ValueAcceptor> acceptorMap = objectAccessor.getAcceptorsMap(Asd.class);

    assertThat(acceptorMap).containsKeys("readOnly", "writeOnly", "readWrite");

    Asd asd = new Asd();

    String readOnly = RND.str(10);
    String readWrite = RND.str(10);

    //read
    asd.readOnlyField = readOnly;
    asd.readWriteField = readWrite;

    assertThat(acceptorMap.get("readOnly").getValue(asd)).isEqualTo(readOnly);
    assertThat(acceptorMap.get("readWrite").getValue(asd)).isEqualTo(readWrite);
  }


  @Test
  public void getAcceptors_isReadOnly_isWriteOnly() throws Exception {

    Map<String, ValueAcceptor> acceptorMap = objectAccessor.getAcceptorsMap(Asd.class);

    assertThat(acceptorMap).containsKeys("readOnly", "writeOnly", "readWrite");

    assertThat(acceptorMap.get("readOnly").isReadOnly()).isTrue();
    assertThat(acceptorMap.get("readOnly").isWriteOnly()).isFalse();

    assertThat(acceptorMap.get("writeOnly").isReadOnly()).isFalse();
    assertThat(acceptorMap.get("writeOnly").isWriteOnly()).isTrue();

    assertThat(acceptorMap.get("readWrite").isReadOnly()).isFalse();
    assertThat(acceptorMap.get("readWrite").isWriteOnly()).isFalse();
  }
}
