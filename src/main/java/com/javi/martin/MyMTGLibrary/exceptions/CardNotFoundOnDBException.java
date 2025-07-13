package com.javi.martin.MyMTGLibrary.exceptions;

import com.javi.martin.MyMTGLibrary.dto.MTGCardDTO;

public class CardNotFoundOnDBException extends RuntimeException {
    public CardNotFoundOnDBException(MTGCardDTO cardDTO) {
        super("The card with name: " + cardDTO.getName() + ". Was not found on the DB");
    }
}
