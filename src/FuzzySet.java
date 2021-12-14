import java.util.ArrayList;

public class FuzzySet {
    //used for assigned ranges
    //i.e.) very low,low ...
    private final String name;
    //used in defuzzfication
    private float centroid;
    //Points x and assign 1 from shape
    private final ArrayList<Pair<Float, Float>> points = new ArrayList<>();

    FuzzySet(String name, ArrayList<Float> Xs) {
        this.name = name;
        //if triangle
        ArrayList<Float> Ys = new ArrayList<>();
        if (Xs.size() == 3) {
            //f cast to Float
            Ys.add(0.0F);
            Ys.add(1.0F);
            Ys.add(0.0F);
        } else if (Xs.size() == 4) {
            Ys.add(0.0F);
            Ys.add(1.0F);
            Ys.add(1.0F);
            Ys.add(0.0F);
        }
        for (int i = 0; i < Xs.size(); i++)
            points.add(new Pair(Xs.get(i), Ys.get(i)));
    }

    public ArrayList<Pair<Float, Float>> getPoints() {
        return points;
    }
}
