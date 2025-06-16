package com.bnpp.pb.lynx.ui.gwt.client.lynx.appconfig;

import com.bnpp.pb.common.coreui.gwt.client.gxt3.base.BaseGridColumnConfig;
import com.bnpp.pb.common.coreui.gwt.client.gxt3.grid.ModelData;
import com.bnpp.pb.common.coreui.gwt.client.gxt3.ui.grid.TableView;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import java.util.List;
import com.bnpp.pb.common.coreui.gwt.client.gxt3.ui.grid.GridFormatter;

public class newColConfig extends BaseGridColumnConfig {
    private GridFormatter formatter;
    public newColConfig(TableView tableView) {
        super(tableView);
        this.formatter = new LynxAppConfigGridFormatter();
    }

    @Override
    public List<ColumnConfig<ModelData<String>, ?>> createColumns() {
        final List<ColumnConfig<ModelData<String>, ?>> columnConfigs = createColumns(
            newFieldDef.gridFieldNames,
            newFieldDef.gridFieldNames
        );
        setColumnProps("id", "Id", 60, true, true);
        setColumnProps("app_id", "App Id", 100, false, true);
        setColumnProps("prop_type", "Property Type", 100, false, true);
        setColumnProps("prop_name", "Property Name", 120, false, true);
        setColumnProps("prop_value", "Property Value", 200, false, true);
        setColumnProps("active", "Active", 40, true, true);
        setColumnProps("env", "Env", 40, false, true);
        setColumnProps("create_date", "Create Date", 100, false, true);
        setColumnProps("created_by", "Created By", 80, false, true);
        return columnConfigs;
    }

    @Override
    public GridFormatter getGridFormatter() {
        return formatter;
    }
}

class LynxAppConfigGridFormatter implements GridFormatter {
    @Override
    public Object format(String field, Object value) {
        return value;
    }
} 