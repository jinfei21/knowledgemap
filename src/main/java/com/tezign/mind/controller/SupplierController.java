package com.tezign.mind.controller;


import com.tezign.mind.common.Result;
import com.tezign.mind.data.AddSupplier;
import com.tezign.mind.data.Supplier;
import com.tezign.mind.data.SupplierToPortfolio;
import com.tezign.mind.data.SupplierToProject;
import com.tezign.mind.entity.ref.Relate;
import com.tezign.mind.service.SupplierService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/supplier")
@Slf4j
public class SupplierController {


    @Autowired
    private SupplierService supplierService;



    @PostMapping("/addSupplier")
    public Result<Supplier> addSupplier(@RequestBody AddSupplier addSupplier){

        Result<Supplier> result = new Result<Supplier>(true);


        try{

            Supplier supplier = supplierService.addSupplier(addSupplier);

            result.setData(supplier);
        }catch (Throwable t){
            log.error("add supplier error,{}",t.getMessage());
            t.printStackTrace();
            result.setSuccess(false);
            result.setMessage(t.getMessage());
        }

        return result;
    }

    @PostMapping("/addBatch")
    public Result<List<Supplier>> addSupplier(@RequestBody List<AddSupplier> addSupplierList){

        Result<List<Supplier>> result = new Result<List<Supplier>>(true);


        try{

            List<Supplier> supplierList = new ArrayList<>();

            for (AddSupplier addSupplier:addSupplierList) {
                Supplier supplier = supplierService.addSupplier(addSupplier);

                supplierList.add(supplier);
            }

            result.setData(supplierList);
        }catch (Throwable t){
            log.error("add supplier error,{}",t.getMessage());
            t.printStackTrace();
            result.setSuccess(false);
            result.setMessage(t.getMessage());
        }

        return result;
    }


    @DeleteMapping("/delSupplierBySid/{id}")
    public Result<String> delSupllier(@PathVariable("id") Long id){


        Result<String> result = new Result<String>(true);


        try{

            supplierService.delSupplierBySid(id);
            result.setData("OK");
        }catch (Throwable t){
            log.error("delete supplier error,{}",t.getMessage());
            result.setSuccess(false);
            result.setMessage(t.getMessage());
        }

        return result;


    }

    @GetMapping("/getSupplierBySid/{id}")
    public Result<Supplier> getSupplier(@PathVariable("id") Long id){
        Result<Supplier> result = new Result<Supplier>(true);


        try{

            Supplier supplier = supplierService.findSupplierBySid(id);

            result.setData(supplier);
        }catch (Throwable t){
            log.error("get supplier error,{}",t.getMessage());
            result.setSuccess(false);
            result.setMessage(t.getMessage());
        }

        return result;
    }

    @PostMapping("/addSupplierToPortfolio")
    public Result<Relate> addSupplierToPortfolio(@RequestBody SupplierToPortfolio supplierToPortfolio){

        Result<Relate> result = new Result<Relate>(true);


        try{

            for(int i=1;i<=19;i++) {

                supplierToPortfolio.setEid((long) i);
                supplierToPortfolio.setSid((long)i);

                Relate relate = supplierService.addSupplierToPortfolio(supplierToPortfolio);

                result.setData(relate);

            }
        }catch (Throwable t){
            log.error("add relation for supplier and porfolio error,{}",t.getMessage());
            result.setSuccess(false);
            result.setMessage(t.getMessage());
        }

        return result;
    }



    @PostMapping("/addSupplierToProject")
    public Result<Relate> addSupplierToProject(@RequestBody SupplierToProject supplierToProject){

        Result<Relate> result = new Result<Relate>(true);


        try{
            for(int i=1;i<=19;i++) {

                supplierToProject.setPid((long) i);
                supplierToProject.setSid((long) i);
                Relate relate = supplierService.addSupplierToProject(supplierToProject);
                result.setData(relate);
            }

        }catch (Throwable t){
            log.error("add relation for supplier and project error,{}",t.getMessage());
            result.setSuccess(false);
            result.setMessage(t.getMessage());
        }

        return result;
    }
}
