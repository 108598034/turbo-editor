# Software Testing and Verification - SELab(Turbo Editor)
[ToC]

## Team Info
### Resource
[專案Github](https://github.com/Sean2525/turbo-editor)
[雲端硬碟](https://drive.google.com/drive/folders/1H2qXh7xxjiQuN8U4cg43ElWZm-D4jTcQ?usp=sharing)

### Schedule
:::spoiler 課程行事曆
![](https://i.imgur.com/UTuiqEz.png)
:::

:::spoiler 團隊行事曆
![](https://i.imgur.com/a92JpEE.png)
:::

### AUT (Application Under Test)
> [Turbo Editor(github)](https://github.com/vmihalachi/turbo-editor)
> [Turbo Editor(Google Play)](https://play.google.com/store/apps/details?id=com.maskyn.fileeditorpro)

### Test Tool
> [Espresso](https://developer.android.com/training/testing/espresso)
> [UI Automator](https://developer.android.com/training/testing/ui-automator)

## Project Phases

:::spoiler Phase 5
## Phase 5 - Final Presentation
### Goal
- [x] Specify your planned test criteria for the project
- [x] Describe the methods used to design the test cases (specify why choose the methods and show how to derive the test cases by giving an example)
- [x] List all the test cases and their covered features to be tested
- [x] Specify the testing tools used for the project
- [x] Show the test scripts of sample test cases
- [x] A short Live demo of the test execution (please provide video for the execution of all test cases)
- [x] Show the test results (show any defects discovered)
- [x] Show the test coverage of the features/combinations of features/user requirements
- [x] Pass/Fail recommendation of the items under test based on your own criteria
- [x] Team member contributions and/or feedbacks
- [x] Lessons Learned (optional by 陳冠穎)

### Internal Due ==6/29(MON.) 23:59==
- [x] Final Presentation PPT ==(陳冠穎)==
- [x] Demo Preparation ==(劉孝忠、古兆瑋)==
- [x] Push All Files to Github ==(劉孝忠、陳冠穎、古兆瑋)==

### External Due ==6/30(TUE.) 23:59==
> TA's name: ==林冠璋==
> TA's e-mail: ==t107598020@ntut.org.tw==
> Spec: 無須影印，電子檔案即可==

### Instructor's Suggestions
- Coverage不要100%，因為有infeasible以及有些太trivial（setter, getter...）不需要測。測到100%成本太高且不一定有效。
- CFG是針對method做單元測試，use case是system level，兩者是不同層級，若是use case轉成graph較有幫助。
- ISP輸入太多要選擇basis choice或each choice等等，測試要先設計好，知道目的及原因。
- System level 的測試覆蓋率有達60幾%就很好了
:::

:::spoiler Phase 4
## Phase 4 - Test Case Implementation (with Code Coverage)
### Goal
- [X] Revise Test Case Specifications (if applicable)
- [X] Implement Test Case Specifications as test scripts
- [X] Perform the functional testing for SUT
- [X] Report any bugs found using a Bug Tracking System (BTS) (optional)
- [X] Analyze test results, including test coverage and bug statistics (if any)
- [X] Submit test result summary

### Internal Due ==6/22(MON.) 23:59==
- [X] Software Test Plan ==(陳冠穎)==
- [X] Test Design Specification ==(陳冠穎、古兆瑋)==
- [X] Test Case Specification ==(陳冠穎、古兆瑋)==
- [X] Test Case Implementation ==(劉孝忠、陳冠穎、古兆瑋)==

### External Due ==6/23(TUE.) 23:59==
> TA's name: ==林冠璋==
> TA's e-mail: ==t107598020@ntut.org.tw==
> Spec: 無須影印，電子檔案即可==
:::

:::spoiler Phase 3
## Phase 3 - Revise Documents & Re-Design Test Case(with more ways of approach)
### Goal
- Submit Test Case Specifications (TCS)
- Revise Test Plan and Test Design Specifications (if applicable)

### Internal Due ==5/19(TUE.) 23:59==
- [x] Software Test Plan ==(陳冠穎)==
- [x] Test Design Specification ==(陳冠穎)==
- [x] Test Case Specification ==(古兆瑋)==
- [x] Test Case Approach Detail ==(陳冠穎)== 
- [ ] Test Case Implementation ==(劉孝忠)==

### External Due ==5/20(WED.) 23:59==
> TA's name: ==林冠璋==
> TA's e-mail: ==t107598020@ntut.org.tw==
> Spec: 無須影印，電子檔案即可==

:::

:::spoiler Phase 2
## Phase 2 -  Test Design Specifications(TDS) & Test Case Specifications(TCS)
### Internal Due ==4/19(SUN.) 23:59==
- [x] Test Design Specification ==(陳冠穎)==
- [x] Test Case Specification ==(古兆瑋)==
- [x] Test Case Approach Detail ==(陳冠穎)== 
- [x] Test Case Implementation ==(劉孝忠)==
- [x] Midterm Presentation PPT ==(陳冠穎)==

#### Reference:
> [Software Testing Project](https://myweb.ntut.edu.tw/~cliu/courses/st/project/project.htm)
> [Software Test Document Templates](https://myweb.ntut.edu.tw/~cliu/courses/st/project/templates.htm)


### Midterm Presentation ==4/21(TUE.) & 4/22(WED.)==
- Specify SUT and Use Case(or feature list), test tools, and responsibility of team members
- Specify (pass/fail) test criteria for the project (e.g., achieve ??% coverage and the number of detected bugs (in terms of severity) is less than ??) 
- Describe the approach for the project (including methods used to design the test cases, specify why choose the methods and show how to derive the test cases by giving an example)
- You may use and combine different methods, such as user scenarios, ISP, graph coverage (e.g., state machine), logic condition, and etc., together to design test cases for each feature to be tested
- List all the project activities and plan schedule

### External Due ==4/22(WED.) 23:59==
> TA's name: ==林冠璋==
> TA's e-mail: ==t107598020@ntut.org.tw==
> Spec: 無須影印，電子檔案即可==

### Instructor's Advice
> Criteria: Coverage 決定測試案例使否足夠、Fail 以 Bug 大小決定是否嚴重成為測試失敗或不在意
> Test Case 不一定要照 Template
> Graph for Source-code, statement coverage 達 80%
> 故意玩壞APP或操作一些很奇葩的User Scenario
:::

:::spoiler Phase 1
## Phase 1 - Software Test Plan(STP)

### Internal Due ==4/7(SUN.) 23:59==
大前提：（）
- [x] PC上的模擬器試跑
- [x] Turbo Editor測試
- [x] Appium試跑
- [x] 黑箱測(稍微試跑、盡可能嘗試多種test)
- [ ] 白箱測(稍微, with code coverage perhaps?)

文件需求 ==（盡量詳細敘述）==：（）
- [ ] 規格(Specification)
- [x] 功能列表(Feature List)
- [x] 測試工具及方法("HOW" to test)


待定案：（==陳冠穎==擬草案）
- [x] Team Title
    - SELab-ML(means Machine Learning)
    - SELab-1(or 2, depends)
    - TurboEditor
    - TurboTester
    - MonkeyTester
    - Za Warudo
    - ...(可自由列舉)
- [x] Staffing & Training
    - [x] Training Plan
        - 內容：Turbo Editor操作流程 / Appium測試方法
        - 時間：（預計期中考前）
        - 地點：宏裕科技大樓1623室
        - 講師：劉孝忠
        - 學員：古兆瑋、陳冠穎
    - [x] Roles and Responsibility
- [x] Schedule & Milestones
    - [x] 依課程行事歷安排3次Milestones
    - [x] 內部人員訓練、實際開發的時程表

### External Due ==4/8(WED.) 23:59==
> TA's name: ==林冠璋==
> TA's e-mail: ==t107598020@ntut.org.tw==
> Spec: 無須影印，電子檔案即可==


### 規格
Android Studio 3.6.2
Appium 1.15.1
CPU: intel i5-4590 or higher
RAM: 8GB or higher

### 功能列表
    New File 
        - Dont save / Cancel /Save
    -
    Open a file
    Rename
    Save as 
    Go to line
    Find (REGEX, MATCH CASE, REPLACE)
    View it on web
    Share?
    Info
    Undo / Redo
    -
    Preference:
    Line Numbers/
    Syntax Highlight/
    Wrap Content/
    Use Monospace/
    Read Only/
    Font Size/
    Theme/
    Accessory View/
    Use the storage Access Framework/
    Keyboard Suggestion and Swipe/
    Auto Save/
    Encoding/
    Ignore Back Button/
    Split The Text If Too Long/
    Fullscreen Mode/
    Open Last Viewed File At Startup


### 測試工具及方法

#### 一. 安裝環境
需求: 
1. Android studio
2. Android Virtual Device Manager
3. SDK Platforms: Android 10.0(Q)
4. SDK Tools: 
    * Android SDK Build-Tools
    * Android SDK Command-line Tools
    * Android Emulator
    * Android SDK Platform-Tools
    * Android SDK Tools(去 [官網](https://developer.android.com/studio) 下載Command line tools only 解壓縮後丟入sdk 然後進行更新)
    * HAXM
5. Appium:
    * Nodejs
    * npm install -g appium
    * npm install -g appium-doctor
    * set ANDROID_HOME
    * set JAVA_HOME
    * set path %ANDROID_HOME%/platform-tools
    * set path %ANDROID_HOME%/tools
    * set path %JAVA_HOME%/bin
    * install ffmpeg

#### 二. 用Android studio開啟Turbo Editor專案
1. 開啟專案後
在`local.properties` 檔案中新增 `keystore.props.file=../key.properties`
新建`key.properties` 在project home目錄
```
storeFile=./key
alias=***
storePass=***
pass=***
```
2. 重開Android studio讓gradle安裝還有build專案
3. 新建java module test
在libs中添加
commons-lang3-3.10.jar
hamcrest-core-1.3.jar
java-client-7.3.0.jar
junit-4.13.jar
selenium-server-standalone-3.141.59.jar
並且右鍵點擊Add as Library
4. 在src下的主檔案新增測試
```java

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Set;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class appTest {
    private AppiumDriver<WebElement> driver;

    @Before
    public void setUp() throws Exception {
        // set up appium
        File classpathRoot = new File(System.getProperty("user.dir"));
        File app = new File(classpathRoot, "../app-pro/build/outputs/apk/debug/app-pro-debug.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName","emulator-5554");
        capabilities.setCapability("automationName","UiAutomator2");
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("appPackage", "com.maskyn.fileeditorpro");
        capabilities.setCapability("appActivity", "HomeActivity");
        driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }
    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void createNewFile() throws InterruptedException {
        Thread.sleep(3000);
        WebElement newFile = driver.findElement(By.xpath("//android.widget.TextView[@text='New file']"));
        newFile.click();
        Thread.sleep(6000);
        WebElement text = driver.findElement(By.xpath("//android.widget.EditText[@resource-id='android:id/title']"));
        WebElement button = driver.findElement(By.xpath("//android.widget.Button[@resource-id='android:id/button1']"));
        text.sendKeys("testfile");
        button.click();
        Thread.sleep(6000);
    }

}

```

* 在SetUp的時候用DesiredCapabilities輸入
    * deviceName(The kind of mobile device or emulator to use)
    * automationName(Which automation engine to use)
    * app(The absolute local path of apk)
    * appPackage(Java package o)f the Android app you want to run.)
    * appActivity(Activity name for the Android activity you want to launch from your package.)
    
* 用AndroidDriver與本地端的Appium-server連結
* 利用driver.findElement來查詢想要的物件進行操作

三. 利用appium desktop來查詢相對物件
https://ithelp.ithome.com.tw/m/articles/10224099
https://github.com/appium/appium/blob/master/docs/en/writing-running-appium/caps.md
:::