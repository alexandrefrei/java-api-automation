package Tests;

import Setup.SetupBase;
import Util.RestUtil;
import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import io.restassured.path.xml.XmlPath;

public class OpenWeatherTests extends SetupBase
{

    @Test
    public void SearchCityByName()
    {
        /* The purpose of this test is validate if sending the city name, the API can search and return the correct city*/

        String city = "London";
        //Sending the Request
        Response response = given()
                    .param("q", city)
                    .param("appid", appId)
                    .when()
                    .get("/weather");


        JsonPath jp = RestUtil.GetJsonPath(response);

        Assert.assertEquals(response.getStatusCode(),200,"Status Code");
        Assert.assertEquals( jp.get("name"),city,"City Name");
    }

    @Test
    public void SearchCityById()
    {
        /* The purpose of this test is validate if sending the city Id, the API can search and return the correct city
        * And also returning the message in the XML Format and performing validation against XML
        * */

        String cityId = "2643743";

        given()
              .param("q", cityId)
              .param("appid", appId)
              .param("mode", "xml")
              .when()
              .get("/weather")
              .then()
              .statusCode(200)
              .contentType(ContentType.XML)
              .assertThat()
              .body("current.city.@id",equalTo("2643743"))
              .body("current.city.@name",equalTo("London"));
    }

    @Test
    public void WithoutAppidKey()
    {
        /* The purpose of this test is validate if the message error is displayed correctly when is not sent the appid */

        String city = "London";
        //Sending the Request
        Response response = given()
                            .param("q", city)
                            .when()
                            .get("/weather");

        JsonPath jp = RestUtil.GetJsonPath(response);

        Assert.assertEquals(jp.get("cod"),401, "Code");
        Assert.assertEquals( jp.get("message"), "Invalid API key. Please see http://openweathermap.org/faq#error401 for more info.","Message Error");
    }

}
