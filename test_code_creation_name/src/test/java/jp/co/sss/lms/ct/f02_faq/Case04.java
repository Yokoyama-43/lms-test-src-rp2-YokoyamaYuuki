package jp.co.sss.lms.ct.f02_faq;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * 結合テスト よくある質問機能
 * ケース04
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース04 よくある質問画面への遷移")
public class Case04 {

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
		goTo("http://localhost:8080/lms/");
		assertEquals("ログイン | LMS", webDriver.getTitle());
		getEvidence(new Object(){});
	}

	@Test
	@Order(2)
	@DisplayName("テスト02 初回ログイン済みの受講生ユーザーでログイン")
	void test02() {
		WebElement loginId = webDriver.findElement(By.name("loginId"));
		WebElement loginPass = webDriver.findElement(By.name("password"));
		WebElement loginPress = webDriver.findElement(By.cssSelector(".btn.btn-primary"));
		loginId.sendKeys("StudentAA01");
		loginPass.sendKeys("Sensei7854");
		loginPress.click();
		pageLoadTimeout(10);
		assertEquals("コース詳細 | LMS", webDriver.getTitle());
		getEvidence(new Object(){});
	}

	@Test
	@Order(3)
	@DisplayName("テスト03 上部メニューの「ヘルプ」リンクからヘルプ画面に遷移")
	void test03() {
		WebElement MenuPress = webDriver.findElement(By.cssSelector(".nav.navbar-nav > li:nth-child(4)"));
		MenuPress.click();
		visibilityTimeout(By.cssSelector(".nav.navbar-nav > li:nth-child(4) ul li:nth-child(4)"), 10);
		WebElement HelpPress = webDriver.findElement(By.cssSelector(".nav.navbar-nav > li:nth-child(4) ul li:nth-child(4)"));
		HelpPress.click();
		pageLoadTimeout(10);
		assertEquals("ヘルプ | LMS", webDriver.getTitle());
		getEvidence(new Object(){});
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「よくある質問」リンクからよくある質問画面を別タブに開く")
	void test04() {
		WebElement QuestionPress = webDriver.findElement(By.cssSelector("div:nth-child(4) .panel-body p a"));
		QuestionPress.click();
		Object[] windowHandles = webDriver.getWindowHandles().toArray();
		webDriver.switchTo().window((String)windowHandles[1]);
		assertEquals("よくある質問 | LMS", webDriver.getTitle());
		getEvidence(new Object(){});
	}

}
