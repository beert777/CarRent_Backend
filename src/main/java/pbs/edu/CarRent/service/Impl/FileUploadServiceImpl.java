package pbs.edu.CarRent.service.Impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pbs.edu.CarRent.payload.request.ResponseCode;
import pbs.edu.CarRent.payload.request.ResultInfo;
import pbs.edu.CarRent.service.FileUploadService;
import pbs.edu.CarRent.service.utils.FtpUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Service
@Slf4j
public class FileUploadServiceImpl implements FileUploadService {

    private static final String IMAGE_SERVER_URL = "#";

    @Override
    public ResultInfo fileUpload(String localPath, String fileName) throws IOException {
        String userid = "pai";
        ResultInfo resultInfo = new ResultInfo();
        FtpUtil ftpUtil = new FtpUtil();
        Boolean aBoolean = ftpUtil.ftpLogin();
        if (!aBoolean){
            log.error("FTP :: Logowanie użytkownika nie powiodło się");
            resultInfo.setResultCode(ResponseCode.FTP_LOGIN_ERROR);
            return resultInfo;
        }

        FileInputStream inputStream = new FileInputStream(new File(localPath));



        Boolean isChangeUserSuccess = ftpUtil.changeWorkingDirectory(userid);
        if (!isChangeUserSuccess){
            log.error("FTP :: Nie udało się przełączyć katalogu użytkownika, katalog użytkownika nie istnieje, najpierw utwórz katalog użytkownika");
            ftpUtil.makeDirectory(userid);
        }
        Boolean isStoreFile = ftpUtil.storeFile(fileName, inputStream,userid);
        String  s= ftpUtil.printWorkingDirectory();
        s = s.replace("\\\\", "/");
        s = s.replace("/websites/aleksanderwieczarek", IMAGE_SERVER_URL);
        resultInfo.setPathFolder(s);
        if(!isStoreFile){
            log.error("FTP :: " +
                    "Nie udało się przesłać pliku po stronie serwera");

            resultInfo.setResultCode(ResponseCode.FTP_STOREFILE_ERROR);

            return resultInfo;
        }
        Boolean isLogout = ftpUtil.ftpLogout();
        if (!isLogout){
            log.error("FTP :: Wylogowanie z serwera FTP nie powiodło się");
            resultInfo.setResultCode(ResponseCode.FTP_LOGOUT_ERROR);
            return resultInfo;
        }

        resultInfo.setResultCode(ResponseCode.SUCCESS);
        return resultInfo;
    }
}
