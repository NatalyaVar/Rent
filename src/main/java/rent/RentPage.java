package rent;

import com.codeborne.selenide.Configuration;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.BeforeClass;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static rent.MyUtils.*;



public class RentPage extends RentElements {
    public RentPage() throws IOException { }
    private static final Logger logger = LoggerFactory.getLogger(RentPage.class);
    private WebDriverWait wait = new WebDriverWait(driver, n);
    private WebDriverWait wait1 = new WebDriverWait(driver, n1);
    JavascriptExecutor je = (JavascriptExecutor) driver;

    static String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH:mm").format(Calendar.getInstance().getTime());
    static String reportsPathRentFull = timeStamp.replace(":", "_").replace("-", "_") + "_" + reportsPathRent;
    static String fileOutRentFull = reportsPathRentFull + fileOutRent;

    private static String userName = "#UserName";
    private static String password = "#Password";
//    private static String enterBtn = "#Login1 > tbody > tr > td > table > tbody > tr:nth-child(6) > td > a";
    private static String enterBtn = "#Login1 tr:nth-child(6) a";
    private static String pathInfoTxt = "Управление имуществом организации";
    private static String appname = "body > div.appname-wrap";
    private static String mainMenu = "#MainMenu";
    List<String> linesUrl = readFileLines(fileURL);
//    public String dashboardUrl = linesUrl.get(3);
    Actions actionList = new Actions(driver);
    public String urlDeals = linesUrl.get(2);
    public String urlHome = linesUrl.get(3);

    int i;
    int j;
    int k;
    int check;
    int num;
    int rnd;


    @BeforeClass
    public static void setUp() throws IOException {
        Configuration.reportsFolder = reportsPathRentFull;
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File(reportsPathRentFull + "0" + png));
        try {
            Writer fstream = new OutputStreamWriter(new FileOutputStream(fileOutRentFull, true), StandardCharsets.UTF_8);
            WebDriverRunner.setWebDriver(driver);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            try {
                //Перейти на домашнюю страницу со страницы авторизации
                List<String> lines = readFileLines(fileURL);
                String baseUrl = lines.get(1);
                String user = lines.get(4);
                String passwordTxt = lines.get(5);
                open(baseUrl);
                $(userName).shouldBe(visible).setValue(user);
                $(password).setValue(passwordTxt);
                $(enterBtn).shouldBe(visible).click();
                sleep(1000);
                $(mainMenu).shouldBe(visible);
                String title = driver.getTitle();
                File scrFile1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(scrFile1, new File(reportsPathRentFull + "0" + png));
                if (!(title.contains(pathInfoTxt))) {
                    fstream.write("\n;;Не открылась страница 'Управление имуществом организации'" + err);
                } else {
                    fstream.write("\n\n;;Открылась страница 'Управление имуществом организации'" + ok);
                    fstream.write("\n\nДата, время начала выполнения теста;Номер теста;Описание шагов;Результат выполнения");
                }
                sleep(1000);

            } finally {
                fstream.flush();
                fstream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


/*
 public DamageCommonPage() throws IOException { }

    private WebDriverWait wait = new WebDriverWait(driver, n);
    private WebDriverWait wait1 = new WebDriverWait(driver, n1);
    JavascriptExecutor je = (JavascriptExecutor) driver;

    static String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH:mm").format(Calendar.getInstance().getTime());
    static String reportsPathCommonFull = timeStamp.replace(":", "_").replace("-", "_") + "_" + reportsPathCommon;
    static String fileOutCommonFull = reportsPathCommonFull + fileOutCommon;

    private static String userName = "#UserName";
    private static String password = "#Password";
    private static String enterBtn = "#Login1 > tbody > tr > td > table > tbody > tr:nth-child(6) > td > a";
    private static String pathInfoTxt = "Система страхования";
    private static String pathInfo = "#PathInfo > a";
    int i;
    int j;
    int k;
    int check;

    List<String> linesUrl = readFileLines(fileURL);
    public String dashboardUrl = linesUrl.get(3);
    public String urlReestr = linesUrl.get(2);
    Actions actionList = new Actions(driver);

    @BeforeClass
    public static void setUp() throws IOException {
        Configuration.reportsFolder = reportsPathCommonFull;
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File(reportsPathCommonFull + "0" + png));
        try {
            Writer fstream = new OutputStreamWriter(new FileOutputStream(fileOutCommonFull, true), StandardCharsets.UTF_8);
            WebDriverRunner.setWebDriver(driver);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            try {
                //Перейти на домашнюю страницу со страницы авторизации
                List<String> lines = readFileLines(fileURL);
                String baseUrl = lines.get(1);
                String user = lines.get(4);
                String passwordTxt = lines.get(5);
                open(baseUrl);
                $(userName).shouldBe(visible).setValue(user);
                $(password).setValue(passwordTxt);
                $(enterBtn).shouldBe(visible).click();
                sleep(1000);
                $(pathInfo).shouldBe(visible);
                String title = driver.getTitle();
                File scrFile1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(scrFile1, new File(reportsPathCommonFull + "0" + png));
                if (!(title.contains(pathInfoTxt))) {
                    fstream.write("\n;;Не открылась страница 'Система страхования'" + err);
                } else {
                    fstream.write("\n\n;;Открылась страница 'Система страхования'" + ok);
                    fstream.write("\n\nДата, время начала выполнения теста;Номер теста;Описание шагов;Результат выполнения");
                }
                sleep(1000);

            } finally {
                fstream.flush();
                fstream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 1
 */


// 1
//Открыть существующую карточку (случайным образом) и проверить, что данные из реестра со страницы 'Анализ ущерба' - 'Дела по ОИ' правильно отображаются в карточке выбранного дела
//Проверяется интерфейс - текст кнопок
public void dealsPoisk() throws IOException {
    Random random = new Random();
    Configuration.reportsFolder = reportsPathRentFull;
    Writer fstream = new OutputStreamWriter(new FileOutputStream(fileOutRentFull, true), StandardCharsets.UTF_8);
    String testNumber = "1_";
    String beginLine = "\n" + timeStamp + ";1";
    fstream.write("\n" + beginLine + ";Управление имуществом организации - Сделки - Поиск"  + inf);
    String screenshotPathFull = reportsPathRentFull + testNumber;
    screen = 0;

    try {
        try {
            driver.navigate().to(urlDeals);
            wait1.until(ExpectedConditions.visibilityOf(dealsTable));
            k = 0;
            //Поиск
            $(searchWindowBtn).shouldBe(visible).click();
            $(clearBtn).shouldBe(visible).click();
            WebElement main = $(byText(mainDataTxt));
            String mainCollapseTxt = main.getAttribute("innerHTML");
            //Если свернуты "Основные данные" - развернуть
            if (mainCollapseTxt.contains("expand")) {
               main.click();
            }
            //Выбрать статус "Проект"
            $(statusDropDown).shouldBe(visible).click();
            WebElement statusBox = $(statusListBox).shouldBe(visible);
//            statusBox.findElement(By.linkText(projectTxt)).click();
            statusBox.findElement(By.cssSelector(statusProjectListBox)).click();
            //Найти
            $(searchButton).shouldBe(visible).click();

            //Сбросить сортировку
            $(splitBtn).shouldBe(visible).click();
            $(resetSortBtn).shouldBe(visible).click();
            sleep(1000);
            //Проверить статус - отсортировать по возр. и убыв. и проверить первую строку
            $(tableHead).shouldBe(visible).findElement(By.linkText(statusTxt)).click();
            sleep(1000);
            String status1 = $(firstRowStatus).shouldBe(visible).getText();
            if (!(status1.equals(projectTxt))) {
                screenCatch(screenshotPathFull, screen);
                fstream.write(beginLine + "   Должен быть статус " + projectTxt + "  фактически  " + status1 + "   ___" + testNumber + screen + err);
                screen = screen + 1;
                      }
        } catch (Exception e) {
            screenCatch(screenshotPathFull, screen);
            screenshot(reportsPath + testNumber + screen);
            e.printStackTrace();
            fstream.write(beginLine + ";Тест завершен с ошибкой" + err);
        }
    } finally {
//        closeExtraPages();
        fstream.write(beginLine);
//        driver.navigate().to(dashboardUrl);
        fstream.flush();
        fstream.close();

    }
}






//4
    //Создать новую сделку - Проект
    //Имущество - случайная строка на 1 странице. Вид процедуры - "По итогам аукциона"
    //Периодичность - "Круглосуточная"
    //ДОДЕЛАТЬ
    public void dealCreateAuction() throws IOException {
        Random random = new Random();
        Configuration.reportsFolder = reportsPathRentFull;
        Writer fstream = new OutputStreamWriter(new FileOutputStream(fileOutRentFull, true), StandardCharsets.UTF_8);
        String testNumber = "4_";
        logger.debug(testNumber);
        String beginLine = "\n" + timeStamp + ";4";
        fstream.write("\n" + beginLine + ";Управление имуществом организации - Создать сделку "  + inf);
        String screenshotPathFull = reportsPathRentFull + testNumber;
        screen = 0;

        try {
            try {
                driver.navigate().to(urlHome);
//                driver.switchTo().frame(0);
                wait1.until(ExpectedConditions.visibilityOf(buildingsLink));
                String buildTxt = $(buildingsLink).shouldBe(visible).getText();
                //Число зданий
                logger.debug("Имущество - здания - " + buildTxt);
                int buildNum = Integer.valueOf(buildTxt);
                if (buildNum > 30) {
                    num = 30;
                }
                else num = buildNum;
                Set<String> winOpen = driver.getWindowHandles(); //Запомнить открытые страницы, для перехода на вновь открытую страницу
                $(buildingsLink).shouldBe(visible).click();

                 rnd = random.nextInt((num - 1));
                logger.debug("Случайная строка " + rnd);
                String winNew = openPages(winOpen);
                //Перейти по ссылке
                driver.switchTo().window(winNew);
//                wait.until(ExpectedConditions.visibilityOf(buildHeader));
                List<WebElement> checkList = buildCheckBoxList;
                i = checkList.size();
                logger.debug("checkList.size()___" +  i);
                wait.until(ExpectedConditions.visibilityOf(buildHeader));
                List<WebElement> trList = buildTrList;
                WebElement trRnd = trList.get(rnd);
                trRnd.click();
                List<WebElement> tdList = trRnd.findElements(By.cssSelector("td"));
                       WebElement tdDiv = tdList.get(0).findElement(By.cssSelector("div"));
                       tdDiv.click();
                $(rentBtn).shouldBe(visible).click();
                driver.switchTo().frame(0);
                JavascriptExecutor je = (JavascriptExecutor) driver;
                je.executeScript("window.scrollBy(0,200);");
                //выбор банка
                String arrowBank = $(bankArrow).shouldBe(visible).getAttribute("className");
                logger.debug("arrowBank     " + arrowBank);
                Actions builder = new Actions(driver);

                if (arrowBank.contains(expand)) {
//
                    sleep(1000);
                    String str = bankDetailsLink.getText();
                    logger.debug(str);
                    $(bankDetailsLink).shouldBe(visible).click();
                    sleep(1000);
                    String arrowBank1 = $(bankArrow).shouldBe(visible).getAttribute("className");//Второй раз - не всегда разворачивается раздел
                    if (arrowBank1.contains(expand)) {

                        String str1 = bankDetailsLink.getText();
                        logger.debug(str1);
                        $(bankDetailsLink).shouldBe(visible).click();
                    }
                }
                sleep(1000);

                                //Выбрать банк
                $(selectBankDetailsBtn).shouldBe(visible).click();
                //Название банка
                List<String> lines2 = readFileLines(filename2);
                String bankName = lines2.get(1);
                driver.switchTo().frame(0);
                $(selectBankTable).shouldBe(visible).click();
//                $(selectBankTable).shouldBe(visible).find(By.linkText(bankName)).click();
                List<WebElement> trBankList = bankTrList;
                for (i = 1; i < trBankList.size(); i++) {
                    WebElement tr = trBankList.get(i);
                    String trTxt = tr.getText();
                    if (trTxt.contains(bankName)) {
                        tr.click();
                        break;
                    }
                }
                $(selectBankBtn).shouldBe(visible).click();
                sleep(2000);
                driver.switchTo().frame(0);
                //Раздел Объект
                List<WebElement> objectArrowsList = objectArrowList;
               WebElement objectArrow1 = objectArrowsList.get(1);
                String arrowObject = $(objectArrow1).shouldBe(visible).getAttribute("className");
                logger.debug("arrowObject     " + arrowObject);
                arrowDown(objectArrow1);
                $(technical).shouldBe(visible).setValue(technicalTxt);
                $(redevelopment).shouldBe(visible).setValue(redevelopmentTxt);
                //Раздел Сделка
                //Вид процедуры
                $(procedure).shouldBe(visible).click();
                $(procedureDrop).shouldBe(visible);
                sleep(600);
                List<WebElement> procedList = procedureList;
                num = procedList.size();
                logger.debug("procedList   " + num);
                String kindTxt = procedList.get(1).getText();
                logger.debug("Вид процедуры   " + kindTxt);
                procedList.get(1).click(); //По итогам аукциона
                //Периодичность использование
                logger.debug("периодичность использования   ");
                $(periodicity).shouldBe(visible).click();
//                $(periodicityDrop).shouldBe(visible);
                sleep(1000);
                List<WebElement> periodicityDropList= periodicityList;
                i = periodicityList.size();
                logger.debug("periodicityList.size()     " + i);
                periodicityDropList.get(1).click(); //Круглосуточная

                //Целевое использование
                $(typeUse).shouldBe(visible).click();
                $(typeUseDrop).shouldBe(visible);
                sleep(600);
                List<WebElement> targetList = typeUseDropList;
                num = targetList.size();
                rnd = random.nextInt((num - 1));
                logger.debug("Случайное целевое использование  " + rnd);
                WebElement targetRnd = targetList.get(rnd);
                String targetTxt = targetRnd.getText();
                logger.debug("Целевое использование  " + targetTxt);
                targetRnd.click();
                //Вид документа
                $(typeDoc).shouldBe(visible).click();
//                $(typeDocDrop).shouldBe(visible);
                sleep(600);
                List<WebElement> typeDocList = typeDocDropList;
                WebElement typeDocSelected = typeDocList.get(1);
                String typeDocTxt = typeDocSelected.getText();
                logger.debug("Вид документа   " + typeDocTxt);
                typeDocSelected.click();
                //Срок действия: окончание - текущая + 1000 дней
//                DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
                Date dateNow = new Date();
                Date endAction = DateUtils.addDays(dateNow, 1000);
                String endActionTxt = DateFormatUtils.format(endAction, "dd.MM.yyyy");
                logger.debug("endActionTxt  " + endActionTxt);
                $(dateEnd).shouldBe(visible).setValue(endActionTxt);
            //Начальная стоимость - случайное двоичное число от 1000 до 1000000
                double startSumRnd = 1000.00 + (1000000.00 - 1.00) * random.nextDouble();
                double startSumDbl = BigDecimal.valueOf(startSumRnd).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                String startSumTxt = Double.toString(startSumDbl).replace(".", ",");
                logger.debug("startSumTxt   " + startSumTxt);
                $(startCost).shouldBe(visible).setValue(startSumTxt);



                //Сохранить
                $(saveBtn).shouldBe(visible).click();
                List<WebElement> errorList = errorNoteList;
                num = errorList.size();
                if (num > 0) {
                    WebElement note = errorList.get(0);
                    String notetxt = note.getText();
                    logger.debug("Error  " + notetxt);
                }

                sleep(3000);



                //Раздел "Документы" - Загрузить файлы



                //Документы
                List<WebElement> trListDoc = documentsTrList;
                num = trListDoc.size();
                logger.debug("Число строк  " + num);
                sleep(1000);
                for (i = 0; i < num - 1; i++) {
                    logger.debug("Номер строки __" + i);
                    List<WebElement> trList1 = documentsTrList;
                    WebElement tr = trList1.get(i);
                    WebElement docType = tr.findElement(By.cssSelector(docTypeLocator));
                    String docTypeTxt = docType.getText();
                    logger.debug("Тип документа   " + docTypeTxt);
                    k = 0;
                    if (docTypeTxt.contains(techPassport)) {
                        k = 1;
                    }
                    if (docTypeTxt.contains(estimation)) {
                        k = 2;
                    }
                    switch (k) {
                        case 0:
                            loadDoc(tr,docPath);




                            break;
                        case 1:
                            logger.debug("case 1   " + techPassport);
                            WebElement img1 = tr.findElement(By.cssSelector(newFileLocator));
                            $(img1).shouldBe(visible).click();
                            sleep(2000);
                            $(dialogBtnOk).shouldBe(visible).click();
                            driver.switchTo().frame(0);
                            j = dialogBody.size();
                            logger.debug("Список dialogBody   " + j);
                            sleep(1000);
                            $("input#files").uploadFile(new File(planPath));
                            $(dialogLoadDataBtn).shouldBe(visible).click();
                            sleep(1000);
                            driver.switchTo().frame(0);
                            break;

                        case 2:
                            logger.debug("case 2   " + estimation);
                            loadDoc(tr,docPath);
                            //ввести номер и дату договор
                            List<WebElement> trList2 = documentsTrList;
                            WebElement tr2 = trList2.get(i);
                            String tr2Txt = tr2.getText();
                            logger.debug(tr2Txt);
                            WebElement numberEstimation = tr2.findElement(By.cssSelector(docNumberLocator));
                            String numberTxt = generateString(random, validNum, 2);
                            logger.debug("номер   " + numberTxt);
                            $(numberEstimation).shouldBe(visible).click();
                            actionList.clickAndHold(numberEstimation).sendKeys(Keys.chord(Keys.CONTROL, "a"), numberTxt).sendKeys(Keys.ENTER).release().build().perform();
                            sleep(1000);
                            Date dateNowEst = new Date();
                            String dateNowTxt = DateFormatUtils.format(dateNowEst, "dd.MM.yyyy");
//                            String dateEstimTxt =  dateNow.toString();
                            logger.debug(dateNowTxt);
                            List<WebElement> trList3 = documentsTrList;
                            WebElement tr3 = trList3.get(i);
                            String tr3Txt = tr3.getText();
                            logger.debug(tr3Txt);
                            WebElement dateEstimation = tr3.findElement(By.cssSelector(dateLocator));
                            $(dateEstimation).shouldBe(visible).click();
                            actionList.clickAndHold(dateEstimation).sendKeys(Keys.chord(Keys.CONTROL, "a"), dateNowTxt).sendKeys(Keys.ENTER).release().build().perform();
                            sleep(1000);
                            break;
                    }

                }


//                List<WebElement> docTr = documentsTr;
//                num = docTr.size();
//                logger.debug("Число строк   " + num);
//                WebElement tr = docTr.get(0);
//                $(tr).shouldBe(visible).click();
//                String str = tr.getText();
//                        logger.debug("str  " + str);
//                je.executeScript("window.scrollBy(0,200);");
////                WebElement loadImg = tr.findElement(By.cssSelector(tdImg));
////                $(loadImg).shouldBe(visible).click();
//                List<WebElement> docTdList = tr.findElements(By.cssSelector("td"));
////                WebElement td = tr.findElement(By.cssSelector("td.k-state-border-down"));
//                WebElement td = docTdList.get(3);
//                td.click();
//                sleep(2000);
////                driver.switchTo().frame(0);
//                $("input#files").uploadFile(new File(docPath));
//                sleep(1000);
/*
   writer.write(beginLine + ";Загрузка файла");
//        $("input#files").uploadFile(new File(path));
//        sleep(2000);
 */

/*
    //Документы
                List<WebElement> trList = documentsTrList;
                num = trList.size();
                logger.debug("Число строк  " + num);
                sleep(1000);
                for (i = 0; i < num - 1; i++) {
                    logger.debug("Номер строки __" + i);
                    List<WebElement> trList1 = documentsTrList;
                    WebElement tr = trList1.get(i);
                    WebElement docType = tr.findElement(By.cssSelector(docTypeLocator));
                    String docTypeTxt = docType.getText();
                    logger.debug("Тип документа   " + docTypeTxt);
                    k = 0;
                    if (docTypeTxt.contains(techPassport)) {
                        k = 1;
                    }
                    if (docTypeTxt.contains(estimation)) {
                        k = 2;
                    }
                    switch (k) {
                        case 0:
                            loadDoc(tr,docPath);




                            break;
                        case 1:
                            logger.debug("case 1   " + techPassport);
                            WebElement img1 = tr.findElement(By.cssSelector(newFileLocator));
                            $(img1).shouldBe(visible).click();
                            sleep(2000);
                            $(dialogBtnOk).shouldBe(visible).click();
                            driver.switchTo().frame(0);
                            j = dialogBody.size();
                            logger.debug("Список dialogBody   " + j);
                            sleep(1000);
                            $("input#files").uploadFile(new File(planPath));
                            $(dialogLoadDataBtn).shouldBe(visible).click();
                            sleep(1000);
                            driver.switchTo().frame(0);
                            break;

                        case 2:
                            logger.debug("case 2   " + estimation);
                            loadDoc(tr,docPath);
                            //ввести номер и дату договор
                            List<WebElement> trList2 = documentsTrList;
                            WebElement tr2 = trList2.get(i);
                            String tr2Txt = tr2.getText();
                            logger.debug(tr2Txt);
                            WebElement numberEstimation = tr2.findElement(By.cssSelector(docNumberLocator));
                            String numberTxt = generateString(random, validNum, 2);
                            logger.debug("номер   " + numberTxt);
                            $(numberEstimation).shouldBe(visible).click();
                            actionList.clickAndHold(numberEstimation).sendKeys(Keys.chord(Keys.CONTROL, "a"), numberTxt).sendKeys(Keys.ENTER).release().build().perform();
                            sleep(1000);
                            Date dateNowEst = new Date();
                            String dateNowTxt = DateFormatUtils.format(dateNowEst, "dd.MM.yyyy");
//                            String dateEstimTxt =  dateNow.toString();
                            logger.debug(dateNowTxt);
                            List<WebElement> trList3 = documentsTrList;
                            WebElement tr3 = trList3.get(i);
                            String tr3Txt = tr3.getText();
                            logger.debug(tr3Txt);
                            WebElement dateEstimation = tr3.findElement(By.cssSelector(dateLocator));
                            $(dateEstimation).shouldBe(visible).click();
                            actionList.clickAndHold(dateEstimation).sendKeys(Keys.chord(Keys.CONTROL, "a"), dateNowTxt).sendKeys(Keys.ENTER).release().build().perform();
                            sleep(1000);
                            break;
                    }

                }
 */












            } catch (Exception e) {
                logger.debug(e.getMessage());
                screenCatch(screenshotPathFull, screen);
                screenshot(reportsPath + testNumber + screen);
                e.printStackTrace();
                fstream.write(beginLine + ";Тест завершен с ошибкой" + err);
            }
        } finally {
//        closeExtraPages();
            fstream.write(beginLine);
           //        driver.navigate().to(dashboardUrl);
            fstream.flush();
            fstream.close();

        }
    }

//2
    //Создать новую сделку - Проект
    //Имущество - случайная строка на 1 странице. Вид процедуры - "Без проведения конкурентных процедур"
    //Периодичность - "Круглосуточная"
    //ДОДЕЛАТЬ
    public void dealCreate() throws IOException {
        Random random = new Random();
        Configuration.reportsFolder = reportsPathRentFull;
        Writer fstream = new OutputStreamWriter(new FileOutputStream(fileOutRentFull, true), StandardCharsets.UTF_8);
        String testNumber = "2_";
        logger.debug(testNumber);
        String beginLine = "\n" + timeStamp + ";2";
        fstream.write("\n" + beginLine + ";Управление имуществом организации - Создать сделку "  + inf);
        String screenshotPathFull = reportsPathRentFull + testNumber;
        screen = 0;

        try {
            try {
                driver.navigate().to(urlHome);
                driver.switchTo().frame(0);
                wait1.until(ExpectedConditions.visibilityOf(buildingsLink));
                String buildTxt = $(buildingsLink).shouldBe(visible).getText();
                //Число зданий
                logger.debug("Имущество - здания - " + buildTxt);
                int buildNum = Integer.valueOf(buildTxt);
                if (buildNum > 30) {
                    num = 30;
                }
                else num = buildNum;
                Set<String> winOpen = driver.getWindowHandles(); //Запомнить открытые страницы, для перехода на вновь открытую страницу
                $(buildingsLink).shouldBe(visible).click();

                 rnd = random.nextInt((num - 1));
                logger.debug("Случайная строка " + rnd);
                String winNew = openPages(winOpen);
                //Перейти по ссылке
                driver.switchTo().window(winNew);
//                wait.until(ExpectedConditions.visibilityOf(buildHeader));
                List<WebElement> checkList = buildCheckBoxList;
                i = checkList.size();
                logger.debug("checkList.size()" +  i);
                wait.until(ExpectedConditions.visibilityOf(buildHeader));
                List<WebElement> trList = buildTrList;
                WebElement trRnd = trList.get(rnd);
                trRnd.click();
                List<WebElement> tdList = trRnd.findElements(By.cssSelector("td"));
                       WebElement tdDiv = tdList.get(0).findElement(By.cssSelector("div"));
                       tdDiv.click();
                $(rentBtn).shouldBe(visible).click();
                driver.switchTo().frame(0);
                JavascriptExecutor je = (JavascriptExecutor) driver;
                je.executeScript("window.scrollBy(0,200);");
                //выбор банка
                String arrowBank = $(bankArrow).shouldBe(visible).getAttribute("className");
                logger.debug("arrowBank     " + arrowBank);
                Actions builder = new Actions(driver);

                if (arrowBank.contains(expand)) {
//
                    sleep(1000);
                    String str = bankDetailsLink.getText();
                    logger.debug(str);
                    $(bankDetailsLink).shouldBe(visible).click();
                    sleep(1000);
                    String arrowBank1 = $(bankArrow).shouldBe(visible).getAttribute("className");//Второй раз - не всегда разворачивается раздел
                    if (arrowBank1.contains(expand)) {

                        String str1 = bankDetailsLink.getText();
                        logger.debug(str1);
                        $(bankDetailsLink).shouldBe(visible).click();
                    }
                }
                sleep(1000);

                                //Выбрать банк
                $(selectBankDetailsBtn).shouldBe(visible).click();
                //Название банка
                List<String> lines2 = readFileLines(filename2);
                String bankName = lines2.get(1);
                driver.switchTo().frame(0);
                $(selectBankTable).shouldBe(visible).click();
//                $(selectBankTable).shouldBe(visible).find(By.linkText(bankName)).click();
                List<WebElement> trBankList = bankTrList;
                for (i = 1; i < trBankList.size(); i++) {
                    WebElement tr = trBankList.get(i);
                    String trTxt = tr.getText();
                    if (trTxt.contains(bankName)) {
                        tr.click();
                        break;
                    }
                }
                $(selectBankBtn).shouldBe(visible).click();
                sleep(2000);
                driver.switchTo().frame(0);
                //Раздел Объект
                arrowDown(objectArrow);
                $(technical).shouldBe(visible).setValue(technicalTxt);
                $(redevelopment).shouldBe(visible).setValue(redevelopmentTxt);
                //Раздел Сделка
                //Вид процедуры
                $(procedure).shouldBe(visible).click();
                $(procedureDrop).shouldBe(visible);
//                List<WebElement> procedList = procedureList;
                procedureList.get(3).click(); //Без проведения конкурентных процедур...
                //Периодичность использование
                $(periodicity).shouldBe(visible).click();
                $(periodicityDrop).shouldBe(visible);
                sleep(1000);
                List<WebElement> periodicityDropList= periodicityList;
                i = periodicityList.size();
                logger.debug("periodicityList.size()     " + i);
                periodicityDropList.get(1).click(); //Круглосуточная

                //Целевое использование
                $(typeUse).shouldBe(visible).click();
                $(typeUseDrop).shouldBe(visible);
                sleep(600);
                List<WebElement> targetList = typeUseDropList;
                num = targetList.size();
                rnd = random.nextInt((num - 1));
                logger.debug("Случайное целевое использование  " + rnd);
                WebElement targetRnd = targetList.get(rnd);
                String targetTxt = targetRnd.getText();
                logger.debug("Целевое использование  " + targetTxt);
                targetRnd.click();
                //Вид документа
                $(typeDoc).shouldBe(visible).click();
                $(typeDocDrop).shouldBe(visible);
                sleep(600);
                List<WebElement> typeDocList = typeDocDropList;
                WebElement typeDocSelected = typeDocList.get(1);
                String typeDocTxt = typeDocSelected.getText();
                logger.debug("Вид договора   " + typeDocTxt);
                typeDocSelected.click();
                //Дата окончания действия - текущая + 1000 дней
                DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
                Date dateNow = new Date();
                Date endAction = DateUtils.addDays(dateNow, 1000);
                String endActionTxt = DateFormatUtils.format(endAction, "dd.MM.yyyy");
                logger.debug("endActionTxt  " + endActionTxt);
                $(dateEnd).shouldBe(visible).setValue(endActionTxt);
            //Начальная стоимость - случайное двоичное число от 1000 до 1000000
                //Площадь по договору
                double startSumRnd = 1000.00 + (1000000.00 - 1.00) * random.nextDouble();
                double startSumDbl = BigDecimal.valueOf(startSumRnd).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                String startSumTxt = Double.toString(startSumDbl).replace(".", ",");
                logger.debug("startSumTxt   " + startSumTxt);
                $(startCost).shouldBe(visible).setValue(startSumTxt);

                //Раздел "Документы" - Загрузить файлы
                List<WebElement> docTr = documentsTr;
                num = docTr.size();
                logger.debug("Число строк   " + num);
                WebElement tr = docTr.get(0);
                $(tr).shouldBe(visible).click();
                String str = tr.getText();
                        logger.debug("str  " + str);
                je.executeScript("window.scrollBy(0,200);");
//                WebElement loadImg = tr.findElement(By.cssSelector(tdImg));
//                $(loadImg).shouldBe(visible).click();
                List<WebElement> docTdList = tr.findElements(By.cssSelector("td"));
//                WebElement td = tr.findElement(By.cssSelector("td.k-state-border-down"));
                WebElement td = docTdList.get(3);
                td.click();
                sleep(2000);
                driver.switchTo().frame(0);
                $("input#files").uploadFile(new File(docPath));
                sleep(1000);
/*
   writer.write(beginLine + ";Загрузка файла");
//        $("input#files").uploadFile(new File(path));
//        sleep(2000);
 */














            } catch (Exception e) {
                logger.debug(e.getMessage());
                screenCatch(screenshotPathFull, screen);
                screenshot(reportsPath + testNumber + screen);
                e.printStackTrace();
                fstream.write(beginLine + ";Тест завершен с ошибкой" + err);
            }
        } finally {
//        closeExtraPages();
            fstream.write(beginLine);
           //        driver.navigate().to(dashboardUrl);
            fstream.flush();
            fstream.close();

        }
    }


    //3
    //Создать новую сделку - Проект - существующий ИП
    //Имущество - кад. номер - из файла. Вид процедуры - "Без проведения конкурентных процедур" - ИП из файла
    //Периодичность - "Круглосуточная"
    public void dealCreateCadNumber() throws IOException {
        Random random = new Random();
        Configuration.reportsFolder = reportsPathRentFull;
        Writer fstream = new OutputStreamWriter(new FileOutputStream(fileOutRentFull, true), StandardCharsets.UTF_8);
        String testNumber = "3_";
        logger.debug(testNumber);
        String beginLine = "\n" + timeStamp + ";3";
        fstream.write("\n" + beginLine + ";Управление имуществом организации - Создать сделку с заданным кад. номером "  + inf);
        String screenshotPathFull = reportsPathRentFull + testNumber;
        screen = 0;
        List<String> lines = readFileLines(filename3);

        try {
            try {
                driver.navigate().to(urlHome);
//                sleep(2000);
//                driver.switchTo().frame(0);
                wait1.until(ExpectedConditions.visibilityOf(buildingsLink));
//                String buildTxt = $(buildingsLink).shouldBe(visible).getText();
                //Страница "Имущество"
//                logger.debug("Имущество - здания - " + buildTxt);
                Set<String> winOpen = driver.getWindowHandles(); //Запомнить открытые страницы, для перехода на вновь открытую страницу
                $(buildingsLink).shouldBe(visible).click();
                String winNew = openPages(winOpen);
                //Перейти по ссылке
                driver.switchTo().window(winNew);
                sleep(2000);
               //Поиск
                $(searchWindowBtn).shouldBe(visible).click();
                $(clearBtn).shouldBe(visible).click();
                sleep(1000);
                String cadTxt = lines.get(2);
                logger.debug("cadTxt   " + cadTxt);
                $(poiskCadNumber).shouldBe(visible).setValue(cadTxt);
                sleep(600);
                $(searchButton).shouldBe(visible).click();
                sleep(1000);
//                List<WebElement> checkList1 = buildCheckBoxList;
//                num = checkList1.size();
//                logger.debug("checkList1   " + num);
                WebElement check1 = buildCheck1;
                String sdfd = check1.getAttribute("innerHTML");
                logger.debug("check1 Attr   " + sdfd);
                check1.click();
//                $(cadNumCheckBox1).click();
//                List<WebElement> arrowsList = poiskArrows;
//                WebElement arrow = arrowsList.get(0);
//                arrowDown(arrow);
                /*
                WebElement main = $(byText(mainDataTxt));
                String mainCollapseTxt = main.getAttribute("innerHTML");
                //Если свернуты "Основные данные" - развернуть
                if (mainCollapseTxt.contains("expand")) {
                    main.click();
                }
                */


//                int buildNum = Integer.valueOf(buildTxt);
//                if (buildNum > 30) {
//                    num = 30;
//                }
//                else num = buildNum;
//                Set<String> winOpen = driver.getWindowHandles(); //Запомнить открытые страницы, для перехода на вновь открытую страницу
//                $(buildingsLink).shouldBe(visible).click();
//
//                rnd = random.nextInt((num - 1));
//                logger.debug("Случайная строка " + rnd);



//                wait.until(ExpectedConditions.visibilityOf(buildHeader));
//                List<WebElement> checkList = buildCheckBoxList;
//                i = checkList.size();
//                logger.debug("checkList.size()" +  i);
//                wait.until(ExpectedConditions.visibilityOf(buildHeader));
//                List<WebElement> trList = buildTrList;
//                WebElement trRnd = trList.get(rnd);
//                trRnd.click();
//                List<WebElement> tdList = trRnd.findElements(By.cssSelector("td"));
//                WebElement tdDiv = tdList.get(0).findElement(By.cssSelector("div"));
//                tdDiv.click();
                $(rentBtn).shouldBe(visible).click();
                driver.switchTo().frame(0);
                JavascriptExecutor je = (JavascriptExecutor) driver;
                je.executeScript("window.scrollBy(0,200);");
                //выбор банка
                String arrowBank = $(bankArrow).shouldBe(visible).getAttribute("className");
                logger.debug("arrowBank     " + arrowBank);
                Actions builder = new Actions(driver);

                if (arrowBank.contains(expand)) {
//
                    sleep(1000);
                    String str = bankDetailsLink.getText();
                    logger.debug(str);
                    $(bankDetailsLink).shouldBe(visible).click();
                    sleep(1000);
                    String arrowBank1 = $(bankArrow).shouldBe(visible).getAttribute("className");//Второй раз - не всегда разворачивается раздел
                    if (arrowBank1.contains(expand)) {

                        String str1 = bankDetailsLink.getText();
                        logger.debug(str1);
                        $(bankDetailsLink).shouldBe(visible).click();
                    }
                }
                sleep(1000);

                //Выбрать банк
                $(selectBankDetailsBtn).shouldBe(visible).click();
                //Название банка

                String bankName = lines.get(1);
                driver.switchTo().frame(0);
                $(selectBankTable).shouldBe(visible).click();
//                $(selectBankTable).shouldBe(visible).find(By.linkText(bankName)).click();
                List<WebElement> trBankList = bankTrList;
                for (i = 1; i < trBankList.size(); i++) {
                    WebElement tr = trBankList.get(i);
                    String trTxt = tr.getText();
                    if (trTxt.contains(bankName)) {
                        tr.click();
                        break;
                    }
                }
                $(selectBankBtn).shouldBe(visible).click();
                sleep(2000);
                driver.switchTo().frame(0);
                //Раздел Объект
                arrowDown(objectArrow);
                $(technical).shouldBe(visible).setValue(technicalTxt);
                $(redevelopment).shouldBe(visible).setValue(redevelopmentTxt);
                //Раздел Сделка
                //Вид процедуры
                $(procedure).shouldBe(visible).click();
                $(procedureDrop).shouldBe(visible);
//                List<WebElement> procedList = procedureList;
                procedureList.get(3).click(); //Без проведения конкурентных процедур...
                //Периодичность использование
                $(periodicity).shouldBe(visible).click();
                $(periodicityDrop).shouldBe(visible);
                sleep(1000);
                List<WebElement> periodicityDropList= periodicityList;
                i = periodicityList.size();
                logger.debug("periodicityList.size()     " + i);
                periodicityDropList.get(1).click(); //Круглосуточная

                //Целевое использование
                $(typeUse).shouldBe(visible).click();
                $(typeUseDrop).shouldBe(visible);
                sleep(600);
                List<WebElement> targetList = typeUseDropList;
                num = targetList.size();
                rnd = random.nextInt((num - 1));
                logger.debug("Случайное целевое использование  " + rnd);
                WebElement targetRnd = targetList.get(rnd);
                String targetTxt = targetRnd.getText();
                logger.debug("Целевое использование  " + targetTxt);
                targetRnd.click();
                //Вид документа
                $(typeDoc).shouldBe(visible).click();
                $(typeDocDrop).shouldBe(visible);
                sleep(600);
                List<WebElement> typeDocList = typeDocDropList;
                WebElement typeDocSelected = typeDocList.get(1);
                String typeDocTxt = typeDocSelected.getText();
                logger.debug("Вид договора   " + typeDocTxt);
                typeDocSelected.click();
                //Дата окончания действия - текущая + 1000 дней
                DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
                Date dateNow = new Date();
                Date endAction = DateUtils.addDays(dateNow, 1000);
                String endActionTxt = DateFormatUtils.format(endAction, "dd.MM.yyyy");
                logger.debug("endActionTxt  " + endActionTxt);
                $(dateEnd).shouldBe(visible).setValue(endActionTxt);
                //Начальная стоимость - случайное двоичное число от 1000 до 1000000
                double startSumRnd = 1000.00 + (1000000.00 - 1.00) * random.nextDouble();
                double startSumDbl = BigDecimal.valueOf(startSumRnd).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                String startSumTxt = Double.toString(startSumDbl).replace(".", ",");
                logger.debug("startSumTxt   " + startSumTxt);
                $(startCost).shouldBe(visible).setValue(startSumTxt);
                //Раздел "Контрагент" - выбрать ИП
                $(contractorInput).shouldBe(visible).click();
                sleep(600);
                $(contractorDrop).shouldBe(visible);
                List<WebElement> contractorsList = contractorDropList;
                WebElement individ = contractorsList.get(1);
                $(individ).shouldBe(visible).click();
                je.executeScript("window.scrollBy(0,400);");


/*
Случайные данные - заменила на данные из файла
                String innTxt = generateString(random, validNum, 12);
                  $(individInn).shouldBe(visible).setValue(innTxt);
                    String ogrnipTxt = generateString(random, validNum, 13);
                $(individOgrnip).shouldBe(visible).setValue(ogrnipTxt);
                //Дата регистрации - текущая дата минус 2000 дней
                Date reg = DateUtils.addDays(dateNow, -2000);
                String regTxt = DateFormatUtils.format(reg, "dd.MM.yyyy");
                logger.debug("Дата регистрации  " + regTxt);
                $(individDateReg).shouldBe(visible).setValue(regTxt);

                  //"Выдан" - текущая дата минус 1000 дней
                Date give = DateUtils.addDays(dateNow, -1000);
                String givenTxt = DateFormatUtils.format(give, "dd.MM.yyyy");
                logger.debug("Выдан  " + givenTxt);
                $(given).shouldBe(visible).setValue(givenTxt);
                //"Документ, удостоверяющий личность"
                $(individDocument).shouldBe(visible).click();
                sleep(600);
                $(individualDocumentDrop).shouldBe(visible);
                List<WebElement> documentsList = individualDocumentList;
                num = documentsList.size();
                rnd = random.nextInt((num - 1));
                WebElement doc = documentsList.get(rnd);
                doc.click();
                String seriesTxt = generateString(random, validNum, 2);
                $(individualSeries).shouldBe(visible).setValue(seriesTxt);
                 String docNumberTxt = generateString(random, validNum, 6);
                $(individualDocNumber).shouldBe(visible).setValue(docNumberTxt);
                String docGiven = lines.get(9);
                $(individualDocGiven).shouldBe(visible).setValue(docGiven);
                String docCodeTxt = generateString(random, validNum, 6);
                $(individualDocCode).shouldBe(visible).setValue(docCodeTxt);

                String indexTxt = generateString(random, validNum, 6);
                $(individualIndex).shouldBe(visible).setValue(indexTxt);
                $(indivCity).shouldBe(visible).setValue(city);
                $(indivAddress).shouldBe(visible).setValue(address);
                $(indivBank).shouldBe(visible).setValue(bank);
                String bikTxt = generateString(random, validNum, 9);
                $(indivBankBik).shouldBe(visible).setValue(bikTxt);
   String accountTxt = generateString(random, validNum, 20);
                $(indivAccount).shouldBe(visible).setValue(accountTxt);
String accountCorTxt = generateString(random, validNum, 20);
                $(indivCorAccount).shouldBe(visible).setValue(accountCorTxt);
 */

                //ФИО
                String surname = lines.get(3);
                String name = lines.get(4);
                String patronymic = lines.get(5);
                String surnameRod = lines.get(6);
                String nameRod = lines.get(7);
                String patronymicRod = lines.get(8);
                $(individSurname).shouldBe(visible).setValue(surname);
                $(individName).shouldBe(visible).setValue(name);
                $(individPatronymic).shouldBe(visible).setValue(patronymic);
                $(individSurnameRod).shouldBe(visible).setValue(surnameRod);
                $(individNameRod).shouldBe(visible).setValue(nameRod);
                $(individPatronymicRod).shouldBe(visible).setValue(patronymicRod);

              //ИНН
                String innTxt = lines.get(10);
                $(individInn).shouldBe(visible).setValue(innTxt);
                //ОГРНИП
                String ogrnipTxt = lines.get(11);
                $(individOgrnip).shouldBe(visible).setValue(ogrnipTxt);





                          //Сохранить
                $(saveBtn).shouldBe(visible).click();
                List<WebElement> errorList = errorNoteList;
                num = errorList.size();
                if (num > 0) {
                 WebElement note = errorList.get(0);
                        String notetxt = note.getText();
                    logger.debug("Error  " + notetxt);
                }

                //Диалоговое окно, что контрагент уже есть
                List<WebElement> dialog = dialogBody;
                num = dialogBody.size();
                logger.debug("Диалоговое окно  " + num);
                if (num > 0) {
                    List<WebElement> btnsList = dialogBtnsList;
                    WebElement elem = btnsList.get(0);
                    elem.click();
                }

                List<WebElement> successList = notificationSuccess;
                num = successList.size();
                logger.debug("Успешно  " + num);
                for(i = 0; i < num ; i++) {
                    String str = successList.get(i).getText();
                    logger.debug(i + "______" + str);
                }
                je.executeScript("window.scrollBy(0,-450);");
                sleep(1000);

                //Документы
                List<WebElement> trList = documentsTrList;
                num = trList.size();
                logger.debug("Число строк  " + num);
                sleep(1000);
                for (i = 0; i < num - 1; i++) {
                    logger.debug("Номер строки __" + i);
                    List<WebElement> trList1 = documentsTrList;
                    WebElement tr = trList1.get(i);
                    WebElement docType = tr.findElement(By.cssSelector(docTypeLocator));
                    String docTypeTxt = docType.getText();
                    logger.debug("Тип документа   " + docTypeTxt);
                    k = 0;
                    if (docTypeTxt.contains(techPassport)) {
                        k = 1;
                    }
                    if (docTypeTxt.contains(estimation)) {
                        k = 2;
                    }
                    switch (k) {
                        case 0:
                            loadDoc(tr,docPath);

                            /*
                                         WebElement img = tr.findElement(By.cssSelector(newFileLocator));
                            $(img).shouldBe(visible).click();
                            sleep(2000);
                            driver.switchTo().frame(0);
//                            List<WebElement> dialogList = dialogBody;
                            j = dialogBody.size();
                            logger.debug("Список dialogBody   " + j);
                                sleep(1000);
                                $("input#files").uploadFile(new File(docPath));
                            $(dialogLoadDataBtn).shouldBe(visible).click();
                            sleep(1000);
                            driver.switchTo().frame(0);
                             */

                            break;
                        case 1:
                            logger.debug("case 1   " + techPassport);
                            WebElement img1 = tr.findElement(By.cssSelector(newFileLocator));
                            $(img1).shouldBe(visible).click();
                            sleep(2000);
                            $(dialogBtnOk).shouldBe(visible).click();
                            driver.switchTo().frame(0);
                            j = dialogBody.size();
                            logger.debug("Список dialogBody   " + j);
                            sleep(1000);
                            $("input#files").uploadFile(new File(planPath));
                            $(dialogLoadDataBtn).shouldBe(visible).click();
                            sleep(1000);
                            driver.switchTo().frame(0);
                            break;

                        case 2:
                            logger.debug("case 2   " + estimation);
                            loadDoc(tr,docPath);
                            //ввести номер и дату договор
                            List<WebElement> trList2 = documentsTrList;
                            WebElement tr2 = trList2.get(i);
                            String tr2Txt = tr2.getText();
                            logger.debug(tr2Txt);
                            WebElement numberEstimation = tr2.findElement(By.cssSelector(docNumberLocator));
                            String numberTxt = generateString(random, validNum, 2);
                            logger.debug("номер   " + numberTxt);
                            $(numberEstimation).shouldBe(visible).click();
                            actionList.clickAndHold(numberEstimation).sendKeys(Keys.chord(Keys.CONTROL, "a"), numberTxt).sendKeys(Keys.ENTER).release().build().perform();
                            sleep(1000);
                            Date dateNowEst = new Date();
                            String dateNowTxt = DateFormatUtils.format(dateNowEst, "dd.MM.yyyy");
//                            String dateEstimTxt =  dateNow.toString();
                            logger.debug(dateNowTxt);
                            List<WebElement> trList3 = documentsTrList;
                            WebElement tr3 = trList3.get(i);
                            String tr3Txt = tr3.getText();
                            logger.debug(tr3Txt);
                            WebElement dateEstimation = tr3.findElement(By.cssSelector(dateLocator));
                            $(dateEstimation).shouldBe(visible).click();
                            actionList.clickAndHold(dateEstimation).sendKeys(Keys.chord(Keys.CONTROL, "a"), dateNowTxt).sendKeys(Keys.ENTER).release().build().perform();
                            sleep(1000);
                            break;
                    }

                }
//                $(contractProjectBtn).shouldBe(visible).click();








            } catch (Exception e) {
                logger.debug(e.getMessage());
                screenCatch(screenshotPathFull, screen);
                screenshot(reportsPath + testNumber + screen);
                e.printStackTrace();
                fstream.write(beginLine + ";Тест завершен с ошибкой" + err);
            }
        } finally {
//        closeExtraPages();
            fstream.write(beginLine);
            //        driver.navigate().to(dashboardUrl);
            fstream.flush();
            fstream.close();

        }
    }

    //3 - копия
    //Создать новую сделку - Проект - ИП - данные из файла
    //Имущество - кад. номер - из файла. Вид процедуры - "Без проведения конкурентных процедур" - ИП из файла
    //Периодичность - "Круглосуточная"
    public void dealCreateCadNumberIndFile() throws IOException {
        Random random = new Random();
        Configuration.reportsFolder = reportsPathRentFull;
        Writer fstream = new OutputStreamWriter(new FileOutputStream(fileOutRentFull, true), StandardCharsets.UTF_8);
        String testNumber = "3_";
        logger.debug(testNumber);
        String beginLine = "\n" + timeStamp + ";3";
        fstream.write("\n" + beginLine + ";Управление имуществом организации - Создать сделку с заданным кад. номером "  + inf);
        String screenshotPathFull = reportsPathRentFull + testNumber;
        screen = 0;
        List<String> lines = readFileLines(filename3);

        try {
            try {
                driver.navigate().to(urlHome);
//                driver.switchTo().frame(0);
                wait1.until(ExpectedConditions.visibilityOf(buildingsLink));
//                String buildTxt = $(buildingsLink).shouldBe(visible).getText();
                //Страница "Имущество"
//                logger.debug("Имущество - здания - " + buildTxt);
                Set<String> winOpen = driver.getWindowHandles(); //Запомнить открытые страницы, для перехода на вновь открытую страницу
                $(buildingsLink).shouldBe(visible).click();
                String winNew = openPages(winOpen);
                //Перейти по ссылке
                driver.switchTo().window(winNew);
                sleep(2000);
               //Поиск
                $(searchWindowBtn).shouldBe(visible).click();
                $(clearBtn).shouldBe(visible).click();
                sleep(1000);
                String cadTxt = lines.get(2);
                logger.debug("cadTxt   " + cadTxt);
                $(poiskCadNumber).shouldBe(visible).setValue(cadTxt);
                sleep(600);
                $(searchButton).shouldBe(visible).click();
                sleep(1000);
//                List<WebElement> checkList1 = buildCheckBoxList;
//                num = checkList1.size();
//                logger.debug("checkList1   " + num);
                WebElement check1 = buildCheck1;
                String sdfd = check1.getAttribute("innerHTML");
                logger.debug("check1 Attr   " + sdfd);
                check1.click();
//                $(cadNumCheckBox1).click();
//                List<WebElement> arrowsList = poiskArrows;
//                WebElement arrow = arrowsList.get(0);
//                arrowDown(arrow);
                /*
                WebElement main = $(byText(mainDataTxt));
                String mainCollapseTxt = main.getAttribute("innerHTML");
                //Если свернуты "Основные данные" - развернуть
                if (mainCollapseTxt.contains("expand")) {
                    main.click();
                }
                */


//                int buildNum = Integer.valueOf(buildTxt);
//                if (buildNum > 30) {
//                    num = 30;
//                }
//                else num = buildNum;
//                Set<String> winOpen = driver.getWindowHandles(); //Запомнить открытые страницы, для перехода на вновь открытую страницу
//                $(buildingsLink).shouldBe(visible).click();
//
//                rnd = random.nextInt((num - 1));
//                logger.debug("Случайная строка " + rnd);



//                wait.until(ExpectedConditions.visibilityOf(buildHeader));
//                List<WebElement> checkList = buildCheckBoxList;
//                i = checkList.size();
//                logger.debug("checkList.size()" +  i);
//                wait.until(ExpectedConditions.visibilityOf(buildHeader));
//                List<WebElement> trList = buildTrList;
//                WebElement trRnd = trList.get(rnd);
//                trRnd.click();
//                List<WebElement> tdList = trRnd.findElements(By.cssSelector("td"));
//                WebElement tdDiv = tdList.get(0).findElement(By.cssSelector("div"));
//                tdDiv.click();
                $(rentBtn).shouldBe(visible).click();
                driver.switchTo().frame(0);
                JavascriptExecutor je = (JavascriptExecutor) driver;
                je.executeScript("window.scrollBy(0,200);");
                //выбор банка
                String arrowBank = $(bankArrow).shouldBe(visible).getAttribute("className");
                logger.debug("arrowBank     " + arrowBank);
                Actions builder = new Actions(driver);

                if (arrowBank.contains(expand)) {
//
                    sleep(1000);
                    String str = bankDetailsLink.getText();
                    logger.debug(str);
                    $(bankDetailsLink).shouldBe(visible).click();
                    sleep(1000);
                    String arrowBank1 = $(bankArrow).shouldBe(visible).getAttribute("className");//Второй раз - не всегда разворачивается раздел
                    if (arrowBank1.contains(expand)) {

                        String str1 = bankDetailsLink.getText();
                        logger.debug(str1);
                        $(bankDetailsLink).shouldBe(visible).click();
                    }
                }
                sleep(1000);

                //Выбрать банк
                $(selectBankDetailsBtn).shouldBe(visible).click();
                //Название банка

                String bankName = lines.get(1);
                driver.switchTo().frame(0);
                $(selectBankTable).shouldBe(visible).click();
//                $(selectBankTable).shouldBe(visible).find(By.linkText(bankName)).click();
                List<WebElement> trBankList = bankTrList;
                for (i = 1; i < trBankList.size(); i++) {
                    WebElement tr = trBankList.get(i);
                    String trTxt = tr.getText();
                    if (trTxt.contains(bankName)) {
                        tr.click();
                        break;
                    }
                }
                $(selectBankBtn).shouldBe(visible).click();
                sleep(2000);
                driver.switchTo().frame(0);
                //Раздел Объект
                arrowDown(objectArrow);
                $(technical).shouldBe(visible).setValue(technicalTxt);
                $(redevelopment).shouldBe(visible).setValue(redevelopmentTxt);
                //Раздел Сделка
                //Вид процедуры
                $(procedure).shouldBe(visible).click();
                $(procedureDrop).shouldBe(visible);
//                List<WebElement> procedList = procedureList;
                procedureList.get(3).click(); //Без проведения конкурентных процедур...
                //Периодичность использование
                $(periodicity).shouldBe(visible).click();
                $(periodicityDrop).shouldBe(visible);
                sleep(1000);
                List<WebElement> periodicityDropList= periodicityList;
                i = periodicityList.size();
                logger.debug("periodicityList.size()     " + i);
                periodicityDropList.get(1).click(); //Круглосуточная

                //Целевое использование
                $(typeUse).shouldBe(visible).click();
                $(typeUseDrop).shouldBe(visible);
                sleep(600);
                List<WebElement> targetList = typeUseDropList;
                num = targetList.size();
                rnd = random.nextInt((num - 1));
                logger.debug("Случайное целевое использование  " + rnd);
                WebElement targetRnd = targetList.get(rnd);
                String targetTxt = targetRnd.getText();
                logger.debug("Целевое использование  " + targetTxt);
                targetRnd.click();
                //Вид документа
                $(typeDoc).shouldBe(visible).click();
                $(typeDocDrop).shouldBe(visible);
                sleep(600);
                List<WebElement> typeDocList = typeDocDropList;
                WebElement typeDocSelected = typeDocList.get(1);
                String typeDocTxt = typeDocSelected.getText();
                logger.debug("Вид договора   " + typeDocTxt);
                typeDocSelected.click();
                //Дата окончания действия - текущая + 1000 дней
                DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
                Date dateNow = new Date();
                Date endAction = DateUtils.addDays(dateNow, 1000);
                String endActionTxt = DateFormatUtils.format(endAction, "dd.MM.yyyy");
                logger.debug("endActionTxt  " + endActionTxt);
                $(dateEnd).shouldBe(visible).setValue(endActionTxt);
                //Начальная стоимость - случайное двоичное число от 1000 до 1000000
                double startSumRnd = 1000.00 + (1000000.00 - 1.00) * random.nextDouble();
                double startSumDbl = BigDecimal.valueOf(startSumRnd).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                String startSumTxt = Double.toString(startSumDbl).replace(".", ",");
                logger.debug("startSumTxt   " + startSumTxt);
                $(startCost).shouldBe(visible).setValue(startSumTxt);
                //Раздел "Контрагент" - выбрать ИП
                $(contractorInput).shouldBe(visible).click();
                sleep(600);
                $(contractorDrop).shouldBe(visible);
                List<WebElement> contractorsList = contractorDropList;
                WebElement individ = contractorsList.get(1);
                $(individ).shouldBe(visible).click();
                je.executeScript("window.scrollBy(0,400);");


/*
Случайные данные - заменила на данные из файла
                String innTxt = generateString(random, validNum, 12);
                  $(individInn).shouldBe(visible).setValue(innTxt);
                    String ogrnipTxt = generateString(random, validNum, 13);
                $(individOgrnip).shouldBe(visible).setValue(ogrnipTxt);
                //Дата регистрации - текущая дата минус 2000 дней
                Date reg = DateUtils.addDays(dateNow, -2000);
                String regTxt = DateFormatUtils.format(reg, "dd.MM.yyyy");
                logger.debug("Дата регистрации  " + regTxt);
                $(individDateReg).shouldBe(visible).setValue(regTxt);

                  //"Выдан" - текущая дата минус 1000 дней
                Date give = DateUtils.addDays(dateNow, -1000);
                String givenTxt = DateFormatUtils.format(give, "dd.MM.yyyy");
                logger.debug("Выдан  " + givenTxt);
                $(given).shouldBe(visible).setValue(givenTxt);
                //"Документ, удостоверяющий личность"
                $(individDocument).shouldBe(visible).click();
                sleep(600);
                $(individualDocumentDrop).shouldBe(visible);
                List<WebElement> documentsList = individualDocumentList;
                num = documentsList.size();
                rnd = random.nextInt((num - 1));
                WebElement doc = documentsList.get(rnd);
                doc.click();
                String seriesTxt = generateString(random, validNum, 2);
                $(individualSeries).shouldBe(visible).setValue(seriesTxt);
                 String docNumberTxt = generateString(random, validNum, 6);
                $(individualDocNumber).shouldBe(visible).setValue(docNumberTxt);
                String docGiven = lines.get(9);
                $(individualDocGiven).shouldBe(visible).setValue(docGiven);
                String docCodeTxt = generateString(random, validNum, 6);
                $(individualDocCode).shouldBe(visible).setValue(docCodeTxt);

                String indexTxt = generateString(random, validNum, 6);
                $(individualIndex).shouldBe(visible).setValue(indexTxt);
                $(indivCity).shouldBe(visible).setValue(city);
                $(indivAddress).shouldBe(visible).setValue(address);
                $(indivBank).shouldBe(visible).setValue(bank);
                String bikTxt = generateString(random, validNum, 9);
                $(indivBankBik).shouldBe(visible).setValue(bikTxt);
   String accountTxt = generateString(random, validNum, 20);
                $(indivAccount).shouldBe(visible).setValue(accountTxt);
String accountCorTxt = generateString(random, validNum, 20);
                $(indivCorAccount).shouldBe(visible).setValue(accountCorTxt);
 */

                //ФИО
                String surname = lines.get(3);
                String name = lines.get(4);
                String patronymic = lines.get(5);
                String surnameRod = lines.get(6);
                String nameRod = lines.get(7);
                String patronymicRod = lines.get(8);
                $(individSurname).shouldBe(visible).setValue(surname);
                $(individName).shouldBe(visible).setValue(name);
                $(individPatronymic).shouldBe(visible).setValue(patronymic);
                $(individSurnameRod).shouldBe(visible).setValue(surnameRod);
                $(individNameRod).shouldBe(visible).setValue(nameRod);
                $(individPatronymicRod).shouldBe(visible).setValue(patronymicRod);

              //ИНН
                String innTxt = lines.get(10);
                $(individInn).shouldBe(visible).setValue(innTxt);
                //ОГРНИП
                String ogrnipTxt = lines.get(11);
                $(individOgrnip).shouldBe(visible).setValue(ogrnipTxt);
                //Дата регистрации
                String regTxt = lines.get(12);
                logger.debug("Дата регистрации  " + regTxt);
                $(individDateReg).shouldBe(visible).setValue(regTxt);
                //№ свидетельства
                String certificate = lines.get(13);
                $(indivCertificate).shouldBe(visible).setValue(certificate);
                //Наименование органа выдачи
                String organ = lines.get(14);
                $(indivOrgan).shouldBe(visible).setValue(organ);
                //Место рождения
                String placeBorn = lines.get(15);
                $(indivPlaceBorn).shouldBe(visible).setValue(placeBorn);
                //Дата рождения
                String birthDay = lines.get(16);
                $(indivBirthDay).shouldBe(visible).setValue(birthDay);
                //"Документ, удостоверяющий личность"
                $(individDocument).shouldBe(visible).click();
                sleep(600);
                $(individualDocumentDrop).shouldBe(visible);
                List<WebElement> documentsList = individualDocumentList;
                num = documentsList.size();
                rnd = random.nextInt((num - 1));
                WebElement doc = documentsList.get(rnd);
                doc.click();
                //Серия
                String seriesTxt = generateString(random, validNum, 2);
                $(individualSeries).shouldBe(visible).setValue(seriesTxt);
                //Номер
                String docNumberTxt = generateString(random, validNum, 6);
                $(individualDocNumber).shouldBe(visible).setValue(docNumberTxt);
                //Кем выдан
                String docGiven = lines.get(9);
                $(individualDocGiven).shouldBe(visible).setValue(docGiven);
                //Код подразделения
                String docCodeTxt = generateString(random, validNum, 6);
                $(individualDocCode).shouldBe(visible).setValue(docCodeTxt);
                //"Выдан" - текущая дата минус 1000 дней
                Date give = DateUtils.addDays(dateNow, -1000);
                String givenTxt = DateFormatUtils.format(give, "dd.MM.yyyy");
                logger.debug("Выдан  " + givenTxt);
                $(given).shouldBe(visible).setValue(givenTxt);

                //Индекс
                String indexTxt = lines.get(17);
                $(individualIndex).shouldBe(visible).setValue(indexTxt);
                //Адрес
                $(indivCity).shouldBe(visible).setValue(city);
                $(indivAddress).shouldBe(visible).setValue(address);
                $(indivBank).shouldBe(visible).setValue(bank);
                //Телефон
                String phone = lines.get(18);
                $(indivPhone).shouldBe(visible).setValue(phone);
                //Fax
                String fax = lines.get(19);
                $(indivFax).shouldBe(visible).setValue(fax);
                //email
                String email = lines.get(20);
                $(indivEmail).shouldBe(visible).setValue(email);
                //Сайт
                String site = lines.get(21);
                $(indivSite).shouldBe(visible).setValue(site);
                //Инн банка
                String bankInn = lines.get(23);
                $(indidvBankInn).shouldBe(visible).setValue(bankInn);
                //КПП банка
                String bankKpp = lines.get(24);
                $(indivBankKpp).shouldBe(visible).setValue(bankKpp);


                String bikTxt = lines.get(22);
                $(indivBankBik).shouldBe(visible).setValue(bikTxt);




                String accountTxt = lines.get(25);
                $(indivAccount).shouldBe(visible).setValue(accountTxt);
                String accountCorTxt = lines.get(26);
                $(indivCorAccount).shouldBe(visible).setValue(accountCorTxt);



                          //Сохранить
                $(saveBtn).shouldBe(visible).click();
                List<WebElement> errorList = errorNoteList;
                num = errorList.size();
                if (num > 0) {
                 WebElement note = errorList.get(0);
                        String notetxt = note.getText();
                    logger.debug("Error  " + notetxt);
                }

                //Диалоговое окно, что контрагент уже есть
                List<WebElement> dialog = dialogBody;
                num = dialogBody.size();
                logger.debug("Диалоговое окно  " + num);
                if (num > 0) {
                    List<WebElement> btnsList = dialogBtnsList;
                    WebElement elem = btnsList.get(0);
                    elem.click();
                }

                List<WebElement> successList = notificationSuccess;
                num = successList.size();
                logger.debug("Успешно  " + num);
                for(i = 0; i < num; i++) {
                    String str = successList.get(i).getText();
                    logger.debug(i + "______" + str);
                }
                je.executeScript("window.scrollBy(0,-450);");
                List<WebElement> tableFilesList = docTableFilesImgList;
                num = tableFilesList.size() -1;
                logger.debug("Число строк  " + num);
                for (i = 0; i < num; i++) {
                    List<WebElement> tableFilesList1 = docTableFilesImgList;
                    WebElement img = tableFilesList1.get(i);
                    $(img).shouldBe(visible).click();
                    $(dialogUploadBtn).shouldBe(visible).click();
                    $("input#files").uploadFile(new File(docPath));
                    $(dialogLoadDataBtn).shouldBe(visible).click();
                }












/*
 //Вид документа
                $(typeDoc).shouldBe(visible).click();
                $(typeDocDrop).shouldBe(visible);
                sleep(600);
                List<WebElement> typeDocList = typeDocDropList;
                WebElement typeDocSelected = typeDocList.get(1);
                String typeDocTxt = typeDocSelected.getText();
                logger.debug("Вид договора   " + typeDocTxt);
                typeDocSelected.click();
 */

                /*
                          //Раздел "Документы" - Загрузить файлы
                List<WebElement> docTr = documentsTr;
                num = docTr.size();
                logger.debug("Число строк   " + num);
                WebElement tr = docTr.get(0);
                $(tr).shouldBe(visible).click();
                String str = tr.getText();
                logger.debug("str  " + str);
                je.executeScript("window.scrollBy(0,200);");
//                WebElement loadImg = tr.findElement(By.cssSelector(tdImg));
//                $(loadImg).shouldBe(visible).click();
                List<WebElement> docTdList = tr.findElements(By.cssSelector("td"));
//                WebElement td = tr.findElement(By.cssSelector("td.k-state-border-down"));
                WebElement td = docTdList.get(3);
                td.click();
                sleep(2000);
                $("input#files").uploadFile(new File(docPath));
                 */















            } catch (Exception e) {
                logger.debug(e.getMessage());
                screenCatch(screenshotPathFull, screen);
                screenshot(reportsPath + testNumber + screen);
                e.printStackTrace();
                fstream.write(beginLine + ";Тест завершен с ошибкой" + err);
            }
        } finally {
//        closeExtraPages();
            fstream.write(beginLine);
            //        driver.navigate().to(dashboardUrl);
            fstream.flush();
            fstream.close();

        }
    }



    //3 - Копия
    //Создать новую сделку - Проект -
    //Имущество - кад. номер - из файла. Вид процедуры - "Без проведения конкурентных процедур"
    //Периодичность - "Круглосуточная"
    public void dealCreateCadNumberCopy() throws IOException {
        Random random = new Random();
        Configuration.reportsFolder = reportsPathRentFull;
        Writer fstream = new OutputStreamWriter(new FileOutputStream(fileOutRentFull, true), StandardCharsets.UTF_8);
        String testNumber = "3_";
        logger.debug(testNumber);
        String beginLine = "\n" + timeStamp + ";3";
        fstream.write("\n" + beginLine + ";Управление имуществом организации - Создать сделку с заданным кад. номером "  + inf);
        String screenshotPathFull = reportsPathRentFull + testNumber;
        screen = 0;
        List<String> lines = readFileLines(filename3);

        try {
            try {
                driver.navigate().to(urlHome);
//                driver.switchTo().frame(0);
                wait1.until(ExpectedConditions.visibilityOf(buildingsLink));
//                String buildTxt = $(buildingsLink).shouldBe(visible).getText();
                //Страница "Имущество"
//                logger.debug("Имущество - здания - " + buildTxt);
                Set<String> winOpen = driver.getWindowHandles(); //Запомнить открытые страницы, для перехода на вновь открытую страницу
                $(buildingsLink).shouldBe(visible).click();
                String winNew = openPages(winOpen);
                //Перейти по ссылке
                driver.switchTo().window(winNew);
                sleep(2000);
                //Поиск
                $(searchWindowBtn).shouldBe(visible).click();
                $(clearBtn).shouldBe(visible).click();
                sleep(1000);
                String cadTxt = lines.get(2);
                logger.debug("cadTxt   " + cadTxt);
                $(poiskCadNumber).shouldBe(visible).setValue(cadTxt);
                sleep(600);
                $(searchButton).shouldBe(visible).click();
                sleep(1000);
//                List<WebElement> checkList1 = buildCheckBoxList;
//                num = checkList1.size();
//                logger.debug("checkList1   " + num);
                WebElement check1 = buildCheck1;
                String sdfd = check1.getAttribute("innerHTML");
                logger.debug("check1 Attr   " + sdfd);
                check1.click();
//                $(cadNumCheckBox1).click();
//                List<WebElement> arrowsList = poiskArrows;
//                WebElement arrow = arrowsList.get(0);
//                arrowDown(arrow);
                /*
                WebElement main = $(byText(mainDataTxt));
                String mainCollapseTxt = main.getAttribute("innerHTML");
                //Если свернуты "Основные данные" - развернуть
                if (mainCollapseTxt.contains("expand")) {
                    main.click();
                }
                */


//                int buildNum = Integer.valueOf(buildTxt);
//                if (buildNum > 30) {
//                    num = 30;
//                }
//                else num = buildNum;
//                Set<String> winOpen = driver.getWindowHandles(); //Запомнить открытые страницы, для перехода на вновь открытую страницу
//                $(buildingsLink).shouldBe(visible).click();
//
//                rnd = random.nextInt((num - 1));
//                logger.debug("Случайная строка " + rnd);



//                wait.until(ExpectedConditions.visibilityOf(buildHeader));
//                List<WebElement> checkList = buildCheckBoxList;
//                i = checkList.size();
//                logger.debug("checkList.size()" +  i);
//                wait.until(ExpectedConditions.visibilityOf(buildHeader));
//                List<WebElement> trList = buildTrList;
//                WebElement trRnd = trList.get(rnd);
//                trRnd.click();
//                List<WebElement> tdList = trRnd.findElements(By.cssSelector("td"));
//                WebElement tdDiv = tdList.get(0).findElement(By.cssSelector("div"));
//                tdDiv.click();
                $(rentBtn).shouldBe(visible).click();
                driver.switchTo().frame(0);
                JavascriptExecutor je = (JavascriptExecutor) driver;
                je.executeScript("window.scrollBy(0,200);");
                //выбор банка
                String arrowBank = $(bankArrow).shouldBe(visible).getAttribute("className");
                logger.debug("arrowBank     " + arrowBank);
                Actions builder = new Actions(driver);

                if (arrowBank.contains(expand)) {
//
                    sleep(1000);
                    String str = bankDetailsLink.getText();
                    logger.debug(str);
                    $(bankDetailsLink).shouldBe(visible).click();
                    sleep(1000);
                    String arrowBank1 = $(bankArrow).shouldBe(visible).getAttribute("className");//Второй раз - не всегда разворачивается раздел
                    if (arrowBank1.contains(expand)) {

                        String str1 = bankDetailsLink.getText();
                        logger.debug(str1);
                        $(bankDetailsLink).shouldBe(visible).click();
                    }
                }
                sleep(1000);

                //Выбрать банк
                $(selectBankDetailsBtn).shouldBe(visible).click();
                //Название банка

                String bankName = lines.get(1);
                driver.switchTo().frame(0);
                $(selectBankTable).shouldBe(visible).click();
//                $(selectBankTable).shouldBe(visible).find(By.linkText(bankName)).click();
                List<WebElement> trBankList = bankTrList;
                for (i = 1; i < trBankList.size(); i++) {
                    WebElement tr = trBankList.get(i);
                    String trTxt = tr.getText();
                    if (trTxt.contains(bankName)) {
                        tr.click();
                        break;
                    }
                }
                $(selectBankBtn).shouldBe(visible).click();
                sleep(2000);
                driver.switchTo().frame(0);
                //Раздел Объект
                arrowDown(objectArrow);
                $(technical).shouldBe(visible).setValue(technicalTxt);
                $(redevelopment).shouldBe(visible).setValue(redevelopmentTxt);
                //Раздел Сделка
                //Вид процедуры
                $(procedure).shouldBe(visible).click();
                $(procedureDrop).shouldBe(visible);
//                List<WebElement> procedList = procedureList;
                procedureList.get(3).click(); //Без проведения конкурентных процедур...
                //Периодичность использование
                $(periodicity).shouldBe(visible).click();
                $(periodicityDrop).shouldBe(visible);
                sleep(1000);
                List<WebElement> periodicityDropList= periodicityList;
                i = periodicityList.size();
                logger.debug("periodicityList.size()     " + i);
                periodicityDropList.get(1).click(); //Круглосуточная

                //Целевое использование
                $(typeUse).shouldBe(visible).click();
                $(typeUseDrop).shouldBe(visible);
                sleep(600);
                List<WebElement> targetList = typeUseDropList;
                num = targetList.size();
                rnd = random.nextInt((num - 1));
                logger.debug("Случайное целевое использование  " + rnd);
                WebElement targetRnd = targetList.get(rnd);
                String targetTxt = targetRnd.getText();
                logger.debug("Целевое использование  " + targetTxt);
                targetRnd.click();
                //Вид документа
                $(typeDoc).shouldBe(visible).click();
                $(typeDocDrop).shouldBe(visible);
                sleep(600);
                List<WebElement> typeDocList = typeDocDropList;
                WebElement typeDocSelected = typeDocList.get(1);
                String typeDocTxt = typeDocSelected.getText();
                logger.debug("Вид договора   " + typeDocTxt);
                typeDocSelected.click();
                //Дата окончания действия - текущая + 1000 дней
                DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
                Date dateNow = new Date();
                Date endAction = DateUtils.addDays(dateNow, 1000);
                String endActionTxt = DateFormatUtils.format(endAction, "dd.MM.yyyy");
                logger.debug("endActionTxt  " + endActionTxt);
                $(dateEnd).shouldBe(visible).setValue(endActionTxt);
                //Начальная стоимость - случайное двоичное число от 1000 до 1000000
                //Площадь по договору
                double startSumRnd = 1000.00 + (1000000.00 - 1.00) * random.nextDouble();
                double startSumDbl = BigDecimal.valueOf(startSumRnd).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                String startSumTxt = Double.toString(startSumDbl).replace(".", ",");
                logger.debug("startSumTxt   " + startSumTxt);
                $(startCost).shouldBe(visible).setValue(startSumTxt);
                //Раздел "Контрагент"
                $(contractorInput).shouldBe(visible).click();
                sleep(600);
                $(contractorDrop).shouldBe(visible);
                List<WebElement> contractorsList = contractorDropList;
                WebElement individ = contractorsList.get(1);
                $(individ).shouldBe(visible).click();
                je.executeScript("window.scrollBy(0,400);");
//                $(individInn).shouldBe(visible).click();
                String innTxt = generateString(random, validNum, 12);
                $(individInn).shouldBe(visible).setValue(innTxt);
                //ФИО
                String surname = lines.get(3);
                String name = lines.get(4);
                String patronymic = lines.get(5);
                String surnameRod = lines.get(6);
                String nameRod = lines.get(7);
                String patronymicRod = lines.get(8);
                $(individSurname).shouldBe(visible).setValue(surname);
                $(individName).shouldBe(visible).setValue(name);
                $(individPatronymic).shouldBe(visible).setValue(patronymic);

                $(individSurnameRod).shouldBe(visible).setValue(surnameRod);
                $(individNameRod).shouldBe(visible).setValue(nameRod);
                $(individPatronymicRod).shouldBe(visible).setValue(patronymicRod);

                String ogrnipTxt = generateString(random, validNum, 13);
                $(individOgrnip).shouldBe(visible).setValue(ogrnipTxt);
                //Дата регистрации - текущая дата минус 2000 дней
                Date reg = DateUtils.addDays(dateNow, -2000);
                String regTxt = DateFormatUtils.format(reg, "dd.MM.yyyy");
                logger.debug("Дата регистрации  " + regTxt);
                $(individDateReg).shouldBe(visible).setValue(regTxt);

                //"Выдан" - текущая дата минус 1000 дней
                Date give = DateUtils.addDays(dateNow, -1000);
                String givenTxt = DateFormatUtils.format(give, "dd.MM.yyyy");
                logger.debug("Выдан  " + givenTxt);
                $(given).shouldBe(visible).setValue(givenTxt);
                //"Документ, удостоверяющий личность"
                $(individDocument).shouldBe(visible).click();
                sleep(600);
                $(individualDocumentDrop).shouldBe(visible);
                List<WebElement> documentsList = individualDocumentList;
                num = documentsList.size();
                rnd = random.nextInt((num - 1));
                WebElement doc = documentsList.get(rnd);
                doc.click();
                String seriesTxt = generateString(random, validNum, 2);
                $(individualSeries).shouldBe(visible).setValue(seriesTxt);
                String docNumberTxt = generateString(random, validNum, 6);
                $(individualDocNumber).shouldBe(visible).setValue(docNumberTxt);
                String docGiven = lines.get(9);
                $(individualDocGiven).shouldBe(visible).setValue(docGiven);
                String docCodeTxt = generateString(random, validNum, 6);
                $(individualDocCode).shouldBe(visible).setValue(docCodeTxt);
                String indexTxt = generateString(random, validNum, 6);
                $(individualIndex).shouldBe(visible).setValue(indexTxt);
                $(indivCity).shouldBe(visible).setValue(city);
                $(indivAddress).shouldBe(visible).setValue(address);
                $(indivBank).shouldBe(visible).setValue(bank);
                String bikTxt = generateString(random, validNum, 9);
                $(indivBankBik).shouldBe(visible).setValue(bikTxt);
                String accountTxt = generateString(random, validNum, 20);
                $(indivAccount).shouldBe(visible).setValue(accountTxt);
                String accountCorTxt = generateString(random, validNum, 20);
                $(indivCorAccount).shouldBe(visible).setValue(accountCorTxt);

                //Сохранить
                $(saveBtn).shouldBe(visible).click();
                List<WebElement> errorList = errorNoteList;
                num = errorList.size();
                if (num > 0) {
                    WebElement note = errorList.get(0);
                    String notetxt = note.getText();
                    logger.debug("Error  " + notetxt);
                }














/*
 //Вид документа
                $(typeDoc).shouldBe(visible).click();
                $(typeDocDrop).shouldBe(visible);
                sleep(600);
                List<WebElement> typeDocList = typeDocDropList;
                WebElement typeDocSelected = typeDocList.get(1);
                String typeDocTxt = typeDocSelected.getText();
                logger.debug("Вид договора   " + typeDocTxt);
                typeDocSelected.click();
 */

                /*
                          //Раздел "Документы" - Загрузить файлы
                List<WebElement> docTr = documentsTr;
                num = docTr.size();
                logger.debug("Число строк   " + num);
                WebElement tr = docTr.get(0);
                $(tr).shouldBe(visible).click();
                String str = tr.getText();
                logger.debug("str  " + str);
                je.executeScript("window.scrollBy(0,200);");
//                WebElement loadImg = tr.findElement(By.cssSelector(tdImg));
//                $(loadImg).shouldBe(visible).click();
                List<WebElement> docTdList = tr.findElements(By.cssSelector("td"));
//                WebElement td = tr.findElement(By.cssSelector("td.k-state-border-down"));
                WebElement td = docTdList.get(3);
                td.click();
                sleep(2000);
                $("input#files").uploadFile(new File(docPath));
                 */















            } catch (Exception e) {
                logger.debug(e.getMessage());
                screenCatch(screenshotPathFull, screen);
                screenshot(reportsPath + testNumber + screen);
                e.printStackTrace();
                fstream.write(beginLine + ";Тест завершен с ошибкой" + err);
            }
        } finally {
//        closeExtraPages();
            fstream.write(beginLine);
            //        driver.navigate().to(dashboardUrl);
            fstream.flush();
            fstream.close();

        }
    }



    //4
    //Удалить сделку
    public void dealDelete() throws IOException {
        Random random = new Random();
        Configuration.reportsFolder = reportsPathRentFull;
        Writer fstream = new OutputStreamWriter(new FileOutputStream(fileOutRentFull, true), StandardCharsets.UTF_8);
        String testNumber = "4_";
        logger.debug(testNumber);
        String beginLine = "\n" + timeStamp + ";4";
        fstream.write("\n" + beginLine + ";Управление имуществом организации - Удалить сделку "  + inf);
        String screenshotPathFull = reportsPathRentFull + testNumber;
        screen = 0;

        try {
            try {
                driver.navigate().to(urlHome);
                driver.switchTo().frame(0);
                wait1.until(ExpectedConditions.visibilityOf(buildingsLink));
                String buildTxt = $(buildingsLink).shouldBe(visible).getText();
                //Число зданий
                logger.debug("Имущество - здания - " + buildTxt);
                int buildNum = Integer.valueOf(buildTxt);
                if (buildNum > 30) {
                    num = 30;
                }
                else num = buildNum;
                Set<String> winOpen = driver.getWindowHandles(); //Запомнить открытые страницы, для перехода на вновь открытую страницу
                $(buildingsLink).shouldBe(visible).click();

                rnd = random.nextInt((num - 1));
                logger.debug("Случайная строка " + rnd);
                String winNew = openPages(winOpen);
                //Перейти по ссылке
                driver.switchTo().window(winNew);
//                wait.until(ExpectedConditions.visibilityOf(buildHeader));
                List<WebElement> checkList = buildCheckBoxList;
                i = checkList.size();
                logger.debug("checkList.size()" +  i);
                wait.until(ExpectedConditions.visibilityOf(buildHeader));
                List<WebElement> trList = buildTrList;
                WebElement trRnd = trList.get(rnd);
                trRnd.click();
                List<WebElement> tdList = trRnd.findElements(By.cssSelector("td"));
                WebElement tdDiv = tdList.get(0).findElement(By.cssSelector("div"));
                tdDiv.click();
                $(rentBtn).shouldBe(visible).click();
                driver.switchTo().frame(0);
                String arrowBank = $(bankArrow).shouldBe(visible).getAttribute("className");
                if (arrowBank.contains(expand)) {
                    bankArrow.click();
                }

                //Выбрать банк
                $(selectBankDetailsBtn).shouldBe(visible).click();
                //Название банка
                List<String> lines2 = readFileLines(filename2);
                String bankName = lines2.get(1);
                driver.switchTo().frame(0);
                $(selectBankTable).shouldBe(visible).click();
//                $(selectBankTable).shouldBe(visible).find(By.linkText(bankName)).click();
                List<WebElement> trBankList = bankTrList;
                for (i = 1; i < trBankList.size(); i++) {
                    WebElement tr = trBankList.get(i);
                    String trTxt = tr.getText();
                    if (trTxt.contains(bankName)) {
                        tr.click();
                        break;
                    }
                }
                $(selectBankBtn).shouldBe(visible).click();
                sleep(2000);
                driver.switchTo().frame(0);
                //Раздел Объект
                arrowDown(objectArrow);
                $(technical).shouldBe(visible).setValue(technicalTxt);
                $(redevelopment).shouldBe(visible).setValue(redevelopmentTxt);










            } catch (Exception e) {
                logger.debug(e.getMessage());
                screenCatch(screenshotPathFull, screen);
                screenshot(reportsPath + testNumber + screen);
                e.printStackTrace();
                fstream.write(beginLine + ";Тест завершен с ошибкой" + err);
            }
        } finally {
//        closeExtraPages();
            fstream.write(beginLine);
//        driver.navigate().to(dashboardUrl);
            fstream.flush();
            fstream.close();

        }
    }



    //Загрузить документ
    public void loadDoc(WebElement tr, String path) {
        WebElement img2 = tr.findElement(By.cssSelector(newFileLocator));
        $(img2).shouldBe(visible).click();
        sleep(2000);
//        driver.switchTo().frame(0);
//                            List<WebElement> dialogList = dialogBody;
        j = dialogBody.size();
        logger.debug("Список dialogBody   " + j);
        driver.switchTo().frame(0);
        sleep(1000);
        $("input#files").uploadFile(new File(path));
        $(dialogLoadDataBtn).shouldBe(visible).click();
        sleep(1000);
        driver.switchTo().frame(0);
    }


}