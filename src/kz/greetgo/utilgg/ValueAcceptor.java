package kz.greetgo.utilgg;

public interface ValueAcceptor extends ValueGetter, ValueSetter {
  boolean isReadOnly();

  boolean isWriteOnly();
}
