package kz.greetgo.utilgg;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ObjectAccessor {

  private final Map<Class<?>, Map<String, ValueGetter>> getterCache = new ConcurrentHashMap<>();

  public Map<String, ValueGetter> getGettersMap(Class<?> clazz) {

    {
      Map<String, ValueGetter> getterMap = getterCache.get(clazz);
      if (getterMap != null) return getterMap;
    }

    Map<String, ValueGetter> ret = new HashMap<>();

    appendFieldGetters(ret, clazz);

    appendMethodGetters(ret, clazz);

    ret = Collections.unmodifiableMap(ret);
    getterCache.put(clazz, ret);
    return ret;
  }

  private void appendMethodGetters(Map<String, ValueGetter> ret, Class<?> clazz) {

    for (Method method : clazz.getMethods()) {

      if (method.getParameterCount() == 0) {

        String name = method.getName();

        if (name.length() > 3 && name.startsWith("get")) {
          ret.put(Strs.firstLower(name.substring(3)), object -> {
            try {
              return method.invoke(object);
            } catch (IllegalAccessException | InvocationTargetException e) {
              throw new RuntimeException(e);
            }
          });

          continue;
        }

        if (name.length() > 2 && name.startsWith("is") && method.getReturnType().equals(Boolean.TYPE)) {

          ret.put(Strs.firstLower(name.substring(2)), object -> {
            try {
              return method.invoke(object);
            } catch (IllegalAccessException | InvocationTargetException e) {
              throw new RuntimeException(e);
            }
          });

          continue;
        }

        if ("a".equals("b")) {
          System.out.println("asd");
        }

      }
    }

  }

  private void appendFieldGetters(Map<String, ValueGetter> ret, Class<?> clazz) {
    for (Field field : clazz.getFields()) {
      ret.put(field.getName(), object -> {
        try {
          return field.get(object);
        } catch (IllegalAccessException e) {
          throw new RuntimeException(e);
        }
      });
    }
  }

  private final Map<Class<?>, Map<String, ValueSetter>> setterCache = new ConcurrentHashMap<>();

  public Map<String, ValueSetter> getSettersMap(Class<?> clazz) {

    {
      Map<String, ValueSetter> ret = setterCache.get(clazz);
      if (ret != null) return ret;
    }

    Map<String, ValueSetter> ret = new HashMap<>();

    appendFieldSetters(ret, clazz);

    appendMethodSetters(ret, clazz);

    ret = Collections.unmodifiableMap(ret);
    setterCache.put(clazz, ret);
    return ret;
  }


  private void appendFieldSetters(Map<String, ValueSetter> ret, Class<?> clazz) {

    for (Field field : clazz.getFields()) {
      ret.put(field.getName(), (object, value) -> {
        try {
          field.set(object, value);
        } catch (IllegalAccessException e) {
          throw new RuntimeException(e);
        }
      });
    }

  }

  private void appendMethodSetters(Map<String, ValueSetter> ret, Class<?> clazz) {

    for (Method method : clazz.getMethods()) {
      String name = method.getName();
      if (name.startsWith("set") && name.length() > 3 && method.getParameterCount() == 1) {
        ret.put(Strs.firstLower(name.substring(3)), (object, value) -> {
          try {
            method.invoke(object, value);
          } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
          }
        });
      }
    }

  }
}
