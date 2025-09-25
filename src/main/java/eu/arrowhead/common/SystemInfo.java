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
package eu.arrowhead.common;

import java.security.PublicKey;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import ai.aitia.arrowhead.Constants;
import eu.arrowhead.common.exception.InvalidParameterException;
import eu.arrowhead.common.http.filter.authentication.AuthenticationPolicy;
import eu.arrowhead.common.model.ServiceModel;
import eu.arrowhead.common.model.SystemModel;
import eu.arrowhead.common.service.validation.address.AddressNormalizer;
import eu.arrowhead.common.service.validation.name.SystemNameNormalizer;
import eu.arrowhead.common.service.validation.name.SystemNameValidator;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;

public abstract class SystemInfo {

	//=================================================================================================
	// members

	@Value(Constants.$SERVER_ADDRESS)
	private String serverAddress;

	@Value(Constants.$SERVER_PORT)
	private int serverPort;

	@Value(Constants.$DOMAIN_NAME)
	private String domainAddress;

	@Value(Constants.$SYSTEM_NAME)
	private String rawSystemName;
	private String systemName;

	@Value(Constants.$SERVICE_REGISTRY_ADDRESS_WD)
	private String serviceRegistryAddress;

	@Value(Constants.$SERVICE_REGISTRY_PORT_WD)
	private int serviceRegistryPort;

	@Value(Constants.$AUTHENTICATION_POLICY_WD)
	private AuthenticationPolicy authenticationPolicy;

	@Value(Constants.$AUTHENTICATOR_LOGIN_DELAY_WD)
	private long authenticatorLoginDelay;

	@Value(Constants.$AUTHENTICATOR_CREDENTIALS)
	private Map<String, String> authenticatorCredentials;

	@Value(Constants.$MQTT_API_ENABLED_WD)
	private boolean mqttEnabled;

	@Value(Constants.$MQTT_BROKER_ADDRESS_WD)
	private String mqttBrokerAddress;

	@Value(Constants.$MQTT_BROKER_PORT_WD)
	private Integer mqttBrokerPort;

	@Value(Constants.$MQTT_CLIENT_PASSWORD)
	private String mqttClientPassword;

	@Autowired
	private SSLProperties sslProperties;

	@Autowired
	private AddressNormalizer addressNormalizer;

	@Resource(name = Constants.ARROWHEAD_CONTEXT)
	private Map<String, Object> arrowheadContext;

	@Autowired
	private SystemNameNormalizer systemNameNormalizer;

	@Autowired
	private SystemNameValidator systemNameValidator;

	//=================================================================================================
	// methods

	//-------------------------------------------------------------------------------------------------
	public abstract SystemModel getSystemModel();

	//-------------------------------------------------------------------------------------------------
	public abstract List<ServiceModel> getServices();

	//-------------------------------------------------------------------------------------------------
	public String getIdentityToken() {
		return authenticationPolicy == AuthenticationPolicy.OUTSOURCED ? (String) arrowheadContext.get(Constants.KEY_IDENTITY_TOKEN) : null;
	}

	//-------------------------------------------------------------------------------------------------
	public boolean isSslEnabled() {
		return sslProperties != null && sslProperties.isSslEnabled();
	}

	//=================================================================================================
	// assistant methods

	//-------------------------------------------------------------------------------------------------
	protected void customInit() {
	};

	//-------------------------------------------------------------------------------------------------
	public String getAddress() {
		return addressNormalizer.normalize(domainAddress);
	}

	//-------------------------------------------------------------------------------------------------
	protected String getPublicKey() {
		if (arrowheadContext.containsKey(Constants.SERVER_PUBLIC_KEY)) {
			final PublicKey publicKey = (PublicKey) arrowheadContext.get(Constants.SERVER_PUBLIC_KEY);

			return Base64.getEncoder().encodeToString(publicKey.getEncoded());
		}

		return "";
	}

	//-------------------------------------------------------------------------------------------------
	@PostConstruct
	private void init() {
		if (Utilities.isEmpty(rawSystemName)) {
			throw new InvalidParameterException("System name is missing or empty");
		}

		if (Utilities.isEmpty(domainAddress)) {
			throw new InvalidParameterException("'domainAddress' is missing or empty");
		}

		if (mqttEnabled && Utilities.isEmpty(mqttBrokerAddress)) {
			throw new InvalidParameterException("MQTT Broker address is not defined");
		}

		if (mqttEnabled && mqttBrokerPort == null) {
			throw new InvalidParameterException("MQTT Broker port is not defined");
		}

		if (AuthenticationPolicy.OUTSOURCED == authenticationPolicy && Utilities.isEmpty(authenticatorCredentials)) {
			throw new InvalidParameterException("No credentials are specified to login to the authentication system");
		}

		initSystemName();
		customInit();
	}

	//-------------------------------------------------------------------------------------------------
	private void initSystemName() {
		systemName = systemNameNormalizer.normalize(rawSystemName);
		systemNameValidator.validateSystemName(systemName);
	}

	//=================================================================================================
	// boilerplate

	//-------------------------------------------------------------------------------------------------
	public String getServerAddress() {
		return serverAddress;
	}

	//-------------------------------------------------------------------------------------------------
	public int getServerPort() {
		return serverPort;
	}

	//-------------------------------------------------------------------------------------------------
	public String getDomainAddress() {
		return domainAddress;
	}

	//-------------------------------------------------------------------------------------------------
	public String getSystemName() {
		return systemName;
	};

	//-------------------------------------------------------------------------------------------------
	public String getServiceRegistryAddress() {
		return serviceRegistryAddress;
	}

	//-------------------------------------------------------------------------------------------------
	public int getServiceRegistryPort() {
		return serviceRegistryPort;
	}

	//-------------------------------------------------------------------------------------------------
	public AuthenticationPolicy getAuthenticationPolicy() {
		return authenticationPolicy;
	}

	//-------------------------------------------------------------------------------------------------
	public boolean isMqttApiEnabled() {
		return this.mqttEnabled;
	}

	//-------------------------------------------------------------------------------------------------
	public String getMqttBrokerAddress() {
		return this.mqttBrokerAddress;
	}

	//-------------------------------------------------------------------------------------------------
	public String getMqttClientPassword() {
		return this.mqttClientPassword;
	}

	//-------------------------------------------------------------------------------------------------
	public Integer getMqttBrokerPort() {
		return this.mqttBrokerPort;
	}

	//-------------------------------------------------------------------------------------------------
	public SSLProperties getSslProperties() {
		return sslProperties;
	}

	//-------------------------------------------------------------------------------------------------
	public Map<String, Object> getArrowheadContext() {
		return arrowheadContext;
	}

	//-------------------------------------------------------------------------------------------------
	public long getAuthenticatorLoginDelay() {
		return authenticatorLoginDelay;
	}

	//-------------------------------------------------------------------------------------------------
	public Map<String, String> getAuthenticatorCredentials() {
		return Collections.unmodifiableMap(authenticatorCredentials);
	}

	//=================================================================================================
	// nested structures

	//-------------------------------------------------------------------------------------------------
	public record PublicConfigurationKeysAndDefaults(Set<String> configKeys, Class<?> defaultsClass) {
	}
}