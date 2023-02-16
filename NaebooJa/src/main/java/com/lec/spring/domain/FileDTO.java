package com.lec.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileDTO {

    private Long id;
    private Long write_id;  // 어느글의 첨부파일? (FK)

    private String source;  // 원본 파일명
    private String file;  // 저장된 파일명(rename 된 파일명)

    private boolean isImage;    // 이미지 여부
}
