package com.project.toystore.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class ObjectReponse {
    private Object data;
    private Map<String, String> errorMassage = null;
    private String status;

    @Override
    public String toString() {
        return "ObjectReponse{" +
                "status='" + status + '\'' +
                '}';
    }
}
