package com.bo.notespring;

import com.bo.notespring.controllers.NoteController;
import com.bo.notespring.dao.NoteDao;
import com.bo.notespring.dao.NoteDaoInterface;
import com.bo.notespring.models.Note;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootConfiguration(proxyBeanMethods = false)
@EnableAutoConfiguration
@Import({ NoteDao.class, Note.class, NoteController.class})
public class NoteSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(NoteSpringApplication.class, args);
    }

}
