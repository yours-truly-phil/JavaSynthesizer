package io.horrorshow.synth;

import io.horrorshow.synth.util.Utils;

enum Wavetable {
    Sine, Square, Saw, Triangle;

    static final int SIZE = 8192;

    static {
        final double FUND_FREQ = 1d / (SIZE / (double) Synthesizer.AudioInfo.SAMPLE_RATE);
        for (int i = 0; i < SIZE; i++) {
            double t = i / (double) Synthesizer.AudioInfo.SAMPLE_RATE;
            double tDivP = t / (1d / FUND_FREQ);
            Sine.samples[i] = (float) Math.sin(Utils.Math.frequencyToAngularFrequency(FUND_FREQ) * t);
            Square.samples[i] = Math.signum(Sine.samples[i]);
            Saw.samples[i] = (float) (2d * (tDivP - Math.floor(0.5 + tDivP)));
            Triangle.samples[i] = (float) (2d * Math.abs(Saw.samples[i]) - 1d);
        }
    }

    private final float[] samples = new float[SIZE];

    float[] getSamples() {
        return samples;
    }
}
