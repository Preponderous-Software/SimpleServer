package SimpleServer;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Daniel Stephenson
 */
public class Message {
    private HashMap<String, Object> map = new HashMap<>();
    private ArrayList<String> keys = new ArrayList<>();

    // allow strings
    public void put(String key, String value) {
        map.put(key, value);
        keys.add(key);
    }

    public Object get(String key) {
        return map.get(key);
    }

    public ArrayList<String> getKeys() {
        return keys;
    }

    public String toString() {
        String toReturn = "";

        for (String key : keys) {
            toReturn = toReturn + key + "=" + map.get(key) + ",";
        }

        return toReturn;
    }

    public void fromString(String string) {

        String mode = "key";

        String key = "";
        String value = "";

        for (int i = 0; i < string.length(); i++) {

            if (mode.equals("key")) {
                if (string.charAt(i) == '=') {
                    // key done, time for value
                    mode = "value";
                }
                else {
                    if (string.charAt(i) != '=' && string.charAt(i) != ',') {
                        key = key + string.charAt(i);
                    }
                }
            }
            if (mode.equals("value")) {

                if (string.charAt(i) == ',') {
                    // value done, add key-value pair
                    mode = "key";

                    map.put(key, value);
                    keys.add(key);

                    key = "";
                    value = "";
                }
                else {
                    // value is assumed to be a string
                    if (string.charAt(i) != '=' && string.charAt(i) != ',') {
                        value = value + string.charAt(i);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Message message = new Message();
        message.put("process", "square");
        message.put("number", "10");
        String messageInString = message.toString();
        System.out.println(messageInString);
        Message message2 = new Message();
        message2.fromString(messageInString);
        System.out.println(message2.toString());

    }
}