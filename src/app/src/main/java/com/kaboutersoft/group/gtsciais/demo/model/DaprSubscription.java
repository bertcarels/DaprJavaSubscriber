package com.kaboutersoft.group.gtsciais.demo.model;

import lombok.Builder;
import lombok.Getter;
import java.util.Map;

@Builder
@Getter
public class DaprSubscription {
    private String pubSubName;
    private String topic;
    private String route;
    private Map<String, String> metadata;
}