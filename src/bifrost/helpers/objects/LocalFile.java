package bifrost.helpers.objects;

import javafx.beans.InvalidationListener;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jeremymorgan on 1/16/14.
 */
public class LocalFile {
    private StringProperty filename = new SimpleStringProperty();
    private StringProperty filepath = new SimpleStringProperty();
    private LongProperty last_modified = new SimpleLongProperty();
    private BooleanProperty has_origin = new SimpleBooleanProperty();
    private StringProperty origin = new SimpleStringProperty();
    private StringProperty origin_id = new SimpleStringProperty();
    private File java_file;

    public final StringProperty getFilenameProperty(){
        return filename;
    }

    public final StringProperty getPathProperty() {
        return filepath;
    }

    public final LongProperty getLastModifiedProperty() {
        return last_modified;
    }

    public final String getFilename() {
        return filename.get();
    }

    public final StringProperty filenameProperty() {
        return filename;
    }

    public final void setFilename(String filename) {
        this.filename.set(filename);
    }

    public final String getFilepath() {
        return filepath.get();
    }

    public final StringProperty filepathProperty() {
        return filepath;
    }

    public final void setFilepath(String filepath) {
        this.filepath.set(filepath);
    }

    public final long getLast_modified() {
        return last_modified.get();
    }

    public final LongProperty last_modifiedProperty() {
        return last_modified;
    }

    public final void setLast_modified(long last_modified) {
        this.last_modified.set(last_modified);
    }

    public final boolean getHas_origin() {
        return has_origin.get();
    }

    public final BooleanProperty has_originProperty() {
        return has_origin;
    }

    public final void setHas_origin(boolean has_origin) {
        this.has_origin.set(has_origin);
    }

    public final String getOrigin() {
        return origin.get();
    }

    public final StringProperty originProperty() {
        return origin;
    }

    public final void setOrigin(String origin) {
        this.origin.set(origin);
    }

    public final String getOrigin_id() {
        return origin_id.get();
    }

    public final StringProperty origin_idProperty() {
        return origin_id;
    }

    public final void setOrigin_id(String origin_id) {
        this.origin_id.set(origin_id);
    }

    public File getJava_file() {
        return java_file;
    }

    public void setJava_file(File java_file) {
        this.java_file = java_file;
    }

    public static LocalFile make(File file){
        LocalFile localFile = new LocalFile();
        localFile.setFilename( file.getName() );
        localFile.setFilepath( file.getAbsolutePath() );
        localFile.setLast_modified( file.lastModified() );
        localFile.setJava_file(file);

        return localFile;
    }

    public static List<LocalFile> make(List<File> fileList) {
        ArrayList<LocalFile> files = new ArrayList<LocalFile>();
        for (File file : fileList){
            files.add( make(file) );
        }
        return files;
    }
}
