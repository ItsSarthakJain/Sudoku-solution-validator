import java.util.Map;

/**
 * This class represents the Result type It has a flag to check if solution is valid or not and a
 * map of faults in the solution
 *
 * @author sarthak jain
 */
public class Result {

  private boolean isValid = true;
  private Map<String, String> faults ;

  /** default constructor */
  public Result() {}

  /**
   * Get isValid flag in the solution
   *
   * @return isValid
   */
  public boolean isValid() {
    return isValid;
  }

  /**
   * set the flag valid
   *
   * @param valid
   */
  public void setValid(boolean valid) {
    isValid = valid;
  }

  /**
   * Get the no of faults in the solution
   *
   * @return faults
   */
  public Map<String, String> getFaults() {
    return faults;
  }

  /**
   * set the no of faults
   *
   * @param faults
   */
  public void setFaults(Map<String, String> faults) {
    this.faults = faults;
  }
}
