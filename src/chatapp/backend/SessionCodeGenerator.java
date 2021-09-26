package chatapp.backend;

import java.util.*;

public class SessionCodeGenerator {

	String passcode="";
	 public String code()
	 {
	    String characterstring1="abcdefghijkmnopqrstuvwxyz"+"ABCDEFGHIJKMNOPQRSTUVWXYZ"+"1234567890";
	    //String s="*&%$#@";
	    int index1=0;
	    Random obj=new Random();
	    for(int j=0;j<14;j++)
	    {
	            if(j==4 || j==9)
	             passcode=passcode+"_";
	            else
	            {
	            index1=obj.nextInt(characterstring1.length());
	            passcode=passcode+String.valueOf(characterstring1.charAt(index1));
	            }
	   }

	    return passcode;
	 }    
}
