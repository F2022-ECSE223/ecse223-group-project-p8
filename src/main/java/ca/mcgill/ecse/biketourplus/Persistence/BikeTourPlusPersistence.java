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
    save(BikeTourPlusApplication.getBikeTour());
  }

  public static void save(BikeTour btp) {
    serializer.serialize(btp, filename);
  }

  public static BikeTour load() {
    var biketour = (BikeTour) serializer.deserialize(filename);
    // model cannot be loaded - create empty BTMS
    if (biketour == null) {
      biketour = new BikeTour(0, 0, 0, null, null);
    } else {
      biketour.reinitialize();
    }
    return biketour;
  }

}
