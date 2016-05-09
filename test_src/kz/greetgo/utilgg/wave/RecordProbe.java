package kz.greetgo.utilgg.wave;

import edu.cmu.sphinx.api.Configuration;

public class RecordProbe {
  public static void main(String[] args) {
    Configuration configuration = new Configuration();
    configuration
      .setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
    configuration
      .setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
    configuration
      .setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");
  }
}
