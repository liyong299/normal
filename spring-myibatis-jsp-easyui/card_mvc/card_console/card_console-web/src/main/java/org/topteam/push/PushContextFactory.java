package org.topteam.push;

/**
 * Created by 枫 on 2014/8/23.
 */
public class PushContextFactory {

    private static final PushContextFactory p = new PushContextFactory();
    private final PushContext pushContext;

    private PushContextFactory() {
        pushContext = new PushContextImpl();
    }

    public final static PushContextFactory getDefault() {
        return p;
    }

    public PushContext getPushContext(){
        return pushContext;
    }
}
