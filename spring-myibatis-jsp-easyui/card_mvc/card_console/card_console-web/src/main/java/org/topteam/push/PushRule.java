package org.topteam.push;

import org.atmosphere.cpr.AtmosphereResource;

/**
 * Created by 枫 on 2014/8/23.
 */
public interface PushRule {

    boolean apply(AtmosphereResource resource);
}
