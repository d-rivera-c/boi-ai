package com.nizite.boi_ai.problems;

import java.util.ArrayList;
import java.util.List;

import com.nizite.boi_ai.representations.Representation;
import com.nizite.boi_ai.utils.Parser;

public abstract class Problem {
	/**
	 * Basic representation to be used.
	 * Add problem definition, define by initial space.
	 * TODO: check that problem put in rep works well
	 */
	protected Representation _representation;
	
	/**
	 * Hardcoded soft constraints. Only a list of human readable string
	 */
	protected List<String> _soft;
	
	/**
	 * Hardcoded hard constraints. Only a list of human readable string
	 */
	protected List<String> _hard;
	
	/**
	 * soft constraints to be enforced, picked by config
	 * follows indexes of _soft
	 */
	protected int[] _implementedSoft;
	
	/**
	 * hard constraints to be enforced, picked by config
	 * follows indexes of _hard
	 */
	protected int[] _implementedHard;
	
	/**
	 * Only text of what the objective function should do
	 * implementation is tied to the representation
	 */
	protected String _objectiveFunction;

	/**
	 * Sets what representation is going to be used and how many soft & hard constraints
	 * to be aware of. Mainly to be passed to Representation instance
	 * @param rep
	 * @param soft
	 * @param hard
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public void config(String rep, String soft, String hard) 
			throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		this.setSoftConstraints();
		this.setHardConstraints();
		this.setObjectiveFunction();
		this.setImplementedSoft(Parser.stringToIntArray(soft));
		this.setImplementedHard(Parser.stringToIntArray(hard));
		
		this.setRepresentation(rep);
	};	
	
	/**
	 * Called by core. Meant to receive a String read from a config file and parse it
	 * in its important parts (unique to each problem)
	 * Then calls setup to set the problem and representation instance
	 * Saves in the unique variables all info needed.
	 * Also instantiates Representation with things set up in config and picks softs and hards
	 * Saves all in variables
	 * @param config
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	public abstract void setup(String setup) throws NumberFormatException, Exception;
	
	public Representation getRepresentation() {
		return this._representation;
	};

	/**
	 * 
	 * @return
	 */
	public List<String> getSoftConstraints() {
		return this._soft;
	};
	
	/**
	 * 
	 * @return
	 */
	public List<String> getHardConstraints() {
		return this._hard;
	};
	
	public int[] getImplementedSoft() {
		return this._implementedSoft;
	}
	
	public int[] getImplementedHard() {
		return this._implementedHard;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<String> getImplementedSoftAsList() {
		ArrayList<String> implemented = new ArrayList<String>();
		for(int imp : this._implementedSoft) {
			implemented.add(this._soft.get(imp));
		}
		return implemented;
	};
	
	/**
	 * 
	 * @return
	 */
	public List<String> getImplementedHardAsList() {
		ArrayList<String> implemented = new ArrayList<String>();
		for(int imp : this._implementedHard) {
			implemented.add(this._soft.get(imp));
		}
		return implemented;		
	};
	
	/**
	 * 
	 * @return
	 */
	public String getObjectiveFunction() {
		return this._objectiveFunction;
	};
	
	/**
	 * TODO :doxs
	 * Note: doesn't make sure class actually exists
	 * @param rep
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	protected void setRepresentation(String rep) 
			throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Class<?> c = Class.forName(rep);
		this._representation = (Representation) c.newInstance();
	};
	
	protected abstract void setProblem(String problem) throws Exception;
	
	/**
	 * TODO: docs
	 */
	protected void setSoftConstraints() {
		this._soft = new ArrayList<String>();
	};
	
	/**
	 * TODO: docs
	 */
	protected void setHardConstraints() {
		this._hard = new ArrayList<String>();
	};
	
	/**
	 * TODO: docs
	 * @param soft
	 */
	//TODO check that all index in implementer are in soft
	protected void setImplementedSoft(int[] soft) {
		this._implementedSoft = soft;
	};
	
	/**
	 * 
	 * @param hard
	 */
	//TODO check that all index in implemented are in hard
	protected void setImplementedHard(int[] hard) {
		this._implementedHard = hard;
	};
	
	/**
	 * 
	 * @param objective
	 */
	protected void setObjectiveFunction() {
		this._objectiveFunction = "";
	};

}
