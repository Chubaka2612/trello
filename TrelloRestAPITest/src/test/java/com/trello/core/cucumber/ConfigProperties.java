package com.trello.core.cucumber;

import ru.yandex.qatools.properties.PropertyLoader;
import ru.yandex.qatools.properties.annotations.Property;
import ru.yandex.qatools.properties.annotations.Resource;

/***
 * This class is responsible for getting information from property file
 * and provide other class with needed settings
 */
@Resource.Classpath("properties/config.properties")
public class ConfigProperties {

    @Property("app.key")
    private String appKey;

    @Property("access.token")
    private  String accessToken;

    public String getAppKey() {
		return appKey;
	}

	public  String getAccessToken() {
		return accessToken;
	}

	public ConfigProperties() {
        PropertyLoader.populate(this);
    }

}