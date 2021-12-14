import java.util.ArrayList;

public class Flogic {
    ArrayList<ProblemParameters> input = new ArrayList<>();
    ArrayList<ProblemParameters> output = new ArrayList<>();

    public void addParameter(String name, boolean type) {
        if (type)
            input.add(new ProblemParameters(name));
        else {
            output.add(new ProblemParameters(name));
        }
    }

    //help in calc membership degree
    // if 50 between start and end of very low f set we will calc degree else =0
    public int calRange(float crisp, ArrayList<Pair<Float, Float>> points) {
        for (int i = 0; i < points.size() - 1; i++) {
            if ((crisp >= points.get(i).first) && (crisp <= points.get(i + 1).second))
                return i;
        }
        return -1;
    }

    public void fuzzification() {
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(i).fuzzySets.size(); j++) {
                var fs = input.get(i).fuzzySets.get(j);
                //check if crisp value in range of current fuzzy set
                var exist = calRange(input.get(i).crisp, fs.getPoints());
                if (exist == -1)
                    //it doesn't relate to fuzzy set
                    input.get(i).membershipDegrees.add(0f);
                else {
                    var p1 = fs.getPoints().get(exist);
                    var p2 = fs.getPoints().get(exist + 1);
                    if (p1.first == p2.first)
                        input.get(i).membershipDegrees.add(1f);
                    else {
                        //line equation
                        //slope
                        float slope = ((p2.second - p1.second) / (p2.first - p1.first));
                        //c
                        float yintersect = p1.second - (slope * p1.first);
                        input.get(i).membershipDegrees.add(slope * input.get(i).crisp + yintersect);
                    }
                }
            }
        }
    }
}
