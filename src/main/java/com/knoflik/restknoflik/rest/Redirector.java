package com.knoflik.restknoflik.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/")
public class Redirector {
   @GetMapping("/api/redirect/room")
   public ModelAndView enterRoom(@RequestParam("roomId") String roomId) {
       RedirectView redirectView = new RedirectView("/room.html?id=" + roomId);
       redirectView.setStatusCode(HttpStatus.PERMANENT_REDIRECT);
       return new ModelAndView(redirectView);
   }
}
