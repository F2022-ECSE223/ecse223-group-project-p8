package ca.mcgill.ecse.biketourplus.controller;

import ca.mcgill.ecse.biketourplus.model.*;
import ca.mcgill.ecse.biketourplus.application.BikeTourPlusApplication;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BikeToursFeatureSetController {


    public static String initiateBikeTourCreationProcess() {
        BikeTourPlus btp = BikeTourPlusApplication.getBikeTourPlus();
        return "";
    }

    public static String payForParticipantTrip(String email, String authCode) {
        // error message : "Participant with email address {email} does not exist"
        // error message : "Invalid authorization code"
        return "";
    }

    public static String startWeekTrips(int weekNumber) {
        return "";
    }

    public static String finishParticipantTrip(String email) {
        return "";
    }

    public static String cancelParticipantTrip(String email) {
        // error message : "Participant with email address {email} does not exist"
        return "";
    }
}
