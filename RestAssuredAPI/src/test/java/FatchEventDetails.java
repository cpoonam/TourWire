import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class FatchEventDetails {
	@Test
	public void fetchdetails() {
		RestAssured.baseURI="https://dev.tourwire.com/dev/v1/artists/events/fetcheventdetails";
		RequestSpecification request= RestAssured.given().header("Authorization","eyJraWQiOiJlbkt6bFR5TWxIS3VBWkxMZFNNSU52WGM4Y1k4MFRVbUphVUlaS0Z5VnlNPSIsImFsZyI6IlJTMjU2In0.eyJzdWIiOiI4Mjc0NGNjNS01MmI2LTQ0YTItYjQzNi0wMzE0Y2FkNTkxMDkiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsImlzcyI6Imh0dHBzOlwvXC9jb2duaXRvLWlkcC51cy13ZXN0LTIuYW1hem9uYXdzLmNvbVwvdXMtd2VzdC0yX3NGUmN0UGVEcSIsImNvZ25pdG86dXNlcm5hbWUiOiI4Mjc0NGNjNS01MmI2LTQ0YTItYjQzNi0wMzE0Y2FkNTkxMDkiLCJnaXZlbl9uYW1lIjoicG9vbmFtIiwiY3VzdG9tOnVzZXJUeXBlIjoiRkFOIiwiYXVkIjoiMnU1aDl0NWU5cjQybGFqOWtrY29ldnJobW8iLCJldmVudF9pZCI6IjYyMzg2MTMyLTczOTMtNDBlYi04ZWY3LTUzMWQyYzllODUxYiIsInVwZGF0ZWRfYXQiOjE2MzI5OTkzNTE3NDMsInRva2VuX3VzZSI6ImlkIiwiYXV0aF90aW1lIjoxNjM0MTAzMzE4LCJleHAiOjE2MzQxMDY5MTgsImlhdCI6MTYzNDEwMzMxOCwiZmFtaWx5X25hbWUiOiJjIiwiZW1haWwiOiJtZ2NsbmJ2akBncnIubGEifQ.MjG_TsR6BElztjDLorgpWkexckLa-LmQxj1a1rXd8eehW_VTZeHZ9boWfsh5rJu3jxZyJBiHysrv45APC2znjO3s001B7sPcRz_jHbIsqOli0pHQRMqINLZU_eAlK5A30Vz7dSdBqHHGohk7GOGx5bJ8KB4JdtnaVcVjzVouAxyKJrWy7M24HNZBjiNdhPoipRM2Dc9dBCoFq_7ygoZn8hu8u_OjzsvAfILCwOUWTiXoN8MjLc3LwIZ49qZGR52S7NndYSl_CqCmFg4KAgaqK83nzAgcwi3bYop7uWXkGhbjb1slgj6pBS5k2ka3T0esMp5YPqB8dZ17B7Txr_CWJw","content-Type","text/plain")
				;
		
		JSONObject requestparams=new JSONObject();
		requestparams.put("userId", "601c8ac510fc776317748261");
		requestparams.put("eventId", "6164800363d0e7e82464dbd5");
		request.body(requestparams.toJSONString());
		Response response=request.post();
		int statuscode=response.getStatusCode();
		Assert.assertEquals(statuscode, 200);
		//String successcode=response.jsonPath().get("SuccessCode");
		//Assert.assertEquals("correct successcode was return", successcode, "OPERATION");
		System.out.println(statuscode);
		System.out.println(response);
		System.out.println(requestparams);
		//System.out.println(request);
		//System.out.println(successcode);
	}

}
