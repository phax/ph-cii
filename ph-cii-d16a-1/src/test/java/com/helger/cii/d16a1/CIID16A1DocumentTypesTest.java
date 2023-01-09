/*
 * Copyright (C) 2016-2023 Philip Helger (www.helger.com)
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

/**
 * Test class for class {@link CIID16A1DocumentTypes}.
 *
 * @author Philip Helger
 */
@Deprecated
public final class CIID16A1DocumentTypesTest
{
  @Test
  public void testGetAllNamespaces ()
  {
    assertNotNull (CIID16A1DocumentTypes.getAllNamespaces ());
    assertNotNull (CIID16A1DocumentTypes.getAllLocalNames ());
    assertEquals (CIID16A1DocumentTypes.getAllNamespaces ().size (), CIID16A1DocumentTypes.getAllLocalNames ().size ());

    for (final String sNamespace : CIID16A1DocumentTypes.getAllNamespaces ())
    {
      assertNotNull (CIID16A1DocumentTypes.getDocumentTypeOfNamespace (sNamespace));
      assertNotNull (CIID16A1DocumentTypes.getImplementationClassOfNamespace (sNamespace));
      assertNotNull (CIID16A1DocumentTypes.getSchemaOfNamespace (sNamespace));
    }
    assertNull (CIID16A1DocumentTypes.getDocumentTypeOfNamespace ("any"));
    assertNull (CIID16A1DocumentTypes.getImplementationClassOfNamespace ("any"));
    assertNull (CIID16A1DocumentTypes.getSchemaOfNamespace ("any"));
    assertNull (CIID16A1DocumentTypes.getDocumentTypeOfNamespace (null));
    assertNull (CIID16A1DocumentTypes.getImplementationClassOfNamespace (null));
    assertNull (CIID16A1DocumentTypes.getSchemaOfNamespace (null));

    for (final String sNamespace : CIID16A1DocumentTypes.getAllLocalNames ())
    {
      assertNotNull (CIID16A1DocumentTypes.getDocumentTypeOfLocalName (sNamespace));
      assertNotNull (CIID16A1DocumentTypes.getImplementationClassOfLocalName (sNamespace));
      assertNotNull (CIID16A1DocumentTypes.getSchemaOfLocalName (sNamespace));
    }
    assertNull (CIID16A1DocumentTypes.getDocumentTypeOfLocalName ("any"));
    assertNull (CIID16A1DocumentTypes.getImplementationClassOfLocalName ("any"));
    assertNull (CIID16A1DocumentTypes.getSchemaOfLocalName ("any"));
    assertNull (CIID16A1DocumentTypes.getDocumentTypeOfLocalName (null));
    assertNull (CIID16A1DocumentTypes.getImplementationClassOfLocalName (null));
    assertNull (CIID16A1DocumentTypes.getSchemaOfLocalName (null));
  }

  @Test
  public void testGetSchemaOfImplementationClass ()
  {
    assertNull (CIID16A1DocumentTypes.getDocumentTypeOfImplementationClass (null));
    assertNull (CIID16A1DocumentTypes.getSchemaOfImplementationClass (null));
    assertNull (CIID16A1DocumentTypes.getSchemaOfImplementationClass (String.class));
    for (final ECIID16A1DocumentType eDocType : ECIID16A1DocumentType.values ())
    {
      assertSame (eDocType,
                  CIID16A1DocumentTypes.getDocumentTypeOfImplementationClass (eDocType.getImplementationClass ()));
      assertSame (eDocType.getSchema (),
                  CIID16A1DocumentTypes.getSchemaOfImplementationClass (eDocType.getImplementationClass ()));
      assertNotNull (eDocType.getValidator (null));
    }
  }
}
