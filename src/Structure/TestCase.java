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
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.CellType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.google.common.collect.Table.Cell;

//THIS CLASS CONTAINS THE VARIABLES THAT NEEDS TO BE INITIALIZED FOR INITIATING THE LOADING AND TESTING OF PAGE
class test{
	String testAction;
	String field;
	String fieldReference;
	String fieldValue;
	int successCondition;
	int failureCondition;
	int fieldValueInt;

	//PARAMETRIZED CONSTRUCTOR FOR INITIALIZING WHEN FIELD VALUE IS A STRING
	public test(String testAction,String field,String fieldReference,String fieldValueString,int successCondition,int failureCondition){
		this.testAction = testAction;
		this.field = field;
		this.fieldReference = fieldReference;
		this.fieldValue = fieldValueString;
		this.fieldValueInt = 0;
		this.successCondition = successCondition;
		this.failureCondition = failureCondition;
	}

	//PARAMETRIZED CONSTRUCTOR FOR INITIALIZING WHEN FIELD VALUE IS AN INTEGER
	public test(String testAction,String field,String fieldReference,int fieldValueInt,int successCondition,int failureCondition){
		this.testAction = testAction;
		this.field = field;
		this.fieldReference = fieldReference;
		this.fieldValue = "";
		this.fieldValueInt = fieldValueInt;
		this.successCondition = successCondition;
		this.failureCondition = failureCondition;
	}

}

public class TestCase {
	public static void main(String args[]) throws Exception{

		WebElement we = null;
		String testAction = "";
		String field = "";
		String fieldRef = ""; 
		String fieldValString = "";
		int successCond = 0;
		int failureCond = 0;
		int fieldValInt = 0;

		String pass = "Passed";
		String fail = "Failed";

		//SET THE DRIVER LOCATION 

		System.setProperty("webdriver.chrome.driver", "/home/devteam/Documents/AllDrivers/chromedriver");
		System.setProperty("webdriver.gecko.driver", "/home/devteam/Documents/AllDrivers/geckodriver");
		//INITIALIZE THE DRIVER

		//WebDriver driver = new ChromeDriver();
		WebDriver driver = new FirefoxDriver();

		//ARRAY OF OBJECTS OF THE TEST CLASS
		test[] testcase = new test[110];
		
		String file = "loadPage.xls";
		String path = "/home/devteam/Documents/ExcelFiles/loadPage/"+file;
		File f = new File(path);
		

		FileInputStream fis = new FileInputStream(f);
		HSSFWorkbook wb = new HSSFWorkbook(fis);
		HSSFWorkbook resultwb = new HSSFWorkbook();
		HSSFSheet sheet = wb.getSheetAt(0);
		
		String resultfile = "Result_"+f.getName();
		File resultf = new File("/home/devteam/Documents/ExcelFiles/loadPage/"+resultfile);
		FileUtils.copyFile(f, resultf);
		
		FileInputStream resfis = new FileInputStream(resultf);
		HSSFWorkbook reswb = new HSSFWorkbook(resfis);
		HSSFSheet ressheet = reswb.getSheetAt(0);
		
		//FileOutputStream fos = new FileOutputStream(resultf);

		int j=1, k=1, i=0;

		int maxrow = sheet.getLastRowNum();
		int maxcol = sheet.getRow(maxrow).getLastCellNum();

		while(j!=(maxrow+1)){
			for(j=1; j<=maxrow; j++){
				System.out.println(sheet.getRow(j).getCell(2)+" "+j);

				testAction = sheet.getRow(j).getCell(2).getStringCellValue();
				field = sheet.getRow(j).getCell(3).getStringCellValue();
				fieldRef = sheet.getRow(j).getCell(4).getStringCellValue();
				successCond = (int) sheet.getRow(j).getCell(6).getNumericCellValue();
				failureCond = (int) sheet.getRow(j).getCell(7).getNumericCellValue();

				HSSFCell cell = sheet.getRow(j).getCell(5);
				CellType type = cell.getCellTypeEnum();

				if(type == CellType.STRING){
					fieldValString = sheet.getRow(j).getCell(5).getStringCellValue();
					testcase[i] = new test(testAction, field, fieldRef, fieldValString, successCond, failureCond);
				}
				if(type == CellType.NUMERIC){
					fieldValInt = (int) sheet.getRow(j).getCell(5).getNumericCellValue();
					testcase[i] = new test(testAction, field, fieldRef, fieldValInt, successCond, failureCond);
				}


				System.out.println(testAction+ field+ fieldRef+ fieldValString + fieldValInt+ successCond+ failureCond);


				//ITERATE THE OBJECTS TILL TEST ACTION IS 'END' 
				switch(testcase[i].testAction){

				//LOADS THE PAGE BASED ON THE STATUS CODE 
				case "loadPage":
					//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					boolean status = checkStatusCode(testcase[i].field);
					System.out.println(status);//CALL THE FUNCTION TO GET THE HTTP RESPONSE CODE
					if(status == true){
						driver.get(testcase[i].field);
						i = testcase[i].successCondition;
						ressheet.getRow(i).getCell(8).setCellValue(pass);
					}
					else{
						i = testcase[i].failureCondition;
						ressheet.getRow(i).getCell(8).setCellValue(fail);
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
							ressheet.getRow(i).getCell(8).setCellValue(pass);
						}
						else{
							i = testcase[i].failureCondition;
							ressheet.getRow(i).getCell(8).setCellValue(fail);
						}
						break;

					case "name":
						if((driver.findElement(By.name(testcase[i].field))).isDisplayed()){
							we = driver.findElement(By.name(testcase[i].field));
							i = testcase[i].successCondition;
							ressheet.getRow(i).getCell(8).setCellValue(pass);
						}
						else{
							i = testcase[i].failureCondition;
							ressheet.getRow(i).getCell(8).setCellValue(fail);
						}
						break;

					case "link":
						if((driver.findElement(By.linkText(testcase[i].field))).isDisplayed()){
							we = driver.findElement(By.linkText(testcase[i].field));
							i = testcase[i].successCondition;
							ressheet.getRow(i).getCell(8).setCellValue(pass);
						}
						else{
							i = testcase[i].failureCondition;
							ressheet.getRow(i).getCell(8).setCellValue(fail);
						}
						break;

					case "class":
						if((driver.findElement(By.className(testcase[i].field))).isDisplayed()){
							we = driver.findElement(By.className(testcase[i].field));
							i = testcase[i].successCondition;
							ressheet.getRow(i).getCell(8).setCellValue(pass);
						}
						else{
							i = testcase[i].failureCondition;
							ressheet.getRow(i).getCell(8).setCellValue(fail);
						}
						break;

					case "partial link":
						if((driver.findElement(By.partialLinkText(testcase[i].field))).isDisplayed()){
							we = driver.findElement(By.partialLinkText(testcase[i].field));
							i = testcase[i].successCondition;
							ressheet.getRow(i).getCell(8).setCellValue(pass);
						}
						else{
							i = testcase[i].failureCondition;
							ressheet.getRow(i).getCell(8).setCellValue(fail);
						}
						break;
					}
					break;

				case "fillValue":
					switch(we.getTagName()){
					case "input":
					case "label":
					case "a":
						if((testcase[i].field).equals("clear")){
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
						if((testcase[i].fieldValue).isEmpty())
							dropdown.selectByIndex(testcase[i].fieldValueInt);

						else
							dropdown.selectByVisibleText(testcase[i].fieldValue);									
						break;
					}
					i = testcase[i].successCondition;
					ressheet.getRow(i).getCell(8).setCellValue(pass);
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


/*
reswb.write(fos);
System.out.println("File written");
reswb.close();*/

/*


Cell cell = (Cell) sheet.getRow(j).getCell(5);
int e = evaluator.evaluateFormulaCell((org.apache.poi.ss.usermodel.Cell) cell);
System.out.println("What i wanna check: "+e+" ");
if (cell!=null) {
    switch (evaluator.evaluateFormulaCell((org.apache.poi.ss.usermodel.Cell) cell)) { 
        case Cell.CELL_TYPE_NUMERIC:
            System.out.println(cell.getNumericCellValue());
            break;

        case Cell.CELL_TYPE_STRING:
            System.out.println(cell.getStringCellValue());
            break;
    }
}*/