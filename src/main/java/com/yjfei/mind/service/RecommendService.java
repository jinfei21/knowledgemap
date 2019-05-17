package com.yjfei.mind.service;

import com.yjfei.mind.data.Portfolio;
import com.yjfei.mind.data.Supplier;
import com.yjfei.mind.entity.BaseNode;
import com.yjfei.mind.entity.node.PortfolioEntity;
import com.yjfei.mind.entity.node.SupplierEntity;
import com.yjfei.mind.repository.SupplierRepository;
import com.yjfei.mind.util.ConvertUtil;
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


        List<PortfolioEntity> portfolioEntities = supplierRepository.searchSimilarPortfolioByPid(pid);


        List<Portfolio> portfolioList = ConvertUtil.convert(portfolioEntities,(portfolioEntity) -> {

            Portfolio portfolio = ConvertUtil.convert(portfolioEntity,Portfolio.class);
            return portfolio;

        });

        return  portfolioList;
    }

    public List<Supplier> recommendSupplierByProject(Long pid){


        List<SupplierEntity> supplierEntities = supplierRepository.searchSimiliarSupplierByPid(pid);

        List<Supplier> supplierList = ConvertUtil.convert(supplierEntities ,(supplierEntity) -> {
            Supplier supplier = ConvertUtil.convert(supplierEntity,Supplier.class);
            return  supplier;
        });

        return supplierList;
    }


    public List<Supplier> recommendSupplierByPortfolio(Long pid){

        List<SupplierEntity> supplierEntities = supplierRepository.searchSimiliarSupplierByEid(pid);

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
