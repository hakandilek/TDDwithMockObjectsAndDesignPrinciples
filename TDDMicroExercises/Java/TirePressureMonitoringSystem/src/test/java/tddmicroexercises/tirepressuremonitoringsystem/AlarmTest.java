package tddmicroexercises.tirepressuremonitoringsystem;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

public class AlarmTest {

    Sensor sensor;
    Alarm alarm;

    @Before
    public void setup() {
	sensor = mock(Sensor.class);
	alarm = new Alarm(sensor);
    }

    @Test
    public void checkNominalPressure() throws Exception {
	for (double pressure = Alarm.LOW_PRESSURE_TRESHOLD; pressure <= Alarm.HIGH_PRESSURE_TRESHOLD; pressure++) {
	    when(sensor.popNextPressurePsiValue()).thenReturn(pressure);
	    alarm.check();
	    assertFalse(alarm.isAlarmOn());
	}
    }

    @Test
    public void checkPressureTooLow() throws Exception {
	when(sensor.popNextPressurePsiValue()).thenReturn(Alarm.LOW_PRESSURE_TRESHOLD - 1);
	alarm.check();
	assertTrue(alarm.isAlarmOn());
    }

    @Test
    public void checkPressureTooHigh() throws Exception {
	when(sensor.popNextPressurePsiValue()).thenReturn(Alarm.HIGH_PRESSURE_TRESHOLD + 1);
	alarm.check();
	assertTrue(alarm.isAlarmOn());
    }

    @Test
    public void alarmShouldBeOnOnceTriggeredHigh() throws Exception {
	when(sensor.popNextPressurePsiValue()).thenReturn(Alarm.HIGH_PRESSURE_TRESHOLD - 1);
	alarm.check();
	when(sensor.popNextPressurePsiValue()).thenReturn(Alarm.HIGH_PRESSURE_TRESHOLD + 1);
	alarm.check();
	when(sensor.popNextPressurePsiValue()).thenReturn(Alarm.HIGH_PRESSURE_TRESHOLD);
	alarm.check();

	assertTrue(alarm.isAlarmOn());
    }

    @Test
    public void alarmShouldBeOnOnceTriggeredLow() throws Exception {
	when(sensor.popNextPressurePsiValue()).thenReturn(Alarm.LOW_PRESSURE_TRESHOLD + 1);
	alarm.check();
	when(sensor.popNextPressurePsiValue()).thenReturn(Alarm.LOW_PRESSURE_TRESHOLD - 1);
	alarm.check();
	when(sensor.popNextPressurePsiValue()).thenReturn(Alarm.LOW_PRESSURE_TRESHOLD);
	alarm.check();

	assertTrue(alarm.isAlarmOn());
    }
}