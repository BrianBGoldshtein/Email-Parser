import java.io.IOException;

class Main {
    public static void main(String[] args) throws IOException {
        EmailList list = EmailList.constructList("src/Emails.txt");

        System.out.println(list);
        System.out.println(list.size());
    }
}