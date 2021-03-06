package edu.neu.ccs.cs5010.assignment4.ridesharemodel.validators;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import edu.neu.ccs.cs5010.assignment4.ridesharemodel.Registration;
import edu.neu.ccs.cs5010.assignment4.ridesharemodel.driver.Country;
import edu.neu.ccs.cs5010.assignment4.ridesharemodel.driver.Driver;
import edu.neu.ccs.cs5010.assignment4.ridesharemodel.driver.License;
import edu.neu.ccs.cs5010.assignment4.ridesharemodel.driver.Name;
import edu.neu.ccs.cs5010.assignment4.ridesharemodel.insurance.Insurance;
import edu.neu.ccs.cs5010.assignment4.ridesharemodel.vehicle.Vehicle;
import java.time.LocalDate;
import java.util.HashSet;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * VehicleValidator Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Oct 13, 2018</pre>
 */
public class VehicleValidatorTest {

  private String firstName;
  private String lastName;
  private String firstName2;
  private String lastName2;
  private LocalDate birthday;
  private String licenseNum;
  private Country issueCountry;
  private Country issueCountry2;
  private Integer year;
  private Integer year2;
  private LocalDate issueDate;
  private LocalDate issueDate2;
  private LocalDate expirationDate;
  private String make;
  private String model;
  private String color;
  private String licensePlate;

  private Integer legalDrivingAge;
  private Integer legalLicenseMonth;

  private Name name;
  private Name name2;
  private License license;
  private Driver owner;
  private Driver owner2;
  private Vehicle vehicle;
  private HashSet<Driver> insuredDrivers;
  private LocalDate date;

  private TimeCalculator timeCalculator;
  private Insurance insurance;
  private Registration registration;

  private VehicleValidator newVehicleValidator;

  @Before
  public void before() throws Exception {
    firstName = "Tim";
    lastName = "Zhu";
    firstName2 = "Jack";
    lastName2 = "Ma";
    year = 2012;
    year2 = 2000;
    birthday = LocalDate.of(1988, 4, 23);
    issueDate = LocalDate.of(2015, 9, 1);
    issueDate2 = LocalDate.of(2018, 9, 1);
    expirationDate = LocalDate.of(2020, 01, 01);
    name = new Name(firstName, lastName);
    name2 = new Name(firstName2, lastName2);
    licenseNum = "123abc";
    issueCountry = Country.US;
    issueCountry2 = Country.Canada;
    make = "Honda";
    model = "Civic";
    color = "Black";
    licensePlate = "BBB5421";

    legalDrivingAge = 21;
    legalLicenseMonth = 6;

    license = new License(licenseNum, name, birthday, issueCountry, issueDate, expirationDate);
    owner = new Driver(name, birthday, license);
    owner2 = new Driver(name2, birthday, license);
    vehicle = new Vehicle(owner, year, make, model, color, licensePlate);
    insuredDrivers = new HashSet<Driver>();
    date = LocalDate.of(2025, 01, 01);
    insurance = new Insurance(owner, vehicle, insuredDrivers, expirationDate);

    timeCalculator = new TimeCalculator();
    registration = new Registration(owner, insurance, vehicle);
    newVehicleValidator = new VehicleValidator(registration);
  }

  @After
  public void after() throws Exception {
  }

  /**
   * Method: validateVehicleAge(Registration registration)
   */
  @Test
  public void testValidateVehicleAge() throws Exception {
    assertTrue(newVehicleValidator.validateVehicleAge(registration));
    Registration registration2 = new Registration(
        new Driver(name, birthday,
            new License(licenseNum, name, birthday, issueCountry2, issueDate, expirationDate)),
        insurance, new Vehicle(owner, year2, make, model, color, licensePlate));
    assertFalse(newVehicleValidator.validateVehicleAge(registration2));
  }

  /**
   * Method: masterValidator(Registration registration)
   */
  @Test
  public void testMasterValidator() throws Exception {
    assertTrue(newVehicleValidator.masterValidator(registration));
  }


} 
