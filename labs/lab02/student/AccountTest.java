

/** A suite of print tests for Account.
 @author Zoe Plaxco
 */

public class AccountTest {

	public static void testWithdraw(){
		System.out.println("Making an account to test withdraw");
		Account account = new Account(1000);
		System.out.println(account.withdraw(500) == true);
		System.out.println(500 == account.balance);
		System.out.println(account.withdraw(9999) == false);
        System.out.println(500 == account.balance);
        System.out.println("All print statements should be true");
	}

	public static void testMerge(){
		System.out.println("Making two accounts to test merge");
		Account one = new Account(100);
		Account two = new Account(100);
		one.merge(two);
		System.out.println(0 == two.balance);
		System.out.println(200 == one.balance);
		System.out.println("Both print statements should be true");
	}

	public static void testParent(){
		System.out.println("Making two accounts to test parent accounts");
		Account parent = new Account(1000);
		Account child = new Account(100,parent);
		System.out.println(child.withdraw(50) == true);
		System.out.println(50 == child.balance);
		System.out.println(1000 == parent.balance);
        System.out.println(child.withdraw(55) == true);
        System.out.println(0 == child.balance);
     	System.out.println(995 == parent.balance);
        System.out.println("All print statements should be true");
	}


	public static void main(String[] args) {
		testWithdraw();
		testMerge();
		testParent();
	}
}