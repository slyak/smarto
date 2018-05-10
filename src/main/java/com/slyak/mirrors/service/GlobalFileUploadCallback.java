package com.slyak.mirrors.service;

import com.slyak.file.FileStoreService;
import com.slyak.file.LocalFileStoreService;
import com.slyak.mirrors.domain.GlobalFile;
import com.slyak.mirrors.repository.GlobalFileRepository;
import com.slyak.web.support.file.FileUploadCallback;
import com.slyak.web.support.freemarker.bootstrap.Fileinput;
import com.slyak.web.support.freemarker.bootstrap.InitialPreviewConfigConverter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;

/**
 * .
 *
 * @author stormning 2018/5/8
 * @since 1.3.0
 */
@Service
@Slf4j
public class GlobalFileUploadCallback implements FileUploadCallback<GlobalFile, List<Fileinput>, String> {

    @Autowired
    private GlobalFileRepository globalFileRepository;

    @Autowired
    private InitialPreviewConfigConverter<GlobalFile> converter;

    private FileStoreService<String> fileStoreService = new LocalFileStoreService();


    @Override
    @SneakyThrows
    public GlobalFile saveMFile(MultipartFile multipartFile) {
        GlobalFile globalFile = new GlobalFile();
        String filename = multipartFile.getOriginalFilename();
        globalFile.setName(filename);
        globalFile.setSize(multipartFile.getSize());
        globalFile.setId(fileStoreService.store(multipartFile.getInputStream(), filename));
        return globalFileRepository.save(globalFile);
    }

    @Override
    @Transactional
    public boolean deleteFile(String id) {
        try {
            globalFileRepository.delete(id);
            fileStoreService.removeFile(id);
            return true;
        } catch (Exception e) {
            log.error("Delete file failed {}", e);
            return false;
        }
    }

    @Override
    public ResponseEntity<List<Fileinput>> createResponseEntity(List<GlobalFile> list) {
        if (CollectionUtils.isEmpty(list)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(converter.convert(list));
    }
}
