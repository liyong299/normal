package org.topteam.push;

/**
 * Created by 枫 on 2014/8/23.
 */
public interface PushContextListener {

    /**
     * Invoked when a message has been successfully pushed to channel.
     * @param channel A String used when calling the {@link PushContext#push(String, Object)}
     * @param message The message pushed.
     */
    void onComplete(String channel, Object message);

    /**
     * Return the original request that was suspended by the {@link PushServlet}.
     * @param request
     */
    void onDisconnect(Object request);
}
