package com.liyun.car.core.editor;

import java.beans.PropertyEditorSupport;

import com.liyun.car.common.utils.StringUtils;

public class StringEditor extends PropertyEditorSupport {


    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(StringUtils.KillEmpty(text));
    }

    @Override
    public String getAsText() {
        return StringUtils.KillEmpty(getValue().toString());
    }
}
