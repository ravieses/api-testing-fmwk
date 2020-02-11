package Issue.testcases;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import dataproviders.request.RequestProperties;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class IssueCreation {
	RequestProperties reqProperties = RequestProperties.getInstance();

	@Test(description="Create an Issue",dataProvider = "CreateIssueDP", dataProviderClass = dataproviders.request.DataProviders.class)
	void createIssue(String description, String fromEmail, String title) {
		RestAssured.baseURI = reqProperties.getURL();
		RequestSpecification httpReq = RestAssured.given();
		JSONObject reqBody = new JSONObject();
		reqBody.put("description", description);
		reqBody.put("fromemail", fromEmail);
		reqBody.put("title", title);
		httpReq.body(reqBody.toJSONString());
		httpReq.headers(reqProperties.getHeaders());
		Response response = httpReq.request(Method.POST);
		int status = response.statusCode();
		String responseBody = response.getBody().asString();
		System.out.println("RequestBody---- :\t" + reqBody);
		System.out.println("ResponeBody---- :\t" + responseBody);
		System.out.println("\n**************************************\n");
		JsonPath jsonPathEvaluator = response.jsonPath();
		String actualDesc = jsonPathEvaluator.get("description");
		String actualfromEmail = jsonPathEvaluator.get("fromemail");
		String actualTitle = jsonPathEvaluator.getString("title");
		Assert.assertEquals(status, 201);
		Assert.assertEquals(actualDesc, description);
		Assert.assertEquals(actualfromEmail, fromEmail);
		Assert.assertEquals(actualTitle, title);

	}
}
