package persistence;

import enumerator.Status;
import lombok.AllArgsConstructor;

import javax.sql.DataSource;
import java.sql.*;

@AllArgsConstructor
public class RepoImpl implements Repo {

    private final DataSource source;

    @Override
    public void saveCode(Long id, int code) {
        try (Connection connection = source.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE urls SET status = ?, http_code = ? WHERE id = ?");
            preparedStatement.setString(1, (code == 200) ? Status.DONE.getValue() : Status.ERROR.getValue());
            preparedStatement.setLong(2, code);
            preparedStatement.setLong(3, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void inProcess(Long id) {
        try (Connection connection = source.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE urls SET status = ? WHERE id = ?");
            preparedStatement.setString(1, Status.PROCESSING.getValue());
            preparedStatement.setLong(2, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
