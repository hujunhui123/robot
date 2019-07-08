package hust.plane.web.controller.vo;

/**
 * Created by hp on 2019/6/18.
 */
public class RobotStatusVo {
    private String location;
    private String dAngle;
    private String speed;
    private String tempreture;
    private String batteryVoltage;
    private String chargeVoltage;
    private String chargeStatus;
    private String workedTime;
    private String remainBattery;
    private String remainTime;
    private String cemaraLift;
    private String WorkedDis;
    private String controlMode;
    private String led;

    @Override
    public String toString() {
        return "RobotStatusVo{" +
                "location='" + location + '\'' +
                ", dAngle='" + dAngle + '\'' +
                ", speed='" + speed + '\'' +
                ", tempreture='" + tempreture + '\'' +
                ", batteryVoltage='" + batteryVoltage + '\'' +
                ", chargeVoltage='" + chargeVoltage + '\'' +
                ", chargeStatus='" + chargeStatus + '\'' +
                ", workedTime='" + workedTime + '\'' +
                ", remainBattery='" + remainBattery + '\'' +
                ", remainTime='" + remainTime + '\'' +
                ", cemaraLift='" + cemaraLift + '\'' +
                ", WorkedDis='" + WorkedDis + '\'' +
                ", controlMode='" + controlMode + '\'' +
                ", led='" + led + '\'' +
                '}';
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getdAngle() {
        return dAngle;
    }

    public void setdAngle(String dAngle) {
        this.dAngle = dAngle;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getTempreture() {
        return tempreture;
    }

    public void setTempreture(String tempreture) {
        this.tempreture = tempreture;
    }

    public String getBatteryVoltage() {
        return batteryVoltage;
    }

    public void setBatteryVoltage(String batteryVoltage) {
        this.batteryVoltage = batteryVoltage;
    }

    public String getChargeVoltage() {
        return chargeVoltage;
    }

    public void setChargeVoltage(String chargeVoltage) {
        this.chargeVoltage = chargeVoltage;
    }

    public String getChargeStatus() {
        return chargeStatus;
    }

    public void setChargeStatus(String chargeStatus) {
        this.chargeStatus = chargeStatus;
    }

    public String getWorkedTime() {
        return workedTime;
    }

    public void setWorkedTime(String workedTime) {
        this.workedTime = workedTime;
    }

    public String getRemainBattery() {
        return remainBattery;
    }

    public void setRemainBattery(String remainBattery) {
        this.remainBattery = remainBattery;
    }

    public String getRemainTime() {
        return remainTime;
    }

    public void setRemainTime(String remainTime) {
        this.remainTime = remainTime;
    }

    public String getCemaraLift() {
        return cemaraLift;
    }

    public void setCemaraLift(String cemaraLift) {
        this.cemaraLift = cemaraLift;
    }

    public String getWorkedDis() {
        return WorkedDis;
    }

    public void setWorkedDis(String workedDis) {
        WorkedDis = workedDis;
    }

    public String getControlMode() {
        return controlMode;
    }

    public void setControlMode(String controlMode) {
        this.controlMode = controlMode;
    }

    public String getLed() {
        return led;
    }

    public void setLed(String led) {
        this.led = led;
    }

}
