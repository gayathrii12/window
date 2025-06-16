package com.bnpp.pb.lynx.ui.gwt.client.lynx.appconfig;

import com.bnpp.pb.lynx.gxt.client.AppConfigFieldsDef;

public class AppConfigFieldsDef extends AppConfigFieldsDef {
    public static String[] gridFieldNames = new String[] {
        "id", "app_id", "prop_type", "prop_name", "prop_value", "active", "env", 
        "create_date", "created_by", "last_modified", "last_modified_user_id", "version"
    };

    public static String[] formFieldNames = new String[] {
        "id", "app_id", "prop_type", "prop_name", "prop_value", "active", "env",
        "create_date", "created_by", "last_modified", "last_modified_user_id", "version"
    };

    public AppConfigFieldsDef() {
        this.setFieldNames(gridFieldNames, formFieldNames);
    }
} 