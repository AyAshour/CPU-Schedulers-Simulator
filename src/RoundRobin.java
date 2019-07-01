import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

public class RoundRobin {
    static ArrayList<process> q = new ArrayList<process>();
    static ArrayList<process> done = new ArrayList<process>();


    int time=0;
    void start()
    {
        int quantum=q.get(0).getQuantum();
        Collections.sort(q);
        Iterator<process> itr =q.iterator();
        int i=0;
        while(itr.hasNext())
        {

            process p = q.get(i);
            if(p.getBurst()<=quantum)
            {
                q.remove(p);
                System.out.print(" "+p.getName()+" ");
                p.setWaiting(p.getWaiting()+(time-p.getEndTime()));
                time+=p.getBurst();
                p.setEndTime(time);
                done.add(p);
            }
            else
            {
                System.out.print(" "+p.getName()+" ");
                q.remove(p);
                p.setBurst(p.getBurst()-quantum);
                p.setWaiting(p.getWaiting()+(time-p.getEndTime()));
                time+=quantum;
                p.setEndTime(time);
                q.add(p);
            }

        }
        System.out.println();
        double avgWT=0.0,avgTT=0.0;
        int count=1;
        for(process p2:done)
        {
            int wt=p2.getWaiting();//-p2.getArrival();
            int tt=p2.getEndTime();
            System.out.println("exec.order: "+count+") "+p2.getName()+" arr: "+p2.getArrival()+" bur: "+p2.getBurst()+" wait: "+wt+" turn: "+tt);
            avgWT+=wt;
            avgTT+=tt;
            count++;
        }
        avgWT/=count;
        avgTT/=count;
        System.out.println("Average waiting time: "+avgWT+"  ,Average turnaround time: "+avgTT);
    }
    public static void main(String args[]) {
        RoundRobin pq = new RoundRobin();
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        String pname;
        int parrival;
        int pburst;
        int pQuantum=s.nextInt();
        for (int i = 0; i < n; i++) {
            pname = s.next();
            parrival = s.nextInt();
            pburst = s.nextInt();
            process p = new process(pname, parrival, pburst, pQuantum,0);
            p.setOldBurst(p.getBurst());
            q.add(p);
            //System.out.println(q.get(i).getName()+" "+q.get(i).getArrival()+" "+q.get(i).getBurst()+" "+q.get(i).getPr());
        }
        //print();
        pq.start();
    }
    /*
    5
10
p1 1 10
p2 1 29
p3 1 3
p4 1 7
p5 1 12
     */
}
