package com.dzvonik.ordermanager.model.dto;

import lombok.Data;

@Data
public class PatchOperation {

    private String op;
    private String path;
    private Object value;

}
