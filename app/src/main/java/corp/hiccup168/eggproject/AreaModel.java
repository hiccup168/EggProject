package corp.hiccup168.eggproject;

/**
 * Created by hiccup168 on 2015/8/4.
 */
public class AreaModel {

    static String[] getAreaContents(String floor){
        String[] results = new String[]{};
        switch(floor){
            case "2F":
                results = new String[]{"2-1", "2-2"};
                break;
            case "3F":
                results = new String[]{"3-1", "3-2", "3-3"};
                break;
            case "4F":
                results = new String[]{"4-1", "4-2", "4-3"};
                break;
            case "5F":
                results = new String[]{"5-1", "5-2"};
                break;
            case "6F":
                results = new String[]{"6-1"};
                break;
        }

        return results;
    }

}
