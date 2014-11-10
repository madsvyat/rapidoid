package org.rapidoid.html.impl;

/*
 * #%L
 * rapidoid-html
 * %%
 * Copyright (C) 2014 Nikolche Mihajlovski
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

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.rapidoid.html.Action;
import org.rapidoid.html.Tag;
import org.rapidoid.html.TagBase;
import org.rapidoid.html.TagEventHandler;
import org.rapidoid.util.Cls;
import org.rapidoid.util.U;

public class TagProxy<TAG extends Tag<?>> implements InvocationHandler {

	public static <TAG extends Tag<?>> TAG create(Class<TAG> tagInterface, String tagName, Object[] contents) {
		TagImpl<TAG> tag = new TagImpl<TAG>(tagInterface, tagName, contents);

		TAG proxy = Cls.createProxy(new TagProxy<TAG>(tag, tagInterface), tagInterface, TagInternals.class);
		tag.setProxy(proxy);

		return proxy;
	}

	private final TagImpl<TAG> tag;

	private final Class<?> clazz;

	public TagProxy(TagImpl<TAG> tag, Class<TAG> clazz) {
		this.tag = tag;
		this.clazz = clazz;
	}

	@SuppressWarnings("unchecked")
	public Object invoke(Object target, Method method, Object[] args) throws Throwable {

		Class<?> methodClass = method.getDeclaringClass();

		if (methodClass.equals(Object.class) || methodClass.equals(TagBase.class)
				|| methodClass.equals(TagInternals.class)) {
			return method.invoke(tag, args);
		}

		String name = method.getName();
		Class<?> ret = method.getReturnType();
		Class<?>[] paramTypes = method.getParameterTypes();

		boolean returnsTag = ret.isAssignableFrom(clazz);
		boolean returnsObj = ret.equals(Object.class);
		boolean returnsStr = ret.equals(String.class);
		boolean returnsBool = ret.equals(boolean.class);

		boolean has0arg = paramTypes.length == 0;
		boolean has1arg = paramTypes.length == 1;

		// event handlers
		if (name.startsWith("on") && name.length() > 2) {

			String event = name.substring(2).toLowerCase();

			// handlers setter
			if (returnsTag && has1arg && TagEventHandler.class.isAssignableFrom(paramTypes[0])) {
				tag.setHandler(event, (TagEventHandler<TAG>) args[0]);
				return target;
			}

			// action setter
			if (returnsTag && has1arg && Action[].class.isAssignableFrom(paramTypes[0])) {
				tag.setHandler(event, (Action[]) args[0]);
				return target;
			}
		}

		// value setter
		if (returnsTag && has1arg && paramTypes[0].equals(Object.class) && name.equals("value")) {
			tag.value(args[0]);
			return target;
		}

		// value getter
		if (returnsObj && has0arg && name.equals("value")) {
			return tag.value();
		}

		// String attribute setter
		if (returnsTag && has1arg && paramTypes[0].equals(String.class)) {
			tag.attr(name, (String) args[0]);
			return target;
		}

		// String attribute getter
		if (returnsStr && has0arg) {
			return tag.attr(name);
		}

		// boolean attribute setter
		if (returnsTag && has1arg && paramTypes[0].equals(boolean.class)) {
			tag.is(name, (Boolean) args[0]);
			return target;
		}

		// boolean attribute getter
		if (returnsBool && has0arg) {
			return tag.is(name);
		}

		// in case something is missed
		throw U.rte("Not implemented: " + name);
	}

}
