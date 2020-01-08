/**
 * Copyright (C) 2016-2020 Philip Helger (www.helger.com)
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

import com.helger.jaxb.builder.JAXBValidationBuilder;

import un.unece.uncefact.data.standard.crossindustryinvoice._100.CrossIndustryInvoiceType;

/**
 * A writer builder for CII D16B documents.
 *
 * @author Philip Helger
 * @param <JAXBTYPE>
 *        The CII D16B implementation class to be read
 */
@NotThreadSafe
public class CIID16BValidator <JAXBTYPE> extends JAXBValidationBuilder <JAXBTYPE, CIID16BValidator <JAXBTYPE>>
{
  /**
   * Create a new validation builder.
   *
   * @param aClass
   *        The UBL class to be validated. May not be <code>null</code>.
   */
  public CIID16BValidator (@Nonnull final Class <JAXBTYPE> aClass)
  {
    this (CIID16BDocumentTypes.getDocumentTypeOfImplementationClass (aClass));
  }

  public CIID16BValidator (@Nonnull final ECIID16BDocumentType eDocType)
  {
    super (eDocType);
  }

  /**
   * Create a validation builder for CrossIndustryInvoiceType.
   *
   * @return The builder and never <code>null</code>
   */
  @Nonnull
  public static CIID16BValidator <CrossIndustryInvoiceType> crossIndustryInvoice ()
  {
    return new CIID16BValidator <> (CrossIndustryInvoiceType.class);
  }
}
