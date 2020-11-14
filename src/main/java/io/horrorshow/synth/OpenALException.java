package io.horrorshow.synth;

import static org.lwjgl.openal.AL10.*;

public class OpenALException extends RuntimeException {
    OpenALException(int errorCode) {
        super("Internal %s OpenAL exception"
                .formatted(errorCode == AL_INVALID_NAME ? "invalid name"
                        : errorCode == AL_INVALID_ENUM ? "invalid enum"
                        : errorCode == AL_INVALID_VALUE ? "invalid value"
                        : errorCode == AL_INVALID_OPERATION ? "invalid operation"
                        : "unknown"));
    }
}
