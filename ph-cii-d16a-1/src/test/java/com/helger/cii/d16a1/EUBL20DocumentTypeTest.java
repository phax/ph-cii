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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.helger.cii.d16a1.EUBL20DocumentType;
import com.helger.commons.io.resource.IReadableResource;
import com.helger.commons.string.StringHelper;

/**
 * Test class for class {@link EUBL20DocumentType}.
 *
 * @author Philip Helger
 */
public final class EUBL20DocumentTypeTest
{
  @Test
  public void testAll ()
  {
    for (final EUBL20DocumentType e : EUBL20DocumentType.values ())
    {
      assertNotNull (e.getImplementationClass ());
      assertTrue (StringHelper.hasText (e.getLocalName ()));
      assertTrue (StringHelper.hasText (e.getNamespaceURI ()));
      assertTrue (e.getAllXSDPaths ().size () >= 1);
      for (final IReadableResource aRes : e.getAllXSDResources ())
        assertTrue (e.name (), aRes.exists ());
      assertNotNull (e.getSchema ());
      assertSame (e.getSchema (), e.getSchema ());
      assertSame (e, EUBL20DocumentType.valueOf (e.name ()));
    }
  }
}
