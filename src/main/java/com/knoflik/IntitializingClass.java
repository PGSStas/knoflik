package com.knoflik;

import com.knoflik.services.PackageParsingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class IntitializingClass {
    @Autowired
    private PackageParsingService packageParsingService;

    @EventListener(ApplicationReadyEvent.class)
    public void setDB() throws Exception {
        if (packageParsingService.getPackageCount() == 0) {
            packageParsingService.parsePackage("pack1.txt");
            packageParsingService.parsePackage("pack2.txt");
            packageParsingService.parsePackage("pack3.txt");
        }
    }
}
