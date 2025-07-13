package com.javi.martin.MyMTGLibrary.service;

import com.javi.martin.MyMTGLibrary.dto.MTGCardDTO;
import com.javi.martin.MyMTGLibrary.repository.CardLibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardDBSearchService {

    @Autowired
    private CardLibraryRepository repository;

    public MTGCardDTO getCardByName(String cardName) {
        return repository.findItemByName(cardName);
    }
}
