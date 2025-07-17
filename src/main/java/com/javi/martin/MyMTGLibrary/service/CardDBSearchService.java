package com.javi.martin.MyMTGLibrary.service;

import com.javi.martin.MyMTGLibrary.dto.MTGCardDTO;
import com.javi.martin.MyMTGLibrary.repository.CardLibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardDBSearchService {

    @Autowired
    private CardLibraryRepository repository;

    public List<MTGCardDTO> getAllCards() {
        return repository.findAll();
    }

    public MTGCardDTO getCardById(String id) {
        return repository.findItemById(id);
    }

    public MTGCardDTO getCardByName(String cardName) {
        return repository.findItemByName(cardName);
    }
}
