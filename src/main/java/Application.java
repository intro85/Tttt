import org.flywaydb.core.Flyway;
import org.postgresql.ds.PGSimpleDataSource;
import persistence.RepoImpl;
import web.Worker;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Application {

    private static final String SQL_GET_RECORD = "SELECT * FROM URLS WHERE STATUS = 'NEW'";

    public static void main(String[] args) throws SQLException {

        PGSimpleDataSource source = new PGSimpleDataSource();
        source.setServerName("localhost");
        source.setPortNumber(5432);
        source.setDatabaseName("proxify");
        source.setUser("postgres");
        source.setPassword("postgres");

        Flyway flyway = Flyway.configure().dataSource(source).load();
        flyway.migrate();

        try (Connection connection = source.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SQL_GET_RECORD);
            RepoImpl repo = new RepoImpl(source);
            while (rs.next()) {
                Worker worker = new Worker(rs, repo);
                repo.inProcess(rs.getLong("id"));
                worker.run();
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
