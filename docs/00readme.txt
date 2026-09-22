Overall page: https://unece.org/trade/uncefact/xml-schemas

Naming convention for the schema ZIP files in the subfolders:
  *_coupled.zip   - the qdt:QualifiedDataType schema module imports all code list and
                    identifier scheme schemas, so code list values are enumerated
  *_uncoupled.zip - the qdt:QualifiedDataType schema module declares all code list types
                    locally as open "xsd:token", without importing the code list schemas

UN/CEFACT publishes both variants inside a single download. Those downloads were split
into one ZIP per variant, so that every schema ZIP names the variant it contains.

This project uses the "uncoupled" variant for all CII and CDAR versions, because the
generated JAXB classes are only meant to read and write documents - not to enforce code
lists. The only exception is D16A.1, for which no uncoupled publication is available.
