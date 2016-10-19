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

import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.jaxb.builder.JAXBValidationBuilder;

import un.unece.uncefact.data.standard.crossindustryinvoice._100.CrossIndustryInvoiceType;

/**
 * A writer builder for CII D16A1 documents.
 *
 * @author Philip Helger
 * @param <JAXBTYPE>
 *        The CII D16A1 implementation class to be read
 */
@NotThreadSafe
public class CIID16A1Validator <JAXBTYPE>
                                      extends JAXBValidationBuilder <JAXBTYPE, CIID16A1Validator <JAXBTYPE>>
{
  public CIID16A1Validator (@Nonnull final ECIID16A1DocumentType eDocType)
  {
    super (eDocType);
  }

  public CIID16A1Validator (@Nonnull final Class <JAXBTYPE> aClass)
  {
    this (CIID16A1DocumentTypes.getDocumentTypeOfImplementationClass (aClass));
  }

  /**
   * Create a new validation builder.
   *
   * @param aClass
   *        The UBL class to be validated. May not be <code>null</code>.
   * @return The new validation builder. Never <code>null</code>.
   * @param <T>
   *        The CII D16A1 document implementation type
   */
  @Nonnull
  public static <T> CIID16A1Validator <T> create (@Nonnull final Class <T> aClass)
  {
    return new CIID16A1Validator<> (aClass);
  }

  /**
   * Create a validation builder for CrossIndustryInvoiceType.
   *
   * @return The builder and never <code>null</code>
   */
  @Nonnull
  public static CIID16A1Validator <CrossIndustryInvoiceType> crossIndustryInvoice ()
  {
    return create (CrossIndustryInvoiceType.class);
  }
}