package com.bnpp.pb.lynx.ui.gwt.client.lynx.appconfig;

import com.bnpp.pb.common.coreui.gwt.client.gxt3.base.BaseFormFieldConfig;
import com.bnpp.pb.common.coreui.gwt.client.gxt3.ui.grid.TableWindow;
import com.bnpp.pb.lynx.ui.gwt.client.AbstractLynxDataEntryForm;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.form.FormPanel;

public class newForm extends AbstractLynxDataEntryForm {
    private static final String BIZ_OBJECT = "lynx_app_config";
    private newFormFields formFields;
    private Widget[] fieldsToDisplay;
    private FormPanel formPanel;
    private TableWindow parentWindow;

    public newForm(TableWindow parentWindow) {
        super();
        this.parentWindow = parentWindow;
        formFields = new newFormFields();
        this.fieldsToDisplay = formFields.createFields();
        setFieldsConfig(formFields);
    }

    @Override
    public Widget[] getFieldsToDisplay() {
        return fieldsToDisplay;
    }

    @Override
    public BaseFormFieldConfig createFormFields() {
        return formFields;
    }

    @Override
    public String getBizObjectName() {
        return BIZ_OBJECT;
    }
} 