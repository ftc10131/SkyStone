package skys.opMode.auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import skys.robot.FoundationGrabber;
@Autonomous(name="RedFoundationAuton", group="Foundation")
@Disabled
public class RFoundationAuton extends FoundationAuton {
    @Override
    public void runOpMode(){
        isRed = true;
        super.runOpMode();
    }

    @Override
    public void runMe(){
        isRed = true;
        super.runMe();
    }
}
