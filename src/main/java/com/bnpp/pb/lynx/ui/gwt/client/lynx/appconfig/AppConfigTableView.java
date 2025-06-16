package com.bnpp.pb.lynx.ui.gwt.client.lynx.appconfig;

import com.bnpp.pb.common.coreui.gwt.client.gxt3.ui.grid.BaseTableView;
import com.bnpp.pb.common.coreui.gwt.client.gxt3.ui.grid.TableWindow;

public class AppConfigTableView extends BaseTableView {
    public AppConfigTableView(TableWindow window) {
        super(window);
    }

    @Override
    public void init() {
        setColConfig(new AppConfigColConfig(this));
        setFiltersBar(new AppConfigFiltersBar());
        setProfile(new AppConfigProfile(getWindow()));
    }
} 