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
package com.helger.cii.d22b;

import org.jspecify.annotations.NonNull;

import com.helger.annotation.concurrent.Immutable;
import com.helger.annotation.style.PresentForCodeCoverage;
import com.helger.io.resource.ClassPathResource;

/**
 * Constants for CII D22B handling.
 *
 * @author Philip Helger
 */
@Immutable
public final class CCIID22B
{
  /** The classpath relative directory where the main XSDs reside */
  public static final String SCHEMA_DIRECTORY = "external/schemas/d22b/";

  public static final String XSD_PATH_CII = SCHEMA_DIRECTORY + "cii/CrossIndustryInvoice_100pD22B.xsd";

  /**
   * @deprecated Use {@link #XSD_PATH_CII} instead
   */
  @Deprecated (forRemoval = true, since = "4.1.1")
  public static final String XSD_PATH = XSD_PATH_CII;

  public static final String XSD_PATH_CDAR = SCHEMA_DIRECTORY +
                                             "cdar/CrossDomainAcknowledgementAndResponse_100pD22B.xsd";

  /** The CII rsm namespace URL */
  public static final String XML_SCHEMA_CII_NAMESPACE_URL = "urn:un:unece:uncefact:data:standard:CrossIndustryInvoice:100";

  /** The CII rsm namespace URL */
  @Deprecated (forRemoval = true, since = "4.1.1")
  public static final String XML_SCHEMA_RSM_NAMESPACE_URL = XML_SCHEMA_CII_NAMESPACE_URL;

  /** The CDAR rsm namespace URL */
  public static final String XML_SCHEMA_CDAR_NAMESPACE_URL = "urn:un:unece:uncefact:data:standard:CrossDomainAcknowledgementAndResponse:100";

  /** The udt namespace URL */
  public static final String XML_SCHEMA_UDT_NAMESPACE_URL = "urn:un:unece:uncefact:data:standard:UnqualifiedDataType:100";

  /** The qdt namespace URL */
  public static final String XML_SCHEMA_QDT_NAMESPACE_URL = "urn:un:unece:uncefact:data:standard:QualifiedDataType:100";

  /** The ram namespace URL */
  public static final String XML_SCHEMA_RAM_NAMESPACE_URL = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100";

  @PresentForCodeCoverage
  private static final CCIID22B INSTANCE = new CCIID22B ();

  private CCIID22B ()
  {}

  @NonNull
  private static ClassLoader _getCL ()
  {
    return CCIID22B.class.getClassLoader ();
  }

  @NonNull
  @Deprecated (forRemoval = true, since = "4.1.1")
  public static ClassPathResource getXSDResource ()
  {
    return getXSDResourceCII ();
  }

  @NonNull
  public static ClassPathResource getXSDResourceCII ()
  {
    return new ClassPathResource (XSD_PATH_CII, _getCL ());
  }

  @NonNull
  public static ClassPathResource getXSDResourceCDAR ()
  {
    return new ClassPathResource (XSD_PATH_CDAR, _getCL ());
  }
}
