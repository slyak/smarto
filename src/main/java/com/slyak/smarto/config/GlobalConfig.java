package com.slyak.smarto.config;

import com.google.common.collect.Maps;
import com.slyak.file.FileStoreService;
import com.slyak.file.LocalFileStoreService;
import com.slyak.smarto.domain.GlobalFile;
import com.slyak.web.support.freemarker.FreemarkerTemplateRender;
import com.slyak.web.support.freemarker.bootstrap.Fileinput;
import com.slyak.web.support.freemarker.bootstrap.InitialPreviewConfigConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.PrintWriter;
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

    @Bean("shellScriptTemplateRender")
    public FreemarkerTemplateRender shellScriptTemplateRender() {
        freemarker.template.Configuration cfg = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_0);
        cfg.setTemplateExceptionHandler((te, env, out) -> {
            if (!env.isInAttemptBlock()) {
                PrintWriter pw = (out instanceof PrintWriter) ? (PrintWriter) out : new PrintWriter(out);
                pw.print("${" + te.getBlamedExpressionString() + "}");
                pw.flush();  // To commit the HTTP response
            }
        });
        return new FreemarkerTemplateRender(cfg);
    }
}
