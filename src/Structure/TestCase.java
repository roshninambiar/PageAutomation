package Structure;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

class test{
	String testAction;
	String field;
	String fieldReference;
	String fieldValue;
	int successCondition;
	int failureCondition;	
	
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
	public static void main(String args[]){
		
		WebElement we = null;
				
		System.setProperty("webdriver.gecko.driver", "/home/devteam/Documents/AllDrivers/geckodriver");
		WebDriver driver = new FirefoxDriver();
		//WebElement we;
		test[] testcase = new test[110];
		//test action, field, fieldReference, field Value, success condition, failure condition
		testcase[0] = new test("loadPage", "https://www.facebook.com", "", "", 1, 8);
		testcase[1] = new test("findElement", "email", "id", "", 2, 8);
		testcase[2] = new test("fillValue", "", "", "newaccount@gmail.com", 3, 8);
		testcase[3] = new test("findElement", "pass", "id", "", 4, 8);
		testcase[4] = new test("fillValue", "", "", "newaccount", 5, 8);
		testcase[5] = new test("findElement", "loginbutton", "id", "", 6, 8);
		testcase[6] = new test("fillValue", "click", "", "", 8, 8);
		//testcase[7] = new test("loadPage", "", "", "", 1, 8);
		testcase[8] = new test("End","https://gmail.com", "", "", 1, 3);
		
		String a = "click";
		
		System.out.println("Test");
		int i = 0;
		
		while(testcase[i].testAction!="End"){
			switch(testcase[i].testAction){
			
				case "loadPage":
					System.out.println("load page");
					driver.get(testcase[i].field);
					i = testcase[i].successCondition;
					break;
				
				case "findElement":
					switch(testcase[i].fieldReference){
						case "id":
							we = driver.findElement(By.id(testcase[i].field));
							break;
						
						case "name":
							we = driver.findElement(By.name(testcase[i].field));
							break;
							
						case "link":
							we = driver.findElement(By.linkText(testcase[i].field));
							break;
							
						case "class":
							we = driver.findElement(By.className(testcase[i].field));
							break;
							
						case "partial link":
							we = driver.findElement(By.partialLinkText(testcase[i].field));
							break;
					}
				
					i = testcase[i].successCondition;
					System.out.println("Value of i: " + i);
					break;
				
				case "fillValue":
					System.out.println("Fill Values" + i);
					if((testcase[i].field).equals("click")){
						System.out.println("Red");
						we.click();	
					}
					else{
						System.out.println("Yellow");
						we.sendKeys(testcase[i].field);
					}
					we.sendKeys(testcase[i].fieldValue);
					i = testcase[i].successCondition;
					break;
				
				case "End":
					System.out.println("End");
					break;
				
				default:
					break;
					
			}
		}
	}

}
