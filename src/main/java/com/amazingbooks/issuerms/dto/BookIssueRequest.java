package com.amazingbooks.issuerms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookIssueRequest {
    private String isbn;
    private String cuspId;
    private int noOfCopies;
}
