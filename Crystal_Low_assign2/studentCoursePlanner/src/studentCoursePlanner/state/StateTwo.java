package studentCoursePlanner.state;

public class StateTwo implements State {
	private CoursePlannerStateI context;
	public StateTwo(CoursePlannerStateI stateInfo) {
		context = stateInfo;
	}


	@Override
	public void s1() {
		context.setState(context.getState1());
	}

	@Override
	public void s2() {
		context.setState(context.getState2());
	}

	@Override
	public void s3() {
		context.setState(context.getState3());

	}

	@Override
	public void s4() {
		context.setState(context.getState4());
	}

	@Override
	public void s5() {
		context.setState(context.getState5());
	}

	@Override
	public void noGrad(){
		context.setState(context.getNoGrad());
	}
}