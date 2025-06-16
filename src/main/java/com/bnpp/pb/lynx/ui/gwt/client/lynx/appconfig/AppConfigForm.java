package com.bnpp.pb.lynx.ui.gwt.client.lynx.appconfig;

import com.bnpp.pb.common.coreui.gwt.client.gxt3.ui.form.BaseForm;
import com.bnpp.pb.common.coreui.gwt.client.gxt3.ui.form.FormFieldsConfig;

public class AppConfigForm extends BaseForm {
    public AppConfigForm() {
        super(new AppConfigFormFields());
    }

    @Override
    public FormFieldsConfig getFormFieldsConfig() {
        return (AppConfigFormFields) super.getFormFieldsConfig();
    }
} 