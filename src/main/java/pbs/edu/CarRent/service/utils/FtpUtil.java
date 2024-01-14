package pbs.edu.CarRent.service.utils;

import io.netty.util.CharsetUtil;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import pbs.edu.CarRent.payload.request.ResultInfo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class FtpUtil {
    ResultInfo resultInfo = new ResultInfo();
    public FTPClient ftpClient = new FTPClient();
    public static final String HOSTNAME = "#";
    //ftp Server port number
    public static final int PORT = 21;
    // Sign in ftp User name and password of the server
    public static final String USERNAME = "#";
    public static final String PWD = "#";
    //ftpuser The base directory of
// All users' files are in this directory
// public static final String BASEPATH = "/var/ftp/test";
// Local character encoding
    static String LOCAL_CHARSET = "UTF-8";
    // FTP In the agreement , Specify the file name code as iso-8859-1
    static String SERVER_CHARSET = "ISO-8859-1";
    /** * @className FtpUtil * @author dnydys * @description For logging in ftp The server * @updateTime 2021/12/17 20:14 * @return: void * @version 1.0 */
    public Boolean ftpLogin() throws IOException {

        ftpClient.setAutodetectUTF8(true);
        ftpClient.setCharset(CharsetUtil.UTF_8);
        ftpClient.setControlEncoding(CharsetUtil.UTF_8.name());
// Server address and port
        ftpClient.connect(HOSTNAME,PORT);
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
// Login username and password
        Boolean isLogin = ftpClient.login(USERNAME, PWD);
        return isLogin;
    }
    /** * @className FtpUtil * @author dnydys * @description Used to quit ftp The server * @updateTime 2021/12/17 20:15 * @return: void * @version 1.0 */
    public Boolean ftpLogout() throws IOException {

        Boolean isLogout = ftpClient.logout();
        return isLogout;
    }

    public Boolean changeWorkingDirectory(String userPath) throws IOException {

        boolean b = ftpClient.changeWorkingDirectory("/websites/aleksanderwieczarek/"+userPath);
        return b ;
    }
    /** * @className FtpUtil * @author dnydys * @description Create user directory false Indicates that the user directory already exists * @updateTime 2021/12/17 21:59 * @return: java.lang.Boolean * @version 1.0 */
    public Boolean makeDirectory(String userid) throws IOException {

        boolean b = ftpClient.makeDirectory("/websites/aleksanderwieczarek/"+userid);
        System.out.println("@@@@@Folder"+"/websites/aleksanderwieczarek/"+userid);
        return b;
    }
    /** * @className FtpUtil * @author dnydys * @description 1. The name of the file saved on the server side ,2. Of uploaded files inputstream * @updateTime 2021/12/17 22:24 * @return: java.lang.Boolean * @version 1.0 */
    public Boolean storeFile(String filename, FileInputStream inputStream,String userID) throws IOException {

        return ftpClient.storeFile("/websites/aleksanderwieczarek/"+userID+"/"+filename,inputStream);

    }
    /** * @className FtpUtil * @author dnydys * @description Print the user's current directory * @updateTime 2021/12/18 9:59 * @return: java.lang.String * @version 1.0 */
    public String printWorkingDirectory() throws IOException {

        return ftpClient.printWorkingDirectory();
    }
    /** * @className FtpUtil * @author dnydys * @description * @updateTime 2021/12/18 10:20 * @return: void * @version 1.0 */
    public void enterLocalPassiveMode(){

// Before each data connection ftp client tell ftp server Opening A port to transmit data .
// as a result of ftp Open different ports to transmit data each time ,linux On , Security restrictions , Some ports are not open , blockages
// But I don't understand what he means by passive and active ways .
// Come back today to check the information and know ：
// FTP The protocol works in two ways ,Port and Pasv The way , Active and passive .
// (1) PORT（ Active mode ）
// How it works ： FTP The client connects to FTP Server's 21 port , Send user name and password login , After logging in successfully list List or read data , The client randomly opens a port （1024 above ）, send out PORT Order to FTP The server , Tell the server client to adopt active mode and open the port ;FTP Server received PORT After active mode command and port number , Through the server 20 Port and client open port connection , send data
// (2) PASV（ Passive mode ） This is the function of the above method .
// How it works ：FTP The client connects to FTP Server's 21 port , Send user name and password login , After logging in successfully list List or read data , send out PASV Order to FTP The server , The server opens a port randomly in the local area （1024 above ）, Then tell the client the open port , The client then connects to the open port of the server for data transmission
        ftpClient.enterLocalPassiveMode();
    }
    /** * @className FtpUtil * @author dnydys * @description Query the list of all files * @updateTime 2021/12/18 22:05 * @return: void * @version 1.0 */
    public FTPFile[] listFiles() throws IOException {

        if (FTPReply.isPositiveCompletion(ftpClient.sendCommand("OPTS UTF8", "ON"))) {

            LOCAL_CHARSET = "UTF-8";
        }
        ftpClient.setControlEncoding(LOCAL_CHARSET);
        return ftpClient.listFiles();
    }
    /** * @className FtpUtil * @author dnydys * @description Retrieving files * @updateTime 2021/12/18 22:12 * @return: java.lang.Boolean * @version 1.0 */
    public Boolean retrieveFile(String fileName, FileOutputStream outputStream) throws IOException {

        return ftpClient.retrieveFile(fileName, outputStream);
    }
    /** * @className FtpUtil * @author dnydys * @description * @updateTime 2021/12/18 23:07 * @return: null * @version 1.0 */
    public InputStream retrieveFileStream(String fileName) throws IOException {

        return ftpClient.retrieveFileStream(fileName);
    }
}


