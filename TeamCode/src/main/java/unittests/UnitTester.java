package unittests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import java.util.ArrayList;
import java.util.TreeMap;

import global.Common;
import teleutil.button.Button;
import teleutil.button.ButtonEventType;

import static global.General.*;

@TeleOp(name = "UnitTester", group = "UnitTests")
public class UnitTester extends OpMode implements Common {
    public static ArrayList<UnitTest> allUnitTests = new ArrayList<>();
    public int testNum = 0;

    @Override
    public void init() {
        reference(this);
        for (UnitTest t: allUnitTests) {
            t.init();
        }
//        Reflections reflections = new Reflections("my.project");
//        Set<Class<? extends SomeType>> subTypes = reflections.getSubTypesOf(SomeType.class);
        LoggerTest logger = new LoggerTest();
        FaultTest faultTest = new FaultTest();
        CommonTest commonTest = new CommonTest();
        CoordinatePlaneTest coordinatePlaneTest = new CoordinatePlaneTest();
        ThreadTest threadTest = new ThreadTest();
        RobotFunctionsTest robotFunctionsTest = new RobotFunctionsTest();
        GamepadTest gamepadTest = new GamepadTest();
        DriveTest driveTest = new DriveTest();
        testAll();
    }

    @Override
    public void start() {
        for (UnitTest t : allUnitTests) {
            t.start();
        }
    }

    @Override
    public void loop() {
        if(getCurrentTest().active()){
            log.display("Testing " + getCurrentTestName());
            getCurrentTest().loop();
        }else{
            nextTest();
        }
        if(gamepad1.x){
            nextTest();
        }
//        gph1.link(Button.X, ButtonEventType.ON_PRESS, args -> nextTest());
        update(true);
    }

    @Override
    public void stop(){
        for (UnitTest t: allUnitTests) {
            t.stop();
        }
        end();
    }
    private String getCurrentTestName(){
        return getCurrentTest().getClass().getSimpleName();
    }
    private UnitTest getCurrentTest(){
        return allUnitTests.get(testNum);
    }
    private void nextTest(){
        testNum++;
        if(testNum >= allUnitTests.size()){
            requestOpModeStop();
        }
    }

    private void testAll(){
        for (UnitTest t: allUnitTests) {
            t.test();
        }
    }
}
