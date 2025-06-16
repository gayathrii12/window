package com.bnpp.pb.lynx.ui.gwt.client.lynx.appconfig;

import com.bnpp.pb.common.coreui.gwt.client.base.BaseFieldsDef;
import com.bnpp.pb.common.coreui.gwt.client.gxt3.base.BaseGridColumnConfig;
import com.bnpp.pb.common.coreui.gwt.client.gxt3.ui.form.DataEntryForm;
import com.bnpp.pb.common.coreui.gwt.client.gxt3.ui.grid.TableView;
import com.bnpp.pb.common.coreui.gwt.client.gxt3.ui.grid.TableWindow;
import com.bnpp.pb.common.coreui.gwt.client.op.IFilterType;
import com.bnpp.pb.common.coreui.gwt.client.to.QueryArgsTO;
import com.bnpp.pb.lynx.enums.app.AppIdsConstants;
import com.bnpp.pb.lynx.ui.gwt.client.LynxUiUtil;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;

public class LynxAppConfigTableView extends TableView {
    private LynxAppConfigColConfig columnConfig;
    private LynxAppConfigWindow parentWindow;
    public static final int DEFAULT_PAGE_SIZE = 20;
    public static final String BIZ_OBJECT = "lynxAppConfig";

    public LynxAppConfigTableView(boolean autoLoad, TableWindow parentWindow) {
        super(autoLoad, parentWindow);
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
        columnConfig = new LynxAppConfigColConfig(this);
        return columnConfig;
    }

    @Override
    public BaseFieldsDef createFieldsDef() {
        return new LynxAppConfigFieldsDef();
    }

    public LynxAppConfigColConfig getColumnConfig() {
        return columnConfig;
    }

    @Override
    public DataEntryForm getDataEntryForm() {
        return null;
    }

    @Override
    public QueryArgsTO getQueryArgsFromFilter(QueryArgsTO queryArgs) {
        QueryArgsTO queryArgsTO = null;
        queryArgsTO = super.getQueryArgsFromFilter(queryArgsTO);
        return LynxUiUtil.appendDefaultQueryArgs(queryArgsTO);
    }

    @Override
    public String getAppId() {
        return AppIdsConstants.APP_LYNX_APP_CONFIG.getCode();
    }

    @Override
    public QueryArgsTO createDefaultQuery() {
        final QueryArgsTO queryArgs = super.createDefaultQuery();
        queryArgs.fieldNames = LynxAppConfigFieldsDef.gridFieldNames;
        queryArgs.filterFieldNames = new String[]{"id", "active"};
        queryArgs.filterTypes = new String[]{IFilterType.GT, IFilterType.EQ};
        queryArgs.filterFieldValues = new String[]{"0", "Y"};
        queryArgs.orderBy = new String[]{"id"};
        queryArgs.orderByDir = new String[]{"asc"};
        return queryArgs;
    }
} 