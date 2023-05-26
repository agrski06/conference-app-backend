package conference;

import conference.api.lecture.Lecture;
import conference.api.lecture.Topic;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class Conference {
    public static Timestamp firstLectureStartDate = Timestamp.valueOf("2023-06-01 10:00:00");
    public static Timestamp firstLectureEndDate = Timestamp.valueOf("2023-06-01 11:45:00");

    public static Timestamp secondLectureStartDate = Timestamp.valueOf("2023-06-01 12:00:00");
    public static Timestamp secondLectureEndDate = Timestamp.valueOf("2023-06-01 13:45:00");

    public static Timestamp thirdLectureStartDate = Timestamp.valueOf("2023-06-01 14:00:00");
    public static Timestamp thirdLectureEndDate = Timestamp.valueOf("2023-06-01 15:45:00");

    private final List<Lecture> lectures = new ArrayList<>();

    public Conference() {
        lectures.add(new Lecture(1L, "Responsive web design", "John Smith", firstLectureStartDate, firstLectureEndDate, Topic.FRONTEND));
        lectures.add(new Lecture(2L, "Introduction to TypeScript", "Jan Kowalski", secondLectureStartDate, secondLectureEndDate, Topic.FRONTEND));
        lectures.add(new Lecture(3L, "Frontend frameworks - Vue.js, Angular", "Anna Nowak", thirdLectureStartDate, thirdLectureEndDate, Topic.FRONTEND));

        lectures.add(new Lecture(4L, "Basics of microservices", "Katarzyna Czyk", firstLectureStartDate, firstLectureEndDate, Topic.BACKEND));
        lectures.add(new Lecture(5L, "Understanding Spring annotations", "John Smith", secondLectureStartDate, secondLectureEndDate, Topic.BACKEND));
        lectures.add(new Lecture(6L, "Comparison of .NET and Spring", "Adam Nowak", thirdLectureStartDate, thirdLectureEndDate, Topic.BACKEND));

        lectures.add(new Lecture(7L, "NoSQL introduction - MongoDB", "Adam Nowak", firstLectureStartDate, firstLectureEndDate, Topic.DATABASE));
        lectures.add(new Lecture(8L, "Indexing - what's that?", "Katarzyna Czyk", secondLectureStartDate, secondLectureEndDate, Topic.DATABASE));
        lectures.add(new Lecture(9L, "Advanced SQL", "Jan Kowalski", thirdLectureStartDate, thirdLectureEndDate, Topic.DATABASE));

        lectures.add(new Lecture(10L, "Infrastructure as code", "Adam Nowak", firstLectureStartDate, firstLectureEndDate, Topic.DEVOPS));
        lectures.add(new Lecture(11L, "Preventing security vulnerabilities", "John Smith", secondLectureStartDate, secondLectureEndDate, Topic.DEVOPS));
        lectures.add(new Lecture(12L, "Optimized toolchains", "Anna Nowak", thirdLectureStartDate, thirdLectureEndDate, Topic.DEVOPS));
    }

}
