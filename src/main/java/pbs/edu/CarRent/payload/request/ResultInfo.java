package pbs.edu.CarRent.payload.request;

import java.nio.file.Path;

public class ResultInfo<T> {
    public Enum resultCode;

    public T data;

    public String pathFolder;
    public Path pathFile;

    public String getPathFolder() {
        return pathFolder;
    }

    public void setPathFolder(String pathFolder) {
        this.pathFolder = pathFolder;
    }

    public Path getPathFile() {
        return pathFile;
    }

    public void setPathFile(Path pathFile) {
        this.pathFile = pathFile;
    }

    public Enum getResultCode() {
        return resultCode;
    }

    public void setResultCode(Enum resultCode) {
        this.resultCode = resultCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }



}
