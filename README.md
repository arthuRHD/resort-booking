# resort-booking

## Where to configure

You need to change the credentials inside the constructor of the class `ConnectionHelper` which is located inside `src/main/java/com/resort/booking/dao/ConnectionHelper.java`.

```java
public ConnectionHelper() throws SQLException {
    loadDriver();
    String password = "*****";
    String login = "*****";

    // Intranet
    String url = "jdbc:oracle:thin:@//srvoracledb.intranet.int:1521/orcl.intranet.int";

    // Remote
    // String url = "jdbc:oracle:thin:@oracle.esigelec.fr:1521:orcl";

    this.connection = DriverManager.getConnection(url, login, password);
}
```

## How to run

After the credentials added, you need to launch the program with the driver added to the classpath

Go inside the repository and launch this command. Make sure you have Java installed on your pc.
Here's a supported JDK version: [graalvm-ce-17.0.7](https://github.com/graalvm/graalvm-ce-builds/releases/tag/jdk-17.0.7)

Launch operations test class to verify database operations
```shell
cd resort-booking
java -Dfile.encoding=UTF-8 -classpath ./target/classes:./lib/ojdbc7.g.jar com.resort.booking.TestOperations
```

Launch the Host menu
```shell
java -Dfile.encoding=UTF-8 -classpath ./target/classes:./lib/ojdbc7.g.jar com.resort.booking.App
```
