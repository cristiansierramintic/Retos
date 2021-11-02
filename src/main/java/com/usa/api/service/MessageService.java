package com.usa.api.service;

import com.usa.api.model.Message;
import com.usa.api.repository.MessageRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getAll() {
        return messageRepository.getAll();
    }

    public Optional<Message> getMessage(int id) {
        return messageRepository.getMessage(id);
    }

    public Message save(Message message) {
        if (message.getIdMessage() == null) {
            return messageRepository.save(message);
        } else {
            Optional<Message> messageAux = messageRepository.getMessage(message.getIdMessage());
            if (messageAux.isEmpty()) {
                return messageRepository.save(message);
            } else {
                return message;
            }
        }
    }

    public Message update(Message message) {
        if (message.getIdMessage() != null) {
            Optional<Message> messageAux = messageRepository.getMessage(message.getIdMessage());
            if (!messageAux.isEmpty()) {
                if (message.getMessageText() != null) {
                    messageAux.get().setMessageText(message.getMessageText());
                }
                return messageRepository.save(messageAux.get());
            }
        }
        return message;
    }

    public boolean delete(int id) {
        Optional<Message> messageAux = messageRepository.getMessage(id);
        if (!messageAux.isEmpty()) {
            messageRepository.delete(messageAux.get());
            return true;
        }
        return false;
    }

}
