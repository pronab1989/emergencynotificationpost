//package test;
//
//import io.restassured.RestAssured;
//import io.restassured.http.ContentType;
//import org.testng.annotations.Test;
//
//import static io.restassured.RestAssured.given;
//
//public class AppTest {
//
//    @Test
//    public void testemergencynotification() {
//
//        RestAssured.baseURI = "http://localhost:8080/newdrm/rest/iptv/tpCmdManager/newCommand";
//
//        String jsonBody = "{\n" +
//                "    \"postJson\": {\n" +
//                "        \"subscriberCodes\": [\"SUB1\",\"SUB2\"],\n" +
//                "        \"command\": \"EMERGENCY_NOTIFICATION\",\n" +
//                "        \"dataMap\": {\n" +
//                "            \"duration\": \"2\",\n" +
//                "            \"fontType\": \"Casual\",\n" +
//                "            \"bgColor\": \"#00FFFF\",\n" +
//                "            \"userCanCloseMessage\": \"True\",\n" +
//                "            \"fontSize\": \"10\",\n" +
//                "            \"position\": \"3,5\",\n" +
//                "            \"message\": \"Subscriber Emergency NOtification\",\n" +
//                "            \"fontColor\": \"#00FFFF\"\n" +
//                "        },\n" +
//                "        \"expiryDate\": \"01-05-2025 12:10:23\",\n" +
//                "        \"module\": \"DRM\"\n" +
//                "    },\n" +
//                "    \"isScheduled\": false,\n" +
//                "    \"startDate\": null,\n" +
//                "    \"endDate\": null,\n" +
//                "    \"intervalInMinutes\": 0\n" +
//                "}";
//
//
//        given()
//            .basePath("http://localhost:8080/newdrm/rest/iptv/tpCmdManager/newCommand")
//            .header("username", "admin")
//            .header("apikey", "0276d666-3593-40ae-b2fb-18deb8c54255")
//            .contentType("application/JSON")
//            .body(jsonBody)
//        .when()
//            .post()
//        .then()
//            .statusCode(202);
//    }
//}


//package test;
//
//import io.restassured.RestAssured;
//import io.restassured.http.ContentType;
//import org.testng.annotations.Test;
//
//import static io.restassured.RestAssured.given;
//
//public class AppTest {
//
//    @Test
//    public void testemergencynotification() {
//
//        RestAssured.baseURI = "http://103.252.170.213:8080/newdrm/rest/iptv/tpCmdManager/newCommand";
//
//        String jsonBody = "{\n" +
//                "    \"postJson\": {\n" +
//                "        \"subscriberCodes\": [\"SUB1\",\"SUB2\"],\n" +
//                "        \"command\": \"EMERGENCY_NOTIFICATION\",\n" +
//                "        \"dataMap\": {\n" +
//                "            \"duration\": \"2\",\n" +
//                "            \"fontType\": \"Casual\",\n" +
//                "            \"bgColor\": \"#00FFFF\",\n" +
//                "            \"userCanCloseMessage\": \"True\",\n" +
//                "            \"fontSize\": \"10\",\n" +
//                "            \"position\": \"3,5\",\n" +
//                "            \"message\": \"Subscriber Emergency NOtification\",\n" +
//                "            \"fontColor\": \"#00FFFF\"\n" +
//                "        },\n" +
//                "        \"expiryDate\": \"01-05-2025 12:10:23\",\n" +
//                "        \"module\": \"DRM\"\n" +
//                "    },\n" +
//                "    \"isScheduled\": false,\n" +
//                "    \"startDate\": null,\n" +
//                "    \"endDate\": null,\n" +
//                "    \"intervalInMinutes\": 0\n" +
//                "}";
//
//        given()
//            .basePath("http://103.252.170.213:8080/newdrm/rest/iptv/tpCmdManager/newCommand")  // ✅ Just the path
//            .header("username", "admin")
//            .header("apikey", "0276d666-3593-40ae-b2fb-18deb8c54255")
//            .contentType(ContentType.JSON) // ✅ Use RestAssured constant or lowercase
//            .body(jsonBody)
//        .when()
//            .post()
//        .then()
//            .log().ifValidationFails() // ✅ Helps log full response on failure
//            .statusCode(202); // Or 200 depending on API
//    }
//}


package test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class AppTest {

    @Test
    public void testEmergencyNotificationWithCrumb() {

        // Base URL
        RestAssured.baseURI = "http://103.252.170.213:8080";

        // Step 1: Get CSRF crumb
        Response crumbResponse = given()
            .auth().preemptive().basic("admin", "admin@123") // Replace with real creds
            .when()
            .get("/crumbIssuer/api/json");

        String crumb = crumbResponse.jsonPath().getString("crumb");
        String crumbField = crumbResponse.jsonPath().getString("crumbRequestField");

        System.out.println("Crumb Field: " + crumbField + ", Crumb: " + crumb);

        // Step 2: Prepare JSON payload
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

        // Step 3: Send POST request with crumb
        given()
            .header("username", "admin")
            .header("apikey", "0276d666-3593-40ae-b2fb-18deb8c54255")
            .header(crumbField, crumb) // CSRF crumb
            .contentType(ContentType.JSON)
            .body(jsonBody)
        .when()
            .post("/newdrm/rest/iptv/tpCmdManager/newCommand")
        .then()
            .log().all()
            .statusCode(202); // or 200 depending on your backend
    }
}

