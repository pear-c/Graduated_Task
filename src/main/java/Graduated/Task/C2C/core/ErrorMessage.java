package Graduated.Task.C2C.core;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor(staticName = "of")
public class ErrorMessage {
    private final int resultCode;
    private final String message;
}