package tddmicroexercises.tirepressuremonitoringsystem;

public class Alarm {
  public static final double LOW_PRESSURE_TRESHOLD = 17;
  public static final double HIGH_PRESSURE_TRESHOLD = 21;

  private final Sensor sensor;

  private boolean alarmOn = false;

  public Alarm(Sensor sensor) {
    this.sensor = sensor;
  }

  public Alarm() {
    this(new Sensor());
  }

  public Sensor getSensor() {
    return sensor;
  }

  public void check() {
    double psiPressureValue = sensor.popNextPressurePsiValue();

    if (psiPressureValue < LOW_PRESSURE_TRESHOLD || HIGH_PRESSURE_TRESHOLD < psiPressureValue) {
      alarmOn = true;
    }
  }

  public boolean isAlarmOn() {
    return alarmOn;
  }
}
