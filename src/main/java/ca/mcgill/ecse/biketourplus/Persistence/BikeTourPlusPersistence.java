package ca.mcgill.ecse.biketourplus.Persistence;

import ca.mcgill.ecse.biketourplus.application.BikeTourPlusApplication;
import ca.mcgill.ecse.biketourplus.model.BikeTour;
import ca.mcgill.ecse.biketourplus.model.BikeTourPlus;

public class BikeTourPlusPersistence {

  private static String filename = "data.json";
  private static JsonSerializer serializer = new JsonSerializer("ca.mcgill.ecse.biketourplus");

  public static void setFilename(String filename) {
    BikeTourPlusPersistence.filename = filename;
  }

  public static void save() {
    save(BikeTourPlusApplication.getBikeTourPlus());
  }

  public static void save(BikeTourPlus btp) {
    serializer.serialize(btp, filename);
  }

  public static BikeTourPlus load() {
    var biketour = (BikeTourPlus) serializer.deserialize(filename);
    // model cannot be loaded - create empty BikeTour
    if (biketour == null) {
      biketour = new BikeTourPlus(null, 0, 0);
    } else {
      biketour.reinitialize();
    }
    return biketour;
  }

}