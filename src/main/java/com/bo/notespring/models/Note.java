package com.bo.notespring.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Table(name="notess")
@Entity
public class Note {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;
    @Column(name = "username")
    private String username;
    @Column(name = "description")
    private String description;
    @Column(name = "target_date")
    private LocalDate targetDate;
    @Column(name = "is_done")
    private boolean status;

    public Note() {
    }

    public Note(Long id, String title, String username, String description, LocalDate targetDate, boolean status) {
        this.id = id;
        this.title = title;
        this.username = username;
        this.description = description;
        this.targetDate = targetDate;
        this.status = status;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", username='" + username + '\'' +
                ", description='" + description + '\'' +
                ", targetDate=" + targetDate +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Note other = (Note) obj;
        if (id != other.id)
            return false;
        return true;
    }
}
