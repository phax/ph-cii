/**
 * Copyright (C) 2016-2021 Philip Helger (www.helger.com)
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
package com.helger.cii.d16b;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.w3c.dom.Document;

import com.helger.commons.error.list.IErrorList;
import com.helger.commons.io.resource.ClassPathResource;
import com.helger.commons.mock.CommonsTestHelper;
import com.helger.xml.serialize.read.DOMReader;
import com.helger.xml.serialize.read.DOMReaderSettings;

import un.unece.uncefact.data.standard.crossindustryinvoice._100.CrossIndustryInvoiceType;

/**
 * Some cross functionality testing
 *
 * @author Philip Helger
 */
public final class CIID16BFuncTest
{
  @Test
  public void testReadAndWriteInvoice ()
  {
    for (final String sFilename : MockCIID16BTestDocuments.getTestDocuments (ECIID16BDocumentType.CROSS_INDUSTRY_INVOICE))
    {
      // Read
      final Document aDoc = DOMReader.readXMLDOM (new ClassPathResource (sFilename),
                                                  new DOMReaderSettings ().setSchema (ECIID16BDocumentType.CROSS_INDUSTRY_INVOICE.getSchema ()));
      assertNotNull (sFilename, aDoc);
      final CrossIndustryInvoiceType aCIIObject = CIID16BReader.crossIndustryInvoice ().read (aDoc);
      assertNotNull (sFilename, aCIIObject);

      // Validate
      IErrorList aErrors = CIID16BValidator.crossIndustryInvoice ().validate (aCIIObject);
      assertNotNull (sFilename, aErrors);
      assertFalse (sFilename, aErrors.containsAtLeastOneError ());

      // write again
      final Document aDoc2 = CIID16BWriter.crossIndustryInvoice ().getAsDocument (aCIIObject);
      assertNotNull (aDoc2);
      assertEquals (aDoc.getDocumentElement ().getNamespaceURI (), aDoc2.getDocumentElement ().getNamespaceURI ());
      assertEquals (aDoc.getDocumentElement ().getLocalName (), aDoc2.getDocumentElement ().getLocalName ());

      // read again
      final CrossIndustryInvoiceType aCIIObject2 = CIID16BReader.crossIndustryInvoice ().read (aDoc2);
      assertNotNull (sFilename, aCIIObject2);
      CommonsTestHelper.testDefaultImplementationWithEqualContentObject (aCIIObject, aCIIObject2);
      CommonsTestHelper.testDefaultImplementationWithEqualContentObject (aCIIObject, aCIIObject.clone ());

      // Validate
      aErrors = CIID16BValidator.crossIndustryInvoice ().validate (aCIIObject2);
      assertNotNull (sFilename, aErrors);
      assertFalse (sFilename, aErrors.containsAtLeastOneError ());
    }

    // Validate empty instance
    final IErrorList aErrors = CIID16BValidator.crossIndustryInvoice ().validate (new CrossIndustryInvoiceType ());
    assertNotNull (aErrors);
    assertTrue (aErrors.containsAtLeastOneError ());
  }
}
