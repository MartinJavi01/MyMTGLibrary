package com.javi.martin.MyMTGLibrary.controller;

import com.javi.martin.MyMTGLibrary.dto.MTGCardDTO;
import com.javi.martin.MyMTGLibrary.service.CardDBSearchService;
import com.javi.martin.MyMTGLibrary.service.CardFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;

import static com.javi.martin.MyMTGLibrary.constants.MyMTGLibraryConstants.*;

@Controller
@RequestMapping("/collection")
public class CollectionController {

    @Autowired
    private CardDBSearchService cardDBSearchService;
    @Autowired
    private CardFilterService filterService;

    @GetMapping(value = "/all")
    public List<MTGCardDTO> showAllCollection(Model model) {
        return cardDBSearchService.getAllCards();
    }
}
