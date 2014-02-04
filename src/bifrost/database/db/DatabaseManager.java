package bifrost.database.db;

import bifrost.database.db.objects.KeyStore;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by jeremymorgan on 1/15/14.
 */
public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:assets/db.sqlite";
    private Dao<KeyStore, Integer> keyDao;
    public DatabaseManager() throws SQLException {
        ConnectionSource source = new JdbcConnectionSource(DB_URL);
        keyDao = DaoManager.createDao(source, KeyStore.class);

        try{
            TableUtils.createTable(source, KeyStore.class);
        } catch (SQLException sql){

        }
    }

    public DatabaseManager create( KeyStore newKey ) throws SQLException {
        keyDao.create(newKey);
        return this;
    }

    public List<KeyStore> read( KeyStore key ) throws SQLException{
        List<KeyStore> results = keyDao.queryForMatching(key);
        return results;
    }

    public DatabaseManager update( KeyStore key ) throws SQLException{
        keyDao.update(key);
        return this;
    }

    public DatabaseManager delete( KeyStore key ) throws SQLException{
        keyDao.delete(key);
        return this;
    }

    public ObservableList<KeyStore> getAll() throws SQLException {
        List<KeyStore> list = keyDao.queryForAll();
        ObservableList<KeyStore> keyStoreObservableList = FXCollections.observableList(list);
        return keyStoreObservableList;
    }
}
