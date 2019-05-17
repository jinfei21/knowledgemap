package com.tezign.mind.service;

import com.tezign.mind.data.Portfolio;
import com.tezign.mind.data.PortfolioToProject;
import com.tezign.mind.data.AddPortfolio;
import com.tezign.mind.entity.BaseNode;
import com.tezign.mind.entity.node.PortfolioEntity;
import com.tezign.mind.entity.node.ProjectEntity;
import com.tezign.mind.entity.node.portfolio.ContentTag;
import com.tezign.mind.entity.node.portfolio.CreativeTag;
import com.tezign.mind.entity.node.portfolio.EmotionTag;
import com.tezign.mind.entity.node.portfolio.VisualTag;
import com.tezign.mind.entity.ref.Relate;
import com.tezign.mind.repository.*;
import com.tezign.mind.util.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service
public class PortfolioService {



    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private ProjectRepository projectRepository;


    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private CreativeRepository creativeRepository;

    @Autowired
    private EmotionRepository emotionRepository;

    @Autowired
    private VisualRepository visualRepository;


    @Autowired
    private RelateService relateService;


    @Transactional
    public Portfolio addPortfolio(AddPortfolio addPortfolio){



        PortfolioEntity portfolioEntity = portfolioRepository.findByEid(addPortfolio.getEid());

        if (portfolioEntity != null){
            throw new RuntimeException("example has existed.");
        }


        portfolioEntity = ConvertUtil.convert(addPortfolio, PortfolioEntity.class);

        portfolioEntity = portfolioRepository.addPortfolio(portfolioEntity);


        if(!CollectionUtils.isEmpty(addPortfolio.getContent())){

            for(String content:addPortfolio.getContent()){
                ContentTag contentTag = new ContentTag(content);
                contentTag = contentRepository.addContent(contentTag);
                Relate portfolioToContent = new Relate(portfolioEntity,"ref",contentTag);
                relateService.addRelate(portfolioToContent);
            }
        }

        if (!CollectionUtils.isEmpty(addPortfolio.getCreativeType())){

            for(String creative:addPortfolio.getCreativeType()){
                CreativeTag creativeTag = new CreativeTag(creative);
                creativeTag = creativeRepository.addCreative(creativeTag);
                Relate portfolioToCreative = new Relate(portfolioEntity,"ref",creativeTag);
                relateService.addRelate(portfolioToCreative);
            }
        }

        if (!CollectionUtils.isEmpty(addPortfolio.getEmotion())){

            for(String emotion:addPortfolio.getEmotion()){
                EmotionTag emotionTag = new EmotionTag(emotion);
                emotionTag = emotionRepository.addEmotion(emotionTag);
                Relate portfolioToEmotion = new Relate(portfolioEntity,"ref",emotionTag);
                relateService.addRelate(portfolioToEmotion);
            }
        }

        if(!CollectionUtils.isEmpty(addPortfolio.getVisualStyle())){

            for(String visual:addPortfolio.getVisualStyle()){
                VisualTag visualTag = new VisualTag(visual);
                visualTag = visualRepository.addVisual(visualTag);
                Relate portfolioToVisual = new Relate(portfolioEntity,"ref",visualTag);
                relateService.addRelate(portfolioToVisual);
            }
        }


        Portfolio portfolio = ConvertUtil.convert(portfolioEntity,Portfolio.class);

        return portfolio;


    }

    public void delPortfolioByPid(Long id){
        portfolioRepository.deleteByEid(id);
    }


    public Relate addPortfolioToProject(PortfolioToProject portfolioToProject){

        PortfolioEntity example = portfolioRepository.findByEid(portfolioToProject.getEid());

        ProjectEntity project = projectRepository.findByPid(portfolioToProject.getPid());

        if(example == null || project == null){
            throw new  RuntimeException("example or project is null");
        }

        BaseNode startNode = example;
        BaseNode endNode = project;


         Relate relate = new Relate(startNode,"belong",endNode);

        relate = relateService.addRelate(relate);

        return relate;
    }


    public Portfolio findPortfolioByPid(Long id){

        PortfolioEntity portfolioEntity = portfolioRepository.findByEid(id);

        Portfolio portfolio = null;

        if(portfolioEntity != null){
            portfolio = ConvertUtil.convert(portfolioEntity, Portfolio.class);
        }
        return portfolio;
    }


}
