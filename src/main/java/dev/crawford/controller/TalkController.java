package dev.crawford.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.crawford.model.Talk;
import dev.crawford.service.TalkService;

@RequestMapping("api/talk")
@RestController
public class TalkController {

    @Autowired
    private TalkService talkService;

    @GetMapping("/")
    public String getMethod(){
        return "Testing";
    }
    
    //Create
    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody Talk talk){
        ResponseEntity<String> response;
        Talk newTalk = this.talkService.createTalk(talk);
        if(newTalk != null){
            response = new ResponseEntity<>("Talk created successfully", HttpStatus.CREATED);
        } else {
            response = new ResponseEntity<>("Talk already exists", HttpStatus.CONFLICT);
        }
        return response;
    }

    //Read Individual
    @GetMapping("/{id}")
    public ResponseEntity<Talk> getTalk(@PathVariable int id){
        Talk temp = talkService.getTalk(id);
        if(temp != null){
            return new ResponseEntity<>(temp, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    //Read All
    @GetMapping("/all")
    public ResponseEntity<List<Talk>> getAllUsers() {
        return new ResponseEntity<>(this.talkService.findAllTalks(), HttpStatus.ACCEPTED);
    }
    //Update
    @PutMapping("/update")
    public ResponseEntity<Talk> editTalk(@RequestBody Talk talk){
        ResponseEntity<Talk> response;
        Talk updatedTalk;
        if(talkService.getTalk(talk.getId()) == null){
            response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            updatedTalk = this.talkService.editTalk(talk);
            response = new ResponseEntity<>(updatedTalk, HttpStatus.ACCEPTED);
        }
        return response;
    }

    //Delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTalk(@PathVariable int id){
        ResponseEntity<String> response;
        if(talkService.getTalk(id) == null){
            response = new ResponseEntity<>("Talk not found", HttpStatus.NOT_FOUND);
        } else {
            talkService.deleteTalk(talkService.getTalk(id));
            response = new ResponseEntity<>("Talk has been deleted", HttpStatus.ACCEPTED);
        }
        return response;
    }
}
