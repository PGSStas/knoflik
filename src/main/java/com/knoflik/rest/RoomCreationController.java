package com.knoflik.rest;

import com.knoflik.entities.RoomSettings;
import com.knoflik.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/room-creation")
public class RoomCreationController {
    @Autowired
    private RoomService roomService;

    @GetMapping
    public String roomCreation(final Model model) {
        model.addAttribute("roomForm", new RoomSettings());

        return "room-creation";
    }

    @PostMapping
    public String createRoom(
            @ModelAttribute("roomForm") final RoomSettings roomForm,
            final BindingResult bindingResult,
            final Model model) {
        if (bindingResult.hasErrors()) {
            return "room-creation";
        }
        if (roomForm.getTopics()
                > 100000000) { // todo: проверить, достаточно ли тем в пакете
            model.addAttribute("packageError", "В пакете недостаточно тем");
            return "room-creation";
        }
        String id = roomService.createRoom(roomForm);
        return "redirect:/room?id=" + id;
    }
}
