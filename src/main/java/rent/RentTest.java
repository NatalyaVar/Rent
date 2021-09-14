package rent;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.support.PageFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class RentTest extends RentPage {
    public RentTest() throws IOException { }

    RentPage rentPage = new RentPage();
    static long mAll = System.currentTimeMillis();

    @Before
    public void pFactory() {
        PageFactory.initElements(driver, rentPage);
    }

    //Открыть существующую карточку и проверить, что данные со страницы 'Анализ ущерба' - 'Дела по ЖП' правильно отображаются в карточке
    //проверка, что текст строки совпадает с тестовыми данными, потом переход по № договора и проверка данных на странице договора
    @Test
    public void n1_dealsPoiskTest() throws IOException, ParseException {
        long m = System.currentTimeMillis();
        rentPage.dealsPoisk();
        reportTime(m);
    }

    //Создать сделку - случайный кад. номер
  @Test
    public void n2_dealCreateTest() throws IOException, ParseException {
        long m = System.currentTimeMillis();
        rentPage.dealCreate();
        reportTime(m);
    }
   //Создать сделку - кад. номер из файла
  @Test
    public void n3_dealCreateCadNumberTest() throws IOException, ParseException {
        long m = System.currentTimeMillis();
        rentPage.dealCreateCadNumber();
        reportTime(m);
    }

  //Создать сделку - кад. номер random, аукцион
  @Test
    public void n4_dealCreateAuctionTest() throws IOException, ParseException {
        long m = System.currentTimeMillis();
        rentPage.dealCreateAuction();
        reportTime(m);
    }

    public static void reportTime (Long m) throws IOException {
        Writer fstream = new OutputStreamWriter(new FileOutputStream(fileOutRentFull, true), StandardCharsets.UTF_8);
        try {
            fstream.write(";Время выполнения  " + (System.currentTimeMillis() - m) / 1000 + "  секунд;инфо");
        }
        finally {fstream.flush();
            fstream.close();
        }
    }
}
