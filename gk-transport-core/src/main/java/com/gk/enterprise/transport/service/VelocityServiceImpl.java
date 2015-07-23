package com.gk.enterprise.transport.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.gk.enterprise.transport.bean.Client;

public class VelocityServiceImpl {

	public String templateToString(Client client) throws VelocityException,
			IOException {
		Properties velocityProperties = new Properties();
		velocityProperties.put("resource.loader", "class");
		velocityProperties
				.put("class.resource.loader.class",
						"org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

		VelocityEngineFactoryBean velocityEngineFactoryBean = new VelocityEngineFactoryBean();
		velocityEngineFactoryBean.setVelocityProperties(velocityProperties);

		VelocityEngine velocityEngine = velocityEngineFactoryBean
				.createVelocityEngine();

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("client", client);

		return VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
				"com/gk/enterprise/transport/emailTemplate.vm", model);
	}
}
