package com.yjfei.mind.service;

import com.yjfei.mind.data.AddProject;
import com.yjfei.mind.data.Project;
import com.yjfei.mind.entity.node.ProjectEntity;
import com.yjfei.mind.entity.node.project.*;
import com.yjfei.mind.entity.ref.Relate;
import com.tezign.mind.repository.*;
import com.yjfei.mind.util.ConvertUtil;
import com.yjfei.mind.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;


    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    private ByRepository byRepository;

    @Autowired
    private GetRepository getRepository;

    @Autowired
    private ToRepository toRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private DomainRepository domainRepository;


    @Autowired
    private RelateService relateService;


    @Transactional
    public Project addProject(AddProject addProject){



        ProjectEntity projectEntity = projectRepository.findByPid(addProject.getPid());

        if(projectEntity != null){
            throw  new RuntimeException("project has existed.");
        }


        projectEntity = ConvertUtil.convert(addProject,ProjectEntity.class);


        projectEntity = projectRepository.addProject(projectEntity);


        BudgetTag budgetTag = ConvertUtil.convert(addProject,BudgetTag.class);
        budgetTag = budgetRepository.addBudget(budgetTag);
        Relate projectToBudget = new Relate(projectEntity,"ref",budgetTag);
        relateService.addRelate(projectToBudget);

        CompanyTag companyTag = ConvertUtil.convert(addProject,CompanyTag.class);
        companyTag = companyRepository.addCompany(companyTag);
        Relate projectToCompany = new Relate(projectEntity,"ref",companyTag);
        relateService.addRelate(projectToCompany);

        if(!CollectionUtils.isEmpty(addProject.getMedium())){
            for(String medium:addProject.getMedium()){
                ByTag byTag = new ByTag(medium);
                byTag = byRepository.addBy(byTag);
                Relate projectToBy = new Relate(projectEntity,"ref",byTag);
                relateService.addRelate(projectToBy);
            }
        }

        if(!CollectionUtils.isEmpty(addProject.getDomain())){
            for(String domain:addProject.getDomain()){
                DomainTag domainTag = new DomainTag(domain);
                domainTag = domainRepository.addDomain(domainTag);
                Relate projectToDomain = new Relate(projectEntity,"ref",domainTag);
                relateService.addRelate(projectToDomain);
            }
        }

        if(!CollectionUtils.isEmpty(addProject.getPortraint())){


            for(String portraint:addProject.getPortraint()){
                GetTag getTag = new GetTag(portraint);
                getTag = getRepository.addGet(getTag);
                Relate projectToGet = new Relate(projectEntity,"ref",getTag);
                relateService.addRelate(projectToGet);

            }
        }


        if(!CollectionUtils.isEmpty(addProject.getPurpose())){
            for(String purpose:addProject.getPurpose()) {
                ToTag toTag = new ToTag(purpose);
                toTag = toRepository.addTo(toTag);
                Relate projectToTo = new Relate(projectEntity, "ref", toTag);
                relateService.addRelate(projectToTo);
            }
        }


        Project project = ConvertUtil.convert(projectEntity,Project.class);

        return project;

    }


    public void delProjectByPid(Long id){
        projectRepository.deleteByPid(id);
    }


    public Project findProjectByPid(Long pid){

        ProjectEntity projectEntity = projectRepository.findByPid(pid);

        Project project = null;

        if(projectEntity != null){
             project = ConvertUtil.convert(projectEntity,Project.class);
        }
        return project;
    }


}
