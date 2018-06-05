package com.slyak.smarto.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * .
 *
 * @author stormning 2018/4/27
 * @since 1.3.0
 */
@Embeddable
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectGroupHostKey implements Serializable {

    private Long projectGroupId;

    private Long hostId;
}