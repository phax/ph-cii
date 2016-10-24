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
package com.helger.cii.d16b;

import javax.annotation.concurrent.Immutable;

import com.helger.commons.annotation.PresentForCodeCoverage;

/**
 * Constants for CII D16B handling.
 *
 * @author Philip Helger
 */
@Immutable
public final class CCIID16B
{
  /** The classpath relative directory where the main XSDs reside */
  public static final String SCHEMA_DIRECTORY = "schemas/d16b/data/standard/";

  /** The CII rsm namespace URL */
  public static final String XML_SCHEMA_RSM_NAMESPACE_URL = "urn:un:unece:uncefact:data:standard:CrossIndustryInvoice:13";

  /** The ccts namespace URL */
  public static final String XML_SCHEMA_CCTS_NAMESPACE_URL = "urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2";

  /** The udt namespace URL */
  public static final String XML_SCHEMA_UDT_NAMESPACE_URL = "urn:un:unece:uncefact:data:standard:UnqualifiedDataType:20";

  /** The qdt namespace URL */
  public static final String XML_SCHEMA_QDT_NAMESPACE_URL = "urn:un:unece:uncefact:data:Standard:QualifiedDataType:20";

  /** The ram namespace URL */
  public static final String XML_SCHEMA_RAM_NAMESPACE_URL = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:20";

  @PresentForCodeCoverage
  private static final CCIID16B s_aInstance = new CCIID16B ();

  private CCIID16B ()
  {}
}