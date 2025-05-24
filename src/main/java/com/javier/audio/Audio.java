package com.javier.audio;

import javax.sound.sampled.*;
import java.io.IOException;


 class Audio {
    private Clip clip;

     Audio(String audioFile) {

        if (audioFile == null ) {
            throw new NullPointerException("Audio file is null");
        }
        if (!audioFile.endsWith(".wav")) {
            throw new IllegalArgumentException("Unsupported audio format: must be .wav");
        }

        try (AudioInputStream ais = AudioSystem.getAudioInputStream(
                getClass().getClassLoader().getResource(audioFile))){
            this.clip= AudioSystem.getClip();
            this.clip.open(ais);
        } catch (UnsupportedAudioFileException uafe) {
            throw new IllegalArgumentException("Formato de audio no soportado: "+ audioFile,uafe);
        } catch (IOException ioe) {
            throw new IllegalStateException("Error de lectura al intentar abrir el archivo: " + audioFile,ioe);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }

    }

    void play() {
        this.clip.start();
    }
     void stop() {
        this.clip.stop();
    }
     void reset(){
        this.clip.stop();
        this.clip.setFramePosition(0);
    }

     void reStart(){
        reset();
        play();
    }

}
