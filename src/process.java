import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class process implements Comparable{
	
	String name;
	int waiting=0;
	int turnaround=0;
	int pr;
	int arrival;
	int burst;
	int quantum;
	int endTime=0;

	public int getEndTime() {
		return endTime;
	}

	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}



	public int getQuantum() {
		return quantum;
	}

	public void setQuantum(int quantum) {
		this.quantum = quantum;
	}


	public int getOldBurst() {
		return oldBurst;
	}

	public void setOldBurst(int oldBurst) {
		this.oldBurst = oldBurst;
	}

	int oldBurst;
public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getWaiting() {
		return waiting;
	}
	public void setWaiting(int waiting) {
		this.waiting = waiting;
	}
	public int getTurnaround() {
		return turnaround;
	}
	public void setTurnaround(int turnaround) {
		this.turnaround = turnaround;
	}
	public int getPr() {
		return pr;
	}
	public void setPr(int pr) {
		this.pr = pr;
	}
	public int getArrival() {
		return arrival;
	}
	public void setArrival(int arrival) {
		this.arrival = arrival;
	}
	public int getBurst() {
		return burst;
	}
	public void setBurst(int burst) {
		this.burst = burst;
	}
public process() {
	// TODO Auto-generated constructor stub
}
process(String n,int ar, int br, int p)
{
	name=n;
	arrival=ar;
	burst=br;
	pr=p;

}
	process(String n,int ar, int br, int q,int a)
	{
		name=n;
		arrival=ar;
		burst=br;
		quantum=q;

	}
	/*public int compareTo(Object obj) {
		process other = (process)obj;

		if(other.pr> pr)
			return -1;

		else if(other.pr== pr)
			return 0;

		else return 1;
	}*/
	public int compareTo(Object obj) {
		process other = (process)obj;

		if(other.arrival> arrival)
			return -1;

		else if(other.arrival== arrival)
			return 0;

		else return 1;
	}
}
