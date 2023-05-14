package com.ryduk.investment_system.dto;

import lombok.*;

import java.util.Iterator;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class EntityPageDto<E> implements Iterable<E>{
    private long totalElements;
    private int totalPages;
    private int page;
    private int pageSize;
    private List<E> elements;

    @Override
    public Iterator<E> iterator() {
        return elements.iterator();
    }
}
