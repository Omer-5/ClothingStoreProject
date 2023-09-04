package Store.Client.ServerCommunication;

// [TYPE] | [METHOD_NAME] | [PARAMETERS]
public class Format {
    String typeSeparator = "@#@";
    String methodSeparator = "$%$";
    String paramsSeparator = "&*&";
    static String fieldSeparator = "**";

    public Format(ClassType type, String methodName, String param1, String param2){

    }

    public ClassType getType(String str)
    {
        String typeStr = str.split(typeSeparator)[0];
        ClassType result = null;
        switch (typeStr) {
            case "Customer":
                result = ClassType.CUSTOMER;
                break;
        
            }
            return result;
    }

    public String getMethod(String str)
    {
        String temp = str.split(this.methodSeparator)[0];
        String methodStr = str.split(typeSeparator)[1];
  
        return methodStr;

    }
    
}
