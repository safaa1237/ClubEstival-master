package Utils;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseUtilis {
    private static Connection conn = null;
    private static String url = "jdbc:mysql://localhost/clubmail";
    private static String login = "root";
    private static String password = "";
    public static void connection() {
        try {
            conn = DriverManager.getConnection(url,login,password );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConn() {
        return conn;
    }

    public static void closeConnection(){
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean isConnected(){
        if(conn != null){
            return true;
        }else return false;
    }

//    public static String getClientLogin(int idReserv){
//        if(isConnected()){
//
//            try {
//                String request = "Select login from client";
//                PreparedStatement stmt= conn.prepareStatement(request);
//                ResultSet rs = stmt.executeQuery();
//                if(rs != null){
//                    while(rs.next()){
//                        return rs.getString(0);
//                    }
//                }else{
//                    System.out.println("User unkonwn");
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return null;
//    }

    public static String getDateArrive(int idClient){
        if(isConnected()){
            String request = "Select date_arrive  from reservation where id_client='"+idClient+"'";
            try {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(request);
                rs.next();
                return String.valueOf(rs.getDate("date_arrive"));
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    public static String getDateDepart(int idClient){
        if(isConnected()){
            String request = "Select date_depart from reservation where id_client='"+idClient+"'";
            try {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(request);
                rs.next();
                return String.valueOf(rs.getDate("date_depart"));
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    public static String getTypeRestauration(int idClient){
        if(isConnected()){

            try {
                String request = "Select id_restauration from reservation where id_client='"+idClient+"'";
                Statement stmt= conn.createStatement();
                ResultSet rs = stmt.executeQuery(request);
//                if(rs != null){
//                    while(rs.next()){
//                rs.next();
//                return rs.getString("");
                rs.next();
                int idRestauration = rs.getInt("id_restauration");
                request = "Select type_restauration from restauration where id ='"+idRestauration+"'";
                stmt = conn.createStatement();
                rs = stmt.executeQuery(request);
                rs.next();
                return rs.getString("type_restauration");
//                }else{
//                    System.out.println("User unkonwn");
//                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    public static int getIdClient(int idReserv){
        if(isConnected()){

            try {
                String request = "Select id from client where id_reservation='"+idReserv+"'";
                Statement stmt= conn.createStatement();
                ResultSet rs = stmt.executeQuery(request);
//                if(rs != null){
                rs.next();
                return rs.getInt("id");

//                }else{
//                    System.out.println("User unkonwn");
//                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }
    public static String getNomComplet(int idClient){
        if(isConnected()){

            try {
                String request = "Select firstname,lastname from personnes where id='"+idClient+"'";
                Statement stmt= conn.createStatement();
                ResultSet rs = stmt.executeQuery(request);
//                if(rs != null){
                rs.next();
                String nomComplet = rs.getString("firstname") +" "+ rs.getString("lastname");
                return nomComplet;

//                }else{
//                    System.out.println("User unkonwn");
//                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }




    public static boolean getReservationState(int idReserv){
        if(isConnected()){

            try {
                String request = "Select regle_paiement from reservation where id_client='"+idReserv+"'";
                Statement stmt= conn.createStatement();
                ResultSet rs = stmt.executeQuery(request);
//                if(rs != null){
//                    while(rs.next()){
                rs.next();
                return rs.getBoolean("regle_paiement");
//                }else{
//                    System.out.println("User unkonwn");
//                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static float getMontantApayer(int idClient){
        if(isConnected()){

            try {
                String request = "Select montant_total from reservation where id_client='"+idClient+"'";
                Statement stmt= conn.createStatement();
                ResultSet rs = stmt.executeQuery(request);
//                if(rs != null){
//                    while(rs.next()){
                rs.next();
                return rs.getFloat("montant_total");
//                }else{
//                    System.out.println("User unkonwn");
//                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public static String getTypeHebergement(int idReserv){
        if(isConnected()){

            try {
                String request = "Select id_hebergement from reservation where id_client='"+idReserv+"'";
                Statement stmt= conn.createStatement();
                ResultSet rs = stmt.executeQuery(request);
//                if(rs != null){
//                    while(rs.next()){
//                rs.next();
//                return rs.getString("");
                rs.next();
                int idHebergement = rs.getInt("id_hebergement");
                request = "Select type from hebergement where id ='"+idHebergement+"'";
                stmt = conn.createStatement();
                rs = stmt.executeQuery(request);
                rs.next();
                return rs.getString("type");
//                }else{
//                    System.out.println("User unkonwn");
//                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static int getNombrePersonne(int idClient){
        if(isConnected()){

            try {
                String request = "Select id_hebergement from reservation where id_client='"+idClient+"'";
                Statement stmt= conn.createStatement();
                ResultSet rs = stmt.executeQuery(request);
//                if(rs != null){
//                    while(rs.next()){
//                rs.next();
//                return rs.getString("");
                rs.next();
                int idHebergement = rs.getInt("id_hebergement");
                request = "Select nbr_personne from hebergement where id ='"+idHebergement+"'";
                stmt = conn.createStatement();
                rs = stmt.executeQuery(request);
                rs.next();
                return rs.getInt("nbr_personne");
//                }else{
//                    System.out.println("User unkonwn");
//                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }
    public static String getReservationRef(int idClient){
        if(isConnected()){

            try {
                String request = "Select ref_facture from facture where id_client='"+idClient+"'";
                Statement stmt= conn.createStatement();
                ResultSet rs = stmt.executeQuery(request);
//                if(rs != null){
//                    while(rs.next()){
                rs.next();
                return rs.getString("ref_facture");
//                }else{
//                    System.out.println("User unkonwn");
//                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    public static void selectClientStateReservation(){

    }



}
