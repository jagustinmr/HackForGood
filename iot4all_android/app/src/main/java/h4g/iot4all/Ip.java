package h4g.iot4all;

/**
 * Created by JosePC on 10/03/2018.
 */

//Singleton para obtener ip
public class Ip {
    private String ip;

    private static Ip instance = null;

    private Ip() {
        ip="192.168.137.85";
    }

    public static Ip getInstance() {
        if(instance == null) {
            instance = new Ip();
        }
        return instance;
    }


    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
