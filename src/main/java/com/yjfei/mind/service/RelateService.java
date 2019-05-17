package com.yjfei.mind.service;

import com.yjfei.mind.entity.ref.Relate;
import com.yjfei.mind.repository.RelateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RelateService {

    @Autowired
    private RelateRepository relateRepository;



    public Relate addRelate(Relate relate){

//        Relate oldRelate = relateRepository.findRelate(relate.getStartNode().getId(),relate.getEndNode().getId());
//
//
//        if(oldRelate == null){
//            oldRelate = relateRepository.addRelate(relate);
//        }
//
//        return oldRelate;

         return  relateRepository.save(relate);
    }
}
