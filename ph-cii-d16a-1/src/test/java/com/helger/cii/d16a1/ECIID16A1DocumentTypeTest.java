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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.helger.commons.io.resource.IReadableResource;
import com.helger.commons.string.StringHelper;

/**
 * Test class for class {@link ECIID16A1DocumentType}.
 *
 * @author Philip Helger
 */
@Deprecated
public final class ECIID16A1DocumentTypeTest
{
  @Test
  public void testAll ()
  {
    for (final ECIID16A1DocumentType e : ECIID16A1DocumentType.values ())
    {
      assertNotNull (e.getImplementationClass ());
      assertTrue (StringHelper.hasText (e.getLocalName ()));
      assertTrue (StringHelper.hasText (e.getNamespaceURI ()));
      assertTrue (e.getAllXSDResources ().size () >= 1);
      for (final IReadableResource aRes : e.getAllXSDResources ())
        assertTrue (e.name (), aRes.exists ());
      assertNotNull (e.getSchema ());
      assertSame (e.getSchema (), e.getSchema ());
      assertSame (e, ECIID16A1DocumentType.valueOf (e.name ()));
    }
  }
}
