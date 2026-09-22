/*
 * Copyright (C) 2016-2026 Philip Helger (www.helger.com)
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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.xml.validation.Schema;

import org.junit.Test;

import com.helger.collection.commons.CommonsArrayList;
import com.helger.collection.commons.ICommonsList;
import com.helger.io.resource.ClassPathResource;
import com.helger.xml.schema.XMLSchemaCache;

/**
 * Test class for class {@link CCIID16B}.
 *
 * @author Philip Helger
 */
public final class CCIID16BTest
{
  @Test
  public void testGetXSDResource ()
  {
    final ICommonsList <ClassPathResource> aXSDs = new CommonsArrayList <> (CCIID16B.getXSDResource ());
    for (final ClassPathResource aXSD : aXSDs)
    {
      assertTrue (aXSD.getPath (), aXSD.exists ());

      final Schema aSchema = XMLSchemaCache.getInstanceOfClassLoader (aXSD.getClassLoader ()).getSchema (aXSD);
      assertNotNull (aXSD.getPath (), aSchema);
    }
  }
}
