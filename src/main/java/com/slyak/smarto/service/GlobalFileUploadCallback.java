package com.slyak.smarto.service;

import com.google.common.hash.*;
import com.google.common.io.ByteSource;
import com.google.common.io.ByteStreams;
import com.google.common.io.Closer;
import com.slyak.file.FileStoreService;
import com.slyak.smarto.domain.GlobalFile;
import com.slyak.smarto.repository.GlobalFileRepository;
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

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

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

    @Autowired
    private FileStoreService<String> fileStoreService;


    @Override
    @SneakyThrows
    public GlobalFile saveMFile(MultipartFile multipartFile) {
        InputStream inputStream = multipartFile.getInputStream();
        InputStreamByteSource byteSource = new InputStreamByteSource(inputStream);
        try {
            String md5 = byteSource.hash(Hashing.md5()).toString();
            GlobalFile globalFile = globalFileRepository.findByMd5(md5);
            if (globalFile == null) {
                globalFile = new GlobalFile();
                String filename = multipartFile.getOriginalFilename();
                globalFile.setName(filename);
                globalFile.setSize(multipartFile.getSize());
                globalFile.setId(fileStoreService.store(inputStream, filename));
                return globalFileRepository.save(globalFile);
            }

            return globalFile;
        } finally {
            byteSource.getCloser().close();
        }
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

    class InputStreamByteSource extends ByteSource {

        private InputStream inputStream;

        private Closer closer;

        public InputStreamByteSource(InputStream inputStream) {
            this.inputStream = inputStream;
        }

        @Override
        public InputStream openStream() throws IOException {
            return inputStream;
        }

        @Override
        public HashCode hash(HashFunction hashFunction) throws IOException {
            Hasher hasher = hashFunction.newHasher();
            copyTo(Funnels.asOutputStream(hasher));
            return hasher.hash();
        }

        @Override
        public long copyTo(OutputStream output) throws IOException {
            checkNotNull(output);

            this.closer = Closer.create();
            try {
                InputStream in = closer.register(openStream());
                return ByteStreams.copy(in, output);
            } catch (Throwable e) {
                throw closer.rethrow(e);
            }
        }

        public Closer getCloser() {
            return closer;
        }
    }
}
