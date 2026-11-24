package jp.co.sss.lms.ct.f01_login1;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

/**
 * 結合テスト ログイン機能①
 * ケース02
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース02 受講生 ログイン 認証失敗")
public class Case02 {

	/** 前処理 */
	@BeforeAll
	static void before() {
		createDriver();
	}

	/** 後処理 */
	@AfterAll
	static void after() {
		closeDriver();
	}

	@Test
	@Order(1)
	@DisplayName("テスト01 トップページURLでアクセス")
	void test01() {
		webDriver.get("http://localhost:8080/lms/");
		File file = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
		try {
			Files.copy(file.toPath(), Paths.get("./evidence/ケース2テスト1エビデンス.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	@Order(2)
	@DisplayName("テスト02 DBに登録されていないユーザーでログイン")
	void test02() {
		webDriver.get("http://localhost:8080/lms/");
		WebElement loginId = webDriver.findElement(By.name("loginId"));
		loginId.clear();
		loginId.sendKeys("間違い太郎");
		WebElement classElement = webDriver.findElement(By.cssSelector(".btn.btn-primary"));
		classElement.click();
		visibilityTimeout(By.cssSelector("fieldset div:nth-child(2) div span"), 10);
		assertEquals("パスワードは必須です。", webDriver.findElement(By.cssSelector("fieldset div:nth-child(2) div span")).getText());
		File file = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
		try {
			Files.copy(file.toPath(), Paths.get("./evidence/ケース2テスト3エビデンス.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		loginId = webDriver.findElement(By.name("loginId"));
		loginId.clear();
		WebElement loginPass = webDriver.findElement(By.name("password"));
		loginPass.sendKeys("fail");
		classElement = webDriver.findElement(By.cssSelector(".btn.btn-primary"));
		classElement.click();
		visibilityTimeout(By.cssSelector("fieldset div:nth-child(1) div span"), 10);
		assertEquals("ログインIDは必須です。", webDriver.findElement(By.cssSelector("fieldset div:nth-child(1) div span")).getText());
		file = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
		try {
			Files.copy(file.toPath(), Paths.get("./evidence/ケース2テスト2エビデンス.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		loginId = webDriver.findElement(By.name("loginId"));
		loginId.sendKeys("間違い太郎");
		loginPass = webDriver.findElement(By.name("password"));
		loginPass.sendKeys("fail");
		classElement = webDriver.findElement(By.cssSelector(".btn.btn-primary"));
		classElement.click();
		visibilityTimeout(By.cssSelector(".form-horizontal span"), 10);
		assertEquals("* ログインに失敗しました。", webDriver.findElement(By.cssSelector(".form-horizontal span")).getText());
		file = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
		try {
			Files.copy(file.toPath(), Paths.get("./evidence/ケース2テスト4エビデンス.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		loginPass = webDriver.findElement(By.name("password"));
		loginPass.sendKeys("fail");
		classElement = webDriver.findElement(By.cssSelector(".btn.btn-primary"));
		classElement.click();
		visibilityTimeout(By.cssSelector(".form-horizontal span"), 10);
		loginPass = webDriver.findElement(By.name("password"));
		loginPass.sendKeys("fail");
		classElement = webDriver.findElement(By.cssSelector(".btn.btn-primary"));
		classElement.click();
		visibilityTimeout(By.cssSelector(".form-horizontal span"), 10);
		assertEquals("* 規定の回数を超えたため、アカウントにロックがかかりました。しばらくたってから再度お試しください。", webDriver.findElement(By.cssSelector(".form-horizontal span")).getText());
		file = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
		try {
			Files.copy(file.toPath(), Paths.get("./evidence/ケース2テスト5エビデンス.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		loginId = webDriver.findElement(By.name("loginId"));
		loginId.clear();
		loginId.sendKeys("StudentAA01");
		loginPass = webDriver.findElement(By.name("password"));
		loginPass.sendKeys("StudentAA01");
		classElement = webDriver.findElement(By.cssSelector(".btn.btn-primary"));
		classElement.click();
		visibilityTimeout(By.cssSelector(".form-horizontal span"), 10);
		assertEquals("* 規定の回数を超えたため、アカウントにロックがかかりました。しばらくたってから再度お試しください。", webDriver.findElement(By.cssSelector(".form-horizontal span")).getText());
		file = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
		try {
			Files.copy(file.toPath(), Paths.get("./evidence/ケース2テスト6エビデンス.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
