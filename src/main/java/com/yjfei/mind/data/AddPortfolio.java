package com.yjfei.mind.data;


import lombok.Data;

import java.util.List;

@Data
public class AddPortfolio {

    private Long id;

    private Long eid;

    private String name;

    private List<String> content;

    private List<String> creativeType;

    private List<String> emotion;

    private List<String> visualStyle;


}
