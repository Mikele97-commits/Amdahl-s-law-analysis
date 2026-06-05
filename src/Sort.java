import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;

public class Sort {
    public static long[] sortMulti(List<Integer> list, int threads) throws ExecutionException, InterruptedException {
        long[] times = new long[2];
        long startTime= System.nanoTime();
        ExecutorService executor = Executors.newFixedThreadPool(threads);
        List<Future<List<Integer>>> futures = new ArrayList<Future<List<Integer>>>();
        int chunkSize = list.size() / threads;
        for(int i = 0; i < threads; i++) {
            if(i== threads - 1) {
                List<Integer> subList = new ArrayList<>(list.subList(i * chunkSize, list.size()));
                futures.add(executor.submit(new SortWorker(subList)));
            }else{
                List<Integer> subList = new ArrayList<>(list.subList(i * chunkSize, (i + 1) * chunkSize));
                futures.add(executor.submit(new SortWorker(subList)));
            }

        }
        List<List<Integer>> sortedChunks=new ArrayList<>();
        long sortStartTime= System.nanoTime();
        for(Future<List<Integer>> future : futures) {
            sortedChunks.add(future.get());
        }
        long sortEndTime= System.nanoTime();
        times[1]=sortEndTime-sortStartTime;
        executor.shutdown();
        List<Integer> sortedList=new ArrayList<>();
        while(!sortedChunks.isEmpty()) {
            int smallest=Integer.MAX_VALUE;
            int smallestIndex=0;
            for (int i = 0; i < sortedChunks.size(); i++) {
                if(sortedChunks.get(i).isEmpty()) {
                    continue;
                }
                if(sortedChunks.get(i).getFirst() < smallest) {
                    smallest = sortedChunks.get(i).getFirst();
                    smallestIndex = i;
                }
            }
            sortedList.add(smallest);
            sortedChunks.get(smallestIndex).removeFirst();
            if(sortedChunks.get(smallestIndex).isEmpty()) {
                sortedChunks.remove(smallestIndex);
            }
        }
        long endTime= System.nanoTime();
        times[0] = endTime - startTime;
        return times;

    }


    public static long sortSingle(List<Integer> list) throws ExecutionException, InterruptedException {
        long startTime= System.nanoTime();
        List<Integer> sortedList=new SortWorker(list).call();
        long endTime= System.nanoTime();
        long finalTime = endTime - startTime;
        return finalTime;
    }

}
