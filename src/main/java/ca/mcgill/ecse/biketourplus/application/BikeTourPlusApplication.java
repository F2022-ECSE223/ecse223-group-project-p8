package ca.mcgill.ecse.biketourplus.application;

import java.sql.Date;
import ca.mcgill.ecse.biketourplus.javafx.fxml.BikeTourPlusFXMLView;
import ca.mcgill.ecse.biketourplus.model.BikeTourPlus;
import javafx.application.Application;

public class BikeTourPlusApplication {

  private static BikeTourPlus bikeTourPlus;
  public enum UIMode { SWING, JAVAFX_PURE_JAVA, JAVAFX_FXML }
  public static final UIMode UI_MODE = UIMode.JAVAFX_FXML;

  public static void main(String[] args) {
    // TODO Start the application user interface here
    Application.launch(BikeTourPlusFXMLView.class, args);
  }

  public static BikeTourPlus getBikeTourPlus() {
    if (bikeTourPlus == null) {
      // these attributes are default, you should set them later with the setters
      bikeTourPlus = new BikeTourPlus(new Date(0), 0, 0);
    }
    return bikeTourPlus;
  }

}
