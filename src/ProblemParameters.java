import java.util.ArrayList;

public class ProblemParameters {
     String Name;
     float crisp;
     ArrayList<FuzzySet>fuzzySets=new ArrayList<>();
     ArrayList <Float> membershipDegrees=new ArrayList<>();

     ProblemParameters(String name){this.Name=name;}
}
