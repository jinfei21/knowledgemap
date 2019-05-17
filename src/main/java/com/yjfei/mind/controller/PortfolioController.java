package com.yjfei.mind.controller;


import com.yjfei.mind.common.Result;
import com.yjfei.mind.data.AddPortfolio;
import com.yjfei.mind.data.Portfolio;
import com.yjfei.mind.data.PortfolioToProject;
import com.yjfei.mind.entity.ref.Relate;
import com.yjfei.mind.service.PortfolioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/portfolio")
@Slf4j
public class PortfolioController {

    @Autowired
    private PortfolioService portfolioService;

    @PostMapping("/addPortfolio")
    public Result<Portfolio> addPortfolio(@RequestBody AddPortfolio addPortfolio){

        Result<Portfolio> result = new Result<Portfolio>(true);


        try{

            Portfolio portfolio = portfolioService.addPortfolio(addPortfolio);

            result.setData(portfolio);
        }catch (Throwable t){
            log.error("add portfolio error,{}",t.getMessage());
            t.printStackTrace();
            result.setSuccess(false);
            result.setMessage(t.getMessage());
        }

        return result;
    }


    @PostMapping("/batchAdd")
    public Result<List<Portfolio>> addPortfolio(@RequestBody List<AddPortfolio> addPortfolioList){

        Result<List<Portfolio>> result = new Result<List<Portfolio>>(true);


        try{

            List<Portfolio> portfolioList = new ArrayList<>();

            for (AddPortfolio addPortfolio:addPortfolioList) {
                Portfolio portfolio = portfolioService.addPortfolio(addPortfolio);
                portfolioList.add(portfolio);
            }

            result.setData(portfolioList);
        }catch (Throwable t){
            log.error("add portfolio error,{}",t.getMessage());
            t.printStackTrace();
            result.setSuccess(false);
            result.setMessage(t.getMessage());
        }

        return result;
    }



    @DeleteMapping("/delPortfolioByPid/{id}")
    public Result<String> delPortfolio(@PathVariable("id") Long id){


        Result<String> result = new Result<String>(true);


        try{

            portfolioService.delPortfolioByPid(id);
            result.setData("OK");
        }catch (Throwable t){
            log.error("delete portfolio error,{}",t.getMessage());
            result.setSuccess(false);
            result.setMessage(t.getMessage());
        }

        return result;


    }


    @GetMapping("/getPortfolioByPid/{id}")
    public Result<Portfolio> getPortfolio(@PathVariable("id") Long id){
        Result<Portfolio> result = new Result<Portfolio>(true);


        try{

            Portfolio portfolio = portfolioService.findPortfolioByPid(id);

            result.setData(portfolio);
        }catch (Throwable t){
            log.error("get portfolio error,{}",t.getMessage());
            result.setSuccess(false);
            result.setMessage(t.getMessage());
        }

        return result;
    }


    @PostMapping("/addPortfolioToProject")
    public Result<Relate> addPortfolioToProject(@RequestBody PortfolioToProject portfolioToProject) {


        Result<Relate> result = new Result<Relate>(true);


        try{

            for(int i=1;i<=19;i++) {

                portfolioToProject.setPid((long) i);
                portfolioToProject.setEid((long) i);

                Relate relate = portfolioService.addPortfolioToProject(portfolioToProject);

                result.setData(relate);
            }
        }catch (Throwable t){
            log.error("add relation for porfolio and project error,{}",t.getMessage());
            result.setSuccess(false);
            result.setMessage(t.getMessage());
        }

        return result;
    }


}
