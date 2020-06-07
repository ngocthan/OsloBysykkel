package no.oslobysykkel.sykkelstativer.model;

public final class Station {
    private String station_name;
    private int num_bikes_available;
    private int num_docks_available;

    public Station(String station_name, int num_bikes_available, int num_docks_available) {
        this.station_name = station_name;
        this.num_bikes_available = num_bikes_available;
        this.num_docks_available = num_docks_available;
    }

    public String getStation_name() {
        return station_name;
    }

    public void setStation_name(String station_name) {
        this.station_name = station_name;
    }

    public int getNum_bikes_available() {
        return num_bikes_available;
    }

    public void setNum_bikes_available(int num_bikes_available) {
        this.num_bikes_available = num_bikes_available;
    }

    public int getNum_docks_available() {
        return num_docks_available;
    }

    public void setNum_docks_available(int num_docks_available) {
        this.num_docks_available = num_docks_available;
    }
}
