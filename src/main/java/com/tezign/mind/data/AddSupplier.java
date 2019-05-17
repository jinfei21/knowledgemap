package com.tezign.mind.data;


import lombok.Data;

import java.util.List;

@Data
public class AddSupplier {

    private Long id;

    private Long sid;

    private String name;

    private String gender;

    private String scale;

    private  List<String>  category;

    private List<String> expertAt;

    private List<String> style;


}
