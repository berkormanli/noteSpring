package com.bo.notespring.dao;

import com.bo.notespring.models.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class NoteDao implements NoteDaoInterface {
    private static final String INSERT_TODOS_SQL = "INSERT INTO notess" + "  (title, username, description, target_date,  is_done) VALUES " + " (?, ?, ?, ?, ?);";

    private static final String SELECT_TODO_BY_ID = "select id,title,username,description,target_date,is_done from notess where id =?";
    private static final String SELECT_ALL_TODOS = "select * from notess";
    private static final String DELETE_TODO_BY_ID = "delete from notess where id = ?;";
    private static final String UPDATE_TODO = "update notess set title = ?, username= ?, description =?, target_date =?, is_done = ? where id = ?;";

    private JdbcTemplate jdbcTemplate;

    @Override
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
    @Override
    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void insertNote(Note note) {
        try
        {
            jdbcTemplate.update(
                    INSERT_TODOS_SQL,
                    note.getTitle(), note.getUsername(), note.getDescription(), note.getTargetDate(), note.getStatus());
        }
        catch (DataAccessException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Note> selectNote(long noteId) {
        try
        {
            return jdbcTemplate.queryForObject(
                    SELECT_TODO_BY_ID,
                    new Object[]{noteId},
                    (rs, row) ->
                            Optional.of(new Note(
                                    rs.getLong("id"),
                                    rs.getString("title"),
                                    rs.getString("username"),
                                    rs.getString("description"),
                                    rs.getDate("target_date").toLocalDate(),
                                    rs.getBoolean("is_done")
                            ))
            );
        }
        catch (DataAccessException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Note> selectAllNotes() {
        try
        {
            return jdbcTemplate.query(
                    SELECT_ALL_TODOS,
                    (rs, row) ->
                            new Note(
                                    rs.getLong("id"),
                                    rs.getString("title"),
                                    rs.getString("username"),
                                    rs.getString("description"),
                                    rs.getDate("target_date").toLocalDate(),
                                    rs.getBoolean("is_done")
                            )
            );
        }
        catch (NullPointerException e)
        {
            throw new RuntimeException(e);
        }
        catch (DataAccessException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteNote(int id) {
        try
        {
            return jdbcTemplate.update(
                    DELETE_TODO_BY_ID,
                    id) == 1;
        }
        catch (DataAccessException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateNote(Note note) {
        try {
            return jdbcTemplate.update(
                    UPDATE_TODO,
                    note.getTitle(), note.getUsername(), note.getDescription(), note.getTargetDate(), note.getStatus(), note.getId()
            ) == 1;
        }
        catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }
}