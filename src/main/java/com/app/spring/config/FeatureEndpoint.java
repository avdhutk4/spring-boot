package com.app.spring.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Endpoint(id = "feature")
public class FeatureEndpoint {

    private final static Map<String,Feature> features =
            new ConcurrentHashMap<>();


    public FeatureEndpoint() {
        features.put("DEPARTMENT", new Feature(true));
        features.put("USER", new Feature(false));
        features.put("AUTH", new Feature(false));
    }

    @ReadOperation
    public Map<String,Feature> getAllFeatures() {
        return features;
    }

    @ReadOperation
    public Feature getFeature(@Selector String featureName){
        return features.get(featureName);
    }


    private static class Feature {

        private boolean isEnabled;

        public Feature(boolean isEnabled) {
            this.isEnabled = isEnabled;
        }

        public Feature() {
        }

        public boolean isEnabled() {
            return isEnabled;
        }

        public void setEnabled(boolean enabled) {
            isEnabled = enabled;
        }
    }

}
