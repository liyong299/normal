package org.topteam.push;

import org.atmosphere.client.TrackMessageSizeInterceptor;
import org.atmosphere.cpr.AtmosphereServlet;
import org.atmosphere.interceptor.AtmosphereResourceLifecycleInterceptor;
import org.atmosphere.interceptor.HeartbeatInterceptor;
import org.atmosphere.interceptor.SuspendTrackerInterceptor;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by 枫 on 2014/8/23.
 */
public class PushServlet extends AtmosphereServlet {

    private final Logger logger = Logger.getLogger(PushServlet.class.getName());
    public final static String RULES = "org.topteam.push.rules";

    @Override
    public void init(ServletConfig sc) throws ServletException {
        PushContext c = PushContextFactory.getDefault().getPushContext();
        if (PushContextImpl.class.isAssignableFrom(c.getClass())) {
            framework().asyncSupportListener(PushContextImpl.class.cast(c));
        }
        super.init(sc);
        framework
                .interceptor(new AtmosphereResourceLifecycleInterceptor())
                .interceptor(new HeartbeatInterceptor())
                .interceptor(new TrackMessageSizeInterceptor())
                .interceptor(new SuspendTrackerInterceptor())
                .addAtmosphereHandler("/*", new TopTeamAtmosphereHandler(configureRules(sc)))
                .initAtmosphereHandler(sc);
    }

    List<PushRule> configureRules(ServletConfig sc) {
        List<PushRule> rules = new ArrayList<PushRule>();

        String s = sc.getInitParameter(RULES);

        if (s != null) {
            String[] r = s.split(",");
            for (String rule : r) {
                try {
                    rules.add(loadRule(rule));
                    logger.log(Level.INFO, "PushRule " + rule + " loaded");
                } catch (Throwable t) {
                    logger.log(Level.WARNING, "Unable to load PushRule " + rule, t);
                }
            }
        }

        if (rules.isEmpty()) {
            rules.add(new DefaultPushRule());
        }

        return rules;
    }

    PushRule loadRule(String ruleName) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        try {
            return (PushRule) Thread.currentThread().getContextClassLoader().loadClass(ruleName).newInstance();
        } catch (Throwable t) {
            return (PushRule) getClass().getClassLoader().loadClass(ruleName).newInstance();
        }
    }

}
