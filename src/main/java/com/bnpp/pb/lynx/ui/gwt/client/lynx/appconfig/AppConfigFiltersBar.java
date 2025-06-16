package com.bnpp.pb.lynx.ui.gwt.client.lynx.appconfig;

import com.bnpp.pb.common.coreui.gwt.client.gxt3.ui.filter.FiltersBar;
import com.bnpp.pb.common.coreui.gwt.client.gxt3.ui.filter.StringFilter;
import com.bnpp.pb.common.coreui.gwt.client.op.IFilterType;

public class AppConfigFiltersBar extends FiltersBar {
    private StringFilter appIdFilter;
    private StringFilter propTypeFilter;
    private StringFilter propNameFilter;
    private StringFilter envFilter;

    public static final String BIZ_OBJECT = "appConfig";

    @Override
    public String getFilterPrefix() {
        return null;
    }

    @Override
    public void create() {
        super.create();

        // App ID Filter
        appIdFilter = new StringFilter();
        appIdFilter.createFilter();
        appIdFilter.setFilterFieldName(AppConfigFieldsDef.app_id);
        appIdFilter.getSearchField().setWidth(150);
        appIdFilter.getSearchField().setEmptyText("Application ID");
        appIdFilter.setFilterOp(IFilterType.EQ);
        addFilter(appIdFilter);

        // Property Type Filter
        propTypeFilter = new StringFilter();
        propTypeFilter.createFilter();
        propTypeFilter.setFilterFieldName(AppConfigFieldsDef.prop_type);
        propTypeFilter.getSearchField().setWidth(150);
        propTypeFilter.getSearchField().setEmptyText("Property Type");
        propTypeFilter.setFilterOp(IFilterType.EQ);
        addFilter(propTypeFilter);

        // Property Name Filter
        propNameFilter = new StringFilter();
        propNameFilter.createFilter();
        propNameFilter.setFilterFieldName(AppConfigFieldsDef.prop_name);
        propNameFilter.getSearchField().setWidth(150);
        propNameFilter.getSearchField().setEmptyText("Property Name");
        propNameFilter.setFilterOp(IFilterType.EQ);
        addFilter(propNameFilter);

        // Environment Filter
        envFilter = new StringFilter();
        envFilter.createFilter();
        envFilter.setFilterFieldName(AppConfigFieldsDef.env);
        envFilter.getSearchField().setWidth(100);
        envFilter.getSearchField().setEmptyText("Environment");
        envFilter.setFilterOp(IFilterType.EQ);
        addFilter(envFilter);
    }

    @Override
    public void resetFilters() {
        super.resetFilters();
        appIdFilter.getSearchField().setEmptyText("Application ID");
        propTypeFilter.getSearchField().setEmptyText("Property Type");
        propNameFilter.getSearchField().setEmptyText("Property Name");
        envFilter.getSearchField().setEmptyText("Environment");
    }
} 