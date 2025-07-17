package com.javi.martin.MyMTGLibrary.repository;

import com.javi.martin.MyMTGLibrary.dto.MTGCardDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CardLibraryRepository extends MongoRepository<MTGCardDTO, String> {

    List<MTGCardDTO> findAll();
    MTGCardDTO findItemById(String id);
    MTGCardDTO findItemByName(String name);
}
