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
    public float getSlope(Pair<Float,Float> p1, Pair<Float,Float> p2){
        return ((p2.second - p1.second) / (p2.first - p1.first));
    }

    public void fuzzification(){
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(i).fuzzySets.size(); j++) {
                //check if crisp value in range of current fuzzy set
                var exist= calRange(input.get(i).crisp,input.get(i).fuzzySets.get(j).getPoints());
                if(exist==-1)
                    input.get(i).membershipDegrees.add(0f);
                else {

                }
            }

        }
        for (ProblemParameters inputVar : inputVars) {
            for (FuzzySet fuzzySet : inputVar.getFuzzySets()) {
                int firstIdx = getRange(inputVar.getCrispValue(), fuzzySet.getPoints());
                if(firstIdx == -1)
                    inputVar.getMembershipDegrees().add(0f);
                else{
                    Point p1 = fuzzySet.getPoints().get(firstIdx);
                    Point p2 = fuzzySet.getPoints().get(firstIdx+1);
                    if (p1.getX() == p2.getX())
                        inputVar.getMembershipDegrees().add(1f);
                    else {
                        float m = getSlope(p1, p2);
                        float c = p1.getY() - (m * p1.getX());
                        inputVar.getMembershipDegrees().add((m * inputVar.getCrispValue()) + c);
                    }
                }
            }
        }
    }
}
