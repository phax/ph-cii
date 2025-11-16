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

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.jspecify.annotations.NonNull;
import org.junit.Test;

import com.helger.io.resource.ClassPathResource;

/**
 * Test class for class {@link CIITestFiles}.
 *
 * @author Philip Helger
 */
public final class CIITestFilesTest
{
  private static void _test (@NonNull final List <String> aFiles)
  {
    for (final String sFile : aFiles)
      assertTrue (sFile + " does not exist", new ClassPathResource (sFile).exists ());
  }

  @Test
  public void testExistence ()
  {
    _test (CIITestFiles.D16A1_FILES);
  }
}
