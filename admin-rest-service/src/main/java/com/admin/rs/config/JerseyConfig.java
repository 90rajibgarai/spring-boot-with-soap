package com.admin.rs.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
@ApplicationPath("/admin")
public class JerseyConfig extends ResourceConfig
{
	public JerseyConfig()
	{
		packages("com.admin.rs.controller");
	}
}
