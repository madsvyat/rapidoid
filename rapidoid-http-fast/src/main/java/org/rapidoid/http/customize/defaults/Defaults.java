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

package org.rapidoid.http.customize.defaults;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.rapidoid.RapidoidThing;
import org.rapidoid.annotation.Authors;
import org.rapidoid.annotation.Since;
import org.rapidoid.data.JSON;
import org.rapidoid.data.XML;
import org.rapidoid.env.Env;
import org.rapidoid.http.HttpWrapper;
import org.rapidoid.http.customize.*;
import org.rapidoid.scan.ClasspathUtil;
import org.rapidoid.u.U;

import java.util.List;

@Authors("Nikolche Mihajlovski")
@Since("5.1.7")
public class Defaults extends RapidoidThing {

	private static final String[] staticFilesPath = {"static", "default/static"};

	private static final HttpWrapper[] wrappers = {};

	private static final ErrorHandler errorHandler = new DefaultErrorHandler();

	private static final HttpResponseRenderer jsonResponseRenderer = new DefaultJsonResponseRenderer();

	private static final HttpResponseRenderer xmlResponseRenderer = new DefaultXmlResponseRenderer();

	private static final BeanParameterFactory beanParameterFactory = new DefaultBeanParameterFactory();

	private static final BeanValidator validator = new DefaultBeanValidator();

	private static final LoginProvider loginProvider = new DefaultLoginProvider();

	private static final RolesProvider rolesProvider = new DefaultRolesProvider();

	private static final ObjectMapper objectMapper = JSON.newMapper();

	private static final XmlMapper xmlMapper = XML.newMapper();

	private static final HttpRequestBodyParser jsonRequestBodyParser = new DefaultJsonRequestBodyParser();

	private static final HttpRequestBodyParser xmlRequestBodyParser = new DefaultXmlRequestBodyParser();

	private static final SessionManager sessionManager = new DefaultSessionManager();

	private static final StaticFilesSecurity staticFilesSecurity = new DefaultStaticFilesSecurity();

	public static String[] staticFilesPath() {
		List<String> path = U.list(staticFilesPath);

		if (Env.isInitialized() && Env.dev()) {
			path.addAll(0, ClasspathUtil.getClasspathStaticFolders());
		}

		return U.arrayOf(path);
	}

	public static ErrorHandler errorHandler() {
		return errorHandler;
	}

	public static HttpResponseRenderer jsonResponseRenderer() {
		return jsonResponseRenderer;
	}

	public static HttpResponseRenderer xmlResponseRenderer() {
		return xmlResponseRenderer;
	}

	public static BeanParameterFactory beanParameterFactory() {
		return beanParameterFactory;
	}

	public static BeanValidator validator() {
		return validator;
	}

	public static LoginProvider loginProvider() {
		return loginProvider;
	}

	public static RolesProvider rolesProvider() {
		return rolesProvider;
	}

	public static ObjectMapper objectMapper() {
		return objectMapper;
	}

	public static XmlMapper xmlMapper() {
		return xmlMapper;
	}

	public static HttpRequestBodyParser jsonRequestBodyParser() {
		return jsonRequestBodyParser;
	}

	public static HttpRequestBodyParser xmlRequestBodyParser() {
		return xmlRequestBodyParser;
	}

	public static SessionManager sessionManager() {
		return sessionManager;
	}

	public static StaticFilesSecurity staticFilesSecurity() {
		return staticFilesSecurity;
	}

	public static HttpWrapper[] wrappers() {
		return wrappers;
	}

}
