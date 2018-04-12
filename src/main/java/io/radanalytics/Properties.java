package io.radanalytics;

import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;

/**
 * Properties class
 */
@Component
public class Properties {

    @Value("${sparkpi.jarfile}")
    private String jarFile;

    public String getJarFile() {
        return jarFile;
    }

}