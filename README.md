# Credit Card Number

*Credit Card Number* is a Java 6 library that can provide details of a bank issued credit card number. All classes are immutable and thread-safe. The standard `toString()` function formats data in a readable form. Validity is enforced by JUnit tests. Maven is needed for a build.

The goal of this project is to use publicly available documentation to create a reliable Java library to provide information about credit card numbers.

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
```
<dependency>
    <groupId>us.fatehi</groupId>
    <artifactId>credit_card_number</artifactId>
    <version>1.3</version>
</dependency>
```

## Examples

### How to Get Bank Card Information

To get bank card information, use code like:
```
final PrimaryAccountNumber pan = new AccountNumber("5266-0922-0141-6174");
final BankCard card = new BankCard(pan);
System.out.println(card);
```
and you will get this output:
```
Bank Card Information: 
  Primary Account Number: 5266092201416174
  Primary Account Number (Secure): MasterCard-6174
    Major Industry Identifier: 5 - Banking and financial
    Issuer Identification Number: 526609
    Card Brand: MasterCard
    Last Four Digits: 6174
    Passes Luhn Check? Yes
    Is Primary Account Number Valid? Yes
```

### How to Secure the Credit Card Number

If you need the account number information, but want to be secure by not storing the actual primary account number in memory, you can use code like:
```
final PrimaryAccountNumber pan = 
  new AccountNumberInfo(new AccountNumber("5266-0922-0141-6174"));
```
