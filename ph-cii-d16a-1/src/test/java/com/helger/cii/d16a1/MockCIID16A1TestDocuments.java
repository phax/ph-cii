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
package com.helger.cii.d16a1;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import com.helger.cii.testfiles.CIITestFiles;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.CollectionHelper;

@Immutable
public final class MockCIID16A1TestDocuments
{
  private MockCIID16A1TestDocuments ()
  {}

  @Nonnull
  @ReturnsMutableCopy
  public static List <String> getTestDocuments (@Nonnull final ECIID16A1DocumentType eType)
  {
    List <String> aFiles = null;
    switch (eType)
    {
      case CROSS_INDUSTRY_INVOICE:
        aFiles = CIITestFiles.D16A1_FILES;
        break;
      default:
        throw new IllegalArgumentException ("No test files available for type " + eType);
    }

    return CollectionHelper.newList (aFiles);
  }
}
