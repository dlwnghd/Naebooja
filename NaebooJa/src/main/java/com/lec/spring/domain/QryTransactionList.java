package com.lec.spring.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class QryTransactionList extends QryResult {
    @JsonProperty("data")
    List<Transaction> list;
}
