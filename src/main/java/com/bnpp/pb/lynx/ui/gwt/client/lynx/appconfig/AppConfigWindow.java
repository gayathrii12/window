package com.bnpp.pb.lynx.ui.gwt.client.lynx.appconfig;

import com.bnpp.pb.common.coreui.gwt.client.gxt3.ui.form.ActionBar;
import com.bnpp.pb.common.coreui.gwt.client.gxt3.ui.grid.TableWindow;
import com.bnpp.pb.common.coreui.gwt.client.gxt3.ui.grid.TableView;
import com.bnpp.pb.common.coreui.gwt.client.gxt3.ui.grid.model.ModelData;
import com.sencha.gxt.widget.core.client.event.RowClickEvent;
import com.sencha.gxt.widget.core.client.event.RowClickEvent.RowClickHandler;

public class AppConfigWindow extends TableWindow {
    private AppConfigForm form;
    private AppConfigButtonHandlers buttonHandlers;

    public AppConfigWindow() {
        super(new AppConfigTableView(null));
        ((AppConfigTableView) getTableView()).setWindow(this);
        setHeadingText("Application Configuration");
        
        initializeForm();
        initializeButtonHandlers();
        setupRowClickHandler();
    }

    private void initializeForm() {
        form = new AppConfigForm();
        setForm(form);
    }

    private void initializeButtonHandlers() {
        buttonHandlers = new AppConfigButtonHandlers(this, form, getActionBar());
    }

    private void setupRowClickHandler() {
        getTableView().getGrid().addRowClickHandler(new RowClickHandler() {
            @Override
            public void onRowClick(RowClickEvent event) {
                ModelData<String> model = getTableView().getSelectionModel().getSelectedItem();
                if (model != null) {
                    form.bind(model);
                }
            }
        });
    }

    @Override
    public void onWindowClose() {
        super.onWindowClose();
        form.reset();
    }
} 