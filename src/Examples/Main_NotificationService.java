package Examples;

/*
Example Question #1

At Amazon.com, we send emails and SMS messages customers every day.
Example messages like order confirmation and daily lightning deals offers etc..

Design an API that:
    Takes requests from internal systems like orders, marketing, payments, etc.
    Sends messages (email, SMS, or both) out to customers based on the request.

Extension:

Support Whatsapp, Facebook Messenger messages.
Millions TPS.
Handle messages with different priorities.

Meets Bar:
Meets almost all of the following.

Asks clarification questions like: traffic, latency, dependencies
Writes extendable classes/interfaces and reusable methods/functions
Create simple and maintainable code for the requirements, no over-engineering
Code is syntactically correct or would be syntactically correct with minor improvements
No or minor syntax

RAISES Bar:

Meets any of the following.

Meets all in "Meets Bar".
Came up solution quick with context and able to expand on that quickly

Lowers Bar:
Meets two or more of the following.

Creates unnecessarily complex code (e.g., doesn't consider reuse, poorly formatted, contains improper coding constructs)
Creates Difficult to maintain code (e.g., difficult to trace impact of changes, poor variable naming conventions)
Code is organized in a way that is difficult to read and understands
Code contains major syntax errors or is in pseudo-code
Code doesn't work as intended

*/
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Enum to represent different channels for messaging
enum Channel {
    DEFAULT, EMAIL, SMS
}

// Abstract base class for messages
abstract class IMessage {
    protected String id;
    protected String message;

    public IMessage(String id, String message) {
        this.id = id;
        this.message = message;
    }

    @Override
    public abstract String toString();
}

// Class for email messages
class EmailMessage extends IMessage {
    private String email;

    public EmailMessage(String id, String message, String email) {
        super(id, message);
        this.email = email;
    }

    @Override
    public String toString() {
        return "Email message [id: " + id + ", message: " + message + "] to " + email;
    }
}

// Class for SMS messages
class SMSMessage extends IMessage {
    private String mobileNumber;

    public SMSMessage(String id, String message, String mobileNumber) {
        super(id, message);
        this.mobileNumber = mobileNumber;
    }

    @Override
    public String toString() {
        return "SMS message [id: " + id + ", message: " + message + "] to " + mobileNumber;
    }
}

// Abstract base class for notification handlers
abstract class BaseNotificationHandler {
    public abstract void send(IMessage message);
    public abstract Channel getChannel();
}

// Class for handling email notifications
class EmailNotificationHandler extends BaseNotificationHandler {
    @Override
    public void send(IMessage message) {
        // Custom logic for sending email (maybe through a 3rd party service)
        System.out.println("Sent " + message);
    }

    @Override
    public Channel getChannel() {
        return Channel.EMAIL;
    }
}

// Class for handling SMS notifications
class SMSNotificationHandler extends BaseNotificationHandler {
    @Override
    public void send(IMessage message) {
        // Custom logic for sending SMS
        System.out.println("Sent " + message);
    }

    @Override
    public Channel getChannel() {
        return Channel.SMS;
    }
}

// Singleton factory class for managing notification handlers
class NotificationHandlerFactory {
    private static NotificationHandlerFactory instance = null;
    private Map<Channel, BaseNotificationHandler> handlers;

    private NotificationHandlerFactory() {
        handlers = new HashMap<>();
    }

    public static NotificationHandlerFactory getInstance() {
        if (instance == null) {
            instance = new NotificationHandlerFactory();
        }
        return instance;
    }

    public void addHandler(BaseNotificationHandler handler) {
        handlers.put(handler.getChannel(), handler);
    }

    public BaseNotificationHandler getHandler(Channel channel) {
        return handlers.get(channel);
    }
}

// Service class for sending messages
class MessageService {
    private NotificationHandlerFactory notificationHandlerFactory;

    public MessageService(NotificationHandlerFactory notificationHandlerFactory) {
        this.notificationHandlerFactory = notificationHandlerFactory;
    }

    public void sendNotifications(String messagesToSend) {
        // Convert JSON string to list of maps
        List<Map<String, String>> messageList = new ArrayList<>();
        messageList = parseJson(messagesToSend);

        for (Map<String, String> message : messageList) {
            if (message.get("channel").equals("email")) {
                EmailMessage emailMessage = new EmailMessage(
                        message.get("id"), message.get("message"), message.get("email"));
                BaseNotificationHandler handler = notificationHandlerFactory.getHandler(Channel.EMAIL);
                handler.send(emailMessage);
            } else if (message.get("channel").equals("sms")) {
                SMSMessage smsMessage = new SMSMessage(
                        message.get("id"), message.get("message"), message.get("mobileNumber"));
                BaseNotificationHandler handler = notificationHandlerFactory.getHandler(Channel.SMS);
                handler.send(smsMessage);
            }
        }
    }

    // Method to parse JSON string into a list of maps (for simplicity, assume valid JSON)
    private List<Map<String, String>> parseJson(String jsonString) {
        // Implement JSON parsing (e.g., using a library like Jackson or Gson)
        // For now, return an empty list
        return new ArrayList<>();
    }
}

public class Main_NotificationService {
    public static void main(String[] args) {
        // Initialize the handler factory and add handlers
        NotificationHandlerFactory handlerFactory = NotificationHandlerFactory.getInstance();
        handlerFactory.addHandler(new SMSNotificationHandler());
        handlerFactory.addHandler(new EmailNotificationHandler());

        // Initialize the message service
        MessageService messageService = new MessageService(handlerFactory);

        // Client requirements
        String messagesToSend = "[{\"id\": \"1\", \"channel\": \"email\", \"message\": \"hello how is it going?\", \"email\": \"test@test.com\"},"
                + "{\"id\": \"2\", \"channel\": \"sms\", \"message\": \"whats gwan bruh?\", \"mobileNumber\": \"9793448789\"}]";

        // Client calls send
        messageService.sendNotifications(messagesToSend);
    }
}

