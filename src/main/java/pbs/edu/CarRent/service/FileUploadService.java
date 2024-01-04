package pbs.edu.CarRent.service;


import pbs.edu.CarRent.payload.request.ResultInfo;

import java.io.IOException;

public interface FileUploadService {
    public ResultInfo fileUpload(String localPath, String fileName) throws IOException;
}
