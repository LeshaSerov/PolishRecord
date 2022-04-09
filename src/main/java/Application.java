import polish.PolishConvertor;

import java.util.Objects;
import java.util.stream.Collectors;

public class Application {
    public static void main(String... args) {
        PolishConvertor polishConvertor = new PolishConvertor();
        String str = "1+2*3";
        System.out.println(polishConvertor.convert(str).stream().map(Objects::toString).map(x->x+' ').collect(Collectors.joining()));
    }
}
