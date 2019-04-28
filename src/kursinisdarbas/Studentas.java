/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kursinisdarbas;

/**
 *
 * @author Airidas
 */
public class Studentas {
    int ID;
    int Kodas;
    String Vardas;
    String Pavarde;
    int Grupes_ID;

    public Studentas() {
    }

    
    
    public Studentas(int ID, int Kodas, String Vardas, String Pavarde, int Grupes_ID) {
        this.ID = ID;
        this.Kodas = Kodas;
        this.Vardas = Vardas;
        this.Pavarde = Pavarde;
        this.Grupes_ID = Grupes_ID;
    }

    // Setteriai ---------------------------------------------------------------
    public void setID(int ID) {
        this.ID = ID;
    }

    public void setKodas(int Kodas) {
        this.Kodas = Kodas;
    }

    public void setVardas(String Vardas) {
        this.Vardas = Vardas;
    }

    public void setPavarde(String Pavarde) {
        this.Pavarde = Pavarde;
    }

    public void setGrupes_ID(int Grupes_ID) {
        this.Grupes_ID = Grupes_ID;
    }
    // Getteriai ---------------------------------------------------------------

    public int getID() {
        return ID;
    }

    public int getKodas() {
        return Kodas;
    }

    public String getVardas() {
        return Vardas;
    }

    public String getPavarde() {
        return Pavarde;
    }

    public int getGrupes_ID() {
        return Grupes_ID;
    }
    
}
