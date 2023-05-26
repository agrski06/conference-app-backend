package conference.mail;

import conference.api.lecture.Lecture;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

@Component
@RequiredArgsConstructor
public class FileMailSender implements IMailSender {

    @Override
    public void sendRegisteredNotification(String email, Lecture lecture) {
        File notifications = new File("notifications.txt");
        try {
            if (!notifications.exists()) {
                notifications.createNewFile();
            }
            FileOutputStream writer = new FileOutputStream(notifications, true);
            writer.write("To: ".concat(email).concat("\n").getBytes());
            writer.write("Date: "
                    .concat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()))
                    .concat("\n")
                    .getBytes());
            writer.write("Hi! You have been successfully registered to lecture: "
                    .concat(lecture.getName())
                    .concat("\n")
                    .getBytes());
            writer.write("\n============================================================================\n\n".getBytes());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
