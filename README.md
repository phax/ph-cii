# ph-cii

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.helger.cii/ph-cii-parent-pom/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.helger.cii/ph-cii-parent-pom) 
[![javadoc](https://javadoc.io/badge2/com.helger.cii/ph-cii-parent-pom/javadoc.svg)](https://javadoc.io/doc/com.helger.cii/ph-cii-parent-pom)
[![CodeCov](https://codecov.io/gh/phax/ph-cii/branch/master/graph/badge.svg)](https://codecov.io/gh/phax/ph-cii)

Java Wrapper for the UN/CEFACT Cross Industry Invoice (using SCRDM schemas).

This library focuses currently on D16A.1 and D16B for use with the EN resulting from directive 2014/55/EU.
Additionally it supports D22B for support for the Zugferd 2.3+ versions.

It is licensed under Apache 2.0 license.

It requires at least Java 17 to be used.

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

For CII D22B (since v3.1.0):

```xml
<dependency>
  <groupId>com.helger.cii</groupId>
  <artifactId>ph-cii-d22b</artifactId>
  <version>x.y.z</version>
</dependency>
```

Note: prior to v2.2.0 the Maven groupId was `com.helger`.

Note: v10.1.0 was accidentally published to Maven and should NOT be used

# References

* Source schemas are located here: http://www.unece.org/cefact/xml_schemas/index.html

# News and noteworthy

v4.1.1 - 2025-11-17
* Added support for the Cross Domain Acknowledgement and Response (CDAR) format in D22B

v4.1.0 - 2025-11-16
* Updated to ph-commons 12.1.0
* Using JSpecify annotations

v4.0.0 - 2025-08-25
* Requires Java 17 as the minimum version
* Updated to ph-commons 12.0.0
* Removed all deprecated methods marked for removal

v3.1.0 - 2024-12-05
* Switched JAXB Maven plugin to `org.jvnet.jaxb:jaxb-maven-plugin`
* Added support for D22B in new submodule `ph-cii-d22b`

v3.0.2 - 2023-07-31
* Updated to ph-commons 11.1

v3.0.1 - 2023-04-14
* Extended API to replace deprecated API
* Moved XML schema files to new folder

v3.0.0 - 2023-01-08
* Using Java 11 as the baseline
* Updated to ph-commons 11
* Using JAXB 4.0 as the baseline
* Deprecated classes `CIID16(A1|B)(Reader|Validator|Writer)` in favour of `CIID16(A1|B)CrossIndustryInvoiceTypeMarshaller`

v2.5.0 - 2021-05-02
* Updated to ph-commons 10.1

v2.4.0 - 2021-03-21
* Updated to ph-commons 10

v2.3.2 - 2021-03-18
* Updated to ph-commons 9.5.5
* Changed the JAXB binding for `xs:dateTime` to `java.time.OffsetDateTime`

v2.3.1 - 2020-09-17
* Updated to Jakarta JAXB 2.3.3

v2.3.0 - 2020-08-30
* Updated to ph-commons 9.4.7
* Using Java 8 date and time classes for JAXB created classes

v2.2.0 - 2020-05-26
* Changed Maven groupId to `com.helger.cii`

v2.1.1 - 2019-01-26
* Extended the API of the created classes with `ph-jaxb22-plugin:ph-value-extender`

v2.1.0 - 2018-11-12
* Requires at least ph-commons 9.2.0

v2.0.1 - 2018-02-01
* Fixed D16B namespace URL versions (use `100` instead of `20`)

v2.0.0 - 2017-11-09
* Updated to ph-commons 9.0.0

v1.0.0 - 2016-11-04
* Initial version with D16A.1 and D16B (SCRDM)

---

My personal [Coding Styleguide](https://github.com/phax/meta/blob/master/CodingStyleguide.md) |
It is appreciated if you star the GitHub project if you like it.