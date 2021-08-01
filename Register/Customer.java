package Register;
import java.util.*;
//This customer class is extending register class 
public class Customer extends Register                                     
{
	static Scanner sc = new Scanner(System.in);
    public void loginmenu()
    {
        System.out.println("\n------>>>>>>>>LOGIN MENU<<<<<<<<------\n");    //printing choices for atm management system
        Scanner sc=new Scanner(System.in);
        System.out.println("1.  Register");
        System.out.println("2.  Login");
        System.out.println("0.  Exit");
        System.out.print("Enter your choice :   ");
        int choice = 0;
        //exception handling
        try                                                                
        {
            choice = sc.nextInt();
        }
        //This wil prevent program from terminating
        catch(InputMismatchException e)
        {
            System.out.println("Enter number in choice");
            loginmenu();
        }
        switch(choice)
        {
            case 1:
				setdetails();                                      //calling setdetails method
				loginmenu();                                       //calling loginmenu
				break; 
            case 2:
				login();                                           //calling login method
				break;
            case 0:
				System.exit(0);                                    //this will exit from loginfram
            default:
				System.out.println("Invalid choice!!");            
				loginmenu();
				break;
        }
    }
    //This method will take username and pin for registered users only
    void login()
    {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter username :   ");                     //this will take username from registered user
        String name=sc.nextLine();
        int i;
        boolean b = false;
        
        for(i=0;i<c;i++)                                            //this for loop will check whether the data entered by regestered user is correct or not
        {
            if(obj[i].username.equalsIgnoreCase(name))              //if name entered by registered user is correct then this wil execute
            {
                b = true;
                System.out.print("Enter PIN :   ");
                String pin = sc.nextLine();
                if(obj[i].PIN.equals(pin))                          //this will check that pin entered by user is same as registered pin or not
                {
                    System.out.println("ACCESS GRANTED");           //if it is same as registered pin then it will grant the access
                    viewSelectionFrame(i);             
                }
                else                                                //and if it is not correct then this will be printed
                {
                    System.out.println("Invalid PIN.\nACCESS REJECTED");
                    loginmenu();
                }
                break;
            }
        }
        if(!b)                                                      //if name entered by user is not same as registered name then this will be printed
        {
			System.out.println("No such account registered with the name " + name + ".");
			loginmenu();
		}
    }
    //this method will provide such options like deposit,check balance,transfer,change name and change pin
    void viewSelectionFrame(int index)
    {
        System.out.println("\n----->>>>>>>SELECTION MENU<<<<<<<-----\n");
        Scanner sc=new Scanner(System.in);
        System.out.println("1.  Deposit\n2.  Withdraw\n3.  Check Balance\n4.  Transfer\n5.  Change username\n6.  Change PIN\n0.  Logout");
        System.out.print("Enter your choice :   ");
        int ch = 0;
        //exception handlng
		try                                                          //if choice entered by user is not a number 
		{
			ch=sc.nextInt();
		}
		catch(InputMismatchException e)                             //then this will be executed and prevent from terminating  
		{
			System.out.println("Enter number in choice");
            viewSelectionFrame(index);
		}
        switch(ch)
        {
            case 1:
				Deposit(index);                                    //calling Deposite method
                break;
            case 2:
				Withdraw(index);                                   //calling Withdraw method
                break;
            case 3:
				checkBalance(index);                              //calling checkbalance method
                break;
            case 4:
				transferAmount(index);                            //clling transfer amount method
                break;
			case 5:
				changeName(index);                                //calling changename method
				viewSelectionFrame(index);
				break;
			case 6:                                               //this is fo changing pin
				System.out.print("Enter the new PIN :   ");
				sc.nextLine();
				String pin = sc.nextLine();
				obj[index].changePIN(pin);
				System.out.println("PIN updated successfully.");
				viewSelectionFrame(index);
				break;
            case 0:
				loginmenu();                                      //ths will go back to login menu
                break;
            default:
				System.out.println("Enter valid choice.");
				viewSelectionFrame(index);
				break;
        }
    }
    //This method is for depositing the amount in the account
    void Deposit(int i)
    {
		boolean bool;
		double deposit = 0.0;                                            //intilizing value
		do{
		bool = false;
		System.out.print("Enter the amount to be deposited :   ");
		//exception handling
		try
		{
			deposit = sc.nextDouble();
		}
		catch(InputMismatchException e)                                  //if amount entered by user is not proper then this will be executed and prevent the program from terminating
		{
			System.out.println("Enter proper amount");
			bool = true;
		}
		}while(bool);
		
		if(deposit <= 0)                                                //if amount is less then 0 then ths will be printed
		{
			System.out.println("Please enter a proper amount value.");
			Deposit(i);
		}
		else                                                              //if amount is in proper form then this will continue
		{
			obj[i].balance+=deposit;                                     //this will deposite amount 
			System.out.println("Rs." + deposit + " successfully deposited.");
			System.out.println("Current balance :   " + obj[i].balance);   //showing current balance  
			viewSelectionFrame(i);                                      //going back to viewselectionframe 
		}
    }
    //this method is to withdraw amount from account
	void Withdraw(int i)
	{
		boolean bool;
		double withdraw = 0;
		do{
		bool = false;
		System.out.print("Enter the amount to be withdrawn :   ");
		//exception handling
		try
		{
			withdraw = sc.nextDouble();
		}
		catch(InputMismatchException e)                                //if amount entered by user is not proper then this will be executed and prevent the program from terminating
		{
			System.out.println("Enter proper amount");
			bool = true;
		}
		}while(bool);
		if(withdraw > obj[i].balance)                                 //if withdraw amount is greater than total amount present in the account then this will be printed
		{
			System.out.println("Not sufficient balance in your account");
			viewSelectionFrame(i);
		}
		else if(withdraw <= 0)                                         //if withdraw amount is less than 0 then this message will be printed
		{
			System.out.println("Please enter a proper amount value.");
			Withdraw(i);
		}
		else                                                            //if valid withdraw amount is entered then this will be continued
		{
			obj[i].balance-=withdraw;
			System.out.println("Rs." + withdraw + " successfully withdrawn."); 
			System.out.println("Current balance :   " + obj[i].balance);  //this will show latest balance
			viewSelectionFrame(i);
		}
	}
	//this method will display current balance 
	void checkBalance(int i)
	{
		System.out.println("Current balance :   " + obj[i].balance);
		viewSelectionFrame(i);
	}
	//this method is to transfer amount from one account to another
	void transferAmount(int i)
	{
		System.out.print("Enter the name of the account holder the amount is to be transferred :   ");
		sc.nextLine();
		String transferName = sc.nextLine();
		int j = check(transferName,i);
		if(j==c)                                           //if name entered by user is not registered ten this will be printed
		{
			System.out.println("Username doesn't exist.");
			viewSelectionFrame(i);
		}
		else
		{
			boolean bool;
			double transfer = 0.0;
			do{
			bool = false;
			System.out.print("Enter the amount to be tranferred :   ");
			try
			{
				transfer = sc.nextDouble();
			}
			catch(InputMismatchException e)
			{
				System.out.println("Enter proper amount");
				bool = true;
			}
			}while(bool);                                    
			if(obj[i].balance < transfer)                         //if balance is less then transfer amount then this will be printed
			{
				System.out.println("Insufficient balance");
				viewSelectionFrame(i);
			}
			else if(transfer <= 0)                               //if transfer amount is less than 0 then this will be printed
			{
				System.out.println("Please enter a proper amount value.");
				transferAmount(i);
			}
			else                                                 //if tranfer amount is valid then this will be continued
			{
				obj[i].balance-=transfer;
				obj[j].balance+=transfer;
				System.out.println("Rs." + transfer + " is debited from your account");
				System.out.println("Current Balance : Rs." + obj[i].balance); 
				viewSelectionFrame(i);
			}
		}
	}
	//to check whether trasfername is valid or not
	private int check(String transferName,int i)
	{
		int j;
		for(j = 0;j<c;j++)
		{
			if(i==j)
				continue;
			else
			{
				if(transferName.equalsIgnoreCase(obj[j].username))
					break;
			}
		}
		return j;
	}
	//this method is to change username
	void changeName(int index)
	{
				boolean bo = false;
				String name;
				do
				{
					System.out.print("Enter the new username :   ");
					name = sc.nextLine();
					for(int i=0;i<c;i++)
					{
						bo = false;
						if(index == i && obj[index].username.equalsIgnoreCase(name))   //if user enters same name as before then this wil be printed
						{
							System.out.println("Username same as before.");
							break;
						}
						if(obj[index].username.equalsIgnoreCase(name))                 //if user enters same name as other registered user's name then this will be printed
						{
							System.out.println("Username already exists.");
							System.out.print("Please re-");
							bo = true;
							break;
						}
					}
				}while(bo);                                                            //if user enters valid name to change then this will continue
				obj[index].changeUsername(name);
				System.out.println("Username updated successfully.");
	}
}