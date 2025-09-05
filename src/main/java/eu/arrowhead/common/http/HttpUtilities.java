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
package eu.arrowhead.common.http;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;

import eu.arrowhead.common.exception.ArrowheadException;
import eu.arrowhead.dto.ErrorMessageDTO;
import eu.arrowhead.dto.enums.ExceptionType;

public final class HttpUtilities {

	//=================================================================================================
	// members

	private static final Logger logger = LogManager.getLogger(HttpUtilities.class);

	//=================================================================================================
	// methods

	//-------------------------------------------------------------------------------------------------
	public static HttpStatus calculateHttpStatusFromArrowheadException(final ArrowheadException ex) {
		Assert.notNull(ex, "Exception is null");

		final ExceptionType exceptionType = ex.getExceptionType() != null ? ex.getExceptionType() : ExceptionType.INTERNAL_SERVER_ERROR;
		HttpStatus status = HttpStatus.resolve(exceptionType.getErrorCode());
		if (status == null) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return status;
	}

	//-------------------------------------------------------------------------------------------------
	public static ErrorMessageDTO createErrorMessageDTO(final ArrowheadException ex) {
		return new ErrorMessageDTO(ex.getMessage(), calculateHttpStatusFromArrowheadException(ex).value(), ex.getExceptionType(), ex.getOrigin());
	}

	//=================================================================================================
	// assistant methods

	//-------------------------------------------------------------------------------------------------
	private HttpUtilities() {
		throw new UnsupportedOperationException();
	}
}