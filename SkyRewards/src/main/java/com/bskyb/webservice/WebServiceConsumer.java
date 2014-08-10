package com.bskyb.webservice;

import org.springframework.web.client.RestTemplate;

public class WebServiceConsumer {
	String webserviceURL;

	public <T> T invoke(String path, Class<T> responseType) {
		String url = webserviceURL + path;
		try {
			RestTemplate restTemplate = new RestTemplate();
			T response = restTemplate.getForObject(url, responseType);
			return response;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public void setWebserviceURL(String webserviceURL) {
		this.webserviceURL = webserviceURL;
	}

}
