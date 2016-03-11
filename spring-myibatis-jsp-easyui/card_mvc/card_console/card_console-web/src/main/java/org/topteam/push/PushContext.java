package org.topteam.push;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by 枫 on 2014/8/23.
 */

/**
 *
 */
public interface PushContext {

    <T> Future<T> push(String channel, T t);

    <T> Future<T> schedule(String channel, T t, int time, TimeUnit unit);

    <T> Future<T> delay(String channel, T t, int time, TimeUnit unit);

    PushContext addListener(PushContextListener p);

    PushContext removeListener(PushContextListener p);

    String getPushServer();
}
