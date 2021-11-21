package unittests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import global.Common;
import util.condition.Expectation;
import util.condition.Magnitude;

import static global.General.*;

// WORKS!

public class FaultTest extends UnitTest {
    @Override
    public void loop() {
        fault.guess("Is fault working?", Expectation.INCONCEIVABLE, Magnitude.CATASTROPHIC, false);
    }
}
