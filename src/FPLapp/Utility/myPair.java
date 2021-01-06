package FPLapp.Utility;

public class myPair<T1, T2> 
{
  T1 first; 
  T2 second;
  
  public myPair() {}
  
  public void setValue(T1 a, T2 b)
  {
    this.first = a;
    this.second = b;
  }
  
  public myPair<T1, T2> getValue()
  {
    return this;
  }
  
}