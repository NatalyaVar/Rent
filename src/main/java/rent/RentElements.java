package rent;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public abstract class RentElements {
    Actions actionList = new Actions(driver);

    public static WebDriver driver = new ChromeDriver();
    int n = 3;
    int n1 = 5;
    int i = 0;
    public WebDriverWait wait = new WebDriverWait(driver, n);
    public WebDriverWait wait1 = new WebDriverWait(driver, n1);
    int k = 0;
    int j = 0;
    int screen = 0;

    public static String fileOutRent = "outputRent.txt";
    public static String reportsPathRent = "Reports\\";

    public static String inf = ";инфо";
    public static String ok = ";OK";
    public static String err = ";ОШИБКА";
    public static String errTestData = ";ОШИБКА тестовых данных";
    public static String reportsPath = "Reports";
    public static String png = ".png";
    String expand = "expand";
    String technicalTxt = "Техническое состояние - проверка";
    String redevelopmentTxt = "Наличие перепланировки - проверка";
    String validNum = "1234567890";
    String city = "г. Москва";
    String address = "улица Новая";
    String bank = "ТЕСТОВЫЙ БАНК";

    public static String dealsTxt;

    public static String projectTxt = "Проект";
    public static String statusTxt = "Статус";
    public static String fileFormatTxt = " формат файла должен быть картинкой";

    static String fileURL = "testData/URLRent.txt";
    public static String filename2 = "testData/2testCreateDeal.txt";
    public static String filename3 = "testData/3testCreateDealСad.txt";
    public static String docPath = "testData/Document.pdf";
    public static String planPath = "testData/Plan.jpeg";

@FindBy(how = How.CSS, css = "#MainMenu > li.k-item")
List<WebElement> mainMenuList;

    @FindBy(how = How.CSS, css = "#contextMenuButton")
    WebElement contextMenuBtn;
    @FindBy(how = How.CSS, css = "#contextMenu")
    WebElement contextMenu;
    @FindBy(how = How.CSS, css = "#Grid-300 > div.k-grid-content.k-auto-scrollable > table")
    WebElement dealsTable;
    @FindBy(how = How.CSS, css = "#dashPanel_9354  div.panel-body  tr:nth-child(1) a")
    WebElement buildingsLink;

//Имущество
    @FindBy(how = How.CSS, css = "#Grid-200 > div.k-grid-content.k-auto-scrollable > table > tbody > tr.k-state-selected > td:nth-child(2) > div")
    WebElement buildHeader;
    @FindBy(how = How.CSS, css = "#Grid-200  td:nth-child(1)   div .k-checkbox")
    List<WebElement> buildCheckBoxList;
    @FindBy(how = How.CSS, css = "#Grid-200  td:nth-child(1)   div")
    WebElement buildCheck1;
 @FindBy(how = How.CSS, css = "#Grid-200 > div.k-grid-content.k-auto-scrollable > table > tbody > tr")
    List<WebElement> buildTrList;
 @FindBy(how = How.CSS, css = "#GridToolBar-200 > a:nth-child(3)")
 WebElement rentBtn;

//Создать сделку
    @FindBy(how = How.CSS, css = "#DA_BankDetailsData > span > span.k-icon")
    WebElement bankArrow;
//    @FindBy(how = How.CSS, css = "#DA_BankDetailsData_label")
//    WebElement bankDetailsLink;
    @FindBy(how = How.CSS, css = "#DA_BankDetailsData > span")
    WebElement bankDetailsLink;
    @FindBy(how = How.CSS, css = "#selectBankDetails")
    WebElement selectBankDetailsBtn;
    @FindBy(how = How.CSS, css = "#Grid-306 > div.k-grid-content.k-auto-scrollable > table > tbody")
    WebElement selectBankTable;
    @FindBy(how = How.CSS, css = "#Grid-306 tr")
    List<WebElement> bankTrList;
    @FindBy(how = How.CSS, css = "#GridToolBar-306 > a:nth-child(3)")
    WebElement selectBankBtn;
//"#objPanelbar > li > span > span.k-icon"
    @FindBy(how = How.CSS, css = "#objPanelbar > li > span > span.k-icon")
    WebElement objectArrow;
    @FindBy(how = How.CSS, css = "#objPanelbar > li > span > span.k-icon")
    List<WebElement> objectArrowList;


    @FindBy(how = How.CSS, css = "[id^=TechnicalCondition]")
    WebElement technical;
  @FindBy(how = How.CSS, css = "[id^=Redevelopment]")
    WebElement redevelopment;
    @FindBy(how = How.CSS, css = "#DA_Deal div:nth-child(3) > div:nth-child(2) > span > span")
    WebElement procedure;
    @FindBy(how = How.CSS, css = "body > div.k-animation-container")
    WebElement procedureDrop;
 @FindBy(how = How.CSS, css = "body > div.k-animation-container li")
    List<WebElement> procedureList;

// @FindBy(how = How.CSS, css = "#DA_Deal div:nth-child(5) > div:nth-child(2) span.k-input")
// WebElement periodicity;
 @FindBy(how = How.CSS, css = "#DA_Deal div:nth-child(2) > div:nth-child(4) span.k-input")
 WebElement periodicity;
 @FindBy(how = How.CSS, css = "#Periodicity-list > div.k-list-scroller")
 WebElement periodicityDrop;
  @FindBy(how = How.CSS, css = "#Periodicity-list > div.k-list-scroller li")
 List<WebElement> periodicityList;
//  @FindBy(how = How.CSS, css = "#DA_Deal div:nth-child(3) > div:nth-child(4) > span > span")
//  WebElement typeUse;
  @FindBy(how = How.CSS, css = "#DA_Deal div:nth-child(5) > div:nth-child(4)")
  WebElement typeUse;
  @FindBy(how = How.CSS, css = "#TypeUse_listbox")
  WebElement typeUseDrop;
 @FindBy(how = How.CSS, css = "#TypeUse_listbox li")
  List<WebElement> typeUseDropList;
// @FindBy(how = How.CSS, css = "#DA_Deal div:nth-child(5) > div:nth-child(2) span.k-input")
// WebElement typeDoc;
 @FindBy(how = How.CSS, css = "#DA_Deal div:nth-child(3) > div:nth-child(4) span.k-input")
 WebElement typeDoc;
 //"#DA_Deal  div:nth-child(2) > div:nth-child(4) span.k-input"
    //"#Periodicity-list > div.k-list-scroller"
 @FindBy(how = How.CSS, css = "#TemplateType_listbox")
 WebElement typeDocDrop;
 @FindBy(how = How.CSS, css = "#TemplateType_listbox li")
 List<WebElement> typeDocDropList;
 @FindBy(how = How.CSS, css = "#DateEnd")
 WebElement dateEnd;
// @FindBy(how = How.CSS, css = "#StartCost")
// WebElement startCost;
 @FindBy(how = How.CSS, css = "#DA_Deal div:nth-child(8) > div:nth-child(4) input.k-formatted-value.k-input")
 WebElement startCost;
 @FindBy(how = How.CSS, css = "#DocumentsGrid > table > tbody > tr")
 List<WebElement> documentsTr;
String tdImg = "td:nth-child(4) > div > img";
//@FindBy(how = How.CSS, css = "#Grid-200 > div.k-grid-content.k-auto-scrollable tr > td:nth-child(1) label")
//WebElement cadNumCheckBox1;
@FindBy(how = How.CSS, css = "#Grid-200 > div.k-grid-content.k-auto-scrollable > table > tbody > tr > td:nth-child(1) .k-checkbox")
WebElement cadNumCheckBox1;

//Контрагент
    @FindBy(how = How.CSS, css = "#contractor--1 > div:nth-child(5) > div.col-sm-4 span.k-input")
    WebElement contractorInput;
    @FindBy(how = How.CSS, css = "body > div:nth-child(13)")
    WebElement contractorDrop;
    @FindBy(how = How.CSS, css = "body > div:nth-child(13) li")
    List<WebElement> contractorDropList;
    @FindBy(how = How.CSS, css = "#contractor--1 > div:nth-child(5) > div.privateIndividualContractor input")
    WebElement individInn;
    @FindBy(how = How.CSS, css = "#contractor--1 > div.privateIndividualContractor > div:nth-child(1) > div:nth-child(2) input")
    WebElement individSurname;
     @FindBy(how = How.CSS, css = "#contractor--1 > div.privateIndividualContractor > div:nth-child(1) > div:nth-child(3) input")
    WebElement individName;
     @FindBy(how = How.CSS, css = "#contractor--1 > div.privateIndividualContractor > div:nth-child(1) > div:nth-child(4) input")
    WebElement individPatronymic;
 @FindBy(how = How.CSS, css = "#contractor--1 > div.privateIndividualContractor > div:nth-child(2) > div:nth-child(2) input")
    WebElement individSurnameRod;
     @FindBy(how = How.CSS, css = "#contractor--1 > div.privateIndividualContractor > div:nth-child(2) > div:nth-child(3) input")
    WebElement individNameRod;
     @FindBy(how = How.CSS, css = "#contractor--1 > div.privateIndividualContractor > div:nth-child(2) > div:nth-child(4) input")
    WebElement individPatronymicRod;
     @FindBy(how = How.CSS, css = "#contractor--1 > div.privateIndividualContractor > div.ipContractor > div:nth-child(1) > div:nth-child(2) > input")
     WebElement individOgrnip;
     @FindBy(how = How.CSS, css = "#contractor--1 > div.privateIndividualContractor > div.ipContractor > div:nth-child(1) > div:nth-child(4) input")
     WebElement individDateReg;
     @FindBy(how = How.CSS, css = "#contractor--1 > div.privateIndividualContractor > div:nth-child(6) > div:nth-child(4) input")
     WebElement given;
     @FindBy(how = How.CSS, css = "#contractor--1 > div.privateIndividualContractor > div:nth-child(6) > div:nth-child(2) span.k-input")
     WebElement individDocument;
     @FindBy(how = How.CSS, css = "body > div:nth-child(16)")
     WebElement individualDocumentDrop;
     @FindBy(how = How.CSS, css = "body > div:nth-child(16) li")
    List<WebElement> individualDocumentList;
     @FindBy(how = How.CSS, css = "#contractor--1 > div.privateIndividualContractor > div:nth-child(7) > div:nth-child(2) > input")
     WebElement individualSeries;
     @FindBy(how = How.CSS, css = "#contractor--1 > div.privateIndividualContractor > div:nth-child(7) > div:nth-child(4) > input")
     WebElement individualDocNumber;
     @FindBy(how = How.CSS, css = "#contractor--1 > div.privateIndividualContractor > div:nth-child(8) input")
     WebElement individualDocGiven;
     @FindBy(how = How.CSS, css = "[name*=DocCode]")
     WebElement individualDocCode;
     @FindBy(how = How.CSS, css = "#contractor--1 > div:nth-child(10) > div:nth-child(2) input")
     WebElement individualIndex;
    @FindBy(how = How.CSS, css = "#contractor--1 > div:nth-child(10) > div.col-sm-3 input")
    WebElement indivCity;
 @FindBy(how = How.CSS, css = "#contractor--1 > div:nth-child(10) > div.col-sm-5 input")
    WebElement indivAddress;
 @FindBy(how = How.CSS, css = "#contractor--1 > div:nth-child(15) > div:nth-child(2) > input")
    WebElement indivBank;
@FindBy(how = How.CSS, css = "#contractor--1 > div:nth-child(15) > div:nth-child(4) > input")
    WebElement indivBankBik;

@FindBy(how = How.CSS, css = "[name*=BankAccCur]")
WebElement indivAccount;
@FindBy(how = How.CSS, css = "[name*=BankCorCur]")
    WebElement indivCorAccount;
@FindBy(how = How.CSS, css = "div.ipContractor > div:nth-child(2) > div:nth-child(2) > input")
WebElement indivCertificate;
@FindBy(how = How.CSS, css = "div.ipContractor > div:nth-child(2) > div:nth-child(4) > input")
WebElement indivOrgan;
@FindBy(how = How.CSS, css = "div.privateIndividualContractor > div:nth-child(4) > div:nth-child(2) > input")
WebElement indivPlaceBorn;
@FindBy(how = How.CSS, css = "div.privateIndividualContractor > div:nth-child(4) > div:nth-child(4) input")
WebElement indivBirthDay;
@FindBy(how = How.CSS, css = "div:nth-child(11) > div:nth-child(2) > input")
WebElement indivPhone;
@FindBy(how = How.CSS, css = "[name*=Fax]")
WebElement indivFax;
@FindBy(how = How.CSS, css = "#contractor--1 > div:nth-child(11) > div:nth-child(4) > input")
WebElement indivEmail;
@FindBy(how = How.CSS, css = "[name*=Www]")
WebElement indivSite;
@FindBy(how = How.CSS, css = "#contractor--1 > div:nth-child(16) > div:nth-child(2) > input")
WebElement indidvBankInn;
@FindBy(how = How.CSS, css = "#contractor--1 > div:nth-child(16) > div:nth-child(4) > input")
WebElement indivBankKpp;

//диалоговое окно
    @FindBy(how = How.CSS, css = "body > div.k-widget.k-window")
    List<WebElement> dialogBody;

    @FindBy(how = How.CSS, css = "body > div.k-widget.k-window > div.k-window-content.k-content > div.dialog-footer")
    WebElement dialogFooter;
    @FindBy(how = How.CSS, css = "body > div.k-widget.k-window > div.k-window-content.k-content > div.dialog-footer .k-button")
    List<WebElement> dialogBtnsList;


    @FindBy(how = How.CSS, css = "[class*= success]")
    List<WebElement> notificationSuccess;

    //Документы
    @FindBy(how = How.CSS, css = "[class*=new_file]")
    List<WebElement> docTableFilesImgList;
    @FindBy(how = How.CSS, css = "[class*=new_file]")
    WebElement newFileImg;
    String newFileLocator = "[class*=new_file]";
    @FindBy(how = How.CSS, css = "div.k-button.k-upload-button")
    WebElement dialogUploadBtn;
    @FindBy(how = How.CSS, css = "#toolbar > a")
    WebElement dialogLoadDataBtn;
    @FindBy(how = How.CSS, css = "#DocumentsGrid > table > tbody > tr")
    List<WebElement> documentsTrList;
    String docTypeLocator = "td:nth-child(5)";
    String docNumberLocator = "td:nth-child(8)";
    String dateLocator = "td:nth-child(9)";
    String techPassport = "Технический паспорт БТИ";
    String estimation = "Отчет";
    @FindBy(how = How.CSS, css = "div.dialog-footer .k-button")
    WebElement dialogBtnOk;






    //Меню справа
    @FindBy(how = How.CSS, css = "#navigation-menu > a:nth-child(8)")
    WebElement menuContractor;


@FindBy(how = How.CSS, css = "#buttonSave")
WebElement saveBtn;
@FindBy(how = How.CSS, css = "#contractProject")
WebElement contractProjectBtn;
@FindBy(how = How.CSS, css = "div.notification.error")
List<WebElement> errorNoteList;

    //Поиск
    @FindBy(how = How.CSS, css = "#SearchWindowButton")
    WebElement searchWindowBtn;
    @FindBy(how = How.CSS, css = "#ClearSearchButton")
    WebElement clearBtn;
    @FindBy(how = How.CSS, css = "#SearchButton")
    WebElement searchButton;
    static String mainDataTxt = "Основные данные";
    @FindBy(how = How.CSS, css = "[id^= PanelBar] div:nth-child(4) > div.col-sm-8 > span")
    WebElement statusDropDown;
    @FindBy(how = How.CSS, css = "#RequestStatus_listbox")
    WebElement statusListBox;
    String statusProjectListBox = "li:nth-child(19)";
    @FindBy(how = How.CSS, css = "[id^=PanelBar] li > a > span")
    List<WebElement> poiskArrows;
    @FindBy(how = How.CSS, css = "#ObjectCadastralNumber")
    WebElement poiskCadNumber;


    //Страница "Сделки"
    @FindBy(how = How.CSS, css = "a.k-button.k-split-button-arrow")
    WebElement splitBtn;
    @FindBy(how = How.CSS, css = "#resetSortButton-300")
    WebElement resetSortBtn;
    @FindBy(how = How.CSS, css = "#Grid-300 > div.k-grid-header thead")
    WebElement tableHead;
    @FindBy(how = How.CSS, css = "#Grid-300 tr:nth-child(1) > td:nth-child(5)")
    WebElement firstRowStatus;









    //Снятие скриншота
    public void screenCatch (String screenshotPathFull, int screen) throws IOException {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File(screenshotPathFull + screen + png));
    }

    //Метод для перехода на вновь открытую страницу
    public String openPages(Set<String> winOpen) throws IOException {
        String winNew = null;
        Set<String> winOpenNew = driver.getWindowHandles();
        //Выбрать из winOpenNew ту страницу, которая не была открыта раньше
        //Из коллекции Set перенести идентификаторы страниц в List
        ArrayList<String> list = new ArrayList<>();
        list.addAll(winOpen);
        ArrayList<String> listNew = new ArrayList<>();
        listNew.addAll(winOpenNew);
        //Ссылка на новую страницу
        listNew.removeAll(list);
        winNew = listNew.get(0);
        return winNew;
    }

//Развернуть раздел = нажать стрелку
    public void arrowDown(WebElement arrow) {
        String arrowTxt = $(arrow).shouldBe(visible).getAttribute("className");
        if (arrowTxt.contains(expand)) {
            arrow.click();
        }
    }


}
