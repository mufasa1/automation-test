import io.restassured.RestAssured;
import io.restassured.http.Header;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.Format;
import java.util.Formatter;

public class TestClientIDs {

    public final String domain_prefix = "https://lips.lisqa7.sb.karmalab.net/LISService.svc";


    @Test
    public void smoke() {
        RestAssured.when().get("https://www.baidu.com").then().statusCode(200);
    }

    @Test(dataProvider = "getClientID")

    public void PropertySettingGet(int clientID) {
        System.out.println(clientID);
        int houtelID = getValidHotelID();
        String propertySettingGetURL = String.format("/lips/V1/hotels/%s/propertysettings",houtelID);
        Header head = new Header("Client-ID","1");
        RestAssured.given().header(head).get(domain_prefix + propertySettingGetURL).then().statusCode(200);
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
        String sql = "select LIMPartitionID from dbo.skuGroupLIMPartition where skuGroupID=" + hotelID;
        String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String LSM_url = "jdbc:sqlserver://LodgingSystemManagement.db.LISQA7.sb.karmalab.net;DatabaseName=LodgingSystemManagement";
        String LSM_userName = "TravSysManagement";
        String LSM_password = "travel";
        try{
            Class.forName(driverName);
            Connection connection = DriverManager.getConnection(LSM_url,LSM_userName,LSM_password);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                if(resultSet.getInt("LIMPartitionID") !=0) {
                    return true;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    private int getHotelIDFromLim() {
        int hotelID = 0;
        String sql = "select top 1 skuGroupID from dbo.skugroup where ActiveStatusTypeID in (2,3) order by newID()";
        String driveName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String lim_url = "jdbc:sqlserver://lodginginventorytx1.db.LISQA7.sb.karmalab.net;DatabaseName=LodgingInventoryMaster";
        String lim_userName = "TravInventory";
        String lim_password = "travel";
        try{
            Class.forName(driveName);
            Connection dbConnect = DriverManager.getConnection(lim_url,lim_userName,lim_password);
            PreparedStatement statement = dbConnect.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                if(resultSet != null && resultSet.getInt("skuGroupID") != 0){
                    hotelID = resultSet.getInt("skuGroupID");
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return hotelID;
    }


    @DataProvider(name = "getClientID")
    public Object[][] getClienIDs(){
        return new Object[][]{
                {1}
        };
    }


}
