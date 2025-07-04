package com.javi.martin.MyMTGLibrary.service;

import com.javi.martin.MyMTGLibrary.repository.CardLibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CardDBSearchService {

    @Autowired
    private CardLibraryRepository repository;

    public boolean isCardOnDb(String cardName) {
        var card = repository.findItemByName(cardName);
        return Objects.nonNull(card);
    }
}
