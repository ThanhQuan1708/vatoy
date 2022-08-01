package com.project.toystore.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchOrderObj {
    private String status;
    private String from;
    private String to;
    public SearchOrderObj(){
        status="";
        from="";
        to="";
    }

    @Override
    public String toString() {
        return "SearchOrderObj{" +
                "status='" + status + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                '}';
    }
}


