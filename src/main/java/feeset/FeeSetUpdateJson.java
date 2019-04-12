package feeset;

import static io.github.sskorol.data.TestDataReader.use;
import static io.restassured.RestAssured.given;
import static io.restassured.config.EncoderConfig.encoderConfig;
import static io.restassured.config.RestAssuredConfig.config;
import static org.hamcrest.Matchers.is;

import com.alibaba.fastjson.JSON;
import io.github.sskorol.core.DataSupplier;
import io.github.sskorol.data.CsvReader;
import io.restassured.RestAssured;
import io.restassured.RestAssured.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.matcher.RestAssuredMatchers.*;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers.*;
import io.restassured.module.jsv.JsonSchemaValidator.*;
import org.testng.annotations.Test;

import javax.print.DocFlavor;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class FeeSetUpdateJson {
    @Test
    public void test() {
        FeeSetUpdateEntity requestBody = new FeeSetUpdateEntity();
        requestBody.setFeeSetID(200811501);
        requestBody.setFeeSetInternalName("extra person fee HC");
        requestBody.setExtraPersonFees( new ArrayList<ExtraPersonFee>());
        requestBody.setPerPersonServiceCharges(new ArrayList<FeeSetUpdateEntity.PerPersonServiceCharge>());
        requestBody.setPerStayServiceCharges(new ArrayList<FeeSetUpdateEntity.PerStayServiceCharge>());
        given().
                config(config().encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false))).
                contentType(ContentType.JSON).
                header("user-ID","33").
                header("client-ID",2).
                body(requestBody).
        when().log().all().
                put("http://chelliappqa701:21234/prs/V1/hotels/759/feesets/200811501")
        .then().
                log().all().
                statusCode(200).log().all();
    }

//    @DataSupplier
//    public StreamEX<String> getFeesetName(){
//        return use(CsvReader.class).withTarget(String.class).withSource("users.csv").read();
//    }


}