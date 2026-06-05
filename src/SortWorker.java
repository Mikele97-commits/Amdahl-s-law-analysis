import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

public class SortWorker implements Callable<List<Integer>> {
    List<Integer> chunk;

    public SortWorker(List<Integer> chunk) {
        this.chunk = chunk;
    }

    public  List<Integer> call() {

            for(int i=0; i<chunk.size(); i++){
                int temp=i;
                for(int j=i+1; j<chunk.size(); j++){
                    if(chunk.get(temp)>chunk.get(j)){
                        temp=j;
                    }
                }
                Collections.swap(chunk,i,temp );
            }
            return chunk;
    }
}
