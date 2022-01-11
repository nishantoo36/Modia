package StepDefinitions;

public class Log {
    public static void log(String message){
        Hooks.scenario.log(message);
    }
}
