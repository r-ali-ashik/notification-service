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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "template_id")
    private Integer templateId;

    @Column(name = "variables", length = 500)
    private String variables;

    @Column(name = "template", length = 1000)
    private String template;


}
