package com.bnpp.pb.lynx.ui.gwt.client.lynx.appconfig;

import com.bnpp.pb.common.coreui.gwt.client.gxt3.ui.form.ActionBar;
import com.bnpp.pb.common.coreui.gwt.client.gxt3.ui.grid.TableWindow;
import com.bnpp.pb.common.coreui.gwt.client.gxt3.ui.grid.profile.AbstractBaseProfile;
import com.bnpp.pb.common.coreui.gwt.client.gxt3.ui.grid.profile.GridProfile;

public class AppConfigProfile extends AbstractBaseProfile {
    private TableWindow parentWindow;
    private AppConfigFiltersBar filterBar;

    public AppConfigProfile(TableWindow parentWindow) {
        super(parentWindow);
        this.parentWindow = parentWindow;
    }

    public void initProfiles() {
        filterBar = new AppConfigFiltersBar();
        getProfileAsList().add(new GridProfile("", parentWindow, filterBar,
            new AppConfigColConfig(getManagedWindow().getTableView()), true));
    }
} 