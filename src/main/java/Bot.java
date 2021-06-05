import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.util.ArrayList;
import java.util.List;


public class Bot extends TelegramLongPollingBot {

    private static final String TOKEN = "1708607859:AAH6OGOyCHWXu7Is5OPCJWkCVTfud6h3hAg";
    private static final String USERNAME = "Belakryly_bot";

    public void onUpdateReceived(Update update) {
        if(update.hasMessage()){
            if(update.getMessage().hasText()){
                if(update.getMessage().getText().equals("/start")) {
                    try {
                        execute(sendInlineKeyBoardMessage(update.getMessage().getChatId()));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else
        if(update.getCallbackQuery().getData().equals("Приду")){
            try{
                execute(new SendMessage().setChatId("Нажато --приду-- "));
            } catch (TelegramApiException e) {
                e.printStackTrace();         }

        }
        try {
            execute(new SendMessage().setText(update.getCallbackQuery().
                    getData()).setChatId(update.getCallbackQuery().getMessage().getChatId()));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        String text = update.getMessage().getText();
        if (text != null) {
            try {
                execute(new SendMessage().setText("Voy"));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        }

    }


    public String getBotToken () {
        return TOKEN;
    }
    public String getBotUsername() {
        return USERNAME;
    }

    public static SendMessage sendInlineKeyBoardMessage(long chatId) {

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();

        inlineKeyboardButton1.setText("Приду").setCallbackData("Хорошо");

        inlineKeyboardButton2.setText("Не приду");
        inlineKeyboardButton2.setCallbackData("Пожалуйста, укажите причину : ");

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        keyboardButtonsRow1.add(inlineKeyboardButton2);
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);

        inlineKeyboardMarkup.setKeyboard(rowList);
        return new SendMessage().setChatId(chatId).setText("Вас пригласили на собрание! ").setReplyMarkup(inlineKeyboardMarkup);
    }

}
