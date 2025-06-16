package com.bnpp.pb.lynx.ui.gwt.client.lynx.appconfig;

import com.bnpp.pb.common.coreui.gwt.client.gxt3.ui.grid.TableWindow;
import com.bnpp.pb.common.coreui.gwt.client.gxt3.ui.grid.profile.AbstractBaseProfile;
import com.bnpp.pb.common.coreui.gwt.client.gxt3.ui.grid.profile.GridProfile;

public class newProfile extends AbstractBaseProfile {
    private TableWindow parentWindow;
    private newFiltersBar filterBar;

    public newProfile(TableWindow parentWindow) {
        super(parentWindow);
        this.parentWindow = parentWindow;
    }

    @Override
    public void initProfiles() {
        filterBar = new newFiltersBar();
        getProfileAsList().add(new GridProfile(
            "",
            parentWindow,
            filterBar,
            new newColConfig(getManagedWindow().getTableView()),
            true
        ));
    }
} 