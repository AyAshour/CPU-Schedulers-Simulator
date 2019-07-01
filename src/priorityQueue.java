import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;


public class priorityQueue {

	static ArrayList<process> q = new ArrayList<process>();
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
		int earliest_arrived=0;
		int size=q.size();
		int exec_order=1,wt=0,tt=0;
		double avg_wt=0.0,avg_tt=0.0;
		for(int i=0; i<size; i++)
		{
			
			//sort
			Collections.sort(q);
			//print();
			for(process  p : q)
			{
				//System.out.println(p.getName());
				if(p.getArrival()<=earliest_arrived)
				{
					wt=earliest_arrived-p.getArrival();
					p.setWaiting(wt);
					avg_wt+=wt;
					tt=wt+p.getBurst();
					p.setTurnaround(tt);
					avg_tt+=tt;
					earliest_arrived+=p.getBurst()+1;
					System.out.println("Exec: "+exec_order+") "+p.getName()+" arr: "+p.getArrival()+" burst: "+p.getBurst()+" waiting: "+p.getWaiting()+" turnaround: "+p.getTurnaround());
					exec_order++;
					q.remove(p);
					if(!q.isEmpty())
						q.get(q.size()-1).setPr(q.get(q.size()-1).getPr()-1);
					break;
				}
			}
			
		}
		avg_wt=avg_wt/size;
		avg_tt=avg_tt/size;
		System.out.println(avg_wt+" "+avg_tt);
	}
	public static void main(String args[])
	{
		priorityQueue pq = new priorityQueue();
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
