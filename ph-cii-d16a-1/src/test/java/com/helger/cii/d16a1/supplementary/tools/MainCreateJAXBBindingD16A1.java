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
package com.helger.cii.d16a1.supplementary.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.cii.testfiles.supplementary.tools.JAXBBindingCreator;

/**
 * Utility class to create JAXB binding stuff.
 *
 * @see JAXBBindingCreator
 * @author Philip Helger
 */
public final class MainCreateJAXBBindingD16A1
{
  private static final Logger LOGGER = LoggerFactory.getLogger (MainCreateJAXBBindingD16A1.class);

  public static void main (final String [] args)
  {
    LOGGER.info ("D16A.1");
    JAXBBindingCreator.runCIIBindingCreation ("d16a1", true);
    LOGGER.info ("Done");
  }
}
