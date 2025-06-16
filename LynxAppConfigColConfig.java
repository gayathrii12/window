package com.bnpp.pb.lynx.ui.gwt.client.lynx.appconfig;

import com.bnpp.pb.common.coreui.gwt.client.gxt3.base.BaseGridColumnConfig;
import com.bnpp.pb.common.coreui.gwt.client.gxt3.grid.ModelData;
import com.bnpp.pb.common.coreui.gwt.client.gxt3.ui.grid.TableView;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import java.util.List;

public class LynxAppConfigColConfig extends BaseGridColumnConfig {
    
    @SuppressWarnings("unchecked")
    public LynxAppConfigColConfig(TableView tableView) {
        super(tableView);
        getGridFormatter().getHrefInfoMap().putAll(new LynxAppConfigGridFormatter().getHrefInfoMap());
    }

    public List<ColumnConfig<ModelData<String>, ?>> createColumns() {
        final List<ColumnConfig<ModelData<String>, ?>> columnConfigs = createColumns(
            LynxAppConfigFieldsDef.gridFieldNames,
            LynxAppConfigFieldsDef.gridFieldNames
        );

        setColumnProps(LynxAppConfigFieldsDef.id, "Id", 60, true, true);
        setColumnProps(LynxAppConfigFieldsDef.app_id, "Application ID", 100, false, true);
        setColumnProps(LynxAppConfigFieldsDef.prop_type, "Property Type", 100, false, true);
        setColumnProps(LynxAppConfigFieldsDef.prop_name, "Property Name", 150, false, true);
        setColumnProps(LynxAppConfigFieldsDef.prop_value, "Property Value", 200, false, true);
        setColumnProps(LynxAppConfigFieldsDef.active, "Active", 60, true, true);
        setColumnProps(LynxAppConfigFieldsDef.env, "Environment", 80, false, true);
        setColumnProps(LynxAppConfigFieldsDef.create_date, "Create Date", 100, false, true);
        setColumnProps(LynxAppConfigFieldsDef.created_by, "Created By", 100, false, true);
        setColumnProps(LynxAppConfigFieldsDef.last_modified, "Last Modified", 100, false, true);
        setColumnProps(LynxAppConfigFieldsDef.last_modified_user_id, "Last Modified By", 120, false, true);
        setColumnProps(LynxAppConfigFieldsDef.version, "Version", 60, false, true);

        return columnConfigs;
    }
} 