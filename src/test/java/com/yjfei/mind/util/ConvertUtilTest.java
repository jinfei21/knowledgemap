package com.yjfei.mind.util;

import com.yjfei.mind.data.Project;
import com.yjfei.mind.entity.node.ProjectEntity;

public class ConvertUtilTest {



    public static  void main(String args[]){


        Project project = new Project();

        project.setId(1L);

        project.setName("unidam");

        ProjectEntity projectEntity = ConvertUtil.convert(project,ProjectEntity.class);


        System.out.println("fsfasfsafsa");

    }
}
