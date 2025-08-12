package com.hyuuny.trelibrary.common;

import com.hyuuny.trelibrary.books.domain.Book;
import com.hyuuny.trelibrary.books.domain.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.List;

@TestConfiguration
public class TestDataInitializer {

    @Bean
    CommandLineRunner initData(BookRepository bookRepository) {
        return args -> {
            if (bookRepository.count() == 0) {
                List<Book> books = List.of(
                        new Book("Practical MongoDB", "Architecting, Developing, and Administering MongoDB", "Sharon Tracey", "2016-03-01", "9781484206485", "https://itbook.store/img/books/9781484206485.png"),
                        new Book("The Definitive Guide to MongoDB, 3rd Edition", "A complete guide to dealing with Big Data using MongoDB", "David Hows", "2016-03-01", "9781484211830", "https://itbook.store/img/books/9781484211830.png"),
                        new Book("MongoDB in Action, 2nd Edition", "Covers MongoDB version 3.0", "Kyle Banker", "2016-03-01", "9781617291609", "https://itbook.store/img/books/9781617291609.png"),
                        new Book("Designing Across Senses", "A Multimodal Approach to Product Design", "Kristie Diehl", "2016-03-01", "9781491954249", "https://itbook.store/img/books/9781491954249.png"),
                        new Book("Web Scraping with Python, 2nd Edition", "Collecting More Data from the Modern Web", "Ryan Mitchell", "2016-03-01", "9781491985571", "https://itbook.store/img/books/9781491985571.png"),
                        new Book("Programming iOS 11", "Dive Deep into Views, View Controllers, and Frameworks", "Matt Neuburg", "2016-03-01", "9781491999226", "https://itbook.store/img/books/9781491999226.png"),
                        new Book("Securing DevOps", "Security in the Cloud", "Julien Vehent", "2016-03-01", "9781617294136", "https://itbook.store/img/books/9781617294136.png"),
                        new Book("Clean Code", "A Handbook of Agile Software Craftsmanship", "Robert C. Martin", "2016-03-01", "9780132350884", "https://itbook.store/img/books/9780132350884.png"),
                        new Book("The Clean Coder", "A Code of Conduct for Professional Programmers", "Robert C. Martin", "2016-03-01", "9780137081073", "https://itbook.store/img/books/9780137081073.png"),
                        new Book("Clean Architecture", "A Craftsman's Guide to Software Structure and Design", "Robert C. Martin", "2016-03-01", "9781617292231", "https://itbook.store/img/books/9780134494166.png"),
                        new Book("Designing Data‑Intensive Applications", "The Big Ideas Behind Reliable, Scalable, and Maintainable Systems", "Martin Kleppmann", "2016-03-01", "9781492056305", "https://itbook.store/img/books/9781492056305.png"),
                        new Book("Head First Design Patterns", "A Brain‑Friendly Guide", "Eric Freeman, Elisabeth Robson", "2016-03-01", "9780596007126", "https://itbook.store/img/books/9780596007126.png"),
                        new Book("Design Patterns", "Elements of Reusable Object‑Oriented Software", "Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides", "2016-03-02", "9780201633610", "https://itbook.store/img/books/9780201633610.png"),
                        new Book("Effective Java, 2nd Edition", "Programming Language Guide", "Joshua Bloch", "2016-03-02", "9780321125217", "https://itbook.store/img/books/9780321356680.png"),
                        new Book("Effective Java, 3rd Edition", "Programming Language Guide", "Joshua Bloch", "2016-03-02", "9780134177304", "https://itbook.store/img/books/9780134685991.png"),
                        new Book("Fluent Python", "Clear, Concise, and Effective Programming", "Luciano Ramalho", "2016-03-02", "9781492078005", "https://itbook.store/img/books/9781491946008.png"),
                        new Book("Learning React", "Functional Web Development with React and Redux", "Alex Banks, Eve Porcello", "2016-03-02", "9781491900864", "https://itbook.store/img/books/9781491954621.png"),
                        new Book("Kubernetes Patterns", "Reusable Elements for Designing Cloud‑Native Applications", "Bilgin Ibryam and Roland Huß", "2016-03-02", "9781617294532", "https://itbook.store/img/books/9781617294532.png"),
                        new Book("Kubernetes in Action", "Ecosystem, API, and Architecture", "Marko Lukša", "2016-03-02", "9781492043404", "https://itbook.store/img/books/9781617293726.png"),
                        new Book("Introduction to Algorithms", "", "Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, Clifford Stein", "2016-03-02", "9780131177055", "https://itbook.store/img/books/9780262033848.png"),
                        new Book("Clean Agile", "Back to Basics", "Robert C. Martin", "2016-03-02", "9780135781861", "https://itbook.store/img/books/9780135781861.png"),
                        new Book("Refactoring", "Improving the Design of Existing Code", "Martin Fowler", "2016-03-02", "9780201485677", "https://itbook.store/img/books/9780201485677.png"),
                        new Book("The Pragmatic Programmer", "Your Journey to Mastery", "Andrew Hunt, David Thomas", "2016-03-02", "9780135957059", "https://itbook.store/img/books/9780135957059.png"),
                        new Book("Test-Driven Development", "By Example", "Kent Beck", "2016-03-02", "9780321146533", "https://itbook.store/img/books/9780321146533.png"),
                        new Book("Continuous Delivery", "Reliable Software Releases through Build, Test, and Deployment Automation", "Jez Humble, David Farley", "2016-03-02", "9780321601919", "https://itbook.store/img/books/9780321601919.png"),
                        new Book("Domain-Driven Design", "Tackling Complexity in the Heart of Software", "Eric Evans", "2016-03-02", "9780321125217", "https://itbook.store/img/books/9780321125217.png"),
                        new Book("Working Effectively with Legacy Code", "", "Michael Feathers", "2016-03-02", "9780131177055", "https://itbook.store/img/books/9780131177055.png"),
                        new Book("Clean Architecture", "A Craftsman's Guide to Software Structure and Design", "Robert C. Martin", "2016-03-03", "9780134494166", "https://itbook.store/img/books/9780134494166.png"),
                        new Book("Code Complete", "A Practical Handbook of Software Construction", "Steve McConnell", "2016-03-03", "9780735619678", "https://itbook.store/img/books/9780735619678.png"),
                        new Book("The Mythical Man-Month", "Essays on Software Engineering", "Frederick P. Brooks Jr.", "2016-03-03", "9780201835953", "https://itbook.store/img/books/9780201835953.png"),
                        new Book("Working Effectively with Legacy Code", "", "Michael Feathers", "2016-03-03", "9780131177055", "https://itbook.store/img/books/9780131177055.png"),
                        new Book("Patterns of Enterprise Application Architecture", "", "Martin Fowler", "2016-03-03", "9780321127426", "https://itbook.store/img/books/9780321127426.png"),
                        new Book("Agile Software Development, Principles, Patterns, and Practices", "", "Robert C. Martin", "2016-03-03", "9780135974445", "https://itbook.store/img/books/9780135974445.png"),
                        new Book("The Art of Computer Programming", "", "Donald E. Knuth", "2016-03-03", "9780201896831", "https://itbook.store/img/books/9780201896831.png"),
                        new Book("Clean Code", "A Handbook of Agile Software Craftsmanship", "Robert C. Martin", "2016-03-03", "9780132350884", "https://itbook.store/img/books/9780132350884.png"),
                        new Book("Java Concurrency in Practice", "", "Brian Goetz", "2016-03-03", "9780321349606", "https://itbook.store/img/books/9780321349606.png"),
                        new Book("Effective Java", "", "Joshua Bloch", "2016-03-04", "9780134685991", "https://itbook.store/img/books/9780134685991.png"),
                        new Book("Head First Design Patterns", "", "Eric Freeman, Elisabeth Robson", "2016-03-04", "9780596007126", "https://itbook.store/img/books/9780596007126.png"),
                        new Book("Refactoring: Improving the Design of Existing Code", "", "Martin Fowler", "2016-03-04", "9780201485677", "https://itbook.store/img/books/9780201485677.png"),
                        new Book("Introduction to Algorithms", "", "Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, Clifford Stein", "2016-03-04", "9780262033848", "https://itbook.store/img/books/9780262033848.png"),
                        new Book("The Pragmatic Programmer", "", "Andrew Hunt, David Thomas", "2016-03-04", "9780201616224", "https://itbook.store/img/books/9780201616224.png"),
                        new Book("Test-Driven Development: By Example", "", "Kent Beck", "2016-03-04", "9780321146533", "https://itbook.store/img/books/9780321146533.png"),
                        new Book("Design Patterns: Elements of Reusable Object-Oriented Software", "", "Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides", "2016-03-04", "9780201633610", "https://itbook.store/img/books/9780201633610.png"),
                        new Book("Patterns of Enterprise Application Architecture", "", "Martin Fowler", "2016-03-04", "9780321127426", "https://itbook.store/img/books/9780321127426.png"),
                        new Book("Clean Architecture: A Craftsman's Guide to Software Structure and Design", "", "Robert C. Martin", "2016-03-04", "9780134494166", "https://itbook.store/img/books/9780134494166.png"),
                        new Book("Working Effectively with Legacy Code", "", "Michael Feathers", "2016-03-04", "9780131177055", "https://itbook.store/img/books/9780131177055.png"),
                        new Book("Domain-Driven Design: Tackling Complexity in the Heart of Software", "", "Eric Evans", "2016-03-04", "9780321125217", "https://itbook.store/img/books/9780321125217.png"),
                        new Book("Code Complete: A Practical Handbook of Software Construction", "", "Steve McConnell", "2016-03-04", "9780735619678", "https://itbook.store/img/books/9780735619678.png"),
                        new Book("The Mythical Man-Month: Essays on Software Engineering", "", "Frederick P. Brooks Jr.", "2016-03-04", "9780201835953", "https://itbook.store/img/books/9780201835953.png"),
                        new Book("Refactoring", "Improving the Design of Existing Code", "Martin Fowler", "2016-03-05", "9780201485677", "https://itbook.store/img/books/9780201485677.png"),
                        new Book("The Pragmatic Programmer", "Your Journey to Mastery", "Andrew Hunt, David Thomas", "2016-03-05", "9780135957059", "https://itbook.store/img/books/9780135957059.png"),
                        new Book("Test-Driven Development", "By Example", "Kent Beck", "2016-03-05", "9780321146533", "https://itbook.store/img/books/9780321146533.png"),
                        new Book("Continuous Delivery", "Reliable Software Releases through Build, Test, and Deployment Automation", "Jez Humble, David Farley", "2016-03-05", "9780321601919", "https://itbook.store/img/books/9780321601919.png"),
                        new Book("Clean Agile", "Back to Basics", "Robert C. Martin", "2016-03-05", "9780135781861", "https://itbook.store/img/books/9780135781861.png"),
                        new Book("Designing Data‑Intensive Applications", "The Big Ideas Behind Reliable, Scalable, and Maintainable Systems", "Martin Kleppmann", "2016-03-05", "9781492056305", "https://itbook.store/img/books/9781492056305.png"),
                        new Book("Effective Java", "Programming Language Guide", "Joshua Bloch", "2016-03-05", "9780134685991", "https://itbook.store/img/books/9780134685991.png"),
                        new Book("Fluent Python", "Clear, Concise, and Effective Programming", "Luciano Ramalho", "2017-06-05", "9781491946008", "https://itbook.store/img/books/9781491946008.png"),
                        new Book("Learning React", "Functional Web Development with React and Redux", "Alex Banks, Eve Porcello", "2017-06-05", "9781491954621", "https://itbook.store/img/books/9781491954621.png"),
                        new Book("Kubernetes Patterns", "Reusable Elements for Designing Cloud‑Native Applications", "Bilgin Ibryam and Roland Huß", "2017-06-05", "9781617294532", "https://itbook.store/img/books/9781617294532.png"),
                        new Book("Kubernetes in Action", "Ecosystem, API, and Architecture", "Marko Lukša", "2017-06-05", "9781492043404", "https://itbook.store/img/books/9781617293726.png"),
                        new Book("You Don't Know JS", "Up & Going", "Kyle Simpson", "2017-06-05", "9781491924464", "https://itbook.store/img/books/9781491924464.png"),
                        new Book("Eloquent JavaScript", "A Modern Introduction to Programming", "Marijn Haverbeke", "2017-06-05", "9781593279509", "https://itbook.store/img/books/9781593279509.png"),
                        new Book("JavaScript: The Good Parts", "", "Douglas Crockford", "2017-06-13", "9780596517748", "https://itbook.store/img/books/9780596517748.png"),
                        new Book("You Don't Know JS", "Scope & Closures", "Kyle Simpson", "2017-06-13", "9781449335588", "https://itbook.store/img/books/9781449335588.png"),
                        new Book("JavaScript Patterns", "Build Better Applications with Coding and Design Patterns", "Stoyan Stefanov", "2017-06-13", "9780596806750", "https://itbook.store/img/books/9780596806750.png"),
                        new Book("Effective TypeScript", "62 Specific Ways to Improve Your TypeScript", "Dan Vanderkam", "2017-06-13", "9781492053748", "https://itbook.store/img/books/9781492053748.png"),
                        new Book("Learning TypeScript", "Enhance Your Web Development Skills Using TypeScript", "Boris Cherny", "2017-06-13", "9781788391471", "https://itbook.store/img/books/9781788391471.png"),
                        new Book("Programming TypeScript", "Making Your JavaScript Applications Scale", "Boris Cherny", "2017-06-17", "9781492037651", "https://itbook.store/img/books/9781492037651.png"),
                        new Book("Pro TypeScript", "Application-Scale JavaScript Development", "Steve Fenton", "2017-06-17", "9781484214978", "https://itbook.store/img/books/9781484214978.png"),
                        new Book("TypeScript Quickly", "Learn TypeScript with Real Projects", "Yakov Fain, Anton Moiseev", "2017-06-17", "9781617295942", "https://itbook.store/img/books/9781617295942.png"),
                        new Book("Angular Up and Running", "Learning Angular, Step by Step", "Shyam Seshadri", "2017-06-17", "9781491999820", "https://itbook.store/img/books/9781491999820.png"),
                        new Book("Pro Angular", "Application Development with Angular", "Adam Freeman", "2017-06-17", "9781484219003", "https://itbook.store/img/books/9781484219003.png"),
                        new Book("Learning Angular", "A Hands-On Guide to Angular 5", "Aristeidis Bampakos, Pablo Deeleman", "2017-06-17", "9781788835583", "https://itbook.store/img/books/9781788835583.png"),
                        new Book("Angular Development with TypeScript", "Implementing Angular Applications with TypeScript", "Yakimychuk Yakiv", "2017-06-17", "9781484221105", "https://itbook.store/img/books/9781484221105.png"),
                        new Book("RxJS in Action", "Reactive Programming with RxJS", "Paul P. Daniels, Luis Atencio", "2017-10-07", "9781617293762", "https://itbook.store/img/books/9781617293762.png"),
                        new Book("React Quickly", "Painless Web Apps with React, JSX, Redux, and GraphQL", "Azat Mardan", "2017-10-07", "9781617293342", "https://itbook.store/img/books/9781617293342.png"),
                        new Book("Learning React", "Functional Web Development with React and Redux", "Alex Banks, Eve Porcello", "2017-10-07", "9781491954621", "https://itbook.store/img/books/9781491954621.png"),
                        new Book("Pro React 16", "Developing Modern Web Apps with React", "Adam Freeman", "2017-10-07", "9781484244508", "https://itbook.store/img/books/9781484244508.png"),
                        new Book("React Design Patterns and Best Practices", "", "Michele Bertoli", "2017-10-07", "9781786464538", "https://itbook.store/img/books/9781786464538.png"),
                        new Book("Fullstack React", "The Complete Guide to ReactJS and Friends", "Anthony Accomazzo, Nathaniel Murray, Ari Lerner, Clay Allsopp", "2017-10-11", "9780991344624", "https://itbook.store/img/books/9780991344624.png"),
                        new Book("Introduction to the Theory of Computation", "An introduction to formal languages and automata theory", "Michael Sipser", "2017-10-11", "9781133187790", "https://itbook.store/img/books/9781133187790.png"),
                        new Book("Algorithms", "4th Edition", "Robert Sedgewick, Kevin Wayne", "2017-10-11", "9780321573513", "https://itbook.store/img/books/9780321573513.png"),
                        new Book("Deep Learning", "Adaptive Computation and Machine Learning series", "Ian Goodfellow, Yoshua Bengio, Aaron Courville", "2017-10-11", "9780262035613", "https://itbook.store/img/books/9780262035613.png"),
                        new Book("Artificial Intelligence: A Modern Approach", "3rd Edition", "Stuart Russell, Peter Norvig", "2017-10-11", "9780136042594", "https://itbook.store/img/books/9780136042594.png"),
                        new Book("Computer Networking: A Top-Down Approach", "7th Edition", "James F. Kurose, Keith W. Ross", "2017-10-11", "9780133594140", "https://itbook.store/img/books/9780133594140.png"),
                        new Book("Operating System Concepts", "10th Edition", "Abraham Silberschatz, Peter Baer Galvin, Greg Gagne", "2017-10-11", "9781119456339", "https://itbook.store/img/books/9781119456339.png"),
                        new Book("Modern Operating Systems", "4th Edition", "Andrew S. Tanenbaum, Herbert Bos", "2017-10-12", "9780133591620", "https://itbook.store/img/books/9780133591620.png"),
                        new Book("Computer Organization and Design", "The Hardware/Software Interface, 5th Edition", "David A. Patterson, John L. Hennessy", "2017-10-12", "9780124077263", "https://itbook.store/img/books/9780124077263.png"),
                        new Book("Structure and Interpretation of Computer Programs", "2nd Edition", "Harold Abelson, Gerald Jay Sussman", "2017-10-12", "9780262510875", "https://itbook.store/img/books/9780262510875.png"),
                        new Book("Compilers: Principles, Techniques, and Tools", "2nd Edition", "Alfred V. Aho, Monica S. Lam, Ravi Sethi, Jeffrey D. Ullman", "2017-10-12", "9780321486813", "https://itbook.store/img/books/9780321486813.png"),
                        new Book("Refactoring: Improving the Design of Existing Code", "2nd Edition", "Martin Fowler", "2017-10-12", "9780134757599", "https://itbook.store/img/books/9780134757599.png"),
                        new Book("Design Patterns: Elements of Reusable Object-Oriented Software", "Erich Gamma, Richard Helm, Ralph Johnson", "John Vlissides", "2017-10-21", "9780201633610", "https://itbook.store/img/books/9780201633610.png"),
                        new Book("Effective Java", "3rd Edition", "Joshua Bloch", "2017-10-21", "9780134685991", "https://itbook.store/img/books/9780134685991.png"),
                        new Book("The Art of Computer Programming", "Volumes 1-4A", "Donald E. Knuth", "2017-10-21", "9780201896831", "https://itbook.store/img/books/9780201896831.png"),
                        new Book("Programming Pearls", "2nd Edition", "Jon Bentley", "2017-10-21", "9780201657883", "https://itbook.store/img/books/9780201657883.png"),
                        new Book("Clean Code", "A Handbook of Agile Software Craftsmanship", "Robert C. Martin", "2017-10-21", "9780132350884", "https://itbook.store/img/books/9780132350884.png"),
                        new Book("The Clean Coder", "A Code of Conduct for Professional Programmers", "Robert C. Martin", "2017-10-21", "9780137081073", "https://itbook.store/img/books/9780137081073.png"),
                        new Book("Test-Driven Development: By Example", "TDD", "Kent Beck", "2017-10-21", "9780321146533", "https://itbook.store/img/books/9780321146533.png"),
                        new Book("Continuous Delivery", "Jez Humble", "David Farley", "2017-10-21", "9780321601919", "https://itbook.store/img/books/9780321601919.png"),
                        new Book("Working Effectively with Legacy Code", "Effectively with Legacy Code", "Michael Feathers", "2017-10-22", "9780131177055", "https://itbook.store/img/books/9780131177055.png")
                );
                bookRepository.saveAll(books);
            }
        };
    }
}
