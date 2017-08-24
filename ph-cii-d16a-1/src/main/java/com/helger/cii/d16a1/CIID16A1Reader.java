/**
 * Copyright (C) 2016-2017 Philip Helger (www.helger.com)
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

import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.jaxb.builder.JAXBReaderBuilder;

import un.unece.uncefact.data.standard.crossindustryinvoice._100.CrossIndustryInvoiceType;

/**
 * A reader builder for CII D16A1 documents.
 *
 * @author Philip Helger
 * @param <JAXBTYPE>
 *        The CII D16A1 implementation class to be read
 */
@NotThreadSafe
public class CIID16A1Reader <JAXBTYPE> extends JAXBReaderBuilder <JAXBTYPE, CIID16A1Reader <JAXBTYPE>>
{
  public CIID16A1Reader (@Nonnull final ECIID16A1DocumentType eDocType, @Nonnull final Class <JAXBTYPE> aImplClass)
  {
    super (eDocType, aImplClass);
  }

  public CIID16A1Reader (@Nonnull final Class <JAXBTYPE> aClass)
  {
    this (CIID16A1DocumentTypes.getDocumentTypeOfImplementationClass (aClass), aClass);
  }

  /**
   * Create a new reader builder.
   *
   * @param aClass
   *        The UBL class to be read. May not be <code>null</code>.
   * @return The new reader builder. Never <code>null</code>.
   * @param <T>
   *        The CII D16A1 document implementation type
   */
  @Nonnull
  public static <T> CIID16A1Reader <T> create (@Nonnull final Class <T> aClass)
  {
    return new CIID16A1Reader <> (aClass);
  }

  /**
   * Create a new reader builder that is not typed, because only the document
   * type enumeration value is available.
   *
   * @param eDocType
   *        The CII D16A1 document type to be read. May not be <code>null</code>
   *        .
   * @return The new reader builder. Never <code>null</code>.
   */
  @Nonnull
  public static CIID16A1Reader <?> createGeneric (@Nonnull final ECIID16A1DocumentType eDocType)
  {
    return new CIID16A1Reader <> (eDocType, Object.class);
  }

  /**
   * Create a reader builder for CrossIndustryInvoiceType.
   *
   * @return The builder and never <code>null</code>
   */
  @Nonnull
  public static CIID16A1Reader <CrossIndustryInvoiceType> crossIndustryInvoice ()
  {
    return create (CrossIndustryInvoiceType.class);
  }
}
