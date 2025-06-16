package com.bnpp.pb.lynx.ui.gwt.client.lynx.appconfig;

import com.bnpp.pb.common.coreui.gwt.client.security.ISecurityHelper;
import com.bnpp.pb.common.coreui.gwt.client.ui.grid.GridFormatUtil;
import com.bnpp.pb.common.coreui.gwt.client.ui.grid.GridFormatterBase;
import com.bnpp.pb.common.coreui.gwt.client.ui.grid.ObjectFormatter;
import com.google.gwt.core.client.GWT;

public class AppConfigGridFormatter extends GridFormatterBase {
    private ObjectFormatter dateFormat;

    public AppConfigGridFormatter(GridFormatUtil gridFormatUtil, ISecurityHelper securityHelper, String appId, ObjectFormatter dateFormat) {
        super(gridFormatUtil, appId, securityHelper);
        this.dateFormat = dateFormat;
    }

    public AppConfigGridFormatter() {
    }

    @Override
    public String getRowClass(int row, int rowNum) {
        return "";
    }

    @Override
    public String getColumnClass(int row, int column) throws Exception {
        String styleClass = "";
        return styleClass;
    }

    @Override
    public String format(int row, int column, String value) throws Exception {
        final String columnName = getGridRecordUtil().getColumnDataIndex(column);
        value = getGridRecordUtil().getAsString(row, columnName);
        try {
            if (value != null && value.length() > 0) {
                // Add any specific formatting logic here if needed
            }
        } catch (Exception e) {
            GWT.log("exception" + e);
            e.printStackTrace();
        }
        return value == null ? "" : value;
    }
} 