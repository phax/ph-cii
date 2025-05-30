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
 * Test class for classes {@link CIID16A1Reader}, {@link CIID16A1Validator} and
 * {@link CIID16A1Writer}.
 *
 * @author Philip Helger
 */
@Deprecated
public final class CIID16A1BuilderFuncTest
{
  private static final Logger LOGGER = LoggerFactory.getLogger (CIID16A1BuilderFuncTest.class);

  @Test
  public void testReadAndWriteInvoice ()
  {
    final CIID16A1Reader <CrossIndustryInvoiceType> aReader = new CIID16A1Reader <> (CrossIndustryInvoiceType.class);
    final CIID16A1Validator <CrossIndustryInvoiceType> aValidator = new CIID16A1Validator <> (CrossIndustryInvoiceType.class);
    final CIID16A1Writer <CrossIndustryInvoiceType> aWriter = new CIID16A1Writer <> (CrossIndustryInvoiceType.class).setFormattedOutput (true);
    aWriter.setNamespaceContext (new MapBasedNamespaceContext ().addMapping ("bla",
                                                                             ECIID16A1DocumentType.CROSS_INDUSTRY_INVOICE.getNamespaceURI ()));

    final String sFilename = MockCIID16A1TestDocuments.getTestDocuments (ECIID16A1DocumentType.CROSS_INDUSTRY_INVOICE)
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
      LOGGER.info (s);
  }
}
