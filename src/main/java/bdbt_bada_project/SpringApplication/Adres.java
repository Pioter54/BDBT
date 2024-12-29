package bdbt_bada_project.SpringApplication;

public class Adres {
    private int Nr_adresu;
    private String Kraj;
    private String Miasto;
    private String Ulica;
    private String Nr_lokalu;
    private int Kod_pocztowy;

    public Adres(int nr_adresu, String kraj, String miasto, String ulica, String nr_lokalu, int kod_pocztowy) {
        Nr_adresu = nr_adresu;
        Kraj = kraj;
        Miasto = miasto;
        Ulica = ulica;
        Nr_lokalu = nr_lokalu;
        Kod_pocztowy = kod_pocztowy;
    }

    public int getNr_adresu() {
        return Nr_adresu;
    }

    public void setNr_adresu(int nr_adresu) {
        Nr_adresu = nr_adresu;
    }

    public String getKraj() {
        return Kraj;
    }

    public void setKraj(String kraj) {
        Kraj = kraj;
    }

    public String getMiasto() {
        return Miasto;
    }

    public void setMiasto(String miasto) {
        Miasto = miasto;
    }

    public String getUlica() {
        return Ulica;
    }

    public void setUlica(String ulica) {
        Ulica = ulica;
    }

    public String getNr_lokalu() {
        return Nr_lokalu;
    }

    public void setNr_lokalu(String nr_lokalu) {
        Nr_lokalu = nr_lokalu;
    }

    public int getKod_pocztowy() {
        return Kod_pocztowy;
    }

    public void setKod_pocztowy(int kod_pocztowy) {
        Kod_pocztowy = kod_pocztowy;
    }

    @Override
    public String toString() {
        return "Adres{" +
                "Nr_adresu=" + Nr_adresu +
                ", Kraj='" + Kraj + '\'' +
                ", Miasto='" + Miasto + '\'' +
                ", Ulica='" + Ulica + '\'' +
                ", Nr_lokalu='" + Nr_lokalu + '\'' +
                ", Kod_pocztowy=" + Kod_pocztowy +
                '}';
    }
}
