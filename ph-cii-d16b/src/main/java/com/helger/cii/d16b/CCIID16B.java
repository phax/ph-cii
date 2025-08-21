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
package com.helger.cii.d16b;

import com.helger.annotation.concurrent.Immutable;
import com.helger.annotation.style.PresentForCodeCoverage;
import com.helger.io.resource.ClassPathResource;

import jakarta.annotation.Nonnull;

/**
 * Constants for CII D16B handling.
 *
 * @author Philip Helger
 */
@Immutable
public final class CCIID16B
{
  /** The classpath relative directory where the main XSDs reside */
  public static final String SCHEMA_DIRECTORY = "external/schemas/d16b/data/standard/";

  public static final String XSD_PATH = SCHEMA_DIRECTORY + "CrossIndustryInvoice_100pD16B.xsd";

  /** The CII rsm namespace URL */
  public static final String XML_SCHEMA_RSM_NAMESPACE_URL = "urn:un:unece:uncefact:data:standard:CrossIndustryInvoice:100";

  /** The udt namespace URL */
  public static final String XML_SCHEMA_UDT_NAMESPACE_URL = "urn:un:unece:uncefact:data:standard:UnqualifiedDataType:100";

  /** The qdt namespace URL */
  public static final String XML_SCHEMA_QDT_NAMESPACE_URL = "urn:un:unece:uncefact:data:standard:QualifiedDataType:100";

  /** The ram namespace URL */
  public static final String XML_SCHEMA_RAM_NAMESPACE_URL = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100";

  @PresentForCodeCoverage
  private static final CCIID16B INSTANCE = new CCIID16B ();

  private CCIID16B ()
  {}

  @Nonnull
  private static ClassLoader _getCL ()
  {
    return CCIID16B.class.getClassLoader ();
  }

  @Nonnull
  public static ClassPathResource getXSDResource ()
  {
    return new ClassPathResource (XSD_PATH, _getCL ());
  }
}
