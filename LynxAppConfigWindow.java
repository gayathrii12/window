package com.bnpp.pb.lynx.ui.gwt.client.lynx.appconfig;

import com.bnpp.pb.common.coreui.gwt.client.enums.ToolbarFlag;
import com.bnpp.pb.common.coreui.gwt.client.gxt3.grid.ModelData;
import com.bnpp.pb.common.coreui.gwt.client.gxt3.ui.form.DataEntryForm;
import com.bnpp.pb.common.coreui.gwt.client.gxt3.ui.grid.GridToolbar;
import com.bnpp.pb.common.coreui.gwt.client.gxt3.ui.grid.TableView;
import com.bnpp.pb.common.coreui.gwt.client.gxt3.ui.grid.TableWindow;
import com.bnpp.pb.common.coreui.gwt.client.gxt3.ui.grid.profile.AbstractBaseProfile;
import com.bnpp.pb.common.coreui.gwt.client.gxt3.util.GridPanel;
import com.bnpp.pb.lynx.enums.app.AppIdsConstants;
import com.bnpp.pb.lynx.ui.gwt.client.LynxGridToolBar;
import com.google.gwt.user.client.ui.IsWidget;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent;

import java.util.EnumSet;

public class LynxAppConfigWindow extends TableWindow {
    private static LynxAppConfigWindow instance;
    private FramedPanel panel;
    private LynxAppConfigForm form;

    private static final EnumSet<ToolbarFlag> defaultFlags = EnumSet.of(
        ToolbarFlag.EXPORT,
        ToolbarFlag.EXPORT_MAIL,
        ToolbarFlag.EXPORT_PRINT
    );

    public static LynxAppConfigWindow getInstance() {
        if (instance == null) {
            instance = new LynxAppConfigWindow();
        }
        return instance;
    }

    private LynxAppConfigWindow() {
        super();
    }

    @Override
    public GridToolbar createGridToolbar() {
        return new LynxGridToolBar(tableView);
    }

    @Override
    public EnumSet<ToolbarFlag> getToolbarFlags() {
        return defaultFlags;
    }

    @Override
    public AbstractBaseProfile createProfile() {
        return new LynxAppConfigProfile(this);
    }

    @Override
    public TableView createTableView() {
        return new LynxAppConfigTableView(true, this);
    }

    @Override
    public String getAppId() {
        return AppIdsConstants.APP_LYNX_APP_CONFIG.getCode();
    }

    @Override
    public IsWidget createPanel() {
        this.form = new LynxAppConfigForm(getTableView().getParentWindow());
        form.init();
        form.setOperationCode(DataEntryForm.INSERT);

        GridPanel gridPanel = (GridPanel) super.createPanel();
        gridPanel.getGrid().getView().setForceFit(true);
        setupGridRowSelection();

        ContentPanel viewPanel = new ContentPanel();
        viewPanel.setHeaderVisible(true);
        viewPanel.setHeading("Application Configuration");
        viewPanel.add(gridPanel);

        VerticalLayoutContainer container = new VerticalLayoutContainer();
        container.add(viewPanel, new VerticalLayoutContainer.VerticalLayoutData(1, 1, new Margins(4)));

        panel = new FramedPanel();
        panel.setButtonAlign(BoxLayoutContainer.BoxLayoutPack.CENTER);
        panel.setHeading("Enter Application Configuration");
        panel.setBodyBorder(true);
        panel.setCollapsible(true);
        panel.setPixelSize(500, 300);
        panel.setCollapsible(false);
        panel.setLayoutData(new MarginData(10));
        panel.setWidget(form.getPanel());

        container.add(panel, new VerticalLayoutContainer.VerticalLayoutData(1, .4d, new Margins(4)));
        return container;
    }

    private void setupGridRowSelection() {
        tableView.getGrid().getSelectionModel().addSelectionChangedHandler(
            new SelectionChangedEvent.SelectionChangedHandler<ModelData<String>>() {
                @Override
                public void onSelectionChanged(SelectionChangedEvent<ModelData<String>> event) {
                    ModelData modelData = tableView.getGrid().getSelectionModel().getSelectedItem();
                    if (modelData != null) {
                        form.resetForm();
                        form.setRecord(modelData);
                        form.setRecordId(modelData.get(LynxAppConfigFieldsDef.id));
                        form.loadData();
                    }
                }
            });
    }
} 