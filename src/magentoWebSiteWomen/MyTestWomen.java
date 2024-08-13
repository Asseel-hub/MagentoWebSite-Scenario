package magentoWebSiteWomen;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyTestWomen {

	WebDriver driver = new ChromeDriver();
	String mywebsite ="https://magento.softwaretestingboard.com/";
	String password="IloveCanada123!";
	String LogoutPage="https://magento.softwaretestingboard.com/customer/account/logout/";
	String emailAddressToLogin = "";
	Random rand = new Random();
	String NickName ="";

	@BeforeTest
	public void mySetUp() {
		driver.manage().window().maximize();
		driver.get(mywebsite);
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
	
	}
	

	@Test(priority  =1 , enabled = false)
	public void createAnAccount() {
		
		WebElement webSitepage= driver.findElement(By.xpath("//div[@class='panel header']//a[normalize-space()='Create an Account']"));
		webSitepage.click();
		String SampleFirstName []= {"Alice", "Bob", "Charlie", "David", "Eve", "Frank", "Grace", "Hannah", "Ian","Judy" };
		String SampleLastName []= { "Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson","Moore", "Taylor" };
        
		int  RandomIndexOffirstName = rand.nextInt(SampleFirstName.length); //.length -- static Array
		int RandomIndexOfLastName=rand.nextInt(SampleLastName.length);
		String FirstName = SampleFirstName[RandomIndexOffirstName];
		String lastName=SampleLastName[RandomIndexOfLastName];
		int RandomNumber=rand.nextInt(9876);
		String domainName="@gmil.com";
		System.out.println(RandomIndexOffirstName);
		System.out.println(RandomIndexOfLastName);
		
		WebElement FirstNameInput = driver.findElement(By.id("firstname"));
		WebElement lastNameInput =driver.findElement(By.id("lastname"));
		WebElement emailAdressInput=driver.findElement(By.id("email_address"));
		WebElement passwordInput =driver.findElement(By.id("password"));
		WebElement confirmpasswordInput =driver.findElement(By.id("password-confirmation"));
		WebElement createAnAccountButton=driver.findElement(By.xpath("//button[@title='Create an Account']"));
		
		FirstNameInput.sendKeys(FirstName);
		lastNameInput.sendKeys(lastName);
		emailAdressInput.sendKeys(FirstName+lastName+RandomNumber+domainName);
		passwordInput.sendKeys(password);
		confirmpasswordInput.sendKeys(password);
		createAnAccountButton.click();
		emailAddressToLogin = FirstName+lastName+RandomNumber+domainName;
		WebElement massageAswebelement = driver.findElement(By.className("messages"));
		String Actualmassage = massageAswebelement.getText();
		String Expectedmassage ="Thank you for registering with Main Website Store.";
		Assert.assertEquals(Actualmassage, Expectedmassage);
		NickName= FirstName;
	}
	
	
	@Test(priority =2)
	public void AddWomenItem () throws InterruptedException {
		WebElement womenSection = driver.findElement(By.cssSelector("#ui-id-4"));
		womenSection.click();
		WebElement OlproductItemContainer = driver.findElement(By.xpath("//ol[@class='product-items widget-product-grid']")) ; //driver--grandfather
		List<WebElement> allItems =  OlproductItemContainer.findElements(By.tagName("li"));                                //container--father
		int totalNumberOfItem = allItems.size();
		int randomItem = rand.nextInt(totalNumberOfItem);
		allItems.get(randomItem).click();
		
		WebElement womenSizesContainer=driver.findElement(By.xpath("//div[@class='swatch-attribute size']")) ;
		List<WebElement> allSizes =   womenSizesContainer.findElements(By.tagName("div"));
		int totalNumberOfSizes = allSizes.size();
		int randomSizes =rand.nextInt(totalNumberOfSizes);
		allSizes.get(randomSizes).click();
		
		WebElement womenColorContainer=driver.findElement(By.xpath("//div[@class='swatch-attribute color']"));
		List<WebElement> allColor = womenColorContainer.findElements(By.tagName("div"));
		int totalNumberOfColor =allColor.size();
		int randomColor=rand.nextInt(totalNumberOfColor);
		allColor.get(randomColor).click();
	
		WebElement addButton = driver.findElement(By.cssSelector("#product-addtocart-button"));
		addButton.click();
		Thread.sleep(3000);

		WebElement massageAdded = driver.findElement(By.xpath("//div[@class='message-success success message']"));
		String Actual = massageAdded.getText();
		boolean Actualmassage = Actual.contains(Actual);
		boolean Expectedmassage = true;
		Assert.assertEquals(Actualmassage, Expectedmassage);
		
		// driver.navigate().refresh();--to refresh the page
		
		WebElement reviewLabel = driver.findElement(By.cssSelector("#tab-label-reviews-title"));
		reviewLabel.click();
		
		JavascriptExecutor js = (JavascriptExecutor) driver ;
		js.executeScript("window.scrollTo(0,1000)");
		
		//WebElement ratingStars = driver.findElement(By.cssSelector(".control.review-control-vote"));
		//ratingStars.findElements(By.tagName("label")).get(2).click();
		
		//driver.findElement(By.id("Rating_2"));
		
		String [] ids= {"Rating_1" ,"Rating_2","Rating_3","Rating_4","Rating_5"};
		int randomIndex=rand.nextInt(ids.length);
		
		// js it is make click
		WebElement element = driver.findElement(By.id(ids[randomIndex]));
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
		//WebElement ratingLabel = driver.findElement(By.cssSelector("#Rating_1_label"));
		//ratingLabel.click();
		
		WebElement nickName = driver.findElement(By.id("nickname_field"));
		nickName.sendKeys("asil");//NickName
		WebElement summary = driver.findElement(By.id("summary_field"));
		String summaryInput ="it is very expensive";
		summary.sendKeys(summaryInput);
		WebElement review =driver.findElement(By.cssSelector("#review_field"));
		String reviewInput ="Review by"+"asil"+"Posted on8/11/24";//NickName
		review.sendKeys(reviewInput);
		WebElement submitReview = driver.findElement(By.cssSelector("button[class='action submit primary']"));
		submitReview.click();
		
		WebElement massageAddedReview=driver.findElement(By.cssSelector(".message-success.success.message"));
		String ActualMsg=massageAddedReview.getText();
		String  ExpectedMsg=   "You submitted your review for moderation." ;
		Assert.assertEquals(ActualMsg, ExpectedMsg);
		
	}
	@Test(priority = 3 , enabled = false)
	public void LogOut() throws InterruptedException {
		driver.get(LogoutPage);
		WebElement massageAslogout = driver.findElement(By.xpath("//span[@data-ui-id='page-title-wrapper']"));
	    String Acutalmassage = massageAslogout.getText();
	    String Expectedmassage= "You are signed out";
	    Assert.assertEquals(Acutalmassage, Expectedmassage);
	  
		
	}
	@Test(priority = 4 , enabled = false)
	public void logIn() {

		WebElement logInPage = driver.findElement(By.linkText("Sign In"));
		logInPage.click();
		WebElement emailInput = driver.findElement(By.id("email"));
		emailInput.sendKeys(emailAddressToLogin);
		WebElement passwordInput = driver.findElement(By.id("pass"));
		passwordInput.sendKeys(password);
		WebElement signInButton = driver.findElement(By.cssSelector(".action.login.primary"));
		signInButton.click();

		String welcomeMassage= driver.findElement(By.className("logged-in")).getText();
		boolean ActualMass= welcomeMassage.contains("Welcome");
		boolean ExpectedMass=true;
	    Assert.assertEquals(ActualMass, ExpectedMass);
	}
		
		
		
		
	

	

	
	
	
	
	
}
