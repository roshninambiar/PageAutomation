package Structure;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

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
	
/*	public test(){
		this.testAction = "df";
		this.field = "sdf";
		this.fieldReference = "sd";
		this.fieldValue = "fs";
		this.successCondition = 0;
		this.failureCondition = 0;
	}*/
	
}

public class TestCase {
	public static void main(String args[]) throws Exception{
		
		WebElement we = null;
		String testAction = "";
		String field = "";
		String fieldRef = ""; 
		String fieldVal = "";
		int successCond = 0;
		int failureCond = 0;
		
		
		//SET THE DRIVER LOCATION 
		System.setProperty("webdriver.chrome.driver", "/home/devteam/Documents/AllDrivers/chromedriver");
				//System.setProperty("webdriver.gecko.driver", "/home/devteam/Documents/AllDrivers/geckodriver");
		
		//INITIALIZE THE DRIVER
		WebDriver driver = new ChromeDriver();
				//WebDriver driver2 = new FirefoxDriver();
		
		//ARRAY OF OBJECTS OF THE TEST CLASS
		test[] testcase = new test[110];

		//THE TEST CASES ARE INITIALIZED
		//testAction, field, fieldReference, fieldValue, successCondition, failureCondition
		
		/*
		 * TEST CASE NO 1
		 *
		testcase[0] = new test("loadPage", "https://www.facebook.com", "", "", 1, 100);
		testcase[1] = new test("findElement", "email", "id", "", 2, 100);
		testcase[2] = new test("fillValue", "sendkeys", "", "nambiar.roshni@yahoo.com", 3, 100);
		testcase[3] = new test("findElement", "pass", "id", "", 4, 100);
		testcase[4] = new test("fillValue", "sendkeys", "", "@stars@80", 5, 100);
		//testcase[7] = new test("fillValue", "clear", "", "", 5, 100);
		testcase[5] = new test("findElement", "loginbutton", "id", "", 6, 100);
		testcase[6] = new test("fillValue", "click", "", "", 8, 100);
		testcase[7] = new test("loadPage","https://www.facebook.com", "", "", 8, 100);
		testcase[8] = new test("findElement","_2n_9","class","",9,100);
		testcase[9] = new test("fillValue", "click", "", "", 100, 100);
		testcase[100] = new test("End", "", "", "", 100, 100);
		*/
		
		
		/*	
		 * TEST CASE NO 2
		 *
	  	testcase[0] = new test("loadPage", "http://localhost/task1/web/index.php?r=student/index", "", "", 1, 100);
		testcase[1] = new test("findElement", "sname", "name", "", 2, 100);
		testcase[2] = new test("fillValue", "sendkeys", "", "nambiar.roshni@yahoo.com", 3, 100);
		testcase[3] = new test("findElement", "dept", "name", "", 4, 100);
		testcase[4] = new test("fillValue", "sendkeys", "", "nambiar", 5, 100);
		testcase[5] = new test("findElement", "specialisation", "id", "", 6, 100);
		testcase[6] = new test("fillValue", "click", "", "2", 7, 100);
		testcase[7] = new test("findElement", "submitButton", "id", "", 8, 100);
		testcase[8] = new test("fillValue", "click", "", "", 100, 100);
		*/
		
		
		
		/*
		 * TEST CASE NO 3
		 *
		testcase[0] = new test("loadPage", "http://localhost/table.html", "", "", 1, 100);
		testcase[1] = new test("findElement", "myfile", "id", "", 2, 100);
		testcase[2] = new test("fillValue", "sendkeys", "", "/home/devteam/Documents/ExcelFiles/loadPage.xls", 3, 100);
		testcase[3] = new test("findElement", "Muthu Kumar", "link", "" , 4, 100);
		testcase[4] = new test("fillValue", "click", "", "", 5, 100);
		*/
		
		File f = new File("/home/devteam/Documents/ExcelFiles/loadPage2.xls");
		FileInputStream fis = new FileInputStream(f);
		HSSFWorkbook wb = new HSSFWorkbook(fis);
		HSSFSheet sheet1 = wb.getSheetAt(0);
		int j=1, k=1, i=0;
		
		int maxrow = sheet1.getLastRowNum();
		int maxcol = sheet1.getRow(maxrow).getLastCellNum();
		
		while(j!=(maxrow+1)){
			for(j=1; j<=maxrow; j++){
				System.out.println(sheet1.getRow(j).getCell(2)+" "+j);

				testAction = sheet1.getRow(j).getCell(2).getStringCellValue();
				field = sheet1.getRow(j).getCell(3).getStringCellValue();
				fieldRef = sheet1.getRow(j).getCell(4).getStringCellValue();
				fieldVal = sheet1.getRow(j).getCell(5).getStringCellValue();
				successCond = (int) sheet1.getRow(j).getCell(6).getNumericCellValue();
				failureCond = (int) sheet1.getRow(j).getCell(7).getNumericCellValue();
				System.out.println(testAction+ field+ fieldRef+ fieldVal+ successCond+ failureCond);
				testcase[i] = new test(testAction, field, fieldRef, fieldVal, successCond, failureCond);
			
			//}
		
		
		//ITERATE THE OBJECTS TILL TEST ACTION IS 'END' 
			//while(testcase[i].testAction!="End"){
				switch(testcase[i].testAction){
			
				//LOADS THE PAGE BASED ON THE STATUS CODE 
					case "loadPage":
						driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
						boolean status = checkStatusCode(testcase[i].field);
						System.out.println(status);//CALL THE FUNCTION TO GET THE HTTP RESPONSE CODE
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
									System.out.println(testcase[i].field);
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
						break;
				
					case "fillValue":
						switch(we.getTagName()){
							case "input":
							case "label":
							case "a":
								if(we.getAttribute("type").contains("date")){
									if(we.getAttribute("type").equals("date")){
										we.sendKeys(testcase[i].field);
									}
								}
								else if((testcase[i].field).equals("clear")){
									System.out.println(testcase[i].field);
									we.clear();
								}
								else if((testcase[i].field).equals("click")){
									System.out.println(testcase[i].field);
									we.click();	
								}
								else{
									System.out.println(testcase[i].field);
									we.sendKeys(testcase[i].fieldValue);
								}
								break;
							
							case "button":
								we.click();
								break;
							
							case "select":
								Select dropdown= new Select(we);
								if(testcase[i].fieldValue.contains("0") ||testcase[i].fieldValue.contains("1") ||testcase[i].fieldValue.contains("2")||
										testcase[i].fieldValue.contains("3") || testcase[i].fieldValue.contains("4") ||testcase[i].fieldValue.contains("5") ||
										testcase[i].fieldValue.contains("6") ||testcase[i].fieldValue.contains("7") ||testcase[i].fieldValue.contains("8") || 
										testcase[i].fieldValue.contains("9"))
									dropdown.selectByIndex(Integer.parseInt(testcase[i].fieldValue));
								else
									dropdown.selectByVisibleText(testcase[i].fieldValue);
								break;
					
							
							default:
								break;
						}
						i = testcase[i].successCondition;
						break;
				
					case "End":
						break;
				
					default:
						break;
					
				}	//outer switch case
			//}
			}	//for loop
		}	//while loop
	}


	public static boolean checkStatusCode(String baseurl) throws ClientProtocolException, IOException{
		boolean ret= true;
		
		//GET STATUS CODE
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(baseurl);
	    HttpResponse response = httpclient.execute(httpget);
		StatusLine sl = response.getStatusLine();
		int code = sl.getStatusCode();
							
		//CHECK IF STATUS CODE IS A SUCCESS OR FAILURE CONDITION
		if(code == 200){
			ret = true;
		}
		else if(code == 404){
			ret = false;
		}
		return ret;	
	}
}




