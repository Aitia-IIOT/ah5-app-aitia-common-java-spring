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
package ai.aitia.arrowhead;

public final class Defaults {

	//=================================================================================================
	// members

	public static final String CORS_ORIGIN_PATTERN_DEFAULT = "*";
	public static final String SERVER_SSL_ENABLED_DEFAULT = "false";
	public static final String DISABLE_HOSTNAME_VERIFIER_DEFAULT = "false";
	public static final String LOG_ALL_REQUEST_AND_RESPONSE_DEFAULT = "false";

	//=================================================================================================
	// assistant methods

	//-------------------------------------------------------------------------------------------------
	private Defaults() {
		throw new UnsupportedOperationException();
	}
}
