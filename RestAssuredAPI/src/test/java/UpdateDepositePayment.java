import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.gherkin.model.Given;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UpdateDepositePayment {
	String token = "eyJraWQiOiJlbkt6bFR5TWxIS3VBWkxMZFNNSU52WGM4Y1k4MFRVbUphVUlaS0Z5VnlNPSIsImFsZyI6IlJTMjU2In0.eyJzdWIiOiI4Mjc0NGNjNS01MmI2LTQ0YTItYjQzNi0wMzE0Y2FkNTkxMDkiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsImlzcyI6Imh0dHBzOlwvXC9jb2duaXRvLWlkcC51cy13ZXN0LTIuYW1hem9uYXdzLmNvbVwvdXMtd2VzdC0yX3NGUmN0UGVEcSIsImNvZ25pdG86dXNlcm5hbWUiOiI4Mjc0NGNjNS01MmI2LTQ0YTItYjQzNi0wMzE0Y2FkNTkxMDkiLCJnaXZlbl9uYW1lIjoicG9vbmFtIiwiY3VzdG9tOnVzZXJUeXBlIjoiRkFOIiwiYXVkIjoiMnU1aDl0NWU5cjQybGFqOWtrY29ldnJobW8iLCJldmVudF9pZCI6IjMwMzljMzllLTM3YzgtNGFkYS04MzU0LTZmNzkxNDFmODU5NiIsInVwZGF0ZWRfYXQiOjE2MzI5OTkzNTE3NDMsInRva2VuX3VzZSI6ImlkIiwiYXV0aF90aW1lIjoxNjM0NzMxMjYxLCJleHAiOjE2MzQ3MzQ4NjEsImlhdCI6MTYzNDczMTI2MSwiZmFtaWx5X25hbWUiOiJjIiwiZW1haWwiOiJtZ2NsbmJ2akBncnIubGEifQ.tjgO6Q3zWMtsC4buOxO5c5lQzKzLRYHBccJvOkd7iDmSHNT_LxFMB5nIFZgorCTdwe8F2mZ8CH28b39ePc20btSSeniFhbOmlZZprtXpzasYkBJNpQmWJgB9JIEyDLf6A6wrBoBiKhUZswt9iosTRGsgffz5z_iUFfwgBMvcNxE9gZsS8s8TRB83OK45hYjFN5NXndpm8ETDKcW7VD3q_uNN2nsLpw3PeHunCyUmj0TFcCTf1z65y1Jz8ODcn8ctgqbxh8u3Smsx5PZIqWeu6_Ho6fF-yb4HWADhkxBj8WsPxSuLYWw8dMpN5emaSNiAKzX8M0vF0osSa44Lv_FaTw";
    String endpoint = "https://dev.tourwire.com/dev/v1/customer/payment/deposit"; //Storing endpoint url in a string
	
	@Test
	    public void testHappyPath(){
        
        RequestSpecification request = RestAssured.given(); //setup happening for httpclient
        request.header("Authorization",token); //adding header
        request.header("Content-Type","application/json"); //adding header
        //request body below
        String str = "{\n" +
                "    \"nonce\": \"cnon:card-nonce-ok\",\n" +
                "    \"given_name\": \"5 feb\",\n" +
                "    \"family_name\": \"fan\",\n" +
                "    \"email\": \"poonamit45@gmail.com\",\n" +
                "    \"ticket_quantity\": 2,\n" +
                "    \"eventId\": \"61604f2f3f01526bf2a2afe7\",\n" +
                "    \"ticketSlabId\": \"61604f2f3f01526bf2a2afe5\"\n" +
                "}";
        request.body(str); //attaching body to the request
        Response response =request.post(endpoint); //hitting the endpoint
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
	@Test
    public void testInvalidNonce(){
       RequestSpecification request = RestAssured.given(); //setup happening for httpclient
    request.header("Authorization",token); //adding header
    request.header("Content-Type","application/json"); //adding header
    //request body below
    String str = "{\n" +
            "    \"nonce\": \"  \",\n" +
            "    \"given_name\": \"5 feb\",\n" +
            "    \"family_name\": \"fan\",\n" +
            "    \"email\": \"poonamit45@gmail.com\",\n" +
            "    \"ticket_quantity\": 2,\n" +
            "    \"eventId\": \"61604f2f3f01526bf2a2afe7\",\n" +
            "    \"ticketSlabId\": \"61604f2f3f01526bf2a2afe5\"\n" +
            "}";
    request.body(str); //attaching body to the request
    Response response =request.post(endpoint); //hitting the endpoint
    System.out.println(response.getStatusCode());
    Assert.assertEquals(450,response.getStatusCode()); //assertion for status code
    String responseAsString = response.asString();
    System.out.println("Response as String=>>>>> "+responseAsString);
    JsonPath jsonPath = JsonPath.from(responseAsString); //JsonPath for extraction
    String message=jsonPath.get("error.message");
    System.out.println(message);
}
	@Test
    public void testInvalidEmail(){
    
    RequestSpecification request = RestAssured.given(); //setup happening for httpclient
    request.header("Authorization",token); //adding header
    request.header("Content-Type","application/json"); //adding header
    //request body below
    String str = "{\n" +
            "    \"nonce\": \"cnon:card-nonce-ok\",\n" +
            "    \"given_name\": \"5 feb\",\n" +
            "    \"family_name\": \"fan\",\n" +
            "    \"email\": \"poonamit45gmail.com\",\n" +
            "    \"ticket_quantity\": 2,\n" +
            "    \"eventId\": \"61604f2f3f01526bf2a2afe7\",\n" +
            "    \"ticketSlabId\": \"61604f2f3f01526bf2a2afe5\"\n" +
            "}";
    request.body(str); //attaching body to the request
    Response response =request.post(endpoint); //hitting the endpoint
    System.out.println(response.getStatusCode());
    Assert.assertEquals(454,response.getStatusCode()); //assertion for status code
    String responseAsString = response.asString();
    System.out.println("Response as String=>>>>> "+responseAsString);
   
}
	@Test
    public void testInvalidQuantity(){
      RequestSpecification request = RestAssured.given(); //setup happening for httpclient
    request.header("Authorization",token); //adding header
    request.header("Content-Type","application/json"); //adding header
    //request body below
    String str = "{\n" +
            "    \"nonce\": \"cnon:card-nonce-ok\",\n" +
            "    \"given_name\": \"5 feb\",\n" +
            "    \"family_name\": \"fan\",\n" +
            "    \"email\": \"poonamit45@gmail.com\",\n" +
            "    \"ticket_quantity\":-1,\n" +
            "    \"eventId\": \"61604f2f3f01526bf2a2afe7\",\n" +
            "    \"ticketSlabId\": \"61604f2f3f01526bf2a2afe5\"\n" +
            "}";
    request.body(str); //attaching body to the request
    Response response =request.post(endpoint); //hitting the endpoint
    System.out.println(response.getStatusCode());
    Assert.assertEquals(454,response.getStatusCode()); //assertion for status code
    String responseAsString = response.asString();
    System.out.println("Response as String=>>>>> "+responseAsString);
   
}

@Test
public void testInvalidgivenName(){
  RequestSpecification request = RestAssured.given(); //setup happening for httpclient
request.header("Authorization",token); //adding header
request.header("Content-Type","application/json"); //adding header
//request body below
String str = "{\n" +
        "    \"nonce\": \"cnon:card-nonce-ok1\",\n" +
       "    \"given_name\": \"\",\n" +
        "    \"family_name\": \"fan\",\n" +
        "    \"email\": \"poonamit45@gmail.com\",\n" +
        "    \"ticket_quantity\": 10,\n" +
        "    \"eventId\": \"61604f2f3f01526bf2a2afe7\",\n" +
        "    \"ticketSlabId\": \"61604f2f3f01526bf2a2afe5\"\n" +
        "}";
request.body(str); //attaching body to the request
Response response =request.post(endpoint); //hitting the endpoint
System.out.println(response.getStatusCode());
Assert.assertEquals(454,response.getStatusCode()); //assertion for status code
String responseAsString = response.asString();
System.out.println("Response as String=>>>>> "+responseAsString);

}
@Test
public void testInvalidFamilyName(){
  RequestSpecification request = RestAssured.given(); //setup happening for httpclient
request.header("Authorization",token); //adding header
request.header("Content-Type","application/json"); //adding header
//request body below
String str = "{\n" +
        "    \"nonce\": \"cnon:card-nonce-ok\",\n" +
        "    \"given_name\": \"5_+++\",\n" +
        "    \"family_name\": \"\",\n" +
        "    \"email\": \"poonamit45@gmail.com\",\n" +
        "    \"ticket_quantity\": 10,\n" +
        "    \"eventId\": \"61604f2f3f01526bf2a2afe7\",\n" +
        "    \"ticketSlabId\": \"61604f2f3f01526bf2a2afe5\"\n" +
        "}";
request.body(str); //attaching body to the request
Response response =request.post(endpoint); //hitting the endpoint
System.out.println(response.getStatusCode());
Assert.assertEquals(454,response.getStatusCode()); //assertion for status code
String responseAsString = response.asString();
System.out.println("Response as String=>>>>> "+responseAsString);

}
@Test
public void testticketslab(){

RequestSpecification request = RestAssured.given(); //setup happening for httpclient
request.header("Authorization",token); //adding header
request.header("Content-Type","application/json"); //adding header
//request body below
String str = "{\n" +
        "    \"nonce\": \"cnon:card-nonce-ok\",\n" +
        "    \"given_name\": \"5 feb\",\n" +
        "    \"family_name\": \"fan\",\n" +
        "    \"email\": \"poonamit45@gmail.com\",\n" +
        "    \"ticket_quantity\": 2,\n" +
        "    \"eventId\": \"61604f2f3f01526bf2a2afe7\",\n" +
        "    \"ticketSlabId\": \"61604f2f3f01526bf2a2afea\"\n" +
        "}";
request.body(str); //attaching body to the request
Response response =request.post(endpoint); //hitting the endpoint
System.out.println(response.getStatusCode());
Assert.assertEquals(453,response.getStatusCode()); //assertion for status code
String responseAsString = response.asString();
System.out.println("Response as String=>>>>> "+responseAsString);

}
@Test
public void testeventExist(){

RequestSpecification request = RestAssured.given(); //setup happening for httpclient
request.header("Authorization",token); //adding header
request.header("Content-Type","application/json"); //adding header
//request body below
String str = "{\n" +
        "    \"nonce\": \"cnon:card-nonce-ok\",\n" +
        "    \"given_name\": \"5 feb\",\n" +
        "    \"family_name\": \"fan\",\n" +
        "    \"email\": \"poonamit45@gmail.com\",\n" +
        "    \"ticket_quantity\": 2,\n" +
        "    \"eventId\": \"61604f2f3f01526bf2a2afe0\",\n" +
        "    \"ticketSlabId\": \"61604f2f3f01526bf2a2afe5\"\n" +
        "}";
request.body(str); //attaching body to the request
Response response =request.post(endpoint); //hitting the endpoint
System.out.println(response.getStatusCode());
//Assert.assertEquals(200,response.getStatusCode()); //assertion for status code
String responseAsString = response.asString();
System.out.println("Response as String=>>>>> "+responseAsString);
JsonPath jsonPath = JsonPath.from(responseAsString); //JsonPath for extraction
int amoutPaid = jsonPath.get("amount_paid"); //getting amount paid from response
String paymentID = jsonPath.get("payment_id"); //getting payment id from response
String currency = jsonPath.get("currency"); //getting currency from response
String msg = jsonPath.get("message"); //getting message from response
}

}

