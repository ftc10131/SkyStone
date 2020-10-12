package skys.robot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.ArrayList;

import skys.robot.sensors.Gyro;
import skys.robot.sensors.Vision;
import skys.util.ParamManager;

public class Robot extends Mechanism{

    public DriveTrain driveTrain;
    public Intake intake;
    public Placer placer;
    public FoundationGrabber foundationGrabber;
    public Gyro gyro;
    public ParamManager paramManager;
    public Vision vision;
    public CapstoneDumper capstoneDumper;

    public String paramFileName;

    HardwareMap hardwareMap;

    public ArrayList<Mechanism> mechanisms;

    public Robot(HardwareMap h){
        super("eponafull", h);

        hardwareMap = h;

        driveTrain = new DriveTrain("driveTrain", hardwareMap);
        intake = new Intake("intake", hardwareMap);
        placer = new Placer("placer", hardwareMap);
        foundationGrabber = new FoundationGrabber("foundationGrabber", hardwareMap);
        gyro = new Gyro("imu",hardwareMap);
        vision = new Vision("vision",hardwareMap);
        capstoneDumper = new CapstoneDumper("capstoneDumper",hardwareMap);
        paramManager = new ParamManager();

        mechanisms = new ArrayList<>();
        mechanisms.add(driveTrain);
        mechanisms.add(intake);
        mechanisms.add(placer);
        mechanisms.add(foundationGrabber);
        mechanisms.add(gyro);
        mechanisms.add(vision);
        mechanisms.add(capstoneDumper);

        paramFileName = mName("Params");
    }

    public void init(boolean usingVision){
        vision.using = usingVision;

        paramManager.loadFromFile(hardwareMap.appContext, paramFileName,hmp);

        for (int i=0; i<mechanisms.size(); i++ ){
     //   for(Mechanism m: mechanisms){
            mechanisms.get(i).init();
            for(String s : mechanisms.get(i).hmp.keySet()){
                mechanisms.get(i).hmp.keySet();
                hmp.put(s,mechanisms.get(i).hmp.get(s));
            }

        }



    }

    public void start(){
        for (int i=0; i<mechanisms.size(); i++ ){
            //   for(Mechanism m: mechanisms){
            mechanisms.get(i).start();
        }
    }

    public void stop(){
        for (int i=0; i<mechanisms.size(); i++ ){
            //   for(Mechanism m: mechanisms){
            mechanisms.get(i).stop();
        }
    }

    public void paramTelemetry(LinearOpMode om){
        for(Mechanism m: mechanisms){
            if(!m.hmp.isEmpty()){
                for(String s: m.hmp.keySet()){
                    om.telemetry.addData(s,m.hmp.get(s).getValue());
                }
            }
        }
    }

    /*public void stopIfStalled(){
        ploop.stopIfStalled();
    }*/
}