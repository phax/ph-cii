# ph-cii

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/5a5b1e7c59124d0a8922ceb5838a1ea3)](https://www.codacy.com/app/philip/ph-cii?utm_source=github.com&utm_medium=referral&utm_content=phax/ph-cii&utm_campaign=badger)

Java Wrapper for the UN/CEFACT Cross Industry Invoice (using SCRDM schemas).

Latest version is **2.1.1**

This library focuses currently on D16A.1 and D16B for use with the EN resulting from directive 2014/55/EU.

It is licensed under Apache 2.0 license.

It requires at least Java 8 to be used.

# News and noteworthy

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

# Maven usage

Add the following to your pom.xml to use this artifact:

For CII D16A.1:

```xml
<dependency>
  <groupId>com.helger</groupId>
  <artifactId>ph-cii-d16a-1</artifactId>
  <version>2.1.1</version>
</dependency>
```

For CII D16B:

```xml
<dependency>
  <groupId>com.helger</groupId>
  <artifactId>ph-cii-d16b</artifactId>
  <version>2.1.1</version>
</dependency>
```

# References

* Source schemas are located here: http://www.unece.org/cefact/xml_schemas/index.html

---

My personal [Coding Styleguide](https://github.com/phax/meta/blob/master/CodingStyleguide.md) |
On Twitter: <a href="https://twitter.com/philiphelger">@philiphelger</a>
