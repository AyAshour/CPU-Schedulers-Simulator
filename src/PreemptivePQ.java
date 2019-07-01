import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import static java.util.Comparator.*;

public class PreemptivePQ {

    static ArrayList<process> q = new ArrayList<process>();
    static ArrayList<process> waiting=new ArrayList<process>();
    static ArrayList<process> done=new ArrayList<process>();

    static void print()//for avg waitingtime & avg turnaroundtime
    {
        for(process p:q)
        {
            System.out.print(p.getName()+" "+p.getArrival()+" "+p.getBurst()+" "+p.getPr()+" ** ");
        }
        System.out.println();
    }
    @SuppressWarnings("unchecked")
    void start()
    {
        int totalBurst=0;
        boolean enter=false;
        for(process p:q) {
            totalBurst += p.getBurst();
            p.setOldBurst(p.getBurst());
        }
        Collections.sort(q);
        process cur=q.get(0);
        for(int i=0;i<totalBurst; i++) {
            enter = false;
            for (int j = 0; j < q.size(); j++) {
                process p = q.get(j);
                if (p.getArrival() <= i) {
                    waiting.add(p);
                    q.remove(p);
                }
                waiting.sort(comparing(process::getPr));
                if (p.getPr() > waiting.get(0).getPr()) {
                    p = waiting.get(0);
                    waiting.remove(p);
                    // cur.setBurst(cur.getBurst() - 1);
                    // cur.setBurst(cur.getBurst() - p.getArrival());
                    System.out.println(cur.getName() + " is out and " + p.getName() + " is in at " + p.getArrival());
                    //q.get(q.size() - 1).setPr(q.get(q.size() - 1).getPr() - 1);  //for starvation
                    if (p.getBurst() > 0)
                        waiting.add(p);
                    else {
                        done.add(p);
                    }
                    for (process t : waiting) {
                        t.setWaiting(t.getWaiting() + 1);
                    }

                    enter = true;
                } else {
                    p.setBurst(p.getBurst() - 1);
                    j--;
                    for (process s : waiting) {
                        if (cur.getArrival() >= s.getArrival())
                            s.setPr(s.getPr() - 1);  //for starvation
                    }
                }

            }
        }
            /*if(!enter) {
                    for(process s:q)
                    {
                        if(cur.getArrival()>=s.getArrival())
                           s.setPr( s.getPr()-1);  //for starvation
                    }
                if (cur.getBurst() == 0) {
                    q.remove(cur);
                    done.add(cur);
                    // cur=q.get(0);
                }
                cur.setBurst(cur.getBurst() - 1);
                for (process t : waiting) {
                    t.setWaiting(t.getWaiting() + 1);
                }
            }
        }
        for(process p:waiting )
        {
            waiting.sort(comparing(process::getPr));
            done.add(p);
            System.out.println(p.getName()+" ");
            for(int k=1; k<waiting.size(); k++){
                waiting.get(k).setWaiting(waiting.get(k).getWaiting()+1);
            }
        }*/
        double avgWT=0.0,avgTT=0.0;
        int count=1;
        for(process p2:done)
        {
            int wt=p2.getWaiting();//-p2.getArrival();
            int tt=p2.getWaiting()+p2.getOldBurst();
            System.out.println("exec.order: "+count+") "+p2.getName()+" arr: "+p2.getArrival()+" bur: "+p2.getBurst()+" wait: "+wt+" turn: "+tt);
            avgWT+=wt;
            avgTT+=tt;
            count++;
        }
        avgWT/=count;
        avgTT/=count;
        System.out.println("Average waiting time: "+avgWT+"  ,Average turnaround time: "+avgTT);
    }
    public static void main(String args[])
    {
        PreemptivePQ pq = new PreemptivePQ();
        Scanner s=new Scanner(System.in);
        int n=s.nextInt();
        String pname;
        int parrival;
        int pburst;
		/*Random r ;
		int rand ;*/
        int ppriority;
        for(int i=0; i<n; i++)
        {
            pname=s.next();
            parrival=s.nextInt();
            pburst=s.nextInt();
            ppriority=s.nextInt();
		/*	r = new Random();
			rand = r.nextInt(5-1) + 1;*/

            process p=new process(pname,parrival,pburst,ppriority);

            q.add(p);
            //System.out.println(q.get(i).getName()+" "+q.get(i).getArrival()+" "+q.get(i).getBurst()+" "+q.get(i).getPr());
        }
        //print();
        pq.start();

    }
}
