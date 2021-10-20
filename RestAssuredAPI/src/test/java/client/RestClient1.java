package client;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestClient1 {
	public Response httpPost(String endpoint, String token, String body) {
		RequestSpecification request = RestAssured.given(); //setup happening for httpclient
	    request.header("Authorization",token); //adding header
	    request.header("Content-Type","application/json"); //adding header
	    request.body(body); //attaching body to the request
	    Response response =request.post(endpoint); //hitting the endpoint
	    return response;
	}
	

}
