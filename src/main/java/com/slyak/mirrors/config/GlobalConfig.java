package com.slyak.mirrors.config;

import com.google.common.collect.Maps;
import com.slyak.file.FileStoreService;
import com.slyak.file.LocalFileStoreService;
import com.slyak.mirrors.domain.GlobalFile;
import com.slyak.web.support.freemarker.bootstrap.Fileinput;
import com.slyak.web.support.freemarker.bootstrap.InitialPreviewConfigConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * .
 *
 * @author stormning 2018/5/9
 * @since 1.3.0
 */
@Configuration
public class GlobalConfig {

    @Bean
    public FileStoreService<String> fileStoreService() {
        return new LocalFileStoreService();
    }

    @Bean
    public InitialPreviewConfigConverter<GlobalFile> previewConfigConverter() {
        return new InitialPreviewConfigConverter<GlobalFile>() {
            @Override
            protected Fileinput convertOne(GlobalFile globalFile) {
                Fileinput fileinput = new Fileinput();
                fileinput.setSize(globalFile.getSize());
                fileinput.setCaption(globalFile.getName());
                fileinput.setKey(globalFile.getId());
                HashMap<String, String> extra = Maps.newHashMap();
                extra.put("id", globalFile.getId());
                fileinput.setExtra(extra);
                return fileinput;
            }
        };
    }
}
