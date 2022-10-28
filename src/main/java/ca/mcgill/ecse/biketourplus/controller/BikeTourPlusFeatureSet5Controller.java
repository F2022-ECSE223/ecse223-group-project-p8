package ca.mcgill.ecse.biketourplus.controller;

import ca.mcgill.ecse.biketourplus.application.*;
import ca.mcgill.ecse.biketourplus.model.*;

// completed by Brian Morava
public class BikeTourPlusFeatureSet5Controller {

  static BikeTourPlus btp = BikeTourPlusApplication.getBikeTourPlus();

  /**
   * Add gear (name and price) to instance of BikeTourPlus
   * 
   * @param name the name of the gear to be added
   * @param pricePerWeek the price of the new gear
   * @return string of error message (empty string if no error)
   * @author Brian Morava
   */
  public static String addGear(String name, int pricePerWeek) {
    String error = "";


    if (name.isEmpty()) {
      error = "The name must not be empty";
      return error;
    }

    if (pricePerWeek < 0) {
      error = "The price per week must be greater than or equal to 0";
      return error;
    }

    for (Gear gear : btp.getGear()) {
      if (gear.getName().equals(name)) {
        error = "A piece of gear with the same name already exists";
        return error;
      }
    }

    for (Combo combo : btp.getCombos()) {
      if (combo.getName().equals(name)) {
        error = "A combo with the same name already exists";
        return error;
      }
    }


    new Gear(name, pricePerWeek, btp);
    return error; // return value will be an empty string if line is executed
  }

  /**
   * Update the attributes of an existing gear
   * 
   * @param oldName the name of the gear item to be changed
   * @param newName the new name of the gear item
   * @param newPricePerWeek the new price of the gear item
   * @return string of error message (empty string if no error)
   * @author Brian Morava
   */
  public static String updateGear(String oldName, String newName, int newPricePerWeek) {

    String error = "";

    if (newPricePerWeek < 0) {
      error = "The price per week must be greater than or equal to 0";
      return error;
    }

    if (newName.isEmpty()) {
      error = "The name must not be empty";
      return error;
    }

    for (Gear gear : btp.getGear()) {
      if (gear.getName().equals(newName) && !newName.equals(oldName)) {
        error = "A piece of gear with the same name already exists";
        return error;
      }
    }


    for (Combo combo : btp.getCombos()) {
      if (combo.getName().equals(newName)) {
        error = "A combo with the same name already exists";
        return error;
      }
    }

    boolean flagFoundName = false;
    for (Gear gear : btp.getGear()) {
      if (gear.getName().equals(oldName)) {
        gear.setName(newName);
        gear.setPricePerWeek(newPricePerWeek);
        flagFoundName = true;
        return error;
      }
    }


    if (!flagFoundName) {
      error = "The piece of gear does not exist";
      return error;
    }

    return error; // return value will be an empty string if line is executed
  }

  /**
   * Delete an instance of gear
   * 
   * @param name the name of the gear to be deleted
   * @return string of error message (empty string if no error)
   * @author Brian Morava
   */
  public static String deleteGear(String name) {

    String error = "";

    for (Gear gear : btp.getGear()) {

      if (gear.getName().equals(name)) {
        if (gear.hasComboItems()) {
          error = "The piece of gear is in a combo and cannot be deleted";
          return error;
        }
        gear.delete();
        return error;
      }
    }

    return error; // return value will be an empty string if line is executed
  }

}
