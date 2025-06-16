package com.bnpp.pb.lynx.ui.gwt.client.lynx.appconfig;

import com.bnpp.pb.common.coreui.gwt.client.gxt3.ui.grid.TableView;
import com.bnpp.pb.common.coreui.gwt.client.gxt3.base.BaseGridColumnConfig;
import com.bnpp.pb.common.coreui.gwt.client.gxt3.base.BaseFieldsDef;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;

public class newTableView extends TableView {
    private newColConfig columnConfig;
    private newWindow parentWindow;
    public static final String BIZ_OBJECT = "lynx_app_config";

    public newTableView(boolean autoLoad, newWindow parentWindow) {
        super(autoLoad, parentWindow);
        this.parentWindow = parentWindow;
        setHistoryMode(true);
    }

    @Override
    public String getBizObjectName() {
        return BIZ_OBJECT;
    }

    @Override
    public ColumnModel createColModel() {
        return new ColumnModel(getColumnsConfig().createColumns());
    }

    @Override
    public BaseGridColumnConfig createColumnsConfig() {
        columnConfig = new newColConfig(this);
        return columnConfig;
    }

    @Override
    public BaseFieldsDef createFieldsDef() {
        return new newFieldDef();
    }

    public newColConfig getColumnConfig() {
        return columnConfig;
    }
} 