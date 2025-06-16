package com.bnpp.pb.lynx.ui.gwt.client.lynx.appconfig;

import com.bnpp.pb.common.coreui.gwt.client.enums.Entitlement;
import com.bnpp.pb.common.coreui.gwt.client.gxt3.app.DialogFactory;
import com.bnpp.pb.common.coreui.gwt.client.gxt3.base.BaseFormFieldConfig;
import com.bnpp.pb.common.coreui.gwt.client.gxt3.binding.FormBinding;
import com.bnpp.pb.common.coreui.gwt.client.gxt3.exception.ExceptionHelper;
import com.bnpp.pb.common.coreui.gwt.client.gxt3.grid.ModelData;
import com.bnpp.pb.common.coreui.gwt.client.gxt3.security.PermissionsHelper;
import com.bnpp.pb.common.coreui.gwt.client.gxt3.ui.grid.TableWindow;
import com.bnpp.pb.common.coreui.gwt.client.gxt3.util.DateUtil;
import com.bnpp.pb.common.coreui.gwt.client.gxt3.util.FieldUtil;
import com.bnpp.pb.common.coreui.gwt.client.log.Logger;
import com.bnpp.pb.common.coreui.gwt.client.service.IGwtService;
import com.bnpp.pb.common.coreui.gwt.client.to.CreateOpResultTO;
import com.bnpp.pb.common.coreui.gwt.client.to.ParamTO;
import com.bnpp.pb.common.coreui.gwt.client.to.UpdateOpResultTO;
import com.bnpp.pb.lynx.enums.app.AppIdsConstants;
import com.bnpp.pb.lynx.ui.gwt.client.AbstractLynxDataEntryForm;
import com.bnpp.pb.lynx.ui.gwt.client.Constants;
import com.bnpp.pb.lynx.ui.gwt.client.ui.bundle.LynxResourceBundle;
import com.google.gwt.user.client.Window.ClosingEvent;
import com.google.gwt.user.client.Window.ClosingHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FormPanel;
import com.sencha.gxt.widget.core.client.form.ValueBaseField;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LynxAppConfigForm extends AbstractLynxDataEntryForm {
    private final static String BIZ_OBJECT = "lynxAppConfig";
    private LynxAppConfigFormFields formFields;
    private Widget[] fieldsToDisplay;
    private Widget[] fieldsToSave;
    private Widget[] fieldsToUpdate;
    private Widget[] fieldsToDelete;
    private int formPanelHeight = 500;
    private int topPanelHeight = 200;
    private Widget[] fields;
    private FormBinding formBinding;
    protected FormPanel formPanel;
    private TableWindow parentWindow;
    private LynxAppConfigFiltersBar filtersBar;
    protected TextButton deleteBtn;
    protected TextButton clearBtn;

    public LynxAppConfigForm(TableWindow parentWindow) {
        super();
        com.google.gwt.user.client.Window.addWindowClosingHandler(new ClosingHandler() {
            @Override
            public void onWindowClosing(ClosingEvent arg0) {
                // release trade lock if the browser is closed
            }
        });
        this.parentWindow = parentWindow;
        formFields = new LynxAppConfigFormFields(this);
        this.fields = formFields.createFields();
        setFieldsConfig(createFormFields());
        fieldsToDisplay = formFields.fieldsToDisplay();
        fieldsToDelete = formFields.fieldsToDelete();
        fieldsToSave = formFields.fieldsToSave();
        fieldsToUpdate = formFields.fieldsToUpdate();
    }

    @Override
    public Widget[] getFieldsToDisplay() {
        return fieldsToDisplay;
    }

    @Override
    public BaseFormFieldConfig createFormFields() {
        return formFields;
    }

    @Override
    public String getBizObjectName() {
        return BIZ_OBJECT;
    }

    @Override
    protected void createFormButtons() {
        submitBtn = createSubmitButton();
        submitBtn.setIcon(LynxResourceBundle.get().lynxConfirm());
        submitBtn.setEnabled(false);
        topPanel.addButton(submitBtn);

        deleteBtn = new TextButton("Delete");
        deleteBtn.addSelectHandler(createDeleteButtonListener());
        deleteBtn.setIcon(LynxResourceBundle.get().dartUnconfirm());
        topPanel.addButton(deleteBtn);

        clearBtn = new TextButton("Clear");
        clearBtn.addSelectHandler(createResetListener());
        clearBtn.setIcon(LynxResourceBundle.get().arrowUndo());
        topPanel.addButton(clearBtn);

        cancelBtn = new TextButton("New Entry");
        cancelBtn.addSelectHandler(createResetListener());
        clearBtn.setIcon(LynxResourceBundle.get().arrowUndo());
        topPanel.addButton(cancelBtn);

        if (!PermissionsHelper.isEntitled(AppIdsConstants.APP_LYNX_APP_CONFIG.getCode(), Entitlement.ANY_ACCESS)) {
            submitBtn.setEnabled(false);
            deleteBtn.setEnabled(false);
            clearBtn.setEnabled(false);
            cancelBtn.setEnabled(false);
        }
    }

    protected SelectEvent.SelectHandler createDeleteButtonListener() {
        return new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                try {
                    formFields.setDeleteDefaultValues();
                    final ParamTO cancelFlag = new ParamTO();
                    cancelFlag.setName(Constants.LYNX_APP_CONFIG);
                    cancelFlag.setValue(Constants.CANCEL_FLAG_PARAM);
                    final String idValue = String.valueOf(getRecordId());
                    executeUpdate(idValue, fieldsToDelete, new ParamTO[]{cancelFlag});
                } catch (final Exception ex) {
                    Logger.error("Error in onClick for submitListener", new Exception(ex));
                }
            }
        };
    }

    protected SelectEvent.SelectHandler createResetListener() {
        return new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                resetForm();
            }
        };
    }

    protected SelectEvent.SelectHandler createCancelListener() {
        return new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                resetForm();
            }
        };
    }

    public void resetForm() {
        this.setOperationCode(INSERT);
        for (Widget field : getFields()) {
            if (field != null) {
                ValueBaseField valueBaseField = (ValueBaseField) (((FieldLabel) field).getWidget());
                if ((ValueBaseField) (((FieldLabel) field).getWidget()) instanceof ValueBaseField) {
                    valueBaseField.reset();
                }
            }
        }
        setOperationCode(INSERT);
        submitBtn.setText("Submit");

        if (!PermissionsHelper.isEntitled(AppIdsConstants.APP_LYNX_APP_CONFIG.getCode(), Entitlement.ANY_ACCESS)) {
            submitBtn.disable();
            submitBtn.setEnabled(false);
            deleteBtn.setEnabled(false);
            clearBtn.setEnabled(false);
            cancelBtn.setEnabled(false);
        } else {
            submitBtn.setEnabled(true);
            submitBtn.enable();
        }
    }

    protected void executeInsert(final Widget[] fieldsForInsert, final ParamTO[] params) {
        parentWindow.getTableView().mask("Submitting...");
        formFields.setDefaults();

        ArrayList<String> fieldIdList = new ArrayList<String>();
        List<Object> fieldValueList = new ArrayList<Object>();
        assignFieldsForUpdate(fieldsForInsert, fieldIdList, fieldValueList);

        final String[] fieldIds = fieldIdList.toArray(new String[fieldIdList.size()]);
        final String[] fieldValues = toStringArray(fieldValueList);

        IGwtService.Util.getInstance().create(getBizObjectName(), fieldIds, fieldValues, params,
            new AsyncCallback<CreateOpResultTO>() {
                @Override
                public void onFailure(final Throwable caught) {
                    parentWindow.getTableView().unmask();
                    ExceptionHelper.onFailure(this, caught);
                }

                @Override
                public void onSuccess(final CreateOpResultTO response) {
                    parentWindow.getTableView().unmask();
                    if (!response.isValid()) {
                        for (final Widget field : fieldsForInsert) {
                            final String fieldName = field.getTitle();
                            final String[] fieldMessages = response.getMessagesFor(fieldName);
                            if (fieldMessages != null) {
                                final StringBuilder invalidText = new StringBuilder();
                                for (final String message : fieldMessages) {
                                    invalidText.append(message);
                                    invalidText.append("<br/>");
                                }
                            }
                        }
                        DialogFactory.getOkAlertBox("Submit", "Submit Failed. " + response.displayMessage, null, null).show();
                    } else {
                        DialogFactory.getOkAlertBox("Submitted Successfully", "Submitted Successfully", null, null).show();
                        refresh();
                    }
                }
            });
    }

    protected void executeUpdate(final String idValue, final Widget[] fieldsForUpdate, final ParamTO[] params) {
        parentWindow.getTableView().mask("Submitting...");
        ArrayList<String> fieldIdList = new ArrayList<String>();
        ArrayList<Object> fieldValueList = new ArrayList<Object>();
        assignFieldsForUpdate(fieldsForUpdate, fieldIdList, fieldValueList);

        final String[] fieldIds = fieldIdList.toArray(new String[fieldIdList.size()]);
        final String[] fieldValues = toStringArray(fieldValueList);

        IGwtService.Util.getInstance().update(getBizObjectName(), idValue, fieldIds, fieldValues, params,
            new AsyncCallback<UpdateOpResultTO>() {
                @Override
                public void onFailure(final Throwable caught) {
                    parentWindow.getTableView().unmask();
                    Logger.debug("Fail - onFailure");
                    ExceptionHelper.onFailure(this, caught);
                }

                @Override
                public void onSuccess(final UpdateOpResultTO response) {
                    parentWindow.getTableView().unmask();
                    Logger.debug("Success");
                    if (!response.isValid()) {
                        for (final Widget field : fieldsForUpdate) {
                            final String fieldName = field.getTitle();
                            final String[] fieldMessages = response.getMessagesFor(fieldName);
                            if (fieldMessages != null) {
                                final StringBuilder invalidText = new StringBuilder();
                                for (final String message : fieldMessages) {
                                    invalidText.append(message);
                                    invalidText.append("<br/>");
                                }
                            }
                        }
                        DialogFactory.getOkAlertBox("Submit", "Submit Failed", null, null).show();
                    } else {
                        if (params != null && params.length > 0) {
                            if (Constants.CANCEL_FLAG_PARAM.equals(params[0].getValue())) {
                                DialogFactory.getOkAlertBox("Deleted Successfully", "Deleted Successfully", null, null).show();
                            } else {
                                DialogFactory.getOkAlertBox("Updated Successfully", "Updated Successfully", null, null).show();
                            }
                        } else {
                            DialogFactory.getOkAlertBox("Submitted Successfully", "Submitted Successfully", null, null).show();
                        }
                        refresh();
                    }
                }
            });
    }

    @Override
    public void loadRecord(ModelData record) {
        if (record == null) {
            formBinding.unbind();
            setRecordId(null);
        } else {
            setRecordId(getRecord());
            formBinding.bind(new ModelData(record));
        }
    }

    public void loadData() {
        this.setOperationCode(UPDATE);
        this.setRecord(this.getRecord());
        submitBtn.setText("Update");

        String appId = String.valueOf(getRecord().get("app_id"));
        String propType = String.valueOf(getRecord().get("prop_type"));
        String propName = String.valueOf(getRecord().get("prop_name"));
        String propValue = String.valueOf(getRecord().get("prop_value"));
        String env = String.valueOf(getRecord().get("env"));

        formFields.getAppIdField().setValue(appId);
        formFields.getPropTypeField().setValue(propType);
        formFields.getPropNameField().setValue(propName);
        formFields.getPropValueField().setValue(propValue);
        formFields.getEnvField().setValue(env);

        if (PermissionsHelper.isEntitled(AppIdsConstants.APP_LYNX_APP_CONFIG.getCode(), Entitlement.ANY_ACCESS)) {
            cancelBtn.enable();
        } else {
            submitBtn.setEnabled(false);
            deleteBtn.setEnabled(false);
            clearBtn.setEnabled(false);
            cancelBtn.setEnabled(false);
        }
    }

    protected SelectEvent.SelectHandler createSubmitListener() {
        return new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                try {
                    if (UPDATE.equals(getOperationCode())) {
                        formFields.setUpdateDefaults();
                        final String idValue = String.valueOf(getRecordId());
                        final ParamTO updateFlag = new ParamTO();
                        updateFlag.setName(Constants.LYNX_APP_CONFIG);
                        updateFlag.setValue(Constants.UPDATE);
                        executeUpdate(idValue, fieldsToUpdate, new ParamTO[]{updateFlag});
                    } else if (INSERT.equals(getOperationCode())) {
                        executeInsert(fieldsToSave, null);
                    }
                } catch (final Exception ex) {
                    Logger.error("Error in onClick for submitListener", new Exception(ex));
                }
            }
        };
    }

    public String[] toStringArray(List<Object> fieldValueList) {
        String[] array = new String[fieldValueList.size()];
        for (int i = 0; i < array.length; i++) {
            Object fieldValue = fieldValueList.get(i);
            if (fieldValue != null) {
                array[i] = fieldValue.toString();
            }
        }
        return array;
    }

    protected void assignFieldsForUpdate(final Widget[] fields, ArrayList<String> fieldIdList, List<Object> fieldValueList) {
        for (Widget field : fields) {
            Object value = extractValue(field);
            HasName widget = (HasName) (FieldUtil.lookupFieldFromFieldLabel(field));
            if (widget instanceof DateField) {
                final Date date = ((DateField) widget).getValue();
                fieldIdList.add(widget.getName());
                fieldValueList.add(DateUtil.formatDateAsNumeric(DateUtil.truncateDate(date)));
            } else if (widget.getName() != null && value != null) {
                fieldValueList.add(value);
                fieldIdList.add(widget.getName());
            }
        }
    }

    public void reloadGrid() {
        if (parentWindow.getTableView() != null) {
            parentWindow.getTableView().reloadGrid(1);
        }
    }

    public void refresh() {
        filtersBar = (LynxAppConfigFiltersBar) parentWindow.getTableView().getFiltersBar();
        filtersBar.resetFilters();
        reloadGrid();
        resetForm();
    }

    public boolean isFormValid() {
        boolean isValid = super.isFormValid();
        if (!PermissionsHelper.isEntitled(AppIdsConstants.APP_LYNX_APP_CONFIG.getCode(), Entitlement.ANY_ACCESS)) {
            isValid = false;
        }
        return isValid;
    }
} 