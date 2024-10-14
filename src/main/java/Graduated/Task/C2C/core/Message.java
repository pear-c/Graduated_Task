package Graduated.Task.C2C.core;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor(staticName = "of")
public class Message<D> {

    private final int resultCode;
    private final D data;

}