package conference.config;

import conference.Conference;
import conference.api.lecture.Lecture;
import conference.api.lecture.LectureRepository;
import conference.api.lecture.Topic;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.HashSet;

@Component
@RequiredArgsConstructor
public class DemoDataLoader {

    private final LectureRepository lectureRepository;

    @EventListener
    public void appReady(ApplicationReadyEvent event) {
        Timestamp firstLectureStartDate = Conference.firstLectureStartDate;
        Timestamp firstLectureEndDate = Conference.firstLectureEndDate;

        Timestamp secondLectureStartDate = Conference.secondLectureStartDate;
        Timestamp secondLectureEndDate = Conference.secondLectureEndDate;

        Timestamp thirdLectureStartDate = Conference.thirdLectureStartDate;
        Timestamp thirdLectureEndDate = Conference.thirdLectureEndDate;

        lectureRepository.save(new Lecture(null, "Responsive web design", "John Smith", firstLectureStartDate, firstLectureEndDate, Topic.FRONTEND, new HashSet<>()));
        lectureRepository.save(new Lecture(null, "Introduction to TypeScript", "Jan Kowalski", secondLectureStartDate, secondLectureEndDate, Topic.FRONTEND, new HashSet<>()));
        lectureRepository.save(new Lecture(null, "Frontend frameworks - Vue.js, Angular", "Anna Nowak", thirdLectureStartDate, thirdLectureEndDate, Topic.FRONTEND, new HashSet<>()));

        lectureRepository.save(new Lecture(null, "Basics of microservices", "Katarzyna Czyk", firstLectureStartDate, firstLectureEndDate, Topic.BACKEND, new HashSet<>()));
        lectureRepository.save(new Lecture(null, "Understanding Spring annotations", "John Smith", secondLectureStartDate, secondLectureEndDate, Topic.BACKEND, new HashSet<>()));
        lectureRepository.save(new Lecture(null, "Comparison of .NET and Spring", "Adam Nowak", thirdLectureStartDate, thirdLectureEndDate, Topic.BACKEND, new HashSet<>()));

        lectureRepository.save(new Lecture(null, "NoSQL introduction - MongoDB", "Adam Nowak", firstLectureStartDate, firstLectureEndDate, Topic.DATABASE, new HashSet<>()));
        lectureRepository.save(new Lecture(null, "Indexing - what's that?", "Katarzyna Czyk", secondLectureStartDate, secondLectureEndDate, Topic.DATABASE, new HashSet<>()));
        lectureRepository.save(new Lecture(null, "Advanced SQL", "Jan Kowalski", thirdLectureStartDate, thirdLectureEndDate, Topic.DATABASE, new HashSet<>()));

        lectureRepository.save(new Lecture(null, "Infrastructure as code", "Adam Nowak", firstLectureStartDate, firstLectureEndDate, Topic.DEVOPS, new HashSet<>()));
        lectureRepository.save(new Lecture(null, "Preventing security vulnerabilities", "John Smith", secondLectureStartDate, secondLectureEndDate, Topic.DEVOPS, new HashSet<>()));
        lectureRepository.save(new Lecture(null, "Optimized toolchains", "Anna Nowak", thirdLectureStartDate, thirdLectureEndDate, Topic.DEVOPS, new HashSet<>()));
    }

}
