
package mlq;
import java.util.ArrayList;
import java.util.*;
import java.lang.System;
import java.io.*;

public class MLQ {

    static ArrayList<PCB> Q2 = new ArrayList<PCB>();
    static ArrayList<PCB> Q1 = new ArrayList<PCB>();
    static ArrayList<PCB> processes = new ArrayList<PCB>();
    static ArrayList<PCB> order = new ArrayList<PCB>();

  
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
while(true){
        System.out.println("choose a number: \n "
                + "1- Enter process' information \n "
                + "2- Report detailed information about each process and different scheduling criteria \n "
                + "3- Exit the program\n ");

       
        


     int choice = input.nextInt();
        switch (choice) {

            case 1:
                System.out.println("enter the number of processes");
                int num = input.nextInt();

                
                for (int i = 0; i < num; i++) {

                    System.out.println("enter the priority of process " + i + " (either 1 or 2)");
                    int priority = input.nextInt();

                    System.out.println("enter the arrival time of process  " + i);
                    int arrive = input.nextInt();

                    System.out.println("enter the CPU burst of process " + i);
                    int burst = input.nextInt();

                    int ID = i + 1;
                    PCB p = new PCB(ID, priority, arrive, burst);
                    processes.add(p);
                    if (priority == 1) {
                        Q1.add( p);
                    } else {
                        Q2.add(p);
                    }

                  
                }
            break;
            
            case 2:
                try{
                System.out.println("in case 2:");
                MLQ();
                PrintInfo();
                }
                catch(Exception e){}
         
                break;

            case 3:
                System.exit(0);
                break;

        }

        } 
    }
    
    
   

    public static void PrintInfo() throws IOException {
        
        FileWriter writer = null;
        PrintWriter printer = null;
        
     try {
        writer = new FileWriter("report.txt");
        printer = new PrintWriter(writer);

        double avgTurnaroundTime = 0;
        double avgWaitingTime = 0;
        double avgResponseTime = 0;
        
        System.out.println("Scheduling order:");
        printer.println("Scheduling order:");
        for (int i = 0; i < order.size(); i++) {
            System.out.print(order.get(i).PID + " | ");
            printer.print(order.get(i).PID + " | ");
        }
        
        System.out.println("\nprocess' information:");
         printer.println("\nprocess' information:");
         
        for (int i = 0; i < processes.size(); i++) {
            System.out.println("Process ID: " + processes.get(i).PID);
            printer.println("Process ID: " + processes.get(i).PID);
            
            System.out.println("Priority: " + processes.get(i).Priority);
            printer.println("Priority: " + processes.get(i).Priority);
            
            System.out.println("Arrival Time: " + processes.get(i).Arrive);
            printer.println("Arrival Time: " + processes.get(i).Arrive);
            
            System.out.println("CPU Burst: " + processes.get(i).CPUburst1);
            printer.println("CPU Burst: " + processes.get(i).CPUburst1);
            
            System.out.println("Start Time: " + processes.get(i).Start);
            printer.println("Start Time: " + processes.get(i).Start);
            
            System.out.println("Termination Time: " + processes.get(i).Termination);
            printer.println("Termination Time: " + processes.get(i).Termination);
            
            System.out.println("Turnaround Time: " + processes.get(i).Turn);
            printer.println("Turnaround Time: " + processes.get(i).Turn);
            
            System.out.println("Waiting Time: " + processes.get(i).Wait);
            printer.println("Waiting Time: " + processes.get(i).Wait);
            
            System.out.println("Response Time: " + processes.get(i).Response);
            printer.println("Response Time: " + processes.get(i).Response);
            
            
            System.out.println("----------------------");
             printer.println("----------------------");
             
            avgTurnaroundTime += processes.get(i).Turn;
            avgWaitingTime += processes.get(i).Wait;
            avgResponseTime += processes.get(i).Response;
        }
        System.out.println("Average Turnaround Time: " + avgTurnaroundTime / processes.size());
        printer.println("Average Turnaround Time: " + avgTurnaroundTime / processes.size());
        System.out.println("Average Waiting Time: " + avgWaitingTime / processes.size());
        printer.println("Average Waiting Time: " + avgWaitingTime / processes.size());
        System.out.println("Average Response Time: " + avgResponseTime / processes.size());
        printer.println("Average Response Time: " + avgResponseTime / processes.size());
     }
     finally{
      
            printer.close();
            writer.close();
        
     }
    }

    static void MLQ() {
        int time = 0;
        if (!Q1.isEmpty()) {
           Collections.sort(Q1, Comparator.comparingInt(p -> p.Arrive));

        }
        while (!Q1.isEmpty() || !Q2.isEmpty()) {

            if (hasProcess(time, Q1)) {
                time = roundRobinScheduling(time);
            } else {
                if (hasProcess(time, Q2)) {
                    time = shortestJobFirstScheduling(time);
                }
            }
        }
        time++;
    }

    static int shortestJobFirstScheduling(int time) {
        int time2 = time;
        Collections.sort(Q2, new Comparator<PCB>() {
            @Override
            public int compare(PCB p1, PCB p2) {
                
                if (time >= p1.Arrive && time >= p2.Arrive) {
                    return Integer.compare(p1.CPUburst, p2.CPUburst);
                } else {
                    
                    if (p1.Arrive != p2.Arrive) {
                        return Integer.compare(p1.Arrive, p2.Arrive);
                    } else {
                       
                        return Integer.compare(p1.CPUburst, p2.CPUburst);

                    }
                }
            }
        });

        PCB p = Q2.remove(0);
        
        if (p.Start == -1) {
            p.Start = time2;
            p.Response = time2 - p.Arrive;
        }
        order.add(p);
        p.CPUburst--;
        while (p.CPUburst > 0) {
            time2++;
            if (hasProcess(time, Q1)) {
                Q2.add(p);
                return time2;
            }
            p.CPUburst--;
        }
        time2++;
        p.Termination = time2;
        p.Turn = p.Termination - p.Arrive;
        p.Wait = p.Turn - p.CPUburst1;
        return time2;
    }

    static boolean hasProcess(int time, ArrayList<PCB> Q) {
        for (int i = 0; i < Q.size(); i++) {
            if (Q.get(i).Arrive <= time) {
                return true;
            }
        }
        return false;
    }

    static int roundRobinScheduling(int time) {
        int time2 = time;

        while (!Q1.isEmpty()) {
            if (Q1.get(0).Arrive > time2) {
                return time2;
            }
            PCB p = Q1.remove(0);
            if (p.Start == -1) {
                p.Start = time2;
                p.Response = time2 - p.Arrive;
            }

            int remainingBurst = p.CPUburst;

            if (remainingBurst <= 3) {  // Process completes within the quantum
                time2 += remainingBurst;
                p.Termination = time2;
                p.Turn = p.Termination - p.Arrive;
                p.Wait = p.Turn - p.CPUburst1;
            } else {  // Process doesn't complete within the quantum
                time2 += 3;
                p.CPUburst -= 3;
                int indexToInsert = 0;
                for (int i = 0; i < Q1.size(); i++) {
                    if (Q1.get(i).Arrive < time2) {
                        indexToInsert = i + 1;
                    } else {
                        break;
                    }
                }
                Q1.add(indexToInsert,p);  // Re-add the process to the end of the queue
            }

            order.add(p);  // Record the execution order

        }//End while
        return time2;
    }

}
