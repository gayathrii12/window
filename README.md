# Lynx Application Configuration Window

This project implements a GWT-based window for managing application configuration settings. It provides a user interface for viewing, creating, updating, and deleting application configuration records.

## Features

- Grid display of application configuration records
- Filtering capabilities for app_id, prop_type, prop_name, and env
- Form for adding/editing records
- Export functionality (to Excel, email, and print)
- Default sorting by ID
- Active record filtering

## Project Structure

The project consists of the following main components:

- `LynxAppConfigFieldsDef`: Defines field names for grid and form views
- `LynxAppConfigColConfig`: Configures grid columns
- `LynxAppConfigFiltersBar`: Implements filtering functionality
- `LynxAppConfigFormFields`: Manages form fields
- `LynxAppConfigGridFormatter`: Handles grid formatting
- `LynxAppConfigProfile`: Manages grid profile configuration
- `LynxAppConfigTableView`: Implements table view
- `LynxAppConfigWindow`: Main window implementation
- `LynxAppConfigForm`: Form handling and CRUD operations

## Database Schema

The application uses the following database table:

```sql
CREATE TABLE dbo.lynx_app_config (
    id numeric(12,0) IDENTITY NOT NULL,
    app_id varchar(255) NOT NULL,
    prop_type varchar(255) NOT NULL,
    prop_name varchar(255) NOT NULL,
    prop_value varchar(1000) NOT NULL,
    active varchar(1) DEFAULT 'Y' NOT NULL,
    env varchar(4) DEFAULT 'Y' NOT NULL,
    create_date datetime DEFAULT GETDATE() NOT NULL,
    created_by varchar(20) NULL,
    last_modified datetime NULL,
    last_modified_user_id varchar(20) NULL,
    version int default 0 NULL,
    partition_key varchar(40) NULL,
    PRIMARY KEY(id)
)
```

## Usage

To use the application configuration window:

```java
LynxAppConfigWindow.getInstance().show();
```

## Dependencies

- GWT (Google Web Toolkit)
- Sencha GXT
- BNP Paribas Common UI Components 