package enumerator;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {

    NEW("NEW"),
    PROCESSING("PROCESSING"),
    DONE("DONE"),
    ERROR("ERROR");

    private String value;

}
