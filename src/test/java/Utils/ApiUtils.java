package Utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;

import static Hooks.Api.spec;
import static io.restassured.RestAssured.given;

public class ApiUtils  {
    static JSONObject reqBody;
    public static String generateToken(){

        spec.pathParams("pp1","api","pp2","getToken");

        reqBody=new JSONObject();
        reqBody.put("email", ConfigReader.getProperty("email"));
        reqBody.put("password", ConfigReader.getProperty("password"));

        Response response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                // .header("Accept","application/json")
                .when()
                .body(reqBody.toString())
                .post("/{pp1}/{pp2}");

        JsonPath repJP=response.jsonPath();

        String token=repJP.getString("token");

        return token;
    }
}
