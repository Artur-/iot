# IoT dashboard microfront example using Java

This example shows an application for monitoring and controlling heating in an apartment.

## Architecture

It is built as far as possible using only Java and consists of the following modules:

### app

The container/base application with the menu, main layout and a few views. In a monolith application, everything would be in this module.

![main-view](https://user-images.githubusercontent.com/260340/66188476-fcf2e780-e68f-11e9-9be0-964675089fd3.png)

### roominfo

A reusable web component created using Java and exported as `<room-info>`. Used in the "Floorplan" and "All rooms" views.

![roominfo](https://user-images.githubusercontent.com/260340/66188456-f5334300-e68f-11e9-83d1-638213775f86.png)

### floorplan

The floorplan view. Separated out to allow it to evolve independently of the other views. Uses a custom SVG based component built using LitElement and integrated into Java.

Uses `<room-info>` as a web component in a popup.

### components

A shared jar containing all the components used in the application.

Used by `roominfo`, `floorplan` and `app`.

### bundle

The shared JavaScript bundle used in the browser. Contains all components for the full application to avoid loading the same components for all different parts. This resolves both a performance issue (load button only once) and a technical limitation (the same web component / custom element / tag can only be defined once for a whole web page).

### db

Sets up an in memory database for the whole application. In a real world application this would be removed and replaced by a real database.

## Building all parts

First build `components`, which is a module used by other parts

```
cd components
mvn clean install
cd ..
```

Build all the other parts

```
for dir in app bundle db floorplan roominfo ; do cd $dir ; mvn install ; cd .. ; done
```

or by running `mvn install` for each part separately

## Running the project

The project has been tested using Apache Tomcat 8.5.47 and 9.0.27. Older versions might have strange issues.

First you need to start the H2 DB. This is accessed by multiple war files so
it should be started outside the servlet container:

```
java -cp ~/.m2/repository/com/h2database/h2/1.4.199/h2-1.4.199.jar org.h2.tools.Server -ifNotExists
```

In real cases, you would use Postgres, MySQL or some other external database and configure that in `*/src/main/resources/application.properties` instead of H2.

When the DB is running, deploy the `db` war to your server. This should be deployed first as it initializes the database tables.

```
cp db/target/*.war ~/tomcat-folder/webapps/
```

Then deploy the rest of the war files

```
cp */target/*.war ~/tomcat-folder/webapps/
```

## Testing

Open http://localhost:8080/iot-app/

This assumes that your local Tomcat runs on port 8080

## The Technical Details

### Load web components only once

Web components can only be defined once in the browser (the tag can only be defined once). A standard Vaadin application loads a JavaScript bundle which defines all components used in the application so if you deploy and use multiple applications on the same page they will all define `<vaadin-button>` and the page will fail.

To deal with this, the `bundle` module includes all used components (https://github.com/Artur-/iot/blob/master/bundle/src/main/java/org/vaadin/artur/microfront/bundle/Bundle.java) and all other application modules excludes all components in their webpack config (https://github.com/Artur-/iot/blob/master/app/webpack.config.js#L28). This ensures that e.g. `<vaadin-button>` is defined by the `bundle` file and not by any other module.

### Exporting UI parts

The main application and menu+navigation is defined in the `app` module. The floorplan view is defined in a separate module and the view is exported as a `<floorplan-view>` web component (https://github.com/Artur-/iot/blob/master/floorplan/src/main/java/org/artur/iot/view/floorplan/Floorplan.java#L18). To be able to navigate to this from the menu in the `app` module, a representation copmponent for this class is created in the `app` module: https://github.com/Artur-/iot/blob/master/app/src/main/java/org/artur/iot/view/floorplan/RemoteFloorplan.java#L14. This class defines the URL for the view (`@Route(value = "floorplan", layout = MainLayout.class)`) and the tag that is used in the `floorplan` module (`@Tag("floorplan-view")`). It also ensures that the JavaScript where the web component is defined (`/iot-floorplan/web-component/floorplan-view.js`) is loaded.

For `<room-info>`, a similar approach is taken in the modules where the component is used, e.g. https://github.com/Artur-/iot/blob/master/app/src/main/java/org/artur/iot/RemoteRoomInfo.java#L11.
