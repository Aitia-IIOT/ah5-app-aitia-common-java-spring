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
package eu.arrowhead.common.http.model;

public record HttpDataModelsOperationModel(
		String input,
		String output) {

	//=================================================================================================
	// members

	public static final String PROP_NAME_INPUT = "input";
	public static final String PROP_NAME_OUTPUT = "output";

	//=================================================================================================
	// nested classes

	//-------------------------------------------------------------------------------------------------
	public static class Builder {

		//=================================================================================================
		// members

		private String input;
		private String output;

		//=================================================================================================
		// methods

		//-------------------------------------------------------------------------------------------------
		public Builder input(final String input) {
			this.input = input;
			return this;
		}

		//-------------------------------------------------------------------------------------------------
		public Builder output(final String output) {
			this.output = output;
			return this;
		}

		//-------------------------------------------------------------------------------------------------
		public HttpDataModelsOperationModel build() {
			return new HttpDataModelsOperationModel(input, output);
		}
	}
}