package io.horrorshow.synth;

import io.horrorshow.synth.util.Utils;

import javax.swing.*;
import java.awt.*;
import java.util.function.Function;

public class WaveViewer extends JPanel {

    private final Oscillator[] oscillators;

    public WaveViewer(Oscillator[] oscillators) {
        this.oscillators = oscillators;
        setBorder(Utils.WindowDesign.LINE_BORDER);
    }

    @Override
    public void paintComponent(Graphics graphics) {
        final int PAD = 25;
        super.paintComponent(graphics);
        Graphics2D g = (Graphics2D) graphics;
        int numSamples = getWidth() - PAD * 2;
        double[] mixedSamples = new double[numSamples];
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        for (Oscillator oscillator : oscillators) {
            double[] samples = oscillator.getSampleWaveform(numSamples);
            for (int i = 0; i < samples.length; i++) {
                mixedSamples[i] += samples[i] / oscillators.length;
            }
        }
        int midY = getHeight() / 2;
        Function<Double, Integer> sampleToYCoord =
                sample -> (int) (midY + sample * (midY - PAD));
        g.drawLine(PAD, midY, getWidth() - PAD, midY);
        g.drawLine(PAD, PAD, PAD, getHeight() - PAD);
        for (int i = 0; i < numSamples; i++) {
            int nextY = i == numSamples - 1
                    ? sampleToYCoord.apply(mixedSamples[i])
                    : sampleToYCoord.apply(mixedSamples[i + 1]);
            g.drawLine(PAD + i,
                    sampleToYCoord.apply(mixedSamples[i]), PAD + i + 1, nextY);
        }
    }
}
