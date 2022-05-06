/*-
 * #%L
 * rapidoid-commons
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

package org.rapidoid.ctx;

import org.rapidoid.RapidoidThing;
import org.rapidoid.annotation.Authors;
import org.rapidoid.annotation.Since;
import org.rapidoid.collection.Coll;
import org.rapidoid.log.Log;
import org.rapidoid.u.U;


@Authors("Nikolche Mihajlovski")
@Since("4.1.0")
public class Ctxs extends RapidoidThing {

	private static volatile ThreadLocal<Ctx> CTXS = new ThreadLocal<Ctx>();

	private Ctxs() {
	}

	public static Ctx get() {
		return CTXS.get();
	}

	public static Ctx required() {
		Ctx ctx = get();

		if (ctx == null) {
			throw new IllegalStateException("No context is available!");
		}

		return ctx;
	}

	public static boolean hasContext() {
		return get() != null;
	}

	public static void attach(Ctx ctx) {
		if (!hasContext()) {
			if (ctx != null) {
				CTXS.set(ctx);
			}
		} else {
			throw new IllegalStateException("The context was already opened: " + required());
		}
	}

	public static Ctx open(WithContext context) {
		Ctx ctx = Ctxs.open(context.tag());

		ctx.setExchange(context.exchange());
		ctx.setPersister(context.persister());
		ctx.setUser(new UserInfo(context.username(), context.roles(), context.scope()));
		Coll.assign(ctx.extras(), U.safe(context.extras()));

		return ctx;
	}

	public static Ctx open(String tag) {
		Ctx ctx = new Ctx(tag);
		Log.debug("Opening context", "ctx", ctx);
		attach(ctx);
		return ctx;
	}

	public static void close() {
		try {
			Ctx ctx = get();

			if (ctx != null) {
				ctx.close();
			} else {
				// don't throw error here, the context might be "double-closed" on shutdown
				Log.warn("The context was already closed!");
			}
		} finally {
			CTXS.remove();
		}
	}

	public static void reset() {
		CTXS = new ThreadLocal<Ctx>();
	}
}
