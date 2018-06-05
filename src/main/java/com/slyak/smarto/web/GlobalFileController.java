package com.slyak.smarto.web;

import com.slyak.smarto.domain.GlobalFile;
import com.slyak.web.support.file.FileController;
import com.slyak.web.support.file.FileUploadCallback;
import com.slyak.web.support.freemarker.bootstrap.Fileinput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * .
 *
 * @author stormning 2018/5/8
 * @since 1.3.0
 */
@Controller
@RequestMapping("/file")
public class GlobalFileController extends FileController<GlobalFile, List<Fileinput>, String> {

    @Autowired
    public GlobalFileController(FileUploadCallback<GlobalFile, List<Fileinput>, String> callback) {
        super(callback);
    }
}
