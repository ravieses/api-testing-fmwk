package dataproviders.request;

import org.testng.annotations.DataProvider;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class DataProviders {
	@DataProvider(name = "queryDP")
	public Object[][] getByQueryDataProvider() {
		return new Object[][] { { "fromemail", "knutmt@gmail.com" }, { "fromemail", "jbeejones@gmail.com" } };

	}

	@DataProvider(name = "idDP")
	public static Object[][] getByIdDataProvider() {
		return new Object[][] { new Object[] { "5887dd51f54b5f59000000d8", },
				new Object[] { "588893fbf54b5f59000003ce" } };
	}

	@DataProvider(name = "idDP2")
	public static Object[][] getByIdDataProvider2() {
		return new Object[][] { new Object[] { "5888b0d1f54b5f590000040f" } };
	}

	@DataProvider(name = "updateIssueDP")
	static String[][] getDataToUpdate() throws FilloException {
		String path = System.getProperty("user.dir") + "/src/test/java/TestData.xlsx";
		Fillo fillo = new Fillo();
		Connection connection = fillo.getConnection(path);
		String strQuery = "Select id ,title from UpdateUser";
		Recordset recordset = connection.executeQuery(strQuery);
		String[][] arr = new String[recordset.getCount()][2];
		int i = 0;
		while (recordset.next()) {
			arr[i][0] = recordset.getField("id");
			arr[i][1] = recordset.getField("title");
			i++;
		}

		recordset.close();
		connection.close();

		return arr;

	}

	@DataProvider(name = "CreateIssueDP")
	static String[][] getDataToCreate() throws FilloException {
		String path = System.getProperty("user.dir") + "/src/test/java/TestData.xlsx";
		Fillo fillo = new Fillo();
		Connection connection = fillo.getConnection(path);
		String strQuery = "Select title ,fromemail,description from CreateUser";
		Recordset recordset = connection.executeQuery(strQuery);
		String[][] arr = new String[recordset.getCount()][3];
		int i = 0;
		while (recordset.next()) {
			arr[i][0] = recordset.getField("title");
			arr[i][1] = recordset.getField("fromemail");
			arr[i][2] = recordset.getField("description");
			i++;
		}

		recordset.close();
		connection.close();

		return arr;

	}

}
