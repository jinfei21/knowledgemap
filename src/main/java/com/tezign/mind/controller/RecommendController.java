package com.tezign.mind.controller;


import com.tezign.mind.common.Result;
import com.tezign.mind.data.Portfolio;
import com.tezign.mind.data.Supplier;
import com.tezign.mind.entity.BaseNode;
import com.tezign.mind.service.RecommendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recommend")
@Slf4j
public class RecommendController {

    @Autowired
    private RecommendService recommendService;


    @GetMapping("/getPortfolioByProjectId/{pid}")
    public Result<List<Portfolio>> getPortfolioByPid(@PathVariable("pid") Long pid){

        Result<List<Portfolio>> result = new Result<List<Portfolio>>();
        try{

            List<Portfolio> portfolioList = recommendService.recommendPortfolioByProject(pid);
            result.setData(portfolioList);

        }catch (Throwable t){
            log.error("recommend portfolio by project Id error,{}",t.getMessage());
            t.printStackTrace();
            result.setSuccess(false);
            result.setMessage(t.getMessage());
        }



        return result;
    }


    @GetMapping("/getSupplierByProjectId/{pid}")
    public Result<List<Supplier>> getSupplierByPid(@PathVariable("pid") Long pid){


        Result<List<Supplier>> result = new Result<List<Supplier>>();
        try{

            List<Supplier> supplierList = recommendService.recommendSupplierByProject(pid);

            result.setData(supplierList);
        }catch (Throwable t){
            log.error("recommend supplier by project Id error,{}",t.getMessage());
            t.printStackTrace();
            result.setSuccess(false);
            result.setMessage(t.getMessage());
        }



        return result;

    }


    @GetMapping("/getSupplierByPortfolioId/{pid}")
    public Result<List<Supplier>> getSupplierByEid(@PathVariable("pid") Long pid){


        Result<List<Supplier>> result = new Result<List<Supplier>>();
        try{

            List<Supplier> supplierList = recommendService.recommendSupplierByPortfolio(pid);

            result.setData(supplierList);
        }catch (Throwable t){
            log.error("recommend supplier by portfolio Id error,{}",t.getMessage());
            t.printStackTrace();
            result.setSuccess(false);
            result.setMessage(t.getMessage());
        }



        return result;

    }

    @GetMapping("/getPortfolioTagByProjectId/{pid}")
    public Result<List<BaseNode>> getPortfolioTagByPid(@PathVariable("pid") Long pid){

        Result<List<BaseNode>> result = new Result<List<BaseNode>>();
        try{

            List<BaseNode> portfolioList = recommendService.getPortfolioTagByProjectId(pid);
            result.setData(portfolioList);

        }catch (Throwable t){
            log.error("recommend portfolio tag by project Id error,{}",t.getMessage());
            t.printStackTrace();
            result.setSuccess(false);
            result.setMessage(t.getMessage());
        }



        return result;
    }

}
