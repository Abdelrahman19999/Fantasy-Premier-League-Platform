package FPLapp.Utility;

public class myPair<T1, T2> 
{
  T1 p1; 
  T2 p2;
  
  public myPair() {}
  
  public void setValue(T1 a, T2 b)
  {
    this.p1 = a;
    this.p2 = b;
  }
  
  myPair<T1, T2> getValue()
  {
    return this;
  }
  
}