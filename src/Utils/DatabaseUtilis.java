package Utils;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseUtilis {
    private static Connection conn = null;
    private static String url = "jdbc:mysql://localhost:3306/clubestival";
    private static String login = "root";
    private static String password = "";
    public static void connection() {
        try {
            conn = DriverManager.getConnection(url,login,password );
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    public static int getCountClient(){
        if(isConnected()){

            try {
                String request = "Select count(1) from client ";
                PreparedStatement stmt= conn.prepareStatement(request);
                ResultSet rs = stmt.executeQuery();
                if(rs != null){
                    while(rs.next()){
                        return rs.getInt(0);
                    }
                }else{
                    System.out.println("User unkonwn");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }


    public static boolean getReservationState(int idReserv){
        if(isConnected()){

            try {
                String request = "Select regle_paiement from reservation where Ref_reservation='"+idReserv+"'";
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

    public static float getMontantApayer(int idReserv){
        if(isConnected()){

            try {
                String request = "Select montant_total from reservation where Ref_reservation='"+idReserv+"'";
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

    public static void selectClientStateReservation(){

    }


}
