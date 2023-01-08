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

import com.helger.jaxb.builder.JAXBReaderBuilder;

import un.unece.uncefact.data.standard.crossindustryinvoice._100.CrossIndustryInvoiceType;

/**
 * A reader builder for CII D16B documents.
 *
 * @author Philip Helger
 * @param <JAXBTYPE>
 *        The CII D16B implementation class to be read
 * @deprecated Since 3.0.0. Use
 *             {@link CIID16BCrossIndustryInvoiceTypeMarshaller} instead
 */
@Deprecated (forRemoval = true)
@NotThreadSafe
public class CIID16BReader <JAXBTYPE> extends JAXBReaderBuilder <JAXBTYPE, CIID16BReader <JAXBTYPE>>
{
  /**
   * Create a new reader builder.
   *
   * @param aImplClass
   *        The CII class to be read. May not be <code>null</code>.
   */
  public CIID16BReader (@Nonnull final Class <JAXBTYPE> aImplClass)
  {
    this (CIID16BDocumentTypes.getDocumentTypeOfImplementationClass (aImplClass), aImplClass);
  }

  /**
   * Create a new reader builder that is not typed, because only the document
   * type enumeration value is available.
   *
   * @param eDocType
   *        The CII D16B document type to be read. May not be <code>null</code>
   *        .
   * @param aImplClass
   *        The CII class to be read. May not be <code>null</code>.
   */
  public CIID16BReader (@Nonnull final ECIID16BDocumentType eDocType, @Nonnull final Class <JAXBTYPE> aImplClass)
  {
    super (eDocType, aImplClass);
  }

  /**
   * Create a reader builder for CrossIndustryInvoiceType.
   *
   * @return The builder and never <code>null</code>
   */
  @Nonnull
  public static CIID16BReader <CrossIndustryInvoiceType> crossIndustryInvoice ()
  {
    return new CIID16BReader <> (CrossIndustryInvoiceType.class);
  }
}
