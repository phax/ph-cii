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
package com.helger.cii.d16a1;

import javax.annotation.Nonnull;
import javax.xml.validation.Schema;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.io.resource.ClassPathResource;
import com.helger.commons.string.StringHelper;
import com.helger.jaxb.builder.IJAXBDocumentType;
import com.helger.jaxb.builder.JAXBDocumentType;

import un.unece.uncefact.data.standard.crossindustryinvoice._100.CrossIndustryInvoiceType;

/**
 * Enumeration with all available CII D16A1 document types.
 *
 * @author Philip Helger
 */
public enum ECIID16A1DocumentType implements IJAXBDocumentType
{
  CROSS_INDUSTRY_INVOICE (CrossIndustryInvoiceType.class, "CrossIndustryInvoice_100p0.xsd");

  @Nonnull
  private static ClassLoader _getCL ()
  {
    return ECIID16A1DocumentType.class.getClassLoader ();
  }

  private final JAXBDocumentType m_aDocType;

  private ECIID16A1DocumentType (@Nonnull final Class <?> aClass, @Nonnull final String sXSDPath)
  {
    m_aDocType = new JAXBDocumentType (aClass,
                                       new CommonsArrayList <> (new ClassPathResource (CCIID16A1.SCHEMA_DIRECTORY +
                                                                                       sXSDPath,
                                                                                       _getCL ())),
                                       s -> StringHelper.trimEnd (s, "Type"));
  }

  @Nonnull
  public Class <?> getImplementationClass ()
  {
    return m_aDocType.getImplementationClass ();
  }

  @Nonnull
  @Nonempty
  @ReturnsMutableCopy
  public ICommonsList <ClassPathResource> getAllXSDResources ()
  {
    return m_aDocType.getAllXSDResources ();
  }

  @Nonnull
  public String getNamespaceURI ()
  {
    return m_aDocType.getNamespaceURI ();
  }

  @Nonnull
  @Nonempty
  public String getLocalName ()
  {
    return m_aDocType.getLocalName ();
  }

  @Nonnull
  public Schema getSchema ()
  {
    return m_aDocType.getSchema ();
  }
}
