package pbs.edu.CarRent.payload.request;

public enum ResponseCode {
    SUCCESS(20000000, "powodzenie"),
    GEN_INTERNAL(20000001, "\n" +
            "operacja nie powiodła się"),
    FTP_LOGIN_ERROR(2000003,"FTPLogowanie do serwera nie powiodło się"),
    FTP_LOGOUT_ERROR(2000004,"Wylogowanie z serwera FTP nie powiodło się"),
    FTP_CHANGE_BASEPATH_ERROR(2000005, "Przełączanie katalogu podstawowego FTP nie powiodło się"),
    FTP_CHANGE_USERPATH_ERROR(2000006,"Przełączanie katalogu użytkownika FTP nie powiodło się"),
    FTP_STOREFILE_ERROR(2000007,"Nie udało się przesłać pliku na serwer FTP"),
    FTP_RETRIEVEFILE_ERROR(2000008, "Serwer FTP nie pobrał pliku"),
    FTP_MAKEDIRECTORY_ERROR(200009,"Nie udało się utworzyć katalogu użytkownika");
    private int code;
    private String desc;

    ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return "ResponseCode{" +
                "code=" + code +
                ", desc='" + desc + '\'' +
                '}';
    }
}
