package org.rapidoid.render;
/*
 * #%L
 * rapidoid-render
 * %%
 * Copyright (C) 2014 - 2016 Nikolche Mihajlovski and contributors
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

import org.junit.Before;
import org.rapidoid.commons.Env;
import org.rapidoid.config.Conf;
import org.rapidoid.data.JSON;
import org.rapidoid.io.Res;
import org.rapidoid.log.Log;
import org.rapidoid.log.LogLevel;
import org.rapidoid.test.TestCommons;

public abstract class AbstractRenderTest extends TestCommons {

	@Before
	public void openContext() {
		Res.reset();
		Conf.reset();
		Conf.setPath(getTestName());
		Log.setLogLevel(LogLevel.INFO);
		Env.profiles().clear();
		Env.profiles().add("default");
	}

	protected void verify(String name, Object actual) {
		super.verifyCase(name, JSON.prettify(actual), name);
	}

}
