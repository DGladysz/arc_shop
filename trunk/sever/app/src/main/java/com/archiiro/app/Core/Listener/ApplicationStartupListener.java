package com.archiiro.app.Core.Listener;

import com.archiiro.app.Core.Service.SetupDataService;
import com.archiiro.app.Core.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartupListener implements ApplicationListener<ContextRefreshedEvent>, InitializingBean {

    private static boolean eventFired = false;

    @Autowired
    private UserService userService;

    @Autowired
    private SetupDataService setupDataService;

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    private static final Logger logger = LoggerFactory.getLogger(ApplicationStartupListener.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (eventFired) {
            return;
        }

        logger.info("Application started.");

        eventFired = true;

        setupDataService.setUpData();
    }
}
