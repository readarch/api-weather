package cl.com.readarch.apiweather.crosscutting.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Util {

  private Util(){}

  // Function to get List from Stream
  public static <T> List<T> getListFromStream(Stream<T> stream) {
    // Convert the Stream to List and return the List
    return stream.collect(Collectors.toCollection(ArrayList::new));
  }

}