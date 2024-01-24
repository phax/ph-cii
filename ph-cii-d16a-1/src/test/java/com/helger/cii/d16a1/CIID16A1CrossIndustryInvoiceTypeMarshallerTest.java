/*
 * Copyright (C) 2016-2024 Philip Helger (www.helger.com)
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
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.error.list.IErrorList;
import com.helger.commons.io.resource.ClassPathResource;
import com.helger.commons.io.stream.StreamHelper;
import com.helger.xml.namespace.MapBasedNamespaceContext;

import un.unece.uncefact.data.standard.crossindustryinvoice._100.CrossIndustryInvoiceType;

/**
 * Test class for class {@link CIID16A1CrossIndustryInvoiceTypeMarshaller}.
 *
 * @author Philip Helger
 */
public final class CIID16A1CrossIndustryInvoiceTypeMarshallerTest
{
  private static final Logger LOGGER = LoggerFactory.getLogger (CIID16A1CrossIndustryInvoiceTypeMarshallerTest.class);

  @Test
  public void testReadAndWriteInvoice ()
  {
    final CIID16A1CrossIndustryInvoiceTypeMarshaller m = new CIID16A1CrossIndustryInvoiceTypeMarshaller ();
    m.setNamespaceContext (new MapBasedNamespaceContext ().addMapping ("bla", CCIID16A1.XML_SCHEMA_RSM_NAMESPACE_URL));

    final String sFilename = MockCIID16A1TestDocuments.getTestCrossIndustryInvoices ().get (0);

    // Read from resource
    final CrossIndustryInvoiceType aRead1 = m.read (new ClassPathResource (sFilename));
    assertNotNull (aRead1);

    // Read from byte[]
    final CrossIndustryInvoiceType aRead2 = m.read (StreamHelper.getAllBytes (new ClassPathResource (sFilename)));
    assertNotNull (aRead2);
    assertEquals (aRead1, aRead2);

    // Validate
    final IErrorList aREG1 = m.validate (aRead1);
    final IErrorList aREG2 = m.validate (aRead2);
    assertEquals (aREG1, aREG2);

    // Write
    final String s = m.getAsString (aRead1);
    if (true)
      LOGGER.info (s);
  }
}
