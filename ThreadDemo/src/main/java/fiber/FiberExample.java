package fiber;

import co.paralleluniverse.fibers.Fiber;
import co.paralleluniverse.fibers.SuspendExecution;
import co.paralleluniverse.strands.channels.Channel;
import co.paralleluniverse.strands.channels.Channels;
 
import java.util.concurrent.ExecutionException;
 
public class FiberExample {
    private static void printer(Channel<Integer> in) throws SuspendExecution,  InterruptedException {
        Integer v;
        while ((v = in.receive()) != null) {
            System.out.println("<< " + v);
        }
    }
 
    public static void main(String[] args) throws ExecutionException, InterruptedException, SuspendExecution {
        //定义两个Channel
        Channel<Integer> naturals = Channels.newChannel(1024, Channels.OverflowPolicy.BLOCK, true, true);
        Channel<Integer> squares = Channels.newChannel(1024, Channels.OverflowPolicy.BLOCK, true, true);
 
        //运行两个Fiber实现.
        new Fiber<>(() -> {
            for (int i = 0; i < 1000; i++) {
                System.out.println(">> " + i);
                naturals.send(i);
            }
            naturals.close();
        }).start();
 
        new Fiber<>(() -> {
            while (!naturals.isClosed()) {
                Integer v = naturals.receive();
                System.out.println("< " + v);
                squares.send(v * v);
            }
            System.out.println("Stopped receiving messages");
            squares.close();
        }).start();
 
        System.out.println("Reached printer");
        printer(squares);
    }
}