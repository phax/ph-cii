/*
 * Copyright (C) 2016-2022 Philip Helger (www.helger.com)
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

import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.jaxb.builder.JAXBWriterBuilder;
import com.helger.xml.namespace.MapBasedNamespaceContext;

import un.unece.uncefact.data.standard.crossindustryinvoice._100.CrossIndustryInvoiceType;

/**
 * A writer builder for CII D16B documents.
 *
 * @author Philip Helger
 * @param <JAXBTYPE>
 *        The CII D16B implementation class to be read
 * @deprecated Since 3.0.0. Use
 *             {@link CIID16BCrossIndustryInvoiceTypeMarshaller} instead
 */
@Deprecated (forRemoval = true)
@NotThreadSafe
public class CIID16BWriter <JAXBTYPE> extends JAXBWriterBuilder <JAXBTYPE, CIID16BWriter <JAXBTYPE>>
{
  /**
   * Create a new writer builder.
   *
   * @param aClass
   *        The CII class to be written. May not be <code>null</code>.
   */
  public CIID16BWriter (@Nonnull final Class <JAXBTYPE> aClass)
  {
    this (CIID16BDocumentTypes.getDocumentTypeOfImplementationClass (aClass));
  }

  public CIID16BWriter (@Nonnull final ECIID16BDocumentType eDocType)
  {
    super (eDocType);

    // Create a special namespace context for the passed document type
    final MapBasedNamespaceContext aNSContext = new MapBasedNamespaceContext ();
    aNSContext.addMappings (CIID16BNamespaceContext.getInstance ());
    aNSContext.addDefaultNamespaceURI (m_aDocType.getNamespaceURI ());
    setNamespaceContext (aNSContext);
  }

  /**
   * Create a writer builder for CrossIndustryInvoiceType.
   *
   * @return The builder and never <code>null</code>
   */
  @Nonnull
  public static CIID16BWriter <CrossIndustryInvoiceType> crossIndustryInvoice ()
  {
    return new CIID16BWriter <> (CrossIndustryInvoiceType.class);
  }
}
