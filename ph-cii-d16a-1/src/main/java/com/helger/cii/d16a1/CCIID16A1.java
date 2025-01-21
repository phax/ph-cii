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
package com.helger.cii.d16a1;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.annotation.PresentForCodeCoverage;
import com.helger.commons.io.resource.ClassPathResource;

/**
 * Constants for CII D16A1 handling.
 *
 * @author Philip Helger
 */
@Immutable
public final class CCIID16A1
{
  /** The classpath relative directory where the main XSDs reside */
  public static final String SCHEMA_DIRECTORY = "external/schemas/d16a1/data/standard/";

  public static final String XSD_PATH = SCHEMA_DIRECTORY + "CrossIndustryInvoice_100p0.xsd";

  /** The CII rsm namespace URL */
  public static final String XML_SCHEMA_RSM_NAMESPACE_URL = "urn:un:unece:uncefact:data:standard:CrossIndustryInvoice:100";

  /** The ccts namespace URL */
  public static final String XML_SCHEMA_CCTS_NAMESPACE_URL = "urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2";

  /** The udt namespace URL */
  public static final String XML_SCHEMA_UDT_NAMESPACE_URL = "urn:un:unece:uncefact:data:standard:UnqualifiedDataType:19";

  /** The qdt namespace URL */
  public static final String XML_SCHEMA_QDT_NAMESPACE_URL = "urn:un:unece:uncefact:data:Standard:QualifiedDataType:19";

  /** The ram namespace URL */
  public static final String XML_SCHEMA_RAM_NAMESPACE_URL = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:19";

  @PresentForCodeCoverage
  private static final CCIID16A1 INSTANCE = new CCIID16A1 ();

  private CCIID16A1 ()
  {}

  @Nonnull
  private static ClassLoader _getCL ()
  {
    return CCIID16A1.class.getClassLoader ();
  }

  @Nonnull
  public static ClassPathResource getXSDResource ()
  {
    return new ClassPathResource (XSD_PATH, _getCL ());
  }
}
