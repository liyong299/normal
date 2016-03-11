package org.topteam.push;

import org.atmosphere.cpr.AtmosphereRequest;
import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.cpr.MetaBroadcaster;
import org.atmosphere.handler.AbstractReflectorAtmosphereHandler;

import java.io.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by 枫 on 2014/8/23.
 */
public class TopTeamAtmosphereHandler extends AbstractReflectorAtmosphereHandler {

    private final Logger logger = Logger.getLogger(TopTeamAtmosphereHandler.class.getName());

    private final List<PushRule> rules;

    public TopTeamAtmosphereHandler(List<PushRule> rules) {
        this.rules = rules;
    }

    @Override
	public void onRequest(AtmosphereResource resource) throws IOException {
        AtmosphereRequest r = resource.getRequest();
        // We only handle GET. POST are supported by PrimeFaces directly via the Broadcaster.
        if (r.getMethod().equalsIgnoreCase("GET")) {
            applyRules(resource);
        } else {
            StringBuilder stringBuilder = read(resource);
            MetaBroadcaster.getDefault().broadcastTo("/*", stringBuilder.toString());
        }
    }

    protected void applyRules(AtmosphereResource resource) {
        boolean ok;
        for (PushRule r : rules) {
            ok = r.apply(resource);
            if (!ok) return;
        }
    }

    public StringBuilder read(AtmosphereResource r) {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            try {
                InputStream inputStream = r.getRequest().getInputStream();
                if (inputStream != null) {
                    bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                }
            } catch (IllegalStateException ex) {
                logger.log(Level.FINE, "", ex);
                Reader reader = r.getRequest().getReader();
                if (reader != null) {
                    bufferedReader = new BufferedReader(reader);
                }
            }

            if (bufferedReader != null) {
                char[] charBuffer = new char[8192];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException ex) {
            logger.log(Level.WARNING, "", ex);
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    logger.log(Level.WARNING, "", ex);
                }
            }
        }
        return stringBuilder;
    }
}
