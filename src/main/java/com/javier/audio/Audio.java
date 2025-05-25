package com.javier.audio;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

class Audio {
    private final Clip clip;

    /**
     * Crea una instancia de Audio cargando un archivo .wav desde el classpath.
     *
     * @param audioFile Ruta del archivo dentro de src/main/resources (por ejemplo: "assets/sound.wav").
     * @throws NullPointerException     si el nombre del archivo es null.
     * @throws IllegalArgumentException si la extensión no es .wav o el archivo no se encuentra o no es soportado.
     * @throws IllegalStateException    si hay un error de lectura del archivo.
     * @throws RuntimeException         si el sistema de sonido no está disponible.
     */
    Audio(String audioFile) {
        if (audioFile == null) {
            throw new NullPointerException("Audio file is null");
        }
        if (!audioFile.endsWith(".wav")) {
            throw new IllegalArgumentException("Unsupported audio format: must be .wav");
        }

        try {
            URL resource = getClass().getClassLoader().getResource(audioFile);
            if (resource == null) {
                throw new IllegalArgumentException("No se encontró el archivo de audio: " + audioFile);
            }

            try (AudioInputStream ais = AudioSystem.getAudioInputStream(resource)) {
                this.clip = AudioSystem.getClip();
                this.clip.open(ais);
            }

        } catch (UnsupportedAudioFileException uafe) {
            throw new IllegalArgumentException("Formato de audio no soportado: " + audioFile, uafe);
        } catch (IOException ioe) {
            throw new IllegalStateException("Error de lectura al intentar abrir el archivo: " + audioFile, ioe);
        } catch (LineUnavailableException e) {
            throw new RuntimeException("No se pudo acceder al sistema de audio", e);
        }
    }

    /**
     * Inicia la reproducción del clip desde la posición actual.
     */
    void play(boolean loop) {
        clip.setFramePosition(0);
        if (loop) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } else {
            clip.start();
        }
    }

    /**
     * Detiene la reproducción del clip si está activo.
     */
    void stop() {
        this.clip.stop();
    }

    /**
     * Reinicia el clip: lo detiene y posiciona al inicio.
     */
    void reset() {
        this.clip.stop();
        this.clip.setFramePosition(0);
    }

    /**
     * Reinicia el clip desde el inicio y lo reproduce nuevamente.
     */
    void reStart(boolean loop) {
        reset();
        play(loop);
    }
}