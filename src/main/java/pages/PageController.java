package main.java.pages;
import org.openqa.selenium.WebDriver;

public class PageController extends BasePage{

	private String url = "https://my.ecoligo.investments/";

	//Pages definition
	RegisterAccountPage createAccountPage;
	RegisterAccountStep2Page registerAccountStep2Page;
	
	public PageController(WebDriver driver) {
		super(driver);
		setRegisterAccountPage();
		setRegisterAccountStep2Page();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public RegisterAccountPage getRegisterAccountPage(){
		return createAccountPage;
	}

	public void setRegisterAccountPage(){
		createAccountPage = new RegisterAccountPage(driver);
	}

	public RegisterAccountStep2Page getRegisterAccountStep2Page() {
		return registerAccountStep2Page;
	}

	public void setRegisterAccountStep2Page() {
		registerAccountStep2Page = new RegisterAccountStep2Page(driver);
	}
}
