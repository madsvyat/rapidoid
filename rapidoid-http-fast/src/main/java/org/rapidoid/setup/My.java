/*-
 * #%L
 * rapidoid-http-fast
 * %%
 * Copyright (C) 2014 - 2018 Nikolche Mihajlovski and contributors
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

package org.rapidoid.setup;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.rapidoid.RapidoidThing;
import org.rapidoid.annotation.Authors;
import org.rapidoid.annotation.Since;
import org.rapidoid.http.HttpWrapper;
import org.rapidoid.http.customize.*;
import org.rapidoid.http.customize.defaults.Defaults;

@Authors("Nikolche Mihajlovski")
@Since("5.1.0")
public class My extends RapidoidThing {

	private static final Customization GLOBAL = new Customization("my", null, null);

	static {
		reset();
	}

	public static void reset() {
		synchronized (GLOBAL) {
			GLOBAL.reset();
			GLOBAL.staticFilesPath(Defaults.staticFilesPath());
			GLOBAL.errorHandler(Defaults.errorHandler());
			GLOBAL.jsonResponseRenderer(Defaults.jsonResponseRenderer());
			GLOBAL.jsonRequestBodyParser(Defaults.jsonRequestBodyParser());
			GLOBAL.xmlResponseRenderer(Defaults.xmlResponseRenderer());
			GLOBAL.xmlRequestBodyParser(Defaults.xmlRequestBodyParser());
			GLOBAL.beanParameterFactory(Defaults.beanParameterFactory());
			GLOBAL.loginProvider(Defaults.loginProvider());
			GLOBAL.rolesProvider(Defaults.rolesProvider());
			GLOBAL.validator(Defaults.validator());
			GLOBAL.objectMapper(Defaults.objectMapper());
			GLOBAL.xmlMapper(Defaults.xmlMapper());
			GLOBAL.sessionManager(Defaults.sessionManager());
			GLOBAL.staticFilesSecurity(Defaults.staticFilesSecurity());
			GLOBAL.wrappers(Defaults.wrappers());
		}
	}

	public static Customization custom() {
		return GLOBAL;
	}

	public static String[] staticFilesPath() {
		return GLOBAL.staticFilesPath();
	}

	public static void staticFilesPath(String... staticFilesPath) {
		GLOBAL.staticFilesPath(staticFilesPath);
	}

	public static String[] templatesPath() {
		return GLOBAL.templatesPath();
	}

	public static void templatesPath(String... templatesPath) {
		GLOBAL.templatesPath(templatesPath);
	}

	public static HttpWrapper[] wrappers() {
		return GLOBAL.wrappers();
	}

	public static void wrappers(HttpWrapper... wrappers) {
		GLOBAL.wrappers(wrappers);
	}

	public static void errorHandler(ErrorHandler errorHandler) {
		GLOBAL.errorHandler(errorHandler);
	}

	public static void jsonResponseRenderer(HttpResponseRenderer jsonResponseRenderer) {
		GLOBAL.jsonResponseRenderer(jsonResponseRenderer);
	}

	public static void xmlResponseRenderer(HttpResponseRenderer xmlResponseRenderer) {
		GLOBAL.xmlResponseRenderer(xmlResponseRenderer);
	}

	public static void beanParameterFactory(BeanParameterFactory beanParameterFactory) {
		GLOBAL.beanParameterFactory(beanParameterFactory);
	}

	public static void validator(BeanValidator validator) {
		GLOBAL.validator(validator);
	}

	public static void loginProvider(LoginProvider loginProvider) {
		GLOBAL.loginProvider(loginProvider);
	}

	public static void rolesProvider(RolesProvider rolesProvider) {
		GLOBAL.rolesProvider(rolesProvider);
	}

	public static void pageDecorator(PageDecorator pageDecorator) {
		GLOBAL.pageDecorator(pageDecorator);
	}

	public static void viewResolver(ViewResolver viewResolver) {
		GLOBAL.viewResolver(viewResolver);
	}

	public static void objectMapper(ObjectMapper jackson) {
		GLOBAL.objectMapper(jackson);
	}

	public static void xmlMapper(XmlMapper xmlMapper) {
		GLOBAL.xmlMapper(xmlMapper);
	}

	public static ErrorHandler errorHandler() {
		return GLOBAL.errorHandler();
	}

	public static HttpResponseRenderer jsonResponseRenderer() {
		return GLOBAL.jsonResponseRenderer();
	}

	public static HttpResponseRenderer xmlResponseRenderer() {
		return GLOBAL.xmlResponseRenderer();
	}

	public static BeanParameterFactory beanParameterFactory() {
		return GLOBAL.beanParameterFactory();
	}

	public static BeanValidator validator() {
		return GLOBAL.validator();
	}

	public static LoginProvider loginProvider() {
		return GLOBAL.loginProvider();
	}

	public static RolesProvider rolesProvider() {
		return GLOBAL.rolesProvider();
	}

	public static PageDecorator pageDecorator() {
		return GLOBAL.pageDecorator();
	}

	public static ViewResolver viewResolver() {
		return GLOBAL.viewResolver();
	}

	public static ObjectMapper objectMapper() {
		return GLOBAL.objectMapper();
	}

	public static HttpRequestBodyParser jsonRequestBodyParser() {
		return GLOBAL.jsonRequestBodyParser();
	}

	public static void jsonRequestBodyParser(HttpRequestBodyParser jsonRequestBodyParser) {
		GLOBAL.jsonRequestBodyParser(jsonRequestBodyParser);
	}

	public static SessionManager sessionManager() {
		return GLOBAL.sessionManager();
	}

	public static Customization sessionManager(SessionManager sessionManager) {
		return GLOBAL.sessionManager(sessionManager);
	}

	public static StaticFilesSecurity staticFilesSecurity() {
		return GLOBAL.staticFilesSecurity();
	}

	public static Customization staticFilesSecurity(StaticFilesSecurity staticFilesSecurity) {
		return GLOBAL.staticFilesSecurity(staticFilesSecurity);
	}

	public static OnError error(Class<? extends Throwable> error) {
		return new OnError(GLOBAL, error);
	}

	public static ResourceLoader templateLoader() {
		return GLOBAL.templateLoader();
	}

	public static Customization templateLoader(ResourceLoader templateLoader) {
		return GLOBAL.templateLoader(templateLoader);
	}

}
