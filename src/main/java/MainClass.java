import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;


public class MainClass {


    public static void main( String[] args ) throws Exception {
        ApiContextInitializer.init();

        TelegramBotsApi telegram = new TelegramBotsApi();


        try {
            telegram.registerBot(new Bot());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
