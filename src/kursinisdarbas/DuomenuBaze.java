package kursinisdarbas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

class DuomenuBaze {
    Connection conn;
    Statement state;
    ArrayList <Studentas> studentai = new ArrayList<>();
    Studentas stud = new Studentas();
    
    
    public DuomenuBaze() throws SQLException {
        conn = DriverManager.getConnection("jdbc:derby://localhost:1527/Universitetas", "Airidas", "database");
        state = conn.createStatement();
        System.out.println(conn);
    }
    public void SpausdintiStudentus() throws SQLException{
        String line = "SELECT studento_kodas, vardas, pavarde FROM Studentas";
        ResultSet rs = state.executeQuery(line);
        while(rs.next()){
            int kodas = rs.getInt("Studento_Kodas");
            String vardas = rs.getString("Vardas");
            String pavarde = rs.getString("Pavarde");
            TrintiTarpus(vardas);
            TrintiTarpus(pavarde);
            System.out.println(kodas+" "+vardas+" "+pavarde);
        }
    }
    public int Prisijungti(int kodas, String slaptazodis, boolean Destytojas) throws SQLException{
        if(!Destytojas){
            String line = "SELECT studento_ID, Studento_kodas, slaptažodis FROM Studentas";
            ResultSet rs = state.executeQuery(line);
            while(rs.next()){
                int ID = rs.getInt("Studento_ID");
                TrintiTarpus(rs.getString("slaptažodis"));
                String pass = rs.getString("slaptažodis");
                if(kodas == rs.getInt("Studento_Kodas") && slaptazodis.equals( TrintiTarpus ( rs.getString("slaptažodis") ) ) ){
                    return ID;
                }
                else return -1;
            }
        }else{
            String line = "SELECT Destytojo_ID, kodas, slaptazodis FROM Destytojas";
            ResultSet rs = state.executeQuery(line);
            while(rs.next()){
                if(kodas == rs.getInt("kodas") && slaptazodis.equals( TrintiTarpus ( rs.getString("slaptazodis") ) ) ){
                    return rs.getInt("Destytojo_ID");
                }
            }
        }
        return -1;
    }
    public Object[][] Studentai(){
        try{
            GautiStudentuSarasa();
            Object[][] row = new Object[studentai.size()][4];
            System.out.print(studentai.size());
            for(int u = 0; u<studentai.size(); u++){
                row[u][0] = studentai.get(u).getID();
                row[u][1] = studentai.get(u).getKodas();
                row[u][2] = studentai.get(u).getVardas();
                row[u][3] = studentai.get(u).getPavarde();
                //System.out.println(row[u][0]+" "+row[u][1]+" "+row[u][2]+" "+row[u][3]);
            }
            return row;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    public String TrintiTarpus(String a){
        return a.replaceAll("\\s+","");
    }

    public void GautiStudentuSarasa(){
        try {
            String Command = "SELECT studento_ID, studento_kodas, vardas, pavarde FROM STUDENTAS";
            ResultSet rs = state.executeQuery(Command);
            //rs.first();
            while(rs.next()){
                stud = new Studentas();
                stud.ID = rs.getInt("Studento_ID");
                stud.Kodas = rs.getInt("Studento_Kodas");
                stud.Vardas = rs.getString("Vardas");
                stud.Pavarde = rs.getString("Pavarde");
                
                TrintiTarpus(stud.Vardas);
                TrintiTarpus(stud.Pavarde);
                
                studentai.add(stud);
                System.out.println(stud.Grupes_ID+" "+stud.Kodas+" "+stud.Vardas+" "+stud.Pavarde);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DuomenuBaze.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private Object getColumnModel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void IvestiNauja(int code, String pass, String name, String lname, String group) throws SQLException{
            String line= "INSERT INTO studentas (Studento_ID, Studento_Kodas, Slaptažodis, Vardas, Pavarde)"
            + "values (" + (int)newID()+", " + code+", '" + pass + "', '" + name + "', '" + lname + "')";
        if(GetGroupID(group) != -1){
            line = "INSERT INTO studentas (Studento_ID, Studento_Kodas, Slaptažodis, Vardas, Pavarde, Grupes_ID)"
            + "values (" + newID()+", " + code+", '" + pass + "', '" + name + "', '" + lname + "', " + GetGroupID(group) + ")";
        }
       state = conn.createStatement();
       state.executeUpdate(line);
    }
    public int GetGroupID(String group) throws SQLException{
        String Command = "SELECT grupes_kodas, grupes_id FROM GRUPE";
        ResultSet rs = state.executeQuery(Command);
        while(rs.next()){
            String n = rs.getString("grupes_kodas");
            int x = rs.getInt("grupes_id");
            if(n.equals(group)) return x;
        }
        return -1;
    }
    public int newID() throws SQLException{
        String Command = "SELECT studento_id FROM STUDENTAS";
        ResultSet rs = state.executeQuery(Command);
        int max = 1;
        while(rs.next()){
            int next= rs.getInt("Studento_id");
           if(next > max)
               max = next;
        }
        System.out.println("L" + max + "L");
        return max+1;
    }
    
}
