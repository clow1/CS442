package studentCoursePlanner.state;
import java.util.Set;
import studentCoursePlanner.courses.Course;
import studentCoursePlanner.util.FileProcessor;

public class CoursePlannerStateI implements State {
	private State currState;
	private int changes;

	private State state1;
	private State state2;
	private State state3;
	private State state4;
	private State state5;
	private State noGrad;

	public CoursePlannerStateI() {
		
		state1 = new StateOne(this);
		state2 = new StateTwo(this);
		state3 = new StateThree(this);
		state4 = new StateFour(this);

		currState = state1;
		changes = 0;
	}
	

	@Override
	public void s1() {
		currState.s1();
		changes++;
	}

	@Override
	public void s2() {
		currState.s2();
		changes++;
	}

	@Override
	public void s3() {
		currState.s3();
		changes++;
	}

	@Override
	public void s4() {
		currState.s4();
		changes++;
	}

	@Override
	public void s5() {
		currState.s5();
		changes++;
	}

	@Override
	public void noGrad() {
		currState.noGrad();
	}

	public void setState(State s) {
		this.currState = s;
	}

	public State getState() {
		return currState;
	}
	
	public void setState1(State s) {
		this.state1 = s;
	}
	public State getState1() {
		return state1;
	}

	public void setState2(State s) {
		this.state2 = s;
	}

	public State getState2() {
		return state2;
	}

	public void setState3(State s) {
		this.state3 = s;
	}

	public State getState3() {
		return state3;
	}

	public void setState4(State s) {
		this.state4 = s;
	}

	public State getState4() {
		return state4;
	}

	public void setState5(State s) {
		this.state5 = s;
	}

	public State getState5() {
		return state5;
	}

	public void setNoGrad(State s) {
		this.noGrad = s;
	}

	public State getNoGrad() {
		//System.out.println("Cannot graduate.");
		return noGrad;
	}

	public int getChanges() {
		return changes;
	}
}