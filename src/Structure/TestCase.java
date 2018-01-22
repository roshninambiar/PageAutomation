package Structure;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

//THIS CLASS CONTAINS THE VARIABLES THAT NEEDS TO BE INITIALIZED FOR INITIATING THE LOADING AND TESTING OF PAGE
class test{
	String testAction;
	String field;
	String fieldReference;
	String fieldValue;
	int successCondition;
	int failureCondition;	
	
	//PARAMETRIZED CONSTRUCTOR FOR INITIALIZING
	public test(String testAction,String field,String fieldReference,String fieldValue,int successCondition,int failureCondition){
	//void test(){
		this.testAction = testAction;
		this.field = field;
		this.fieldReference = fieldReference;
		this.fieldValue = fieldValue;
		this.successCondition = successCondition;
		this.failureCondition = failureCondition;
	}
	
}

public class TestCase {
	public static void main(String args[]) throws Exception{
		
		WebElement we = null;
			
		//SET THE DRIVER LOCATION 
		System.setProperty("webdriver.gecko.driver", "/home/devteam/Documents/AllDrivers/geckodriver");
		//INITIALIZE THE DRIVER
		WebDriver driver = new FirefoxDriver();
	
		//ARRAY OF OBJECTS OF THE TEST CLASS
		test[] testcase = new test[110];

		//THE TEST CASES ARE INITIALIZED
		testcase[0] = new test("loadPage", "https://www.facebook.com", "", "", 1, 100);
		testcase[1] = new test("findElement", "email", "id", "", 2, 100);
		testcase[2] = new test("fillValue", "", "", "newaccount@gmail.com", 3, 100);
		testcase[3] = new test("findElement", "pass", "id", "", 4, 100);
		testcase[4] = new test("fillValue", "", "", "newaccount", 5, 100);
		testcase[5] = new test("findElement", "loginbutton", "id", "", 6, 100);
		testcase[6] = new test("fillValue", "click", "", "", 7, 100);
		testcase[7] = new test("loadPage","https://www.gmail.com", "", "", 8, 100);
		

		
		System.out.println("Test");
		int i = 0;
		
		//ITERATE THE OBJECTS TILL TEST ACTION IS 'END' 
		while(testcase[i].testAction!="End"){
			switch(testcase[i].testAction){
			
				//LOADS THE PAGE BASED ON THE STATUS CODE
				case "loadPage":
					System.out.println("load page");
					boolean status = checkStatusCode(testcase[i].field);	//CALL THE FUNCTION TO GET THE HTTP RESPONSE CODE
					if(status == true){
						driver.get(testcase[i].field);
						i = testcase[i].successCondition;
					}
					else{
						i = testcase[i].failureCondition;
					}
					break;
				
				//FINDS THE ELEMENTS, CLASSIFY BASED ON THE ATTRIBUTE
				case "findElement":
					switch(testcase[i].fieldReference){
						case "id":
							if((driver.findElement(By.id(testcase[i].field))).isDisplayed()){
								we = driver.findElement(By.id(testcase[i].field));
								i = testcase[i].successCondition;
							}
							else{
								i = testcase[i].failureCondition;
							}
							break;
						
						case "name":
							if((driver.findElement(By.name(testcase[i].field))).isDisplayed()){
								we = driver.findElement(By.name(testcase[i].field));
								i = testcase[i].successCondition;
							}
							else{
								i = testcase[i].failureCondition;
							}
							break;
							
						case "link":
							if((driver.findElement(By.linkText(testcase[i].field))).isDisplayed()){
								we = driver.findElement(By.linkText(testcase[i].field));
								i = testcase[i].successCondition;
							}
							else{
								i = testcase[i].failureCondition;
							}
							break;
							
						case "class":
							if((driver.findElement(By.className(testcase[i].field))).isDisplayed()){
								we = driver.findElement(By.className(testcase[i].field));
								i = testcase[i].successCondition;
							}
							else{
								i = testcase[i].failureCondition;
							}
							break;
							
						case "partial link":
							if((driver.findElement(By.partialLinkText(testcase[i].field))).isDisplayed()){
								we = driver.findElement(By.partialLinkText(testcase[i].field));
								i = testcase[i].successCondition;
							}
							else{
								i = testcase[i].failureCondition;
							}
							break;
					}
				
					i = testcase[i].successCondition;
					System.out.println("Value of i: " + i);
					break;
				
				case "fillValue":
					System.out.println("Fill Values" + i);
					if((testcase[i].field).equals("click")){
						we.click();	
					}
					else{
						we.sendKeys(testcase[i].field);
					}
					we.sendKeys(testcase[i].fieldValue);
					i = testcase[i].successCondition;
					break;
				
				case "End":
					break;
				
				default:
					break;
					
			}
		}
	}


	public static boolean checkStatusCode(String baseurl) throws ClientProtocolException, IOException{
		
		boolean ret= true;
		
		//Get status code
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(baseurl);
	    HttpResponse response = httpclient.execute(httpget);
		StatusLine sl = response.getStatusLine();
		int code = sl.getStatusCode();
							
		//check if status code is a success or failure condition
		if(code == 200){
			ret = true;
		}
		else if(code == 404){
			ret = false;
		}
		return ret;
		
	
	}
}
