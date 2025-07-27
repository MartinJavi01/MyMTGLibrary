package com.javi.martin.MyMTGLibrary.service;

import com.javi.martin.MyMTGLibrary.dto.MTGCardDTO;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.javi.martin.MyMTGLibrary.constants.MyMTGLibraryConstants.TYPE_LINE_SEPARATOR;

@Service
public class CardFilterService {

    public List<MTGCardDTO> filterCardsByType(List<MTGCardDTO> cards, String type) {
        return cards.stream()
                .filter(card -> card.getTypeLine().split(TYPE_LINE_SEPARATOR)[0].contains(type))
                .toList();
    }

    public List<MTGCardDTO> filterCardsByCardSubType(List<MTGCardDTO> cards, String subType) {
        return cards.stream()
                .filter(card -> (card.getTypeLine().contains(TYPE_LINE_SEPARATOR) &&
                        card.getTypeLine().split(TYPE_LINE_SEPARATOR)[1].contains(subType)))
                .toList();
    }
}
