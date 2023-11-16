package com.ssafy.hotplace;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class CrawlingTest {
	public static void main(String[] args) throws InterruptedException {
		int num = 0;
		
		new CrawlingThread(num++, "유진이네 찜닭").start();
		new CrawlingThread(num++, "매취랑 수내점").start();
		new CrawlingThread(num++, "매취랑 정자점").start();
		new CrawlingThread(num++, "매취랑 서현점").start();
		new CrawlingThread(num++, "매취랑 야탑점").start();
		new CrawlingThread(num++, "매취랑 분당점").start();
		new CrawlingThread(num++, "매취랑 가락점").start();
		new CrawlingThread(num++, "매취랑 평택고덕점").start();
		new CrawlingThread(num++, "유진이네 찜닭").start();
		new CrawlingThread(num++, "매취랑 수내점").start();
		new CrawlingThread(num++, "매취랑 정자점").start();
		new CrawlingThread(num++, "매취랑 서현점").start();
		new CrawlingThread(num++, "매취랑 야탑점").start();
		new CrawlingThread(num++, "매취랑 분당점").start();
		new CrawlingThread(num++, "매취랑 가락점").start();
		new CrawlingThread(num++, "매취랑 평택고덕점").start();
		new CrawlingThread(num++, "유진이네 찜닭").start();
		new CrawlingThread(num++, "매취랑 수내점").start();
		new CrawlingThread(num++, "매취랑 정자점").start();
		new CrawlingThread(num++, "매취랑 서현점").start();
		new CrawlingThread(num++, "매취랑 야탑점").start();
		new CrawlingThread(num++, "매취랑 분당점").start();
		new CrawlingThread(num++, "매취랑 가락점").start();
		new CrawlingThread(num++, "매취랑 평택고덕점").start();
		new CrawlingThread(num++, "유진이네 찜닭").start();
		new CrawlingThread(num++, "매취랑 수내점").start();
		new CrawlingThread(num++, "매취랑 정자점").start();
		new CrawlingThread(num++, "매취랑 서현점").start();
		new CrawlingThread(num++, "매취랑 야탑점").start();
		new CrawlingThread(num++, "매취랑 분당점").start();
		new CrawlingThread(num++, "매취랑 가락점").start();
		new CrawlingThread(num++, "매취랑 평택고덕점").start();
		new CrawlingThread(num++, "유진이네 찜닭").start();
		new CrawlingThread(num++, "매취랑 수내점").start();
		new CrawlingThread(num++, "매취랑 정자점").start();
		new CrawlingThread(num++, "매취랑 서현점").start();
		new CrawlingThread(num++, "매취랑 야탑점").start();
		new CrawlingThread(num++, "매취랑 분당점").start();
		new CrawlingThread(num++, "매취랑 가락점").start();
		new CrawlingThread(num++, "매취랑 평택고덕점").start();
		new CrawlingThread(num++, "유진이네 찜닭").start();
		new CrawlingThread(num++, "매취랑 수내점").start();
		new CrawlingThread(num++, "매취랑 정자점").start();
		new CrawlingThread(num++, "매취랑 서현점").start();
		new CrawlingThread(num++, "매취랑 야탑점").start();
		new CrawlingThread(num++, "매취랑 분당점").start();
		new CrawlingThread(num++, "매취랑 가락점").start();
		new CrawlingThread(num++, "매취랑 평택고덕점").start();
		new CrawlingThread(num++, "유진이네 찜닭").start();
		new CrawlingThread(num++, "매취랑 수내점").start();
		new CrawlingThread(num++, "매취랑 정자점").start();
		new CrawlingThread(num++, "매취랑 서현점").start();
		new CrawlingThread(num++, "매취랑 야탑점").start();
		new CrawlingThread(num++, "매취랑 분당점").start();
		new CrawlingThread(num++, "매취랑 가락점").start();
		new CrawlingThread(num++, "매취랑 평택고덕점").start();
		new CrawlingThread(num++, "유진이네 찜닭").start();
		new CrawlingThread(num++, "매취랑 수내점").start();
		new CrawlingThread(num++, "매취랑 정자점").start();
		new CrawlingThread(num++, "매취랑 서현점").start();
		new CrawlingThread(num++, "매취랑 야탑점").start();
		new CrawlingThread(num++, "매취랑 분당점").start();
		new CrawlingThread(num++, "매취랑 가락점").start();
		new CrawlingThread(num++, "매취랑 평택고덕점").start();
		new CrawlingThread(num++, "유진이네 찜닭").start();
		new CrawlingThread(num++, "매취랑 수내점").start();
		new CrawlingThread(num++, "매취랑 정자점").start();
		new CrawlingThread(num++, "매취랑 서현점").start();
		new CrawlingThread(num++, "매취랑 야탑점").start();
		new CrawlingThread(num++, "매취랑 분당점").start();
		new CrawlingThread(num++, "매취랑 가락점").start();
		new CrawlingThread(num++, "매취랑 평택고덕점").start();
		new CrawlingThread(num++, "유진이네 찜닭").start();
		new CrawlingThread(num++, "매취랑 수내점").start();
		new CrawlingThread(num++, "매취랑 정자점").start();
		new CrawlingThread(num++, "매취랑 서현점").start();
		new CrawlingThread(num++, "매취랑 야탑점").start();
		new CrawlingThread(num++, "매취랑 분당점").start();
		new CrawlingThread(num++, "매취랑 가락점").start();
		new CrawlingThread(num++, "매취랑 평택고덕점").start();
		
	}
}
class  CrawlingThread extends Thread {
	private int threadNum = 0;
	
	public CrawlingThread(int num, String keyword) {
		this.threadNum = num;
		this.keyword = keyword;
	}
	
	@Override
	public void run() {
		try {
			process();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private WebDriver driver;

	private String keyword;
	private static final String url = "https://map.naver.com/v5/search/";

	public void process() throws InterruptedException, IOException {

		// 크롬 드라이버 세팅 (드라이버 설치 경로 입력)
		System.setProperty("webdriver.chrome.driver", "/SSAFY/chromedriver-win64/chromedriver.exe");

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");

		// 브라우저 선택
		options.setHeadless(true);
		driver = new ChromeDriver(options);

		getDataList();

		// 탭 닫기
		driver.close();
		// 브라우저 닫기
		driver.quit();
	}

	// 데이터 가져오기
	private void getDataList() throws InterruptedException, IOException {
		System.out.println("???????????????????????????????1111");
		// (1) 브라우저에서 url로 이동한다.
		driver.get(url + keyword);
		System.out.println("???????????????????????????????2222");
		// 브라우저 로딩될 때까지 잠시 기다린다.
//		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
		Thread.sleep(3000);
		System.out.println("???????????????????????????????3333");
//		System.out.println(driver.getPageSource());
		// (2) 검색결과 iframe으로 frame을 바꾼다.
		driver.switchTo().frame(driver.findElement(By.cssSelector("iframe#entryIframe")));
		System.out.println("???????????????????????????????4444");
//		System.out.println(driver.getPageSource());
		// 검색 결과 장소 목록을 elements에 담는다.
		List<WebElement> elements = driver.findElements(By.cssSelector(".place_section " + "#_title span"));

		System.out.println("TestTest**********************************");
		System.out.println("elements.size() = " + elements.size());

		// (3) 첫번째 검색결과를 클릭한다.
		System.out.println("zzzzzzzzzzzzzz" + elements.get(0).getText());
		System.out.println("zzzzzzzzzzzzzz" + elements.get(0).getText());
		
		File f = new File("c://yangyu//test"+threadNum+".txt");
		f.createNewFile();
		
		
		FileWriter os = new FileWriter(f);
		os.write(elements.get(0).getText()+"\n");
		os.flush();
		os.close();

		// 요소가 로드될 때까지 기다린다.
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
		// 현재 프레임에서 상위 프레임으로 이동한다.
		driver.switchTo().defaultContent();

		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
		// (4) 상세정보가 나오는 프레임으로 이동한다.
		driver.switchTo().frame(driver.findElement(By.cssSelector("iframe#entryIframe")));

		// (5) 상세정보 프레임에서 주소 정보가 들어있는 곳으로 이동한다.
		List<WebElement> placeSectionContents = driver.findElements(By.cssSelector(".place_section_content"));
		WebElement homeElement = placeSectionContents.get(1);

		// (6) "주소" 버튼 요소를 찾아 클릭한다.
		WebElement addressButton = homeElement.findElement(By.className("LDgIH"));
		addressButton.click();

		// (7) "도로명"과 "지번" 정보가 들어있는 div 요소를 찾아서, 해당 정보를 가져온다.
		WebElement addressDiv = driver.findElement(By.className("Y31Sf"));
		List<WebElement> addressInfos = addressDiv.findElements(By.className("nQ7Lh"));

		for (WebElement addressInfo : addressInfos) {
			WebElement addressType = addressInfo.findElement(By.tagName("span"));
			String address = addressInfo.getText().replace(addressType.getText(), "").trim();
			System.out.println(addressType.getText() + " : " + address);
		}

	}
}
