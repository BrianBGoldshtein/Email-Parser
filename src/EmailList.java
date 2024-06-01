import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class EmailList {
    private String data;

    private ArrayList<String> emails;
    public EmailList(String s) {
        data = s;

        createEmails();
    }

    public ArrayList<String> getEmails() {
        return emails;
    }

    public int size() {
        return emails.size();
    }

    public String toString() {
        String out = "";

        for(String s: emails) {
            out += s + ", ";
        }
        out = out.substring(0, out.length()-2);
        return out;
    }

    private void createEmails() {
        emails = new ArrayList<>();

        //split data text by the special characters
        for(int i = data.length()-1; i >= 0; i--) {
            char c = data.charAt(i);
            if(Character.isLetter(c) || Character.isDigit(c) || c == '.' || c == '@') continue;

            data = data.substring(0,i) + "_" + data.substring(i+1);
        }

        String s[] = data.split("_");

        //only add strings that contain an @ symbol (meaning they are emails)
        for(String string: s) {
            if(string.contains("@")) emails.add(string);
        }

        //get rid of any "extra" text from the end of the emails, after ".com" and ".org"
        for(int i = 0; i < emails.size(); i++) {
            String email = emails.get(i);
            int index = Math.max(email.indexOf("org"), email.indexOf("com"));

            if(index+3 < email.length()) email = email.substring(0, index+3);

            emails.set(i, email);
        }
    }

    public static EmailList constructList(String directory) throws IOException {
        //harvests data String from .txt file
        List<String> list = Files.readAllLines(Paths.get(directory));
        String data = "";
        for(String s: list) {
            data += s;
        }

        return new EmailList(data);
    }
}
