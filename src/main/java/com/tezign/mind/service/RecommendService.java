package com.tezign.mind.service;

import com.tezign.mind.data.Portfolio;
import com.tezign.mind.data.Supplier;
import com.tezign.mind.entity.BaseNode;
import com.tezign.mind.entity.node.PortfolioEntity;
import com.tezign.mind.entity.node.SupplierEntity;
import com.tezign.mind.repository.SupplierRepository;
import com.tezign.mind.util.ConvertUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class RecommendService {

    @Autowired
    private SupplierRepository supplierRepository;


    public List<Portfolio> recommendPortfolioByProject(Long pid){


        List<PortfolioEntity> portfolioEntities = supplierRepository.findSimilarPortfolioByPid(pid);


        List<Portfolio> portfolioList = ConvertUtil.convert(portfolioEntities,(portfolioEntity) -> {

            Portfolio portfolio = ConvertUtil.convert(portfolioEntity,Portfolio.class);
            return portfolio;

        });

        return  portfolioList;
    }

    public List<Supplier> recommendSupplierByProject(Long pid){


        List<SupplierEntity> supplierEntities = supplierRepository.findRightSupplierByPid(pid);

        List<Supplier> supplierList = ConvertUtil.convert(supplierEntities ,(supplierEntity) -> {
            Supplier supplier = ConvertUtil.convert(supplierEntity,Supplier.class);
            return  supplier;
        });

        return supplierList;
    }


    public List<Supplier> recommendSupplierByPortfolio(Long pid){

        List<SupplierEntity> supplierEntities = supplierRepository.findRightSupplierByEid(pid);

        List<Supplier> supplierList = ConvertUtil.convert(supplierEntities,(supplierEntity) -> {
            Supplier supplier = ConvertUtil.convert(supplierEntity,Supplier.class);
            return  supplier;
        });

        return supplierList;
    }


    public List<BaseNode> getPortfolioTagByProjectId(Long projectId){

        return supplierRepository.findPortfolioTagByPid(projectId);
    }
}
