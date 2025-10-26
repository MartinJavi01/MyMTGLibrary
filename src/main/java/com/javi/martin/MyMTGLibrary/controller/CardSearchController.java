package com.javi.martin.MyMTGLibrary.controller;

import com.javi.martin.MyMTGLibrary.dto.ApiSearchResponseDTO;
import com.javi.martin.MyMTGLibrary.dto.MTGCardDTO;
import com.javi.martin.MyMTGLibrary.service.CardApiSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/search")
@CrossOrigin(origins = "http://localhost:4200")
public class CardSearchController {

    @Autowired
    private CardApiSearchService searchApiService;

    @GetMapping("/name/{searchString}")
    public ApiSearchResponseDTO searchForCardByName(@PathVariable String searchString) {
        return searchApiService.searchCardByName(searchString);
    }

    @GetMapping("/id/{id}")
    public MTGCardDTO searchForCardById(@PathVariable String id) {
        return searchApiService.searchCardById(id);
    }
}
