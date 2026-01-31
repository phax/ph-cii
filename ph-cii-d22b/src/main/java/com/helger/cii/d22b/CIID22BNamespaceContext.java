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
package com.helger.cii.d22b;

import javax.xml.XMLConstants;

import org.jspecify.annotations.NonNull;

import com.helger.annotation.misc.Singleton;
import com.helger.xml.namespace.MapBasedNamespaceContext;

/**
 * The namespace context to be used as the namespace prefix mapper.
 *
 * @author Philip Helger
 */
@Singleton
public class CIID22BNamespaceContext extends MapBasedNamespaceContext
{
  private static final class SingletonHolder
  {
    static final CIID22BNamespaceContext INSTANCE = new CIID22BNamespaceContext ();
  }

  protected CIID22BNamespaceContext ()
  {
    addMapping ("xsi", XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI);
    addMapping ("xs", XMLConstants.W3C_XML_SCHEMA_NS_URI);
    addMapping ("udt", CCIID22B.XML_SCHEMA_UDT_NAMESPACE_URL);
    addMapping ("qdt", CCIID22B.XML_SCHEMA_QDT_NAMESPACE_URL);
    addMapping ("ram", CCIID22B.XML_SCHEMA_RAM_NAMESPACE_URL);
  }

  @NonNull
  public static CIID22BNamespaceContext getInstance ()
  {
    return SingletonHolder.INSTANCE;
  }

  @NonNull
  public static MapBasedNamespaceContext getForCII ()
  {
    return getInstance ().getClone ().addMapping ("rsm", CCIID22B.XML_SCHEMA_CII_NAMESPACE_URL);
  }

  @NonNull
  public static MapBasedNamespaceContext getForCDAR ()
  {
    return getInstance ().getClone ().addMapping ("rsm", CCIID22B.XML_SCHEMA_CDAR_NAMESPACE_URL);
  }
}
