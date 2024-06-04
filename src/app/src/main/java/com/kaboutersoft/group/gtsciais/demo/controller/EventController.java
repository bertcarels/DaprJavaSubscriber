package com.kaboutersoft.group.gtsciais.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import reactor.core.publisher.Mono;

import com.kaboutersoft.group.gtsciais.demo.model.Event;
import com.kaboutersoft.group.gtsciais.demo.model.DaprSubscription;

import com.kaboutersoft.group.gtsciais.demo.service.db.EventServiceDb;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

//import io.dapr.Topic;
import io.dapr.client.domain.CloudEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class EventController {

    private static final Logger logger = LoggerFactory.getLogger(EventController.class);

    @Value("${kab.dapr.pubsub.topic}")
    private String TOPIC_NAME;
    @Value("${kab.dapr.pubsub.name}")
    private String PUBSUB_NAME;

    @Value("${kab.dapr.pubsub.raw}")
    private String PUBSUB_RAW;

    @Value("${kab.dapr.pubsub.format}")
    private String PUBSUB_FORMAT;

    @Value("${kab.dapr.pubsub.route}")
    private String PUBSUB_ROUTE;


    private final EventServiceDb eventServiceDb;

    @Autowired
    public EventController(EventServiceDb eventServiceDb) {
        this.eventServiceDb = eventServiceDb;

    }

    /**
     * Register Dapr pub/sub subscriptions.
     *
     * @return DaprSubscription Object containing pubsub name, topic and route for
     *         subscription.
     */
    @GetMapping(path = "/dapr/subscribe", produces = MediaType.APPLICATION_JSON_VALUE)
    public DaprSubscription[] getSubscription() {
       
        logger.info("Dapr Pubsub name: " + PUBSUB_NAME);
        logger.info("Dapr Topic name: " + TOPIC_NAME);
        logger.info("Dapr Pubsub raw: " + PUBSUB_RAW);
        logger.info("Dapr Pubsub format: " + PUBSUB_FORMAT);
        logger.info("Route: " + PUBSUB_ROUTE);

        Map<String, String> metadata = new HashMap<>();

        
        if (!PUBSUB_FORMAT.equals("")) {
            metadata.put("valueSchemaType", PUBSUB_FORMAT);
        }
        if (!PUBSUB_RAW.equals("")) {
            metadata.put("rawPayload", "true");
        }
        
        logger.info("Metadata: {}", metadata);


        if (metadata.size()==0) {
            logger.info("Subscribing to Pubsubname no metatdata {}, topic {} for endpoint {}", PUBSUB_NAME, TOPIC_NAME,
            PUBSUB_ROUTE);
            DaprSubscription daprSubscription = DaprSubscription.builder()
                    .pubSubName(PUBSUB_NAME)
                    .topic(TOPIC_NAME)
                    .route(PUBSUB_ROUTE)
                    .build();
            logger.info("Subscribed to Pubsubname {}, topic {} for endpoint {}", PUBSUB_NAME, TOPIC_NAME, PUBSUB_ROUTE);
            DaprSubscription[] arr = new DaprSubscription[] { daprSubscription };
            return arr;
        } else {
            logger.info("Subscribing to Pubsubname with metatdata {}, topic {} for endpoint {}", PUBSUB_NAME,
                    TOPIC_NAME, PUBSUB_ROUTE);
            DaprSubscription daprSubscription = DaprSubscription.builder()
                    .pubSubName(PUBSUB_NAME)
                    .topic(TOPIC_NAME)
                    .route(PUBSUB_ROUTE)
                    .metadata(metadata)
                    .build();

            logger.info("Subscribed to Pubsubname {}, topic {} for endpoint {}", PUBSUB_NAME, TOPIC_NAME, PUBSUB_ROUTE);
            DaprSubscription[] arr = new DaprSubscription[] { daprSubscription };
            return arr;

        }

    }

    @PostMapping(path = "/events1", consumes = MediaType.ALL_VALUE)
    public Mono<ResponseEntity> createEvent1(@RequestBody(required = false) Object body) {

        String requestBodyAsString = (body != null) ? body.toString() : "No request body";
        // Log the request body
        logger.info("Received request body: " + requestBodyAsString);
        logger.info("Dapr Pubsub raw: " + PUBSUB_RAW);
        logger.info("Dapr Pubsub format: " + PUBSUB_FORMAT);

        return Mono.fromSupplier(() -> {
            try {

                logger.info("Processing RAW body: " + requestBodyAsString);
                logger.info("Processed RAW body: " + requestBodyAsString);

                return ResponseEntity.ok("SUCCESS");

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @PostMapping(path = "/events2", consumes = MediaType.ALL_VALUE)
    public Mono<ResponseEntity> createEvent2(@RequestBody(required = false) CloudEvent<Event> cloudEvent) {

        return Mono.fromSupplier(() -> {
            try {

                logger.info("Event received:" + cloudEvent.getData().getId());

                Event e = eventServiceDb.createEvent(cloudEvent.getData());
                logger.info("Event saved:" + e.getId());

                return ResponseEntity.ok("SUCCESS");

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        });
    }

    @PostMapping(path = "/events3", consumes = MediaType.ALL_VALUE)
    public Mono<ResponseEntity> createEvent3(@RequestBody(required = false) Event event) {

        return Mono.fromSupplier(() -> {
            try {

                logger.info("Event received:" + event.getId());

                Event e = eventServiceDb.createEvent(event);
                logger.info("Event saved:" + e.getId());

                return ResponseEntity.ok("SUCCESS");

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        });
    }


}
