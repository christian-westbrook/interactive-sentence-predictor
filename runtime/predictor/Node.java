

public class Node {
	
	private String val;
	private double prob;
	
	public Node(String val, double prob) {
		
		this.val = val;
		this.prob = prob;
	}
	
	public void setValues(String val, double prob) {
		
		this.val = val;
		this.prob = prob;
		
	}

	public String getVal() {
		
		return val;
		
	}
	
	public double getProb() {
		
		return prob;
		
	}

}
