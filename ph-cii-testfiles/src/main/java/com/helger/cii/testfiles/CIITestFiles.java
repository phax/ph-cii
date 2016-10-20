/**
 * Copyright (C) 2016 Philip Helger (www.helger.com)
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

import java.util.List;

import javax.annotation.concurrent.Immutable;

import com.helger.commons.annotation.CodingStyleguideUnaware;
import com.helger.commons.collection.CollectionHelper;

/**
 * List of all CII test files categorized by version.
 *
 * @author Philip Helger
 */
@Immutable
@CodingStyleguideUnaware
public final class CIITestFiles
{
  public static final List <String> D16A1_FILES = CollectionHelper.makeUnmodifiable (/*
                                                                                      * "test-cii/d16a1/CII_example1.xml",
                                                                                      * "test-cii/d16a1/CII_example2.xml",
                                                                                      * "test-cii/d16a1/CII_example3.xml",
                                                                                      * "test-cii/d16a1/CII_example4.xml",
                                                                                      * "test-cii/d16a1/CII_example5.xml",
                                                                                      * "test-cii/d16a1/CII_example6.xml",
                                                                                      */
                                                                                     "test-cii/d16a1/CII_example7.xml"
  /*
   * "test-cii/d16a1/CII_example8.xml", "test-cii/d16a1/CII_example9.xml"
   */);
  public static final List <String> D16B_FILES = CollectionHelper.makeUnmodifiable (/*
                                                                                     * "test-cii/d16b/CII_example1.xml",
                                                                                     * "test-cii/d16b/CII_example2.xml",
                                                                                     * "test-cii/d16b/CII_example3.xml",
                                                                                     * "test-cii/d16b/CII_example4.xml",
                                                                                     * "test-cii/d16b/CII_example5.xml",
                                                                                     * "test-cii/d16b/CII_example6.xml",
                                                                                     * "test-cii/d16b/CII_example7.xml",
                                                                                     * "test-cii/d16b/CII_example8.xml",
                                                                                     * "test-cii/d16b/CII_example9.xml"
                                                                                     */);

  public CIITestFiles ()
  {}
}
