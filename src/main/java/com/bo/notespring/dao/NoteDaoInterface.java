package com.bo.notespring.dao;

import com.bo.notespring.models.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public interface NoteDaoInterface {
    JdbcTemplate getJdbcTemplate();

    @Autowired
    void setJdbcTemplate(JdbcTemplate jdbcTemplate);

    void setDataSource(DataSource dataSource);

    void insertNote(Note note);

    Optional<Note> selectNote(long noteId);

    List<Note> selectAllNotes();

    boolean deleteNote(int id);

    boolean updateNote(Note note);
}
