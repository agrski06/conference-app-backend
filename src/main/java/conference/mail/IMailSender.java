package conference.mail;

import conference.api.lecture.Lecture;

public interface IMailSender {
    /**
     * @param email send to this email
     * @param lecture used for retrieving info about lecture
     */
    void sendRegisteredNotification(String email, Lecture lecture);
}
