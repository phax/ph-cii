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

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import com.helger.cii.d16a1.EUBL20DocumentType;
import com.helger.cii.testfiles.CIITestFiles;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.CollectionHelper;

@Immutable
public final class MockUBL20TestDocuments
{
  private MockUBL20TestDocuments ()
  {}

  @Nonnull
  @ReturnsMutableCopy
  public static List <String> getUBL20TestDocuments (@Nonnull final EUBL20DocumentType eType)
  {
    List <String> aFiles = null;
    switch (eType)
    {
      case CATALOGUE:
        aFiles = CIITestFiles.CATALOGUE_FILES;
        break;
      case CREDIT_NOTE:
        aFiles = CIITestFiles.CREDIT_NOTE_FILES;
        break;
      case DESPATCH_ADVICE:
        aFiles = CIITestFiles.DESPATCH_ADVICE_FILES;
        break;
      case FORWARDING_INSTRUCTIONS:
        aFiles = CIITestFiles.FORWARDING_INSTRUCTIONS_FILES;
        break;
      case INVOICE:
        aFiles = CIITestFiles.INVOICE_FILES;
        break;
      case ORDER:
        aFiles = CIITestFiles.ORDER_FILES;
        break;
      case ORDER_RESPONSE_SIMPLE:
        aFiles = CIITestFiles.ORDER_RESPONSE_SIMPLE_FILES;
        break;
      case QUOTATION:
        aFiles = CIITestFiles.QUOTATION_FILES;
        break;
      case RECEIPT_ADVICE:
        aFiles = CIITestFiles.RECEIPT_ADVICE_FILES;
        break;
      case REQUEST_FOR_QUOTATION:
        aFiles = CollectionHelper.newList (CIITestFiles.REQUEST_FOR_QUOTATION_FILES);
        aFiles.remove ("test-ubl/requestforquotation/UBL-RequestForQuotation-2.1-Example.xml");
        break;
      case REMITTANCE_ADVICE:
        aFiles = CIITestFiles.REMITTANCE_ADVICE_FILES;
        break;
      case STATEMENT:
        aFiles = CIITestFiles.STATEMENT_FILES;
        break;
      case WAYBILL:
        aFiles = CIITestFiles.WAYBILL_FILES;
        break;
      default:
        throw new IllegalArgumentException ("No test files available for type " + eType);
    }

    return CollectionHelper.newList (aFiles);
  }
}
