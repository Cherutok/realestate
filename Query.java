package realestate;

import java.sql.*;
import java.util.Objects;

public class Query{
    Statement stmt = null;
    Connection conn;
    public Query(Connection newConnection){
        conn = newConnection;
    }

    public boolean newHouse(String[] values)
    {
        String query;
        query = "INSERT INTO HOUSES (HOUSE_ID,HOUSE_NAME,LOCATION,TYPE,ROOMS,PRICE)";
        query+="VALUES(" +values[0]+ ",'" +values[1]+ "','"+values[2]+"','"+values[3]+"','"+values[4]+"','" +values[5]+ "')";
        System.out.println(query);
        return(createStatement(query));

    }

    public boolean createStatement(String query){
        try{
            stmt = conn.createStatement();
            if(stmt.executeUpdate(query) == 1){
                return true;
            }
            stmt.close();

        }catch(SQLException e){
            System.out.println(e.getMessage());

        }
        return  false;
    }

    public ResultSet fetchData(String table,String field){
        String query = "SELECT "+ field +" FROM "+table.toUpperCase()+"";
        System.out.println(query);
        ResultSet rs =null;
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return rs;
    }
    public ResultSet fetchData(String table,String field,String condition){
        String query = "SELECT "+ field +" FROM "+table.toUpperCase()+" WHERE "+condition;
        System.out.println(query);
        ResultSet rs =null;
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return rs;
    }

    public Boolean userLogin(String[] values){
        if(values[0].length()<2||values[1].length()<2){
            values[0] = "1000";
            values[1] = "nill";
        }
        String query = "SELECT * FROM USERS WHERE ID="+values[0]+"";
        System.out.println(query);
        ResultSet rs =null;
        String inputPass = values[1];
        String password="";
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        try{
            while(rs.next()) {
                password = rs.getString(3);
            }
        }catch(SQLException e){System.out.println(e.getMessage());}

        System.out.println(password);
        return(Objects.equals(inputPass,password));

    }




    public int getTableSize(String table){
        String primaryKey;
        
        if(table == "houses") 
        {
            primaryKey = "House_ID";
        }
        else
        {
            primaryKey = "User_ID";
        }

        String query = "SELECT COUNT("+ primaryKey +") FROM "+table.toUpperCase()+"";
        System.out.println(query);
        ResultSet rs = null;
        int count =0;
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            //count =  rs.getInt(1);
            while (rs.next()){
                count = rs.getInt(1);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return  count;
    }
}

