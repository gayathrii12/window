package com.bnpp.pb.lynx.ui.gwt.client.lynx.appconfig;

import com.bnpp.pb.common.coreui.gwt.client.gxt3.base.BaseFormFieldConfig;
import com.bnpp.pb.common.coreui.gwt.client.gxt3.security.LoginHelper;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.form.Field;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;

public class AppConfigFormFields extends BaseFormFieldConfig {
    private AppConfigForm appConfigForm;
    private FieldLabel appIdField;
    private FieldLabel propTypeField;
    private FieldLabel propNameField;
    private FieldLabel propValueField;
    private FieldLabel envField;

    public AppConfigFormFields(AppConfigForm appConfigForm) {
        this.appConfigForm = appConfigForm;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Widget[] createFields() {
        setFieldNames(AppConfigFieldsDef.formFieldNames);

        getField(AppConfigFieldsDef.id).setTitle("Id");
        getField(AppConfigFieldsDef.active).setTitle("Active");
        getField(AppConfigFieldsDef.created_by).setTitle("Created By");
        getField(AppConfigFieldsDef.create_date).setTitle("Create Date");
        getField(AppConfigFieldsDef.last_modified).setTitle("Last Modified Date");
        getField(AppConfigFieldsDef.version).setTitle("Version");
        getField(AppConfigFieldsDef.last_modified_user_id).setTitle("Last Modified User Id");

        appIdField = createTextField("Application ID", "app_id", 200);
        propTypeField = createTextField("Property Type", "prop_type", 200);
        propNameField = createTextField("Property Name", "prop_name", 200);
        propValueField = createTextField("Property Value", "prop_value", 300);
        envField = createTextField("Environment", "env", 100);

        return fieldsToDisplay();
    }

    private FieldLabel createTextField(String label, String name, int width) {
        TextField field = new TextField();
        field.setName(name);
        field.setTitle(label);
        field.setWidth(width);
        return new FieldLabel(field, label);
    }

    public Widget[] fieldsToSave() {
        return new Widget[] {
            appIdField,
            propTypeField,
            propNameField,
            propValueField,
            envField,
            getField(AppConfigFieldsDef.active),
            getField(AppConfigFieldsDef.create_date),
            getField(AppConfigFieldsDef.created_by),
            getField(AppConfigFieldsDef.last_modified),
            getField(AppConfigFieldsDef.version),
            getField(AppConfigFieldsDef.last_modified_user_id)
        };
    }

    public Widget[] fieldsToUpdate() {
        return new Widget[] {
            appIdField,
            propTypeField,
            propNameField,
            propValueField,
            envField,
            getField(AppConfigFieldsDef.active),
            getField(AppConfigFieldsDef.create_date),
            getField(AppConfigFieldsDef.created_by),
            getField(AppConfigFieldsDef.last_modified),
            getField(AppConfigFieldsDef.version),
            getField(AppConfigFieldsDef.last_modified_user_id)
        };
    }

    public Widget[] fieldsToDelete() {
        return new Widget[] {
            getField(AppConfigFieldsDef.last_modified_user_id),
            getField(AppConfigFieldsDef.active)
        };
    }

    public Widget[] fieldsToDisplay() {
        return new Widget[] {
            appIdField,
            propTypeField,
            propNameField,
            propValueField,
            envField
        };
    }

    public void setDefaults() {
        ((Field)((FieldLabel)getField(AppConfigFieldsDef.active)).getWidget()).setValue("Y");
        ((Field)((FieldLabel)getField(AppConfigFieldsDef.version)).getWidget()).setValue("0");
        String generatedByUsername = LoginHelper.getCurrentUserDetails().getUserFullName();
        if (generatedByUsername == null) {
            generatedByUsername = LoginHelper.getCurrentUserId();
        }
        ((Field)((FieldLabel)getField(AppConfigFieldsDef.created_by)).getWidget()).setValue(generatedByUsername);
    }

    public void setUpdateDefaults() {
        ((Field)((FieldLabel)getField(AppConfigFieldsDef.active)).getWidget()).setValue("Y");
        String generatedByUsername = LoginHelper.getCurrentUserDetails().getUserFullName();
        if (generatedByUsername == null) {
            generatedByUsername = LoginHelper.getCurrentUserId();
        }
        ((Field)((FieldLabel)getField(AppConfigFieldsDef.created_by)).getWidget()).setValue(generatedByUsername);
        ((Field)((FieldLabel)getField(AppConfigFieldsDef.last_modified_user_id)).getWidget()).setValue(generatedByUsername);
    }

    public void setDeleteDefaultValues() {
        String generatedByUsername = LoginHelper.getCurrentUserDetails().getUserFullName();
        if (generatedByUsername == null) {
            generatedByUsername = LoginHelper.getCurrentUserId();
        }
        ((Field)((FieldLabel)getField(AppConfigFieldsDef.last_modified_user_id)).getWidget()).setValue(generatedByUsername);
        ((Field)((FieldLabel)getField(AppConfigFieldsDef.active)).getWidget()).setValue("N");
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

    public TextField getEnvField() {
        return (TextField) envField.getWidget();
    }
} 