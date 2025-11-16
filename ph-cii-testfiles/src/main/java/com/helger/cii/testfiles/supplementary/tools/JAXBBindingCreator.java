/*
 * Copyright (C) 2016-2025 Philip Helger (www.helger.com)
 * philip[at]helger[dot]com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.helger.cii.testfiles.supplementary.tools;

import java.io.File;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import javax.xml.XMLConstants;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.annotation.Nonempty;
import com.helger.base.array.ArrayHelper;
import com.helger.base.string.StringHelper;
import com.helger.base.string.StringImplode;
import com.helger.base.string.StringReplace;
import com.helger.base.url.URLHelper;
import com.helger.cache.regex.RegExHelper;
import com.helger.collection.commons.CommonsArrayList;
import com.helger.collection.commons.CommonsHashSet;
import com.helger.collection.commons.CommonsTreeMap;
import com.helger.collection.commons.ICommonsList;
import com.helger.collection.commons.ICommonsNavigableMap;
import com.helger.collection.commons.ICommonsSet;
import com.helger.collection.helper.CollectionSort;
import com.helger.datetime.xml.XMLOffsetDate;
import com.helger.datetime.xml.XMLOffsetDateTime;
import com.helger.datetime.xml.XMLOffsetTime;
import com.helger.io.file.FileSystemIterator;
import com.helger.io.file.IFileFilter;
import com.helger.io.resource.FileSystemResource;
import com.helger.jaxb.adapter.AdapterDuration;
import com.helger.jaxb.adapter.AdapterXMLOffsetDate;
import com.helger.jaxb.adapter.AdapterXMLOffsetDateTime;
import com.helger.jaxb.adapter.AdapterXMLOffsetTime;
import com.helger.xml.CXML;
import com.helger.xml.microdom.IMicroDocument;
import com.helger.xml.microdom.IMicroElement;
import com.helger.xml.microdom.MicroDocument;
import com.helger.xml.microdom.serialize.MicroReader;
import com.helger.xml.microdom.serialize.MicroWriter;
import com.helger.xml.microdom.util.XMLMapHandler;
import com.helger.xml.namespace.MapBasedNamespaceContext;
import com.helger.xml.serialize.write.EXMLIncorrectCharacterHandling;
import com.helger.xml.serialize.write.XMLWriterSettings;

/**
 * Utility class that creates:
 * <ul>
 * <li>src/main/jaxb/bindings.xjb - the JAXB binding file</li>
 * <li>src/test/resources/external/schemas/*.xsd.mapping - mapping files that contain the generated
 * explicit enum mappings</li>
 * </ul>
 *
 * @author Philip Helger
 */
public final class JAXBBindingCreator
{
  private static final String JAXB_NS_URI = "https://jakarta.ee/xml/ns/jaxb";
  private static final String XJC_NS_URI = "http://java.sun.com/xml/ns/jaxb/xjc";
  private static final Logger LOGGER = LoggerFactory.getLogger (JAXBBindingCreator.class);

  @NonNull
  private static IMicroDocument _createBaseDoc ()
  {
    final IMicroDocument eDoc = new MicroDocument ();
    eDoc.addComment ("This file is generated. Do NOT edit manually.");
    final IMicroElement eRoot = eDoc.addElementNS (JAXB_NS_URI, "bindings");
    eRoot.setAttributeNS (XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI,
                          "schemaLocation",
                          JAXB_NS_URI + " https://jakarta.ee/xml/ns/jaxb/bindingschema_3_0.xsd");
    eRoot.setAttribute ("version", "3.0");

    final IMicroElement eGlobal = eRoot.addElementNS (JAXB_NS_URI, "globalBindings");
    eGlobal.setAttribute ("typesafeEnumMaxMembers", "2000");
    eGlobal.setAttribute ("typesafeEnumMemberName", "generateError");

    // When in "xjc" namespace "adapter" can be used, when in "jaxb"
    // namespace, parse and print must be used
    eGlobal.addElementNS (XJC_NS_URI, "javaType")
           .setAttribute ("name", XMLOffsetDateTime.class.getName ())
           .setAttribute ("xmlType", "xsd:dateTime")
           .setAttribute ("adapter", AdapterXMLOffsetDateTime.class.getName ());
    eGlobal.addElementNS (XJC_NS_URI, "javaType")
           .setAttribute ("name", XMLOffsetDate.class.getName ())
           .setAttribute ("xmlType", "xsd:date")
           .setAttribute ("adapter", AdapterXMLOffsetDate.class.getName ());
    eGlobal.addElementNS (XJC_NS_URI, "javaType")
           .setAttribute ("name", XMLOffsetTime.class.getName ())
           .setAttribute ("xmlType", "xsd:time")
           .setAttribute ("adapter", AdapterXMLOffsetTime.class.getName ());
    eGlobal.addElementNS (XJC_NS_URI, "javaType")
           .setAttribute ("name", Duration.class.getName ())
           .setAttribute ("xmlType", "xsd:duration")
           .setAttribute ("adapter", AdapterDuration.class.getName ());

    return eDoc;
  }

  @NonNull
  private static ICommonsList <File> _getFileList (final String sPath)
  {
    return CollectionSort.getSorted (new FileSystemIterator (sPath).withFilter (IFileFilter.filenameEndsWith (".xsd")),
                                     Comparator.comparing (File::getName));
  }

  @Nullable
  private static String _getTargetNamespace (@NonNull final IMicroDocument aDoc)
  {
    return aDoc.getDocumentElement ().getAttributeValue (CXML.XML_ATTR_XSD_TARGETNAMESPACE);
  }

  @NonNull
  private static String _convertToPackage (@NonNull final String sNamespaceURI)
  {
    // Lowercase everything
    String s = sNamespaceURI.toLowerCase (Locale.US);

    String [] aParts;
    final URL aURL = URLHelper.getAsURL (sNamespaceURI, false);
    if (aURL != null)
    {
      // Host
      String sHost = aURL.getHost ();

      // Kick static prefix: www.helger.com -> helger.com
      sHost = StringHelper.trimStart (sHost, "www.");

      // Reverse domain: helger.com -> com.helger
      final List <String> x = StringHelper.getExploded ('.', sHost);
      Collections.reverse (x);

      // Path in regular order:
      final String sPath = StringHelper.trimStart (aURL.getPath (), '/');
      x.addAll (StringHelper.getExploded ('/', sPath));

      // Convert to array
      aParts = ArrayHelper.createArray (x, String.class);
    }
    else
    {
      // Kick known prefixes
      for (final String sPrefix : new String [] { "urn:", "http://" })
        if (s.startsWith (sPrefix))
        {
          s = s.substring (sPrefix.length ());
          break;
        }

      // Replace all illegal characters
      s = StringReplace.replaceAll (s, ':', '.');
      s = StringReplace.replaceAll (s, '-', '_');
      aParts = StringHelper.getExplodedArray ('.', s);
    }

    // Split into pieces and replace all illegal package parts (e.g. only
    // numeric) with valid ones
    for (int i = 0; i < aParts.length; ++i)
      aParts[i] = RegExHelper.getAsIdentifier (aParts[i]);

    return StringImplode.getImploded (".", aParts);
  }

  private static void _generateExplicitEnumMapping (@NonNull final IMicroDocument aDoc,
                                                    @NonNull @Nonempty final String sFilename,
                                                    @NonNull final IMicroElement eBindings)
  {
    final ICommonsNavigableMap <String, String> aValueToConstants = new CommonsTreeMap <> ();

    for (final IMicroElement eSimpleType : aDoc.getDocumentElement ()
                                               .getAllChildElements (XMLConstants.W3C_XML_SCHEMA_NS_URI, "simpleType"))
    {
      final IMicroElement eRestriction = eSimpleType.getFirstChildElement (XMLConstants.W3C_XML_SCHEMA_NS_URI,
                                                                           "restriction");
      if (eRestriction == null)
        continue;

      final ICommonsList <IMicroElement> aEnumerations = eRestriction.getAllChildElements (XMLConstants.W3C_XML_SCHEMA_NS_URI,
                                                                                           "enumeration");
      if (aEnumerations.isEmpty ())
        continue;

      final ICommonsSet <String> aUsedNames = new CommonsHashSet <> ();
      final IMicroElement eInnerBindings = eBindings.addElementNS (JAXB_NS_URI, "bindings")
                                                    .setAttribute ("node",
                                                                   "xsd:simpleType[@name='" +
                                                                           eSimpleType.getAttributeValue ("name") +
                                                                           "']");
      final IMicroElement eTypesafeEnumClass = eInnerBindings.addElementNS (JAXB_NS_URI, "typesafeEnumClass");

      for (final IMicroElement eEnumeration : aEnumerations)
      {
        final String sValue = eEnumeration.getAttributeValue ("value");
        // Create an upper case Java identifier, without duplicate "_"
        String sCodeName = RegExHelper.getAsIdentifier (sValue.trim ().toUpperCase (Locale.US)).replaceAll ("_+", "_");

        if (!aUsedNames.add (sCodeName))
        {
          // Ensure uniqueness of the enum member name
          int nSuffix = 1;
          while (true)
          {
            final String sSuffixedCodeName = sCodeName + "_" + nSuffix;
            if (aUsedNames.add (sSuffixedCodeName))
            {
              sCodeName = sSuffixedCodeName;
              break;
            }
            ++nSuffix;
          }
        }

        eTypesafeEnumClass.addElementNS (JAXB_NS_URI, "typesafeEnumMember")
                          .setAttribute ("value", sValue)
                          .setAttribute ("name", sCodeName);
        aValueToConstants.put (sValue, sCodeName);
      }
    }

    // Write out the mapping file for easy later-on resolving
    if (aValueToConstants.isNotEmpty ())
      XMLMapHandler.writeMap (aValueToConstants,
                              new FileSystemResource ("src/test/resources/external/schemas/" + sFilename + ".mapping"));
  }

  public static void runCIIBindingCreation (@NonNull @Nonempty final String sDName, final boolean bWithCodelists)
  {
    final IMicroDocument eDoc = _createBaseDoc ();
    final ICommonsSet <String> aNamespaces = new CommonsHashSet <> ();
    final ICommonsList <String> aParts = new CommonsArrayList <> ("data/standard");
    if (bWithCodelists)
    {
      aParts.add ("identifierlist/standard");
      aParts.add ("codelist/standard");
    }
    for (final String sPart : aParts)
    {
      final String sBasePath = "/resources/external/schemas/" + sDName + "/" + sPart;
      for (final File aFile : _getFileList ("src/main" + sBasePath))
      {
        final String sFilename = aFile.getName ();
        final IMicroDocument aDoc = MicroReader.readMicroXML (new FileSystemResource (aFile));
        final String sTargetNamespace = _getTargetNamespace (aDoc);
        if (!aNamespaces.add (sTargetNamespace))
        {
          LOGGER.info ("Ignored " + sTargetNamespace + " in " + sFilename);
          continue;
        }
        final String sPackageName = _convertToPackage (sTargetNamespace);
        // schemaLocation must be relative to bindings file!
        final IMicroElement eBindings = eDoc.getDocumentElement ()
                                            .addElementNS (JAXB_NS_URI, "bindings")
                                            .setAttribute ("schemaLocation", ".." + sBasePath + "/" + sFilename)
                                            .setAttribute ("node", "/xsd:schema");

        eBindings.addElementNS (JAXB_NS_URI, "schemaBindings")
                 .addElementNS (JAXB_NS_URI, "package")
                 .setAttribute ("name", sPackageName);

        if (sDName.equalsIgnoreCase ("d16a1"))
          _generateExplicitEnumMapping (aDoc, sFilename, eBindings);
      }
    }
    MicroWriter.writeToFile (eDoc,
                             new File ("src/main/jaxb/bindings.xjb"),
                             new XMLWriterSettings ().setIncorrectCharacterHandling (EXMLIncorrectCharacterHandling.DO_NOT_WRITE_LOG_WARNING)
                                                     .setNamespaceContext (new MapBasedNamespaceContext ().addMapping ("",
                                                                                                                       JAXB_NS_URI)
                                                                                                          .addMapping ("xjc",
                                                                                                                       XJC_NS_URI)
                                                                                                          .addMapping ("xsd",
                                                                                                                       XMLConstants.W3C_XML_SCHEMA_NS_URI)
                                                                                                          .addMapping ("xsi",
                                                                                                                       XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI))
                                                     .setPutNamespaceContextPrefixesInRoot (true));
  }
}
