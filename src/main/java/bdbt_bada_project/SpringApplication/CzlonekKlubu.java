package bdbt_bada_project.SpringApplication;

import java.time.LocalDate;

public class CzlonekKlubu {
    private int Nr_czlonka_klubu;
    private String Imie;
    private String Nazwisko;
    private String Pesel;
    private LocalDate Data_urodzenia;
    private String Plec;
    private String Telefon;
    private LocalDate Data_dolaczenia;
    private LocalDate Data_odejscia;
    private int Nr_adresu;

    public CzlonekKlubu() {
    }

    public CzlonekKlubu(int nr_czlonka_klubu, String imie, String nazwisko, String pesel, LocalDate data_urodzenia, String plec, String telefon, LocalDate data_dolaczenia, LocalDate data_odejscia, int nr_adresu) {
        Nr_czlonka_klubu = nr_czlonka_klubu;
        Imie = imie;
        Nazwisko = nazwisko;
        Pesel = pesel;
        Data_urodzenia = data_urodzenia;
        Plec = plec;
        Telefon = telefon;
        Data_dolaczenia = data_dolaczenia;
        Data_odejscia = data_odejscia;
        Nr_adresu = nr_adresu;
    }

    public String getImie() {
        return Imie;
    }

    public void setImie(String imie) {
        Imie = imie;
    }

    public String getNazwisko() {
        return Nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        Nazwisko = nazwisko;
    }

    public int getNr_czlonka_klubu() {
        return Nr_czlonka_klubu;
    }

    public void setNr_czlonka_klubu(int nr_czlonka_klubu) {
        Nr_czlonka_klubu = nr_czlonka_klubu;
    }

    public String getPesel() {
        return Pesel;
    }

    public void setPesel(String pesel) {
        Pesel = pesel;
    }

    public LocalDate getData_urodzenia() {
        return Data_urodzenia;
    }

    public void setData_urodzenia(LocalDate data_urodzenia) {
        Data_urodzenia = data_urodzenia;
    }

    public String getPlec() {
        return Plec;
    }

    public void setPlec(String plec) {
        Plec = plec;
    }

    public String getTelefon() {
        return Telefon;
    }

    public void setTelefon(String telefon) {
        Telefon = telefon;
    }

    public LocalDate getData_dolaczenia() {
        return Data_dolaczenia;
    }

    public void setData_dolaczenia(LocalDate data_dolaczenia) {
        Data_dolaczenia = data_dolaczenia;
    }

    public LocalDate getData_odejscia() {
        return Data_odejscia;
    }

    public void setData_odejscia(LocalDate data_odejscia) {
        Data_odejscia = data_odejscia;
    }

    public int getNr_adresu() {
        return Nr_adresu;
    }

    public void setNr_adresu(int nr_adresu) {
        Nr_adresu = nr_adresu;
    }

    @Override
    public String toString() {
        return "CzlonekKlubu{" +
                "Nr_czlonka_klubu=" + Nr_czlonka_klubu +
                ", Pesel='" + Pesel + '\'' +
                ", Data_urodzenia=" + Data_urodzenia +
                ", Plec='" + Plec + '\'' +
                ", Telefon='" + Telefon + '\'' +
                ", Data_dolaczenia=" + Data_dolaczenia +
                ", Data_odejscia=" + Data_odejscia +
                ", Nr_adresu=" + Nr_adresu +
                '}';
    }
}
