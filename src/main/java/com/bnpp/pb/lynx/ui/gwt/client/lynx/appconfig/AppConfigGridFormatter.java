package com.bnpp.pb.lynx.ui.gwt.client.lynx.appconfig;

import com.bnpp.pb.common.coreui.gwt.client.security.ISecurityHelper;
import com.bnpp.pb.common.coreui.gwt.client.ui.grid.GridFormatUtil;
import com.bnpp.pb.common.coreui.gwt.client.ui.grid.GridFormatterBase;
import com.bnpp.pb.common.coreui.gwt.client.ui.grid.ObjectFormatter;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import java.util.Date;

public class AppConfigGridFormatter extends GridFormatterBase {
    private ObjectFormatter dateFormat;
    private static final String ACTIVE_STYLE = "x-grid-cell-active";
    private static final String INACTIVE_STYLE = "x-grid-cell-inactive";

    public AppConfigGridFormatter(GridFormatUtil gridFormatUtil, ISecurityHelper securityHelper, String appId, ObjectFormatter dateFormat) {
        super(gridFormatUtil, appId, securityHelper);
        this.dateFormat = dateFormat;
    }

    public AppConfigGridFormatter() {
    }

    @Override
    public String getRowClass(int row, int rowNum) {
        try {
            String active = getGridRecordUtil().getAsString(row, AppConfigFieldsDef.active);
            return "Y".equalsIgnoreCase(active) ? ACTIVE_STYLE : INACTIVE_STYLE;
        } catch (Exception e) {
            GWT.log("Error getting row class: " + e.getMessage());
            return "";
        }
    }

    @Override
    public String getColumnClass(int row, int column) throws Exception {
        String columnName = getGridRecordUtil().getColumnDataIndex(column);
        if (AppConfigFieldsDef.active.equals(columnName)) {
            String active = getGridRecordUtil().getAsString(row, columnName);
            return "Y".equalsIgnoreCase(active) ? ACTIVE_STYLE : INACTIVE_STYLE;
        }
        return "";
    }

    @Override
    public String format(int row, int column, String value) throws Exception {
        final String columnName = getGridRecordUtil().getColumnDataIndex(column);
        value = getGridRecordUtil().getAsString(row, columnName);
        
        try {
            if (value != null && value.length() > 0) {
                // Format dates
                if (AppConfigFieldsDef.create_date.equals(columnName) || 
                    AppConfigFieldsDef.last_modified.equals(columnName)) {
                    try {
                        Date date = DateTimeFormat.getFormat("yyyy-MM-dd HH:mm:ss").parse(value);
                        return DateTimeFormat.getFormat("dd/MM/yyyy HH:mm:ss").format(date);
                    } catch (Exception e) {
                        return value;
                    }
                }
                
                // Format active status
                if (AppConfigFieldsDef.active.equals(columnName)) {
                    return "Y".equalsIgnoreCase(value) ? "Yes" : "No";
                }
                
                // Format version number
                if (AppConfigFieldsDef.version.equals(columnName)) {
                    try {
                        return String.format("v%s", value);
                    } catch (Exception e) {
                        return value;
                    }
                }
            }
        } catch (Exception e) {
            GWT.log("Error formatting value: " + e.getMessage());
        }
        
        return value == null ? "" : value;
    }
} 