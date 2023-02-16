package com.lec.spring.controller;
// 데이터를 response 하기 위해

import com.lec.spring.domain.FileDTO;
import com.lec.spring.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class FileController {

    @Value("${app.upload.path}")  // org.springframework.beans.factory.annotation.Value
    private String uploadDir;

    private FileService fileService;

    @Autowired
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }

    public FileController() {
        System.out.println(getClass().getName() + "() 생성");
    }


    // 파일 다운로드
    // id: 첨부파일의 id
    @RequestMapping("/board/download")
    public ResponseEntity<Object> download(Long id){

        if(id == null) return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);   // 400

        FileDTO file = fileService.findById(id);
        if(file == null) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);  // 404

        String sourceName = file.getSource();    // 원본 이름
        String fileName = file.getFile();        // 저장된 파일명

        String path = new File(uploadDir + File.separator + fileName).getAbsolutePath();

        try{
            // 파일 유형 (Mimetype) 추출
            String mimeType = Files.probeContentType(Paths.get(path));

            // 유형이 지정되지 않은 경우
            if(mimeType == null){
                mimeType = "application/octet-stream";   // 일련의 8bit 스트림 타입.  유형이 알려지지 않은 파일에 대한 형식 지정
            }

            Path filePath = Paths.get(path);
            Resource resource  // org.springframework.core.io.Resource;
                    = new InputStreamResource(Files.newInputStream(filePath));

            // response header 세팅
            HttpHeaders headers = new HttpHeaders();
            // ⬇️원본 파일 이름(sourceName)으로 다운로드 하게 하기위한 세팅
            headers.setContentDisposition(
                    ContentDisposition.builder("attachment").filename(URLEncoder.encode(sourceName, "utf-8")).build());
            headers.setCacheControl("no-cache");
            headers.setContentType(MediaType.parseMediaType(mimeType));

            // ResponseEntity 리턴 (body, header, status)
            return new ResponseEntity<>(resource, headers, HttpStatus.OK);  // 200
        } catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);  // 409
        }


    }


}
