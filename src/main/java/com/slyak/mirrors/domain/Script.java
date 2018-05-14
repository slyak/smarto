package com.slyak.mirrors.domain;

import com.slyak.spring.jpa.hibernate.JSONType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.util.List;

/**
 * .
 *
 * @author stormning 2018/5/10
 * @since 1.3.0
 */
@Entity
@Table(name = "t_script")
public class Script extends AbstractPersistable<Long> {

    private String name;

    @Lob
    private String help;

    @Lob
    private String content;

    private String osName;

    @Type(type = JSONType.TYPE)
    private List<String> osVersions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHelp() {
        return help;
    }

    public void setHelp(String help) {
        this.help = help;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public List<String> getOsVersions() {
        return osVersions;
    }

    public void setOsVersions(List<String> osVersions) {
        this.osVersions = osVersions;
    }
}