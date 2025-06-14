package com.bnpp.pb.lynx.ui.gwt.client.lynx.appconfig;

import com.bnpp.pb.common.coreui.gwt.client.gxt3.ui.grid.TableWindow;
import com.bnpp.pb.common.coreui.gwt.client.gxt3.ui.grid.GridToolbar;
import com.bnpp.pb.lynx.ui.gwt.client.LynxGridToolBar;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.core.client.util.Margins;
import com.google.gwt.user.client.ui.IsWidget;
import java.util.EnumSet;

public class newWindow extends TableWindow {
    private static newWindow instance;
    private FramedPanel panel;
    private newForm form;

    public static newWindow getInstance() {
        if (instance == null) {
            instance = new newWindow();
        }
        return instance;
    }

    private newWindow() {
        super();
    }

    @Override
    public GridToolbar createGridToolbar() {
        return new LynxGridToolBar(tableView);
    }

    @Override
    public EnumSet getToolbarFlags() {
        return EnumSet.of(
            ToolbarFlag.EXPORT,
            ToolbarFlag.EXPORT_MAIL,
            ToolbarFlag.EXPORT_PRINT
        );
    }

    @Override
    public newProfile createProfile() {
        return new newProfile(this);
    }

    @Override
    public newTableView createTableView() {
        return new newTableView(true, this);
    }

    @Override
    public String getAppId() {
        return "APP_LYNX_APP_CONFIG"; // Adjust as needed
    }

    @Override
    public IsWidget createPanel() {
        this.form = new newForm(getTableView().getParentWindow());
        form.init();
        form.setOperationCode(DataEntryForm.INSERT);
        GridPanel gridPanel = (GridPanel) super.createPanel();
        gridPanel.getGrid().getView().setForceFit(true);

        ContentPanel viewPanel = new ContentPanel();
        viewPanel.setHeaderVisible(true);
        viewPanel.setHeading("Lynx App Config List");
        viewPanel.add(gridPanel);

        VerticalLayoutContainer container = new VerticalLayoutContainer();
        container.add(viewPanel, new VerticalLayoutContainer.VerticalLayoutData(1, .6d, new Margins(4)));

        panel = new FramedPanel();
        panel.setButtonAlign(BoxLayoutContainer.BoxLayoutPack.CENTER);
        panel.setHeading("Enter App Config");
        panel.setBodyBorder(true);
        panel.setCollapsible(true);
        panel.setPixelSize(500, 300);
        panel.setCollapsible(false);
        panel.setLayoutData(new MarginData(10));
        panel.setWidget(form.getPanel());

        container.add(panel, new VerticalLayoutContainer.VerticalLayoutData(1, .4d, new Margins(4)));
        return container;
    }
} 