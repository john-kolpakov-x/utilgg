package kz.greetgo.utilgg.bml;

import java.util.stream.IntStream;

public interface Block {
  BlockSizeType type();

  long size();

  byte[] getBytes();

  IntStream getInputStream();
}
