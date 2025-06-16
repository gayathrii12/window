package com.bnpp.pb.lynx.ui.gwt.client.lynx.appconfig;

import com.bnpp.pb.common.coreui.gwt.client.gxt3.base.BaseGridColumnConfig;
import com.bnpp.pb.common.coreui.gwt.client.gxt3.grid.ModelData;
import com.bnpp.pb.common.coreui.gwt.client.gxt3.ui.grid.TableView;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import java.util.List;

public class AppConfigColConfig extends BaseGridColumnConfig {
    
    @SuppressWarnings("unchecked")
    public AppConfigColConfig(TableView tableView) {
        super(tableView);
        getGridFormatter().getHrefInfoMap().putAll(new AppConfigGridFormatter().getHrefInfoMap());
    }

    public List<ColumnConfig<ModelData<String>, ?>> createColumns() {
        final List<ColumnConfig<ModelData<String>, ?>> columnConfigs = createColumns(
            AppConfigFieldsDef.gridFieldNames,
            AppConfigFieldsDef.gridFieldNames
        );

        setColumnProps(AppConfigFieldsDef.id, "Id", 60, true, true);
        setColumnProps(AppConfigFieldsDef.app_id, "Application ID", 100, false, true);
        setColumnProps(AppConfigFieldsDef.prop_type, "Property Type", 100, false, true);
        setColumnProps(AppConfigFieldsDef.prop_name, "Property Name", 150, false, true);
        setColumnProps(AppConfigFieldsDef.prop_value, "Property Value", 200, false, true);
        setColumnProps(AppConfigFieldsDef.active, "Active", 60, true, true);
        setColumnProps(AppConfigFieldsDef.env, "Environment", 80, false, true);
        setColumnProps(AppConfigFieldsDef.create_date, "Create Date", 100, false, true);
        setColumnProps(AppConfigFieldsDef.created_by, "Created By", 100, false, true);
        setColumnProps(AppConfigFieldsDef.last_modified, "Last Modified", 100, false, true);
        setColumnProps(AppConfigFieldsDef.last_modified_user_id, "Last Modified By", 120, false, true);
        setColumnProps(AppConfigFieldsDef.version, "Version", 60, false, true);

        return columnConfigs;
    }
} 