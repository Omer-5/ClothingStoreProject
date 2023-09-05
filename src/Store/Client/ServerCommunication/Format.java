package Store.Client.ServerCommunication;

// [TYPE] | [METHOD_NAME] | [PARAMETERS]
public class Format {
    public static String typeSeparator = "@@@";
    public static String methodSeparator = "###";
    public static String paramsSeparator = "&&&";
    public static String fieldSeparator = "**";

    public static String encode(ClassType type, String methodName, String param1, String param2){
        return encode(type, methodName, param1) + param2 + paramsSeparator;
    }

    public static String encode(ClassType type, String methodName, String param){
        return type + typeSeparator + methodName + methodSeparator + param + paramsSeparator;
    }

    public static ClassType getType(String str)
    {
        String typeStr = str.split(typeSeparator)[0];
        ClassType result = null;
        switch (typeStr) {
            case "CUSTOMER":
                result = ClassType.CUSTOMER;
                break;
        
            }
            return result;
    }

    public static String getMethod(String str)
    {
        String temp = str.split(typeSeparator)[1];
        return temp.split(methodSeparator)[0];

    }

    public static String getFirstParam(String str)
    {
        String temp = str.split(paramsSeparator)[0];
        return temp.split(methodSeparator)[1];
    }

    public static String getSecondParam(String str)
    {
        String temp = str.split(paramsSeparator)[1];
        return temp.split(paramsSeparator)[0];
    }
    
}
