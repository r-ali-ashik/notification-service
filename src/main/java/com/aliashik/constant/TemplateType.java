package com.aliashik.constant;

public enum TemplateType {
    EMAIL(1),
    SMS(2),
    FAX(3);
    private Integer value;

    private TemplateType(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
