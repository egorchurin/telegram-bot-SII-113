import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.net.URL;

public class SDBC extends TelegramLongPollingBot {

    private  Document getPage() throws IOException {
        String url = "https://www.gismeteo.ru/weather-samara-4618/";
        Document page = Jsoup.parse(new URL(url),3000);
        return page;
    }
    public  String ain() throws IOException {
        Document page = getPage();
        Element tablewth = page.select("a[class=weathertab weathertab-block tooltip]").first();
        //System.out.println(tablewth);
        Elements tem = tablewth.select("span[class=unit unit_temperature_c]");

        String t= tablewth.select("span[class=unit unit_temperature_c]").text();

        String date = tablewth.select("div[class=date date-1]").text();

        String jvkline = page.select("a[class=weathertab weathertab-block tooltip]").text();
        //System.out.println(jvkline);

        String ror = (date+ " Температура: "+ t);
        return ror;
    }

    @Override
    public void onUpdateReceived(Update update) {
        //     System.out.println("Massage: ");
        //   System.out.println(update.getMessage().getText());
        // System.out.println("Username: ");
        //System.out.println(update.getMessage().getFrom().getFirstName());

        String command = update.getMessage().getText();
        String message = command;
        SendMessage responce = new SendMessage();
        responce.setChatId(update.getMessage().getChatId().toString());

        SendMessage res = new SendMessage();
        res.setChatId(update.getMessage().getChatId().toString());
        String rtp = null;
        try {
            rtp = ain();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        res.setText(rtp);
        try{
            execute(res);
        } catch (TelegramApiException E){
            E.printStackTrace();
        }

    }

    @Override
    public String getBotUsername() {
        // TODO
        return "IMadeThisBot";
    }

    @Override
    public String getBotToken() {
        // TODO
        return "5428419276:AAG2dUsiM1HW-3VtHJP0i0gW6ULIguBOu14";
    }

}
