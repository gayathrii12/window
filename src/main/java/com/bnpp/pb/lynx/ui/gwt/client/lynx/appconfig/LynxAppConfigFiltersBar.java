package com.bnpp.pb.lynx.ui.gwt.client.lynx.appconfig;

import com.bnpp.pb.common.coreui.gwt.client.gxt3.ui.filter.FiltersBar;
import com.bnpp.pb.common.coreui.gwt.client.gxt3.ui.filter.StringFilter;

public class LynxAppConfigFiltersBar extends FiltersBar {
    private StringFilter appIdFilter;
    private StringFilter propNameFilter;

    @Override
    public void create() {
        super.create();
        appIdFilter = new StringFilter();
        appIdFilter.createFilter();
        appIdFilter.setFilterFieldName("app_id");
        appIdFilter.getSearchField().setWidth(100);
        appIdFilter.getSearchField().setEmptyText("App Id");
        addFilter(appIdFilter);

        propNameFilter = new StringFilter();
        propNameFilter.createFilter();
        propNameFilter.setFilterFieldName("prop_name");
        propNameFilter.getSearchField().setWidth(120);
        propNameFilter.getSearchField().setEmptyText("Property Name");
        addFilter(propNameFilter);
    }

    @Override
    public void resetFilters() {
        super.resetFilters();
        appIdFilter.getSearchField().setEmptyText("App Id");
        propNameFilter.getSearchField().setEmptyText("Property Name");
    }
} 