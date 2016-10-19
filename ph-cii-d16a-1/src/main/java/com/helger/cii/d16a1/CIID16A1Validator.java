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

import com.helger.commons.annotation.PresentForCodeCoverage;

import un.unece.uncefact.data.standard.crossindustryinvoice._100.CrossIndustryInvoiceType;

/**
 * Validate all CII D16A1 document types.
 *
 * @author Philip Helger
 */
@NotThreadSafe
public final class CIID16A1Validator
{
  @PresentForCodeCoverage
  private static final CIID16A1Validator s_aInstance = new CIID16A1Validator ();

  private CIID16A1Validator ()
  {}

  /**
   * Create a validation builder for CrossIndustryInvoiceType.
   *
   * @return The builder and never <code>null</code>
   */
  @Nonnull
  public static CIID16A1ValidatorBuilder <CrossIndustryInvoiceType> crossIndustryInvoice ()
  {
    return CIID16A1ValidatorBuilder.create (CrossIndustryInvoiceType.class);
  }
}
