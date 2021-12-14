import java.util.ArrayList;
import java.util.Arrays;

public class Formation {
    public static void main(String[] args) {
        Flogic _flogic = new Flogic();
        _flogic.addParameter("project_funding",true);
        /////////////////////////////////////////////////////////////////////////
        ArrayList<Float> verylow=new ArrayList<>();
        verylow.add(0F);verylow.add(0F);verylow.add(10F);verylow.add(30F);
        _flogic.input.get(0).fuzzySets.add(new FuzzySet("verylow",verylow));
        /////////////////////////////////////////////////////////////////////////
        ArrayList<Float> low=new ArrayList<>();
        low.add(10F);low.add(30F);low.add(40F);low.add(60F);
        _flogic.input.get(0).fuzzySets.add(new FuzzySet("low",low));
        ////////////////////////////////////////////////////////////////////////
        ArrayList<Float> medium=new ArrayList<>();
        medium.add(40F);medium.add(60F);medium.add(70F);medium.add(90F);
        _flogic.input.get(0).fuzzySets.add(new FuzzySet("medium",medium));
        ///////////////////////////////////////////////////////////////////////////
        ArrayList<Float> high=new ArrayList<>();
        high.add(70F);high.add(90F);high.add(100F);high.add(100F);
        _flogic.input.get(0).fuzzySets.add(new FuzzySet("high",high));
        ///////////////////////////////////////////////////////////////////////////

        _flogic.addParameter("team_experience_level",true);
        //////////////////////////////////////////////////////////////////////////
        ArrayList<Float>beginner=new ArrayList<>();
        beginner.add(0F);beginner.add(15F);beginner.add(30F);
        _flogic.input.get(1).fuzzySets.add(new FuzzySet("beginner",beginner));
        //////////////////////////////////////////////////////////////////////////
        ArrayList<Float>intermediate=new ArrayList<>();
        intermediate.add(15F);intermediate.add(30F);intermediate.add(45F);
        _flogic.input.get(1).fuzzySets.add(new FuzzySet("intermediate",intermediate));
        //////////////////////////////////////////////////////////////////////////
        ArrayList<Float>expert=new ArrayList<>();
        expert.add(30F);expert.add(60F);expert.add(60F);
        _flogic.input.get(1).fuzzySets.add(new FuzzySet("expert",expert));
        /////////////////////////////////////////////////////////////////////////////

        _flogic.addParameter("risk",false);
        //////////////////////////////////////////////////////////////////////////////////////
        ArrayList<Float>risklow=new ArrayList<>();
        risklow.add(0F);risklow.add(25F);risklow.add(50F);
        _flogic.output.get(0).fuzzySets.add(new FuzzySet("low",risklow));
        //////////////////////////////////////////////////////////////////////////////////////
        ArrayList<Float>risknormal=new ArrayList<>();
        risknormal.add(25F);risknormal.add(50F);risknormal.add(75F);
        _flogic.output.get(0).fuzzySets.add(new FuzzySet("normal",risknormal));
        //////////////////////////////////////////////////////////////////////////////////////
        ArrayList<Float>riskhigh=new ArrayList<>();
        riskhigh.add(50F);riskhigh.add(100F);riskhigh.add(100F);
        _flogic.output.get(0).fuzzySets.add(new FuzzySet("high",riskhigh));
        /////////////////////////////////////////////////////////////////////////////////////

        _flogic.input.get(0).crisp=50;
        _flogic.input.get(1).crisp=40;

        _flogic.fuzzification();

    }
}
