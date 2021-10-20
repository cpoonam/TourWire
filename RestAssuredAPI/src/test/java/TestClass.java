import org.testng.Assert;
import org.testng.annotations.Test;

import client.RestClient1;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TestClass {
	String endpoint="https://dev.tourwire.com/dev/v1/customer/payment/deposit";
	String token="eyJraWQiOiJlbkt6bFR5TWxIS3VBWkxMZFNNSU52WGM4Y1k4MFRVbUphVUlaS0Z5VnlNPSIsImFsZyI6IlJTMjU2In0.eyJzdWIiOiI4Mjc0NGNjNS01MmI2LTQ0YTItYjQzNi0wMzE0Y2FkNTkxMDkiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsImlzcyI6Imh0dHBzOlwvXC9jb2duaXRvLWlkcC51cy13ZXN0LTIuYW1hem9uYXdzLmNvbVwvdXMtd2VzdC0yX3NGUmN0UGVEcSIsImNvZ25pdG86dXNlcm5hbWUiOiI4Mjc0NGNjNS01MmI2LTQ0YTItYjQzNi0wMzE0Y2FkNTkxMDkiLCJnaXZlbl9uYW1lIjoicG9vbmFtIiwiY3VzdG9tOnVzZXJUeXBlIjoiRkFOIiwiYXVkIjoiMnU1aDl0NWU5cjQybGFqOWtrY29ldnJobW8iLCJldmVudF9pZCI6IjcwOTk0MDYzLWQ0M2MtNDI3Yy1iOTY2LTEyMWQ5YTgwNWUwNyIsInVwZGF0ZWRfYXQiOjE2MzI5OTkzNTE3NDMsInRva2VuX3VzZSI6ImlkIiwiYXV0aF90aW1lIjoxNjM0NjY0OTI4LCJleHAiOjE2MzQ2Njg1MjgsImlhdCI6MTYzNDY2NDkyOCwiZmFtaWx5X25hbWUiOiJjIiwiZW1haWwiOiJtZ2NsbmJ2akBncnIubGEifQ.UM36M2OseImCHANFrQ_3ycVjv037wwIZkMxIY_fufIHRF3cVlEvp5fzdpAQMWrir9dK8di0yw5-LE8fFfZs8yPgIUP0rRdapMNcOT22-QxT-eUysmbnezBnxeGPqc5Ctt5-qe2aiXKOQajsgKYFL6w_hrTgexjb7COWxtXYUwHgty9rEk3P3-Y4-tDLb5fhMZgcH8SBsKB1W5ZWnE-dPd1Moth1xDZR6f7u-IH-ztINQHAvzVEakabTN2I7PlUsVx0cCmM8hRoGmCrfHqowU-Fqi5IfrNRHEjtcWKRxiH6SeziNi9gzIm6z4sqEdRKbNY6cOnUiQxJ0hXb3VGqjg2A";
	String body = "{\n" +
            "    \"nonce\": \"cnon:card-nonce-ok\",\n" +
            "    \"given_name\": \"5 feb\",\n" +
            "    \"family_name\": \"fan\",\n" +
            "    \"email\": \"poonamit45@gmail.com\",\n" +
            "    \"ticket_quantity\": 2,\n" +
            "    \"eventId\": \"61604f2f3f01526bf2a2afe7\",\n" +
            "    \"ticketSlabId\": \"61604f2f3f01526bf2a2afe5\"\n" +
            "}";
	@Test
public void test() {
	RestClient1 rest=new RestClient1();
	
	Response response=rest.httpPost(endpoint, token, body);
	 System.out.println(response.getStatusCode());
     Assert.assertEquals(200,response.getStatusCode()); //assertion for status code
     String responseAsString = response.asString();
     System.out.println("Response as String=>>>>> "+responseAsString);
     JsonPath jsonPath = JsonPath.from(responseAsString); //JsonPath for extraction
     int amoutPaid = jsonPath.get("amount_paid"); //getting amount paid from response
     String paymentID = jsonPath.get("payment_id"); //getting payment id from response
     String currency = jsonPath.get("currency"); //getting currency from response
     String msg = jsonPath.get("message"); //getting message from response

     System.out.println(amoutPaid);
     Assert.assertEquals(2240,amoutPaid);
     System.out.println(paymentID);
     Assert.assertNotEquals(null,paymentID);
     System.out.println(currency);
     Assert.assertEquals("CAD",currency);
     System.out.println(msg);
     Assert.assertEquals("Deposit processed",msg);
 
}
}
