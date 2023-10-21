public class StateAndReward {

	
	

	/* State discretization function for the full hover controller */

		
		/* State discretization function for the angle controller */
	public static String getStateHover(double angle, double vx, double vy) {
	    int angleState = discretize(angle, 11, -Math.PI, Math.PI);  // 10 states for angle
	    int vxState = discretize(vx, 5, -1, 1);  // 5 states for vx
	    int vyState = discretize(vy, 5, -1, 1);  // 5 states for vy	 
	    String state = String.valueOf(angleState) + ":" + String.valueOf(vxState) + ":" + String.valueOf(vyState);
	
	    return state;
	}
	public static double getRewardHover(double angle, double vx, double vy) {
	    double positionPenalty = -Math.abs(vy);  
	    double anglePenalty = getRewardAngle(angle,vx,vy);
	    if (angle> 0 && angle<0.1) {
	    	anglePenalty += 6;
		    if (angle> 0 && angle<0.05) {
		    	anglePenalty +=7;
		    	if (angle> 0 && angle<0.01) {
		    		anglePenalty +=9;
		    	
				    if (Math.abs(vx)> 0 && Math.abs(vx)<0.2 ) {
				    	anglePenalty +=5;
				    
					    if (Math.abs(vx)> 0 && Math.abs(vx)<0.1 ) {
					    	anglePenalty +=5;
					    }
					    if (Math.abs(vy)> 0 && Math.abs(vy)<1) {
					    	positionPenalty +=6;
					    
						    if (Math.abs(vy)> 0 && Math.abs(vy)<0.5) {
						    	positionPenalty +=10;
					   
							    if (Math.abs(vy)> 0 && Math.abs(vy)<0.1) {
							    	positionPenalty +=12;
							    }
						    }
				    }
				    }
		    	}
		    }
	    }
	   
	        return positionPenalty + anglePenalty;    
	}
	public static String getStateAngle(double angle, double vx, double vy) {
	    int angleState = discretize(angle, 10, -1, 1);
	    
	    return "" + angleState;
	}
	public static double getRewardAngle(double angle, double vx, double vy) {
	    double anglePenalty =  Math.abs(vx)+ Math.abs(angle);  
	    
	    return -anglePenalty;  
	}







	// ///////////////////////////////////////////////////////////
	// discretize() performs a uniform discretization of the
	// value parameter.
	// It returns an integer between 0 and nrValues-1.
	// The min and max parameters are used to specify the interval
	// for the discretization.
	// If the value is lower than min, 0 is returned
	// If the value is higher than min, nrValues-1 is returned
	// otherwise a value between 1 and nrValues-2 is returned.
	//
	// Use discretize2() if you want a discretization method that does
	// not handle values lower than min and higher than max.
	// ///////////////////////////////////////////////////////////
	public static int discretize(double value, int nrValues, double min,
			double max) {
		if (nrValues < 2) {
			return 0;
		}

		double diff = max - min;

		if (value < min) {
			return 0;
		}
		if (value > max) {
			return nrValues - 1;
		}

		double tempValue = value - min;
		double ratio = tempValue / diff;

		return (int) (ratio * (nrValues - 2)) + 1;
	}

	// ///////////////////////////////////////////////////////////
	// discretize2() performs a uniform discretization of the
	// value parameter.
	// It returns an integer between 0 and nrValues-1.
	// The min and max parameters are used to specify the interval
	// for the discretization.
	// If the value is lower than min, 0 is returned
	// If the value is higher than min, nrValues-1 is returned
	// otherwise a value between 0 and nrValues-1 is returned.
	// ///////////////////////////////////////////////////////////
	public static int discretize2(double value, int nrValues, double min,
			double max) {
		double diff = max - min;

		if (value < min) {
			return 0;
		}
		if (value > max) {
			return nrValues - 1;
		}

		double tempValue = value - min;
		double ratio = tempValue / diff;

		return (int) (ratio * nrValues);
	}

}
