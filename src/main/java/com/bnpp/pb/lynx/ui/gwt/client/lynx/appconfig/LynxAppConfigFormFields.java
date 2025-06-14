package com.bnpp.pb.lynx.ui.gwt.client.lynx.appconfig;

import com.bnpp.pb.common.coreui.gwt.client.gxt3.base.BaseFormFieldConfig;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;

public class LynxAppConfigFormFields extends BaseFormFieldConfig {
    private FieldLabel appIdField;
    private FieldLabel propTypeField;
    private FieldLabel propNameField;
    private FieldLabel propValueField;

    public LynxAppConfigFormFields() {
        setFieldNames(LynxAppConfigFieldsDef.formFieldNames);
        appIdField = new FieldLabel(new TextField(), "App Id");
        propTypeField = new FieldLabel(new TextField(), "Property Type");
        propNameField = new FieldLabel(new TextField(), "Property Name");
        propValueField = new FieldLabel(new TextField(), "Property Value");
    }

    @Override
    public Widget[] createFields() {
        return new Widget[] {
            appIdField,
            propTypeField,
            propNameField,
            propValueField,
            getField("active"),
            getField("env"),
            getField("create_date"),
            getField("created_by"),
            getField("last_modified"),
            getField("last_modified_user_id"),
            getField("version"),
            getField("partition_key")
        };
    }

    public TextField getAppIdField() {
        return (TextField) appIdField.getWidget();
    }
    public TextField getPropTypeField() {
        return (TextField) propTypeField.getWidget();
    }
    public TextField getPropNameField() {
        return (TextField) propNameField.getWidget();
    }
    public TextField getPropValueField() {
        return (TextField) propValueField.getWidget();
    }
} 