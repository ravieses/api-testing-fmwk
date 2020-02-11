package Issue.testcases;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import dataproviders.request.RequestProperties;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class IssueModification {
	RequestProperties reqProperties = RequestProperties.getInstance();
	
	@Test(description="Update an issue",dataProvider = "updateIssueDP", dataProviderClass = dataproviders.request.DataProviders.class)
	void modifyIssueById(String id ,String description) {
		RestAssured.baseURI = reqProperties.getURL();
		RequestSpecification httpReq = RestAssured.given();
		JSONObject reqBody = new JSONObject();
		reqBody.put("description", description);
		reqBody.put("fromemail", "knutmt@gmail.com");
		reqBody.put("title", "New Issue");
		httpReq.body(reqBody.toJSONString());
		httpReq.headers(reqProperties.getHeaders());
		Response response = httpReq.request(Method.PUT, id);
		int status = response.statusCode();
		String responseBody = response.getBody().asString();
		System.out.println("RequestBody---- :\t" + reqBody);
		System.out.println("ResponseBody---- :\t" + responseBody);
		System.out.println("\n**************************************\n");
		Assert.assertEquals(status, 200);
	}
}
