/**
 * Copyright (C) 2016-2021 Philip Helger (www.helger.com)
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

/**
 * Test class for class {@link CIID16BDocumentTypes}.
 *
 * @author Philip Helger
 */
public final class CIID16BDocumentTypesTest
{
  @Test
  public void testGetAllNamespaces ()
  {
    assertNotNull (CIID16BDocumentTypes.getAllNamespaces ());
    assertNotNull (CIID16BDocumentTypes.getAllLocalNames ());
    assertEquals (CIID16BDocumentTypes.getAllNamespaces ().size (), CIID16BDocumentTypes.getAllLocalNames ().size ());

    for (final String sNamespace : CIID16BDocumentTypes.getAllNamespaces ())
    {
      assertNotNull (CIID16BDocumentTypes.getDocumentTypeOfNamespace (sNamespace));
      assertNotNull (CIID16BDocumentTypes.getImplementationClassOfNamespace (sNamespace));
      assertNotNull (CIID16BDocumentTypes.getSchemaOfNamespace (sNamespace));
    }
    assertNull (CIID16BDocumentTypes.getDocumentTypeOfNamespace ("any"));
    assertNull (CIID16BDocumentTypes.getImplementationClassOfNamespace ("any"));
    assertNull (CIID16BDocumentTypes.getSchemaOfNamespace ("any"));
    assertNull (CIID16BDocumentTypes.getDocumentTypeOfNamespace (null));
    assertNull (CIID16BDocumentTypes.getImplementationClassOfNamespace (null));
    assertNull (CIID16BDocumentTypes.getSchemaOfNamespace (null));

    for (final String sNamespace : CIID16BDocumentTypes.getAllLocalNames ())
    {
      assertNotNull (CIID16BDocumentTypes.getDocumentTypeOfLocalName (sNamespace));
      assertNotNull (CIID16BDocumentTypes.getImplementationClassOfLocalName (sNamespace));
      assertNotNull (CIID16BDocumentTypes.getSchemaOfLocalName (sNamespace));
    }
    assertNull (CIID16BDocumentTypes.getDocumentTypeOfLocalName ("any"));
    assertNull (CIID16BDocumentTypes.getImplementationClassOfLocalName ("any"));
    assertNull (CIID16BDocumentTypes.getSchemaOfLocalName ("any"));
    assertNull (CIID16BDocumentTypes.getDocumentTypeOfLocalName (null));
    assertNull (CIID16BDocumentTypes.getImplementationClassOfLocalName (null));
    assertNull (CIID16BDocumentTypes.getSchemaOfLocalName (null));
  }

  @Test
  public void testGetSchemaOfImplementationClass ()
  {
    assertNull (CIID16BDocumentTypes.getDocumentTypeOfImplementationClass (null));
    assertNull (CIID16BDocumentTypes.getSchemaOfImplementationClass (null));
    assertNull (CIID16BDocumentTypes.getSchemaOfImplementationClass (String.class));
    for (final ECIID16BDocumentType eDocType : ECIID16BDocumentType.values ())
    {
      assertSame (eDocType,
                  CIID16BDocumentTypes.getDocumentTypeOfImplementationClass (eDocType.getImplementationClass ()));
      assertSame (eDocType.getSchema (),
                  CIID16BDocumentTypes.getSchemaOfImplementationClass (eDocType.getImplementationClass ()));
      assertNotNull (eDocType.getValidator (null));
    }
  }
}
