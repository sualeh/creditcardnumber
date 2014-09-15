# Credit Card Number

*Credit Card Number* is a Java 7 library that can provide details of a bank issued credit card number. All classes are immutable and thread-safe. The standard `toString()` function formats data in a readable form. Validity is enforced by JUnit tests. Maven is needed for a build.

## Download

You can download the 
[jar on the Maven Central Repository](http://search.maven.org/#search%7Cga%7C1%7Ca%3A%22credit_card_number%22).

## Maven Build

To use *Credit Card Number* in your Maven build, include the following dependency. No repositories references are needed, since the jars are in the Maven Central Repository.
```
<dependency>
    <groupId>us.fatehi</groupId>
    <artifactId>credit_card_number</artifactId>
    <version>1.0</version>
</dependency>
```

## Examples

### How to Get Bank Card Information

To get bank card information, use code like:
```
final PrimaryAccountNumber pan = new AccountNumber("371449635398431");
final BankCard card = new BankCard(pan);
System.out.println(card);
```
and you will get this output:
```
Bank Card Information: 
  Primary Account Number: 371449635398431
  Primary Account Number (Secure): AmericanExpress-8431
    MII: 3 - Travel and entertainment and banking/financial
    IIN: 371449
    Card Brand: AmericanExpress
    Last Four Digits: 8431
    Passes Luhn Check: Yes
```

### How to Secure the Credit Card Number

If you need the account number information, but want to be secure by not storing the actual primary account number in memory, you can use code like:
```
final PrimaryAccountNumber pan = new AccountNumberInfo(new AccountNumber("371449635398431"));
final BankCard card = new BankCard(pan);
System.out.println(card);
```
