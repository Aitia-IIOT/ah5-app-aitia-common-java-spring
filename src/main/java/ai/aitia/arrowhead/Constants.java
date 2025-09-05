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

public final class Constants {

	//=================================================================================================
	// members

	public static final String BASE_PACKAGE = "ai.aitia.arrowhead";
	public static final String COMMON_BASE_PACKAGE = "eu.arrowhead";

	public static final String SECURITY_REQ_AUTHORIZATION = "Authorization";
	public static final String UTC = "UTC";
	public static final String UNKNOWN = "<unknown>";

	// SSL related

	public static final String SERVER_SSL_ENABLED = "server.ssl.enabled";
	public static final String $SERVER_SSL_ENABLED_WD = "${" + SERVER_SSL_ENABLED + ":" + Defaults.SERVER_SSL_ENABLED_DEFAULT + "}";
	public static final String DISABLE_HOSTNAME_VERIFIER = "disable.hostname.verifier";
	public static final String $DISABLE_HOSTNAME_VERIFIER_WD = "${" + DISABLE_HOSTNAME_VERIFIER + ":" + Defaults.DISABLE_HOSTNAME_VERIFIER_DEFAULT + "}";

	// CORS defaults

	public static final long CORS_MAX_AGE = 600;
	public static final boolean CORS_ALLOW_CREDENTIALS = true;
	public static final String CORS_ORIGIN_PATTERNS = "cors.origin.patterns";
	public static final String $CORS_ORIGIN_PATTERNS_WD = "${" + CORS_ORIGIN_PATTERNS + ":" + Defaults.CORS_ORIGIN_PATTERN_DEFAULT + "}";

	// HTTP related

	public static final String HTTP_STATUS_OK = "200";
	public static final String HTTP_STATUS_CREATED = "201";
	public static final String HTTP_STATUS_NO_CONTENT = "204";
	public static final String HTTP_STATUS_BAD_REQUEST = "400";
	public static final String HTTP_STATUS_UNAUTHORIZED = "401";
	public static final String HTTP_STATUS_FORBIDDEN = "403";
	public static final String HTTP_STATUS_NOT_FOUND = "404";
	public static final String HTTP_STATUS_INTERNAL_SERVER_ERROR = "500";
	public static final String HTTP_STATUS_SERVICE_UNAVAILABLE = "503";

	public static final String LOG_ALL_REQUEST_AND_RESPONSE = "log.all.request.and.response";
	public static final String $LOG_ALL_REQUEST_AND_RESPONSE_WD = "${" + LOG_ALL_REQUEST_AND_RESPONSE + ":" + Defaults.LOG_ALL_REQUEST_AND_RESPONSE_DEFAULT + "}";

	public static final String HTTP_API_OP_ECHO_PATH = "/echo";

	// Swagger

	public static final String SWAGGER_API_DOCS_URI = "/v3/api-docs";
	public static final String SWAGGER_UI_URI = "/swagger-ui";
	public static final String SWAGGER_UI_INDEX_HTML = SWAGGER_UI_URI + "/index.html";
	public static final String SWAGGER_HTTP_200_MESSAGE = "Ok";
	public static final String SWAGGER_HTTP_201_MESSAGE = "Created";
	public static final String SWAGGER_HTTP_204_MESSAGE = "No changes was necessary";
	public static final String SWAGGER_HTTP_400_MESSAGE = "Bad request";
	public static final String SWAGGER_HTTP_401_MESSAGE = "You are not authenticated";
	public static final String SWAGGER_HTTP_403_MESSAGE = "You have no permission";
	public static final String SWAGGER_HTTP_404_MESSAGE = "Not found";
	public static final String SWAGGER_HTTP_500_MESSAGE = "Internal server error";
	public static final String SWAGGER_HTTP_503_MESSAGE = "Service unavailable";

	//=================================================================================================
	// assistant methods

	//-------------------------------------------------------------------------------------------------
	private Constants() {
		throw new UnsupportedOperationException();
	}
}