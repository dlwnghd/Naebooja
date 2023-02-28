package com.lec.spring.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class QryStatisticList extends QryResult {

    @JsonProperty("data")  // JSON 변환시 "data" 란 이름의 property 로 변환됨 // 나중에 안헷갈리게 comment_data 이런식으로 해도 될 듯?
    List<Transaction> list;
    Date date;
}
