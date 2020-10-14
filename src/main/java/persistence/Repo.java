package persistence;

public interface Repo {

    void saveCode(Long id, int code);
    void inProcess(Long id);

}
