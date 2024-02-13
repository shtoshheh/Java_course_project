package Entities;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "progress")
public class Progress implements Serializable {
    @Serial
    private static final long serialVersionUID = 7L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long progress_id;
    protected int vocabulary_progress;
    protected int grammar_progress;
    protected int listening_progress;
    protected int speaking_progress;

    public Progress(){}

    public Long getProgress_id() {
        return progress_id;
    }

    public void setProgress_id(Long progress_id) {
        this.progress_id = progress_id;
    }

    public int getVocabulary_progress() {
        return vocabulary_progress;
    }

    public void setVocabulary_progress(int vocabulary_progress) {
        this.vocabulary_progress = vocabulary_progress;
    }

    public int getGrammar_progress() {
        return grammar_progress;
    }

    public void setGrammar_progress(int grammar_progress) {
        this.grammar_progress = grammar_progress;
    }

    public int getListening_progress() {
        return listening_progress;
    }

    public void setListening_progress(int listening_progress) {
        this.listening_progress = listening_progress;
    }

    public int getSpeaking_progress() {
        return speaking_progress;
    }

    public void setSpeaking_progress(int speaking_progress) {
        this.speaking_progress = speaking_progress;
    }
}
