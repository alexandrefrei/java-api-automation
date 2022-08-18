package Util;

import io.restassured.RestAssured;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;



public class RestUtil
{
    public static String path;

    public static void SetBaseURI (String baseURI)
    {
        RestAssured.baseURI = baseURI;
    }

    public static void ResetBaseURI ()
    {
        RestAssured.baseURI = null;
    }

    public static JsonPath GetJsonPath (Response res)
    {
        String json = res.asString();
        return new JsonPath(json);
    }


}
