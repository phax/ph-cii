/*
 * Copyright (C) 2016-2022 Philip Helger (www.helger.com)
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
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.io.resource.ClassPathResource;
import com.helger.jaxb.GenericJAXBMarshaller;

import un.unece.uncefact.data.standard.crossindustryinvoice._100.CrossIndustryInvoiceType;

/**
 * A JAXB Marshaller for CII D16A1 CrossIndustryInvoiceType documents.
 *
 * @author Philip Helger
 * @since 3.0.0
 */
@NotThreadSafe
public class CIID16A1CrossIndustryInvoiceTypeMarshaller extends GenericJAXBMarshaller <CrossIndustryInvoiceType>
{
  public static final String XSD_PATH = CCIID16A1.SCHEMA_DIRECTORY + "CrossIndustryInvoice_100p0.xsd";

  @Nonnull
  private static ClassLoader _getCL ()
  {
    return CIID16A1CrossIndustryInvoiceTypeMarshaller.class.getClassLoader ();
  }

  /**
   * Create a new reader builder.
   *
   * @param aImplClass
   *        The CII class to be read. May not be <code>null</code>.
   */
  public CIID16A1CrossIndustryInvoiceTypeMarshaller ()
  {
    super (CrossIndustryInvoiceType.class,
           new CommonsArrayList <> (new ClassPathResource (XSD_PATH, _getCL ())),
           new un.unece.uncefact.data.standard.crossindustryinvoice._100.ObjectFactory ()::createCrossIndustryInvoice);
    setNamespaceContext (CIID16A1NamespaceContext.getInstance ());
  }
}
