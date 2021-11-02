package com.usa.api.service;

import com.usa.api.model.Score;
import com.usa.api.repository.ScoreRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreService {
    
    @Autowired
    private ScoreRepository scoreRepository;
    
    public List<Score> getAll() {
        return scoreRepository.getAll();
    }
    
    public Optional<Score> getScore(int id) {
        return scoreRepository.getScore(id);
    }
    
    public Score save(Score score) {
        if (score.getIdScore() == null) {
            return scoreRepository.save(score);
        } else {
            Optional<Score> scoreAux = scoreRepository.getScore(score.getIdScore());
            if (scoreAux.isEmpty()) {
                return scoreRepository.save(score);
            } else {
                return score;
            }
        }
    }
    
    public Score update(Score score) {
        if (score.getIdScore() != null) {
            Optional<Score> scoreAux = scoreRepository.getScore(score.getIdScore());
            if (!scoreAux.isEmpty()) {
                if (score.getScore() != null) {
                    scoreAux.get().setScore(score.getScore());
                }
                if (score.getMessageText() != null) {
                    scoreAux.get().setMessageText(score.getMessageText());
                }
                return scoreRepository.save(scoreAux.get());
            }
        }
        return score;
    }
    
    public boolean delete(int id) {
        Optional<Score> scoreAux = scoreRepository.getScore(id);
        if (!scoreAux.isEmpty()) {
            scoreRepository.delete(scoreAux.get());
            return true;
        }
        return false;
    }
}
