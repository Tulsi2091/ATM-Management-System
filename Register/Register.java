package Register;                                         
import java.util.*;
//This class is made to set values of username,pin and balance 
class Details
{
    String username;
    String PIN;
    double balance;
    Details()                                             //Intializing details 
    {
        username = "";
        PIN = "";
        balance = 0.0;
    }
    Details(String username,String PIN,double balance)   //This will store the values of data entered by user
    {
        this();
        this.username = username;
        this.PIN = PIN;
        this.balance = balance;
    }
	void changeUsername(String username)                //for changing username
	{
		this.username = username;
	}
	void changePIN(String PIN)                         //for changing pin
	{
		this.PIN = PIN;
	}
}
class Register
{
    Details[] obj = new Details[50];
    static int c = 0;
    void setdetails()                                 //method to get username and pin from user to register
    {
        boolean flag = true;
        Scanner sc = new Scanner(System.in);
        String name,pin;
        do                                           //this do while loop will take the proper username from the user
        {
            System.out.print("Enter Username :   ");
            name = sc.nextLine().trim();
            if(isValidName(name))                    //checking if username entered by user is valid or not
                flag = false;
			else
				System.out.println("Username already exists. Please enter a unique username.");
        }while(flag);
        flag = true;
        do                                          //this do whie loop will take the proper pin number from the user
        {
            System.out.print("Enter PIN :   ");
            pin = sc.nextLine();
            if(isValidPIN(pin))                     //checking if pin entered by user is valid or not
                flag = false;
			else
				System.out.print("Invalid PIN. Please re-");
        }while(flag);
        obj[c] = new Details(name,pin,0.0);         //if above information is proper then user can register successfully
        c++;
    }
    boolean isValidName(String name)                //method for checking valid username
    {
        int i;
        for(i=0;i<c;i++)
        {
            if(obj[i].username.equalsIgnoreCase(name))//this will check if name entered by user is already registerd or not
                return false;
        }
        return true;
    }
    boolean isValidPIN(String p)                     //method for checking valid pin
    {
        int i;
        if(p.length()!=4)                           //if pin number entered by user is greater than or less than 4 number then it will return false value
        {
            return false;
        }
        for(i=0;i<p.length();i++)                    //to check that input given by user is in number or character
        {
            if(Character.isDigit(p.charAt(i))==false)//if input given by user is in character then it will return false value
                return false;
        }
        return true; 
    }
}