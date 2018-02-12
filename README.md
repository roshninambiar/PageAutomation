# PageAutomation

1. Overview:

The project concentrates on automating the tests that have to be performed on various web pages.
When various functionalities are added to the web pages, each time testing it manually becomes a tedious task.
Thus to automate the entire process, automation testing has been used. This reduced the time consumption and redundancy.
Reports are also generated according to the test case results.

2. Working:

  2.1. Loading the web page:
       The ‘Load Page’ functionality will make sure that the web page whose URL has been provided will be loaded successfully.
       If any failure condition occurs,(For Eg: Error Code 200 shows up) then the test case will be either terminated or 
            another functionality will be loaded.
            
  2.2. Finding the web elements:
       The web elements on the web page are identified and captured one by one.
       Each element l, found or is displayed on the web page, will go to the next module.
       
  2.3. Action to be performed on the web elements:
       The web elements once successfully found, various actions can be performed on it. 
       For Eg: In a login page, the values have to be filled in the text boxes, submit button has to be clicked, 
            date has to be picked, value from the drop-down box has to be chosen etc.
            
  2.4. Report generation:
       When the tests are executed, a report will be generated automatically that will contain information regarding the 
            execution of the test case.
       Apart from that, other assertions can also be given make the test execution more interactive and user friendly.

3. Requirements:

	3.1. Selenium 2.0
  
	3.2. Java 8

4. Dependencies:

	4.1. Java JRE 8 and JDK 8
  
	4.2. Browser Drivers
		Download link : http://www.seleniumhq.org/download/
    
	4.3. Apache POI 
		Download link : https://poi.apache.org/download.html
    
	4.4. Selenium jar files
		Download link : http://selenium-release.storage.googleapis.com/index.html?path=3.8/
		version: Selenium-java 3.8.1
    
