package com.javi.martin.MyMTGLibrary.service;

import com.javi.martin.MyMTGLibrary.dto.MTGCardDTO;
import com.javi.martin.MyMTGLibrary.repository.CardLibraryRepository;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardDBSaverService {

    @Autowired
    private CardLibraryRepository cardLibraryRepository;
    @Autowired
    private MongoOperations template;

    public CardDBSaverService(CardLibraryRepository cardLibraryRepository) {
        this.cardLibraryRepository = cardLibraryRepository;
        template = new MongoTemplate(new SimpleMongoClientDatabaseFactory(MongoClients.create(), "MyMTGLibrary"));
    }

    public void saveCard(MTGCardDTO cardDTO, int quantity) {
        Query nameQuery = new Query(Criteria.where("name").is(cardDTO.getName()));
        Optional.ofNullable(cardLibraryRepository.findItemByName(cardDTO.getName()))
                .ifPresentOrElse( card -> template.update(MTGCardDTO.class)
                .matching(nameQuery).apply(new Update().inc("quantity", quantity)).upsert(),
                        () -> template.insert(cardDTO));
    }
}
