package web;

import lombok.AllArgsConstructor;
import persistence.RepoImpl;

import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.ResultSet;

@AllArgsConstructor
public class Worker implements Runnable {

    public ResultSet resultSet;
    public RepoImpl repo;

    @Override
    public void run() {
        try {
            URL url = new URL(resultSet.getString("url"));
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            repo.saveCode(resultSet.getLong("id"), con.getResponseCode());
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
