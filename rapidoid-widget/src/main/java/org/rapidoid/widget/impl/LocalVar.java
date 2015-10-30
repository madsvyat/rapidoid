package org.rapidoid.widget.impl;

/*
 * #%L
 * rapidoid-widget
 * %%
 * Copyright (C) 2014 - 2015 Nikolche Mihajlovski and contributors
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

import java.io.Serializable;

import org.rapidoid.annotation.Authors;
import org.rapidoid.annotation.Since;
import org.rapidoid.ctx.Ctxs;
import org.rapidoid.u.U;

@Authors("Nikolche Mihajlovski")
@Since("4.0.0")
public class LocalVar<T extends Serializable> extends WidgetVar<T> {

	private static final long serialVersionUID = 2761159925375675659L;

	private final String localKey;

	private final T defaultValue;

	public LocalVar(String localKey, T defaultValue, boolean initial) {
		super(localKey, initial);
		this.localKey = localKey;
		this.defaultValue = defaultValue;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T get() {
		T val = (T) U.or(Ctxs.ctx().data().get(localKey), defaultValue);
		return val;
	}

	@Override
	public void set(T value) {
		Ctxs.ctx().data().put(localKey, value);
	}

}
