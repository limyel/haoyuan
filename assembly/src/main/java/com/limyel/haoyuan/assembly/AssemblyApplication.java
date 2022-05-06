package com.limyel.haoyuan.assembly;

import com.limyel.haoyuan.admin.HaoyuanAdminApplication;
import com.limyel.haoyuan.portal.HaoyuanPortalApplication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
@SpringBootApplication
public class AssemblyApplication {

    public static void main(String[] args) {
        final ConfigurableApplicationContext commonContext = new SpringApplicationBuilder(AssemblyApplication.class)
                .web(WebApplicationType.NONE).run(args);
        log.info(commonContext.getId() + " has activated: " + commonContext.isActive());
        log.info(commonContext.getId() + " env: " + commonContext.getEnvironment());

        // portal
        if (commonContext.getEnvironment().containsProperty("portal")) {
            final ConfigurableApplicationContext context =
                    new SpringApplicationBuilder(HaoyuanPortalApplication.class)
                            .parent(commonContext)
                            .properties("spring.application.name=haoyuan-portal")
                            .profiles("portal")
                            .run(args);
            log.info(context.getId() + " has activated: " + context.isActive());
            log.info(commonContext.getId() + " env: " + context.getEnvironment());
        }

        // admin
        if (commonContext.getEnvironment().containsProperty("admin")) {
            final ConfigurableApplicationContext context =
                    new SpringApplicationBuilder(HaoyuanAdminApplication.class)
                            .parent(commonContext)
                            .properties("spring.application.name=haoyuan-admin")
                            .profiles("admin")
                            .run(args);
            log.info(context.getId() + " has activated: " + context.isActive());
            log.info(commonContext.getId() + " env: " + context.getEnvironment());
        }

    }

}
