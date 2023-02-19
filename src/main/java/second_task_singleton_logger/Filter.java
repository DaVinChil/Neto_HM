package second_task_singleton_logger;

import java.util.ArrayList;
import java.util.List;

public class Filter {
    private Logger logger;
    protected int limit;

    public int getLimit(){
        return limit;
    }

    public void setLimit(int limit){
        this.limit = limit;
    }

    public Filter(int limit){
        logger = Logger.getInstance();
        this.limit = limit;
    }

    public List<Integer> filterOut(List<Integer> sourceList){
        logger.log("Start filtering");
        List<Integer> list  = new ArrayList<>();
        int size = sourceList.size();
        for(int i = 0; i < sourceList.size(); i++){
            if(sourceList.get(i) < limit){
                logger.log("Element \"" + sourceList.get(i) + "\" doesnt suit");
            } else {
                list.add(sourceList.get(i));
            }
        }

        logger.log("Filter pass " + list.size() + " elements from " + size);
        return list;
    }
}
