package com.client.rs.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
@ApplicationPath("/client")
public class JerseyConfig extends ResourceConfig
{
	public JerseyConfig()
	{
		packages("com.client.rs.controller");
	}
}
