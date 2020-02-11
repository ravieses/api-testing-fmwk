package Issue.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import dataproviders.request.RequestProperties;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class IssueList {

	RequestProperties reqData = RequestProperties.getInstance();

	@Test(description="Get list of all Issues")
	void getIssuesList() {
		RestAssured.baseURI = reqData.getURL();
		RequestSpecification httpReq = RestAssured.given();
		httpReq.headers(reqData.getHeaders());
		Response response = httpReq.request(Method.GET);
		int status = response.statusCode();
		String responseBody = response.getBody().asString();
		System.out.println("ResponeBody :\t" + responseBody);
		Assert.assertEquals(status, 200);
		System.out.println("\n**************************************\n");
	}

	@Test(description="Get List of Issues by its ID", dataProvider = "idDP", dataProviderClass = dataproviders.request.DataProviders.class)
	void getIssuesListByID(String id) {
		RestAssured.baseURI = reqData.getURL();
		RequestSpecification httpReq = RestAssured.given();
		httpReq.headers(reqData.getHeaders());
		Response response = httpReq.request(Method.GET, id);

		int status = response.statusCode();
		String responseBody = response.getBody().asString();
		System.out.println("Issues for the attribute \t" + id + "\n" + responseBody);
		System.out.println("\n**************************************\n");
		Assert.assertEquals(status, 200);
	}

	@Test(description="Get list of Issues using a Query",dataProvider = "queryDP", dataProviderClass = dataproviders.request.DataProviders.class)
	void getIssuesListByQuery(String attribute, String value) {
		RestAssured.baseURI = reqData.getURL();
		RestAssured.given().param(attribute, value);
		RequestSpecification httpReq = RestAssured.given();
		httpReq.headers(reqData.getHeaders());
		Response response = httpReq.request(Method.GET);
		int status = response.statusCode();
		String responseBody = response.getBody().asString();
		System.out.println("Issues for the \t" + attribute + "\t=\t" + value + "\n" + responseBody);
		System.out.println("\n**************************************\n");
		Assert.assertEquals(status, 200);
	}
}
