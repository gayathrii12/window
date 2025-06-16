package com.bnpp.pb.lynx.ui.gwt.client.lynx.appconfig;

import com.bnpp.pb.common.coreui.gwt.client.gxt3.ui.grid.TableWindow;

public class AppConfigWindow extends TableWindow {
    public AppConfigWindow() {
        super(new AppConfigTableView(null));
        ((AppConfigTableView) getTableView()).setWindow(this);
        setHeadingText("Application Configuration");
    }
} 