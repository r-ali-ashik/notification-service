package com.aliashik.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "tbl_template")
@NoArgsConstructor
@Data
public class Template {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "template_id", length = 40)
    private String templateId;

    @Column(name = "variables", length = 1000)
    private String variables;
}
