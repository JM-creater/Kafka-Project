package com.example;

import com.example.entity.WikiMediaData;
import com.example.repository.WikiMediaDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaDatabaseConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaDatabaseConsumer.class);

    private WikiMediaDataRepository wikiMediaDataRepository;

    public KafkaDatabaseConsumer(WikiMediaDataRepository wikiMediaDataRepository) {
        this.wikiMediaDataRepository = wikiMediaDataRepository;
    }

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(String eventMessage) {
        LOGGER.info(String.format("Event message received -> %s", eventMessage));

        WikiMediaData wikiMediaData = new WikiMediaData();
        wikiMediaData.setWikiEventData(eventMessage);
        wikiMediaDataRepository.save(wikiMediaData);
    }
}
