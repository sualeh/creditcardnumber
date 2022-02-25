<!-- markdownlint-disable MD040 MD041 MD004 MD012 -->
[![Quick Build](https://github.com/sualeh/creditcardnumber/workflows/Quick%20Build/badge.svg)](https://github.com/sualeh/creditcardnumber/actions?query=workflow%3A%22Quick+Build%22)
[![The Central Repository](https://img.shields.io/maven-central/v/us.fatehi/creditcardnumber.svg)](https://search.maven.org/search?q=g:us.fatehi%20creditcardnumber*)


# Credit Card Number

*Credit Card Number* is a Java library that can provide details of a bank issued
credit card number.

(Also see [Magnetic Track Parser](https://github.com/sualeh/magnetictrackparser).)


## Resources

> **The goal of this project is to use publicly and freely available documentation
to create a reliable Java library to provide information about credit card numbers.**

Some resources consulted are:

- [Payment card number](https://en.wikipedia.org/wiki/Payment_card_number)
- [How do you detect Credit card type based on number?](http://stackoverflow.com/questions/72768/how-do-you-detect-credit-card-type-based-on-number)
- [Finding or Verifying Credit Card Numbers](http://www.regular-expressions.info/creditcard.html)
- [Major Industry Identifier](https://en.wikipedia.org/wiki/ISO/IEC_7812#Major_industry_identifier)
- [Luhn Algorithm](http://en.wikipedia.org/wiki/Luhn_algorithm)
- [Where can I find test credit card numbers?](https://www.paypal.com/us/smarthelp/article/where-can-i-find-test-credit-card-numbers-ts2157)


## Design Principles

- All classes are immutable and thread-safe
- Secure data follows standards in the
[Java Cryptography Architecture (JCA) Reference Guide](https://docs.oracle.com/javase/8/docs/technotes/guides/security/crypto/CryptoSpec.html#PBEEx)
- The standard `toString()` function formats data in a readable form
- Internationalization of card numbers is supported
- Validity is enforced by JUnit 5 tests
- Java 8 or newer is required
- Android friendly


## Download and Use in Projects

You can [download the jar on the Maven Central Repository](https://search.maven.org/artifact/us.fatehi/creditcardnumber).
The [download page](https://search.maven.org/artifact/us.fatehi/creditcardnumber) has instructions on how to use the library in your Maven or Gradle build.


## Examples

### How to Get Bank Card Information

To get bank card information, use code like:

```java
AccountNumber pan = AccountNumbers.completeAccountNumber("5266-0922-0141-6174");
ExpirationDate expiration = new ExpirationDate(2015, 4);
Name name = new Name("Sualeh", "Fatehi");
ServiceCode serviceCode = new ServiceCode("225");
BankCard card = new BankCard(pan, expiration, name, serviceCode);
System.out.println(card);
```

and you will get this output:

```
Bank Card Information:
  Raw Account Number: 5266-0922-0141-6174
  Primary Account Number: 5266092201416174
    Major Industry Identifier: 5 - Banking and financial
    Issuer Identification Number: 52660922
    Card Brand: MasterCard
    Last Four Digits: 6174
    Passes Luhn Check? Yes
    Is Primary Account Number Valid? Yes
  Expiration Date: 2015-04
    Is Expired? Yes
  Name: Sualeh Fatehi
  Service Code:
    2 - Interchange: International interchange. Technology: Integrated circuit card.
    2 - Authorization Processing: By issuer.
    5 - Allowed Services: Goods and services only. PIN Requirements: PIN required.
```


### How to Secure the Credit Card Number

If you need the account number information, but want to be secure by not storing the actual primary account number in memory, you can use code like:

```java
AccountNumber pan = AccountNumbers.completeAccountNumber("5266-0922-0141-6174");
pan.dispose();
System.out.println(pan.getAccountNumber());
```

and you will get this output:

```
null

```

The account number is no longer retained in memory. Even though the memory has been cleared of
the account number, you may want to allow the original reference to be garbage collected by
doing this:

```java
// (Call dispose() first)
AccountNumber securePan = pan.toSecureAccountNumber();
```


### How to Encrypt the Credit Card Number

If you need the account number information, but want to encrypt it using a cryptographic algorithm such as AES, you can use code like:

```java
AccountNumber sealedPan = AccountNumbers.sealedAccountNumber("5266-0922-0141-6174", cipher);
System.out.println(sealedPan.getAccountNumber());
```

and you will get this output:

```
null

```

The account number can be decrypted by doing this:

```java
AccountNumber pan = AccountNumbers.completeAccountNumber(sealedPan, key);
System.out.println(pan.getAccountNumber());
```

The cipher and key are from [javax.crypto and java.security](https://docs.oracle.com/en/java/javase/17/security/java-security-overview1.html).


### Internationalization is Supported

You can use code like:

```java
AccountNumber pan = AccountNumbers.completeAccountNumber("५२६६ ०९२२ ०१४१ ६१७४");
System.out.println(pan.getAccountNumber());
```

and you will get this output:

```
5266092201416174
```


### Look-ahead Typing

The Credit Card Number library allows look-ahead typing and identifies the
card brand by at least the first four digits of the card number.

You can use code like:

```java
CardBrand cardBrand = CardBrand.from("5366");
System.out.println(cardBrand);
```

and you will get this output:

```
MasterCard
```
