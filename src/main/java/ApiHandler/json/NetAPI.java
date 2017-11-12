package ApiHandler.json;

/**
 * Created by Ermin Kameric on 12.11.2017.
 */
public class NetAPI {

    private String region;

    private String loc;

    private String hostname;

    private String org;

    private String country;

    private String city;

    private String ip;

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "ClassPojo [region = " + region + ", loc = " + loc + ", hostname = " + hostname + ", org = " + org + ", country = " + country + ", city = " + city + ", ip = " + ip + "]";
    }
}
