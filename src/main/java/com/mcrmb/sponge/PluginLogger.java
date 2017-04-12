package com.mcrmb.sponge;

import com.google.inject.Inject;

import java.util.logging.Logger;

/**
 * Created by txgs888 on 2017/4/12.
 */
public class PluginLogger {
    @Inject
    private Logger logger;

    @Inject
    private void setLogger(Logger logger) {
        this.logger = logger;
    }

    public Logger getLogger() {
        return logger;
    }
}
