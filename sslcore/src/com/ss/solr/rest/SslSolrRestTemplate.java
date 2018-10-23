/**
 *
 */
package com.ss.solr.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.feed.AtomFeedHttpMessageConverter;
import org.springframework.http.converter.feed.RssChannelHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.util.ClassUtils;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

/**
 * @author ashishsabal
 *
 */
public class SslSolrRestTemplate extends RestTemplate {
	private static boolean romePresent = ClassUtils.isPresent(
			"com.rometools.rome.feed.WireFeed",
			RestTemplate.class.getClassLoader());

	private static final boolean jaxb2Present = ClassUtils.isPresent(
			"javax.xml.bind.Binder", RestTemplate.class.getClassLoader());

	private static final boolean jackson2Present = (ClassUtils.isPresent(
			"com.fasterxml.jackson.databind.ObjectMapper",
			RestTemplate.class.getClassLoader()))
			&& (ClassUtils.isPresent(
					"com.fasterxml.jackson.core.JsonGenerator",
					RestTemplate.class.getClassLoader()));

	private static final boolean gsonPresent = ClassUtils.isPresent(
			"com.google.gson.Gson", RestTemplate.class.getClassLoader());

	private final List<HttpMessageConverter<?>> messageConverters;

	public SslSolrRestTemplate() {
		this.messageConverters = new ArrayList();

		super.setErrorHandler(new DefaultResponseErrorHandler());
		// super.headersExtractor = new HeadersExtractor(null);

		this.messageConverters.add(new ByteArrayHttpMessageConverter());
		this.messageConverters.add(new StringHttpMessageConverter());
		this.messageConverters.add(new ResourceHttpMessageConverter());
		this.messageConverters.add(new SourceHttpMessageConverter());
		this.messageConverters
				.add(new AllEncompassingFormHttpMessageConverter());

		if (romePresent) {
			super.getMessageConverters()
					.add(new AtomFeedHttpMessageConverter());
			super.getMessageConverters().add(
					new RssChannelHttpMessageConverter());
		}
		if (jaxb2Present) {
			this.messageConverters
					.add(new Jaxb2RootElementHttpMessageConverter());
		}
		if (jackson2Present) {
			this.messageConverters
					.add(new MappingJackson2HttpMessageConverter());
		} else if (gsonPresent) {
			this.messageConverters.add(new GsonHttpMessageConverter());
		}

		super.setMessageConverters(this.messageConverters);
	}
}
