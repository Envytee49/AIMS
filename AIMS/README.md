# AIMS - An Internet Media Store

## Tech Stack
	-Database: MySQL
	-Backend: Java, Hibernate
	-Frontend: ReactJs, JavaFx
## How to run

1. Download [MySQL](https://dev.mysql.com/downloads/installer/) (mysql-installer-community-8.0.37.0.msi)
2. Configure username and password in `src/main/resources/META-INF`
3. Create database 'aims' in MySQL
4. Run `createTables` method in `src/test/java/com/example/aims/AIMSTEST.java`
5. Execute `data.sql` in MySQL from `src/main/resources/com/example/aims/db/data.sql`
6. Start the program from `src/main/java/com/example/aims/App.java`

## How to run online module

1. Enter correct gmail at 'Delivery Info Screen' during application runtime.
2. After successful payment, an email will be sent to your gmail.
3. Start the server at `AIMS/OnlineOrder/src/main/java/org/example/onlineorder/OnlineOrderApplication.java`.
    - Note: If port exception occurs, change configuration in `META-INF/persistence.xml`.
4. Go to `AIMS/OnlineOrder/frontend/online-order` and run `npm run dev` (run `npm install` first if necessary).
5. Download [Node.js](https://nodejs.org/dist/v20.11.1/node-v20.11.1-x64.msi) if not already installed.
