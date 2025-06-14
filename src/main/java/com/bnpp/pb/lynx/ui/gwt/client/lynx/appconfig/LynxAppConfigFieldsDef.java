package com.bnpp.pb.lynx.ui.gwt.client.lynx.appconfig;

import com.bnpp.pb.common.coreui.gwt.client.gxt3.base.BaseFieldsDef;

public class newFieldDef extends BaseFieldsDef {
    public static String[] gridFieldNames = new String[] {
        "id", "app_id", "prop_type", "prop_name", "prop_value", "active", "env", "create_date", "created_by"
    };
    public static String[] formFieldNames = new String[] {
        "id", "app_id", "prop_type", "prop_name", "prop_value", "active", "env", "create_date", "created_by", "last_modified", "last_modified_user_id", "version", "partition_key"
    };

    public newFieldDef() {
        this.setFieldNames(gridFieldNames, formFieldNames);
    }
} 