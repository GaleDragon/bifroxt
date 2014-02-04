package bifrost.helpers.objects;

import bifrost.helpers.interfaces.BifrostFile;

/**
 * Created by jeremymorgan on 1/17/14.
 */
public class SyncFile implements BifrostFile {
    @Override
    public boolean sync() {
        return false;
    }
}
