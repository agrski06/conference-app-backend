package conference.mail;

import conference.api.lecture.Lecture;

public interface IMailSender {
    /**
     * Defines mail sent after successful registration for lecture
     * @param email send to this email
     * @param lecture used for retrieving info about lecture
     */
    void sendRegisteredNotification(String email, Lecture lecture);
}
