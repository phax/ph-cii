/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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

import com.helger.cii.d16a1.ECIID16A1DocumentType;
import com.helger.cii.d16a1.UBL20ReaderBuilder;
import com.helger.cii.d16a1.UBL20ValidatorBuilder;
import com.helger.cii.d16a1.UBL20WriterBuilder;
import com.helger.commons.error.list.IErrorList;
import com.helger.commons.io.resource.ClassPathResource;
import com.helger.commons.io.stream.StreamHelper;
import com.helger.xml.namespace.MapBasedNamespaceContext;

import oasis.names.specification.ubl.schema.xsd.invoice_2.InvoiceType;

/**
 * Test class for classes {@link UBL20ReaderBuilder},
 * {@link UBL20ValidatorBuilder} and {@link UBL20WriterBuilder}.
 *
 * @author Philip Helger
 */
public final class UBL20BuilderFuncTest
{
  @Test
  public void testReadAndWriteInvoice ()
  {
    final UBL20ReaderBuilder <InvoiceType> aReader = new UBL20ReaderBuilder<> (InvoiceType.class);
    final UBL20ValidatorBuilder <InvoiceType> aValidator = new UBL20ValidatorBuilder<> (InvoiceType.class);
    final UBL20WriterBuilder <InvoiceType> aWriter = new UBL20WriterBuilder<> (InvoiceType.class).setFormattedOutput (true);
    aWriter.setNamespaceContext (new MapBasedNamespaceContext ().addMapping ("bla",
                                                                             ECIID16A1DocumentType.INVOICE.getNamespaceURI ()));

    final String sFilename = MockUBL20TestDocuments.getUBL20TestDocuments (ECIID16A1DocumentType.INVOICE).get (0);

    // Read from resource
    final InvoiceType aRead1 = aReader.read (new ClassPathResource (sFilename));
    assertNotNull (aRead1);

    // Read from byte[]
    final InvoiceType aRead2 = aReader.read (StreamHelper.getAllBytes (new ClassPathResource (sFilename)));
    assertNotNull (aRead2);
    assertEquals (aRead1, aRead2);

    // Validate
    final IErrorList aREG1 = aValidator.validate (aRead1);
    final IErrorList aREG2 = aValidator.validate (aRead2);
    assertEquals (aREG1, aREG2);

    // Write
    final String s = aWriter.getAsString (aRead1);
    System.out.println (s);
  }
}
