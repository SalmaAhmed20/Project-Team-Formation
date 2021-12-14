import java.util.ArrayList;
import java.util.Collections;

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
        for (int k = 0; k < points.size(); k++) {
            System.out.print("("+points.get(k).first+","+points.get(k).second+")");

        }
        for (int i = 0; i < points.size() - 1; i++) {
            if ((crisp >= points.get(i).first) && (crisp <= points.get(i + 1).first)) {
                return i;
            }
        }
        return -1;
    }

    public void fuzzification() {
        for (int i = 0; i < input.size(); i++) {
            System.out.println(input.get(i).Name);
            for (int j = 0; j < input.get(i).fuzzySets.size(); j++) {
                var fs = input.get(i).fuzzySets.get(j);
                System.out.print(fs.getName() + " ");
                //check if crisp value in range of current fuzzy set
                var exist = calRange(input.get(i).crisp, fs.getPoints());
                if (exist == -1)
                    //it doesn't relate to fuzzy set
                    input.get(i).membershipDegrees.add(0f);
                else {
                    var p1 = fs.getPoints().get(exist);
                    var p2 = fs.getPoints().get(exist + 1);
                    if (p1.first.equals(p2.first))
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
                System.out.println(" "+input.get(i).membershipDegrees.get(j));
            }
            System.out.println();
        }
    }

    public void inference() {
        //-----rule1-----
        //project_funding is high or team_experience_level is expert then risk is low.
        ArrayList<Float> values1 = new ArrayList<>();
        ArrayList<Float> values_2 = new ArrayList<>();
        float rule;
        values1.add(input.get(0).membershipDegrees.get(3));
        values1.add(input.get(1).membershipDegrees.get(2));
        rule = Collections.max(values1);
        output.get(0).membershipDegrees.add(rule);
        values1.clear();
        //-----rule2-----
        //project_funding is medium and team_experience_level is intermediate
        // or team_experience_level is beginner then risk is normal.
        values1.add(input.get(0).membershipDegrees.get(2));
        values1.add(input.get(1).membershipDegrees.get(1));
        values_2.add(Collections.min(values1));
        values_2.add(input.get(1).membershipDegrees.get(0));
        rule = Collections.max(values_2);
        output.get(0).membershipDegrees.add(rule);
        values1.clear();
        values_2.clear();
        //-----rule3-rule4-----
        //project_funding is very low then risk is high
        //project_funding is low and team_experience_level is beginner then risk is high.
        values_2.add(input.get(0).membershipDegrees.get(0));
        values1.add(input.get(0).membershipDegrees.get(1));
        values1.add(input.get(1).membershipDegrees.get(0));
        values_2.add(Collections.min(values1));
        rule = Collections.max(values_2);
        output.get(0).membershipDegrees.add(rule);
        values1.clear();
        values_2.clear();


    }

    public float CalculateCentroids(ArrayList<Pair<Float, Float>> points) {
        float Sum = 0;
        for (int i = 0; i < points.size(); i++)
            Sum += points.get(i).first;

        return Sum / points.size();
    }
}
