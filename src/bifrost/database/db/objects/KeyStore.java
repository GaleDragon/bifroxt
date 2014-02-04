package bifrost.database.db.objects;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by jeremymorgan on 1/15/14.
 */

@DatabaseTable(tableName = "keystore")
public class KeyStore {

    @DatabaseField(id = true)
    private int _id;

    @DatabaseField
    private String key;

    @DatabaseField
    private String host;

    @DatabaseField
    private String email;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /*
    * ORMLite needs a no-arg constructor
    * */
    public KeyStore(){}

    public String toString(){
        return email;
    }
}
