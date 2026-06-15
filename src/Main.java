import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        //Create.all(); //Call if doesn't have files
        // List<Integer> largeList=Read.large();
        List<Integer> smallList=Read.small();
        List<Integer> mediumList=Read.medium();
        //float fSmall,fMedium,fLarge;

        List<Long> smallTimes=new ArrayList<>();
        List<Float> speedupSmall=new ArrayList<>();
        for (int i=1;i<=4;i++){
            if(i==1){

                smallTimes.add(Sort.sortSingle(smallList));
            }else{
                smallTimes.add(Sort.sortMulti(smallList,i)[0]);
                speedupSmall.add((float) smallTimes.getFirst()/smallTimes.get(i-1));
            }
        }

        List<Long> mediumTimes=new ArrayList<>();
        List<Float> speedupMedium=new ArrayList<>();
        for (int i=1;i<=4;i++){
            if(i==1){
                mediumTimes.add(Sort.sortSingle(mediumList));
            }else{
                mediumTimes.add(Sort.sortMulti(mediumList,i)[0]);
                speedupMedium.add((float) mediumTimes.getFirst()/mediumTimes.get(i-1));
            }
        }

       /*List<Long> largeTimes=new ArrayList<>();
        for (int i=1;i<=8;i=i*2){
            if(i==1){
                largeTimes.add(Sort.sortSingle(largeList)[0]);
            }else{
                largeTimes.add(Sort.sortMulti(largeList,i)[0]);
            }
        }*/  //Don't run until you got hours to spend

        //System.out.println("Given times are calculated for 1,2,4 and 8 threads respectively");


        //System.out.println("Amdahl's law is defined with equation \nS(P)=1/((1-f)+f/P)\n Where S(P) is speedup get by adding P threads, and f is a constant dependent on nature of program itself, or to be precise, fraction of the program that can be executed in parallel");
        //System.out.println("Here 'f' is calculated as time spent on sorting itself divided by time of whole operation, that is read, split, sort, merge, on 4 threads");
        //System.out.println("In given examples P equals respectively 1,2,4,8, therefore according to the law");
        float fSmall=calculateFSmall();
        System.out.println("F small:"+fSmall);
        //System.out.println("F medium:"+calculateFMedium());
        //System.out.println("F large:" +calculateFLarge());
        System.out.println("Times for small file");
        System.out.println(smallTimes.toString());
        System.out.println("Speedup small:"+speedupSmall.toString());
        System.out.println("Theoretical speedup S(P) =1/("+(1-fSmall)+")+"+fSmall+"/P)");
        System.out.println("So S(2)="+1/((1-fSmall)+fSmall/2));
        System.out.println("\n\n");

        float fMedium=calculateFMedium();
        System.out.println("Times for medium file");
        System.out.println(mediumTimes.toString());
        System.out.println("Speedup medium:"+speedupMedium.toString());
        System.out.println("Theoretical speedup S(P) =1/("+(1-fMedium)+")+"+fMedium+"/P)");
        System.out.println("So S(2)="+1/((1-fMedium)+fMedium/2));


    }

    public static float calculateFSmall() throws IOException, ExecutionException, InterruptedException {
        long startTime = System.nanoTime();
        List<Integer> smallList=Read.small();
        long sortTime=Sort.sortMulti(smallList,4)[1];
        long endTime=System.nanoTime();
        return (float) sortTime /(endTime-startTime);
    }
    public static float calculateFMedium() throws IOException, ExecutionException, InterruptedException {
        long startTime = System.nanoTime();
        List<Integer> mediumList=Read.medium();
        long sortTime=Sort.sortMulti(mediumList,4)[1];
        long endTime=System.nanoTime();
        return (float) sortTime /(endTime-startTime);
    }
    public static float calculateFLarge() throws IOException, ExecutionException, InterruptedException {
        long startTime = System.nanoTime();
        List<Integer> largeList=Read.large();
        long sortTime=Sort.sortMulti(largeList,4)[1];
        long endTime=System.nanoTime();
        return (float) sortTime /(endTime-startTime);
    }
}
