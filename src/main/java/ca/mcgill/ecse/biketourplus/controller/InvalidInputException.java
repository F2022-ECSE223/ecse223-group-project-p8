package ca.mcgill.ecse.biketourplus.controller;

public class InvalidInputException extends Exception {

  public InvalidInputException(String errorMessage) {
    super(errorMessage);
  }

}
