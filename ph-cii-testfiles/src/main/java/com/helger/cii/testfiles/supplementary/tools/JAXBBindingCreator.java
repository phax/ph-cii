/**
 * Copyright (C) 2016 Philip Helger (www.helger.com)
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
import java.util.Comparator;
import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.xml.XMLConstants;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.collection.ArrayHelper;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.collection.ext.CommonsArrayList;
import com.helger.commons.collection.ext.CommonsHashSet;
import com.helger.commons.collection.ext.CommonsTreeMap;
import com.helger.commons.collection.ext.ICommonsList;
import com.helger.commons.collection.ext.ICommonsNavigableMap;
import com.helger.commons.collection.ext.ICommonsSet;
import com.helger.commons.io.file.FileHelper;
import com.helger.commons.io.file.filter.IFileFilter;
import com.helger.commons.io.file.iterate.FileSystemIterator;
import com.helger.commons.io.resource.FileSystemResource;
import com.helger.commons.regex.RegExHelper;
import com.helger.commons.string.StringHelper;
import com.helger.commons.url.URLHelper;
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
 * <li>src/test/resources/schemas/*.xsd.mapping - mapping files that contain the
 * generated explicit enum mappings</li>
 * </ul>
 *
 * @author Philip Helger
 */
public final class JAXBBindingCreator
{
  public static final String JAXB_NS_URI = "http://java.sun.com/xml/ns/jaxb";
  public static final String XJC_NS_URI = "http://java.sun.com/xml/ns/jaxb/xjc";

  @Nonnull
  private static IMicroDocument _createBaseDoc ()
  {
    final IMicroDocument eDoc = new MicroDocument ();
    final IMicroElement eRoot = eDoc.appendElement (JAXB_NS_URI, "bindings");
    eRoot.setAttribute (CXML.XML_NS_XSI,
                        "schemaLocation",
                        JAXB_NS_URI + " http://java.sun.com/xml/ns/jaxb/bindingschema_2_0.xsd");
    eRoot.setAttribute ("version", "2.1");

    final IMicroElement eGlobal = eRoot.appendElement (JAXB_NS_URI, "globalBindings");
    eGlobal.setAttribute ("typesafeEnumMaxMembers", "2000");
    eGlobal.setAttribute ("typesafeEnumMemberName", "generateError");
    return eDoc;
  }

  @Nonnull
  private static Iterable <File> _getFileList (final String sPath)
  {
    return CollectionHelper.getSorted (new FileSystemIterator (sPath).withFilter (IFileFilter.filenameEndsWith (".xsd")),
                                       Comparator.comparing (File::getName));
  }

  @Nullable
  private static String _getTargetNamespace (@Nonnull final IMicroDocument aDoc)
  {
    return aDoc.getDocumentElement ().getAttributeValue (CXML.XML_ATTR_XSD_TARGETNAMESPACE);
  }

  @Nonnull
  private static String _convertToPackage (@Nonnull final String sNamespaceURI)
  {
    // Lowercase everything
    String s = sNamespaceURI.toLowerCase (Locale.US);

    String [] aParts;
    final URL aURL = URLHelper.getAsURL (sNamespaceURI);
    if (aURL != null)
    {
      // Host
      String sHost = aURL.getHost ();

      // Kick static prefix: www.helger.com -> helger.com
      sHost = StringHelper.trimStart (sHost, "www.");

      // Reverse domain: helger.com -> com.helger
      final ICommonsList <String> x = StringHelper.getExploded ('.', sHost);
      x.reverse ();

      // Path in regular order:
      final String sPath = StringHelper.trimStart (aURL.getPath (), '/');
      x.addAll (StringHelper.getExploded ('/', sPath));

      // Convert to array
      aParts = ArrayHelper.newArray (x, String.class);
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
      s = StringHelper.replaceAll (s, ':', '.');
      s = StringHelper.replaceAll (s, '-', '_');
      aParts = StringHelper.getExplodedArray ('.', s);
    }

    // Split into pieces and replace all illegal package parts (e.g. only
    // numeric) with valid ones
    for (int i = 0; i < aParts.length; ++i)
      aParts[i] = RegExHelper.getAsIdentifier (aParts[i]);

    return StringHelper.getImploded (".", aParts);
  }

  public static void runCIIBindingCreation (@Nonnull @Nonempty final String sDName, final boolean bWithCodelists)
  {
    final IMicroDocument eDoc = _createBaseDoc ();
    final ICommonsSet <String> aNamespaces = new CommonsHashSet<> ();
    final ICommonsList <String> aParts = new CommonsArrayList<> ("data/standard");
    if (bWithCodelists)
    {
      aParts.add ("identifierlist/standard");
      aParts.add ("codelist/standard");
    }
    for (final String sPart : aParts)
    {
      final String sBasePath = "/resources/schemas/" + sDName + "/" + sPart;
      for (final File aFile : _getFileList ("src/main" + sBasePath))
      {
        final String sFilename = aFile.getName ();
        final IMicroDocument aDoc = MicroReader.readMicroXML (new FileSystemResource (aFile));
        final String sTargetNamespace = _getTargetNamespace (aDoc);
        if (!aNamespaces.add (sTargetNamespace))
        {
          System.out.println ("Ignored " + sTargetNamespace + " in " + sFilename);
          continue;
        }
        final String sPackageName = _convertToPackage (sTargetNamespace);
        // schemaLocation must be relative to bindings file!
        final IMicroElement eBindings = eDoc.getDocumentElement ()
                                            .appendElement (JAXB_NS_URI, "bindings")
                                            .setAttribute ("schemaLocation", ".." + sBasePath + "/" + sFilename)
                                            .setAttribute ("node", "/xsd:schema");

        eBindings.appendElement (JAXB_NS_URI, "schemaBindings")
                 .appendElement (JAXB_NS_URI, "package")
                 .setAttribute ("name", sPackageName);

        if (sFilename.equals (""))
          _generateExplicitEnumMapping (aDoc, sFilename, eBindings);
      }
    }
    MicroWriter.writeToStream (eDoc,
                               FileHelper.getOutputStream ("src/main/jaxb/bindings.xjb"),
                               new XMLWriterSettings ().setIncorrectCharacterHandling (EXMLIncorrectCharacterHandling.DO_NOT_WRITE_LOG_WARNING)
                                                       .setNamespaceContext (new MapBasedNamespaceContext ().addMapping (XMLConstants.DEFAULT_NS_PREFIX,
                                                                                                                         JAXB_NS_URI)
                                                                                                            .addMapping ("xsd",
                                                                                                                         CXML.XML_NS_XSD)
                                                                                                            .addMapping ("xsi",
                                                                                                                         CXML.XML_NS_XSI))
                                                       .setPutNamespaceContextPrefixesInRoot (true));
  }

  private static void _generateExplicitEnumMapping (@Nonnull final IMicroDocument aDoc,
                                                    @Nonnull @Nonempty final String sFilename,
                                                    @Nonnull final IMicroElement eBindings)
  {
    final ICommonsNavigableMap <String, String> aValueToConstants = new CommonsTreeMap<> ();

    for (final IMicroElement eSimpleType : aDoc.getDocumentElement ().getAllChildElements (CXML.XML_NS_XSD,
                                                                                           "simpleType"))
    {
      final IMicroElement eRestriction = eSimpleType.getFirstChildElement (CXML.XML_NS_XSD, "restriction");
      if (eRestriction == null)
        continue;

      final ICommonsList <IMicroElement> aEnumerations = eRestriction.getAllChildElements (CXML.XML_NS_XSD,
                                                                                           "enumeration");
      if (aEnumerations.isEmpty ())
        continue;

      final ICommonsSet <String> aUsedNames = new CommonsHashSet<> ();
      final IMicroElement eInnerBindings = eBindings.appendElement (JAXB_NS_URI, "bindings")
                                                    .setAttribute ("node",
                                                                   "xsd:simpleType[@name='" +
                                                                           eSimpleType.getAttributeValue ("name") +
                                                                           "']");
      final IMicroElement eTypesafeEnumClass = eInnerBindings.appendElement (JAXB_NS_URI, "typesafeEnumClass");

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

        eTypesafeEnumClass.appendElement (JAXB_NS_URI, "typesafeEnumMember")
                          .setAttribute ("value", sValue)
                          .setAttribute ("name", sCodeName);
        aValueToConstants.put (sValue, sCodeName);
      }
    }

    // Write out the mapping file for easy later-on resolving
    if (aValueToConstants.isNotEmpty ())
      XMLMapHandler.writeMap (aValueToConstants,
                              new FileSystemResource ("src/test/resources/schemas/" + sFilename + ".mapping"));
  }
}
