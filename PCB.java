
package mlq;

public class PCB   {
    
    public int PID;
    public int Priority;
    public int Arrive;
    public int CPUburst;
    public int Start;
    public int Termination;
    public int Turn;
    public int Wait;
    public int Response;
    public int CPUburst1;

    public PCB(int PID, int Priority, int Arrive, int CPUburst) {
        this.PID = PID;
        this.Priority = Priority;
        this.Arrive = Arrive;
        this.CPUburst = CPUburst;
        this.CPUburst1 = CPUburst;
        this.Start = -1;
        this.Termination = -1;
        this.Turn = -1;
        this.Wait = -1;
        this.Response = -1;
    }

    public int getPID() {
        return PID;
    }

    public void setPID(int PID) {
        this.PID = PID;
    }

    public int getPriority() {
        return Priority;
    }

    public void setPriority(int Priority) {
        this.Priority = Priority;
    }

    public int getArrive() {
        return Arrive;
    }

    public void setArrive(int Arrive) {
        this.Arrive = Arrive;
    }

    public int getCPUburst() {
        return CPUburst;
    }

    public void setCPUburst(int CPUburst) {
        this.CPUburst = CPUburst;
    }

    public int getStart() {
        return Start;
    }

    public void setStart(int Start) {
        this.Start = Start;
    }

    public int getTermination() {
        return Termination;
    }

    public void setTermination(int Termination) {
        this.Termination = Termination;
    }

    public int getTurn() {
        return Turn;
    }

    public void setTurn(int Turn) {
        this.Turn = Turn;
    }

    public int getWait() {
        return Wait;
    }

    public void setWait(int Wait) {
        this.Wait = Wait;
    }

    public int getResponse() {
        return Response;
    }

    public void setResponse(int Response) {
        this.Response = Response;
    }

    public int getCPUburst1() {
        return CPUburst1;
    }

    public void setCPUburst1(int CPUburst1) {
        this.CPUburst1 = CPUburst1;
    }
   
       
}
    

  

