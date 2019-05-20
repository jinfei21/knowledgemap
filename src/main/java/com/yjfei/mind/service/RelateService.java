package com.yjfei.mind.service;

import com.yjfei.mind.entity.node.PortfolioEntity;
import com.yjfei.mind.entity.node.ProjectEntity;
import com.yjfei.mind.entity.node.SupplierEntity;
import com.yjfei.mind.entity.ref.Relate;
import com.yjfei.mind.repository.RelateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RelateService {

    @Autowired
    private RelateRepository relateRepository;



    public Relate addRelate(Relate relate){

        Relate oldRelate = null;


        if (relate.getStartNode() instanceof SupplierEntity && relate.getEndNode() instanceof PortfolioEntity){
            oldRelate = relateRepository.findSupplierToPortfolioRelate(((SupplierEntity) relate.getStartNode()).getSid(),((PortfolioEntity) relate.getEndNode()).getEid());
        }else if (relate.getStartNode() instanceof SupplierEntity && relate.getEndNode() instanceof ProjectEntity){
            oldRelate = relateRepository.findSupplierToProjectRelate(((SupplierEntity) relate.getStartNode()).getSid(),((ProjectEntity) relate.getEndNode()).getPid());
        }else if (relate.getStartNode() instanceof PortfolioEntity && relate.getEndNode() instanceof ProjectEntity){
            oldRelate = relateRepository.findPortfolioToProjectRelate(((PortfolioEntity) relate.getStartNode()).getEid(),((ProjectEntity) relate.getEndNode()).getPid());
        }


        if(oldRelate == null){
            oldRelate = relateRepository.save(relate);
        }

        return oldRelate;
    }
}
