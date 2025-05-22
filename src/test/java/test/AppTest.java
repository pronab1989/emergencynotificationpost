package test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AppTest {

    @Test
    public void emergencynotification() {

        RestAssured.baseURI = "http://localhost:8080/newdrm/rest/iptv/tpCmdManager/newCommand";

        String jsonBody = "{\n" +
                "    \"postJson\": {\n" +
                "        \"subscriberCodes\": [\"SUB1\",\"SUB2\"],\n" +
                "        \"command\": \"EMERGENCY_NOTIFICATION\",\n" +
                "        \"dataMap\": {\n" +
                "            \"duration\": \"2\",\n" +
                "            \"fontType\": \"Casual\",\n" +
                "            \"bgColor\": \"#00FFFF\",\n" +
                "            \"userCanCloseMessage\": \"True\",\n" +
                "            \"fontSize\": \"10\",\n" +
                "            \"position\": \"3,5\",\n" +
                "            \"message\": \"Subscriber Emergency NOtification\",\n" +
                "            \"fontColor\": \"#00FFFF\"\n" +
                "        },\n" +
                "        \"expiryDate\": \"01-05-2025 12:10:23\",\n" +
                "        \"module\": \"DRM\"\n" +
                "    },\n" +
                "    \"isScheduled\": false,\n" +
                "    \"startDate\": null,\n" +
                "    \"endDate\": null,\n" +
                "    \"intervalInMinutes\": 0\n" +
                "}";


        given()
            .basePath("http://localhost:8080/newdrm/rest/iptv/tpCmdManager/newCommand")
            .header("username", "admin")
            .header("apikey", "0276d666-3593-40ae-b2fb-18deb8c54255")
            .contentType("application/JSON")
            .body(jsonBody)
        .when()
            .post()
        .then()
            .statusCode(200);
    }
}
