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
package com.helger.cii.d22b;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.w3c.dom.Document;

import com.helger.diagnostics.error.list.IErrorList;
import com.helger.io.resource.ClassPathResource;
import com.helger.unittest.support.TestHelper;
import com.helger.xml.serialize.read.DOMReader;

import un.unece.uncefact.data.standard.cii.d22b.CrossIndustryInvoiceType;

/**
 * Some cross functionality testing
 *
 * @author Philip Helger
 */
public final class CIID22BFuncTest
{
  @Test
  public void testReadAndWriteInvoice ()
  {
    final CIID22BCrossIndustryInvoiceTypeMarshaller m = new CIID22BCrossIndustryInvoiceTypeMarshaller ();
    for (final String sFilename : MockCIID22BTestDocuments.getTestCrossIndustryInvoices ())
    {
      // Read
      final Document aDoc = DOMReader.readXMLDOM (new ClassPathResource (sFilename));
      assertNotNull (sFilename, aDoc);
      final CrossIndustryInvoiceType aCIIObject = m.read (aDoc);
      assertNotNull (sFilename, aCIIObject);

      // Validate
      IErrorList aErrors = m.validate (aCIIObject);
      assertNotNull (sFilename, aErrors);
      assertFalse (sFilename, aErrors.containsAtLeastOneError ());

      // write again
      final Document aDoc2 = m.getAsDocument (aCIIObject);
      assertNotNull (aDoc2);
      assertEquals (aDoc.getDocumentElement ().getNamespaceURI (), aDoc2.getDocumentElement ().getNamespaceURI ());
      assertEquals (aDoc.getDocumentElement ().getLocalName (), aDoc2.getDocumentElement ().getLocalName ());

      // read again
      final CrossIndustryInvoiceType aCIIObject2 = m.read (aDoc2);
      assertNotNull (sFilename, aCIIObject2);
      TestHelper.testDefaultImplementationWithEqualContentObject (aCIIObject, aCIIObject2);
      TestHelper.testDefaultImplementationWithEqualContentObject (aCIIObject, aCIIObject.clone ());

      // Validate
      aErrors = m.validate (aCIIObject2);
      assertNotNull (sFilename, aErrors);
      assertFalse (sFilename, aErrors.containsAtLeastOneError ());
    }

    // Validate empty instance
    final IErrorList aErrors = m.validate (new CrossIndustryInvoiceType ());
    assertNotNull (aErrors);
    assertTrue (aErrors.containsAtLeastOneError ());
  }
}
