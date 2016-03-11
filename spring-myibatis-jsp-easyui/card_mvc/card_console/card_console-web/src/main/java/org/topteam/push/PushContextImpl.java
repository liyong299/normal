/*
 * Copyright 2009-2013 PrimeTek.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.topteam.push;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import org.atmosphere.cpr.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class PushContextImpl extends AsyncSupportListenerAdapter implements PushContext {

    private final ConcurrentLinkedQueue<PushContextListener> listeners = new ConcurrentLinkedQueue<PushContextListener>();
    private final MetaBroadcaster broadcaster = MetaBroadcaster.getDefault();

    @Override
	public <T> Future<T> push(final String channel, final T t) {
        String data = toJSON(t);
        final BroadcasterListener l = new PushContextMetaListener<T>(listeners, channel, t);
        final Future<?> f = broadcaster.addBroadcasterListener(l).broadcastTo(channel, data);

        finalizePush(f, l);
        return new WrappedFuture<T>(f, t);
    }

    @Override
	public <T> Future<T> schedule(final String channel, final T t, int time, TimeUnit unit) {
        Object data = t;
        if(!(t instanceof Callable || t instanceof Runnable)) {
            data = toJSON(t);
        }

        final BroadcasterListener l = new PushContextMetaListener<T>(listeners, channel, t);
        final Future<List<Broadcaster>> f = broadcaster.addBroadcasterListener(l).scheduleTo(channel, data, time, unit);

        finalizePush(f, l);
        return new WrappedFuture<T>(f, t);
    }

    @Override
	public <T> Future<T> delay(final String channel, final T t, int time, TimeUnit unit) {
        String data = toJSON(t);

        final BroadcasterListener l = new PushContextMetaListener<T>(listeners, channel, t);
        final Future<?> f = broadcaster.addBroadcasterListener(l).delayTo(channel, data, time, unit);

        finalizePush(f, l);
        return new WrappedFuture<T>(f, t);
    }

    private void finalizePush(Future<?> f, BroadcasterListener l) {
        if (f.isDone()) {
            broadcaster.removeBroadcasterListener(l);
        }
    }

    @Override
	public PushContext addListener(PushContextListener p) {
        listeners.add(p);
        return this;
    }

    @Override
	public PushContext removeListener(PushContextListener p) {
        listeners.remove(p);
        return this;
    }

    @Override
    public String getPushServer() {
        return null;
    }

    private String toJSON(Object data) {
        try {
            StringBuilder jsonBuilder = new StringBuilder();
            jsonBuilder.append("{");

            if(isBean(data)) {
                jsonBuilder.append("\"").append("data").append("\":").append(JSON.toJSONString(data));
            }
            else {
                Map<String,Object> map = new HashMap<String, Object>();
                map.put("data",data);
                String json = JSON.toJSONString(map);

                jsonBuilder.append(json.substring(1, json.length() - 1));
            }

            jsonBuilder.append("}");

            return jsonBuilder.toString();
        }

        catch(JSONException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

    }

    private boolean isBean(Object value) {
        if(value == null) {
            return false;
        }

        if(value instanceof Boolean || value instanceof String || value instanceof Number) {
            return false;
        }

        return true;
    }

    @Override
    public void onTimeout(AtmosphereRequest request, AtmosphereResponse response) {
        for (PushContextListener l : listeners) {
            l.onDisconnect(request);

            if (l instanceof AdvancedPushContextListener) {
                ((AdvancedPushContextListener) l).onTimeout(request, response);
            }
        }
    }

    @Override
    public void onClose(AtmosphereRequest request, AtmosphereResponse response) {
        for (PushContextListener l : listeners) {
            l.onDisconnect(request);

            if (l instanceof AdvancedPushContextListener) {
                ((AdvancedPushContextListener) l).onClose(request, response);
            }
        }
    }

    @Override
    public void onResume(AtmosphereRequest request, AtmosphereResponse response) {
        for (PushContextListener l : listeners) {
            if (l instanceof AdvancedPushContextListener) {
                ((AdvancedPushContextListener) l).onResume(request, response);
            }
        }
    }

    @Override
    public void onDestroyed(AtmosphereRequest request, AtmosphereResponse response) {
        for (PushContextListener l : listeners) {
            if (l instanceof AdvancedPushContextListener) {
                ((AdvancedPushContextListener) l).onDestroyed(request, response);
            }
        }
    }

    @Override
    public void onSuspend(AtmosphereRequest request, AtmosphereResponse response) {
        for (PushContextListener l : listeners) {
            if (l instanceof AdvancedPushContextListener) {
                ((AdvancedPushContextListener) l).onSuspend(request, response);
            }
        }
    }

    private final static class WrappedFuture<T> implements Future<T> {

        private final Future<?> f;
        private final T t;

        private WrappedFuture(Future<?> f, T t) {
            this.f = f;
            this.t = t;
        }

        @Override
		public boolean cancel(boolean b) {
            return f.cancel(b);
        }

        @Override
		public boolean isCancelled() {
            return f.isCancelled();
        }

        @Override
		public boolean isDone() {
            return f.isDone();
        }

        @Override
		public T get() throws InterruptedException, ExecutionException {
            f.get();
            return t;
        }

        @Override
		public T get(long l, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
            f.get(l, timeUnit);
            return t;
        }
    }

    private final static class PushContextMetaListener<T> implements BroadcasterListener {
        private final ConcurrentLinkedQueue<PushContextListener> listeners;
        private final String channel;
        private final T t;

        private PushContextMetaListener(ConcurrentLinkedQueue<PushContextListener> listeners, String channel, T t) {
            this.listeners = listeners;
            this.channel = channel;
            this.t = t;
        }

        @Override
        public void onMessage(Broadcaster broadcaster, Deliver deliver) {

        }

        @Override
		public void onPostCreate(Broadcaster broadcaster) {
            for (PushContextListener l : listeners) {
                if (l instanceof AdvancedPushContextListener) {
                    ((AdvancedPushContextListener) l).onPostCreate(broadcaster);
                }
            }
        }

        @Override
		public void onComplete(Broadcaster b) {
            for (PushContextListener p: listeners) {
                p.onComplete(channel, t);
            }

            MetaBroadcaster broadcaster = MetaBroadcaster.getDefault();
            broadcaster.removeBroadcasterListener(this);
        }

        @Override
		public void onPreDestroy(Broadcaster broadcaster) {
            for (PushContextListener l : listeners) {
                if (l instanceof AdvancedPushContextListener) {
                    ((AdvancedPushContextListener) l).onPreDestroy(broadcaster);
                }
            }
        }

        @Override
		public void onAddAtmosphereResource(Broadcaster broadcaster, AtmosphereResource resource) {

        }

        @Override
		public void onRemoveAtmosphereResource(Broadcaster broadcaster, AtmosphereResource resource) {

        }


    }
}
