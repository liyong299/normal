package org.topteam.push;

import org.atmosphere.cpr.AtmosphereRequest;
import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.cpr.Broadcaster;
import org.atmosphere.cpr.BroadcasterFactory;

/**
 * Created by 枫 on 2014/8/23.
 */
public class DefaultPushRule implements PushRule {

    @Override
	public boolean apply(AtmosphereResource resource) {
        String pathInfo = resource.getRequest().getPathInfo();
        String servletPath = resource.getRequest().getServletPath();
        AtmosphereRequest r = resource.getRequest();
        String uri = r.getRequestURI();
        if (pathInfo == null) {
            int i = uri.indexOf(servletPath);
            pathInfo = uri.substring(i+servletPath.length(),uri.length());
        }


        if(pathInfo == null){
            resource.setBroadcaster(BroadcasterFactory.getDefault().lookup("/*"));
            return true;
        }
        final Broadcaster b = BroadcasterFactory.getDefault().lookup(pathInfo, true);
        resource.setBroadcaster(b);
        return true;
    }
}
