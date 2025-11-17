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
package com.helger.cii.testfiles;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.helger.annotation.concurrent.Immutable;
import com.helger.annotation.style.CodingStyleguideUnaware;

/**
 * List of all CDAR test files categorized by version.
 *
 * @author Philip Helger
 */
@Immutable
@CodingStyleguideUnaware
public final class CDARTestFiles
{
  private static final String PREFIX = "external/test-cdar/";

  @CodingStyleguideUnaware
  public static final List <String> D22B_FILES;
  static
  {
    final var aList = Arrays.asList (PREFIX + "d22b/UC1_F202500003_01-CDV-200_Deposee_POUR_PPF.xml",
                                     PREFIX + "d22b/UC1_F202500003_01-CDV-200_Deposee.xml",
                                     PREFIX + "d22b/UC1_F202500003_02-CDV-202_Recue.xml",
                                     PREFIX + "d22b/UC1_F202500003_03-CDV-203_Mise_a_disposition.xml",
                                     PREFIX + "d22b/UC1_F202500003_04-CDV-204_Prise_en_charge.xml",
                                     PREFIX + "d22b/UC1_F202500003_05-CDV-205_Approuvee.xml",
                                     PREFIX + "d22b/UC1_F202500003_06-CDV-211_Paiement_transmis.xml",
                                     PREFIX + "d22b/UC1_F202500003_07-CDV-212_Encaissee_POUR_PPF.xml",
                                     PREFIX + "d22b/UC1_F202500003_07-CDV-212_Encaissee.xml",
                                     PREFIX + "d22b/UC4_F202500006_04-CDV-207_En_litige.xml",
                                     PREFIX + "d22b/UC5_F202500007_04-CDV-207_En_litige.xml");
    D22B_FILES = Collections.unmodifiableList (aList);
  }

  public CDARTestFiles ()
  {}
}
