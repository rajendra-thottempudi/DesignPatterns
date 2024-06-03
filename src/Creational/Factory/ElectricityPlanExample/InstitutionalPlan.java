package Creational.Factory.ElectricityPlanExample;

class  InstitutionalPlan extends Plan{

    //@Override - works even without this
    public void getRate(){
        rate=5.50;
    }
}