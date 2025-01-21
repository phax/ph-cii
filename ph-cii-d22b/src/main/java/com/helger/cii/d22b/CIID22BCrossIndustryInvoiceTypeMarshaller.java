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
package com.helger.cii.d22b;

import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.jaxb.GenericJAXBMarshaller;

import un.unece.uncefact.data.standard.cii.d22b.CrossIndustryInvoiceType;

/**
 * A JAXB Marshaller for CII D22B CrossIndustryInvoiceType documents.
 *
 * @author Philip Helger
 * @since 3.1.0
 */
@NotThreadSafe
public class CIID22BCrossIndustryInvoiceTypeMarshaller extends GenericJAXBMarshaller <CrossIndustryInvoiceType>
{
  /**
   * Create a new Marshaller
   */
  public CIID22BCrossIndustryInvoiceTypeMarshaller ()
  {
    super (CrossIndustryInvoiceType.class,
           new CommonsArrayList <> (CCIID22B.getXSDResource ()),
           new un.unece.uncefact.data.standard.cii.d22b.ObjectFactory ()::createCrossIndustryInvoice);
    setNamespaceContext (CIID22BNamespaceContext.getInstance ());
  }
}
