package algorithms.search;

import java.io.Serializable;

public class State<T extends Comparable<T>> implements Comparable<State<T>>, Serializable {
	//---------Variables---------//
	private T state;
	private int distance;
	private State<T> parentState;
	
	//----------Methods---------//
	//	Constructors:
	/**
	*@author Yaniv and Asaf
	*@return First constructor to give a state its state.
	 */
	public State(T state, State<T> parentState) {
		this.state = state;
		this.parentState = parentState; 
		this.distance = parentState.distance + 1;
	}
	
	public State(T state) {
		this.state = state;
		this.parentState = null; 
		this.distance = 0;
	}
	
	public State(State<T> stateToCopy){
		this.state = stateToCopy.getState();
		this.distance = stateToCopy.getDistance();
		this.parentState = (State<T>) stateToCopy.getParentState();
	}
	
	//	Getters & Setters:
	public T getState() {
		return state;
	}

	public void setState(T state) {
		this.state = state;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public State<T> getParentState(){
			return parentState;
		
	}

	public void setParentState(State<T> parentState) {
		this.parentState = parentState;
		this.distance = parentState.distance + 1;
	}
	/**
	*@author Yaniv and Asaf
	*@return An overrided method that compares a state to another state.
	 */
	@Override
	public int compareTo(State<T> o) {
		return this.getState().compareTo(o.getState());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		return result;
	}

	
	/**
	*@author Yaniv and Asaf
	*@return Check if to objects are equals.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		State other = (State) obj;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		return true;
	}
	/**
	*@author Yaniv and asaf
	*@return Takes the state and change it to a string.
	 */
	@Override
	public String toString() {
		if (parentState == null)
			return state + ", distance=" + distance + ", parent=" + parentState;
		else
			return state + ", distance=" + distance + ", parent=" + parentState.state;
	}	

	
}

