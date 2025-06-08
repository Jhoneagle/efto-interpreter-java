package fi.omat.efto;

import java.util.List;

public interface EftoCallable {
    int arity();
    Object call(Interpreter interpreter, List<Object> arguments);
}
