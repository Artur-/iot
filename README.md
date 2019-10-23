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

```
mvn clean install
```

## Running the project

First you need to start the H2 DB. This is accessed by multiple war files so
it should be started outside the servlet container:
```
java -cp ~/.m2/repository/com/h2database/h2/1.4.199/h2-1.4.199.jar org.h2.tools.Server -ifNotExists
```

In real cases, you would use Postgres, MySQL or some other external database and configure that in `*/src/main/resources/application.properties` instead of H2.

When the DB is running, deploy the war files to your server, e.g. Apache
Tomcat
```
cp */target/*.war ~/tomcat-folder/webapps/
```

## Testing
Open http://localhost:8080/iot-app/

This assumes that your local Tomcat runs on port 8080

