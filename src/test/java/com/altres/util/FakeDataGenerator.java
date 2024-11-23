package com.altres.util;

import java.text.DecimalFormat;
import java.util.Random;

import com.github.javafaker.Faker;

/**
 * FakeDataGenerator generates fake data using the Faker library.
 */
public class FakeDataGenerator {

  public static final Faker faker = new Faker();

  /**
   * Generates a random first name using the Faker library.
   *
   * @return
   */
  public static String getRandomFirstName() {
    return faker.name().firstName();
  }

  /**
   * Generates a random full name using the Faker library.
   *
   * @return
   */
  public static String getRandomFullName() {
    return faker.name().fullName();
  }

  /**
   * Generates a random numeric string of the specified length using the Faker library.
   *
   * @param count
   * @return
   */
  public static String getRandomNumber(int count) {
    return faker.number().digits(count);
  }

  /**
   * Generates a random email address using the Faker library.
   *
   * @return
   */
  public static String getEmail() {
    return faker.internet().emailAddress();
  }

  /**
   * Generates a random full name (intended as a question template name) using the Faker library.
   *
   * @return
   */
  public static String getQuestionTemplateName() {
    return faker.name().fullName();
  }

  /**
   * Generates a random last name using the Faker library.
   *
   * @return
   */
  public static String getRandomLastName() {
    return faker.name().lastName();
  }

  /**
   * Generates a title using the Faker library.
   *
   * @return
   */
  public static String getRandomTitle() {
    return faker.name().title();
  }


  /**
   * Generates a random word.
   *
   * @return
   */
  public static String getRandomWord() {
    return faker.book().title();
  }

  /**
   * Generates a random company name.
   *
   * @return
   */
  public static String getCompany() {
    return faker.company().name();
  }

  /**
   * Generates a random full address.
   *
   * @return
   */
  public static String getAddress() {
    return faker.address().fullAddress();
  }

  /**
   * Generates a random city.
   *
   * @return
   */
  public static String getCity() {
    return faker.address().city();
  }

  /**
   * Generates a random paragraph of text.
   *
   * @return
   */
  public static String getDescription() {
    return faker.lorem().paragraph();
  }

  /**
   * Generates a random string of the specified length.
   *
   * @param characterCount the length of the string
   * @return a random string of the given length
   */
  public static String generateRandomStringWithCount(int characterCount) {
    if (characterCount <= 0) {
      throw new IllegalArgumentException("Character count must be greater than zero.");
    }
    return faker.lorem().fixedString(characterCount);
  }

  /**
   * Generates a random  username using the Faker library.
   *
   * @return
   */
  public static String getUsername() {
    return faker.name().username();
  }

  /**
   * Generates a random password using the Faker library.The password will have a length between 8 and 16 characters,
   * and will include a mix of upper and lower case letters, numbers, and special characters.
   *
   * @return
   */
  public static String getPassword() {

    return faker.internet().password(8, 16, true, true, true);
  }

  /**
   * Method to generate random number ignoring specific number.
   *
   * @param max
   * @param min
   * @param ignore
   * @return
   */
  public static int randomNumberIgnoringSpecificNumber(int max, int min, double ignore) {
    Random rand = new Random();
    int random = 0;
    for (int index = min; index <= max; index++) {
      int n = (rand.nextInt(max) + min);
      if (n != ignore) {
        random = n;
        break;
      }
    }
    return random;
  }

  /**
   * Returns a string in phone number format.
   *
   * @return
   */
  public static String getRandomPhoneNumber() {
    Random rand = new Random();
    int num1 = (rand.nextInt(7) + 1) * 100 + (rand.nextInt(8) * 10) + rand.nextInt(8);
    int num2 = rand.nextInt(743);
    int num3 = rand.nextInt(10000);

    DecimalFormat df3 = new DecimalFormat("000");
    DecimalFormat df4 = new DecimalFormat("0000");

    return df3.format(num1) + df3.format(num2) + df4.format(num3);
  }

  /**
   * Returns an integer between the lower bounds (inclusive) and the upper bounds (exclusive).
   *
   * @param lowerBounds
   * @param upperBounds
   * @return
   */
  public static int getRandomNumber(int lowerBounds, int upperBounds) {
    return lowerBounds + (int) (Math.random() * (upperBounds - lowerBounds));
  }
}

