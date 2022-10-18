package ru.relex.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.relex.service.UpdateProducer;
import ru.relex.utils.MessageUtils;

import static ru.relex.model.RabbitQueue.*;

@Component
@Log4j
public class UpdateController {
    private TelegramBot telegramBot;
    private final MessageUtils messageUtils;
    private final UpdateProducer updateProducer;

    public UpdateController(MessageUtils messageUtils, UpdateProducer updateProducer) {
	this.messageUtils = messageUtils;
	this.updateProducer = updateProducer;
    }

    public void registerBot(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }
    
    public void processUpdate(Update update) {
        if (update == null) {
            log.error("Received update is null");
            return;
	}
        
        if (update.hasMessage()) {
            distributeMessagesByType(update);
	} else {
            log.error("Unsupported message type is received: " + update);
	}
    }

    private void distributeMessagesByType(Update update) {
        var message = update.getMessage();
        if (message.hasText()) {
            processTextMessage(update);
	} else if (message.hasDocument()) {
            processDocMessage(update);
	} else if (message.hasPhoto()) {
            processPhotoMessage(update);
	} else {
            setUnsupportedMessageTypeView(update);
	}
    }

    private void setUnsupportedMessageTypeView(Update update) {
        var sendMessage = messageUtils.generateSendMessageWithText(update,
			"Неподдерживаемый тип сообщения!");
        setView(sendMessage);
    }

    private void setFileIsReceivedView(Update update) {
	var sendMessage = messageUtils.generateSendMessageWithText(update,
			"Файл получен! Обрабатывается...");
	setView(sendMessage);
    }

    public void setView(SendMessage sendMessage) {
        telegramBot.sendAnswerMessage(sendMessage);
    }

    private void processPhotoMessage(Update update) {
	updateProducer.produce(PHOTO_MESSAGE_UPDATE, update);
	setFileIsReceivedView(update);
    }

    private void processDocMessage(Update update) {
	updateProducer.produce(DOC_MESSAGE_UPDATE, update);
	setFileIsReceivedView(update);
    }

    private void processTextMessage(Update update) {
        updateProducer.produce(TEXT_MESSAGE_UPDATE, update);
    }
}
