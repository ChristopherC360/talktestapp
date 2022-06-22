package dev.crawford.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.crawford.model.Talk;
import dev.crawford.repository.TalkRepository;

@Service
public class TalkService {

    @Autowired
    private TalkRepository talkRepository;

    //Create
    public Talk createTalk(Talk talk) {
        Optional<Talk> optionalTalk = this.talkRepository.findById(talk.getId());
        if(optionalTalk.isEmpty()){
            return this.talkRepository.save(talk);
        }
        return null;
    }

    //Read Individual
    public Talk getTalk(int id) {
        Optional<Talk> optionalTalk = this.talkRepository.findById(id);
        if(optionalTalk.isPresent()){
            return optionalTalk.get();
        } 
        return null;
    }

    //Read All
    public List<Talk> findAllTalks() {
        List<Talk> talkList = this.talkRepository.findAll();
        return talkList;
    }

    //Update
    public Talk editTalk(Talk talk) {
        Optional<Talk> optionalTalk = this.talkRepository.findById(talk.getId());
        Talk temp = optionalTalk.get();
        if(temp == null) return null;
        else {
            if(talk.getName() != null) temp.setName(talk.getName());
            if(talk.getSpeaker() != null) temp.setSpeaker(talk.getSpeaker());
            if(talk.getVenue() != null) temp.setVenue(talk.getVenue());
            if(talk.getDuration() != null) temp.setDuration(talk.getDuration());
        }
        return this.talkRepository.save(temp);
    }

    //Delete
    public void deleteTalk(Talk talk) {
        this.talkRepository.delete(talk);
    }
}
