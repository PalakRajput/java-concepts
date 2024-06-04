package src.main.com.concept.designpatterns.behavioral.interpreter;

import java.util.Arrays;
import java.util.stream.Collectors;

public class InterpreterDemo {
    public static void main(String[] args) {
        String context = "is this sentence grammatically correct this is";
        NoRepeatedWordInterpreter interpreter = new NoRepeatedWordInterpreter();
        System.out.println(interpreter.interpret(context));
    }
}

interface Interpreter {
    String interpret(String context);
}

class FistNameUpperCaseInterpreter implements Interpreter {
    final EndsWithPeriodInterpreter fn = new EndsWithPeriodInterpreter();


    //Terminal interpreter
    @Override
    public String interpret(String context) {
        context = context.substring(0, 1).toUpperCase() + context.substring(1);
        return fn.interpret(context);
    }
}

class EndsWithPeriodInterpreter implements Interpreter {

    @Override
    public String interpret(String context) {
        if (!context.substring(context.length() - 1).matches("\\.")) {
            context = context + ".";
        }
        return context;
    }
}

class NoRepeatedWordInterpreter implements Interpreter {
    final FistNameUpperCaseInterpreter fn = new FistNameUpperCaseInterpreter();

    @Override
    public String interpret(String context) {
        context = Arrays.stream(context.split(" ")).distinct().collect(Collectors.joining(" "));
        return fn.interpret(context);
    }
}
