package com.devking.fefilm.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieRequest {
    private String field;
    private String value;
    private String direction;
    private int page;
    private int itemsPerPage;
    private String orderByColumn;
}
