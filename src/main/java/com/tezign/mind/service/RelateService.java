package com.tezign.mind.service;

import com.tezign.mind.entity.ref.Relate;
import com.tezign.mind.repository.RelateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RelateService {

    @Autowired
    private RelateRepository relateRepository;



    public Relate addRelate(Relate relate){

        Relate oldRelate = relateRepository.findRelate(relate.getStartNode(),relate.getEndNode());


        if(oldRelate == null){
            oldRelate = relateRepository.addRelate(relate);
        }

        return oldRelate;
    }
}
