package second_task_singleton_logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static Logger logger;
    protected int counter = 1;

    private Logger(){}

    public static Logger getInstance(){
        if(logger == null) logger = new Logger();
        return logger;
    }

    void log(String msg){
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);
        System.out.println("[" + formattedDate + " " + counter + "] " + msg);
        counter++;
    }
}
