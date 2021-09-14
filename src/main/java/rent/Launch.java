package rent;

import org.junit.runner.JUnitCore;

public class Launch {

    public static void main(String args[]) {
//        JUnitCore.main(MFCLoadTest.class.getName());

        JUnitCore junit = new JUnitCore();
//        junit.addListener(new TextListener(System.out));
        junit.run(RentTest.class);
    }
}
