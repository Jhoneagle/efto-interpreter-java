package fi.omat.efto;

import java.util.List;
import java.util.Map;

class EftoClass implements EftoCallable {
    final String name;
    private final Map<String, EftoFunction> methods;

    EftoClass(String name, Map<String, EftoFunction> methods) {
        this.name = name;
        this.methods = methods;
    }

    EftoFunction findMethod(String name) {
        if (methods.containsKey(name)) {
            return methods.get(name);
        }

        return null;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public Object call(Interpreter interpreter, List<Object> arguments) {
        EftoInstance instance = new EftoInstance(this);
        EftoFunction initializer = findMethod("init");
        if (initializer != null) {
            initializer.bind(instance).call(interpreter, arguments);
        }
        return instance;
    }

    @Override
    public int arity() {
        EftoFunction initializer = findMethod("init");
        if (initializer == null) return 0;
        return initializer.arity();
    }
}
