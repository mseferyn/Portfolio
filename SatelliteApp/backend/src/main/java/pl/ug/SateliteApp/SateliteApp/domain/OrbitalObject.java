package pl.ug.SateliteApp.SateliteApp.domain;

public class OrbitalObject {

    private float CCSDS_OMM_VERS;
    private String comment;
    private String centerName;
    private float period;
    private String rcsSize;
    private String creationDate;
    private String originator;
    private String objectName;
    private String objectId;
    private String referFrame;
    private String timeSystem;
    private String meanElemTheory;
    private String epoch;
    private float meanMotion;
    private float eccentricity;
    private float inclination;
    private float rateOfAscNode;
    private float argOfPericenter;
    private float meanAnomaly;
    private float ephermisType;
    private String classType;
    private String NORADCatId;
    private int elemSet;
    private int revAtEpoch;
    private float bstar;
    private float meanMotionDot;
    private float meanMotionDdot;
    private float semimajorAxis;
    private float apoapsis;
    private float periapsis;
    private String objectType;
    private String countryCode;
    private String launchDate;
    private String site;
    private String decayRate;
    private int file;
    private int gpId;
    private String tleLine0;
    private String tleLine1;
    private String tleLine2;
    private double[] coords;

    public OrbitalObject(String[] input) {
        this.CCSDS_OMM_VERS = Float.parseFloat(input[0]);
        this.comment = input[1];
        this.creationDate = input[2];
        this.originator = input[3];
        this.objectName = input[4];
        this.objectId = input[5];
        this.centerName = input[6];
        this.referFrame = input[7];
        this.timeSystem = input[8];
        this.meanElemTheory = input[9];
        this.epoch = input[10];
        this.meanMotion = Float.parseFloat(input[11]);
        this.eccentricity = Float.parseFloat(input[12]);
        this.inclination = Float.parseFloat(input[13]);
        this.inclination = Float.parseFloat(input[13]);
        this.rateOfAscNode = Float.parseFloat(input[14]);
        this.argOfPericenter = Float.parseFloat(input[15]);
        this.meanAnomaly = Float.parseFloat(input[16]);
        this.ephermisType = Float.parseFloat(input[17]);
        this.classType = input[18];
        this.NORADCatId = input[19];
        this.elemSet = Integer.parseInt(input[20]);
        this.revAtEpoch = Integer.parseInt(input[21]);
        this.bstar = Float.parseFloat(input[22]);
        this.meanMotionDot = Float.parseFloat(input[23]);
        this.meanMotionDdot = Float.parseFloat(input[24]);
        this.semimajorAxis = Float.parseFloat(input[25]);
        this.period = Float.parseFloat(input[26]);
        this.apoapsis = Float.parseFloat(input[27]);
        this.periapsis = Float.parseFloat(input[28]);
        this.objectType = input[29];
        this.rcsSize = input[30];
        this.countryCode = input[31];
        this.launchDate = input[32];
        this.site = input[33];
        this.decayRate = input[34];
        this.file = Integer.parseInt(input[35]);
        this.gpId = Integer.parseInt(input[36]);
        this.tleLine0 = input[37];
        this.tleLine1 = input[38];
        this.tleLine2 = input[39];
    }

    public OrbitalObject(float CCSDS_OMM_VERS, String comment, String creationDate, String originator, String objectName, String objectId, String centerName, String referFrame, String timeSystem, String meanElemTheory, String epoch, float meanMotion, float eccentricity, float inclination, float rateOfAscNode, float argOfPericenter, float meanAnomaly, float ephermisType, String classType, String NORADCatId, int elemSet, int revAtEpoch, float bstar, float meanMotionDot, float meanMotionDdot, float semimajorAxis, float period, float apoapsis, float periapsis, String objectType, String rcsSize, String countryCode, String launchDate, String site, String decayRate, int file, int gpId, String tleLine0, String tleLine1, String tleLine2) {
        this.CCSDS_OMM_VERS = CCSDS_OMM_VERS;
        this.comment = comment;
        this.creationDate = creationDate;
        this.originator = originator;
        this.objectName = objectName;
        this.objectId = objectId;
        this.centerName = centerName;
        this.referFrame = referFrame;
        this.timeSystem = timeSystem;
        this.meanElemTheory = meanElemTheory;
        this.epoch = epoch;
        this.meanMotion = meanMotion;
        this.eccentricity = eccentricity;
        this.inclination = inclination;
        this.rateOfAscNode = rateOfAscNode;
        this.argOfPericenter = argOfPericenter;
        this.meanAnomaly = meanAnomaly;
        this.ephermisType = ephermisType;
        this.classType = classType;
        this.NORADCatId = NORADCatId;
        this.elemSet = elemSet;
        this.revAtEpoch = revAtEpoch;
        this.bstar = bstar;
        this.meanMotionDot = meanMotionDot;
        this.meanMotionDdot = meanMotionDdot;
        this.semimajorAxis = semimajorAxis;
        this.period = period;
        this.apoapsis = apoapsis;
        this.periapsis = periapsis;
        this.objectType = objectType;
        this.rcsSize = rcsSize;
        this.countryCode = countryCode;
        this.launchDate = launchDate;
        this.site = site;
        this.decayRate = decayRate;
        this.file = file;
        this.gpId = gpId;
        this.tleLine0 = tleLine0;
        this.tleLine1 = tleLine1;
        this.tleLine2 = tleLine2;
    }

    public OrbitalObject(String name, String id, double[] coords) {
        this.objectName = name;
        this.NORADCatId = id;
        this.coords = coords;
    }

    public float getCcsdsOmmVers() {
        return CCSDS_OMM_VERS;
    }

    public void setCcsdsOmmVers(float CCSDS_OMM_VERS) {
        this.CCSDS_OMM_VERS = CCSDS_OMM_VERS;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getcreationDate() {
        return creationDate;
    }

    public void setcreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getOriginator() {
        return originator;
    }

    public void setOriginator(String originator) {
        this.originator = originator;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public String getReferFrame() {
        return referFrame;
    }

    public void setReferFrame(String referFrame) {
        this.referFrame = referFrame;
    }

    public String getTimeSystem() {
        return timeSystem;
    }

    public void setTimeSystem(String timeSystem) {
        this.timeSystem = timeSystem;
    }

    public String getMeanElemTheory() {
        return meanElemTheory;
    }

    public void setMeanElemTheory(String meanElemTheory) {
        this.meanElemTheory = meanElemTheory;
    }

    public String getEpoch() {
        return epoch;
    }

    public void setEpoch(String epoch) {
        this.epoch = epoch;
    }

    public float getMeanMotion() {
        return meanMotion;
    }

    public void setMeanMotion(float meanMotion) {
        this.meanMotion = meanMotion;
    }

    public float getEccentricity() {
        return eccentricity;
    }

    public void setEccentricity(float eccentricity) {
        this.eccentricity = eccentricity;
    }

    public float getInclination() {
        return inclination;
    }

    public void setInclination(float inclination) {
        this.inclination = inclination;
    }

    public float getRateOfAscNode() {
        return rateOfAscNode;
    }

    public void setRateOfAscNode(float rateOfAscNode) {
        this.rateOfAscNode = rateOfAscNode;
    }

    public float getArgOfPericenter() {
        return argOfPericenter;
    }

    public void setArgOfPericenter(float argOfPericenter) {
        this.argOfPericenter = argOfPericenter;
    }

    public float getMeanAnomaly() {
        return meanAnomaly;
    }

    public void setMeanAnomaly(float meanAnomaly) {
        this.meanAnomaly = meanAnomaly;
    }

    public float getEphermisType() {
        return ephermisType;
    }

    public void setEphermisType(float ephermisType) {
        this.ephermisType = ephermisType;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public String getNORADCatId() {
        return NORADCatId;
    }

    public void setNORADCatId(String NORADCatId) {
        this.NORADCatId = NORADCatId;
    }

    public int getElemSet() {
        return elemSet;
    }

    public void setElemSet(int elemSet) {
        this.elemSet = elemSet;
    }

    public int getRevAtEpoch() {
        return revAtEpoch;
    }

    public void setRevAtEpoch(int revAtEpoch) {
        this.revAtEpoch = revAtEpoch;
    }

    public float getBstar() {
        return bstar;
    }

    public void setBstar(float bstar) {
        this.bstar = bstar;
    }

    public float getMeanMotionDot() {
        return meanMotionDot;
    }

    public void setMeanMotionDot(float meanMotionDot) {
        this.meanMotionDot = meanMotionDot;
    }

    public float getMeanMotionDdot() {
        return meanMotionDdot;
    }

    public void setMeanMotionDdot(float meanMotionDdot) {
        this.meanMotionDdot = meanMotionDdot;
    }

    public float getSemimajorAxis() {
        return semimajorAxis;
    }

    public void setSemimajorAxis(float semimajorAxis) {
        this.semimajorAxis = semimajorAxis;
    }

    public float getPeriod() {
        return period;
    }

    public void setPeriod(float period) {
        this.period = period;
    }

    public float getApoapsis() {
        return apoapsis;
    }

    public void setApoapsis(float apoapsis) {
        this.apoapsis = apoapsis;
    }

    public float getPeriapsis() {
        return periapsis;
    }

    public void setPeriapsis(float periapsis) {
        this.periapsis = periapsis;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getRcsSize() {
        return rcsSize;
    }

    public void setRcsSize(String rcsSize) {
        this.rcsSize = rcsSize;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getLaunchDate() {
        return launchDate;
    }

    public void setLaunchSDate(String launchDate) {
        this.launchDate = launchDate;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getDecayRate() {
        return decayRate;
    }

    public void setDecayRate(String decayRate) {
        this.decayRate = decayRate;
    }

    public int getFile() {
        return file;
    }

    public void setFile(int file) {
        this.file = file;
    }

    public int getGpId() {
        return gpId;
    }

    public void setGpId(int gpId) {
        this.gpId = gpId;
    }

    public String getTleLine0() {
        return tleLine0;
    }

    public void setTleLine0(String tleLine0) {
        this.tleLine0 = tleLine0;
    }

    public String getTleLine1() {
        return tleLine1;
    }

    public void setTleLine1(String tleLine1) {
        this.tleLine1 = tleLine1;
    }

    public String getTleLine2() {
        return tleLine2;
    }

    public void setTleLine2(String tleLine2) {
        this.tleLine2 = tleLine2;
    }


    public double[] getCoords() {
        return coords;
    }

    public void setCoords(double[] coords) {
        this.coords = coords;
    }

    public OrbitalObject() {
    }

    private void calculateCoords() {

    }
}