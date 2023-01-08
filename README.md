# ph-cii

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/5a5b1e7c59124d0a8922ceb5838a1ea3)](https://www.codacy.com/app/philip/ph-cii?utm_source=github.com&utm_medium=referral&utm_content=phax/ph-cii&utm_campaign=badger)

Java Wrapper for the UN/CEFACT Cross Industry Invoice (using SCRDM schemas).

This library focuses currently on D16A.1 and D16B for use with the EN resulting from directive 2014/55/EU.

It is licensed under Apache 2.0 license.

It requires at least Java 11 to be used.


# Maven usage

Add the following to your pom.xml to use this artifact:, replacing `x.y.z` with the real version number.

For CII D16A.1:

```xml
<dependency>
  <groupId>com.helger.cii</groupId>
  <artifactId>ph-cii-d16a-1</artifactId>
  <version>x.y.z</version>
</dependency>
```

For CII D16B:

```xml
<dependency>
  <groupId>com.helger.cii</groupId>
  <artifactId>ph-cii-d16b</artifactId>
  <version>x.y.z</version>
</dependency>
```

Note: prior to v2.2.0 the Maven groupId was `com.helger`.

#
# Gradle considerations

This project relies on JDK version based Maven profile activation.
See https://github.com/phax/ph-jaxb-pom#gradle-usage for help on this specific issue. 

# References

* Source schemas are located here: http://www.unece.org/cefact/xml_schemas/index.html

# News and noteworthy

* v3.0.0 - 2023-01-08
    * Using Java 11 as the baseline
    * Updated to ph-commons 11
    * Using JAXB 4.0 as the baseline
    * Deprecated classes `CIID16(A1|B)(Reader|Validator|Writer)` in favour of `CIID16(A1|B)CrossIndustryInvoiceTypeMarshaller`
* v2.5.0 - 2021-05-02
    * Updated to ph-commons 10.1
* v2.4.0 - 2021-03-21
    * Updated to ph-commons 10
* v2.3.2 - 2021-03-18
    * Updated to ph-commons 9.5.5
    * Changed the JAXB binding for `xs:dateTime` to `java.time.OffsetDateTime`
* v2.3.1 - 2020-09-17
    * Updated to Jakarta JAXB 2.3.3
* v2.3.0 - 2020-08-30
    * Updated to ph-commons 9.4.7
    * Using Java 8 date and time classes for JAXB created classes
* v2.2.0 - 2020-05-26
    * Changed Maven groupId to `com.helger.cii`
* v2.1.1 - 2019-01-26
    * Extended the API of the created classes with `ph-jaxb22-plugin:ph-value-extender`
* v2.1.0 - 2018-11-12
    * Requires at least ph-commons 9.2.0
* v2.0.1 - 2018-02-01
    * Fixed D16B namespace URL versions (use `100` instead of `20`)
* v2.0.0 - 2017-11-09
    * Updated to ph-commons 9.0.0
* v1.0.0 - 2016-11-04
    * Initial version with D16A.1 and D16B (SCRDM)

---

My personal [Coding Styleguide](https://github.com/phax/meta/blob/master/CodingStyleguide.md) |
On Twitter: <a href="https://twitter.com/philiphelger">@philiphelger</a> |
Kindly supported by [YourKit Java Profiler](https://www.yourkit.com)