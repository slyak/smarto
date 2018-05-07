package com.slyak.mirrors.domain;

import lombok.ToString;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * .
 *
 * @author stormning 2018/5/7
 * @since 1.3.0
 */
@Embeddable
@ToString
public class HostGroupScriptKey implements Serializable {

    private Long hostGroupId;

    private Long scriptId;


}
