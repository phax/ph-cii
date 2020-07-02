/**
 * Copyright (C) 2016-2020 Philip Helger (www.helger.com)
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
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.xml.validation.Schema;

import com.helger.commons.annotation.PresentForCodeCoverage;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.ArrayHelper;
import com.helger.commons.collection.impl.CommonsHashMap;
import com.helger.commons.collection.impl.ICommonsMap;
import com.helger.commons.collection.impl.ICommonsSet;

/**
 * CII document type map. Provides sanity methods for accessing CII D16A1
 * document types based on different information items.
 *
 * @author Philip Helger
 */
@Immutable
public final class CIID16A1DocumentTypes
{
  /** Maps namespaces to document types */
  private static final ICommonsMap <String, ECIID16A1DocumentType> s_aNamespace2DocType = new CommonsHashMap <> ();

  /** Maps local names to document types */
  private static final ICommonsMap <String, ECIID16A1DocumentType> s_aLocalName2DocType = new CommonsHashMap <> ();

  static
  {
    // Register all CII D16A1 document types
    for (final ECIID16A1DocumentType eDocType : ECIID16A1DocumentType.values ())
    {
      // add to namespace map
      final String sNamespace = eDocType.getNamespaceURI ();
      if (s_aNamespace2DocType.containsKey (sNamespace))
        throw new IllegalArgumentException ("The namespace '" + sNamespace + "' is already mapped!");
      s_aNamespace2DocType.put (sNamespace, eDocType);

      // add to local name map
      final String sLocalName = eDocType.getLocalName ();
      if (s_aLocalName2DocType.containsKey (sLocalName))
        throw new IllegalArgumentException ("The local name '" + sLocalName + "' is already mapped!");
      s_aLocalName2DocType.put (sLocalName, eDocType);
    }
  }

  @PresentForCodeCoverage
  private static final CIID16A1DocumentTypes s_aInstance = new CIID16A1DocumentTypes ();

  private CIID16A1DocumentTypes ()
  {}

  /**
   * @return A non-<code>null</code> set of all supported CII D16A1 namespaces.
   */
  @Nonnull
  @ReturnsMutableCopy
  public static ICommonsSet <String> getAllNamespaces ()
  {
    return s_aNamespace2DocType.copyOfKeySet ();
  }

  /**
   * Get the document type matching the passed namespace.
   *
   * @param sNamespace
   *        The namespace URI of any CII D16A1 document type. May be
   *        <code>null</code>.
   * @return <code>null</code> if no CII D16A1 document type matching the
   *         specified namespace URI exists.
   */
  @Nullable
  public static ECIID16A1DocumentType getDocumentTypeOfNamespace (@Nullable final String sNamespace)
  {
    return s_aNamespace2DocType.get (sNamespace);
  }

  /**
   * Get the domain object class of the passed namespace.
   *
   * @param sNamespace
   *        The namespace URI of any CII D16A1 document type. May be
   *        <code>null</code>.
   * @return <code>null</code> if no such CII D16A1 document type exists.
   */
  @Nullable
  public static Class <?> getImplementationClassOfNamespace (@Nullable final String sNamespace)
  {
    final ECIID16A1DocumentType eDocType = getDocumentTypeOfNamespace (sNamespace);
    return eDocType == null ? null : eDocType.getImplementationClass ();
  }

  /**
   * Get the XSD Schema object for the CII D16A1 document type of the passed
   * namespace.
   *
   * @param sNamespace
   *        The namespace URI of any CII D16A1 document type. May be
   *        <code>null</code>.
   * @return <code>null</code> if no such CII D16A1 document type exists.
   */
  @Nullable
  public static Schema getSchemaOfNamespace (@Nullable final String sNamespace)
  {
    final ECIID16A1DocumentType eDocType = getDocumentTypeOfNamespace (sNamespace);
    return eDocType == null ? null : eDocType.getSchema ();
  }

  /**
   * @return A non-<code>null</code> set of all supported CII D16A1 document
   *         element local names.
   */
  @Nonnull
  @ReturnsMutableCopy
  public static ICommonsSet <String> getAllLocalNames ()
  {
    return s_aLocalName2DocType.copyOfKeySet ();
  }

  /**
   * Get the document type matching the passed document element local name.
   *
   * @param sLocalName
   *        The document element local name of any CII D16A1 document type. May
   *        be <code>null</code>.
   * @return <code>null</code> if no CII D16A1 document type matching the
   *         specified local name exists.
   */
  @Nullable
  public static ECIID16A1DocumentType getDocumentTypeOfLocalName (@Nullable final String sLocalName)
  {
    return s_aLocalName2DocType.get (sLocalName);
  }

  /**
   * Get the domain object class of the passed document element local name.
   *
   * @param sLocalName
   *        The document element local name of any CII D16A1 document type. May
   *        be <code>null</code>.
   * @return <code>null</code> if no such implementation class exists.
   */
  @Nullable
  public static Class <?> getImplementationClassOfLocalName (@Nullable final String sLocalName)
  {
    final ECIID16A1DocumentType eDocType = getDocumentTypeOfLocalName (sLocalName);
    return eDocType == null ? null : eDocType.getImplementationClass ();
  }

  /**
   * Get the CII D16A1 document type matching the passed implementation class.
   *
   * @param aImplClass
   *        The implementation class to use. May be <code>null</code>.
   * @return <code>null</code> if the implementation class is <code>null</code>
   *         or if no CII D16A1 document type has the specified implementation
   *         class.
   */
  @Nullable
  public static ECIID16A1DocumentType getDocumentTypeOfImplementationClass (@Nullable final Class <?> aImplClass)
  {
    if (aImplClass == null)
      return null;
    return ArrayHelper.findFirst (ECIID16A1DocumentType.values (),
                                  eDocType -> eDocType.getImplementationClass ().equals (aImplClass));
  }

  /**
   * Get the XSD Schema object for the CII D16A1 document type of the passed
   * document element local name.
   *
   * @param sLocalName
   *        The document element local name of any CII D16A1 document type. May
   *        be <code>null</code>.
   * @return <code>null</code> if no such CII D16A1 document type exists.
   */
  @Nullable
  public static Schema getSchemaOfLocalName (@Nullable final String sLocalName)
  {
    final ECIID16A1DocumentType eDocType = getDocumentTypeOfLocalName (sLocalName);
    return eDocType == null ? null : eDocType.getSchema ();
  }

  /**
   * Get the XSD Schema object for the CII D16A1 document type of the passed
   * implementation class.
   *
   * @param aImplClass
   *        The implementation class of any CII D16A1 document type. May be
   *        <code>null</code>.
   * @return <code>null</code> if no such CII D16A1 document type exists.
   */
  @Nullable
  public static Schema getSchemaOfImplementationClass (@Nullable final Class <?> aImplClass)
  {
    final ECIID16A1DocumentType eDocType = getDocumentTypeOfImplementationClass (aImplClass);
    return eDocType == null ? null : eDocType.getSchema ();
  }
}
