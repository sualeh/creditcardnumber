[![Build Status](https://travis-ci.org/sualeh/credit_card_number.svg)](https://travis-ci.org/sualeh/credit_card_number)
[![Maven Central](https://img.shields.io/maven-central/v/us.fatehi/credit_card_number.svg)](http://search.maven.org/#search%7Cga%7C1%7Cg%3Aus.fatehi%20credit_card_number)

# Credit Card Number

*Credit Card Number* is a Java library that can provide details of a bank issued credit card number. 

> The goal of this project is to use publicly and freely available documentation to create a reliable Java library to provide information about credit card numbers.

All classes are immutable and thread-safe. The standard `toString()` function formats data in a readable form. Validity is enforced by JUnit tests. 

Java 6 or newer is required. This library deliberately supports Java 6, to make it usable in Android apps. In order to be Java 8 ready, Credit Card Number depends on Stephen Colebourne's [ThreeTen backport project](https://github.com/ThreeTen/threetenbp), a port of [JSR 310](https://jcp.org/en/jsr/detail?id=310) to Java 6. 

Some resources consulted are:
* [Bank card number](http://en.wikipedia.org/wiki/Bank_card_number)
* [How do you detect Credit card type based on number?](http://stackoverflow.com/questions/72768/how-do-you-detect-credit-card-type-based-on-number)  
* [Finding or Verifying Credit Card Numbers](http://www.regular-expressions.info/creditcard.html)
* [Major Industry Identifier](https://en.wikipedia.org/wiki/Bank_card_number#Major_Industry_Identifier_.28MII.29)
* [Luhn Algorithm](http://en.wikipedia.org/wiki/Luhn_algorithm)

## Download

You can [download the jar on the Maven Central Repository](http://search.maven.org/#search%7Cga%7C1%7Ca%3A%22credit_card_number%22).

## Maven Build

To use *Credit Card Number* in your Maven build, include the following dependency. No repositories references are needed, since the jars are in the Maven Central Repository.
```xml
<dependency>
    <groupId>us.fatehi</groupId>
    <artifactId>credit_card_number</artifactId>
    <version>1.8</version>
</dependency>
```

## Examples

### How to Get Bank Card Information

To get bank card information, use code like:
```java
PrimaryAccountNumber pan = new AccountNumber("5266-0922-0141-6174");
ExpirationDate expirationDate = new ExpirationDate(2015, 4);
Name name = new Name("Sualeh", "Fatehi");
BankCard card = new BankCard(pan, expirationDate, name);
System.out.println(card);
```
and you will get this output:
```
Bank Card Information: 
  Raw Account Number: 5266-0922-0141-6174
  Primary Account Number: 5266092201416174
  Primary Account Number (Secure): MasterCard-6174
    Major Industry Identifier: 5 - Banking and financial
    Issuer Identification Number: 526609
    Card Brand: MasterCard
    Last Four Digits: 6174
    Passes Luhn Check? Yes
    Is Primary Account Number Valid? Yes
  Expiration Date: 1504
    Is Expired: No
  Name: Sualeh Fatehi
```

### How to Secure the Credit Card Number

If you need the account number information, but want to be secure by not storing the actual primary account number in memory, you can use code like:
```java
final PrimaryAccountNumber pan = 
  new AccountNumberInfo(new AccountNumber("5266-0922-0141-6174"));
```
