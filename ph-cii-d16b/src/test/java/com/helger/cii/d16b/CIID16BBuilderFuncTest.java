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
package com.helger.cii.d16b;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.error.list.IErrorList;
import com.helger.commons.io.resource.ClassPathResource;
import com.helger.commons.io.stream.StreamHelper;
import com.helger.xml.namespace.MapBasedNamespaceContext;

import un.unece.uncefact.data.standard.crossindustryinvoice._13.CrossIndustryInvoiceType;

/**
 * Test class for classes {@link CIID16BReader}, {@link CIID16BValidator} and
 * {@link CIID16BWriter}.
 *
 * @author Philip Helger
 */
public final class CIID16BBuilderFuncTest
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (CIID16BBuilderFuncTest.class);

  @Test
  @Ignore ("No test files")
  public void testReadAndWriteInvoice ()
  {
    final CIID16BReader <CrossIndustryInvoiceType> aReader = new CIID16BReader<> (CrossIndustryInvoiceType.class);
    final CIID16BValidator <CrossIndustryInvoiceType> aValidator = new CIID16BValidator<> (CrossIndustryInvoiceType.class);
    final CIID16BWriter <CrossIndustryInvoiceType> aWriter = new CIID16BWriter<> (CrossIndustryInvoiceType.class).setFormattedOutput (true);
    aWriter.setNamespaceContext (new MapBasedNamespaceContext ().addMapping ("bla",
                                                                             ECIID16BDocumentType.CROSS_INDUSTRY_INVOICE.getNamespaceURI ()));

    final String sFilename = MockCIID16BTestDocuments.getTestDocuments (ECIID16BDocumentType.CROSS_INDUSTRY_INVOICE)
                                                     .get (0);

    // Read from resource
    final CrossIndustryInvoiceType aRead1 = aReader.read (new ClassPathResource (sFilename));
    assertNotNull (aRead1);

    // Read from byte[]
    final CrossIndustryInvoiceType aRead2 = aReader.read (StreamHelper.getAllBytes (new ClassPathResource (sFilename)));
    assertNotNull (aRead2);
    assertEquals (aRead1, aRead2);

    // Validate
    final IErrorList aREG1 = aValidator.validate (aRead1);
    final IErrorList aREG2 = aValidator.validate (aRead2);
    assertEquals (aREG1, aREG2);

    // Write
    final String s = aWriter.getAsString (aRead1);
    if (true)
      s_aLogger.info (s);
  }
}
