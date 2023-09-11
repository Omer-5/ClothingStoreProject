package Store.Client.ServerCommunication;

public class EncodeExecuteCommandLogger {
    public static String turnOffSavingChat() {
        return Format.encode(ClassType.LOGGER, "turnOffSavingChat");
    }

    public static String turnOnSavingChat() {
        return Format.encode(ClassType.LOGGER, "turnOnSavingChat");
    }
}
