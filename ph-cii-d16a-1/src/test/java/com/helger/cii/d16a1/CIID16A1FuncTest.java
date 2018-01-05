/**
 * Copyright (C) 2016-2018 Philip Helger (www.helger.com)
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
package com.helger.cii.d16a1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

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
public final class CIID16A1FuncTest
{
  @Test
  public void testReadAndWriteInvoice () throws SAXException
  {
    for (final String sFilename : MockCIID16A1TestDocuments.getTestDocuments (ECIID16A1DocumentType.CROSS_INDUSTRY_INVOICE))
    {
      // Read
      final Document aDoc = DOMReader.readXMLDOM (new ClassPathResource (sFilename),
                                                  new DOMReaderSettings ().setSchema (ECIID16A1DocumentType.CROSS_INDUSTRY_INVOICE.getSchema ()));
      assertNotNull (sFilename, aDoc);
      final CrossIndustryInvoiceType aUBLObject = CIID16A1Reader.crossIndustryInvoice ().read (aDoc);
      assertNotNull (sFilename, aUBLObject);

      // Validate
      IErrorList aErrors = CIID16A1Validator.crossIndustryInvoice ().validate (aUBLObject);
      assertNotNull (sFilename, aErrors);
      assertFalse (sFilename, aErrors.containsAtLeastOneError ());

      // write again
      final Document aDoc2 = CIID16A1Writer.crossIndustryInvoice ().getAsDocument (aUBLObject);
      assertNotNull (aDoc2);
      assertEquals (aDoc.getDocumentElement ().getNamespaceURI (), aDoc2.getDocumentElement ().getNamespaceURI ());
      assertEquals (aDoc.getDocumentElement ().getLocalName (), aDoc2.getDocumentElement ().getLocalName ());

      // read again
      final CrossIndustryInvoiceType aUBLObject2 = CIID16A1Reader.crossIndustryInvoice ().read (aDoc2);
      assertNotNull (sFilename, aUBLObject2);
      CommonsTestHelper.testDefaultImplementationWithEqualContentObject (aUBLObject, aUBLObject2);
      CommonsTestHelper.testDefaultImplementationWithEqualContentObject (aUBLObject, aUBLObject.clone ());

      // Validate
      aErrors = CIID16A1Validator.crossIndustryInvoice ().validate (aUBLObject2);
      assertNotNull (sFilename, aErrors);
      assertFalse (sFilename, aErrors.containsAtLeastOneError ());
    }

    // Validate
    final IErrorList aErrors = CIID16A1Validator.crossIndustryInvoice ().validate (new CrossIndustryInvoiceType ());
    assertNotNull (aErrors);
    assertTrue (aErrors.containsAtLeastOneError ());
  }
}
