package pl.ug.SateliteApp.SateliteApp.service;

import org.hipparchus.geometry.euclidean.threed.Vector3D;
import org.orekit.data.DataProvider;
import org.orekit.data.DataProvidersManager;
import org.orekit.data.DirectoryCrawler;
import org.orekit.errors.OrekitException;
import org.orekit.frames.FramesFactory;
import org.orekit.orbits.CartesianOrbit;
import org.orekit.propagation.SpacecraftState;
import org.orekit.propagation.analytical.tle.TLE;
import org.orekit.propagation.analytical.tle.TLEPropagator;
import org.orekit.time.AbsoluteDate;
import org.orekit.time.TimeScale;
import org.orekit.time.TimeScalesFactory;
import org.orekit.utils.PVCoordinates;

import java.io.File;
import java.util.Calendar;
import java.util.TimeZone;

public class TLEcalculator {

    public TLEcalculator() {
    }

    private void calculateLocation(String objTLE1, String objTLE2) {

        TLE tle = new TLE(objTLE1, objTLE2);

        AbsoluteDate now = new AbsoluteDate(Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTime(), TimeScalesFactory.getUTC());
        double ageOfTle = now.durationFrom(tle.getDate());
//        logger.info("Age of TLE (s): " + ageOfTle + "; (h): " + ageOfTle / (60 * 60) + " (d): " + ageOfTle / (24 * 60 * 60));

        /* now i use TLE propagator in this way */
        TLEPropagator tlePropagator = TLEPropagator.selectExtrapolator(tle);
//        tlePropagator.setMasterMode(1.0, new TutorialStepHandler());
      tlePropagator.propagate(now,now.shiftedBy(3600));
    }

    private void calculateLL(String line1, String line2) {
        final TLE tle = new TLE(line1, line2);
        final TLEPropagator propagator = TLEPropagator.selectExtrapolator(tle);
    }


}

