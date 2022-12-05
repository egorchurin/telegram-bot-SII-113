import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLOutput;

public class Parser {

    private static Document getPage() throws IOException {
        String url = "https://www.gismeteo.ru/weather-samara-4618/";
        Document page = Jsoup.parse(new URL(url),3000);
        return page;
    }
    public static String ain() throws IOException {
        Document page = getPage();
        Element tablewth = page.select("a[class=weathertab weathertab-block tooltip]").first();
        //System.out.println(tablewth);
        Elements tem = tablewth.select("span[class=unit unit_temperature_c]");

        String t= tablewth.select("span[class=unit unit_temperature_c]").text();

        String date = tablewth.select("div[class=date date-6]").text();

        String jvkline = page.select("a[class=weathertab weathertab-block tooltip]").text();
        //System.out.println(jvkline);

        String ror = (date+ " Температура: "+ t);
        //System.out.println(ror);
        //System.out.println("Дата: "+ date );
        //System.out.println("Температура: "+ t);
        return ror;
    }
}
