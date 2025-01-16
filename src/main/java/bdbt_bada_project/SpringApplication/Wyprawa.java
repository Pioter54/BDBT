package bdbt_bada_project.SpringApplication;

public class Wyprawa {
    private int nr_wycieczki;
    private String data_od;
    private String data_do;
    private String opis;
    private double cena;

    public Wyprawa() {}

    public Wyprawa(int nr_wycieczki, String data_od, String data_do, String opis, double cena) {
        this.nr_wycieczki = nr_wycieczki;
        this.data_od = data_od;
        this.data_do = data_do;
        this.opis = opis;
        this.cena = cena;
    }

    // Gettery i settery
    public int getNr_wycieczki() {
        return nr_wycieczki;
    }

    public void setNr_wycieczki(int nr_wycieczki) {
        this.nr_wycieczki = nr_wycieczki;
    }

    public String getData_od() {
        return data_od;
    }

    public void setData_od(String data_od) {
        this.data_od = data_od;
    }

    public String getData_do() {
        return data_do;
    }

    public void setData_do(String data_do) {
        this.data_do = data_do;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    @Override
    public String toString() {
        return "Wyprawa{" +
                "nr_wycieczki=" + nr_wycieczki +
                ", data_od='" + data_od + '\'' +
                ", data_do='" + data_do + '\'' +
                ", opis='" + opis + '\'' +
                ", cena=" + cena +
                '}';
    }
}
