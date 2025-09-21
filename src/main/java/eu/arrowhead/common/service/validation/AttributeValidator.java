/*******************************************************************************
 *
 * Copyright (c) 2025 AITIA
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 *
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *  	AITIA - implementation
 *  	Arrowhead Consortia - conceptualization
 *
 *******************************************************************************/
package eu.arrowhead.common.service.validation;

import java.util.Set;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import ai.aitia.arrowhead.Constants;
import eu.arrowhead.common.Utilities;
import eu.arrowhead.common.exception.InvalidParameterException;

@Component
public class AttributeValidator {

	//=================================================================================================
	// members

	// camelCase naming convention, only allowed characters are lower- and upper-case ASCII letters and numbers
	private static final String ATTRIBUTE_REGEX_STRING = "([a-z]{1})|(^[a-z][0-9A-Za-z]+$)";
	private static final Pattern ATTRIBUTE_REGEX_PATTERN = Pattern.compile(ATTRIBUTE_REGEX_STRING);

	private final Logger logger = LogManager.getLogger(this.getClass());

	//=================================================================================================
	// methods

	//-------------------------------------------------------------------------------------------------
	public void validateAttributeSet(final Set<String> attributes) {
		logger.debug("validateAttributes started...");
		attributes.forEach(a -> validateAttribute(a));
	}

	//=================================================================================================
	// assistant methods

	//-------------------------------------------------------------------------------------------------
	private void validateAttribute(final String attribute) {

		if (Utilities.isEmpty(attribute)
				|| !ATTRIBUTE_REGEX_PATTERN.matcher(attribute).matches()
				|| attribute.length() > Constants.ATTRIBUTE_MAX_LENGTH) {
			throw new InvalidParameterException("The specified attribute name does not match the naming convention: " + attribute);
		}
	}
}
