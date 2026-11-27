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
 * ケース06
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース06 カテゴリ検索 正常系")
public class Case06 {

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
		pageLoadTimeout(100);
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
		pageLoadTimeout(100);
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

	@Test
	@Order(5)
	@DisplayName("テスト05 カテゴリ検索で該当カテゴリの検索結果だけ表示")
	void test05() {
		WebElement CategoryPress = webDriver.findElement(By.cssSelector("#main > div.well.bs-component > fieldset > ul:nth-of-type(2) > li > a"));
		CategoryPress.click();
		pageLoadTimeout(100);
		WebElement SearchAnswer1 = webDriver.findElement(By.cssSelector("#question-h0 > dt > span:nth-child(2)"));
		WebElement SearchAnswer2 = webDriver.findElement(By.cssSelector("#question-h1 > dt > span:nth-child(2)"));
		WebElement SearchAnswer3 = webDriver.findElement(By.cssSelector("#question-h2 > dt > span:nth-child(2)"));
		assertEquals("セルフ・キャリアドック制度とは何か", SearchAnswer1.getText());
		assertEquals("事業所が変わった場合、何かしら手続きをする必要がありますか？", SearchAnswer2.getText());
		assertEquals("助成金書類の作成方法が分かりません", SearchAnswer3.getText());
		getEvidence(new Object(){});
	}

	@Test
	@Order(6)
	@DisplayName("テスト06 検索結果の質問をクリックしその回答を表示")
	void test06() {
		scrollBy("1000");
		WebElement CategoryAnswer = webDriver.findElement(By.cssSelector("#question-h0 > dt"));
		CategoryAnswer.click();
		visibilityTimeout(By.cssSelector("#question-h0 > dd > span:nth-child(2)"), 10);
		WebElement QuestionAnswer = webDriver.findElement(By.cssSelector("#question-h0 > dd > span:nth-child(2)"));
		assertEquals(true, QuestionAnswer.isDisplayed());
		getEvidence(new Object(){});
	}

}
