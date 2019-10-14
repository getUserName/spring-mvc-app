package com.tropika.app.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.tropika.app.aop.annotations.LogExecution;
import com.tropika.app.service.SetupService;
import com.tropika.app.service.booking.DTO.Room;

@Controller
public class RoomController {
	
	@Autowired
	private SetupService setupSrv;
	
	@GetMapping("/rooms") @LogExecution
    public String rooms(Model model) {
    	model.addAttribute("rooms", setupSrv.getRooms());
    	return "rooms";
    }
    
    @GetMapping("/addroom") @LogExecution
    public String addRoom(Model model, Room room) {
    	setupSrv.addRoom(room);
    	return rooms(model);
    }
    
    @GetMapping("/removeroom") @LogExecution
    public String removeRoom(Model model, Room room) {
    	setupSrv.removeRoom(room);
    	return rooms(model);
    }
    
    @GetMapping("/updateroom") @LogExecution
    public String updateRoom(Model model, Room room) {
    	setupSrv.updateRoom(room);
    	return rooms(model);
    }
}
