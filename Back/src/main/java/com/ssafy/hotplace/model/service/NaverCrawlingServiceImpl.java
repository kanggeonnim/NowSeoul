package com.ssafy.hotplace.model.service;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;
import com.ssafy.hotplace.model.NaverCrawlingDTO;

@Service
public class NaverCrawlingServiceImpl implements NaverCrawlingService {
	private String URL = "https://map.naver.com/p/search/";
	private WebDriver driver;

	public void process(String place) {

		// 크롬 드라이버 세팅 (드라이버 설치 경로 입력)
		System.setProperty("webdriver.chrome.driver", "/SSAFY/chromedriver-win64/chromedriver.exe");

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");

		// 브라우저 선택
//		options.setHeadless(true);
		driver = new ChromeDriver(options);

		getDataList(place);

		// 탭 닫기
		driver.close();
		// 브라우저 닫기
		driver.quit();

	}

	private void getDataList(String place) {

		// (1) 브라우저에서 url로 이동한다.
		driver.get(URL + place);
		// 브라우저 로딩될 때까지 잠시 기다린다.
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));

		System.out.println(driver);
		System.out.println(driver.getCurrentUrl());
		System.out.println(driver.getPageSource());
		// (2) 검색결과 iframe으로 frame을 바꾼다.
		driver.switchTo().frame(driver.findElement(By.cssSelector("iframe#searchIframe")));

		// 검색 결과 장소 목록을 elements에 담는다.
		List<WebElement> elements = driver.findElements(By.className(".xlx7Q"));
		
		System.out.println("TestTest**********************************");
		System.out.println("elements.size() = " + elements.size());
		System.out.println(elements);
//		 (3) 첫번째 검색결과를 클릭한다.
		elements.get(0).click();

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

	@Override
	public NaverCrawlingDTO getNaverData(String place) throws IOException, InterruptedException {
		Document document = Jsoup.connect(URL).get();

		// section ul.type2 li
//		System.out.println(document);
//		Elements contents = document.select("span.xlx7Q");
//		System.out.println(URL + place);
//		System.out.println(contents);
//		contents.forEach(System.out::println);
//		
		process(place);
		NaverCrawlingDTO naver = NaverCrawlingDTO.builder().tel("").menu("").openingHours("").image("").build();

		return naver;
	}
}
