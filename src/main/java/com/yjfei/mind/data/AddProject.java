package com.yjfei.mind.data;

import lombok.Data;

import java.util.List;

@Data
public class AddProject {

    private Long id;  //节点ID

    private Long pid;  //项目ID

    private String name;  //项目名称

    private String budget;

    private List<String> medium;

    private String company;

    private List<String> domain;

    private List<String> portraint;

    private List<String> purpose;

}
