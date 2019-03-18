import com.jayway.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TestClientIDs {

    public final String domain_prefix = "https://chelliappqa701:";

    public final String port = "8090";

    @Test
    public void smoke() {
        RestAssured.when().get("https://www.baidu.com").then().statusCode(200);
    }

    @Test(dataProvider = "getClientID")

    public void PropertySettingGet(int clientID) {
        System.out.println(clientID);
        int houtelID = getValidHotelID();
        RestAssured.when().get(domain_prefix + port).then().statusCode(200);
    }

    private int getValidHotelID() {
        int hotelID = getHotelIDFromLim();
        if(hotelID != 0){
            if(! validateHotelIDInLSM(hotelID)){
                hotelID =  getValidHotelID();
            }
        }
        return hotelID;
    }
    private boolean validateHotelIDInLSM(int hotelID) {
        String sql = "select limID from dbo.skuGroupLIMPartition where hotelID=" + hotelID;
        String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String LSM_url = "";
        String LSM_userName = "travel";
        String LSM_password = "travel";
        try{
            Class.forName(driverName);
            Connection connection = DriverManager.getConnection(LSM_url,LSM_userName,LSM_password);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery(sql);
            if(resultSet.getInt("limID") !=0){
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    private static int getHotelIDFromLim() {
        int hotelID = 0;
        String sql = "select top 1 skuGroupID from dbo.skugroup where activeStatus in (2,3) order by newID()";
        String driveName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String lim_url = "jdbc:sqlserver://lodginginventorytx1.db.LISQA7.sb.karmalab.net.LodgingInventoryMaster";
        String lim_userName = "TravInventory";
        String lim_password = "travel";
        try{
            Class.forName(driveName);
            Connection dbConnect = DriverManager.getConnection(lim_url,lim_userName,lim_password);
            PreparedStatement statement = dbConnect.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.getInt("skuGroupID") != 0){
                hotelID = resultSet.getInt("skuGroupID");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return hotelID;
    }


    @DataProvider(name = "getClientID")
    public Object[][] getClienIDs(){
        return new Object[][]{
                {1},
                {2}
        };
    }

    public static void main(String[] args){
        int hotelID = getHotelIDFromLim();
        System.out.println(hotelID);
    }

}
