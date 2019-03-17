import com.jayway.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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
        RestAssured.when().get(domain_prefix + port).then().statusCode(200);
    }

    @DataProvider(name = "getClientID")
    public Object[][] getClienIDs(){
        return new Object[][]{
                {1},
                {2}
        };
    }

}
