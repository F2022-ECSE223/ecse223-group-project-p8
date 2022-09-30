package ca.mcgill.ecse.biketourplus.application;

import java.sql.Date;
import ca.mcgill.ecse.biketourplus.model.BikeTourPlus;

public class BikeTourPlusApplication {

  private static BikeTourPlus bikeTourPlus;

  public static void main(String[] args) {
    // TODO Start the application user interface here
  }

  public static BikeTourPlus getBikeTourPlus() {
    if (bikeTourPlus == null) {
      // these attributes are default, you should set them later with the setters
      bikeTourPlus = new BikeTourPlus(new Date(0), 0, 0);
    }
    return bikeTourPlus;
  }

}
