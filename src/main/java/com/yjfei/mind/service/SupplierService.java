package com.yjfei.mind.service;

import com.yjfei.mind.data.AddSupplier;
import com.yjfei.mind.data.Supplier;
import com.yjfei.mind.data.SupplierToPortfolio;
import com.yjfei.mind.data.SupplierToProject;
import com.yjfei.mind.entity.BaseNode;
import com.yjfei.mind.entity.node.PortfolioEntity;
import com.yjfei.mind.entity.node.ProjectEntity;
import com.yjfei.mind.entity.node.SupplierEntity;
import com.yjfei.mind.entity.node.supplier.CategoryTag;
import com.yjfei.mind.entity.node.supplier.ExpertTag;
import com.yjfei.mind.entity.node.supplier.ScaleTag;
import com.yjfei.mind.entity.node.supplier.StyleTag;
import com.yjfei.mind.entity.ref.Relate;
import com.tezign.mind.repository.*;
import com.yjfei.mind.util.ConvertUtil;
import com.yjfei.mind.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;


    @Autowired
    private PortfolioRepository portfolioRepository;


    @Autowired
    private ProjectRepository projectRepository;


    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ExportRepository exportRepository;

    @Autowired
    private ScaleRepository scaleRepository;

    @Autowired
    private StyleRepository styleRepository;


    @Autowired
    private RelateService relateService;


    @Transactional
    public Supplier addSupplier(AddSupplier addSupplier){


        SupplierEntity supplierEntity = supplierRepository.findBySid(addSupplier.getSid());

        if (supplierEntity != null){
            throw new  RuntimeException("supplier has existed!");
        }


        supplierEntity = ConvertUtil.convert(addSupplier,SupplierEntity.class);

        supplierEntity = supplierRepository.addSupplier(supplierEntity);

        ScaleTag scaleTag = ConvertUtil.convert(addSupplier,ScaleTag.class);
        scaleTag = scaleRepository.addScale(scaleTag);
        Relate supplierToScale = new Relate(supplierEntity,"ref",scaleTag);
        relateService.addRelate(supplierToScale);


        if(!CollectionUtils.isEmpty(addSupplier.getCategory())){
            for(String category:addSupplier.getCategory()){
                CategoryTag categoryTag = new CategoryTag(category);
                categoryTag = categoryRepository.addCategory(categoryTag);
                Relate supplierToCategory = new Relate(supplierEntity,"ref",categoryTag);
                relateService.addRelate(supplierToCategory);
            }
        }

        if(!CollectionUtils.isEmpty(addSupplier.getExpertAt())){

            for(String expert:addSupplier.getExpertAt()){
                ExpertTag expertTag = new ExpertTag(expert);
                expertTag = exportRepository.addExpert(expertTag);
                Relate supplierToExpert = new Relate(supplierEntity,"ref",expertTag);
                relateService.addRelate(supplierToExpert);
            }
        }

        if (!CollectionUtils.isEmpty(addSupplier.getStyle())){

            for(String style:addSupplier.getStyle()) {
                StyleTag styleTag = new StyleTag(style);
                styleTag = styleRepository.addStyle(styleTag);
                Relate supplierToStyle = new Relate(supplierEntity, "ref", styleTag);
                relateService.addRelate(supplierToStyle);
            }
        }

        Supplier supplier = ConvertUtil.convert(supplierEntity,Supplier.class);

        return supplier;


    }


    public void delSupplierBySid(Long id){
        supplierRepository.deleteBySid(id);
    }


    public Relate addSupplierToPortfolio(SupplierToPortfolio supplierToPortfolio){
        SupplierEntity supplier = supplierRepository.findBySid(supplierToPortfolio.getSid());
        PortfolioEntity example = portfolioRepository.findByEid(supplierToPortfolio.getEid());



        if(supplier == null || example == null){
            throw new  RuntimeException("supplier or example is null");
        }

        BaseNode startNode = supplier;
        BaseNode endNode = example;


        Relate relate = new Relate(startNode,"design",endNode);
        relateService.addRelate(relate);

        return relate;
    }

    public Relate addSupplierToProject(SupplierToProject supplierToProject){
       SupplierEntity supplier = supplierRepository.findBySid(supplierToProject.getSid());

       ProjectEntity project = projectRepository.findByPid(supplierToProject.getPid());

        if(supplier == null || project == null){
            throw new  RuntimeException("supplier or project is null");
        }

        BaseNode startNode = supplier;
        BaseNode endNode = project;


        Relate relate = new Relate(startNode,"actor",endNode);
        relateService.addRelate(relate);
        return relate;
    }


    public Supplier findSupplierBySid(Long id){

        SupplierEntity supplierEntity = supplierRepository.findBySid(id);

        Supplier supplier = null;

        if(supplierEntity != null){
            supplier = ConvertUtil.convert(supplierEntity,Supplier.class);
        }
        return supplier;
    }

}
