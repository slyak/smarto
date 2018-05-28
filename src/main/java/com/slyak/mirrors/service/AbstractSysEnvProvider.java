package com.slyak.mirrors.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.collect.Lists;
import com.slyak.mirrors.dto.SysEnv;
import org.springframework.beans.BeanUtils;
import org.springframework.core.ResolvableType;

import java.util.List;

/**
 * .
 *
 * @author stormning 2018/5/25
 * @since 1.3.0
 */
public abstract class AbstractSysEnvProvider<T> implements SysEnvProvider<T> {

    private String structure;

    public AbstractSysEnvProvider() {
        ResolvableType type = ResolvableType.forClass(getClass()).as(SysEnvProvider.class);
        this.structure = JSON.toJSONString(loopCreate(type.getGeneric(0)),
                SerializerFeature.PrettyFormat,
                SerializerFeature.WriteNonStringKeyAsString,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteNullBooleanAsFalse,
                SerializerFeature.WriteNullNumberAsZero,
                SerializerFeature.WriteMapNullValue
        );
    }

    protected Object loopCreate(ResolvableType type) {
        Class<?> clazz = type.resolve();
        if (Iterable.class.isAssignableFrom(clazz) || type.isArray()) {
            List<Object> objects = Lists.newArrayList();
            objects.add(loopCreate(type.getGeneric(0)));
            return objects;
        } else {
            return BeanUtils.instantiate(clazz);
        }
    }

    @Override
    public SysEnv getMetadata() {
        SysEnv env = new SysEnv();
        env.setName(getName());
        env.setDescription(getDescription());
        env.setStructure(structure);
        return env;
    }

    protected abstract String getDescription();

    protected abstract String getName();
}
