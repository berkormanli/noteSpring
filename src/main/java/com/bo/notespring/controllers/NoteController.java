package com.bo.notespring.controllers;


import com.bo.notespring.NoteSpringApplication;
import com.bo.notespring.repository.NoteRepository;
import com.bo.notespring.dao.NoteDaoInterface;
import com.bo.notespring.models.Note;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class NoteController {
    @Autowired
    private NoteDaoInterface noteDAO;

    @Autowired
    private NoteRepository noteRepository;
    private static final Logger log = LoggerFactory.getLogger(NoteSpringApplication.class);

    @RequestMapping("/")
    public String home(Model model) {
        //List<Note> noteList = noteDAO.selectAllNotes();
        List<Note> noteList = noteRepository.findAll();
        log.info("home çalıştı {}", noteList.size());
        model.addAttribute("noteList", noteList);
        return "index";
    }

    @RequestMapping("/new")
    public String formPage(Model model) {
        model.addAttribute("message", "Yeni not ekleyebilirsin.");
        model.addAttribute("note", new Note());
        return "notes-form";
    }

    @RequestMapping(value="/insert", method= RequestMethod.POST)
    public String formSubmit(@ModelAttribute Note note, Model model) {
        //log.info(note.toString());
        //model.addAttribute("note", note);
        //noteDAO.insertNote(note);
        noteRepository.save(note);
        //noteList.add(note);
        return "redirect:/";
    }

    @RequestMapping(value="/delete")
    public String deleteNote(@RequestParam("id") Long noteId) {
        //log.info(noteId);
        //noteDAO.deleteNote(id);
        Optional<Note> optionalNote = noteRepository.findById(noteId);
        if (optionalNote.isPresent()) {
            noteRepository.deleteById(noteId);
        }
        return "redirect:/";
    }

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @RequestMapping(value="/edit")
    public String editNote(@RequestParam("id") Long noteId, Model model) {
        Optional<Note> tempNote = noteRepository.findById(noteId);
        //log.info(tempNote.toString());
        if (tempNote.isPresent()) {
            Note selectedNote = tempNote.get();
            model.addAttribute("message", "Daha önceden eklenmiş bir notu değiştirebilirsin.");
            model.addAttribute("note", selectedNote);
            return "notes-edit";
        } else
            return "redirect:/";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateNote(@ModelAttribute Note note, Model model) {
        log.info(note.toString());

        model.addAttribute("note", note);
        noteRepository.save(note);
        //noteList.add(note);
        return "redirect:/";
    }

    /*
    @RequestMapping(value = {"/new-password-request"}, method = RequestMethod.POST)
    public String newPasswordRequest(@Valid @ModelAttribute("newPasswordForm") PasswordChangeForm passwordChangeForm,
                                     BindingResult result,
                                     Model model,
                                     Locale locale,
                                     RedirectAttributes redAttr,
                                     HttpServletRequest req) {
        result.hasErrors();
        return "";
    }
    */
}
