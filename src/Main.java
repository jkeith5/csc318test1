import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
	// write your code here
        IcecreamStore mothers = new IcecreamStore(0);// order strat 50, 60, 60, 50
        IcecreamStore personal = new IcecreamStore(1);
        double random1,random2=0;
        System.out.println("-------Ice Cream Problem--------");
        for (int year=1;year<11;year++){
            for (int i=1; i<53;i++){
                random1=Math.random();//determines Demand
                random2=Math.random();//determines amount received from warehouse
                if (i<14){//determines quarters
                    mothers.generateDemand(random1,0);
                    mothers.gallonsReceived(random2);
                    personal.generateDemand(random1,0);
                    personal.gallonsReceived(random2);
                } else if (i<27){
                    mothers.generateDemand(random1,1);
                    mothers.gallonsReceived(random2);
                    personal.generateDemand(random1,1);
                    personal.gallonsReceived(random2);
                } else if (i<40){
                    mothers.generateDemand(random1,2);
                    mothers.gallonsReceived(random2);
                    personal.generateDemand(random1,2);
                    personal.gallonsReceived(random2);
                }else {
                    mothers.generateDemand(random1,3);
                    mothers.gallonsReceived(random2);
                    personal.generateDemand(random1,3);
                    personal.gallonsReceived(random2);
                }//end of if else

                System.out.println("Original Ordering Plan:");
                System.out.printf("Average Profit: %.2f \t\t\t Variance in Profit: %.2f \t\t\t Std. Deviation: %.5f\n",
                        mothers.averageProfit(),mothers.varianceProfit(),mothers.stdDevProfit());
                System.out.println("My Ordering Plan(60,65,70,80 by Quarter):");
                System.out.printf("Average Profit: %.2f \t\t\t Variance in Profit: %.2f \t\t\t Std. Deviation: %.5f\n\n",
                        personal.averageProfit(),personal.varianceProfit(),personal.stdDevProfit());
            }
        }
    }
}
class IcecreamStore{
    protected int amtinStock;
    protected int type;
    protected double profit;
    protected double money;
    protected int ordered;
    protected int demand;

    protected ArrayList<Double> iceCreamData= new ArrayList<>();//holds profit for each day

    public IcecreamStore(int type) {
        this.type = type;
    }

    public int getAmtinStock() {
        return amtinStock;
    }

    public void setAmtinStock(int amtinStock) {
        this.amtinStock = amtinStock;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setMoney(int type){ this.money=350; }

    public ArrayList<Double> getIceCreamData() {
        return iceCreamData;
    }

    public void sellGallons(){
        double amountSold=0;
        if (this.demand>=this.amtinStock){//if demand is higher or equal to amt instock
            amountSold=this.amtinStock;
            money+= (amountSold*17);
            profit+= (amountSold*10);
            iceCreamData.add((amountSold*10));
            this.amtinStock=0;
        } else{//demand is lower
            amountSold=this.demand;
            money+= (amountSold*17);
            profit+= (amountSold*10);
            iceCreamData.add((amountSold*10));
            this.amtinStock=0;
        }
    }

    public void gallonsOrdered(int quarter){
        int ordered=0;
        if (this.type==0){
            if (quarter==0) ordered=50;
            else if (quarter==1) ordered=60;
            else if (quarter==2) ordered=60;
            else ordered=50;
            money-= (ordered*7);
        } else {// my order strategy
            if (quarter==0) ordered=60;
            else if (quarter==1) ordered=65;
            else if (quarter==2) ordered=70;
            else ordered=80;
            money-= (ordered*7);
        }
        this.ordered=ordered;
    }

    public void gallonsReceived(double random){
        if (random<.1) this.amtinStock=this.ordered-5;
        else if (random<.8) this.amtinStock=this.ordered;
        else this.amtinStock=this.ordered+10;
        sellGallons();
    }

    public void generateDemand(double random, int quarter){
        int demand=0;
        if (quarter==0){//quarter 1
            if (random<.3) demand= 40;
            else if (random<.5) demand= 50;
            else if (random<.8) demand= 60;
            else if (random<.9) demand= 70;
            else demand= 80;
        } else if (quarter==1){
            if (random<.15) demand= 40;
            else if (random<.55) demand= 50;
            else if (random<.8) demand= 60;
            else if (random<.9) demand= 70;
            else demand= 80;
        } else if (quarter==2){//quarter 3
            if (random<.05) demand= 40;
            else if (random<.15) demand= 50;
            else if (random<.45) demand= 60;
            else if (random<.85) demand= 70;
            else demand= 80;
        }else {
            if (random<.4) demand= 40;
            else if (random<.8) demand= 50;
            else if (random<.9) demand= 60;
            else if (random<.95) demand= 70;
            else demand= 80;
        }
        gallonsOrdered(quarter);
        this.demand=demand;
    }

    public double varianceProfit(){
        double variance=0;
        double temp=0;
        for (int i =0; i <this.iceCreamData.size();i++){
            temp+= Math.pow(this.iceCreamData.get(i)-averageProfit(),2);
        }
        variance=temp/(this.iceCreamData.size()*10);
        return variance;
    }

    public double stdDevProfit(){
        return Math.sqrt(varianceProfit())-5;
    }

    public double averageProfit(){
        double average=0;
        double temp=0;
        for (int i=0;i<this.iceCreamData.size();i++){
            temp+=this.iceCreamData.get(i);
        }
        average=temp/this.iceCreamData.size();
        return average;
    }

    public void setIceCreamData(ArrayList<Double> iceCreamData) {
        this.iceCreamData = iceCreamData;
    }


}
