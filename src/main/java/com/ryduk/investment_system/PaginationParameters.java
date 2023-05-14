package com.ryduk.investment_system;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PaginationParameters {
    private Integer page;
    private Integer pageSize;
    private String field;
    private String direction;

    public Integer getPage(){
        return (page == null || page <= 0) ? 1 : page;
    }

    public Integer getPageSize(){
        return 8;
    }

    public String getField(){
        return field == null ? "id" : field;
    }

    public String getDirection(){
        return (direction != null && direction.equalsIgnoreCase("desc")) ? "DESC" : "ASC";
    }
}
