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
import static org.junit.Assert.assertNotNull;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.base.io.stream.StreamHelper;
import com.helger.diagnostics.error.list.IErrorList;
import com.helger.io.resource.ClassPathResource;

import un.unece.uncefact.data.standard.cdar.d22b.CrossDomainAcknowledgementAndResponseType;

/**
 * Test class for class {@link CIID22BCDARMarshaller}.
 *
 * @author Philip Helger
 */
public final class CIID22BCDARMarshallerTest
{
  private static final Logger LOGGER = LoggerFactory.getLogger (CIID22BCDARMarshallerTest.class);

  @Test
  @Ignore ("Enable when test files are present")
  public void testReadAndWriteInvoice ()
  {
    final CIID22BCDARMarshaller m = new CIID22BCDARMarshaller ();
    m.setNamespaceContext (CIID22BNamespaceContext.getInstance ()
                                                  .getClone ()
                                                  .addMapping ("bla", CCIID22B.XML_SCHEMA_CDAR_NAMESPACE_URL));

    final String sFilename = MockCIID22BTestDocuments.getTestCrossIndustryInvoices ().get (0);

    // Read from resource
    final CrossDomainAcknowledgementAndResponseType aRead1 = m.read (new ClassPathResource (sFilename));
    assertNotNull (aRead1);

    // Read from byte[]
    final CrossDomainAcknowledgementAndResponseType aRead2 = m.read (StreamHelper.getAllBytes (new ClassPathResource (sFilename)));
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
