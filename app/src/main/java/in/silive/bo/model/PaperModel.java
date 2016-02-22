package in.silive.bo.model;

import java.util.ArrayList;

/**
 * Created by kartikey on 22/02/16.
 */
public class PaperModel {
    public static String Title;
    public static String ExamCategory;
    public static String PaperCategory;
    public static String Size;
    public static String URL;

    @SuppressWarnings("serial")
    public static class PapersList extends ArrayList<PaperModel> {
    }
}
